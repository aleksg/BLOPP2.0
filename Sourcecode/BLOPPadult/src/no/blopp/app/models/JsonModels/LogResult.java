package no.blopp.app.models.JsonModels;

import java.util.ArrayList;


public class LogResult
{
	private boolean sqlSuccess;
	private int childId;
	private int month, year;
	private String query;
	private ArrayList<LogDayResult> logDayResults;
	public LogResult()
	{
		logDayResults = new ArrayList<LogDayResult>();
	}
	public ArrayList<LogDayResult> getLogDayResults()
	{
		return logDayResults;
	}
	public void setLogDayResults(ArrayList<LogDayResult> logDayResults)
	{
		this.logDayResults = logDayResults;
	}
	public boolean isSqlSuccess()
	{
		return sqlSuccess;
	}
	public void setSqlSuccess(boolean sqlSuccess)
	{
		this.sqlSuccess = sqlSuccess;
	}
	public int getChildId()
	{
		return childId;
	}
	public void setChildId(int childId)
	{
		this.childId = childId;
	}
	public int getMonth()
	{
		return month;
	}
	public void setMonth(int month)
	{
		this.month = month;
	}
	public int getYear()
	{
		return year;
	}
	public void setYear(int year)
	{
		this.year = year;
	}
	public String getQuery()
	{
		return query;
	}
	public void setQuery(String query)
	{
		this.query = query;
	}
	
	//TODO: Fix dates as SimpleDateFormat or something??
	public int getHealthStateIdByDate(String d)
	{
		for (LogDayResult result : logDayResults)
		{
			String date = result.getDate();
			if (date.equalsIgnoreCase(d))
			{
				return result.getHealthStateId();
			}
		}
		return -1;
	}
	
	

}
