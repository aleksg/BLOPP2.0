package no.blopp.app.models.JsonModels;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.util.Log;

public class AddMedicineToPlanModel
{
	private int childId;
	private int healthStateId;
	private String medicineTime;
	private int medicineId;
	public AddMedicineToPlanModel(int childId, int healthStateId,
			String medicineTime, int medicineId)
	{
		super();
		this.childId = childId;
		this.healthStateId = healthStateId;
		this.medicineTime = medicineTime;
		this.medicineId = medicineId;
	}
	public AddMedicineToPlanModel()
	{

	}
	public int getChildId()
	{
		return childId;
	}
	public void setChildId(int childId)
	{
		this.childId = childId;
	}
	public int getHealthStateId()
	{
		return healthStateId;
	}
	public void setHealthStateId(int healthStateId)
	{
		this.healthStateId = healthStateId;
	}
	public String getMedicineTime()
	{
		return medicineTime;
	}
	public void setMedicineTime(String medicineTime)
	{
		this.medicineTime = medicineTime;
	}
	public int getMedicineId()
	{
		return medicineId;
	}
	public void setMedicineId(int medicineId)
	{
		this.medicineId = medicineId;
	}
	
	@Override
	public String toString()
	{
		String child, time, medisinId, status;
		
		try
		{
			child = URLEncoder.encode(Integer.toString(childId), "UTF-8");
			time = URLEncoder.encode(medicineTime, "UTF-8");
			medisinId = URLEncoder.encode(Integer.toString(medicineId), "UTF-8");
			status = URLEncoder.encode(Integer.toString(healthStateId), "UTF-8");
			Log.d("RegisterMedicine", "child_id="+child + "&time=" + time + "&medicine_id" + medisinId + "&health_state_id=" + status);
			return "child_id="+child + "&time=" + time + "&medicine_id=" + medisinId + "&health_state_id=" + status; 
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
