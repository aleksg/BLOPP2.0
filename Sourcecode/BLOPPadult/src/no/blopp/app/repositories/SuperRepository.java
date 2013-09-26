package no.blopp.app.repositories;

import java.sql.ResultSet;
import java.sql.Statement;


import no.blopp.app.jsonparsers.DBConnection;
/**
 * @deprecated
 * The repository package is a start on further development of the project. It contains some methods for inserting and updating
 * records in the database. The thought is that when further development starts, the application must use either a different database
 * directly accessible for the application, or it must use a webservice like the one used now. If a different database server is used,
 * the developers taking over can extend the current functionality. 
 */
@Deprecated
public class SuperRepository
{

	protected DBConnection dbConnection;
	protected Statement statement = null;
	protected ResultSet resultSet = null;
	
	public SuperRepository()
	{
		System.out.println("Connecting");
		dbConnection = new DBConnection();
	}
}
