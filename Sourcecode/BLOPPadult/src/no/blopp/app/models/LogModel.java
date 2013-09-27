package no.blopp.app.models;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;


import no.blopp.app.jsonparsers.LogModelParser;
import no.blopp.app.models.JsonModels.LogDayResult;
import no.blopp.app.models.JsonModels.LogDosesModel;



import android.util.Log;

public class LogModel
{
	private int childId;
	protected LogModelParser logModelParser;
	private static final String TAG = LogModel.class.getSimpleName();
	public LogModel(int childId)
	{
		this.childId = childId;
		logModelParser = new LogModelParser(childId);
		initLogModelParser();
	}
	public LogModel(int childId, int month, int year)
	{
		this.childId=childId;
		logModelParser = new LogModelParser(childId, month, year);
		initLogModelParser();
	}
	
	/**
	 * Initializes LogModelParser and executes it. After this is called, 
	 * one may call LogModelParser.getResult();
	 */
	public void initLogModelParser()
	{
		logModelParser.execute();
		try
		{
			logModelParser.get();
		} catch (InterruptedException e)
		{
			
			Log.e(TAG, e.getMessage());
			return;
		} catch (ExecutionException e)
		{
			Log.e(TAG, e.getMessage());
			return;
		}
	}
	/**
	 * Used by CalendarView to find the healthstate of a given day
	 * @param date
	 * @return
	 */
	public HealthZone getHealthZoneAtDay(String date)
	{
//		Log.d(TAG, date);
		int healthZoneId = logModelParser.getLogResult().getHealthStateIdByDate(date);
		
		if (healthZoneId!=-1)
		{
			return HealthState.getHealthZoneById(healthZoneId);
		} else
		{
			return HealthZone.GREEN_ZONE;
		}
	}

	/**
	 * 
	 * @param date - The date searched for formatted as String ("yyyy-MM-dd")
	 * @return mappping with medicineId as keys, amount of medicine taken as value.
	 */
	public HashMap<Integer, Integer> getAmountOfMedicineAtDate(String date)
	{
		HashMap<Integer, Integer> medicinesTakenAtDate = new HashMap<Integer, Integer>();
		
		for (LogDayResult ldr : logModelParser.getLogResult().getLogDayResults())
		{
			
			String loggedDate = ldr.getDate();
			
			if(loggedDate.equalsIgnoreCase(date))
			{
				for (LogDosesModel dosesModel : ldr.getLogDosesList())
				{
					if(ldr.getLogDosesList().isEmpty()){Log.d(TAG, "Log doses is empty. Error?");}
					int medicineId = dosesModel.getMedicineId();
					/*
					 * Accumulate results from past dosesModels.
					 */
					if(medicinesTakenAtDate.containsKey(medicineId))
					{
						int acc = medicinesTakenAtDate.get(medicineId);	
						medicinesTakenAtDate.remove(medicineId);
						medicinesTakenAtDate.put(medicineId, acc+1);
					}else
					{
						medicinesTakenAtDate.put(medicineId, 1);
					}
				}
				
			}
		}
		return medicinesTakenAtDate;
	}
	

}
