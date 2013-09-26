package no.blopp.app.med.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		//Commented out until the view is made
		//setContentView(R.layout.SplashScreenActivity);
		Thread timer = new Thread(){
			@Override
			public void run(){
				try {
					//Will be replaced with a splash screen.
					sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally{
					Intent openMainMenu = new Intent("no.blopp.app.med.MainMenu.MainMenu");
					startActivity(openMainMenu);
				}
			}
		};
		timer.start();
	}
}
