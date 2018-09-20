package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_CourseHole;

public interface GCA_CourseHoleDAOI {
	enum SQL {
		GET_COURSEHOLE_BY_ID("select courseholeid,courseid,hole,handicap from GCA_CourseHoles "
				+ "where courseholeid = ?"),
		GET_COURSEHOLE_BY_COURSEID("select courseholeid,courseid,hole,handicap from GCA_CourseHoles "
				+ "where courseid = ?"),
		INSERT_COURSEHOLE("insert into GCA_CourseHoles (courseholeid,courseid,hole,handicap) values(?,?,?,?)"),
		DELETE_COURSEHOLE("delete from GCA_CourseHoles where courseholeid = ?"),
		UPDATE_COURSEHOLE("update GCA_CourseHoles set courseid=?,hole=?,handicap=? where courseholeid = ?"),
		;
		private final String sql;

		private SQL(String s) {
			this.sql = s;
		}

		public String getSQL() {
			return this.sql;
		}
	}

	public boolean verify(Connection conn, String name, String password) throws GCAException;

	public GCA_CourseHole get(Connection conn, int id) throws GCAException;

	//public Map<Integer, GCA_CourseHole> getListAll(Connection conn) throws GCAException;

	public Map<Integer, GCA_CourseHole> getListByCourseId(Connection conn, int courseId) throws GCAException;

	public boolean insert(Connection conn, GCA_CourseHole o) throws GCAException;

	public boolean delete(Connection conn, GCA_CourseHole o) throws GCAException;

	public boolean update(Connection conn, GCA_CourseHole o) throws GCAException;
}
