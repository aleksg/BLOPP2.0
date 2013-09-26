package no.blopp.app.jsonposters;

import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;


/**
 * Used to add a medicine to a medicine plan. 
 * @author aarseth_90
 *
 */
public class AddMedicineToPlanPoster extends DatabasePoster
{
	public static final String TAG = AddMedicineToPlanPoster.class.getSimpleName();
	private boolean isSuccess;
	private static final String phpPage = "add_plan_dose.php/";
	/**
	 * 
	 * @param params. A string of URLEncoded data, containing the POST-parameters
	 */
	public AddMedicineToPlanPoster(String params)
	{
		super(params, phpPage);
	}
	
	public void initializeDataFromJSON(String result)
	{
		JSONObject object;
		try
		{
			object = new JSONObject(result);
			this.isSuccess = object.getBoolean("sqlsuccess");
			
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		Log.d(TAG, "Successfully added a medicine to the plan");
	}
	
	/**
	 * We don't need any result other than wether the SQL-query was successful
	 * @return
	 */
	public boolean getPlanSuccessfullyPosted()
	{
		return this.isSuccess;
	}

}
