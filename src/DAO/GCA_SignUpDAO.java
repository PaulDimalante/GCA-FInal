package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import commonFunctions.CommonFunctions;

import java.util.Date;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_SignUp;
import systemInterfaces.GCA_SignUpDAOI;

public class GCA_SignUpDAO implements GCA_SignUpDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;
	};

	public GCA_SignUp get(Connection conn, int id) throws GCAException {
		GCA_SignUp signUp = new GCA_SignUp();
		String sql = SQL.GET_GCA_SIGNUPS_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				// signupid,clubid,memberid,signupdate,tournamentid
				signUp.setSignUpId(rs.getInt("signUpId"));
				signUp.setClubId(rs.getInt("clubId"));
				signUp.setMemberId(rs.getInt("memberId"));
				signUp.setSignUpDate(rs.getDate("signUpDate"));
				signUp.setTournamentId(rs.getInt("tournamentId"));
				signUp.setSignedUpFlag(rs.getString("signedUpFlag"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return signUp;
	};

//	public Map<Integer, GCA_SignUp> getListAll(Connection conn) throws GCAException {
//
//	};

	public Map<Integer, Map<Integer,GCA_SignUp>> getListByTournamentId(Connection conn, int tournamentId) throws GCAException {
		Map<Integer, Map<Integer,GCA_SignUp>> mapT = new TreeMap<Integer, Map<Integer,GCA_SignUp>>();
		String sql = SQL.GET_GCA_SIGNUPS_BY_TOURNAMENTID.getSQL();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tournamentId);
			rs = ps.executeQuery();
			boolean hasNext = rs.next();
			while (hasNext) {
				// signupid,clubid,memberid,signupdate,tournamentid
				Map<Integer,GCA_SignUp> mapS = new TreeMap<Integer,GCA_SignUp>();
				while (hasNext && tournamentId == rs.getInt("tournamentid")) {
					GCA_SignUp signUp = new GCA_SignUp();
					signUp.setSignUpId(rs.getInt("signUpid"));
					signUp.setClubId(rs.getInt("clubid"));
					signUp.setMemberId(rs.getInt("memberid"));
					signUp.setSignUpDate(rs.getDate("signUpDate"));
					signUp.setTournamentId(rs.getInt("tournamentid"));
					signUp.setSignedUpFlag(rs.getString("signedUpFlag"));

					mapS.put(signUp.getSignUpId(), signUp);
					hasNext = rs.next();
				}
				mapT.put(tournamentId, mapS);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return mapT;
	};

	@Override
	public Map<Integer, Map<Integer,GCA_SignUp>> getListByClubIdDateRange(Connection conn, int clubId, Date fromDate, Date thruDate)
			throws GCAException {
		Map<Integer, Map<Integer,GCA_SignUp>> mapT = new TreeMap<Integer, Map<Integer,GCA_SignUp>>();
		String sql = SQL.GET_GCA_SIGNUPS_BY_CLUBID_DATE_RANGE.getSQL();
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
			boolean hasNext = rs.next();
			while (hasNext) {
				// signupid,clubid,memberid,signupdate,tournamentid
				Map<Integer,GCA_SignUp> mapS = new TreeMap<Integer,GCA_SignUp>();
				int tournamentIdOld = rs.getInt("tournamentid");
				
				while (hasNext && tournamentIdOld == rs.getInt("tournamentid")) {
					GCA_SignUp signUp = new GCA_SignUp();
					signUp.setSignUpId(rs.getInt("signUpid"));
					signUp.setClubId(rs.getInt("clubid"));
					signUp.setMemberId(rs.getInt("memberid"));
					signUp.setSignUpDate(rs.getDate("signUpDate"));
					signUp.setTournamentId(rs.getInt("tournamentid"));
					signUp.setSignedUpFlag(rs.getString("signedUpFlag"));
					mapS.put(signUp.getSignUpId(), signUp);
					hasNext = rs.next();
				}
				
				mapT.put(tournamentIdOld, mapS);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return mapT;
	}

	@Override
	public Map<Integer, Map<Integer, GCA_SignUp>> getListByMemberIdDateRange(Connection conn, int memberId, Date fromDate, Date thruDate)
			throws GCAException {
		Map<Integer, Map<Integer, GCA_SignUp>> mapT = new TreeMap<Integer, Map<Integer, GCA_SignUp>>();
		String sql = SQL.GET_GCA_SIGNUPS_BY_MEMBERID_DATE_RANGE.getSQL();
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
			ps.setInt(1, memberId);
			ps.setDate(2, CommonFunctions.utilDateToSqlDate(thruDateUse));
			ps.setDate(3, CommonFunctions.utilDateToSqlDate(fromDate));
			rs = ps.executeQuery();
			boolean hasNext = rs.next();
			while (hasNext) {
				// signupid,clubid,memberid,signupdate,tournamentid
				Map<Integer, GCA_SignUp> mapS = new TreeMap<Integer, GCA_SignUp>();
				int tournamentIdOld = rs.getInt("tournamentid");
				while (hasNext && tournamentIdOld == rs.getInt("tournamentid")) {
					GCA_SignUp signUp = new GCA_SignUp();
					signUp.setSignUpId(rs.getInt("signUpid"));
					signUp.setClubId(rs.getInt("clubid"));
					signUp.setMemberId(rs.getInt("memberid"));
					signUp.setSignUpDate(rs.getDate("signUpDate"));
					signUp.setTournamentId(rs.getInt("tournamentid"));
					signUp.setSignedUpFlag(rs.getString("signedUpFlag"));
					mapS.put(signUp.getTournamentId(), signUp);
					
					hasNext = rs.next();
				}
				
				mapT.put(tournamentIdOld, mapS);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return mapT;
	};

	public boolean insert(Connection conn, GCA_SignUp signUp) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_GCA_SIGNUPS.getSQL();
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, signUp.getSignUpId());
			ps.setInt(2, signUp.getClubId());
			ps.setInt(3, signUp.getMemberId());
			ps.setDate(4, CommonFunctions.utilDateToSqlDate(signUp.getSignUpDate()));
			ps.setInt(5, signUp.getTournamentId());
			ps.setString(6, signUp.getSignedUpFlag());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_SignUp signUp) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_GCA_SIGNUPS.getSQL();
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, signUp.getSignUpId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean deleteByMemberId(Connection conn, int memberId) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_GCA_SIGNUPS_BY_MEMBERID.getSQL();
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

	public boolean update(Connection conn, GCA_SignUp signUp) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_GCA_SIGNUPS.getSQL();
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, signUp.getClubId());
			ps.setInt(2, signUp.getMemberId());
			ps.setDate(3, CommonFunctions.utilDateToSqlDate(signUp.getSignUpDate()));
			ps.setInt(4, signUp.getTournamentId());
			ps.setString(5, signUp.getSignedUpFlag());
			ps.setInt(6, signUp.getSignUpId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	}

}
