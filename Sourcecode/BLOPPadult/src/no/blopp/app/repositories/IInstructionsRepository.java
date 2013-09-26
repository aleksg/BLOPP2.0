package no.blopp.app.repositories;
import java.sql.SQLException;

import java.util.List;

import android.graphics.Bitmap;

import no.blopp.app.models.*;
/**
 * @deprecated
 * The repository package is a start on further development of the project. It contains some methods for inserting and updating
 * records in the database. The thought is that when further development starts, the application must use either a different database
 * directly accessible for the application, or it must use a webservice like the one used now. If a different database server is used,
 * the developers taking over can extend the current functionality. 
 */
@Deprecated
public interface IInstructionsRepository
{
	Instructions getInstructionById(int id) throws SQLException;
	
	List<Instructions> getInstructions() throws SQLException;
	
	List<Bitmap> getInstructionsImagesById(int id) throws SQLException;
	
	
}
