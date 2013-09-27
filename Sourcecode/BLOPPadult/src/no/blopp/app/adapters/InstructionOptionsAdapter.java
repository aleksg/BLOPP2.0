package no.blopp.app.adapters;


import no.blopp.app.conf.R;
import no.blopp.app.utils.OptionsIdentifier;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class InstructionOptionsAdapter extends BaseAdapter
{
	
//	public String[] options;
	private String medicineName;
	private MedicineListModel medicineListModel;
	private Context context;
	private InstructionsOptionsModel[] options;
	private InstructionsOptionsModel optionsModel;
	public InstructionOptionsAdapter(String medicinename, String desc, Context context)
	{
		optionsModel = new InstructionsOptionsModel();
		
		options = optionsModel.getModel(medicinename);
		this.medicineName = medicinename;
		medicineListModel = new MedicineListModel(BitmapFactory.decodeResource(context.getResources(), getBitmapResourceId(medicinename)), medicinename, desc);
		this.context = context;
		
	}
	private int getBitmapResourceId(String medicineName)
	{
		if(medicineName.equals("Flutide"))
		{
			return R.drawable.medicine_flutide_125microg;
		}else if(medicineName.equals("Seretide"))
		{
			return R.drawable.medicine_seretide_250microg;
		}
		else if(medicineName.equals("Ventoline"))
		{
			return R.drawable.medicine_ventoline_100microg;
		}
		return R.drawable.medicine_flutide_125microg;
		
	}
	
	public int getCount()
	{
		// TODO Auto-generated method stub
		return options.length + 1;
	}

	public Object getItem(int position)
	{
		if(position==0)
		{
			return this.medicineListModel;
		}
		// TODO Auto-generated method stub
		return options[position-1];
	}

	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View medicineOptionsListView;

		if (convertView == null)
		{

			medicineOptionsListView = new View(context);
			
			if(position == 0)
			{
				medicineOptionsListView = inflater.inflate(R.layout.instructions_medicine_list_item, parent,
						false);

				ImageView imageView = (ImageView) medicineOptionsListView
						.findViewById(R.id.medicine_imageView);
				imageView.setImageBitmap(medicineListModel.getBitmap());
				imageView.setPadding(10, 10, 0, 0);
				TextView nameView = (TextView) medicineOptionsListView
						.findViewById(R.id.medicine_name_textView);
				nameView.setText(medicineListModel.getName());
				nameView.setTextColor(Color.BLACK);
				nameView.setPadding(10, 10, 0, 0);
				TextView descView = (TextView) medicineOptionsListView
						.findViewById(R.id.medicine_short_desc_textView);
				descView.setText(medicineListModel.getDescription());
				descView.setTextColor(Color.BLACK);
				descView.setPadding(10,10,0,0);
			}
			else{
				medicineOptionsListView = inflater.inflate(R.layout.standard_text_list_item, parent,
						false);
				TextView textView = (TextView) medicineOptionsListView.findViewById(R.id.list_item_textView);
				textView.setText(options[position-1].getOption());
				textView.setTextColor(Color.BLACK);
				textView.setPadding(10, 5, 15, 0);
				
			}

		} else
		{
			medicineOptionsListView = convertView;
		}
		return medicineOptionsListView;
		
	}
	
	
	public class InstructionsOptionsModel
	{
		private OptionsIdentifier optionId;
		private String option;
		public InstructionsOptionsModel(String option, OptionsIdentifier optionId)
		{
			this.option = option;
			this.optionId = optionId;
		}
		public InstructionsOptionsModel()
		{
			
		}
		public String getOption()
		{
			return this.option;
		
		}
		
		public OptionsIdentifier getOptionsId()
		{
			return this.optionId;
		}
		
		public InstructionsOptionsModel[] getModel(String medicineName){ 
			
			InstructionsOptionsModel[] model = new InstructionsOptionsModel[3];
			model[0] = new InstructionsOptionsModel("Hva " + medicineName + " er, og hva det brukes mot", OptionsIdentifier.WHAT);
			model[1] = new InstructionsOptionsModel("Viktig informasjon om "+medicineName, OptionsIdentifier.INFO);		
			model[2] = new InstructionsOptionsModel("Hvordan du bruker " + medicineName, OptionsIdentifier.USAGE);
			return model;
		}

	}

}
