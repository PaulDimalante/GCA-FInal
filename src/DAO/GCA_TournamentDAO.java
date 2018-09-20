package DAO;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import commonFunctions.CommonFunctions;

import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Tournament;
import systemInterfaces.GCA_TournamentDAOI;

public class GCA_TournamentDAO implements GCA_TournamentDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_Tournament get(Connection conn, int id) throws GCAException {
		GCA_Tournament tournament = new GCA_Tournament();
		String sql = SQL.GET_GCA_TOURNAMENTS_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				//tournamentid,
				// clubid,
				// tournamentname,
				// alwaysflag,
				// tournamentstartdate,
				// tournamentenddate,
				// courseid,
				// strokematchrule,
				// handicaprule,
				// handicapreductionpct,
				// teamsize,
				// groupflag,
				// pointsflag,
				// pointsfrontnine,
				// pointsbacknine,
				// pointsoverall,
				// inflightflag,
				// qualifierflag,
				// qualifyingtournamentid 
				tournament.setTournamentId(rs.getInt("tournamentid"));
				tournament.setClubId(rs.getInt("clubid"));
				tournament.setTournamentName(rs.getString("tournamentname"));
				tournament.setAlwaysFlag(rs.getString("alwaysflag"));
				tournament.setTournamentStartDate(rs.getDate("tournamentstartdate"));
				tournament.setTournamentEndDate(rs.getDate("tournamentenddate"));
				tournament.setCourseId(rs.getInt("courseid"));
				tournament.setStrokeMatchRule(rs.getString("strokematchrule"));
				tournament.setHandicapRule(rs.getString("handicaprule"));
				tournament.setHandicapReductionPct(rs.getInt("handicapreductionpct"));
				tournament.setTeamSize(rs.getInt("teamsize"));
				tournament.setGroupFlag(rs.getString("groupflag"));
				tournament.setPointsFlag(rs.getString("pointsflag"));
				tournament.setPointsFrontNine(rs.getInt("pointsfrontnine"));
				tournament.setPointsBackNine(rs.getInt("pointsbacknine"));
				tournament.setPointsOverAll(rs.getInt("pointsoverall"));
				tournament.setInFlightFlag(rs.getString("inflightflag"));
				tournament.setQualifierFlag(rs.getString("qualifierflag"));
				tournament.setQualifyingTournamentId(rs.getInt("qualifyingtournamentid"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return tournament;
	};

//	public Map<Integer, GCA_Tournament> getListAll(Connection conn) throws GCAException {
////	};

	public Map<Date, GCA_Tournament> getListByClubId(Connection conn, int clubId) throws GCAException {
		Map<Date, GCA_Tournament> map = new TreeMap<Date, GCA_Tournament>();
		String sql = SQL.GET_GCA_TOURNAMENTS_BY_CLUBID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, clubId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//tournamentid,
				// clubid,
				// tournamentname,
				// alwaysflag,
				// tournamentstartdate,
				// tournamentenddate,
				// courseid,
				// strokematchrule,
				// handicaprule,
				// handicapreductionpct,
				// teamsize,
				// groupflag,
				// pointsflag,
				// pointsfrontnine,
				// pointsbacknine,
				// pointsoverall,
				// inflightflag,
				// qualifierflag,
				// qualifyingtournamentid 
				GCA_Tournament tournament = new GCA_Tournament();
				tournament.setTournamentId(rs.getInt("tournamentid"));
				tournament.setClubId(rs.getInt("clubid"));
				tournament.setTournamentName(rs.getString("tournamentname"));
				tournament.setAlwaysFlag(rs.getString("alwaysflag"));
				tournament.setTournamentStartDate(rs.getDate("tournamentstartdate"));
				tournament.setTournamentEndDate(rs.getDate("tournamentenddate"));
				tournament.setCourseId(rs.getInt("courseid"));
				tournament.setStrokeMatchRule(rs.getString("strokematchrule"));
				tournament.setHandicapRule(rs.getString("handicaprule"));
				tournament.setHandicapReductionPct(rs.getInt("handicapreductionpct"));
				tournament.setTeamSize(rs.getInt("teamsize"));
				tournament.setGroupFlag(rs.getString("groupflag"));
				tournament.setPointsFlag(rs.getString("pointsflag"));
				tournament.setPointsFrontNine(rs.getInt("pointsfrontnine"));
				tournament.setPointsBackNine(rs.getInt("pointsbacknine"));
				tournament.setPointsOverAll(rs.getInt("pointsoverall"));
				tournament.setInFlightFlag(rs.getString("inflightflag"));
				tournament.setQualifierFlag(rs.getString("qualifierflag"));
				tournament.setQualifyingTournamentId(rs.getInt("qualifyingtournamentid"));
				
				map.put(tournament.getTournamentStartDate(), tournament);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public Map<Date, GCA_Tournament> getListByClubIdDateRange(Connection conn, int clubId, Date fromDate, Date thruDate) throws GCAException {
		Map<Date, GCA_Tournament> map = new TreeMap<Date, GCA_Tournament>();
		String sql = SQL.GET_GCA_TOURNAMENTS_BY_CLUBID_DATE_RANGE.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		Date thruDateUse;
		
		if (thruDate == null) {
			thruDateUse = CommonFunctions.parseDate("9999-12-31");
		} else {
			thruDateUse = thruDate;
		}
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, clubId);
			ps.setDate(2, CommonFunctions.utilDateToSqlDate(thruDateUse));
			ps.setDate(3, CommonFunctions.utilDateToSqlDate(fromDate));
			rs = ps.executeQuery();
			while (rs.next()) {
				//tournamentid,
				// clubid,
				// tournamentname,
				// alwaysflag,
				// tournamentstartdate,
				// tournamentenddate,
				// courseid,
				// strokematchrule,
				// handicaprule,
				// handicapreductionpct,
				// teamsize,
				// groupflag,
				// pointsflag,
				// pointsfrontnine,
				// pointsbacknine,
				// pointsoverall,
				// inflightflag,
				// qualifierflag,
				// qualifyingtournamentid 
				GCA_Tournament tournament = new GCA_Tournament();
				tournament.setTournamentId(rs.getInt("tournamentid"));
				tournament.setClubId(rs.getInt("clubid"));
				tournament.setTournamentName(rs.getString("tournamentname"));
				tournament.setAlwaysFlag(rs.getString("alwaysflag"));
				tournament.setTournamentStartDate(rs.getDate("tournamentstartdate"));
				tournament.setTournamentEndDate(rs.getDate("tournamentenddate"));
				tournament.setCourseId(rs.getInt("courseid"));
				tournament.setStrokeMatchRule(rs.getString("strokematchrule"));
				tournament.setHandicapRule(rs.getString("handicaprule"));
				tournament.setHandicapReductionPct(rs.getInt("handicapreductionpct"));
				tournament.setTeamSize(rs.getInt("teamsize"));
				tournament.setGroupFlag(rs.getString("groupflag"));
				tournament.setPointsFlag(rs.getString("pointsflag"));
				tournament.setPointsFrontNine(rs.getInt("pointsfrontnine"));
				tournament.setPointsBackNine(rs.getInt("pointsbacknine"));
				tournament.setPointsOverAll(rs.getInt("pointsoverall"));
				tournament.setInFlightFlag(rs.getString("inflightflag"));
				tournament.setQualifierFlag(rs.getString("qualifierflag"));
				tournament.setQualifyingTournamentId(rs.getInt("qualifyingtournamentid"));
				
				map.put(tournament.getTournamentStartDate(), tournament);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_Tournament tournament) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_GCA_TOURNAMENTS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tournament.getTournamentId());
			ps.setInt(2, tournament.getClubId());
			ps.setString(3, tournament.getTournamentName());
			ps.setString(4, tournament.getAlwaysFlag());
			ps.setDate(5, CommonFunctions.utilDateToSqlDate(tournament.getTournamentStartDate()));
			ps.setDate(6, CommonFunctions.utilDateToSqlDate(tournament.getTournamentEndDate()));
			ps.setInt(7, tournament.getCourseId());
			ps.setString(8, tournament.getStrokeMatchRule());
			ps.setString(9, tournament.getHandicapRule());
			ps.setInt(10, tournament.getHandicapReductionPct());
			ps.setInt(11, tournament.getTeamSize());
			ps.setString(12, tournament.getGroupFlag());
			ps.setString(13, tournament.getPointsFlag());
			ps.setInt(14, tournament.getPointsFrontNine());
			ps.setInt(15, tournament.getPointsBackNine());
			ps.setInt(16, tournament.getPointsOverAll());
			ps.setString(17, tournament.getInFlightFlag());
			ps.setString(18, tournament.getQualifierFlag());
			ps.setInt(19, tournament.getQualifyingTournamentId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_Tournament tournament) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_GCA_TOURNAMENTS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tournament.getTournamentId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_Tournament tournament) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_GCA_TOURNAMENTS.getSQL();
		PreparedStatement ps;
		 
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tournament.getClubId());
			ps.setString(2, tournament.getTournamentName());
			ps.setString(3, tournament.getAlwaysFlag());
			ps.setDate(4, CommonFunctions.utilDateToSqlDate(tournament.getTournamentStartDate()));
			ps.setDate(5, CommonFunctions.utilDateToSqlDate(tournament.getTournamentEndDate()));
			ps.setInt(6, tournament.getCourseId());
			ps.setString(7, tournament.getStrokeMatchRule());
			ps.setString(8, tournament.getHandicapRule());
			ps.setInt(9, tournament.getHandicapReductionPct());
			ps.setInt(10, tournament.getTeamSize());
			ps.setString(11, tournament.getGroupFlag());
			ps.setString(12, tournament.getPointsFlag());
			ps.setInt(13, tournament.getPointsFrontNine());
			ps.setInt(14, tournament.getPointsBackNine());
			ps.setInt(15, tournament.getPointsOverAll());
			ps.setString(16, tournament.getInFlightFlag());
			ps.setString(17, tournament.getQualifierFlag());
			ps.setInt(18, tournament.getQualifyingTournamentId());
			ps.setInt(19, tournament.getTournamentId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	}

}
