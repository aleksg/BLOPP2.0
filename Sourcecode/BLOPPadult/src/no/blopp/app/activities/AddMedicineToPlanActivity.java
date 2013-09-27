package no.blopp.app.activities;

import java.util.Set;
import java.util.concurrent.ExecutionException;

import no.blopp.app.conf.R;
import no.blopp.app.jsonposters.AddMedicineToPlanPoster;
import no.blopp.app.models.JsonModels.AddMedicineToPlanModel;
import no.blopp.app.repositories.AvailableMedicines;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;


public class AddMedicineToPlanActivity extends Activity implements
		OnClickListener
{
	private static final String TAG = AddMedicineToPlanActivity.class
			.getSimpleName();
	private static final int CHILD_ID = 6;
	private Spinner spinnerMedicine;
	private TimePicker timepickerMedicine;
	private LinearLayout mLayout;
	private AvailableMedicines availableMeds;
	private Button btnAddMedicine;
	private int healthStateId;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_medication_to_plan);

		initializeSpinner();
		initializeHealthState();
		setUpListenerForButton(btnAddMedicine, R.id.add_medicine_button);
		
		mLayout = (LinearLayout) findViewById(R.id.add_medicine_linearlayout);
		timepickerMedicine = (TimePicker) findViewById(R.id.timePicker_medicine);
		timepickerMedicine.setIs24HourView(true);
	}
	/**
	 * Get the health state given the input from the last activity
	 */
	private void initializeHealthState()
	{
		Bundle bundle = getIntent().getExtras();
		healthStateId = bundle.getInt("healthState");
	}
	/**
	 * Initialize the medicine spinner to the medicines existing in the database. 
	 */
	public void initializeSpinner()
	{
		this.spinnerMedicine = (Spinner) findViewById(R.id.medicine_spinner);
		availableMeds = new AvailableMedicines();
		Set<String> keys = availableMeds.medicinesMap.keySet();
		String[] medicationNames = new String[keys.size()];
		medicationNames = keys.toArray(medicationNames);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.standard_text_list_item, medicationNames);
		this.spinnerMedicine.setAdapter(adapter);
	}
	
	private void setUpListenerForButton(Button b, int id)
	{
		b = (Button) findViewById(id);
		b.setOnClickListener(this);
	}
	
	public void onClick(View v)
	{
		int vid = v.getId();

		if (vid == R.id.add_medicine_button)
		{

			int medicineId = availableMeds.getMedicineByName(spinnerMedicine
					.getSelectedItem().toString());
			int hour = timepickerMedicine.getCurrentHour();
			int minute = timepickerMedicine.getCurrentMinute();

			String time = hour + ":" + minute + ":" + "00";
			AddMedicineToPlanModel model = new AddMedicineToPlanModel(CHILD_ID,
					healthStateId, time, medicineId);
			boolean wasSuccess = executePost(model);
			
			if (!(wasSuccess))
			{

				Toast t = Toast
						.makeText(
								this,
								R.string.post_error,
								Toast.LENGTH_LONG);
				t.show();
				return;
			}
			Intent resultIntent = new Intent();
			// resultIntent.putExtra("BACKFIRE", aml);
			setResult(Activity.RESULT_OK, resultIntent);
			finish();

		}
	}

	/**
	 * @param model
	 * @return true if the post was succesfull, false otherwise
	 */
	private boolean executePost(AddMedicineToPlanModel model)
	{
		AddMedicineToPlanPoster poster = new AddMedicineToPlanPoster(
				model.toString());
		poster.execute();
		try
		{
			poster.get();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
			return false;
		} catch (ExecutionException e)
		{
			e.printStackTrace();
			return false;
		}

		return poster.getPlanSuccessfullyPosted();
	}

}
