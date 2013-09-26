package no.blopp.app.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.joda.time.DateTime;

import no.blopp.app.conf.R;
import no.blopp.app.jsonposters.HealthStatePoster;
import no.blopp.app.models.JsonModels.*;
import no.blopp.app.models.HealthState;
import no.blopp.app.models.HealthZone;
import no.blopp.app.models.LogModel;
import no.blopp.app.utils.DateAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**
 * An activity that lists all the health states, where you can press each
 * healthstate to view the medicationplan for that state.
 */
public class MedicationPlanActivity extends Activity implements
		OnItemClickListener
{
	private static final String TAG = MedicationInformationActivity.class
			.getSimpleName();
	private static final int CHILD_ID = 6;
	private ArrayList<HealthZone> healthZones;
	private ListView medicationPlanListView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Started activity " + TAG);
		setContentView(R.layout.medication_plan_list);
		medicationPlanListView = (ListView) findViewById(R.id.medication_plan_listView);
		medicationPlanListView.setDivider(null);
		healthZones = new ArrayList<HealthZone>();
		initializeList();

		medicationPlanListView.setAdapter(new MedicationPlanAdapter(
				getApplicationContext()));
		medicationPlanListView.setOnItemClickListener(this);
	}

	/**
	 * Makes and returns an OnItemClickListener for the listview in this class
	 */
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long arg3)
	{

		Intent intent = new Intent(MedicationPlanActivity.this,
				ViewMedicationPlanActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("healthZone", healthZones.get(position));
		
		intent.putExtras(bundle);
		startActivity(intent);

	}
	
	/**
	 * Updates the list model behind the listview with the information lying in
	 * the repository.
	 */
	private void initializeList()
	{
		
		healthZones.add(HealthZone.GREEN_ZONE);
		healthZones.add(HealthZone.YELLOW_ZONE);
		healthZones.add(HealthZone.RED_ZONE);
	}

	public class MedicationPlanAdapter extends BaseAdapter implements
			OnClickListener
	{

		private Context context;
		private PlanListItem[] listItems;
		private HashMap<CheckBox, Integer> checkList;
		private int currentPlan;
		public MedicationPlanAdapter(Context ctx)
		{
			this.context = ctx;
			initArrays();
			checkList = new HashMap<CheckBox, Integer>();
			currentPlan = getCurrentMedicationPlan();
		}
		private int getCurrentMedicationPlan()
		{
			LogModel logModel = new LogModel(6);
			DateTime date = new DateTime();
			DateAdapter adapter = new DateAdapter(date.getDayOfMonth(), date.getMonthOfYear(), date.getYear());
			return HealthState.getIdByHealthZone(logModel.getHealthZoneAtDay(adapter.getSqlFormattedDate()));
		}
		
		private void initArrays()
		{
			this.listItems = new PlanListItem[3];
			listItems[0] = new PlanListItem("FRISK",
					BitmapFactory.decodeResource(getResources(),
							R.drawable.smiley), true);
			listItems[1] = new PlanListItem("LITT SYK",
					BitmapFactory.decodeResource(getResources(),
							R.drawable.mellowie), false);
			listItems[2] = new PlanListItem("SYK",
					BitmapFactory.decodeResource(getResources(),
							R.drawable.sadie), false);
		}

		public int getCount()
		{

			return listItems.length;
		}

		public Object getItem(int position)
		{
			return listItems[position];
		}

		public long getItemId(int position)
		{
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent)
		{
			LayoutInflater inflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View listView;

			if (convertView == null)
			{

				listView = new View(context);

				listView = inflater.inflate(R.layout.plan_list_item, parent,
						false);

				ImageView iv = (ImageView) listView
						.findViewById(R.id.plan_smiley_imageView);
				iv.setImageBitmap(listItems[position].getSmiley());

				TextView planName = (TextView) listView
						.findViewById(R.id.plan_textView);
				planName.setText(listItems[position].getName());
				planName.setTextColor(Color.BLACK);

				CheckBox checkBox = (CheckBox) listView
						.findViewById(R.id.plan_checkBox);
				checkBox.setOnClickListener(this);
				if(position+1 == currentPlan)
				{
					checkBox.setChecked(true);
				}
				if (checkList.size() < 3)
				{
					checkList.put(checkBox, position+1);
				}

			} else
			{
				listView = convertView;
			}
			return listView;
		}

		public void onClick(View v)
		{
			int healthStateId = 0;
			for (CheckBox c : checkList.keySet())
			{
				if (c.equals(v))
				{
					healthStateId = checkList.get(c);
				}
				c.setChecked(false);
			}
			((CompoundButton) v).setChecked(true);
			changeHealthState(healthStateId);

		}

		/**
		 * A PlanListItem contains an image of a smiley, a name, and a boolean deciding whether the given plan is the current plan for the child
		 * 
		 * @author aarseth_90
		 * 
		 */
		public class PlanListItem
		{
			private String name;
			private Bitmap smiley;
			private boolean isActive;

			public PlanListItem(String name, Bitmap bitmap, boolean isActive)
			{
				this.name = name;
				this.smiley = bitmap;
				this.isActive = isActive;
			}

			public String getName()
			{
				return name;
			}

			public void setName(String name)
			{
				this.name = name;
			}

			public Bitmap getSmiley()
			{
				return smiley;
			}

			public void setSmiley(Bitmap smiley)
			{
				this.smiley = smiley;
			}

			public boolean isActive()
			{
				return isActive;
			}

			public void setActive(boolean isActive)
			{
				this.isActive = isActive;
			}

		}
	}

	private void changeHealthState(int healthStateId)
	{
		HealthStatePostModel model = new HealthStatePostModel();
		model.setChildId(CHILD_ID);
		DateTime dateTime = new DateTime();

		DateAdapter da = new DateAdapter(dateTime.getDayOfMonth(),
				dateTime.getMonthOfYear(), dateTime.getYear());
		
		model.setDayDate(da.getSqlFormattedDate());

		// Healthy if not working
		model.setHealthStateId(healthStateId != 0 ? healthStateId : 1);

		HealthStatePoster poster = new HealthStatePoster(model.toString());
		boolean b = executePoster(poster);
		if (b)
		{
			Toast.makeText(getApplicationContext(), "Du har skiftet medisinplan ",
					Toast.LENGTH_SHORT).show();
		} else
		{
			Toast.makeText(getApplicationContext(),
					R.string.post_error,
					Toast.LENGTH_SHORT).show();
		}
	}

	private boolean executePoster(HealthStatePoster poster)
	{
		poster.execute();
		try
		{
			poster.get();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
			return false;
		} catch (ExecutionException e)
		{
			e.printStackTrace();
			return false;
		}
		return poster.getHealthStatePostResult().isSqlSuccess();

	}

}
