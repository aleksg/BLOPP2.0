package no.blopp.app.repositories;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import no.blopp.app.models.HealthState;
import no.blopp.app.models.HealthZone;
import no.blopp.app.models.LogModel;
import no.blopp.app.models.Medicine;
import no.blopp.app.models.PollenState;
/**
 * @deprecated
 * The repository package is a start on further development of the project. It contains some methods for inserting and updating
 * records in the database. The thought is that when further development starts, the application must use either a different database
 * directly accessible for the application, or it must use a webservice like the one used now. If a different database server is used,
 * the developers taking over can extend the current functionality. 
 */
@Deprecated
public class LogModelRepository extends SuperRepository implements
		ILogModelRepository
{

	public LogModel getLogModelByChildId(int id) throws SQLException
	{
		return null;
	}

	public void updateLogModel(int childId, LogModel logModel)
	{
		// TODO Auto-generated method stub
		
	}

	public List<PollenState> getPollenAtDate(Date date) throws SQLException
	{
		List<PollenState> states = new ArrayList<PollenState>();
		statement = dbConnection.getConnection().createStatement();
		resultSet = statement.executeQuery(
				"SELECT POLLEN_SPREAD.spread, POLLEN_STATES.name FROM POLLEN_SPREAD, POLLEN_STATES, POLLEN"+
				"WHERE POLLEN.date ="+date+ 
				"AND POLLEN_SPREAD.id = POLLEN.pollen_spread_id"+
				"AND POLLEN_STATES.id = POLLEN.pollen_state_id;");
		
		while(resultSet.next())
		{
			states.add(createPollenStateFromResultSet(resultSet));
		}
		resultSet.close();
		return states;
	}

	public HealthZone getHealthZoneAtDate(Date date) throws SQLException
	{
		statement = dbConnection.getConnection().createStatement();
		resultSet = statement.executeQuery("");
		return null;
	}

	public List<Medicine> getMedicinesForChildAtDate(int childId, Date date) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}
	private PollenState createPollenStateFromResultSet(ResultSet rs) throws SQLException
	{
//		PollenState state = new PollenState(rs.getDate("date"), rs.getString("pollen_state_id"),
//				rs.getString("pollen_spread_id"));
//		return state;
		return null;
	}
	
	private HealthZone createHealthZoneFromResultSet(ResultSet rs) throws SQLException
	{
		int healthZoneId = rs.getInt("id");
		
		return HealthState.getHealthZoneById(healthZoneId);
		
	}
	
	public static void main(String[] args)
	{
		LogModelRepository repo = new LogModelRepository();
		try
		{
			System.out.println(repo.getPollenAtDate(new Date(2012, 9, 30)).toString());
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
