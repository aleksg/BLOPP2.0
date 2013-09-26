package no.blopp.app.adapters;

import java.util.HashMap;
import no.blopp.app.conf.R;
import no.blopp.app.repositories.AvailableMedicines;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * Used by a listview to show how many times a medicine has been taken at a given day
 * @author aarseth_90
 */
public class TakenMedicinesAdapter extends BaseAdapter 
{
	
	private Context context;
	
	//medicines[0][i] = medicine taken
	//medicines[i][0] = number of medicines taken
	private String[][] medicines;
	
	public TakenMedicinesAdapter(Context context, HashMap<Integer, Integer> medicinesTaken)
	{
		this.context = context;
		medicines = new String[3][2];
		buildStringArray(medicinesTaken);
	}
	public int getCount()
	{
		return medicines.length;
	}
	public Object getItem(int arg0)
	{
		return medicines[arg0][0];
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
			
			if (convertView == null) {
	 
				listView = new View(context);
				
				listView = inflater.inflate(R.layout.daily_details, parent, false);
				listView.setPadding(20, 5, 0, 0);
				
				TextView medicineNameView = (TextView) listView.findViewById(R.id.daily_details_type_textview);
				medicineNameView.setText(medicines[position][0]);
				medicineNameView.setTextColor(Color.BLACK);
				
				TextView amountOfMedicineTakenView = (TextView) listView
						.findViewById(R.id.daily_details_state_textview);
				amountOfMedicineTakenView.setText(medicines[position][1]);
				amountOfMedicineTakenView.setTextColor(Color.BLACK);
			} else {
				listView = convertView;
			}
			return listView;
	}
	/**
	 * Builds the String array shown to the user, given it's input of medicinestaken
	 * @param medicineTaken
	 */
	public void buildStringArray(HashMap<Integer, Integer> medicineTaken)
	{
		//map<id, taken>
		AvailableMedicines am = new AvailableMedicines();
		
		for(int i=0; i<medicines.length; i++)
		{
			this.medicines[i][0] = am.getMedicineById(i+1);
			this.medicines[i][1] = (medicineTaken.containsKey(i+1) ? Integer.toString(medicineTaken.get(i+1)) : Integer.toString(0));
		}
		
	}
	/**
	 * Called when the user changes a cell. We then need to redraw our view. 
	 * @param map (medicineId, amountOfTimesTaken) 
	 */
	public void setAmountOfMedicinesTaken(HashMap<Integer, Integer> map)
	{
		buildStringArray(map);
		
	}
	
	
}
