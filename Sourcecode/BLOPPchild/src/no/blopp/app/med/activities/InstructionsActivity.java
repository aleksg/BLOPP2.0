package no.blopp.app.med.activities;

import no.blopp.app.med.R;
import no.blopp.app.med.adapters.TabsAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class InstructionsActivity extends Activity{

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
