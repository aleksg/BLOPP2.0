package no.blopp.app.jsonposters;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import no.blopp.app.jsonparsers.IInitializeFromJSON;

import org.apache.http.client.methods.HttpPost;

import android.os.AsyncTask;
import android.util.Log;

public abstract class DatabasePoster extends AsyncTask<Void, Void, Void> implements IInitializeFromJSON 
{
//	register_medicine_taken.php
	private HttpPost httpPost;
	private String urlBody = "http://folk.ntnu.no/yngvesva/blopp/";
	private String params;
	/**
	 * 
	 * @param params data sent in the post message. NB! Must be "UTF-8"-encoded!
	 * @param phpPage phpfile hosted at /yngvesva/blopp. Must end with /
	 */
	public DatabasePoster(String params, String phpPage)
	{
		urlBody+=phpPage;
		this.params = params;
	}
	
	

	@Override
	protected Void doInBackground(Void... iamNotUsedForAnythingSoDontPutAnythingHere)
	{
		URL url;
		String reply = "";
		try
		{
			url = new URL(urlBody);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();           
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false); 
			connection.setRequestMethod("POST"); 
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(params.getBytes().length));
			connection.setUseCaches (false);
			
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
			wr.writeBytes(params);
			wr.flush();
			wr.close();
			
			Log.d("DatabasePoster", connection.getResponseMessage());
			InputStream in = connection.getInputStream();
			StringBuffer sb = new StringBuffer();
			
			reply = buildString(in, sb);
			
			initializeDataFromJSON(reply);
			connection.disconnect();
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} 
		return null;
	}
	private String buildString(InputStream in, StringBuffer sb) throws IOException
	{
		String reply;
		try {
			int chr;
			while ((chr = in.read()) != -1) {
				sb.append((char) chr);
			}
			reply = sb.toString();
		} finally {
			in.close();
		}
		return reply;
	}
}
