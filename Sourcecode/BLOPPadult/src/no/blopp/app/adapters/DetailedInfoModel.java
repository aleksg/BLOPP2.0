package no.blopp.app.adapters;

public class DetailedInfoModel
{

	public enum detailedInfoIdentifier
	{
		HEADER, INFORMATION
	};

	private String header;
	private String information;

	public DetailedInfoModel(String header, String information,
			detailedInfoIdentifier detailedId)
	{
		this.header = header;
		this.information = information;
	}

	public String getHeader()
	{
		return this.header;
	}

	public String getInfo()
	{
		return this.information;
	}

	public static DetailedInfoModel[] getModel(String medicineName)
	{

		DetailedInfoModel[] model = new DetailedInfoModel[2];
		model[0] = new DetailedInfoModel("Test1", "Test2",
				detailedInfoIdentifier.HEADER);
		model[1] = new DetailedInfoModel("Test3", "Test4",
				detailedInfoIdentifier.INFORMATION);

		return model;
	}

}
