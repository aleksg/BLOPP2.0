package no.blopp.app.med.jsonparsers;

import java.util.ArrayList;

import no.blopp.app.med.JsonModels.InstructionsResult;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.util.Log;

public class InstructionsParser extends GenericParser 
{
	//Warning: PHP-reference
	//TODO: Cache images. Update every XX days or something
	private static final String PHPPage = "get_instructions.php?";
	private String url;
	private ArrayList<Bitmap> bitmap;
	private DownloadImageTask imageTask;
	private InstructionsResult instructionResult;
	
	public InstructionsParser(int medicineId)
	{
		super(PHPPage + "medicine_id=" + medicineId);
		Log.d(this.getClass().getSimpleName(), "URL = " + url);
	}
	
	public void initializeDataFromJSON(String result)
	{
		JSONObject object;
		try
		{
			object = new JSONObject(result);
			JSONObject ir = object.getJSONObject("instructions");
			instructionResult = new InstructionsResult();
			instructionResult.setEffect(ir.getString("effect"));
			instructionResult.setId(ir.getInt("id"));
			instructionResult.setImageUrl(ir.getString("url"));
			instructionResult.setInstructions(ir.getString("information"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		imageTask = new DownloadImageTask(instructionResult.getImageUrl());
	}
	
	public InstructionsResult getResult() {
		return this.instructionResult;
	}
	
	public DownloadImageTask getImageTask()
	{
		return this.imageTask;
	}
	
}
