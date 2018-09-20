package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Role;

public interface GCA_RoleDAOI {
	enum SQL {
		GET_GCA_ROLES_BY_ID("select roleid,clubid,rolename from gca_roles where roleid = ?"),
		GET_GCA_ROLES_BY_CLUBID("select roleid,clubid,rolename from gca_roles where clubid = ?"),
		GET_GCA_ROLES_BY_CLUBID_ROLENAME("select roleid,clubid,rolename from gca_roles "
				+ "where clubid = ? and rolename = ?"),
		INSERT_GCA_ROLES("insert into gca_roles (roleid,clubid,rolename) values(?,?,?)"),
		DELETE_GCA_ROLES("update gca_roles set clubid=?,rolename=? where roleid = ?"),
		UPDATE_GCA_ROLES("update from gca_roles clubid=?,rolename=? where roleid = ?"),
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

	public GCA_Role get(Connection conn, int id) throws GCAException;

	public GCA_Role getByClubIdRoleName(Connection conn, int id, String name) throws GCAException;

	public Map<Integer, GCA_Role> getListByClubId(Connection conn, int clubId) throws GCAException;

	public boolean insert(Connection conn, GCA_Role o) throws GCAException;

	public boolean delete(Connection conn, GCA_Role o) throws GCAException;

	public boolean update(Connection conn, GCA_Role o) throws GCAException;
}
