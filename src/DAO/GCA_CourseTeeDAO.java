package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import commonFunctions.CommonFunctions;

import java.util.Map;
import customExceptions.GCAException;
import models.GCA_CourseTee;
import systemInterfaces.GCA_CourseTeeDAOI;

public class GCA_CourseTeeDAO implements GCA_CourseTeeDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_CourseTee get(Connection conn, int id) throws GCAException {
		GCA_CourseTee courseTee = new GCA_CourseTee();
		String sql = SQL.GET_COURSETEE_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				courseTee.setCourseTeeId(rs.getInt("courseTeeid"));
				courseTee.setCourseId(rs.getInt("courseid"));
				courseTee.setFromDate(rs.getDate("fromdate"));
				courseTee.setThruDate(rs.getDate("thrudate"));
				courseTee.setTee(rs.getString("tee"));
				courseTee.setRating(rs.getDouble("rating"));
				courseTee.setSlope(rs.getInt("slope"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return courseTee;
	};

	public Map<Integer, Map<String, GCA_CourseTee>> getListAll(Connection conn) throws GCAException {
		Map<Integer, Map<String, GCA_CourseTee>> mapC = new TreeMap<Integer, Map<String, GCA_CourseTee>>();
		Map<String, GCA_CourseTee> mapT = new TreeMap<String, GCA_CourseTee>();
		String sql = SQL.GET_COURSETEE_ALL.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			boolean hasNext = rs.next();
			while (hasNext) {
				int courseIdOld = rs.getInt("courseid");
				while (hasNext && courseIdOld == rs.getInt("courseid")) {
					GCA_CourseTee courseTee = new GCA_CourseTee();
					courseTee.setCourseTeeId(rs.getInt("courseTeeid"));
					courseTee.setCourseId(rs.getInt("courseid"));
					courseTee.setFromDate(rs.getDate("fromdate"));
					courseTee.setThruDate(rs.getDate("thrudate"));
					courseTee.setTee(rs.getString("tee"));
					courseTee.setRating(rs.getDouble("rating"));
					courseTee.setSlope(rs.getInt("slope"));
					mapT.put(courseTee.getTee(), courseTee);
					hasNext = rs.next();
				}
				mapC.put(courseIdOld, mapT);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return mapC;
	};

	public Map<Integer, Map<String, GCA_CourseTee>> getListByCourseId(Connection conn, int courseId) throws GCAException {
		Map<Integer, Map<String, GCA_CourseTee>> mapC = new TreeMap<Integer, Map<String, GCA_CourseTee>>();
		Map<String, GCA_CourseTee> mapT = new TreeMap<String, GCA_CourseTee>();
		String sql = SQL.GET_COURSETEE_BY_COURSEID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, courseId);
			rs = ps.executeQuery();
			boolean hasNext = rs.next();
			while (hasNext) {
				while (hasNext && courseId == rs.getInt("courseid")) {
					GCA_CourseTee courseTee = new GCA_CourseTee();
					courseTee.setCourseTeeId(rs.getInt("courseTeeid"));
					courseTee.setCourseId(rs.getInt("courseid"));
					courseTee.setFromDate(rs.getDate("fromdate"));
					courseTee.setThruDate(rs.getDate("thrudate"));
					courseTee.setTee(rs.getString("tee"));
					courseTee.setRating(rs.getDouble("rating"));
					courseTee.setSlope(rs.getInt("slope"));
					mapT.put(courseTee.getTee(), courseTee);
				}
				mapC.put(courseId, mapT);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return mapC;
	};

	public boolean insert(Connection conn, GCA_CourseTee courseTee) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_COURSETEE.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, courseTee.getCourseTeeId());
			ps.setInt(2, courseTee.getCourseId());
			ps.setDate(3, CommonFunctions.utilDateToSqlDate(courseTee.getFromDate()));
			ps.setDate(4, CommonFunctions.utilDateToSqlDate(courseTee.getThruDate()));
			ps.setString(5, courseTee.getTee());
			ps.setDouble(6, courseTee.getRating());
			ps.setInt(7, courseTee.getSlope());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_CourseTee courseTee) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_COURSETEE.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, courseTee.getCourseTeeId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_CourseTee courseTee) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_COURSETEE.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, courseTee.getCourseId());
			ps.setDate(2, CommonFunctions.utilDateToSqlDate(courseTee.getFromDate()));
			ps.setDate(3, CommonFunctions.utilDateToSqlDate(courseTee.getThruDate()));
			ps.setString(4, courseTee.getTee());
			ps.setDouble(5, courseTee.getRating());
			ps.setInt(6, courseTee.getSlope());
			ps.setInt(7, courseTee.getCourseTeeId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
