package no.blopp.app.med.activities;

import no.blopp.app.med.R;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import no.blopp.app.med.div.ColorMeds;
import no.blopp.app.med.models.*;


/**
 * The <code>Activity</code> used for the alarm. This is updated in the 
 * <code>AlarmUpdateReciever</code> class, and it updates its view based on which 
 * <code>MedicinePlanModel</code> is in the extras from the intent.
 */
public class AlarmReceiverActivity extends Activity {
    private MedicinePlanModel medicinePlanModel;
    private Ringtone ringtone;
    private AudioManager audioManager;
    private int ringerMode;
    @SuppressWarnings("static-access")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //update medicinePlanModel with the medicine sent in with the intent
		medicinePlanModel = (MedicinePlanModel) getIntent().getExtras().getSerializable("medicinePlanModel");
       
       
        // set window related stuff, view etc.
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.alarm);
        
        //update the view based on the medicinePlanModel
        updateViewWithMedicine();
        
        //Find the phones assigned alarmsound, and play it.
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
        ringtone.play();
        
        //Set the phones ringer mode to be normal
		audioManager = (AudioManager)getSystemService(this.AUDIO_SERVICE);
        ringerMode = audioManager.getRingerMode();
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        
        
        //Button for stopping the alarm
        ImageView stopAlarm = (ImageView) findViewById(R.id.stop_alarm_imageview);
        stopAlarm.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
            	ringtone.stop();
            	audioManager.setRingerMode(ringerMode);
                finish();
            }
        });
        
        //button for starting the distraction sequence.
        ImageView startTreatment = (ImageView) findViewById(R.id.alarm_medicine_imageview);
        startTreatment.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Log.d(DistractionActivity.class.getSimpleName(), "activityStarter");
				Intent intent = new Intent(AlarmReceiverActivity.this,DistractionActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("medicinePlanModel", medicinePlanModel);
				intent.putExtras(bundle);
				startActivity(intent);
				ringtone.stop();
            	audioManager.setRingerMode(ringerMode);
				finish();
			}
		});
    }
 
    /**
     * Updates the view with the medicine that was found in the intents extras.
     * this means updating the image and text according to medicinePlanModel.
     */
    private void updateViewWithMedicine() {
    	ImageView imgv = (ImageView) findViewById(R.id.alarm_medicine_imageview);
    	TextView txtv = (TextView) findViewById(R.id.alarm_medicine_textview);
    	imgv.setImageResource(ColorMeds.medicineImage(medicinePlanModel.getMedicineColor()));
		txtv.setText(String.format("Husk å ta %s!", medicinePlanModel.getMedicineName()));
	}
}