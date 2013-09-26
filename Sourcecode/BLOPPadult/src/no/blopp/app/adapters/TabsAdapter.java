package no.blopp.app.adapters;

import no.blopp.app.conf.R;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

public class TabsAdapter extends PagerAdapter{

	private static final int INSTRUCTIONS_PAGES = 7;
	@Override
	public int getCount() {
        return INSTRUCTIONS_PAGES;
    }
    @Override
	public Object instantiateItem(View collection, int position) {
        LayoutInflater inflater = (LayoutInflater) collection.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int resId = 0;
        switch (position) {
        case 0:
            resId = R.layout.instructionstab1;
            break;
        case 1:
            resId = R.layout.instructionstab2;
            break;
        case 2:
            resId = R.layout.instructionstab3;
            break;
        case 3:
            resId = R.layout.instructionstab4;
            break;
        case 4:
            resId = R.layout.instructionstab5;
            break;
        case 5:
        	resId = R.layout.instructionstab6;
        	break;
        case 6: resId = R.layout.instructionstab7;
        	break;
        }
        View view = inflater.inflate(resId, null);
        ((ViewPager) collection).addView(view, 0);
        return view;
    }
    @Override
    public void destroyItem(View view, int arg1, Object arg2) {
        ((ViewPager) view).removeView((View) arg2);
    }
    @Override
    public boolean isViewFromObject(View view, Object arg1) {
        return view == ((View) arg1);
    }
    @Override
    public Parcelable saveState() {
        return null;
    }
	
}