package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import commonFunctions.CommonFunctions;

import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Club;
import systemInterfaces.GCA_ClubDAOI;

public class GCA_ClubDAO implements GCA_ClubDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		boolean flag = false;
		return flag;	};

	public GCA_Club get(Connection conn, int id) throws GCAException {
		GCA_Club club = new GCA_Club();
		String sql = SQL.GET_CLUB_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				club.setClubId(rs.getInt("clubid"));
				club.setClubName(rs.getString("clubname"));
				club.setClubUsgaId(rs.getString("clubusgaid"));
				club.setClubHomeCourseName(rs.getString("clubhomecoursename"));
				club.setClubCity(rs.getString("clubcity"));
				club.setClubState(rs.getString("clubstate"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return club;	};

	public Map<String, GCA_Club> getListAll(Connection conn) throws GCAException {
		Map<String, GCA_Club> map = new TreeMap<String, GCA_Club>();
		String sql = SQL.GET_CLUB_ALL.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				GCA_Club club = new GCA_Club();
				club.setClubId(rs.getInt("clubid"));
				club.setClubName(rs.getString("clubname"));
				club.setClubUsgaId(rs.getString("clubusgaid"));
				club.setClubHomeCourseName(rs.getString("clubhomecoursename"));
				club.setClubCity(rs.getString("clubcity"));
				club.setClubState(rs.getString("clubstate"));
				map.put(club.getClubName(), club);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public Map<String, GCA_Club> getListByMemberId(Connection conn, int memberId) throws GCAException {
		Map<String, GCA_Club> map = new TreeMap<String, GCA_Club>();
		String sql = SQL.GET_CLUB_BY_MEMBER_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memberId);
			rs = ps.executeQuery();
			while (rs.next()) {
				GCA_Club club = new GCA_Club();
				club.setClubId(rs.getInt("clubid"));
				club.setClubName(rs.getString("clubname"));
				club.setClubUsgaId(rs.getString("clubusgaid"));
				club.setClubHomeCourseName(rs.getString("clubhomecoursename"));
				club.setClubCity(rs.getString("clubcity"));
				club.setClubState(rs.getString("clubstate"));
				map.put(club.getClubName(), club);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public Map<String, GCA_Club> getListByMemberLoginIdPassWord(Connection conn, String memberLoginId, String memberPassWord) throws GCAException {
		Map<String, GCA_Club> map = new TreeMap<String, GCA_Club>();
//		String sql = SQL.GET_CLUB_BY_LOGINID_PASSWORD.getSQL();
		String sql = SQL.GET_CLUB_BY_LOGINID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, memberLoginId);
			//ps.setString(2, memberPassWord);
			rs = ps.executeQuery();
			while (rs.next()) {
				String encryptPW = rs.getString("memberpassword");
				if (CommonFunctions.chk2Encrypt(memberPassWord, encryptPW)) {
					GCA_Club club = new GCA_Club();
					club.setClubId(rs.getInt("clubid"));
					club.setClubName(rs.getString("clubname"));
					club.setClubUsgaId(rs.getString("clubusgaid"));
					club.setClubHomeCourseName(rs.getString("clubhomecoursename"));
					club.setClubCity(rs.getString("clubcity"));
					club.setClubState(rs.getString("clubstate"));
					map.put(club.getClubName(), club);
				}
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_Club club) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_CLUB.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, club.getClubId());
			ps.setString(2, club.getClubName());
			ps.setString(3, club.getClubUsgaId());
			ps.setString(4, club.getClubHomeCourseName());
			ps.setString(5, club.getClubCity());
			ps.setString(6, club.getClubState());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_Club club) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_CLUB.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, club.getClubId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_Club club) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_CLUB.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, club.getClubName());
			ps.setString(2, club.getClubUsgaId());
			ps.setString(3, club.getClubHomeCourseName());
			ps.setString(4, club.getClubCity());
			ps.setString(5, club.getClubState());
			ps.setInt(6, club.getClubId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
