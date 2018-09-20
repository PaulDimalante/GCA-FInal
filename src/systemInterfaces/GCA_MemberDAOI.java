package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Member;

public interface GCA_MemberDAOI {
	enum SQL {
		VERIFY("select count(*) from GCA_Members where memberloginid = ? and memberpassword = ?"),
		GET_MEMBERS_BY_LOGINID("select memberid,clubid,membername,membernickname,memberusgaid,memberpassword,memberloginid "
				+ "from GCA_Members where memberloginid = ?"),
		GET_MEMBERS_BY_ID("select memberid,clubid,membername,membernickname,memberusgaid,memberpassword,memberloginid "
				+ "from GCA_Members where memberid = ?"),
		GET_MEMBERS_BY_CLUBID("select memberid,clubid,membername,membernickname,memberusgaid,memberpassword,memberloginid "
				+ "from GCA_Members where clubid = ?"),
		GET_MEMBERS_BY_LOGIN_PASSWORD_CLUBID("select memberid,clubid,membername,membernickname,memberusgaid,memberpassword,memberloginid "
				+ "from GCA_Members where memberloginid = ? and memberpassword= ? and clubid = ?"),
		GET_MEMBERS_BY_LOGIN_CLUBID("select memberid,clubid,membername,membernickname,memberusgaid,memberpassword,memberloginid "
				+ "from GCA_Members where memberloginid = ? and clubid = ?"),
		INSERT_MEMBERS("insert into GCA_Members (memberid,clubid,membername,membernickname,memberusgaid,memberpassword,memberloginid) " 
				+ "values(?,?,?,?,?,?,?)"),
		DELETE_MEMBERS("delete from GCA_Members where memberid = ?"), 
		UPDATE_MEMBERS("update GCA_Members set clubid=?,membername=?,membernickname=?,memberusgaid=?,memberpassword=?,memberloginid=? " 
				+ "where memberid = ?"),
		;
		private final String sql;

		private SQL(String s) {
			this.sql = s;
		}

		public String getSQL() {
			return this.sql;
		}
	}

	public boolean verify(Connection conn, String loginId, String password) throws GCAException;

	public GCA_Member get(Connection conn, int id) throws GCAException;

	public GCA_Member getMemberByLoginIdPassWordClubId(Connection conn, String loginId, String passWord, int clubId) throws GCAException;

	//public Map<Integer, GCA_Member> getListAll(Connection conn) throws GCAException;

	public Map<String, GCA_Member> getListByClubIdKeyMemberName(Connection conn, int clubId) throws GCAException;

	public Map<Integer, GCA_Member> getListByClubIdKeyMemberId(Connection conn, int clubId) throws GCAException;

	public boolean insert(Connection conn, GCA_Member o) throws GCAException;

	public boolean delete(Connection conn, GCA_Member o) throws GCAException;

	public boolean deleteByMemberId(Connection conn, int memberId) throws GCAException;

	public boolean update(Connection conn, GCA_Member o) throws GCAException;
}
