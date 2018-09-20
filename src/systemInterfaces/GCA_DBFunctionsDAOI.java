package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Club;

public interface GCA_DBFunctionsDAOI {
	enum SQL {
		GET_NEXT_ID("select gca_seq.nextval as id from dual"),
		GET_CURRENT_ID("select gca_seq.currval as id from dual"),
		COMMIT_TRANSACTION("commit"),
		ROLLBACK_TRANSACTION("rollback"),
		;
		private final String sql;

		private SQL(String s) {
			this.sql = s;
		}

		public String getSQL() {
			return this.sql;
		}
	}

	public int getNextId(Connection conn) throws GCAException;

	public int getCurrentId(Connection conn) throws GCAException;

}
