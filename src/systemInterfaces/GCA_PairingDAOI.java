package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Pairing;

public interface GCA_PairingDAOI {
	enum SQL {
		GET_GCA_PAIRNGS_BY_ID("select pairingid,tournamentid,teetime from gca_pairings where pairingid = ?"),
		GET_GCA_PAIRNGS_BY_TOURNAMENTID("select pairingid,tournamentid,teetime from gca_pairings where tournamentid = ?"),
		INSERT_GCA_PAIRINGS("insert into gca_pairings (pairingid,tournamentid,teetime) values(?,?,?)"),
		DELETE_GCA_PAIRINGS("delete from gca_pairings where pairingid = ?"),
		UPDATE_GCA_PAIRINGS("update gca_pairings set tournamentid=?,teetime=? where pairingid = ?)")
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

	public GCA_Pairing get(Connection conn, int id) throws GCAException;

	//public Map<Integer, GCA_Pairing> getListAll(Connection conn) throws GCAException;

	public Map<Integer, GCA_Pairing> getListByTournamentId(Connection conn, int tournamentid) throws GCAException;

	public boolean insert(Connection conn, GCA_Pairing o) throws GCAException;

	public boolean delete(Connection conn, GCA_Pairing o) throws GCAException;

	public boolean update(Connection conn, GCA_Pairing o) throws GCAException;
}
