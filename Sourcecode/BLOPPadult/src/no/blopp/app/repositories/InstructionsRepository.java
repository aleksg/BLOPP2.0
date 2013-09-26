package no.blopp.app.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import no.blopp.app.models.Instructions;
import android.graphics.Bitmap;
/**
 * @deprecated
 * The repository package is a start on further development of the project. It contains some methods for inserting and updating
 * records in the database. The thought is that when further development starts, the application must use either a different database
 * directly accessible for the application, or it must use a webservice like the one used now. If a different database server is used,
 * the developers taking over can extend the current functionality. 
 */
@Deprecated
public class InstructionsRepository extends SuperRepository implements
		IInstructionsRepository
{

	public InstructionsRepository()
	{
		super();
	}
	
	
	public Instructions getInstructionById(int id) throws SQLException
	{
		statement = dbConnection.getConnection().createStatement();
		resultSet = statement
				.executeQuery("SELECT * FROM 'MEDICINE_INSTRUCTIONS' WHERE 'id'=" + id);
		return createInstructionFromSQL(resultSet);
		
	}
	
	
	public List<Instructions> getInstructions() throws SQLException
	{
		List<Instructions> instructions = new ArrayList<Instructions>();
		statement = dbConnection.getConnection().createStatement();
		resultSet = statement.executeQuery("SELECT * FROM MEDICINE_INSTRUCTIONS");
		
		while (resultSet.next()) 
		{
			instructions.add(createInstructionFromSQL(resultSet));
		}
		resultSet.close();
		return instructions;
	}
	
	
	public List<Bitmap> getInstructionsImagesById(int id) throws SQLException
	{
		//TODO: Create the method
		return null;
	}
	
	
	public Instructions createInstructionFromSQL(ResultSet rs) throws SQLException
	{
		Instructions instruction = new Instructions(rs.getInt("id"),
				rs.getString("information"), rs.getString("effect"), rs.getString("url"));
		rs.close();
		return instruction;
	}



}
