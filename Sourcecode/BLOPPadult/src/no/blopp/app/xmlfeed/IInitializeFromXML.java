package no.blopp.app.xmlfeed;

import java.io.InputStream;

public interface IInitializeFromXML
{
	/**
	 * Uses the result from HTTP GET to extract necessary information. 
	 * @param result
	 */
	public void initializeDataFromXML(InputStream is);
	
}
