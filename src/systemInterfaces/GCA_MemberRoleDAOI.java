package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_MemberRole;

public interface GCA_MemberRoleDAOI {
	enum SQL {
		GET_MEMBERROLES_BY_ID("select memberroleid,memberid,roleid from gca_memberroles "
				+ "where memberroleid = ?"),
		GET_MEMBERROLES_BY_CLUBID("select memberroleid,memberid,roleid from gca_memberroles "
				+ "where memberid in (select memberid from gca_clubs where clubid = ?"),
		GET_MEMBERROLES_BY_MEMBERID("select memberroleid,memberid,roleid from gca_memberroles "
				+ "where memberid = ?"),
		INSERT_MEMBERROLES("insert into gca_memberroles (memberroleid,memberid,roleid) values(?,?,?)"),
		DELETE_MEMBERROLES("delete from gca_memberroles where memberroleid = ?"),
		DELETE_MEMBERROLES_BY_MEMBERID("delete from gca_memberroles where memberid = ?"),
		UPDATE_MEMBERROLES("update gca_memberroles set memberid=?,roleid=? where memberroleid = ?"),
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

	public GCA_MemberRole get(Connection conn, int id) throws GCAException;

	public Map<Integer, GCA_MemberRole> getListByClubId(Connection conn, int clubId) throws GCAException;

	public Map<Integer, GCA_MemberRole> getListByMemberId(Connection conn, int memberId) throws GCAException;

	public boolean insert(Connection conn, GCA_MemberRole o) throws GCAException;

	public boolean delete(Connection conn, GCA_MemberRole o) throws GCAException;

	public boolean deleteByMemberId(Connection conn, int memberId) throws GCAException;

	public boolean update(Connection conn, GCA_MemberRole o) throws GCAException;
}
