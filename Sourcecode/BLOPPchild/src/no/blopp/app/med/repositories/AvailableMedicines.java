package no.blopp.app.med.repositories;

import java.util.HashMap;

public class AvailableMedicines 
{

	public HashMap<String, Integer> medicinesMap;
	/**
	 * MUST REFLECT DATABASE-IDS!
	 */
	public AvailableMedicines()
	{
		medicinesMap = new HashMap<String, Integer>();
		medicinesMap.put("Flutide", 1);
		medicinesMap.put("Ventoline", 2);
		medicinesMap.put("Seretide", 3);
	}
	
	public int getMedicineByName(String name)
	{
		for(String s : medicinesMap.keySet())
		{
			if(s==name)
			{
				return medicinesMap.get(s);
			}
		}
		return -1;
	}
	
	public String getMedicineById(int id)
	{
		for(String s : medicinesMap.keySet())
		{
			if(id==medicinesMap.get(s))
			{
				return s;
			}
		}
		return "Dette ble feil. Slutt med det";
	}
	
}
