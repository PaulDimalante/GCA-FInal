package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Function;
import systemInterfaces.GCA_FunctionDAOI;

public class GCA_FunctionDAO implements GCA_FunctionDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_Function get(Connection conn, int id) throws GCAException {
		GCA_Function function = new GCA_Function();
		String sql = SQL.GET_GCA_FUNCTIONS_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				function.setFunctionId(rs.getInt("functionid"));
				function.setFunctionName(rs.getString("functionname"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return function;
	};

	public Map<String, GCA_Function> getListAll(Connection conn) throws GCAException {
		Map<String, GCA_Function> map = new TreeMap<String, GCA_Function>();
		String sql = SQL.GET_GCA_FUNCTIONS_ALL.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				GCA_Function function = new GCA_Function();
				function.setFunctionId(rs.getInt("functionid"));
				function.setFunctionName(rs.getString("functionname"));
				map.put(function.getFunctionName(), function);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

//	public Map<String, GCA_Function> getListById(Connection conn, int id) throws GCAException {
////	};

	public boolean insert(Connection conn, GCA_Function o) throws GCAException {
		boolean success = false;
		GCA_Function function = new GCA_Function();
		String sql = SQL.INSERT_GCA_FUNCTIONS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, function.getFunctionId());
			ps.setString(2, function.getFunctionName());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_Function o) throws GCAException {
		boolean success = false;
		GCA_Function function = new GCA_Function();
		String sql = SQL.DELETE_GCA_FUNCTIONS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, function.getFunctionId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_Function o) throws GCAException {
		boolean success = false;
		GCA_Function function = new GCA_Function();
		String sql = SQL.UPDATE_GCA_FUNCTIONS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, function.getFunctionName());
			ps.setInt(2, function.getFunctionId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
