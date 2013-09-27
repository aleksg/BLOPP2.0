package no.blopp.app.med.activities;

import no.blopp.app.med.R;
import no.blopp.app.med.models.ChildRewards;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayRewardsActivity extends Activity
{
	GridView gridView;
	TextView countView;
	
	private int childId = 6;
	private ChildRewards childRewards;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.rewards);
		
		gridView = (GridView) findViewById(R.id.rewardsView);
		countView = (TextView) findViewById(R.id.displayRewardsTextView);
		
		childRewards = new ChildRewards(childId);
		childRewards.initChildModelParser();
		
		gridView.setAdapter(new StarAdapter(this));
		countView.setText(Integer.toString(childRewards.getCredits()));
	}
	
	public class StarAdapter extends BaseAdapter
	{
		private Context context;
		
		public StarAdapter(Context context)
		{
			this.context = context;
		}

		public int getCount()
		{
			return childRewards.getCredits() + 1;
		}

		public Object getItem(int position)
		{
			return position;
		}

		public long getItemId(int position)
		{
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent)
		{
			ImageView imageView;
			if (convertView == null)
			{
				imageView = new ImageView(context);
				imageView.setLayoutParams(new GridView.LayoutParams(64,64));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(0,0,0,0);
			}
			else
			{
				imageView = (ImageView) convertView;
			}
			imageView.setImageResource(R.drawable.star);
			return imageView;
		}
	}
}
