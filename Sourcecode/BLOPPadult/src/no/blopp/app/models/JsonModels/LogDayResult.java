package no.blopp.app.models.JsonModels;

import java.util.ArrayList;

/**
 * The log for a given day.
 * Consists of a date and a list of losDosesModel, which in turn handles which medicines that is taken, the health state of the child, etc.
 * @author aarseth_90
 *
 */
public class LogDayResult 
{
	private int healthStateId;
	
	private String date;
	
	private ArrayList<LogDosesModel> logDosesList;
	
	public LogDayResult()
	{
		logDosesList = new ArrayList<LogDosesModel>();
	}
	
	public int getHealthStateId()
	{
		return healthStateId;
	}
	public void setHealthStateId(int health_state_id)
	{
		this.healthStateId = health_state_id;
	}
	
	public String getDate()
	{
		return date;
	}
	public void setDate(String date)
	{
		this.date = date;
	}
	
	public ArrayList<LogDosesModel> getLogDosesList()
	{
		return logDosesList;
	}
	public void setLogDosesList(ArrayList<LogDosesModel> logDosesList)
	{
		this.logDosesList = logDosesList;
	}

	
}
