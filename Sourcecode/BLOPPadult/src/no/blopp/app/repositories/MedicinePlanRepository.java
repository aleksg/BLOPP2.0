package no.blopp.app.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import no.blopp.app.models.MedicinePlanModel;
/**
 * @deprecated
 * The repository package is a start on further development of the project. It contains some methods for inserting and updating
 * records in the database. The thought is that when further development starts, the application must use either a different database
 * directly accessible for the application, or it must use a webservice like the one used now. If a different database server is used,
 * the developers taking over can extend the current functionality. 
 */
@Deprecated
public class MedicinePlanRepository extends SuperRepository implements IMedicinePlanRepository {

	public MedicinePlanModel getMedicinePlanById(int id) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public List<MedicinePlanModel> getMedicinePlans(int id) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void updateMedicinePlan(MedicinePlanModel model) throws SQLException
	{
		// TODO Auto-generated method stub
		
	}

	public void deleteMedicinePlan(MedicinePlanModel model) throws SQLException
	{

		statement = dbConnection.getConnection().createStatement();
		String sql = "DELETE FROM 'MEDICAL_PLANS' WHERE id="+model.getId();
		statement.execute(sql);
		resultSet.close();
	}
	
	public MedicinePlanModel createMedicinePlanModelFromSQL(ResultSet rs) throws SQLException 
	{
		//MedicinePlanModel medicinePlanModel = new MedicinePlanModel(rs.getInt("id"), rs.getString("label"));
		return null;

	}

//	public MedicinePlanModel getMedicinePlanById(int id) throws SQLException 
//	{
//		statement = dbConnection.getConnection().createStatement();
//		resultSet = statement.executeQuery("SELECT * FROM 'MEDICAL_PLANS' WHERE id=" + id);
//		MedicinePlanModel medicinePlanModel = createMedicinePlanModelFromSQL(resultSet);
//		return medicinePlanModel;
//	}
//
//	public List<MedicinePlanModel> getMedicinePlans(int id) throws SQLException 
//	{
//		ArrayList<MedicinePlanModel> medicinPlans = new ArrayList<MedicinePlanModel>();
//		statement = dbConnection.getConnection().createStatement();
//		resultSet = statement.executeQuery("SELECT * FROM 'MEDICAL_PLANS'");
//		while (resultSet.next()) {
//			MedicinePlanModel mpm = createMedicinePlanModelFromSQL(resultSet);
//			medicinPlans.add(mpm);
//			System.out.println("Added MedicinePlan to the list. Woopadiddledoo!");
//		}
//		resultSet.close();
//		return medicinPlans;
//	}
//
//	public void updateMedicinePlan(MedicinePlanModel model) throws SQLException 
//	{
//		statement = dbConnection.getConnection().createStatement();
//		String sql = "UPDATE 'MEDICAL_PLANS' SET label="+model.getLabel()+" WHERE id="+model.getId();
//		statement.executeUpdate(sql);
//		resultSet.close();
//	}
//
//	public void deleteMedicinePlan(MedicinePlanModel model) throws SQLException 
//	{
//		statement = dbConnection.getConnection().createStatement();
//		String sql = "DELETE FROM 'MEDICAL_PLANS' WHERE id="+model.getId();
//		statement.execute(sql);
//		resultSet.close();
//	}
//	
//	public MedicinePlanModel createMedicinePlanModelFromSQL(ResultSet rs) throws SQLException 
//	{
//		MedicinePlanModel medicinePlanModel = new MedicinePlanModel(rs.getInt("id"), rs.getString("label"));
//		return medicinePlanModel;
//	}

}
