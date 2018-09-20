package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Function;

public interface GCA_FunctionDAOI {
	enum SQL {
		GET_GCA_FUNCTIONS_BY_ID("select functionid,functionname from GCA_Functions where functionid = ?"),
		GET_GCA_FUNCTIONS_ALL("select functionid,functionname from GCA_Functions"),
		INSERT_GCA_FUNCTIONS("insert into GCA_Functions (functionid,functionname) values(?,?)"),
		DELETE_GCA_FUNCTIONS("delete from GCA_Functions where functionid = ?"),
		UPDATE_GCA_FUNCTIONS("update GCA_Functions set functionname=? where functionid = ?"),
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

	public GCA_Function get(Connection conn, int id) throws GCAException;

	public Map<String, GCA_Function> getListAll(Connection conn) throws GCAException;

//	public Map<String, GCA_Function> getListById(Connection conn, int id) throws GCAException;

	public boolean insert(Connection conn, GCA_Function o) throws GCAException;

	public boolean delete(Connection conn, GCA_Function o) throws GCAException;

	public boolean update(Connection conn, GCA_Function o) throws GCAException;
}
