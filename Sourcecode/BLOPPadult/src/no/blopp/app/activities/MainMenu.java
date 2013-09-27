package no.blopp.app.activities;

import no.blopp.app.adapters.MainMenuAdapter;
import no.blopp.app.adapters.MainMenuItem;
import no.blopp.app.adapters.MainMenuItem.MenuOptions;
import no.blopp.app.conf.R;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainMenu extends Activity implements OnItemClickListener
{

	private static final String TAG = MainMenu.class.getSimpleName();
	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		listView = (ListView) findViewById(R.id.main_menu_listView);
		listView.setAdapter(new MainMenuAdapter(getApplicationContext()));
		listView.setOnItemClickListener(this);

	}

	private void activityStarter(Class<?> c)
	{
		Intent intent = new Intent(MainMenu.this, c);
		startActivity(intent);
	}

	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long id)
	{
		MainMenuAdapter adapter = (MainMenuAdapter) listView.getAdapter();
		MainMenuItem[] items = adapter.getMainMenuItems();
		MainMenuItem itemClicked = items[position];
		reactToInput(itemClicked.getShortDesc());
	}

	/*
	 * Blocks input to activities that needs internet connection.
	 */
	public void reactToInput(MenuOptions option)
	{

		if (option.equals(MenuOptions.INSTRUCTIONS))
		{
			activityStarter(MedicationInformationActivity.class);
		} else if (option.equals(MenuOptions.TREATMENT))
		{
			startTreatment();
		} else if (option.equals(MenuOptions.LOG))
		{
			startCalendar();
		} else if (option.equals(MenuOptions.MANUAL))
		{
			activityStarter(InstructionSlideShowActivity.class);
		} else if (option.equals(MenuOptions.PLAN))
		{
			startPlan();
		}
	}

	private void startCalendar()
	{
		if (isConnectedToInternet())
		{
			activityStarter(CalendarActivity.class);
		} else
		{
			makeConnectionToast();
			return;
		}
	}

	private void startPlan()
	{
		if (isConnectedToInternet())
		{
			activityStarter(MedicationPlanActivity.class);
		} else
		{
			makeConnectionToast();
			return;
		}
		
	}

	private void startTreatment()
	{
		if (isConnectedToInternet())
		{
			activityStarter(TreatmentActivity.class);
		} else
		{
			makeConnectionToast();
		}
	}

	/**
	 * 
	 * @return a boolean, true if the device is connected to internet.
	 * 
	 */
	private boolean isConnectedToInternet()
	{
		ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectionManager
				.getActiveNetworkInfo();

		return activeNetworkInfo != null;
	}

	/**
	 * Give the user a notice that the device is not connected to internet.
	 */
	private void makeConnectionToast()
	{
		Toast.makeText(getApplicationContext(), R.string.connect_message,
				Toast.LENGTH_SHORT).show();
	}
}
