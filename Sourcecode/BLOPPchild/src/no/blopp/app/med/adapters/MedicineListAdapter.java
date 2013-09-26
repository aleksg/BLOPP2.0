package no.blopp.app.med.adapters;

import no.blopp.app.med.R;
import no.blopp.app.med.models.Medicine;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MedicineListAdapter extends BaseAdapter 
{
	private Medicine[] medicines;
	private Context context;

	public MedicineListAdapter(Context c, Medicine[] medicines)
	{
		this.context = c;
		this.medicines = medicines;
	}

	public int getCount()
	{
		return medicines.length;
	}

	public Object getItem(int position)
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

			medicineListView = inflater.inflate(R.layout.medicine_list_item, parent,
					false);

			ImageView medicineIconView = (ImageView) medicineListView
					.findViewById(R.id.medicine_imageView);
			medicineIconView.setImageBitmap(medicines[position].getBitmap());
			medicineIconView.setPadding(10, 5, 0, 0);
			TextView medicineNameView = (TextView) medicineListView
					.findViewById(R.id.medicine_name_textView);
			medicineNameView.setText(medicines[position].getName());
			medicineNameView.setTextColor(Color.BLACK);
			medicineNameView.setPadding(10,15,0,0);
			
			TextView medicineDescriptionView = (TextView) medicineListView
					.findViewById(R.id.medicine_short_desc_textView);
			medicineDescriptionView.setText("");
			medicineDescriptionView.setTextColor(Color.BLACK);
			medicineDescriptionView.setPadding(10,5,0,0);

		} else
		{
			medicineListView = convertView;
		}
		return medicineListView;

	}

	

}
