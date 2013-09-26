package no.blopp.app.med.JsonModels;

public class ChildResultModel
{
	private int id;
	private String name;
	private int persNum;
	private int medicalPlanId;
	private int avatarId;
	private int credits;
	private double locationLatitude;
	private double locationAltitude;

	public ChildResultModel() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPersNum() {
		return persNum;
	}

	public void setPersNum(int persNum) {
		this.persNum = persNum;
	}

	public int getMedicalPlanId() {
		return medicalPlanId;
	}

	public void setMedicalPlanId(int medicalPlanId) {
		this.medicalPlanId = medicalPlanId;
	}

	public int getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(int avatarId) {
		this.avatarId = avatarId;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public double getLocationLatitude() {
		return locationLatitude;
	}

	public void setLocationLatitude(double locationLatitude) {
		this.locationLatitude = locationLatitude;
	}

	public double getLocationAltitude() {
		return locationAltitude;
	}

	public void setLocationAltitude(double locationAltitude) {
		this.locationAltitude = locationAltitude;
	}
}
