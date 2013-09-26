package no.blopp.app.repositories;

import java.sql.Date;

import java.sql.SQLException;
import java.util.List;

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
public interface ILogModelRepository
{
	LogModel getLogModelByChildId(int id) throws SQLException;
	
	void updateLogModel(int childId, LogModel logModel) throws SQLException;
	
	List<PollenState> getPollenAtDate(Date date) throws SQLException;
	
	HealthZone getHealthZoneAtDate(Date date) throws SQLException;
	
	List<Medicine> getMedicinesForChildAtDate(int childId, Date date) throws SQLException;
	
	
}
