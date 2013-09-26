package no.blopp.app.med.jsonparsers;

import java.util.ArrayList;

import no.blopp.app.med.models.MedicinePlanModel;
import no.blopp.app.med.models.JsonModels.MedicationPlanResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MedicationPlanParser extends GenericParser
{
	public static String phpPage = "get_doses_for_current_state.php?";
	private MedicationPlanResult medicationPlanResult;
	public MedicationPlanParser(int child_id)
	{
		
		super(phpPage + "child_id=" + child_id);
		// TODO Auto-generated constructor stub
	}

	public void initializeDataFromJSON(String result)
	{
		JSONObject json_data;
		try
		{
			json_data = new JSONObject(result);
			medicationPlanResult = new MedicationPlanResult();
			
			medicationPlanResult.setChildId(Integer.parseInt(json_data.getString("child_id")));
			medicationPlanResult.setQuery(json_data.getString("query"));
			medicationPlanResult.setSqlSuccess(json_data.getBoolean("sqlsuccess"));
			JSONArray array = json_data.getJSONArray("rows");
			ArrayList<MedicinePlanModel> arrayList = new ArrayList<MedicinePlanModel>();
			
			for(int i=0; i<array.length(); i++)
			{
				MedicinePlanModel mpl = new MedicinePlanModel();
				JSONObject plan = array.getJSONObject(i);
				mpl.setHealthStateId(plan.getInt("health_state_id"));
				mpl.setId(plan.getInt("id"));
				mpl.setMedicalPlanId(plan.getInt("medical_plan_id"));
				mpl.setTime(plan.getString("time"));
				mpl.setMedicineId(plan.getInt("medicine_id"));
				mpl.setMedicineName(plan.getString("medicine_name"));
				mpl.setMedicineColor(plan.getString("medicine_color"));
				arrayList.add(mpl);
			}
			medicationPlanResult.setPlans(arrayList);
			
		} catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public MedicationPlanResult medicationPlanResult()
	{
		return this.medicationPlanResult;
	}
	

}
