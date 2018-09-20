package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_RoleFunction;

public interface GCA_RoleFunctionDAOI {
	enum SQL {
		GET_GCA_ROLEFUNCTIONS_BY_ID("select rolefunctionid,roleid,functionid,accessrights from gca_rolefunctions where rolefunctionid = ?"),
		GET_GCA_ROLEFUNCTIONS_BY_CLUBID("select rolefunctionid,roleid,functionid,accessrights "
				+ "from gca_rolefunctions "
				+ "where rollid in (select rollid from gca_roles where clubid = ?"),
		INSERT_GCA_ROLEFUNCTIONS("insert into gca_rolefunctions (rolefunctionid,roleid,functionid,accessrights) values(?,?,?,?)"),
		DELETE_GCA_ROLEFUNCTIONS("delete from gca_rolefunctions where rolefunctionid = ?"),
		UPDATE_GCA_ROLEFUNCTIONS("update gca_rolefunctions set roleid=?,functionid=?,accessrights=? where rolefunctionid = ?"),
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

	public GCA_RoleFunction get(Connection conn, int id) throws GCAException;

//	public Map<Integer, GCA_RoleFunction> getListAll(Connection conn) throws GCAException;

	public Map<Integer, GCA_RoleFunction> getListByClubId(Connection conn, int clubId) throws GCAException;

	public boolean insert(Connection conn, GCA_RoleFunction o) throws GCAException;

	public boolean delete(Connection conn, GCA_RoleFunction o) throws GCAException;

	public boolean update(Connection conn, GCA_RoleFunction o) throws GCAException;
}
