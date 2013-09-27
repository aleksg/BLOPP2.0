package no.blopp.app.adapters;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MedicineSpinnerAdapter extends ArrayAdapter
{

	public MedicineSpinnerAdapter(Context context, int textViewResourceId)
	{
		super(context, textViewResourceId);
		
	}

	public MedicineSpinnerAdapter(Context context, int resource,
			int textViewResourceId)
	{
		super(context, resource, textViewResourceId);
		
	}

	public MedicineSpinnerAdapter(Context context, int textViewResourceId,
			Object[] objects)
	{
		super(context, textViewResourceId, objects);
		
	}

	public MedicineSpinnerAdapter(Context context, int textViewResourceId,
			List objects)
	{
		super(context, textViewResourceId, objects);
		
	}

	public MedicineSpinnerAdapter(Context context, int resource,
			int textViewResourceId, Object[] objects)
	{
		super(context, resource, textViewResourceId, objects);
		
	}

	public MedicineSpinnerAdapter(Context context, int resource,
			int textViewResourceId, List objects)
	{
		super(context, resource, textViewResourceId, objects);
		
	}

}
