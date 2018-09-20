package systemInterfaces;

import java.sql.Connection;
import java.util.Date;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_SignUp;

public interface GCA_SignUpDAOI {
	enum SQL {
		GET_GCA_SIGNUPS_BY_ID("select signupid,clubid,memberid,signupdate,tournamentid,signedupflag from gca_signups where signupid = ?"),
		GET_GCA_SIGNUPS_BY_TOURNAMENTID("select signupid,clubid,memberid,signupdate,tournamentid,signedupflag from gca_signups where tournamentid = ?"),
		GET_GCA_SIGNUPS_BY_CLUBID_DATE_RANGE("select signupid,clubid,memberid,signupdate,tournamentid,signedupflag "
				+ "from gca_signups s where clubid = ? "
				+ "and tournamentid in (select tournamentId from gca_tournaments t "
				+ "where t.clubid = s.clubid and tournamentstartdate <= ? and tournamentenddate >= ?)"),
		GET_GCA_SIGNUPS_BY_MEMBERID_DATE_RANGE("select signupid,clubid,memberid,signupdate,tournamentid,signedupflag "
				+ "from gca_signups s "
				+ "where memberid = ? "
				+ "and tournamentid in (select tournamentId from gca_tournaments t "
				+ "where t.clubid = s.clubid and tournamentstartdate <= ? and tournamentenddate >= ?)"),
		INSERT_GCA_SIGNUPS("insert into gca_signups (signupid,clubid,memberid,signupdate,tournamentid,signedupflag) values(?,?,?,?,?,?)"),
		DELETE_GCA_SIGNUPS("delete from gca_signups where signupid = ?"),
		DELETE_GCA_SIGNUPS_BY_MEMBERID("delete from gca_signups where memberid = ?"),
		UPDATE_GCA_SIGNUPS("update gca_signups set clubid=?,memberid=?,signupdate=?,tournamentid=?,signedupflag=? where signupid = ?"),
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

	public GCA_SignUp get(Connection conn, int id) throws GCAException;

//	public Map<Integer, GCA_SignUp> getListAll(Connection conn) throws GCAException;

	public Map<Integer, Map<Integer,GCA_SignUp>> getListByTournamentId(Connection conn, int tournamentId) throws GCAException;

	public Map<Integer, Map<Integer,GCA_SignUp>> getListByClubIdDateRange(Connection conn, int clubId, Date fromDate, Date thruDate) throws GCAException;

	public Map<Integer, Map<Integer, GCA_SignUp>> getListByMemberIdDateRange(Connection conn, int memberId, Date fromDate, Date thruDate) throws GCAException;

	public boolean insert(Connection conn, GCA_SignUp o) throws GCAException;

	public boolean delete(Connection conn, GCA_SignUp o) throws GCAException;

	public boolean deleteByMemberId(Connection conn, int memberId) throws GCAException;

	public boolean update(Connection conn, GCA_SignUp o) throws GCAException;
}
