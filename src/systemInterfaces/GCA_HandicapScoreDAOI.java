package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_HandicapScore;

public interface GCA_HandicapScoreDAOI {
	enum SQL {
		GET_GCA_HANDICAPSCORES_BY_ID("select handicapscoreid,scoreholeid,adjustedscore "
				+ "from GCA_HandicapScores where handicapscoreid = ?"),
		GET_GCA_HANDICAPSCORES_BY_MEMBERID("select handicapscoreid,scoreholeid,adjustedscore " + 
				"from gca_handicapScores hs " + 
				"where hs.scoreholeid in " +
				"(select sh.scoreholeid from gca_scoreholes sh " +  
				"where sh.scoreid in " + 
				"(select s.scoreid from gca_scores s " + 
				"where s.memberid = ?))"),
		GET_GCA_HANDICAPSCORES_BY_CLUBID("select handicapscoreid,scoreholeid,adjustedscore " + 
				"from gca_handicapScores hs " + 
				"where hs.scoreholeid in " +
				"(select sh.scoreholeid from gca_scoreholes sh " +  
				"where sh.scoreid in " + 
				"(select s.scoreid from gca_scores s " + 
				"where s.memberid in " +
				"(select memberid from gca_clubs "
				+ "where clubid = ?)))"),
		INSERT_GCA_HANDICAPSCORES("insert into GCA_HandicapScores (handicapscoreid,scoreholeid,adjustedscore) "
				+ "values(?,?,?)"),
		DELETE_GCA_HANDICAPSCORES("delete from GCA_HandicapScores where handicapscoreid = ?"),
		UPDATE_GCA_HANDICAPSCORES("update GCA_HandicapScores set scoreholeid=?,adjustedscore=? "
				+ "where handicapscoreid = ?"),
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

	public GCA_HandicapScore get(Connection conn, int id) throws GCAException;

	public Map<Integer, GCA_HandicapScore> getListByMemberId(Connection conn, int memberId) throws GCAException;

	public Map<Integer, GCA_HandicapScore> getListByClubId(Connection conn, int clubId) throws GCAException;

	public boolean insert(Connection conn, GCA_HandicapScore o) throws GCAException;

	public boolean delete(Connection conn, GCA_HandicapScore o) throws GCAException;

	public boolean update(Connection conn, GCA_HandicapScore o) throws GCAException;
}
