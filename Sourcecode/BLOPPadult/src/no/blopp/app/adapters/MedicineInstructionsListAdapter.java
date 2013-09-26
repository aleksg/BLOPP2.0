package no.blopp.app.adapters;

import no.blopp.app.conf.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MedicineInstructionsListAdapter extends BaseAdapter 
{
	private MedicineListModel[] medicines;
	private Context context;

	public MedicineInstructionsListAdapter(Context c)
	{
		this.context = c;
		initialize();
	}
	
	private void initialize()
	{
		medicines = new MedicineListModel[3];
		Bitmap b1 = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.medicine_flutide_125microg);
		
		MedicineListModel item1 = new MedicineListModel(b1, "Flutide",
				"Inhalasjonsaerosol");
		Bitmap b2 = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.medicine_ventoline_100microg);
		MedicineListModel item2 = new MedicineListModel(b2, "Ventoline",
				"Inhalasjonsaerosol");
		
		Bitmap b3 = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.medicine_seretide_250microg);
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

			medicineListView = inflater.inflate(R.layout.instructions_medicine_list_item, parent,
					false);

			ImageView medicineIconView = (ImageView) medicineListView
					.findViewById(R.id.medicine_imageView);
			medicineIconView.setImageBitmap(medicines[position].getBitmap());
			medicineIconView.setPadding(10, 5, 0, 0);
			TextView medicineNameView = (TextView) medicineListView
					.findViewById(R.id.medicine_name_textView);
			medicineNameView.setText(medicines[position].getName());
			medicineNameView.setTextColor(Color.BLACK);
			medicineNameView.setPadding(10,5,0,0);
			
			TextView medicineDescriptionView = (TextView) medicineListView
					.findViewById(R.id.medicine_short_desc_textView);
			medicineDescriptionView.setText(medicines[position].getDescription());
			medicineDescriptionView.setTextColor(Color.BLACK);
			medicineDescriptionView.setPadding(10,5,0,0);

		} else
		{
			medicineListView = convertView;
		}
		return medicineListView;

	}

	

}
