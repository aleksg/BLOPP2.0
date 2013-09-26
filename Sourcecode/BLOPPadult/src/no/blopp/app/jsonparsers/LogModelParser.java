package no.blopp.app.jsonparsers;

import java.util.ArrayList;

import no.blopp.app.models.JsonModels.LogDayResult;
import no.blopp.app.models.JsonModels.LogDosesModel;
import no.blopp.app.models.JsonModels.LogResult;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class LogModelParser extends GenericJSONParser
{
	private LogResult logResult;
	private static final String PHPPAGE = "get_log_days_for_child.php?",
			TAG = LogModelParser.class.getSimpleName();

	public LogModelParser(String urltail)
	{
		super(urltail);
	}

	public LogModelParser(int childId)
	{
		super(PHPPAGE + "child_id=" + childId);
		Log.d(TAG, URL);
	}

	public LogModelParser(int childId, int month, int year)
	{
		super(PHPPAGE + "child_id=" + childId + "&month=" + month + "&year="
				+ year);
		Log.d(TAG, URL);
		
	}

	public void initializeDataFromJSON(String result)
	{
		JSONObject json_data;
		try
		{
			json_data = new JSONObject(result);
			this.logResult = new LogResult();
			logResult.setSqlSuccess(json_data.getBoolean("sqlsuccess"));
			logResult.setMonth(Integer.parseInt(json_data.getString("month")));
			logResult.setYear(Integer.parseInt(json_data.getString("year")));
			logResult.setQuery(json_data.getString("query"));
			logResult.setChildId(Integer.parseInt(json_data
					.getString("child_id")));
			JSONArray array = (json_data.getJSONArray("days"));
			ArrayList<LogDayResult> logDayResults = new ArrayList<LogDayResult>();

			for (int i = 0; i < array.length(); i++)
			{
				LogDayResult log_day = new LogDayResult();
				JSONObject day = array.getJSONObject(i);
				
				log_day.setDate(day.getString("date"));
				log_day.setHealthStateId(Integer.parseInt(day.getString("health_state_id")));
				JSONArray doses = day.getJSONArray("doses");
				
				log_day.setLogDosesList(initDosesModel(doses));
				logDayResults.add(log_day);

			}
			logResult.setLogDayResults(logDayResults);

		} catch (JSONException e)
		{
			Log.e(TAG, e.getMessage());
		}

	}
	private ArrayList<LogDosesModel> initDosesModel(JSONArray doses)
	{
		ArrayList<LogDosesModel> modelArray = new ArrayList<LogDosesModel>();
		for (int i = 0; i<doses.length(); i++)
		{
			LogDosesModel model = new LogDosesModel();
			try
			{
				JSONObject dose = doses.getJSONObject(i);
				model.setId(Integer.parseInt(dose.getString("id")));
				model.setHealthStateId(Integer.parseInt(dose.getString("health_state_id")));
				model.setTime(dose.getString("time"));
				model.setDate(dose.getString("day_date"));
				model.setChildId(Integer.parseInt(dose.getString("child_id")));
				model.setReward(Integer.parseInt(dose.getString("reward")));
				model.setMedicineId(Integer.parseInt(dose.getString("medicine_id")));
				model.setPlanId(Integer.parseInt(dose.getString("medical_plan_dose_id")));
				model.setPollenStateId(Integer.parseInt(dose.getString("pollen_state_id")));
			} catch (JSONException e)
			{
				e.printStackTrace();
			}
			modelArray.add(model);
			
		}
		
		return modelArray;
	}
	public LogResult getLogResult()
	{
		return this.logResult;
	}

}
