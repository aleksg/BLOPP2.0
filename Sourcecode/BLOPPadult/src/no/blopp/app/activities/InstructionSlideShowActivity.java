package no.blopp.app.activities;

import no.blopp.app.adapters.TabsAdapter;
import no.blopp.app.conf.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class InstructionSlideShowActivity extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.pager);
	    TabsAdapter adapter = new TabsAdapter();
	    ViewPager myPager = (ViewPager) findViewById(R.id.instructionspager);
	    myPager.setAdapter(adapter);
	    myPager.setCurrentItem(0);
	}

}
