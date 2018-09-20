package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_TournamentResult;
import systemInterfaces.GCA_TournamentResultDAOI;

public class GCA_TournamentResultDAO implements GCA_TournamentResultDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_TournamentResult get(Connection conn, int id) throws GCAException {
		GCA_TournamentResult tournamentResult = new GCA_TournamentResult();
		String sql = SQL.GET_GCA_TOURNAMENTRESULTS_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				//tournamentresultid,scoreid,frontnine,backnine,overall
				tournamentResult.setTournamentResultId(rs.getInt("tournamentResultid"));
				tournamentResult.setScoreId(rs.getInt("scoreid"));
				tournamentResult.setFrontNine(rs.getInt("frontnine"));
				tournamentResult.setBackNine(rs.getInt("backnine"));
				tournamentResult.setOverAll(rs.getInt("overall"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return tournamentResult;
	};

//	public Map<Integer, GCA_TournamentResult> getListAll(Connection conn) throws GCAException {
////	};

	public Map<Integer, GCA_TournamentResult> getListByTournamentId(Connection conn, int tournamentId) throws GCAException {
		Map<Integer, GCA_TournamentResult> map = new TreeMap<Integer, GCA_TournamentResult>();
		String sql = SQL.GET_GCA_TOURNAMENTRESULTS_BY_TOURNAMENTID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tournamentId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//tournamentresultid,scoreid,frontnine,backnine,overall
				GCA_TournamentResult tournamentResult = new GCA_TournamentResult();
				tournamentResult.setTournamentResultId(rs.getInt("tournamentResultid"));
				tournamentResult.setScoreId(rs.getInt("scoreid"));
				tournamentResult.setFrontNine(rs.getInt("frontnine"));
				tournamentResult.setBackNine(rs.getInt("backnine"));
				tournamentResult.setOverAll(rs.getInt("overall"));
				map.put(tournamentResult.getTournamentResultId(), tournamentResult);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_TournamentResult tournamentResult) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_GCA_TOURNAMENTRESULTS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tournamentResult.getTournamentResultId());
			ps.setInt(2, tournamentResult.getScoreId());
			ps.setInt(3, tournamentResult.getFrontNine());
			ps.setInt(4, tournamentResult.getBackNine());
			ps.setInt(5, tournamentResult.getOverAll());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_TournamentResult tournamentResult) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_GCA_TOURNAMENTRESULTS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tournamentResult.getTournamentResultId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_TournamentResult tournamentResult) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_GCA_TOURNAMENTRESULTS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tournamentResult.getScoreId());
			ps.setInt(2, tournamentResult.getFrontNine());
			ps.setInt(3, tournamentResult.getBackNine());
			ps.setInt(4, tournamentResult.getOverAll());
			ps.setInt(5, tournamentResult.getTournamentResultId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	}

}
