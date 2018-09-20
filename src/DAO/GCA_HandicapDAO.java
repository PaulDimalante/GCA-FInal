package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import commonFunctions.CommonFunctions;

import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Handicap;
import systemInterfaces.GCA_HandicapDAOI;

public class GCA_HandicapDAO implements GCA_HandicapDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_Handicap get(Connection conn, int id) throws GCAException {
		GCA_Handicap handicap = new GCA_Handicap();
		String sql = SQL.GET_GCA_HANDICAPS_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				//handicapid,memberid,asofdate,handicapindex
				handicap.setHandicapId(rs.getInt("handicapid"));
				handicap.setMemberId(rs.getInt("memberid"));
				handicap.setAsOfDate(rs.getDate("asofdate"));
				handicap.setHandicapIndex(rs.getDouble("handicapindex"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return handicap;
	};

	public Map<Integer, GCA_Handicap> getListByMemberId(Connection conn, int memberId) throws GCAException {
		Map<Integer, GCA_Handicap> map = new TreeMap<Integer, GCA_Handicap>();
		String sql = SQL.GET_GCA_HANDICAPS_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memberId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//handicapid,memberid,asofdate,handicapindex
				GCA_Handicap handicap = new GCA_Handicap();
				handicap.setHandicapId(rs.getInt("handicapid"));
				handicap.setMemberId(rs.getInt("memberid"));
				handicap.setAsOfDate(rs.getDate("asofdate"));
				handicap.setHandicapIndex(rs.getDouble("handicapindex"));
				map.put(handicap.getHandicapId(), handicap);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public Map<Integer, GCA_Handicap> getListByClubId(Connection conn, int clubId) throws GCAException {
		Map<Integer, GCA_Handicap> map = new TreeMap<Integer, GCA_Handicap>();
		String sql = SQL.GET_GCA_HANDICAPS_BY_CLUBID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, clubId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//handicapid,memberid,asofdate,handicapindex
				GCA_Handicap handicap = new GCA_Handicap();
				handicap.setHandicapId(rs.getInt("handicapid"));
				handicap.setMemberId(rs.getInt("memberid"));
				handicap.setAsOfDate(rs.getDate("asofdate"));
				handicap.setHandicapIndex(rs.getDouble("handicapindex"));
				map.put(handicap.getHandicapId(), handicap);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_Handicap handicap) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_GCA_HANDICAPS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, handicap.getHandicapId());
			ps.setInt(2, handicap.getMemberId());
			ps.setDate(3, CommonFunctions.utilDateToSqlDate(handicap.getAsOfDate()));
			ps.setDouble(4, handicap.getHandicapIndex());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_Handicap handicap) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_GCA_HANDICAPS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, handicap.getHandicapId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean deleteByMemberId(Connection conn, int memberId) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_GCA_HANDICAPS_BY_MEMBERID.getSQL();
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

	public boolean update(Connection conn, GCA_Handicap handicap) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_GCA_HANDICAPS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, handicap.getMemberId());
			ps.setDate(2, CommonFunctions.utilDateToSqlDate(handicap.getAsOfDate()));
			ps.setDouble(3, handicap.getHandicapIndex());
			ps.setInt(4, handicap.getHandicapId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
