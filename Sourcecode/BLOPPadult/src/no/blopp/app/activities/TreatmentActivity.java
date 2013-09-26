package no.blopp.app.activities;

import java.util.concurrent.ExecutionException;

import org.joda.time.DateTime;

import no.blopp.app.adapters.MedicineListModel;
import no.blopp.app.adapters.MedicineRadioAdapter;
import no.blopp.app.conf.R;
import no.blopp.app.jsonparsers.HealthStateParser;
import no.blopp.app.jsonposters.PostRegisterTreatment;
import no.blopp.app.models.JsonModels.RegisterMedicinePostModel;
import no.blopp.app.repositories.AvailableMedicines;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TreatmentActivity extends Activity implements OnClickListener
{
	private static final String TAG = TreatmentActivity.class.getSimpleName();
	private static final int CHILD_ID = 6;
	private EditText dateEditText;
	
	private AvailableMedicines availableMedicines;
	private Button submitButton;
	private String dateNow;
	private ListView medicineListView; //Listview containing a checkbox, the medicinename and the icon of the medicine. 
	private TextView dateTextField, chooseMedicineTextField;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.treatment);

		submitButton = (Button) findViewById(R.id.register_treatment_button);
		dateEditText = (EditText) findViewById(R.id.treatment_date_editText);
		availableMedicines = new AvailableMedicines();
		medicineListView = (ListView) findViewById(R.id.treatment_medication_listView);
		medicineListView.setDivider(null);
		
		medicineListView.setAdapter(new MedicineRadioAdapter(
				getApplicationContext()));
		dateTextField = (TextView)findViewById(R.id.treatment_date_textView);
		dateTextField.setPadding(10, 0, 0, 0);
		chooseMedicineTextField = (TextView) findViewById(R.id.treatment_choose_medicine);
		chooseMedicineTextField.setPadding(10, 0, 0, 0);
//		medicineListView.setOnItemClickListener(this);
		initDateEditText();
		submitButton.setOnClickListener(this);
	}
	
	/**
	 * Initializes the field dateEditText with the current date. 
	 */
	private void initDateEditText()
	{
		DateTime dateTime = new DateTime();
		int day = dateTime.getDayOfMonth();
		int month = dateTime.getMonthOfYear();
		int year = dateTime.getYear();
		dateNow = day + "-" + month + "-" + year + "";
		dateEditText.setText(dateNow);
	}

	public void onClick(View v)
	{
		if (v.getId() == submitButton.getId())
		{
			submitForm();
		}
	}

	/**
	 * When user presses "Register", validate the form and submit it. 
	 */
	private void submitForm()
	{
 
		String date = formatDate(dateEditText.getText().toString());
		MedicineRadioAdapter adapter = (MedicineRadioAdapter)medicineListView.getAdapter();
		MedicineListModel medicineChosen = adapter.getCheckedItem();
		if(medicineChosen==null)
		{
			Toast.makeText(getApplicationContext(), "Velg en medisin", Toast.LENGTH_SHORT).show();
			return;
		}
		int medicineId = availableMedicines
				.getMedicineByName(medicineChosen.getName());
		
		if (!validateDate(date))
		{
			Toast.makeText(this, R.string.date_error,
					Toast.LENGTH_SHORT).show();
			return; //Return if date is invalid
		}
		//Should not occur unless there is some sort of injection in the view. 
		if (medicineId == -1)
		{
			Toast.makeText(getApplicationContext(),
					"Vennligst velg en medisin", Toast.LENGTH_SHORT).show();
			return; //Return if medicineId is -1. This happens when the medicine with given name is not in stored in the database. 
		}
		
		post(repairDateFormat(date), medicineId);
	}
	/**
	 * Posts to the database, the date and medicineid. The table used for this is
	 * day_medicine_doses.
	 * @param date
	 * @param medicineId
	 */
	private void post(String date, int medicineId)
	{
		// TODO: Replace with actual childId
		int healthStateId = getCurrentHealthState();
		if(healthStateId==-1)
		{
			return; //Return if we could not retrieve healthstate 
		}
		RegisterMedicinePostModel model = new RegisterMedicinePostModel(date,
				medicineId, CHILD_ID, healthStateId);
		
		PostRegisterTreatment poster = new PostRegisterTreatment(
				model.toString());
		//Execute the post
		poster.execute();
		try
		{
			poster.get();
		} catch (InterruptedException e)
		{
			showPostError();
			e.printStackTrace();
			return; //Return if the post was unsuccessful
		} catch (ExecutionException e)
		{
			showPostError();
			e.printStackTrace();
			return; //Return if the post was unsuccessful
		}
		int reward = poster.getTreatmentResult().getReward();
		Toast t2 = Toast.makeText(this, "Barnet ditt fikk " + reward
				+ " stjerner", Toast.LENGTH_LONG); //Notify the parent that the child got some stars
		t2.show();
		returnToMainMenu(); //Return when the post is completed
	}
	/**
	 * Replaces . and / with -, to cope with mysql-standards. 
	 * Also adds a year to the returned string if this is not manually written in. 
	 * @param date
	 * @return
	 */
	private String formatDate(String date)
	{
		String tmp;
		tmp = date.replace(".", "-");
		tmp = tmp.replace("/", "-");
		if (tmp.split("-").length == 2)
		{
			DateTime dt = new DateTime();

			tmp = tmp + "-" + dt.getYear();
		}
		return tmp;
	}
	/**
	 * Contains one bug. That is, if the user inputs day = 31 for a month containing 30 or less days. Needs a fix
	 * @param date, the input from the user
	 * @return returns true if 1<=day<=31,1<=month<=12, year>=2011. 
	 */
	private boolean validateDate(String date)
	{
		int dateLength = date.split("-").length;
		String[] dateElements = new String[dateLength];
		int[] dates = new int[3];

		for (int i = 0; i < dateLength; i++)
		{
				dateElements[i] = date.split("-")[i];
				dates[i] = Integer.parseInt(dateElements[i]);
		}
		dates[2] = expandYear(dates[2]);
		boolean a = (dates[0] <= 31 && dates[0] >= 1);
		boolean b = (dates[1] >= 1 && dates[1] <= 12);
		boolean c = (dates[2] >= 2011);
		return a && b && c;
	}

	/**
	 * Split input on "-".
	 * 
	 * @param inputDate
	 * @return
	 */
	private String repairDateFormat(String inputDate)
	{
		
		String[] splittedInput = inputDate.split("-");
		String[] tmp = new String[3];
		//If input only contains day and month, add 2012
		if (splittedInput.length == 2)
		{
			tmp[0] = splittedInput[0];
			tmp[1] = splittedInput[1];
			tmp[2] = "2012";
			splittedInput = tmp;
		}
		String newDateElement = splittedInput[2] + "-" + splittedInput[1] + "-"
				+ splittedInput[0];
		return newDateElement;
	}

	/**
	 * If the user has only put in two digits, expand it with 2000. Assumes we
	 * never go out of this thousandyear
	 * 
	 * @param year
	 * @return
	 */
	private int expandYear(int year)
	{
		return (year <= 99 && year >= 1) ? (year + 2000) : year;
	}
	/**
	 * Called after the user has successfully submitted the form.
	 */
	private void returnToMainMenu()
	{
		Intent intent = new Intent(TreatmentActivity.this, MainMenu.class);
		startActivity(intent);
	}


	private void showPostError()
	{
		Toast.makeText(getApplicationContext(), R.string.post_error, Toast.LENGTH_SHORT).show();
	}
	private int getCurrentHealthState()
	{
		HealthStateParser hParser = new HealthStateParser(CHILD_ID);
		int healthStateId = -1;
		hParser.execute();
		try
		{
			hParser.get();
		} catch (InterruptedException e)
		{
			Toast.makeText(getApplicationContext(), R.string.download_error, Toast.LENGTH_SHORT).show();
			e.printStackTrace();
			return -1;
		} catch (ExecutionException e)
		{
			Toast.makeText(getApplicationContext(), R.string.download_error, Toast.LENGTH_SHORT).show();
			e.printStackTrace();
			return -1;
		}
		healthStateId = hParser.getHealthStateId();
		return healthStateId;
		
	}

}
