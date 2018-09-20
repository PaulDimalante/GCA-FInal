package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import commonFunctions.CommonFunctions;

import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Score;
import systemInterfaces.GCA_ScoreDAOI;

public class GCA_ScoreDAO implements GCA_ScoreDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_Score get(Connection conn, int id) throws GCAException {
		GCA_Score score = new GCA_Score();
		String sql = SQL.GET_GCA_SCORES_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				//scoreid,memberid,tournamentid,dateofround,courseid,courseteeid,groupname,team,roundNo,tournamentflag
				score.setScoreId(rs.getInt("scoreid"));
				score.setMemberId(rs.getInt("memberid"));
				score.setTournamentId(rs.getInt("tournamentid"));
				score.setDateOfRound(rs.getDate("dateofround"));
				score.setCourseId(rs.getInt("courseid"));
				score.setCourseTeeId(rs.getInt("courseteeid"));
				score.setGroupName(rs.getString("groupname"));
				score.setTeam(rs.getString("team"));
				score.setRoundNo(rs.getInt("roundNo"));
				score.setTournamentFlag(rs.getString("tournamentflag"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return score;
	};

	public Map<Integer, GCA_Score> getListByTournamentId(Connection conn, int tournamentId) throws GCAException {
		Map<Integer, GCA_Score> map = new TreeMap<Integer, GCA_Score>();
		String sql = SQL.GET_GCA_SCORES_BY_TOURNAMENTID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tournamentId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//scoreid,memberid,tournamentid,dateofround,courseid,courseteeid,groupname,team,roundNo,tournamentflag
				GCA_Score score = new GCA_Score();
				score.setScoreId(rs.getInt("scoreid"));
				score.setMemberId(rs.getInt("memberid"));
				score.setTournamentId(rs.getInt("tournamentid"));
				score.setDateOfRound(rs.getDate("dateofround"));
				score.setCourseId(rs.getInt("courseid"));
				score.setCourseTeeId(rs.getInt("courseteeid"));
				score.setGroupName(rs.getString("groupname"));
				score.setTeam(rs.getString("team"));
				score.setRoundNo(rs.getInt("roundNo"));
				score.setTournamentFlag(rs.getString("tournamentflag"));
				map.put(score.getScoreId(), score);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public Map<Integer, GCA_Score> getListByMemberId(Connection conn, int memberId) throws GCAException {
		Map<Integer, GCA_Score> map = new TreeMap<Integer, GCA_Score>();
		String sql = SQL.GET_GCA_SCORES_BY_MEMBERID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memberId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//scoreid,memberid,tournamentid,dateofround,courseid,courseteeid,groupname,team,roundNo,tournamentflag
				GCA_Score score = new GCA_Score();
				score.setScoreId(rs.getInt("scoreid"));
				score.setMemberId(rs.getInt("memberid"));
				score.setTournamentId(rs.getInt("tournamentid"));
				score.setDateOfRound(rs.getDate("dateofround"));
				score.setCourseId(rs.getInt("courseid"));
				score.setCourseTeeId(rs.getInt("courseteeid"));
				score.setGroupName(rs.getString("groupname"));
				score.setTeam(rs.getString("team"));
				score.setRoundNo(rs.getInt("roundNo"));
				score.setTournamentFlag(rs.getString("tournamentflag"));
				map.put(score.getScoreId(), score);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_Score score) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_GCA_SCORES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, score.getScoreId());
			ps.setInt(2, score.getMemberId());
			ps.setInt(3, score.getTournamentId());
			ps.setDate(4, CommonFunctions.utilDateToSqlDate(score.getDateOfRound()));
			ps.setInt(5, score.getCourseId());
			ps.setInt(6, score.getCourseTeeId());
			ps.setString(7, score.getGroupName());
			ps.setString(8, score.getTeam());
			ps.setInt(9, score.getRoundNo());
			ps.setString(10, score.getTournamentFlag());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_Score score) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_GCA_SCORES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, score.getScoreId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean deleteByMemberId(Connection conn, int memberId) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_GCA_SCORES_BY_MEMBERID.getSQL();
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

	public boolean update(Connection conn, GCA_Score score) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_GCA_SCORES.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, score.getMemberId());
			ps.setInt(2, score.getTournamentId());
			ps.setDate(3, CommonFunctions.utilDateToSqlDate(score.getDateOfRound()));
			ps.setInt(4, score.getCourseId());
			ps.setInt(5, score.getCourseTeeId());
			ps.setString(6, score.getGroupName());
			ps.setString(7, score.getTeam());
			ps.setInt(8, score.getRoundNo());
			ps.setString(9, score.getTournamentFlag());
			ps.setInt(10, score.getScoreId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
