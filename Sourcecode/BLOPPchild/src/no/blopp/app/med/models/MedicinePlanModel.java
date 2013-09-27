package no.blopp.app.med.models;

import java.io.Serializable;

public class MedicinePlanModel implements Serializable
{
	
	private static final long serialVersionUID = 1149525340444227051L;
	private int id;
	private String label;
	private int medicalPlanId;
	private int healthStateId;
	private String time;
	private int medicineId;
	private String medicineColor;
	private String medicineName;
	
	public MedicinePlanModel()
	{
		
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getLabel()
	{
		return label;
	}
	public void setLabel(String label)
	{
		this.label = label;
	}
	public int getMedicalPlanId()
	{
		return medicalPlanId;
	}
	public void setMedicalPlanId(int medicalPlanId)
	{
		this.medicalPlanId = medicalPlanId;
	}
	public int getHealthStateId()
	{
		return healthStateId;
	}
	public void setHealthStateId(int healthStateId)
	{
		this.healthStateId = healthStateId;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public int getMedicineId()
	{
		return medicineId;
	}
	public void setMedicineId(int medicineId)
	{
		this.medicineId = medicineId;
	}
	public String getMedicineColor()
	{
		return medicineColor;
	}
	public void setMedicineColor(String medicineColor)
	{
		this.medicineColor = medicineColor;
	}
	public String getMedicineName()
	{
		return medicineName;
	}
	public void setMedicineName(String medicineName)
	{
		this.medicineName = medicineName;
	}
	@Override
	public String toString(){
		return medicineName;
	}
	
}
