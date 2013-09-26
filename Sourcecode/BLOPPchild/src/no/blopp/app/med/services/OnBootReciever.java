package no.blopp.app.med.services;

import java.util.Calendar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * A BroadcastReceiver running when the system boots, ensuring that alarms
 * is always set according to the database. The Broadcast updating alarms is set
 * using alarmManager to make sure the alarms in the system is updated regularly.
 */
public class OnBootReciever extends BroadcastReceiver{
	private int updateTime = 30*1000;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		//Set up the Calendar used for the first AlarmUpdateReceiver, 10 seconds from current time.
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND,10);
		Intent i = new Intent(context, AlarmUpdateReceiver.class);
		
		//Set up the pendingIntent, and set the alarm to repeat according to updateTime.
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
    			0, i, PendingIntent.FLAG_CANCEL_CURRENT);
    	AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
    	alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), updateTime, pendingIntent);  
		
	}	
}
