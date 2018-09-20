package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_ScoreHole;

public interface GCA_ScoreHoleDAOI {
	enum SQL {
		GET_SCOREHOLES_BY_ID("select scoreholeid,scoreid,hole,score,xout from gca_scoreholes where scoreholeid = ?"),
		GET_SCOREHOLES_BY_SCOREID("select scoreholeid,scoreid,hole,score,xout from gca_scoreholes where scoreid = ?"),
		INSERT_SCOREHOLES("insert into gca_scoreholes (scoreholeid,scoreid,hole,score,xout) values(?,?,?,?,?)"),
		DELETE_SCOREHOLES("delete from gca_scoreholes where scoreholeid = ?"),
		DELETE_SCOREHOLES_BY_SCOREID("delete from gca_scoreholes where scoreid = ?"),
		DELETE_SCOREHOLES_BY_MEMBERID("delete from gca_scoreholes "
				+ "where scoreid in (select scoreid from gca_scores where memberid = ?)"),
		UPDATE_SCOREHOLES("update gca_scoreholes set scoreid=?,hole=?,score=?,xout=? where scoreholeid = ?")
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

	public GCA_ScoreHole get(Connection conn, int id) throws GCAException;

//	public Map<Integer, GCA_ScoreHole> getListAll(Connection conn) throws GCAException;

	public Map<Integer, GCA_ScoreHole> getListByScoreId(Connection conn, int scoreId) throws GCAException;

	public boolean insert(Connection conn, GCA_ScoreHole o) throws GCAException;

	public boolean delete(Connection conn, GCA_ScoreHole o) throws GCAException;

	public boolean deleteByMemberId(Connection conn, int memberId) throws GCAException;

	public boolean deleteByScoreId(Connection conn, int scoreId) throws GCAException;

	public boolean update(Connection conn, GCA_ScoreHole o) throws GCAException;
}
