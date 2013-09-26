package no.blopp.app.med.JsonModels;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.util.Log;

public class RegisterMedicinePostModel
{
	private String date;
	private int medicineId;
	private int healthStateId;
	private int childId;
	public RegisterMedicinePostModel( String date, int medicineId, int childId, int healthId)
	{		
		this.date = date;
		this.medicineId = medicineId;
		this.healthStateId = healthId;
		this.childId = childId;	
	}
	
	@Override
	public String toString()
	{
		String child, dato, medisinId, healthState;
		try
		{
			child = URLEncoder.encode(Integer.toString(childId), "UTF-8");
			dato = URLEncoder.encode(date, "UTF-8");
			medisinId = URLEncoder.encode(Integer.toString(medicineId), "UTF-8");
			healthState = URLEncoder.encode(Integer.toString(healthStateId), "UTF-8");
			Log.d("RegisterMedicine", "child_id="+child + "&day_date=" + dato + "&medicine_id" + medisinId + "&health_state_id=" + healthState);
			return "child_id="+child + "&day_date=" + dato + "&medicine_id=" + medisinId + "&health_state_id=" + healthState; 
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
