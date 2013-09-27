package no.blopp.app.activities;

import java.io.ByteArrayOutputStream;
import no.blopp.app.adapters.MedicineInstructionsListAdapter;
import no.blopp.app.adapters.MedicineListModel;
import no.blopp.app.conf.R;
import no.blopp.app.repositories.AvailableMedicines;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MedicationInformationActivity extends Activity implements
		 OnItemClickListener
{
	private static final String TAG = MedicationInformationActivity.class.getSimpleName();
	private ListView medicineListView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.medicine_instructions);
		medicineListView = (ListView) findViewById(R.id.instruction_options_listView);
		medicineListView.setAdapter(new MedicineInstructionsListAdapter(getApplicationContext()));
		medicineListView.setOnItemClickListener(this);
	}
	
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		/*
		 * find medicine id, then go to next step with this id.
		 */
		int medicineId = getMedicineId(position);
		AvailableMedicines am = new AvailableMedicines();
		String medicineName = am.getMedicineById(medicineId);
		MedicineListModel model = (MedicineListModel) parent.getItemAtPosition(position);
		
		//Convert the bitmap to bytearray and pass it to InstructionsActivity
		Bitmap bmp = model.getBitmap();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] byteArray = stream.toByteArray();
		String bytes = byteArray.toString();
		
		String desc = model.getDescription();
		
		//Start next activity
		Intent intent = new Intent(MedicationInformationActivity.this, InstructionOptionsActivity.class);
		intent.putExtra("name", medicineName);
		intent.putExtra("desc", desc);
		intent.putExtra("bitmapId", bytes);
		startActivity(intent);

	}
		
	private int getMedicineId(int position)
	{
		MedicineListModel model = (MedicineListModel)medicineListView.getItemAtPosition(position);
		String med = model.getName();
		AvailableMedicines am = new AvailableMedicines();
		int medicineId = am.getMedicineByName(med);
		return medicineId;
	}
}
