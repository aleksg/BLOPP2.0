package no.blopp.app.jsonparsers;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


/**
 * @deprecated 
 * Not used since all databasecalls are done through JSON.
 * However, it may be used when further development starts, if the application will
 * use a different mysql server for direct access without going through php-pages.
 */
@Deprecated
public class DBConnection
{
	private static final String URL = "jdbc:mysql://mysql.stud.ntnu.no/yngvesva_blopp";
	private static final String DBUserName = "yngvesva_blopp";
	private static final String DBPassword = "Naaf2012";
	private Connection connection;


	public DBConnection()
	{
		connection = null;
		initConnection();
	}

	public void initConnection()
	{
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(URL, DBUserName, DBPassword);
			System.out.println("DBConnection Connection success");
		}catch(SQLException ex)
		{
			System.out.println("DBConnection Connection failure");
			System.out.println(ex.getMessage());
		}catch(ClassNotFoundException ex)
		{
			System.out.println("DBConnection Driver loading failed");
		} catch (InstantiationException e)
		{
			System.out.println("DBConnection Instantiation of connection failed");

		} catch (IllegalAccessException e)
		{
			System.out.println("DBConnection Illegal access?");
		}
		
	}

	public Connection getConnection()
	{
		return connection;
	}

	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}

	public void closeConnection()
	{
		try
		{
			this.connection.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
}
