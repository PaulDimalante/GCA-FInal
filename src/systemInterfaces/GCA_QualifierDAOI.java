package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Qualifier;

public interface GCA_QualifierDAOI {
	enum SQL {
		GET_GCA_QUALIFIERS_BY_ID("select qualifierid,tournamentid,memberid from gca_qualifiers where qualifierid = ?"),
		GET_GCA_QUALIFIERS_BY_TOURNAMENTID("select qualifierid,tournamentid,memberid from gca_qualifiers where tournamentid = ?"),
		INSERT_GCA_QUALIFIERS("insert into gca_qualifiers (qualifierid,tournamentid,memberid) values(?,?,?)"),
		DELETE_GCA_QUALIFIERS("delete from gca_qualifiers where qualifierid = ?"),
		UPDATE_GCA_QUALIFIERS("update gca_qualifiers set tournamentid=?,memberid=? where qualifierid = ?"),
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

	public GCA_Qualifier get(Connection conn, int id) throws GCAException;

//	public Map<Integer, GCA_Qualifier> getListAll(Connection conn) throws GCAException;

	public Map<Integer, GCA_Qualifier> getListByTournamentId(Connection conn, int tournamentId) throws GCAException;

	public boolean insert(Connection conn, GCA_Qualifier o) throws GCAException;

	public boolean delete(Connection conn, GCA_Qualifier o) throws GCAException;

	public boolean update(Connection conn, GCA_Qualifier o) throws GCAException;
}
