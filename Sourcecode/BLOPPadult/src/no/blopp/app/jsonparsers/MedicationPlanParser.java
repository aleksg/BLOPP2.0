package no.blopp.app.jsonparsers;

import java.util.ArrayList;

import no.blopp.app.models.MedicinePlanModel;
import no.blopp.app.models.JsonModels.MedicationPlanResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MedicationPlanParser extends GenericJSONParser
{
	public static String phpPage = "get_plan.php?";
	private MedicationPlanResult medicationPlanResult;
	public MedicationPlanParser(int child_id)
	{
		
		super(phpPage + "child_id=" + child_id);		
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
				
				
				JSONObject plan = array.getJSONObject(i);
				int healthStateId = (plan.getInt("health_state_id"));
				MedicinePlanModel mpl = elementExists(arrayList, healthStateId);
				
				if(elementExists(arrayList, healthStateId)!=null)
				{
					String time = (plan.getString("time"));
					String name = (plan.getString("medicine_name"));
					mpl.addEntryToMap(time, name);
				}else
				{
					mpl = new MedicinePlanModel();
					mpl.setHealthStateId(plan.getInt("health_state_id"));
					mpl.setId(plan.getInt("id"));
					mpl.setMedicalPlanId(plan.getInt("medical_plan_id"));
					mpl.setMedicineId(plan.getInt("medicine_id"));
					String time = plan.getString("time");
					String medicineName = plan.getString("medicine_name");
					mpl.addEntryToMap(time, medicineName);
					arrayList.add(mpl);
				}
				
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
	public MedicinePlanModel elementExists(ArrayList<MedicinePlanModel> arrayList, int healthStateId)
	{
		for (MedicinePlanModel model : arrayList)
		{
			if(model.getHealthStateId()==healthStateId){
				return model;
			}
			
		}
		return null;
	}
	

}
