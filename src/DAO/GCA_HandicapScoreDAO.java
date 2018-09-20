package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_HandicapScore;
import systemInterfaces.GCA_HandicapScoreDAOI;

public class GCA_HandicapScoreDAO implements GCA_HandicapScoreDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_HandicapScore get(Connection conn, int id) throws GCAException {
		GCA_HandicapScore handicapScore = new GCA_HandicapScore();
		String sql = SQL.GET_GCA_HANDICAPSCORES_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				//handicapscoreid,scoreholeid,adjustedscore
				handicapScore.setHandicapScoreId(rs.getInt("handicapScoreid"));
				handicapScore.setScoreHoleId(rs.getInt("scoreholeid"));
				handicapScore.setAdjustedScore(rs.getInt("adjustedscore"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return handicapScore;
	};

	public Map<Integer, GCA_HandicapScore> getListByMemberId(Connection conn, int memberId) throws GCAException {
		Map<Integer, GCA_HandicapScore> map = new TreeMap<Integer, GCA_HandicapScore>();
		String sql = SQL.GET_GCA_HANDICAPSCORES_BY_MEMBERID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memberId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//handicapscoreid,scoreholeid,adjustedscore
				GCA_HandicapScore handicapScore = new GCA_HandicapScore();
				handicapScore.setHandicapScoreId(rs.getInt("handicapScoreid"));
				handicapScore.setScoreHoleId(rs.getInt("scoreholeid"));
				handicapScore.setAdjustedScore(rs.getInt("adjustedscore"));
				map.put(handicapScore.getHandicapScoreId(), handicapScore);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public Map<Integer, GCA_HandicapScore> getListByClubId(Connection conn, int clubId) throws GCAException {
		Map<Integer, GCA_HandicapScore> map = new TreeMap<Integer, GCA_HandicapScore>();
		String sql = SQL.GET_GCA_HANDICAPSCORES_BY_CLUBID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, clubId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//handicapscoreid,scoreholeid,adjustedscore
				GCA_HandicapScore handicapScore = new GCA_HandicapScore();
				handicapScore.setHandicapScoreId(rs.getInt("handicapScoreid"));
				handicapScore.setScoreHoleId(rs.getInt("scoreholeid"));
				handicapScore.setAdjustedScore(rs.getInt("adjustedscore"));
				map.put(handicapScore.getHandicapScoreId(), handicapScore);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_HandicapScore handicapScore) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_GCA_HANDICAPSCORES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, handicapScore.getHandicapScoreId());
			ps.setInt(2, handicapScore.getScoreHoleId());
			ps.setInt(3, handicapScore.getAdjustedScore());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_HandicapScore handicapScore) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_GCA_HANDICAPSCORES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, handicapScore.getHandicapScoreId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_HandicapScore handicapScore) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_GCA_HANDICAPSCORES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, handicapScore.getScoreHoleId());
			ps.setInt(2, handicapScore.getAdjustedScore());
			ps.setInt(3, handicapScore.getHandicapScoreId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
