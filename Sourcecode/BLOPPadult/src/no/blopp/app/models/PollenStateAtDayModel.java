package no.blopp.app.models;

import java.util.ArrayList;

public class PollenStateAtDayModel
{
	private ArrayList<PollenState> pollenStatesAtDay;

	public PollenStateAtDayModel()
	{
		this.pollenStatesAtDay = new ArrayList<PollenState>();
	}

	public ArrayList<PollenState> getPollenStatesAtDay()
	{
		return pollenStatesAtDay;
	}

	public void setPollenStatesAtDay(ArrayList<PollenState> pollenStatesAtDay)
	{
		this.pollenStatesAtDay = pollenStatesAtDay;
	}

}
