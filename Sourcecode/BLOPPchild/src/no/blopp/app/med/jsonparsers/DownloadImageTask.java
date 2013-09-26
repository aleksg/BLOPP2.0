package no.blopp.app.med.jsonparsers;

import java.io.InputStream;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadImageTask extends AsyncTask<Void, Void, ArrayList<Bitmap>>{

	
	private static final String urlBody = "http://folk.ntnu.no/yngvesva/blopp/img/";
	
	private String imageUrl;
	private ArrayList<String> imageUrls;
	
	public DownloadImageTask()
	{
		
	}
	
	/**
	 * @param url
	 */
	
	public DownloadImageTask(String url)
	{
		this.imageUrls = new ArrayList<String>();
		this.imageUrl = url;
	}
	
	/**
	 * 
	 * @param imageUrls
	 * 					for each picture
	 */
	public DownloadImageTask(ArrayList<String> imageUrls)
	{
		this.imageUrl = "";
		this.imageUrls = imageUrls;
	}
	
	/**
	 * Downloads images and adds them to bitmap-arraylist. This arraylist is
	 * returned when thread using this method calls DownloagImageTask.get();
	 */
	@Override
	protected ArrayList<Bitmap> doInBackground(Void... urls)
	{
		ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
		bitmaps = (!imageUrls.isEmpty()) ? getSeveralBitmaps() : getOneBitMap();
		return bitmaps;
	}
	
	private ArrayList<Bitmap> getOneBitMap()
	{
		ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
		
		Bitmap image = null;
		InputStream in = null;
		String fullUrl = urlBody + imageUrl;
		try
		{
			in = new java.net.URL(fullUrl).openStream();
			image = BitmapFactory.decodeStream(in);
			bitmaps.add(image);
			Log.d(this.getClass().getSimpleName(), "Found image");
		} catch (Exception e) {
			Log.e("Error", e.getMessage());
		}
		
		return bitmaps;
	}
	
	
	private ArrayList<Bitmap> getSeveralBitmaps()
	{
		ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
		for(String url : imageUrls)
		{
			Bitmap image = null;
			InputStream in = null;
			String fullUrl = urlBody + url;
			try{
				in = new java.net.URL(fullUrl).openStream();
				image = BitmapFactory.decodeStream(in);
				Log.d(this.getClass().getSimpleName(), "found image?");
				bitmaps.add(image);
			} catch (Exception e)
			{
				Log.e("Error", e.getMessage());
			}
		}
		return bitmaps; 
		
	}
	
	
	
	
}
