package no.blopp.app.jsonposters;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Used to remove a medicine from a plan. Handles the database.
 * @author aarseth_90
 *
 */
public class DeleteMedicineFromPlanPoster extends DatabasePoster {
	private static final String PHP_STRING = "remove_plan_medicine_at_time?";
	private boolean success;
	/**
	 * 
	 * @param params, a String containing URLEncoded POST-parameters
	 */
	public DeleteMedicineFromPlanPoster(String params) {
		super(params, PHP_STRING);
	}

	public void initializeDataFromJSON(String result) {
		JSONObject data;
		try {
			data = new JSONObject(result);
			success = data.getBoolean("sqlsuccess");
		} catch (JSONException e) {
			
			//TODO: Handle appropriately??
			Log.d(DeleteMedicineFromPlanPoster.class.getSimpleName(), "An exception while handling json has occured");
			e.printStackTrace();
		}
	}
	public boolean isSuccess()
	{
		return this.success;
	}

}
