package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Course;

public interface GCA_CourseDAOI {
	enum SQL {
		GET_COURSE_BY_ID("select courseid,coursename,coursecity,coursestate from GCA_Courses where courseid = ?"),
		GET_COURSE_ALL("select courseid,coursename,coursecity,coursestate from GCA_Courses"),
		GET_COURSE_BY_NAME("select courseid,coursename,coursecity,coursestate from GCA_Courses order by coursename"),
		INSERT_COURSE("insert into GCA_Courses (courseid,coursename,coursecity,coursestate) values(?,?,?,?)"),
		DELETE_COURSE("delete from GCA_Courses where courseid = ?"),
		UPDATE_COURSE("update GCA_Courses set coursename=?,coursecity=?,coursestate=?) where courseid = ?"),
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

	public GCA_Course get(Connection conn, int id) throws GCAException;

	public Map<Integer, GCA_Course> getListAll(Connection conn) throws GCAException;

	public Map<String, GCA_Course> getListByName(Connection conn) throws GCAException;

	public boolean insert(Connection conn, GCA_Course o) throws GCAException;

	public boolean delete(Connection conn, GCA_Course o) throws GCAException;

	public boolean update(Connection conn, GCA_Course o) throws GCAException;
}
