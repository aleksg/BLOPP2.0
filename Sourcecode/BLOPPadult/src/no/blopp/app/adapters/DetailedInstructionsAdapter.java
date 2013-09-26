package no.blopp.app.adapters;

import no.blopp.app.conf.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DetailedInstructionsAdapter extends BaseAdapter
{
	private String header;
	private String information;
	private Context context;
	private DetailedInfoModel detailedInfoModel;
	private DetailedInfoModel[] detailedInstructions;
	
	public DetailedInstructionsAdapter(String head, String info, Context context)
	{
		detailedInstructions = DetailedInfoModel.getModel(header);
		this.header = header;
		this.context = context;
	}
	
	public int getCount()
	{
		return detailedInstructions.length + 1;
	}
	
	public Object getItem(int position)
	{
		return detailedInstructions[position];
	}
	
	public long getItemId(int position)
	{
		return 0;
	}
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View detailedInstructionsView;
		
		if (convertView == null)
		{
			detailedInstructionsView = new View(context);
			
			if(position == 0)
			{
				detailedInstructionsView = inflater.inflate(R.layout.detailedinstructions, parent, false);
				
				TextView nameView = (TextView) detailedInstructionsView.findViewById(R.id.header_textview);
				nameView.setText(detailedInfoModel.getHeader());
				nameView.setTextColor(Color.BLACK);
				
				TextView detailedInfoView = (TextView) detailedInstructionsView.findViewById(R.id.detailed_info_view);
				detailedInfoView.setText(detailedInfoModel.getInfo());
				detailedInfoView.setTextColor(Color.BLACK);
			}
		} else 
		{
			detailedInstructionsView = convertView;
		}
		return detailedInstructionsView;
	}
	
}


