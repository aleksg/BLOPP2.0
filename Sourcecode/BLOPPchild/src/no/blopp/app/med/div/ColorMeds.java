package no.blopp.app.med.div;

import no.blopp.app.med.R;

public enum ColorMeds
{
	blue(
		"BLUE", 
		"bl\u00E5", 
		R.drawable.karotz_blue, 
		R.drawable.shake_medication_animation_blue, 
		R.drawable.karotz_blue_breath1, 
		R.drawable.karotz_breathing_animation_blue,
		R.drawable.medicine_ventoline_100microg
	),
	orange(
		"ORANGE", 
		"oransje", 
		R.drawable.karotz_orange, 
		R.drawable.shake_medication_animation_orange, 
		R.drawable.karotz_orange_breath1, 
		R.drawable.karotz_breathing_animation_orange,
		R.drawable.medicine_flutide_125microg
	),
	purple(
		"PURPLE", 
		"lilla", 
		R.drawable.karotz_purple, 
		R.drawable.shake_medication_animation_purple, 
		R.drawable.karotz_purple_breath1, 
		R.drawable.karotz_breathing_animation_purple,
		R.drawable.medicine_seretide_250microg
	);
	
	public final String db;
	public final String no;
	public final int notificationImage;
	public final int shakeAnimation;
	public final int maskInstructionImage;
	public final int breatheAnimation;
	public final int medicineImage;
	
	public static int getNotificationImage(String db)
	{
		if (db.equalsIgnoreCase(blue.db))
		{
			return blue.notificationImage;
		} 
		else if (db.equalsIgnoreCase(orange.db))
		{
			return orange.notificationImage;
		}
		else// if (db == purple.db)
		{
			return purple.notificationImage;
		}
	}
	public static int shakeAnimation(String db)
	{
		if (db.equalsIgnoreCase(blue.db))
		{
			return blue.shakeAnimation;
		} 
		else if (db.equalsIgnoreCase(orange.db))
		{
			return orange.shakeAnimation;
		}
		else// if (db == purple.db)
		{
			return purple.shakeAnimation;
		}
	}
	public static int maskInstructionImage(String db)
	{
		if (db.equalsIgnoreCase(blue.db))
		{
			return blue.maskInstructionImage;
		} 
		else if (db.equalsIgnoreCase(orange.db))
		{
			return orange.maskInstructionImage;
		}
		else// if (db == purple.db)
		{
			return purple.maskInstructionImage;
		}
	}	
	public static int breatheAnimation(String db)
	{
		if (db.equalsIgnoreCase(blue.db))
		{
			return blue.breatheAnimation;
		} 
		else if (db.equalsIgnoreCase(orange.db))
		{
			return orange.breatheAnimation;
		}
		else// if (db == purple.db)
		{
			return purple.breatheAnimation;
		}
	}
	public static int medicineImage(String db)
	{
		if (db.equalsIgnoreCase(blue.db))
		{
			return blue.medicineImage;
		} 
		else if (db.equalsIgnoreCase(orange.db))
		{
			return orange.medicineImage;
		}
		else// if (db == purple.db)
		{
			return purple.medicineImage;
		}
	}

	private ColorMeds(String db, String no, int notificationImage, int shakeAnimation,
			int maskInstructionImage, int breatheAnimation, int medicineImage)
	{
		this.db = db;
		this.no = no;
		this.notificationImage = notificationImage;
		this.shakeAnimation = shakeAnimation;
		this.maskInstructionImage = maskInstructionImage;
		this.breatheAnimation = breatheAnimation;
		this.medicineImage = medicineImage;
	}
	
	public static String getNorwegianWord(String db)
	{
		if (db.equalsIgnoreCase(blue.db))
		{
			return blue.no;
		} 
		else if (db.equalsIgnoreCase(orange.db))
		{
			return orange.no;
		}
		else// if (db == purple.db)
		{
			return purple.no;
		}
	}
}
