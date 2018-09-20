package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Role;
import systemInterfaces.GCA_RoleDAOI;

public class GCA_RoleDAO implements GCA_RoleDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_Role get(Connection conn, int id) throws GCAException {
		GCA_Role role = new GCA_Role();
		String sql = SQL.GET_GCA_ROLES_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				//roleid,clubid,rolename
				role.setRoleId(rs.getInt("roleid"));
				role.setClubId(rs.getInt("clubid"));
				role.setRoleName(rs.getString("rolename"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return role;
	};

	public GCA_Role getByClubIdRoleName(Connection conn, int clubId, String roleName) throws GCAException {
		GCA_Role role = new GCA_Role();
		String sql = SQL.GET_GCA_ROLES_BY_CLUBID_ROLENAME.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, clubId);
			ps.setString(2, roleName);
			rs = ps.executeQuery();
			if (rs.next()) {
				//roleid,clubid,rolename
				role.setRoleId(rs.getInt("roleid"));
				role.setClubId(rs.getInt("clubid"));
				role.setRoleName(rs.getString("rolename"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return role;
	};

	public Map<Integer, GCA_Role> getListByClubId(Connection conn, int clubId) throws GCAException {
		Map<Integer, GCA_Role> map = new TreeMap<Integer, GCA_Role>();
		String sql = SQL.GET_GCA_ROLES_BY_CLUBID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, clubId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//roleid,clubid,rolename
				GCA_Role role = new GCA_Role();
				role.setRoleId(rs.getInt("roleid"));
				role.setClubId(rs.getInt("clubid"));
				role.setRoleName(rs.getString("rolename"));
				map.put(role.getRoleId(), role);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_Role role) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_GCA_ROLES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, role.getRoleId());
			ps.setInt(2, role.getClubId());
			ps.setString(3, role.getRoleName());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_Role role) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_GCA_ROLES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, role.getRoleId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_Role role) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_GCA_ROLES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, role.getClubId());
			ps.setString(2, role.getRoleName());
			ps.setInt(3, role.getRoleId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
