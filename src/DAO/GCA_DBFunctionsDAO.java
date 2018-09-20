package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import customExceptions.GCAException;
import systemInterfaces.GCA_DBFunctionsDAOI;

public class GCA_DBFunctionsDAO implements GCA_DBFunctionsDAOI {
	
	public int getNextId(Connection conn) throws GCAException {
		int id = -1;
		String sql = SQL.GET_NEXT_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return id;
	};

	public int getCurrentId(Connection conn) throws GCAException {
		int id = -1;
		String sql = SQL.GET_CURRENT_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}
		
		return id;
	};
	
}
