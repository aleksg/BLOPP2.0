package no.blopp.app.models.JsonModels;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class DeleteMedicineModel {
	private static final int CHILDID = 6; //Replace when support for several children is possible
	private int medicineId;
	private String time;
	private int healthStateId;
	/**
	 * 
	 * @param id. The id of the medicine
	 * @param time, the time when the medicine was originally to be taken
	 */
	public DeleteMedicineModel(int id, String time, int healthStateId)
	{
		this.medicineId = id;
		this.time= time; 
		this.healthStateId = healthStateId;
	}
	public int getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getHealthStateId()
	{
		return this.healthStateId;
	}
	@Override
	/**
	 * Returns the URL-encoded POST-parameters
	 */
	public String toString() {
		String medicine, timer, child, health;
		try {
			child = URLEncoder.encode(Integer.toString(CHILDID), "UTF-8");
			timer = URLEncoder.encode(this.time, "UTF-8");
			medicine = URLEncoder.encode(Integer.toString(this.medicineId), "UTF-8");
			health = URLEncoder.encode(Integer.toString(this.healthStateId), "UTF-8");
			return "child_id="+child + "&time=" + timer + "&medicine_id=" + medicine + "&health_state_id=" + health; 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
