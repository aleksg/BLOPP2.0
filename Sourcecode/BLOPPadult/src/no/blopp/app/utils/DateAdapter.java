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
		return "" + this.year + "-" + (this.month <= 9 ? "0"+this.month : this.month) + "-" + (this.day<=9 ? "0"+this.day : this.day);   
	}
}
