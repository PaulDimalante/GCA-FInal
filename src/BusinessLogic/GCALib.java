package BusinessLogic;

import java.sql.Connection;

import DAO.GCA_DBFunctionsDAO;
import customExceptions.GCAException;

public class GCALib {
	public static int getNextId(Connection connection) {
		int id = -1;
		
		try {
			id = new GCA_DBFunctionsDAO().getNextId(connection);
		} catch (GCAException e) {
			id = -1;
			e.printStackTrace();
		} finally {
			return id;
		} 
	}

}
