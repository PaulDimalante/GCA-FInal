package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_PairingsMember;

public interface GCA_PairingsMemberDAOI {
	enum SQL {
		GET_PAIRINGSMEMBERS_BY_ID("select pairingsmemberid,pairingid,memberid,team,groupname,position "
				+ "from gca_pairingsmembers where pairingsmemberid = ?"),
		GET_PAIRINGSMEMBERS_BY_TOURNAMENTID("select pairingsmemberid,pairingid,memberid,team,groupname,position "
				+ "from gca_pairingsmembers where pairingid in "
				+ "(select pairingid from gac_pairings where tournamentid = ?"),
		INSERT_PAIRINGSMEMBERS("insert into gca_pairingsmembers (pairingsmemberid,pairingid,memberid,team,groupname,position) "
				+ "values(?,?,?,?,?,?)"),
		DELETE_PAIRINGSMEMBERS("delete from gca_pairingsmembers where pairingsmemberid = ?"),
		UPDATE_PAIRINGSMEMBERS("update gca_pairingsmembers set pairingid=?,memberid=?,team=?,groupname=?,position=? "
				+ "where pairingsmemberid = ?"),
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

	public GCA_PairingsMember get(Connection conn, int id) throws GCAException;

//	public Map<Integer, GCA_PairingsMember> getListAll(Connection conn) throws GCAException;

	public Map<Integer, GCA_PairingsMember> getListByTournamentId(Connection conn, int tournamentId) throws GCAException;

	public boolean insert(Connection conn, GCA_PairingsMember o) throws GCAException;

	public boolean delete(Connection conn, GCA_PairingsMember o) throws GCAException;

	public boolean update(Connection conn, GCA_PairingsMember o) throws GCAException;
}
