package no.blopp.app.models;

/**
 * @deprecated
 * This model needs some extensions before deployment. We had no time to implement functionality for several children. These models
 * can be used as a start.
 */
@Deprecated
public class Person
{
	protected int id;
	protected String name;
	protected String ssn;
	
	public Person(String name, String ssn)
	{
		this.name = name;
		this.ssn = ssn;
	}
}
