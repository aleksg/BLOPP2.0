package no.blopp.app.adapters;

import android.graphics.Bitmap;
import android.widget.RadioButton;


//TODO: Why is this class in adapters?
public class MedicineListModel
{
	private Bitmap bitmap;
	private String name;
	private String description;
	private RadioButton radioButton;
	public MedicineListModel(Bitmap bitmap, String name, String description)
	{
		super();
		this.bitmap = bitmap;
		this.name = name;
		this.description = description;
	}

	public Bitmap getBitmap()
	{
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap)
	{
		this.bitmap = bitmap;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public RadioButton getRadioButton() {
		return radioButton;
	}

	public void setRadioButton(RadioButton radioButton) {
		this.radioButton = radioButton;
	}

}
