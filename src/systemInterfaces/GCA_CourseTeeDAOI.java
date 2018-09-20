package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_CourseTee;

public interface GCA_CourseTeeDAOI {
	enum SQL {
		GET_COURSETEE_BY_ID("select courseteeid,courseid,fromdate,thrudate,tee,rating,slope "
				+ "from GCA_CourseTees where courseteeid = ?"),
		GET_COURSETEE_ALL("select courseteeid,courseid,fromdate,thrudate,tee,rating,slope "
				+ "from GCA_CourseTees"),
		GET_COURSETEE_BY_COURSEID("select courseteeid,courseid,fromdate,thrudate,tee,rating,slope "
				+ "from GCA_CourseTees where courseid = ?"),
		INSERT_COURSETEE("insert into GCA_CourseTees (courseteeid,courseid,fromdate,thrudate,tee,rating,slope) "
				+ "values(?,?,?,?,?,?,?)"),
		DELETE_COURSETEE("delete from GCA_CourseTees where courseteeid = ?"),
		UPDATE_COURSETEE("update GCA_CourseTees set courseid=?,fromdate=?,thrudate=?,tee=?,rating=?,slope=? "
				+ "where courseteeid = ?")
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

	public GCA_CourseTee get(Connection conn, int id) throws GCAException;

	public Map<Integer, Map<String, GCA_CourseTee>> getListAll(Connection conn) throws GCAException;

	public Map<Integer, Map<String, GCA_CourseTee>> getListByCourseId(Connection conn, int courseId) throws GCAException;

	public boolean insert(Connection conn, GCA_CourseTee o) throws GCAException;

	public boolean delete(Connection conn, GCA_CourseTee o) throws GCAException;

	public boolean update(Connection conn, GCA_CourseTee o) throws GCAException;
}
