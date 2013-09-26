package no.blopp.app.med.models;

import java.util.concurrent.ExecutionException;

import android.util.Log;

import no.blopp.app.med.jsonparsers.ChildModelParser;

public class ChildRewards {
	private int credits;
	protected ChildModelParser childModelParser;
	
	public ChildRewards(int childId)
	{
		childModelParser = new ChildModelParser(childId);
	}
	
	public void initChildModelParser()
	{
		childModelParser.execute();
		try {
			childModelParser.get();
			this.credits = childModelParser.getLogResult().getCredits();
		} catch (InterruptedException e) {
			Log.e("ChildRewards", e.getMessage());
		} catch (ExecutionException e) {
			Log.e("ChildRewards", e.getMessage());
		}
	}
	
	public int getCredits()
	{
		return credits;
	}
}
