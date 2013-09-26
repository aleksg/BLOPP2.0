package no.blopp.app.activities;

import no.blopp.app.conf.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailedInstructionsActivity extends Activity
{

	private static final String TAG = MedicationInformationActivity.class
			.getSimpleName();
	private TextView details, header;
	private ImageView imageView;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailedinstructions);
		details = (TextView) findViewById(R.id.detailed_info_view);
		details.setPadding(10, 10, 10, 0);
		header = (TextView)findViewById(R.id.header_textview);
		header.setPadding(10,10,0,0);
		imageView = (ImageView) findViewById(R.id.detailed_medicine_imageView);
		imageView.setPadding(5, 5, 0, 0);
		Bundle bundle = getIntent().getExtras();
		initializeTextViews(bundle);
		header.setTextSize(22);
		details.setTextSize(17);
	}

	public void initializeTextViews(Bundle bundle)
	{
		String name = bundle.getString("Name");
		String value = bundle.getString("Option");
		if(name.equalsIgnoreCase("Seretide"))
		{
			imageView.setImageResource(R.drawable.seretide_small);
			initSeretide(value);
		}else if(name.equalsIgnoreCase("Flutide"))
		{
			imageView.setImageResource(R.drawable.flutide_small);
			initFlutide(value);
		}
		else if(name.equalsIgnoreCase("Ventoline"))
		{
			imageView.setImageResource(R.drawable.ventolide_small);
			initVentoline(value);
		}
		header.setText(name);
		
	}
	
	private void initSeretide(String val)
	{
		
		if(val.equalsIgnoreCase("WHAT"))
		{
			details.setText(R.string.SeretideWhat);
		}
		
		else if(val.equalsIgnoreCase("INFO"))
		{
			details.setText(R.string.SeretideInfo);
		}
	}
	private void initVentoline(String val)
	{
		if(val.equalsIgnoreCase("WHAT"))
		{
			details.setText(R.string.VentolineWhat);
		}
		
		else if(val.equalsIgnoreCase("INFO"))
		{
			details.setText(R.string.VentolineInfo);
		}	
	}
	private void initFlutide(String val)
	{
		if(val.equalsIgnoreCase("WHAT"))
		{
			details.setText(R.string.FlutideWhat);
		}
		
		else if(val.equalsIgnoreCase("INFO"))
		{
			details.setText(R.string.FlutideInfo);
		}
		
	}
	

}
