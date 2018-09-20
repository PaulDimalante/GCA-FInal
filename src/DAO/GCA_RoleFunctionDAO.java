package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_RoleFunction;
import systemInterfaces.GCA_RoleFunctionDAOI;

public class GCA_RoleFunctionDAO implements GCA_RoleFunctionDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_RoleFunction get(Connection conn, int id) throws GCAException {
		GCA_RoleFunction roleFunction = new GCA_RoleFunction();
		String sql = SQL.GET_GCA_ROLEFUNCTIONS_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				//rolefunctionid,roleid,functionid,accessrights
				roleFunction.setRoleFunctionId(rs.getInt("roleFunctionid"));
				roleFunction.setRoleId(rs.getInt("roleid"));
				roleFunction.setFunctionId(rs.getInt("Functionid"));
				roleFunction.setAccessRights(rs.getString("accessrights"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return roleFunction;
	};

//	public Map<Integer, GCA_RoleFunction> getListAll(Connection conn) throws GCAException {
////	};

	public Map<Integer, GCA_RoleFunction> getListByClubId(Connection conn, int clubId) throws GCAException {
		Map<Integer, GCA_RoleFunction> map = new TreeMap<Integer, GCA_RoleFunction>();
		String sql = SQL.GET_GCA_ROLEFUNCTIONS_BY_CLUBID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, clubId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//rolefunctionid,roleid,functionid,accessrights
				GCA_RoleFunction roleFunction = new GCA_RoleFunction();
				roleFunction.setRoleFunctionId(rs.getInt("roleFunctionid"));
				roleFunction.setRoleId(rs.getInt("roleid"));
				roleFunction.setFunctionId(rs.getInt("Functionid"));
				roleFunction.setAccessRights(rs.getString("accessrights"));
				map.put(roleFunction.getRoleFunctionId(), roleFunction);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_RoleFunction roleFunction) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_GCA_ROLEFUNCTIONS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, roleFunction.getRoleFunctionId());
			ps.setInt(2, roleFunction.getRoleId());
			ps.setInt(3, roleFunction.getFunctionId());
			ps.setString(4, roleFunction.getAccessRights());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_RoleFunction roleFunction) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_GCA_ROLEFUNCTIONS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, roleFunction.getRoleFunctionId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_RoleFunction roleFunction) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_GCA_ROLEFUNCTIONS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, roleFunction.getRoleId());
			ps.setInt(2, roleFunction.getFunctionId());
			ps.setString(3, roleFunction.getAccessRights());
			ps.setInt(4, roleFunction.getRoleFunctionId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
