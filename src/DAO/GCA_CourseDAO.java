package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import customExceptions.GCAException;
import models.GCA_Course;
import systemInterfaces.GCA_CourseDAOI;

public class GCA_CourseDAO implements GCA_CourseDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_Course get(Connection conn, int id) throws GCAException {
		GCA_Course course = new GCA_Course();
		String sql = SQL.GET_COURSE_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				course.setCourseId(rs.getInt("courseid"));
				course.setCourseName(rs.getString("coursename"));
				course.setCourseCity(rs.getString("coursecity"));
				course.setCourseState(rs.getString("coursestate"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return course;
	};

	public Map<Integer,String> getCourseDropDownList(Connection conn) throws GCAException {
		Map<Integer,String> ddList = new TreeMap<Integer,String>();
		Map<Integer,GCA_Course> map = new GCA_CourseDAO().getListAll(conn);
		for (Entry<Integer,GCA_Course> e : map.entrySet()) {
			ddList.put(e.getKey(), e.getValue().getCourseName());
		}
			
		return ddList;
	}

	public Map<Integer, GCA_Course> getListAll(Connection conn) throws GCAException {
		Map<Integer,GCA_Course> map = new TreeMap<Integer,GCA_Course>();
		String sql = SQL.GET_COURSE_ALL.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				GCA_Course course = new GCA_Course();
				course.setCourseId(rs.getInt("courseid"));
				course.setCourseName(rs.getString("coursename"));
				course.setCourseCity(rs.getString("coursecity"));
				course.setCourseState(rs.getString("coursestate"));
				map.put(course.getCourseId(), course);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public Map<String, GCA_Course> getListByName(Connection conn) throws GCAException {
		Map<String,GCA_Course> map = new TreeMap<String,GCA_Course>();
		String sql = SQL.GET_COURSE_BY_NAME.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				GCA_Course course = new GCA_Course();
				course.setCourseId(rs.getInt("courseid"));
				course.setCourseName(rs.getString("coursename"));
				course.setCourseCity(rs.getString("coursecity"));
				course.setCourseState(rs.getString("coursestate"));
				map.put(course.getCourseName(), course);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_Course course) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_COURSE.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, course.getCourseId());
			ps.setString(2,  course.getCourseName());
			ps.setString(3,  course.getCourseCity());
			ps.setString(4,  course.getCourseState());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_Course course) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_COURSE.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, course.getCourseId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_Course course) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_COURSE.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,  course.getCourseName());
			ps.setString(2,  course.getCourseCity());
			ps.setString(3,  course.getCourseState());
			ps.setInt(4, course.getCourseId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
