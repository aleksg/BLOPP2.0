package no.blopp.app.xmlfeed;

import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public abstract class XMLFeed extends AsyncTask<Void, Void, Void> implements IInitializeFromXML
{
	//TODO: Replace URL with URL given from pollenvarslingen.no
	public String URL = "http://folk.ntnu.no/yngvesva/blopp/dummy/PollenForecast.xml";
	public String TAG;
	private HttpGet httpGet;
	private InputStream inputStream;
	/**
	 * MethodUrl must be a string on form "op=(Methodname)", for instance "op=GetPollenType".
	 * @param methodUrl
	 */
	public XMLFeed()
	{
		TAG = this.getClass().getSimpleName();
	}
	
	@Override
	protected Void doInBackground(Void... iamneverusedsodontputanythinghere)
	{
		String result = "";
		System.out.println("Test");
		result = "";
		try
		{
			HttpClient httpclient = new DefaultHttpClient();
			httpGet = new HttpGet(URL);
		
			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			inputStream = entity.getContent();
			Log.e(TAG, "connection success ");
		} catch (Exception e)
		{
			Log.e(TAG, "Error in http connection " + e.toString());

		}
		// convert response to string
		try
		{
			initializeDataFromXML(inputStream);
			
			//Close inputstream when completed. 
			inputStream.close();

		} catch (Exception e)
		{
			//TODO: Maybe replace with some sort of real error handling
			Log.e(TAG, "Error converting result " + e.toString());
		}
		
		return null;
	}


	
	
}
