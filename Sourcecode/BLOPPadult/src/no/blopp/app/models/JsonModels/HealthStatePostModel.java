package no.blopp.app.models.JsonModels;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.util.Log;

public class HealthStatePostModel
{

	private int childId, healthStateId;
	private String dayDate;
	
	public HealthStatePostModel(int childId, int healthStateId)
	{
		this.childId = childId;
		this.healthStateId = healthStateId;
	}
	public HealthStatePostModel()
	{
		// TODO Auto-generated constructor stub
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
	public String getDayDate()
	{
		return dayDate;
	}
	public void setDayDate(String dayDate)
	{
		this.dayDate = dayDate;
	}
	
	@Override
	/**
	 * Returns the URL-encoded POST-parameters to the webservice, at form "child_id=X&date=Y" etc. 
	 */
	public String toString()
	{
		String child, date, status;
		try
		{
			child = URLEncoder.encode(Integer.toString(childId), "UTF-8");
			date = URLEncoder.encode(dayDate, "UTF-8");
			status = URLEncoder.encode(Integer.toString(healthStateId), "UTF-8");
			Log.d("RegisterMedicine", "child_id="+child + "&day_date=" + date +  "&health_state_id=" + status);
			return "child_id="+child + "&date=" + date  + "&state_id=" + status; 
		} catch (UnsupportedEncodingException e)
		{
			//Hope that everything works out fine. Should never happen though
			e.printStackTrace();
		}
		return null;
	}
	

}
