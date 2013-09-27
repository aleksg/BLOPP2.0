package no.blopp.app.med.jsonparsers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public abstract class GenericParser extends AsyncTask<Void, Void, Void> implements IInitializeFromJSON
{
	protected String TAG;
	//Warning: URL
	public static final String URLbody = "http://folk.ntnu.no/yngvesva/blopp/";
	protected InputStream is;
	protected HttpGet httpGet;
	protected String result;
	protected JSONObject json_data;
	protected String URL;
	
	public GenericParser(String urltail)
	{
		URL = URLbody+urltail;
	}
	
	
	@Override
	protected Void doInBackground(Void... arg0)
	{
		
		System.out.println("Test");
		result = "";
		try
		{
			HttpClient httpclient = new DefaultHttpClient();
			httpGet = new HttpGet(URL);
			

			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e(TAG, "connection success ");
		} catch (Exception e)
		{
			Log.e(TAG, "Error in http connection " + e.toString());

		}
		// convert response to string
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while ((line = reader.readLine()) != null)
			{
				sb.append(line + "\n");
			}
			is.close();

			result = sb.toString();

		} catch (Exception e)
		{
			Log.e(TAG, "Error converting result " + e.toString());
		}
		initializeDataFromJSON(result);
		return null;
	}
	
	
	

}
