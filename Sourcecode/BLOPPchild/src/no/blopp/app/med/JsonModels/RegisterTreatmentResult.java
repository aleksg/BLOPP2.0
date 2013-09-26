package no.blopp.app.med.JsonModels;

public class RegisterTreatmentResult
{
	private int reward;
	private boolean sqlSuccess;
	
	public int getReward()
	{
		return reward;
	}
	public void setReward(int reward)
	{
		this.reward = reward;
	}
	public boolean isSqlSuccess()
	{
		return sqlSuccess;
	}
	public void setSqlSuccess(boolean sqlSuccess)
	{
		this.sqlSuccess = sqlSuccess;
	}
	
}
