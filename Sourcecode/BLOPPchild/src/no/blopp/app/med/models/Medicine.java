package no.blopp.app.med.models;

import android.graphics.Bitmap;

//Initial testing purposes for the log.
public class Medicine implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3468898819365808929L;
	private int id;
	private String name;
	private String color;
	private int instructionsId;
	private int imgId;
	private Bitmap img;
	
	public Medicine(int id, String name, String color, Bitmap img)
	{
		this.id = id;
		this.name = name;
		this.color = color;
		this.img = img;
	}
	
	public Bitmap getBitmap()
	{
		return img;
	}
	
	public Medicine(int id, String name, int instructionsId, int imgId, String color)
	{
		this.id = id;
		this.name = name;
		this.instructionsId = instructionsId;
		this.imgId = imgId;
		this.color = color;
	}
	
	public Medicine(String name, int instructionsId, int imgId)
	{
		this.instructionsId = instructionsId;
		this.name = name;
		this.setImgId(imgId);
	}
	public Medicine(String name, int instructionsId)
	{
		this.instructionsId = instructionsId;
		this.name = name;
	}
	public Medicine(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		if(name.isEmpty())
		{
			throw new IllegalArgumentException("The name cannot be null");
			
		}
		this.name = name;
	}
	public int getId()
	{
		return this.id;
	}
	public int getInstructionsId()
	{
		return instructionsId;
	}
	public void setInstructionsId(int instructionsId)
	{
		if(instructionsId<0) 
			throw new IllegalArgumentException("instructionsId must be bigger than null");
	
		this.instructionsId = instructionsId;
	}
	public int getImgId() {
		return imgId;
	}
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}
	
	
}
