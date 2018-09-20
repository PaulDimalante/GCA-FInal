package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_ScoreHole;
import systemInterfaces.GCA_ScoreHoleDAOI;

public class GCA_ScoreHoleDAO implements GCA_ScoreHoleDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_ScoreHole get(Connection conn, int id) throws GCAException {
		GCA_ScoreHole scoreHole = new GCA_ScoreHole();
		String sql = SQL.GET_SCOREHOLES_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				//scoreHoleId,scoreid,hole,score,xout
				scoreHole.setScoreHoleId(rs.getInt("scoreHoleid"));
				scoreHole.setScoreId(rs.getInt("scoreid"));
				scoreHole.setHole(rs.getInt("Hole"));
				scoreHole.setScore(rs.getInt("score"));
				scoreHole.setxOut(rs.getString("xout"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return scoreHole;
	};

//	public Map<Integer, GCA_ScoreHole> getListAll(Connection conn) throws GCAException {
////	};

	public Map<Integer, GCA_ScoreHole> getListByScoreId(Connection conn, int scoreId) throws GCAException {
		Map<Integer, GCA_ScoreHole> map = new TreeMap<Integer, GCA_ScoreHole>();
		String sql = SQL.GET_SCOREHOLES_BY_SCOREID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, scoreId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//scoreHoleId,scoreid,hole,score,xout
				GCA_ScoreHole scoreHole = new GCA_ScoreHole();
				scoreHole.setScoreHoleId(rs.getInt("scoreHoleid"));
				scoreHole.setScoreId(rs.getInt("scoreid"));
				scoreHole.setHole(rs.getInt("Hole"));
				scoreHole.setScore(rs.getInt("score"));
				scoreHole.setxOut(rs.getString("xout"));
				map.put(scoreHole.getScoreHoleId(), scoreHole);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_ScoreHole scoreHole) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_SCOREHOLES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, scoreHole.getScoreHoleId());
			ps.setInt(2, scoreHole.getScoreId());
			ps.setInt(3, scoreHole.getHole());
			ps.setInt(4, scoreHole.getScore());
			ps.setString(5, scoreHole.getxOut());
			success = (ps.executeUpdate() ==1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_ScoreHole scoreHole) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_SCOREHOLES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, scoreHole.getScoreHoleId());
			success = (ps.executeUpdate() ==1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean deleteByMemberId(Connection conn, int memberId) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_SCOREHOLES_BY_MEMBERID.getSQL();
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

	public boolean deleteByScoreId(Connection conn, int scoreId) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_SCOREHOLES_BY_SCOREID.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, scoreId);
			success = (ps.executeUpdate() ==1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_ScoreHole scoreHole) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_SCOREHOLES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, scoreHole.getScoreId());
			ps.setInt(2, scoreHole.getHole());
			ps.setInt(3, scoreHole.getScore());
			ps.setString(4, scoreHole.getxOut());
			ps.setInt(5, scoreHole.getScoreHoleId());
			success = (ps.executeUpdate() ==1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
