package no.blopp.app.models.JsonModels;

public class LogDosesModel
{
	private int id, childId, medicineId, healthStateId, planId, pollenStateId;
	/**
	 * The reward of a given doses. 
	 */
	private int reward;
	private String date, time;
	

	public LogDosesModel()
	{
		
	}


	/**
	 * 
	 * @return the id of a pollenstate. The id should be in the range 0-4, as given by 
	 * "pollenvarslingen.no"
	 */
	public int getPollenStateId()
	{
		return pollenStateId;
	}


	public void setPollenStateId(int pollen_state_id)
	{
		this.pollenStateId = pollen_state_id;
	}


	public int getId()
	{
		return id;
	}


	public void setId(int id)
	{
		this.id = id;
	}

	
	public int getReward()
	{
		return reward;
	}


	public void setReward(int reward)
	{
		this.reward = reward;
	}


	public int getChildId()
	{
		return childId;
	}


	public void setChildId(int childId)
	{
		this.childId = childId;
	}


	public int getMedicineId()
	{
		return medicineId;
	}


	public void setMedicineId(int medicineId)
	{
		this.medicineId = medicineId;
	}


	public int getHealthStateId()
	{
		return healthStateId;
	}


	public void setHealthStateId(int healthStateId)
	{
		this.healthStateId = healthStateId;
	}


	public int getPlanId()
	{
		return planId;
	}


	public void setPlanId(int planId)
	{
		this.planId = planId;
	}


	public String getDate()
	{
		return date;
	}


	public void setDate(String date)
	{
		this.date = date;
	}


	public String getTime()
	{
		return time;
	}


	public void setTime(String time)
	{
		this.time = time;
	}

}
