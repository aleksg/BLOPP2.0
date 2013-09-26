package no.blopp.app.activities;

import java.util.Calendar;
import no.blopp.app.models.*;

import no.blopp.app.conf.R;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

//This class should not be used, but it contains the code for adding alarms
public class AlarmActivity extends Activity {
	private static final String TAG = AlarmActivity.class.getSimpleName();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
       setContentView(R.layout.activity_main_menu);
         
       			// ADDING ALARMS:
    			//Set time for the alarm
    			Calendar calendar = Calendar.getInstance();
    			calendar.add(Calendar.SECOND, 10);
    	        //Create a new PendingIntent and add it to the AlarmManager
    	        Intent intent = new Intent(this, AlarmReceiverActivity.class);
    	        
    	        //Make a bundle to get the Medicine object across to the alarm, and add the relevant medicine
    	        Bundle bundle = new Bundle();
    	        //note that the medicine will typically be the one that will causes the alarm.
    	        Medicine medicine = new Medicine("Medisin3", 3, R.drawable.medicine_image_distraction);
    	        bundle.putSerializable("medicine", medicine);

    	        //Add the bundle to the intent and do the standard alarmfiring calls. NOTE: 12345 is a placeholder for a 
    	        //requestcode, and should be changed with a nonstatic number when more alarms are being lined up.
    	        intent.putExtras(bundle);
    	        PendingIntent pendingIntent = PendingIntent.getActivity(this,
    	            12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    	        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    	        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

}
