package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Club;

public interface GCA_ClubDAOI {
	enum SQL {
		GET_CLUB_ALL("select clubid,clubname,clubusgaid,clubhomecoursename,clubcity,clubstate "
				+ "from GCA_Clubs"),
		GET_CLUB_BY_ID("select clubid,clubname,clubusgaid,clubhomecoursename,clubcity,clubstate "
				+ "from GCA_Clubs where clubid = ?"),
		GET_CLUB_BY_MEMBER_ID("select clubid,clubname,clubusgaid,clubhomecoursename,clubcity,clubstate "
				+ "from GCA_Clubs where clubid in (select clubid from gca_members where memberid = ?)"),
		GET_CLUB_BY_LOGINID_PASSWORD("select clubid,clubname,clubusgaid,clubhomecoursename,clubcity,clubstate "
				+ "from GCA_Clubs where clubid in (select clubid from gca_members where memberloginid = ? and memberpassword = ?)"),
		GET_CLUB_BY_LOGINID("select c.clubid,"
				+ "c.clubname,c.clubusgaid,c.clubhomecoursename,"
				+ "c.clubcity,c.clubstate,"
				+ "m.memberpassword "
				+ "from GCA_Clubs c "
				+ "inner join gca_members m on c.clubid = m.clubid "
				+ "where m.memberloginid = ?"),
		INSERT_CLUB("insert into GCA_Clubs (clubid,clubname,clubusgaid,clubhomecoursename,clubcity,clubstate) "
				+ "values(?,?,?,?,?,?)"),
		DELETE_CLUB("delete from GCA_Clubs where clubid = ?"),
		UPDATE_CLUB("update GCA_Clubs set clubname=?,clubusgaid=?,clubhomecoursename=?,clubcity=?,clubstate=?) "
				+ "where clubid = ?")
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

	public GCA_Club get(Connection conn, int id) throws GCAException;

	public Map<String, GCA_Club> getListAll(Connection conn) throws GCAException;

	public Map<String, GCA_Club> getListByMemberId(Connection conn, int memberId) throws GCAException;

	public Map<String, GCA_Club> getListByMemberLoginIdPassWord(Connection conn, String memberLoginId, String memberPassWord) throws GCAException;

	public boolean insert(Connection conn, GCA_Club o) throws GCAException;

	public boolean delete(Connection conn, GCA_Club o) throws GCAException;

	public boolean update(Connection conn, GCA_Club o) throws GCAException;
}
