package no.blopp.app.med.jsonparsers;

import no.blopp.app.med.JsonModels.ChildResultModel;
import no.blopp.app.med.jsonparsers.GenericParser;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ChildModelParser extends GenericParser
{
	public static final String PHPPAGE = "get_child.php";
	private ChildResultModel child;
	
	public ChildModelParser(int childId)
	{
		super(PHPPAGE + "?child_id=" + childId);
		TAG = this.getClass().getSimpleName();
	}
	
	public void initializeDataFromJSON(String result)
	{
		JSONObject json_data;
		try
		{
			json_data = new JSONObject(result);
			this.child = new ChildResultModel();
			JSONObject information = json_data.getJSONObject("information");
			child.setId(information.getInt("id"));
			child.setName(information.getString("name"));
			child.setPersNum(information.getInt("pers_num"));
			child.setCredits(information.getInt("credits"));
			child.setMedicalPlanId(information.getInt("medical_plan_id"));
			child.setAvatarId(information.getInt("avatar_id"));
			child.setLocationAltitude(information.getDouble("location_altitude"));
			child.setLocationLatitude(information.getDouble("location_latitude"));
		} catch (JSONException e)
		{
			Log.e(TAG, e.getMessage());
		}

	}
	public ChildResultModel getLogResult()
	{
		return this.child;
	}
}
