package no.blopp.app.activities;

import no.blopp.app.adapters.InstructionOptionsAdapter.InstructionsOptionsModel;
import no.blopp.app.adapters.*;
import no.blopp.app.conf.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class InstructionOptionsActivity extends Activity implements OnItemClickListener
{

	private static final String TAG = InstructionOptionsActivity.class.getSimpleName();
	private ListView listView;
	private MedicineListModel model;
	private String medicineName, desc;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.medicine_instructions);
		
		listView = (ListView) findViewById(R.id.instruction_options_listView);
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		
		this.medicineName = bundle.getString("name");
		this.desc = bundle.getString("desc");
		listView.setAdapter(new InstructionOptionsAdapter(medicineName, desc, getApplicationContext()));
		listView.setOnItemClickListener(this);
	}
	/**
	 * Redirects to next activity
	 */
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3)
	{
		if (position == 0) 
		{
			return;
		}
		
		InstructionsOptionsModel model = (InstructionsOptionsModel) adapterView.getItemAtPosition(position);
		Intent intent = new Intent(InstructionOptionsActivity.this, DetailedInstructionsActivity.class);
		switch (model.getOptionsId())
		{
		
		case USAGE: 
			intent = new Intent(InstructionOptionsActivity.this, InstructionSlideShowActivity.class);
			startActivity(intent);
			return;
		case INFO:
			intent.putExtra("Name", this.medicineName);
			intent.putExtra("Option", "INFO");
			break;
		case WHAT:
			intent.putExtra("Name", this.medicineName);
			intent.putExtra("Option", "WHAT");
			break;
		default: 
			Log.d(TAG, "Noe gikk galt");
		}
		startActivity(intent);	
	}
}
