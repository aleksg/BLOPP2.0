package no.blopp.app.models.JsonModels;

import java.util.ArrayList;

import no.blopp.app.models.MedicinePlanModel;

public class MedicationPlanResult
{

	private boolean sqlSuccess;
	private int childId;
	private String query;
	private ArrayList<MedicinePlanModel> plans;
	public MedicationPlanResult()
	{
		// TODO Auto-generated constructor stub
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
	public String getQuery()
	{
		return query;
	}
	public void setQuery(String query)
	{
		this.query = query;
	}
	public ArrayList<MedicinePlanModel> getPlans()
	{
		return plans;
	}
	public void setPlans(ArrayList<MedicinePlanModel> plans)
	{
		this.plans = plans;
	}

}
