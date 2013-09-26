package no.blopp.app.med.activities;

import java.util.Calendar;

import no.blopp.app.med.R;
import no.blopp.app.med.services.AlarmUpdateReceiver;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends Activity implements
		android.view.View.OnClickListener
{
	private Button DisplayRewardsButton, TreatmentButton, instructionsButton;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		setUpListeners();
		startAlarmUpdater();
	}

	private void startAlarmUpdater() {
		//Set up the Calendar used for the first AlarmUpdateReceiver, 10 seconds from current time.
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND,10);
		Intent i = new Intent(this, AlarmUpdateReceiver.class);
		int updateTime = 30*1000;
		//Set up the pendingIntent, and set the alarm to repeat according to updateTime.
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
		    	0, i, PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), updateTime, pendingIntent);  	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_main_menu, menu);
		return true;
	}

	private void setUpListenerForButton(Button b, int id)
	{
		b = (Button) findViewById(id);
		b.setOnClickListener(this);
		Log.d("MainMenu", "Set up listener for id:" + id);
	}

	private void setUpListeners()
	{
		setUpListenerForButton(DisplayRewardsButton, R.id.rewards_button);
		setUpListenerForButton(TreatmentButton, R.id.treatment_button);
		setUpListenerForButton(instructionsButton, R.id.instructions_button);
	}

	public void onClick(View v)
	{
		int vid = v.getId();
		Log.d("MainMenu", "got input on view: " + v.getId());
		if (vid == R.id.treatment_button)
		{
			if (isConnectedToInternet())
			{
				activityStarter(DistractionActivity.class);

			} else
			{
				makeConnectionToast();
				return;
			}
		} else if (vid == R.id.rewards_button)
		{
			if (isConnectedToInternet())
			{
				activityStarter(DisplayRewardsActivity.class);
			}
			else
			{
				makeConnectionToast();
				return;
			}
		} else if (vid == R.id.instructions_button)
		{
			activityStarter(InstructionsActivity.class);
		}
		Log.d("MainMenu", "v.id()=" + v.getId());
	}

	/**
	 * Starts the specified activity
	 * 
	 * @param c
	 *            , the activity to be started.
	 */
	private void activityStarter(Class<?> c)
	{
		Log.d(c.getSimpleName(), "activityStarter");
		Intent intent = new Intent(MainMenu.this, c);
		startActivity(intent);
	}

	private boolean isConnectedToInternet()
	{
		ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectionManager
				.getActiveNetworkInfo();

		return activeNetworkInfo != null;
	}
	/**
	 * Give the user a notice that the device is not connected to internet
	 */
	private void makeConnectionToast()
	{
		Toast.makeText(getApplicationContext(), R.string.connect_message, Toast.LENGTH_LONG).show();
	}

}
