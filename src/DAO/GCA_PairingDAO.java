package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Pairing;
import systemInterfaces.GCA_PairingDAOI;

public class GCA_PairingDAO implements GCA_PairingDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_Pairing get(Connection conn, int id) throws GCAException {
		GCA_Pairing pairing = new GCA_Pairing();
		String sql = SQL.GET_GCA_PAIRNGS_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				//pairingid,tournamentid,teetime
				pairing.setPairingId(rs.getInt("pairingid"));
				pairing.setTournamentId(rs.getInt("tournamentid"));
				pairing.setTeeTime(rs.getTimestamp("teetime"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return pairing;
	};

//	public Map<Integer, GCA_Pairing> getListAll(Connection conn) throws GCAException {
////	};

	public Map<Integer, GCA_Pairing> getListByTournamentId(Connection conn, int tournamentId) throws GCAException {
		Map<Integer, GCA_Pairing> map = new TreeMap<Integer, GCA_Pairing>();
		String sql = SQL.GET_GCA_PAIRNGS_BY_TOURNAMENTID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tournamentId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//pairingid,tournamentid,teetime
				GCA_Pairing pairing = new GCA_Pairing();
				pairing.setPairingId(rs.getInt("pairingid"));
				pairing.setTournamentId(rs.getInt("tournamentid"));
				pairing.setTeeTime(rs.getTimestamp("teetime"));
				map.put(pairing.getPairingId(), pairing);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_Pairing pairing) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_GCA_PAIRINGS.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pairing.getPairingId());
			ps.setInt(2, pairing.getTournamentId());
			ps.setTimestamp(3, pairing.getTeeTime());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_Pairing pairing) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_GCA_PAIRINGS.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pairing.getPairingId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_Pairing pairing) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_GCA_PAIRINGS.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pairing.getTournamentId());
			ps.setTimestamp(2, pairing.getTeeTime());
			ps.setInt(3, pairing.getPairingId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
