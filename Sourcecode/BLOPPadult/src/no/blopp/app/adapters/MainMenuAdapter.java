package no.blopp.app.adapters;

import no.blopp.app.adapters.MainMenuItem.MenuOptions;
import no.blopp.app.conf.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainMenuAdapter extends BaseAdapter
{
	private MainMenuItem[] items;
	private Context context;
	public Bitmap medicinePlan, instructions, medicineLog, registerTreatment, information;
	
	public MainMenuAdapter(Context context)
	{
		this.context = context;
		initialize();
	}
	/**
	 * Initializes the list items in the main menu
	 */
	public void initialize()
	{
		initBitmaps();
		this.items = new MainMenuItem[5];
		items[0] = new MainMenuItem("Medisinplan", medicinePlan, MenuOptions.PLAN);
		items[1] = new MainMenuItem("Etterregistrer medisin", registerTreatment, MenuOptions.TREATMENT);
		items[2] = new MainMenuItem("Medisinlogg", medicineLog, MenuOptions.LOG);
		items[3] = new MainMenuItem("Legemiddelinformasjon", instructions, MenuOptions.INSTRUCTIONS);		
		items[4] = new MainMenuItem("Manual", information, MenuOptions.MANUAL);
		
	}
	/**
	 * Initializes images used in the main menu
	 */
	public void initBitmaps()
	{
		medicinePlan = BitmapFactory.decodeResource(context.getResources(), R.drawable.medicineplan);
		medicineLog= BitmapFactory.decodeResource(context.getResources(), R.drawable.log);
		information = BitmapFactory.decodeResource(context.getResources(), R.drawable.info);
		instructions = BitmapFactory.decodeResource(context.getResources(), R.drawable.instructions);
		registerTreatment = BitmapFactory.decodeResource(context.getResources(), R.drawable.etterregistrer);
	}
	public int getCount()
	{
		return items.length;
	}

	public Object getItem(int position)
	{
		return items[position];
	}

	public long getItemId(int position)
	{
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View mainMenuListView;

		if (convertView == null)
		{

			mainMenuListView = new View(context);

			mainMenuListView = inflater.inflate(R.layout.main_menu_list_item, parent,
					false);

			ImageView imageView = (ImageView) mainMenuListView
					.findViewById(R.id.main_menu_icon);
			imageView.setImageBitmap(items[position].getIcon());
			
			TextView nameView = (TextView) mainMenuListView
					.findViewById(R.id.main_menu_list_textView);
			nameView.setText(items[position].getOptions());
			nameView.setTextColor(Color.BLACK);
			nameView.setTextSize(20);
			nameView.setPadding(15, 0, 0, 0);
			mainMenuListView.setPadding(5, 25, 0, 25);
			
		} else
		{
			mainMenuListView = convertView;
		}
		return mainMenuListView;
	}
	
	/**
	 * 
	 * @return the complete list of main menu items.
	 */
	public MainMenuItem[] getMainMenuItems()
	{
		return this.items;
	}

}
