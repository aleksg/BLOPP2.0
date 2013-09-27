package no.blopp.app.jsonparsers;

import java.io.InputStream;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadImageTask extends AsyncTask<Void, Void, ArrayList<Bitmap>>
{

	private static final String urlBody = "http://folk.ntnu.no/yngvesva/blopp/img/";
	 
	private String imageUrl;
	
	private ArrayList<String> imageUrls;

	public DownloadImageTask()
	{
		
	}

	/**
	 * Constructor used when only one image-url is available
	 * @param url
	 */
	public DownloadImageTask(String url)
	{
		this.imageUrls = new ArrayList<String>();
		this.imageUrl = url;
	}

	/**
	 * 
	 * @param urls for each picture
	 */
	public DownloadImageTask(ArrayList<String> imageUrls)
	{
		this.imageUrl = ""; 
		this.imageUrls = imageUrls;
	}

	/**
	 * Downloads images and adds them to bitmaps-arraylist. This arraylist is
	 * returned when thread using this method calls DownloadImageTask.get();
	 */
	@Override
	protected ArrayList<Bitmap> doInBackground(Void... urls)
	{
		ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
		//If there are several imageurls, return severalbitmaps, if not, return arraylist containing one bitmap. 
		bitmaps = (!imageUrls.isEmpty()) ? getSeveralBitmaps() : getOneBitmap(); 
		return bitmaps;
	}

	private ArrayList<Bitmap> getOneBitmap()
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
		} catch (Exception e)
		{
			Log.e("Error", e.getMessage());

		}

		return bitmaps;
	}

	private ArrayList<Bitmap> getSeveralBitmaps()
	{
		ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
		for (String url : imageUrls)
		{
			Bitmap image = null;
			InputStream in = null;
			String fullUrl = urlBody + url;
			try
			{
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
