package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_TournamentResultHole;

public interface GCA_TournamentResultHoleDAOI {
	enum SQL {
		GET_GCA_TOURNAMENTRESULTHOLES_BY_ID("select tournamentresultholeid,tournamentresultid,scoreholeid,netscore "
				+ "from gca_tournamentresultholes where tournamentresultholeid = ?"),
		GET_GCA_TOURNAMENTRESULTHOLES_BY_TOURNAMENTRESULTID("select tournamentresultholeid,tournamentresultid,scoreholeid,netscore "
				+ "from gca_tournamentresultholes where tournamentresultid = ?"),
		GET_GCA_TOURNAMENTRESULTHOLES_BY_SCOREID("select tournamentresultholeid,tournamentresultid,scoreholeid,netscore "
				+ "from gca_tournamentresultholes where tournamentresultid in "
				+ "(select tournamentresultid from gcs_tournamentresults where scoreid = ?"),
		INSERT_GCA_TOURNAMENTRESULTHOLES("insert into gca_tournamentresultholes (tournamentresultholeid,tournamentresultid,scoreholeid,netscore) "
				+ "values(?,?,?,?)"),
		DELETE_GCA_TOURNAMENTRESULTHOLES("delete from gca_tournamentresultholes where tournamentresultholeid = ?"),
		UPDATE_GCA_TOURNAMENTRESULTHOLES("update gca_tournamentresultholes set tournamentresultid=?,scoreholeid=?,netscore=? where tournamentresultholeid = ?")
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

	public GCA_TournamentResultHole get(Connection conn, int id) throws GCAException;

	public Map<Integer, GCA_TournamentResultHole> getListByTournamentResultId(Connection conn, int tournamentResultId) throws GCAException;

	public Map<Integer, GCA_TournamentResultHole> getListByScoreId(Connection conn, int Scoreid) throws GCAException;

	public boolean insert(Connection conn, GCA_TournamentResultHole o) throws GCAException;

	public boolean delete(Connection conn, GCA_TournamentResultHole o) throws GCAException;

	public boolean update(Connection conn, GCA_TournamentResultHole o) throws GCAException;
}
