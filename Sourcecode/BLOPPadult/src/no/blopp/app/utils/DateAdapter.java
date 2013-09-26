package no.blopp.app.utils;


/**
 * 
 * Used to get sql formatted date from CalendarView. 
 * @author aarseth_90
 * 
 */
public class DateAdapter
{
	private int day, month, year;
	public DateAdapter()
	{
		
	}
	public DateAdapter(int day, int month, int year)
	{
		this.day = day;
		this.month = month;
		this.year = year;
	}
	

	/**
	 * @return sql-formatted date.
	 */
	public String getSqlFormattedDate()
	{
		//TODO: Maybe add a check if this.day<=9. If this is the case, we should add a zero to the string, getting the "real" SQL-format.
		//As for now, it works, and if it works, don't fix it
		return "" + this.year + "-" + this.month + "-" + (this.day<=9 ? "0"+this.day : this.day);   

	}
}
