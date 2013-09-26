package no.blopp.app.jsonposters;



import no.blopp.app.models.JsonModels.RegisterTreatmentResult;

import org.json.JSONException;
import org.json.JSONObject;


public class PostRegisterTreatment extends DatabasePoster
{
	public static final String phpPage = "register_medicine_taken.php/";
	private RegisterTreatmentResult treatmentResult;
	public PostRegisterTreatment(String params)
	{
		super(params, phpPage);
	}

	public void initializeDataFromJSON(String result)
	{
		JSONObject json_data;
		this.treatmentResult = new RegisterTreatmentResult();
		try
		{
			json_data = new JSONObject(result);
			treatmentResult.setReward(json_data.getInt("reward"));
			treatmentResult.setSqlSuccess(json_data.getBoolean("sqlsuccess"));
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
	}
	public RegisterTreatmentResult getTreatmentResult()
	{
		return this.treatmentResult;
	}

}

