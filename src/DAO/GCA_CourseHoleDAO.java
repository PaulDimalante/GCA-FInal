package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_CourseHole;
import systemInterfaces.GCA_CourseHoleDAOI;

public class GCA_CourseHoleDAO implements GCA_CourseHoleDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_CourseHole get(Connection conn, int id) throws GCAException {
		GCA_CourseHole courseHole = new GCA_CourseHole();
		String sql = SQL.GET_COURSEHOLE_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				courseHole.setCourseHoleId(rs.getInt("courseholeid"));
				courseHole.setCourseId(rs.getInt("courseid"));
				courseHole.setHole(rs.getInt("hole"));
				courseHole.setHandicap(rs.getInt("handicap"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return courseHole;
	};

//	public Map<Integer, GCA_CourseHole> getListAll(Connection conn) throws GCAException {
////	};

	public Map<Integer, GCA_CourseHole> getListByCourseId(Connection conn, int courseId) throws GCAException {
		Map<Integer, GCA_CourseHole> map = new TreeMap<Integer, GCA_CourseHole>();
		String sql = SQL.GET_COURSEHOLE_BY_COURSEID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, courseId);
			rs = ps.executeQuery();
			while (rs.next()) {
				GCA_CourseHole courseHole = new GCA_CourseHole();
				courseHole.setCourseHoleId(rs.getInt("courseholeid"));
				courseHole.setCourseId(rs.getInt("courseid"));
				courseHole.setHole(rs.getInt("hole"));
				courseHole.setHandicap(rs.getInt("handicap"));
				map.put(courseHole.getCourseHoleId(), courseHole);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_CourseHole courseHole) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_COURSEHOLE.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, courseHole.getCourseHoleId());
			ps.setInt(2, courseHole.getCourseId());
			ps.setInt(3, courseHole.getHole());
			ps.setInt(4, courseHole.getHandicap());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_CourseHole courseHole) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_COURSEHOLE.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, courseHole.getCourseHoleId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_CourseHole courseHole) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_COURSEHOLE.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, courseHole.getCourseId());
			ps.setInt(2, courseHole.getHole());
			ps.setInt(3, courseHole.getHandicap());
			ps.setInt(4, courseHole.getCourseHoleId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
