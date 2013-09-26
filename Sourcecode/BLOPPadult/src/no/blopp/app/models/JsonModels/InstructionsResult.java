package no.blopp.app.models.JsonModels;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class InstructionsResult
{
	private String instructions, effect, imageUrl;
	private int id;
	private ArrayList<Bitmap> instructionImages;
	

	public InstructionsResult()
	{
		instructionImages = new ArrayList<Bitmap>();
	}
	
	public ArrayList<Bitmap> getInstructionImages()
	{
		if(instructionImages == null)
		{
			instructionImages = new ArrayList<Bitmap>();
		}
		return instructionImages;
	}

	public void setInstructionImages(ArrayList<Bitmap> instructionImages)
	{
		this.instructionImages = instructionImages;
	}
	public String getInstructions()
	{
		return instructions;
	}

	public void setInstructions(String instructions)
	{
		this.instructions = instructions;
	}

	public String getEffect()
	{
		return effect;
	}

	public void setEffect(String effect)
	{
		this.effect = effect;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
