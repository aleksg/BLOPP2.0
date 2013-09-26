package no.blopp.app.models;


public class PollenState
{
	//Autmatically set by the database.
	
	private int distribution;
	private String distributionDescription;
	private String pollenName;
	
	public PollenState(String name, int distribution, String distributionDescription)
	{
		this.pollenName = name;
		this.distribution = distribution;
		this.distributionDescription = distributionDescription;
	}
	
	
	public int getDistribution()
	{
		return distribution;
	}

	public void setDistribution(int distribution)
	{
		this.distribution = distribution;
	}

	public String getDistributionDescription()
	{
		return distributionDescription;
	}

	public void setDistributionDescription(String distributionDescription)
	{
		this.distributionDescription = distributionDescription;
	}

	public String getPollenName()
	{
		return pollenName;
	}

	public void setPollenName(String pollenName)
	{
		this.pollenName = pollenName;
	}
	
	
	
	
}
