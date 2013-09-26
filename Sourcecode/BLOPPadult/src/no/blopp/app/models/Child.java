package no.blopp.app.models;




/**
 * @deprecated
 * This model needs some extensions before deployment. We had no time to implement functionality for several children. These models
 * can be used as a start.
 */
@Deprecated
public class Child extends Person
{
	private MedicinePlanModel medicinePlanModel;
	
	

	public Child(String name, String SSN)
	{
		super(name, SSN);
	}
	
	public MedicinePlanModel getMedicinePlanModel()
	{
		return this.medicinePlanModel;
	}
	public void setMedicinePlan(MedicinePlanModel model)
	{
		//Should validate the model somehow before this statement.
		if(model==null)
		{
			throw new IllegalArgumentException("model is null");
		}
		this.medicinePlanModel = model;
	}
	public void updateHealthZone(HealthZone hz)
	{
		
	}
	

	

}
