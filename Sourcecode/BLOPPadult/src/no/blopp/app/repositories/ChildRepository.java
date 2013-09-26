package no.blopp.app.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import no.blopp.app.models.Child;

/**
 * @deprecated
 * The repository package is a start on further development of the project. It contains some methods for inserting and updating
 * records in the database. The thought is that when further development starts, the application must use either a different database
 * directly accessible for the application, or it must use a webservice like the one used now. If a different database server is used,
 * the developers taking over can extend the current functionality. 
 */
@Deprecated
public class ChildRepository extends SuperRepository implements
		IChildRepository
{

	public ChildRepository()
	{
		super();
	}
	public Child getChildById(int id) throws SQLException
	{
		statement = dbConnection.getConnection().createStatement();
		resultSet = statement
				.executeQuery("SELECT * FROM 'CHILDREN' WHERE 'id'=" + id);
		Child child = createChildFromSQL(resultSet);
		return child;
	}

	public List<Child> getAllChildren() throws SQLException
	{
		ArrayList<Child> children = new ArrayList<Child>();
		statement = dbConnection.getConnection().createStatement();
		resultSet = statement.executeQuery("SELECT * FROM 'Children'");
		while (resultSet.next())
		{
			Child c = createChildFromSQL(resultSet);
			children.add(c);
			System.out.println("Added child to list");
		}
		resultSet.close();
		return children;
	}

	public void updateChild(Child child)
	{
		return;
	}

	public Child createChildFromSQL(ResultSet rs) throws SQLException
	{
		Child child = new Child(rs.getString("name"),
				rs.getString("pers_num"));
		// dbConnection.closeConnection();
		return child;
	}
	


}
