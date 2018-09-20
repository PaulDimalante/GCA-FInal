package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_MemberRole;
import systemInterfaces.GCA_MemberRoleDAOI;

public class GCA_MemberRoleDAO implements GCA_MemberRoleDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_MemberRole get(Connection conn, int id) throws GCAException {
		GCA_MemberRole memberRole = new GCA_MemberRole();
		String sql = SQL.GET_MEMBERROLES_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				//memberroleid,memberid,roleid
				memberRole.setMemberRoleId(rs.getInt("memberRoleid"));
				memberRole.setMemberId(rs.getInt("memberid"));
				memberRole.setRoleId(rs.getInt("roleid"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return memberRole;
	};

	public Map<Integer, GCA_MemberRole> getListByClubId(Connection conn, int clubId) throws GCAException {
		Map<Integer, GCA_MemberRole> map = new TreeMap<Integer, GCA_MemberRole>();
		String sql = SQL.GET_MEMBERROLES_BY_CLUBID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, clubId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//memberroleid,memberid,roleid
				GCA_MemberRole memberRole = new GCA_MemberRole();
				memberRole.setMemberRoleId(rs.getInt("memberRoleid"));
				memberRole.setMemberId(rs.getInt("memberid"));
				memberRole.setRoleId(rs.getInt("roleid"));
				map.put(memberRole.getMemberRoleId(), memberRole);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public Map<Integer, GCA_MemberRole> getListByMemberId(Connection conn, int memberId) throws GCAException {
		Map<Integer, GCA_MemberRole> map = new TreeMap<Integer, GCA_MemberRole>();
		String sql = SQL.GET_MEMBERROLES_BY_MEMBERID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memberId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//memberroleid,memberid,roleid
				GCA_MemberRole memberRole = new GCA_MemberRole();
				memberRole.setMemberRoleId(rs.getInt("memberRoleid"));
				memberRole.setMemberId(rs.getInt("memberid"));
				memberRole.setRoleId(rs.getInt("roleid"));
				map.put(memberRole.getMemberRoleId(), memberRole);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_MemberRole memberRole) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_MEMBERROLES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memberRole.getMemberRoleId());
			ps.setInt(2, memberRole.getMemberId());
			ps.setInt(3, memberRole.getRoleId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_MemberRole memberRole) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_MEMBERROLES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memberRole.getMemberRoleId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean deleteByMemberId(Connection conn, int memberId) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_MEMBERROLES_BY_MEMBERID.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memberId);
			ps.executeUpdate();
			success = true;
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_MemberRole memberRole) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_MEMBERROLES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memberRole.getMemberId());
			ps.setInt(2, memberRole.getRoleId());
			ps.setInt(3, memberRole.getMemberRoleId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
