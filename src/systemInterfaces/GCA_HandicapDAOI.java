package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Handicap;

public interface GCA_HandicapDAOI {
	enum SQL {
		GET_GCA_HANDICAPS_BY_ID("select handicapid,memberid,asofdate,handicapindex from GCA_Handicaps "
				+ "where handicapid = ?"),
		GET_GCA_HANDICAPS_BY_MEMBERID("select handicapid,memberid,asofdate,handicapindex from GCA_Handicaps "
				+ "where memberid = ?"),
		GET_GCA_HANDICAPS_BY_CLUBID("select handicapid,memberid,asofdate,handicapindex from GCA_Handicaps "
		+ "where memberid in (select memberid from gca_clubs where clubid = ?)"), 
		INSERT_GCA_HANDICAPS("insert into GCA_Handicaps (handicapid,memberid,asofdate,handicapindex) values(?,?,?,?)"),
		DELETE_GCA_HANDICAPS("delete from GCA_Handicaps where handicapid = ?"),
		DELETE_GCA_HANDICAPS_BY_MEMBERID("delete from GCA_Handicaps where memberid = ?"),
		UPDATE_GCA_HANDICAPS("update GCA_Handicaps set memberid=?,asofdate=?,handicapindex=? where handicapid = ? "
				+ "where handicapid = ?"),
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

	public GCA_Handicap get(Connection conn, int id) throws GCAException;

	public Map<Integer, GCA_Handicap> getListByMemberId(Connection conn, int memberId) throws GCAException;

	public Map<Integer, GCA_Handicap> getListByClubId(Connection conn, int clubId) throws GCAException;

	public boolean insert(Connection conn, GCA_Handicap o) throws GCAException;

	public boolean delete(Connection conn, GCA_Handicap o) throws GCAException;

	public boolean deleteByMemberId(Connection conn, int memberId) throws GCAException;

	public boolean update(Connection conn, GCA_Handicap o) throws GCAException;
}
