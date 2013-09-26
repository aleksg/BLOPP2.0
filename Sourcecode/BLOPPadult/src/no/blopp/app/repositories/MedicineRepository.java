package no.blopp.app.repositories;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import no.blopp.app.models.Medicine;
/**
 * @deprecated
 * The repository package is a start on further development of the project. It contains some methods for inserting and updating
 * records in the database. The thought is that when further development starts, the application must use either a different database
 * directly accessible for the application, or it must use a webservice like the one used now. If a different database server is used,
 * the developers taking over can extend the current functionality. 
 */
@Deprecated
public class MedicineRepository extends SuperRepository implements IMedicineRepository
{

	public Medicine getMedicineById(int id) throws SQLException 
	{
		statement = dbConnection.getConnection().createStatement();
		resultSet = statement.executeQuery("SELECT * FROM 'MEDICINES' WHERE 'id'=" + id);
		return createMedicineFromSQL(resultSet);
	}

	public List<Medicine> getMedicines() throws SQLException 
	{
		List<Medicine> medicines = new ArrayList<Medicine>();
		statement = dbConnection.getConnection().createStatement();
		resultSet = statement.executeQuery("SELECT * FROM 'MEDICINES'");
		
		while (resultSet.next()) 
		{
			medicines.add(createMedicineFromSQL(resultSet));
		}
		resultSet.close();
		return medicines;
	}


	public void updateMedicine(Medicine medicine) throws SQLException 
	{
		statement = dbConnection.getConnection().createStatement();
		String sql = "UPDATE 'MEDICINES' SET name="+medicine.getName()+"AND instructions_id="+medicine.getInstructionsId()+
				" WHERE id=" + medicine.getId();
		statement.executeUpdate(sql);
		resultSet.close();
	}

	public Medicine createMedicineFromSQL(ResultSet rs) throws SQLException 
	{
		Medicine medicine = new Medicine(rs.getString("name"), rs.getInt("instructions_id"));
		rs.close();
		return medicine;
	}
	
}
