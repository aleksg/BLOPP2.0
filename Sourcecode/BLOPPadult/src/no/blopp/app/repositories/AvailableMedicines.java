package no.blopp.app.repositories;

import java.util.HashMap;

/**
 * This is a helper class to get the available medicines in the database.
 * Thus, medcinesMap must always reflect the exact fields in the database. If not,
 * medicines will not appear in any lists.
 */
public class AvailableMedicines
{
	public HashMap<String, Integer> medicinesMap;
	
	public AvailableMedicines()
	{
		medicinesMap = new HashMap<String, Integer>();
		medicinesMap.put("Flutide", 1);
		medicinesMap.put("Ventoline", 2);
		medicinesMap.put("Seretide", 3);
	}
	
	public int getMedicineByName(String name)
	{
		for (String s : medicinesMap.keySet())
		{
			if(s.equalsIgnoreCase(name))
			{
				return medicinesMap.get(s);
			}
		}
		throw new IllegalArgumentException("The name of this medicine does not exist");
	}
	public String getMedicineById(int id)
	{
		for (String s : medicinesMap.keySet())
		{
			if(id==medicinesMap.get(s))
			{
				return s;
			}
		}
		throw new IllegalArgumentException("The id does not exist");
	}
}
