/*
 * Copyright (C) 2011 Chris Gao <chris@exina.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.blopp.app.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import org.joda.time.DateTime;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import no.blopp.app.adapters.TakenMedicinesAdapter;
import no.blopp.app.adapters.PollenDistributionAdapter;
import no.blopp.app.conf.R;
import no.blopp.app.views.*;
import no.blopp.app.views.CalendarView.OnCellTouchListener;
import no.blopp.app.xmlfeed.PollenCast;
import no.blopp.app.models.LogModel;
import no.blopp.app.models.PollenState;
import no.blopp.app.utils.*;


public class CalendarActivity extends Activity implements
		android.view.View.OnClickListener, OnCellTouchListener
{
	private static final int CHILD_ID = 6;
	private static final String TAG = CalendarActivity.class.getSimpleName();
	CalendarView calendarView;
	TextView monthTextView;
	Button nextMonthButton, previousMonthButton;
	
	private ListView medicineTakenListView;
	private ListView pollenListView;
	private LogModel logModel;
	
	private PollenCast pollenCast;
	private DateAdapter dateAdapter;
	private int day, month, year;
	
	private DateTime dateTime = new DateTime();
	private TakenMedicinesAdapter medicineGridAdapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);
		
		logModel = new LogModel(CHILD_ID, dateTime.getMonthOfYear(), dateTime.getYear());
		
		calendarView = (CalendarView) findViewById(R.id.calendarview);
		calendarView.setOnCellTouchListener(this);
		
		initializeDaysShownInMedicineList();
		
		nextMonthButton = (Button) findViewById(R.id.next_month_button);
		nextMonthButton.setOnClickListener(this);
		
		previousMonthButton = (Button) findViewById(R.id.prev_month_button);
		previousMonthButton.setOnClickListener(this);

		
		
		monthTextView = (TextView) findViewById(R.id.month_of_year_textview);
		updateMonthTextField();
		
		medicineTakenListView = (ListView) findViewById(R.id.medicine_taken_listView);
		medicineGridAdapter = new TakenMedicinesAdapter(getApplicationContext(), getAmountOfMedicinesTaken());
		medicineTakenListView.setAdapter(medicineGridAdapter);
		
		pollenListView = (ListView)findViewById(R.id.pollen_listView);
		pollenListView.setAdapter(new PollenDistributionAdapter(getApplicationContext(), getPollenStates()));
		pollenListView.setDivider(null);
	}
	

	private void initializeDaysShownInMedicineList()
	{
		
		day = dateTime.getDayOfMonth();
		month = dateTime.getMonthOfYear();
		year = dateTime.getYear();
		dateAdapter = new DateAdapter(day, month, year);
		makeToast(day + "-" + month + "-" + year, Toast.LENGTH_SHORT);
	}
	
	private void updateMonthTextField()
	{
		DateTime.Property month = dateTime.monthOfYear();
		DateTime.Property year = dateTime.year();
		monthTextView.setText(month.getAsText() + "-" + year.getAsText());
	}
	
	/**
	 * When a user clicks "Next" or "Previous", update the days such that the JSON-call becomes correct.
	 */
	
	private void updateDates()
	{
		day = dateTime.getDayOfMonth();
		month = dateTime.getMonthOfYear();
		year = dateTime.getYear();
	}
	
	/**
	 * Updates medicineTakenListView according to day selected.
	 */
	public void onTouch(Cell cell)
	{
		day = cell.getDayOfMonth();
		month = calendarView.getMonth()+1;
		year = calendarView.getYear();
		
		dateAdapter = new DateAdapter(day, month, year);
		medicineTakenListView.setAdapter(new TakenMedicinesAdapter(getApplicationContext(), getAmountOfMedicinesTaken()));
		
		makeToast(day+"-"+month+"-"+year, Toast.LENGTH_SHORT);
	}
	/**
	 * Used if the user presses "next" or "previous"
	 */
	public void onClick(View v)
	{
		if (v.getId() == nextMonthButton.getId())
		{
			calendarView.nextMonth();
			dateTime = dateTime.plusMonths(1);
			logModel = new LogModel(CHILD_ID, dateTime.getMonthOfYear(), dateTime.getYear());
		} else if (v.getId() == previousMonthButton.getId())
		{
			calendarView.previousMonth();
			dateTime = dateTime.minusMonths(1);
			logModel = new LogModel(CHILD_ID, dateTime.getMonthOfYear(), dateTime.getYear());
		}
		updateMonthTextField();	
		updateDates();
		refreshMedicinesTaken(day, month, year);
	}
	/**
	 * Helper to redraw medicineTakenListView. 
	 * @param day
	 * @param month
	 * @param year
	 */
	private void refreshMedicinesTaken(int day, int month, int year)
	{
		dateAdapter = new DateAdapter(day, month, year);
		medicineTakenListView.setAdapter(new TakenMedicinesAdapter(getApplicationContext(), getAmountOfMedicinesTaken()));		
		makeToast(day+"-"+month+"-"+year, Toast.LENGTH_SHORT);
	}
	/**
	 * 
	 * @return a hashmap with medicineId as key and number of times this medicine has been used as value
	 */
	private HashMap<Integer, Integer> getAmountOfMedicinesTaken()
	{
		return logModel.getAmountOfMedicineAtDate(dateAdapter.getSqlFormattedDate());
	}
	
	/**
	 * WARNING: NOT COMPLETED YET.
	 * @return ArrayList<PollenState> which contains pollenstates for the selected day
	 */
	private ArrayList<PollenState> getPollenStates()
	{
		this.pollenCast = new PollenCast();
		pollenCast.execute();
		try
		{
			pollenCast.get();
		} catch (InterruptedException e)
		{
			makeToast(R.string.download_error, Toast.LENGTH_SHORT);
			//Error handling
			e.printStackTrace();
		} catch (ExecutionException e)
		{
			makeToast(R.string.download_error, Toast.LENGTH_SHORT);
			//Error handling
			e.printStackTrace();
		}
		return pollenCast.getPollenStateAtDayModel().getPollenStatesAtDay();
		
	}
	/**
	 * Used to infrom the user about which date is set.
	 * @param toastMessage, date to be displayed or the error message. 
	 * @param length
	 */
	private void makeToast(int toastMessage, int length)
	{
		Toast.makeText(this, toastMessage, length).show();
	}
	
	
	private void makeToast(String toastMessage, int length)
	{
		Toast.makeText(this, toastMessage, length).show();
	}
}