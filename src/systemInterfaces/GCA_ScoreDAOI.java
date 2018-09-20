package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Score;

public interface GCA_ScoreDAOI {
	enum SQL {
		GET_GCA_SCORES_BY_ID("select scoreid,memberid,tournamentid,dateofround,courseid,courseteeid,groupname,team,roundNo,tournamentflag from gca_scores where scoreid = ?"),
		GET_GCA_SCORES_BY_TOURNAMENTID("select scoreid,memberid,tournamentid,dateofround,courseid,courseteeid,groupname,team,roundNo,tournamentflag "
				+ "from gca_scores where tournamentid = ?"),
		GET_GCA_SCORES_BY_MEMBERID("select scoreid,memberid,tournamentid,dateofround,courseid,courseteeid,groupname,team,roundNo,tournamentflag "
				+ "from gca_scores where memberid = ?"),
		INSERT_GCA_SCORES("insert into gca_scores (scoreid,memberid,tournamentid,dateofround,courseid,courseteeid,groupname,team,roundNo,tournamentflag) "
				+ "values(?,?,?,?,?,?,?,?,?,?)"),
		DELETE_GCA_SCORES("delete from gca_scores where scoreid = ?"),
		DELETE_GCA_SCORES_BY_MEMBERID("delete from gca_scores where memberid = ?"),
		UPDATE_GCA_SCORES("update gca_scores set memberid=?,tournamentid=?,dateofround=?,courseid=?,courseteeid=?,groupname=?,team=?,roundNo=?,tournamentflag=? "
				+ "where scoreid = ?"),
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

	public GCA_Score get(Connection conn, int id) throws GCAException;

	public Map<Integer, GCA_Score> getListByTournamentId(Connection conn, int tournamentId) throws GCAException;

	public Map<Integer, GCA_Score> getListByMemberId(Connection conn, int memberId) throws GCAException;

	public boolean insert(Connection conn, GCA_Score o) throws GCAException;

	public boolean delete(Connection conn, GCA_Score o) throws GCAException;

	public boolean deleteByMemberId(Connection conn, int memberId) throws GCAException;

	public boolean update(Connection conn, GCA_Score o) throws GCAException;
}
