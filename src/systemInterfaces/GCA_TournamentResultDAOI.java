package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_TournamentResult;

public interface GCA_TournamentResultDAOI {
	enum SQL {
		GET_GCA_TOURNAMENTRESULTS_BY_ID("select tournamentresultid,scoreid,frontnine,backnine,overall from gca_tournamentresults where tournamentresultid = ?"),
		GET_GCA_TOURNAMENTRESULTS_BY_TOURNAMENTID("select tournamentresultid,scoreid,frontnine,backnine,overall "
				+ "from gca_tournamentresults where tournamentresultid in "
				+ "(select scoreid from gcs_scores where tournamentid in ?)"),
		INSERT_GCA_TOURNAMENTRESULTS("insert into gca_tournamentresults (tournamentresultid,scoreid,frontnine,backnine,overall) values(?,?,?,?,?)"),
		DELETE_GCA_TOURNAMENTRESULTS("delete from gca_tournamentresults where tournamentresultid = ?"),
		UPDATE_GCA_TOURNAMENTRESULTS("update gca_tournamentresults set scoreid=?,frontnine=?,backnine=?,overall=? where tournamentresultid = ?")
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

	public GCA_TournamentResult get(Connection conn, int id) throws GCAException;

//	public Map<Integer, GCA_TournamentResult> getListAll(Connection conn) throws GCAException;

	public Map<Integer, GCA_TournamentResult> getListByTournamentId(Connection conn, int tournamentId) throws GCAException;

	public boolean insert(Connection conn, GCA_TournamentResult o) throws GCAException;

	public boolean delete(Connection conn, GCA_TournamentResult o) throws GCAException;

	public boolean update(Connection conn, GCA_TournamentResult o) throws GCAException;
}
