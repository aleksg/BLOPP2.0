package no.blopp.app.jsonparsers;


import org.json.JSONException;
import org.json.JSONObject;
/**
 * Used to get the current healthstate of the child
 */
public class HealthStateParser extends GenericJSONParser
{
	private static String PHP_PAGE = "get_child_state?";
	private int healthStateId = 0;
	private boolean sqlsuccess = false;

	public HealthStateParser(String urltail)
	{
		super(PHP_PAGE + urltail);
	}
	public HealthStateParser(int childId)
	{
		super(PHP_PAGE + "child_id=" + childId);
	}
	public void initializeDataFromJSON(String result)
	{
		JSONObject jsonData;
		try
		{
			jsonData = new JSONObject(result);
			sqlsuccess = jsonData.getBoolean("sqlsuccess");

			JSONObject state = jsonData.getJSONObject("state");
			this.healthStateId = Integer.parseInt(state.getString("id"));

		} catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public int getHealthStateId()
	{
		return this.healthStateId;
	}

	public boolean isSqlSuccess()
	{
		return this.sqlsuccess;
	}
}
