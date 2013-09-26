package no.blopp.app.adapters;

import java.util.HashMap;

import no.blopp.app.conf.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MedicineRadioAdapter extends BaseAdapter implements OnClickListener 
{
	private static final String TAG = MedicineRadioAdapter.class.getSimpleName();
	
	private Context context;
	private MedicineListModel[] medicines;
	private HashMap<CheckBox, Integer> checkList;
	private MedicineListModel medicineChecked = null;
	public MedicineRadioAdapter(Context c)
	{
		this.context = c;
		checkList = new HashMap<CheckBox, Integer>();
		initialize();
	}
	
	private void initialize()
	{
		medicines = new MedicineListModel[3];
		Bitmap b1 = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.flutide_small);
		
		MedicineListModel item1 = new MedicineListModel(b1, "Flutide",
				"Inhalasjonsaerosol");
		
		Bitmap b2 = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.ventolide_small);
		MedicineListModel item2 = new MedicineListModel(b2, "Ventoline",
				"Inhalasjonsaerosol");
		
		Bitmap b3 = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.seretide_small);
		MedicineListModel item3 = new MedicineListModel(b3, "Seretide",
				"Inhalasjonsaerosol");
		
		medicines[0] = item1;
		medicines[1] = item3;
		medicines[2] = item2;
	}
	
	public int getCount() 
	{		
		return medicines.length;
	}

	public MedicineListModel getItem(int position) 
	{	
		return medicines[position];
	}

	public long getItemId(int position) 
	{
		
		return 0;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		LayoutInflater inflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View medicineListView;
		
		if (convertView == null)
		{

			medicineListView = new View(context);
			medicineListView.setPadding(30, 0, 0, 0);
			medicineListView = inflater.inflate(R.layout.register_medicine_item, parent,
					false);

			ImageView imageView = (ImageView) medicineListView
					.findViewById(R.id.register_medicine_item_imageview);
			imageView.setImageBitmap(medicines[position].getBitmap());
			imageView.setPadding(15, 0, 0, 0);
			
			
			CheckBox checkBox = (CheckBox) medicineListView.findViewById(R.id.register_medicine_checkBox);
			checkBox.setOnClickListener(this);
			checkBox.setText("");
			checkBox.setPadding(10, -15, 0, 0);
			//There is a bug in the framework, that confuses this view a bit. 
			//We need this to control the size of number of checkboxes.
			if (checkList.size() < 3)
			{
				checkList.put(checkBox, position);
			}
			
			TextView nameView = (TextView) medicineListView
					.findViewById(R.id.register_medicine_item_textview);
			nameView.setText(medicines[position].getName());
			nameView.setTextColor(Color.BLACK);
			nameView.setPadding(30, 15,0,0);

		} else
		{
			medicineListView = convertView;
		}
		return medicineListView;
	}
	
	/**
	 * 
	 * @return full list of medicines
	 */
	public MedicineListModel[] getMedicineListModel()
	{
		return medicines;
	}

	/**
	 * Set all other buttons to false, and set medicineChecked to the new checked item
	 */
	public void onClick(View v)
	{
		
		for(CheckBox c : checkList.keySet())
		{
			if(c.equals(v))
			{
				c.setChecked(true);
				medicineChecked = getItem(checkList.get(c));
			}else{
				c.setChecked(false);
			}
		}
	}
	/**
	 * 
	 * @return the medicineListModel that is currently checked
	 */
	public MedicineListModel getCheckedItem()
	{
		
		return medicineChecked;
	}
	

}
