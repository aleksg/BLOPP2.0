package no.blopp.app.models;

public class HealthState
{

	
	public static HealthZone getHealthZoneById(int id)
	{
		switch(id)
		{
		case 1:
			return HealthZone.GREEN_ZONE;
		case 2:
			return HealthZone.YELLOW_ZONE;
		case 3:
			return HealthZone.RED_ZONE;
		default:
			return HealthZone.GREEN_ZONE;
		}
	}
	public static int getIdByHealthZone(HealthZone hz)
	{
		if(hz.equals(HealthZone.GREEN_ZONE))
		{return 1;}
		else if(hz.equals(HealthZone.YELLOW_ZONE))
		{return 2;}
		else if(hz.equals(HealthZone.RED_ZONE))
		{return 3;}
		return 1;
	}
	
}
