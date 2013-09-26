package no.blopp.app.adapters;

import android.graphics.Bitmap;
public class MainMenuItem
{
	public enum MenuOptions{LOG, TREATMENT,  MANUAL, INSTRUCTIONS, PLAN}
	private String options;
	private Bitmap icon;
	private MenuOptions shortDesc;
	public MainMenuItem(String option, Bitmap bitmap, MenuOptions o)
	{
		this.options = option;
		this.icon = bitmap;
		this.shortDesc = o;
	}
	public String getOptions()
	{
		return options;
	}
	public Bitmap getIcon()
	{
		return icon;
	}
	public MenuOptions getShortDesc()
	{
		return this.shortDesc;
	}

}
