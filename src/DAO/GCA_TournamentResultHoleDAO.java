package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_TournamentResultHole;
import systemInterfaces.GCA_TournamentResultHoleDAOI;

public class GCA_TournamentResultHoleDAO implements GCA_TournamentResultHoleDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_TournamentResultHole get(Connection conn, int id) throws GCAException {
		GCA_TournamentResultHole tournamentResultHole = new GCA_TournamentResultHole();
		String sql = SQL.GET_GCA_TOURNAMENTRESULTHOLES_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				//tournamentresultholeid,tournamentresultid,scoreholeid,netscore
				tournamentResultHole.setTournamentResultHoleId(rs.getInt("tournamentResultHoleid"));
				tournamentResultHole.setTournamentResultId(rs.getInt("tournamentResultid"));
				tournamentResultHole.setScoreHoleId(rs.getInt("scoreHoleid"));
				tournamentResultHole.setNetScore(rs.getInt("netscore"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return tournamentResultHole;
	};

	public Map<Integer, GCA_TournamentResultHole> getListByTournamentResultId(Connection conn, int tournamentResultId) throws GCAException {
		Map<Integer, GCA_TournamentResultHole> map = new TreeMap<Integer, GCA_TournamentResultHole>();
		String sql = SQL.GET_GCA_TOURNAMENTRESULTHOLES_BY_TOURNAMENTRESULTID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tournamentResultId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//tournamentresultholeid,tournamentresultid,scoreholeid,netscore
				GCA_TournamentResultHole tournamentResultHole = new GCA_TournamentResultHole();
				tournamentResultHole.setTournamentResultHoleId(rs.getInt("tournamentResultHoleid"));
				tournamentResultHole.setTournamentResultId(rs.getInt("tournamentResultid"));
				tournamentResultHole.setScoreHoleId(rs.getInt("scoreHoleid"));
				tournamentResultHole.setNetScore(rs.getInt("netscore"));
				map.put(tournamentResultHole.getTournamentResultHoleId(), tournamentResultHole);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public Map<Integer, GCA_TournamentResultHole> getListByScoreId(Connection conn, int scoreId) throws GCAException {
		Map<Integer, GCA_TournamentResultHole> map = new TreeMap<Integer, GCA_TournamentResultHole>();
		String sql = SQL.GET_GCA_TOURNAMENTRESULTHOLES_BY_SCOREID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, scoreId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//tournamentresultholeid,tournamentresultid,scoreholeid,netscore
				GCA_TournamentResultHole tournamentResultHole = new GCA_TournamentResultHole();
				tournamentResultHole.setTournamentResultHoleId(rs.getInt("tournamentResultHoleid"));
				tournamentResultHole.setTournamentResultId(rs.getInt("tournamentResultid"));
				tournamentResultHole.setScoreHoleId(rs.getInt("scoreHoleid"));
				tournamentResultHole.setNetScore(rs.getInt("netscore"));
				map.put(tournamentResultHole.getTournamentResultHoleId(), tournamentResultHole);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_TournamentResultHole tournamentResultHole) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_GCA_TOURNAMENTRESULTHOLES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt( 1, tournamentResultHole.getTournamentResultHoleId());
			ps.setInt( 2, tournamentResultHole.getTournamentResultId());
			ps.setInt( 3, tournamentResultHole.getScoreHoleId());
			ps.setInt( 4, tournamentResultHole.getNetScore());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_TournamentResultHole tournamentResultHole) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_GCA_TOURNAMENTRESULTHOLES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt( 1, tournamentResultHole.getTournamentResultHoleId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_TournamentResultHole tournamentResultHole) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_GCA_TOURNAMENTRESULTHOLES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt( 1, tournamentResultHole.getTournamentResultId());
			ps.setInt( 2, tournamentResultHole.getScoreHoleId());
			ps.setInt( 3, tournamentResultHole.getNetScore());
			ps.setInt( 4, tournamentResultHole.getTournamentResultHoleId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
