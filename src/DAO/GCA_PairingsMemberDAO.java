package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_PairingsMember;

import systemInterfaces.GCA_PairingsMemberDAOI;

public class GCA_PairingsMemberDAO implements GCA_PairingsMemberDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_PairingsMember get(Connection conn, int id) throws GCAException {
		GCA_PairingsMember pairingsMember = new GCA_PairingsMember();
		String sql = SQL.GET_PAIRINGSMEMBERS_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				//pairingsmemberid,pairingid,memberid,team,groupname,position
				pairingsMember.setPairingsMemberId(rs.getInt("pairingsMemberid"));
				pairingsMember.setPairingId(rs.getInt("pairingsid"));
				pairingsMember.setMemberId(rs.getInt("Memberid"));
				pairingsMember.setTeam(rs.getString("team"));
				pairingsMember.setGroupName(rs.getString("groupname"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return pairingsMember;
	};

//	public Map<Integer, GCA_PairingsMember> getListAll(Connection conn) throws GCAException {
////	};

	public Map<Integer, GCA_PairingsMember> getListByTournamentId(Connection conn, int tournamentId) throws GCAException {
		Map<Integer, GCA_PairingsMember> map = new TreeMap<Integer, GCA_PairingsMember>();
		String sql = SQL.GET_PAIRINGSMEMBERS_BY_TOURNAMENTID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tournamentId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//pairingsmemberid,pairingid,memberid,team,groupname,position
				GCA_PairingsMember pairingsMember = new GCA_PairingsMember();
				pairingsMember.setPairingsMemberId(rs.getInt("pairingsMemberid"));
				pairingsMember.setPairingId(rs.getInt("pairingsid"));
				pairingsMember.setMemberId(rs.getInt("Memberid"));
				pairingsMember.setTeam(rs.getString("team"));
				pairingsMember.setGroupName(rs.getString("groupname"));
				map.put(pairingsMember.getPairingsMemberId(), pairingsMember);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_PairingsMember pairingsMember) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_PAIRINGSMEMBERS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pairingsMember.getPairingsMemberId());
			ps.setInt(2, pairingsMember.getPairingId());
			ps.setInt(3, pairingsMember.getMemberId());
			ps.setString(4, pairingsMember.getTeam());
			ps.setString(5, pairingsMember.getGroupName());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_PairingsMember pairingsMember) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_PAIRINGSMEMBERS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pairingsMember.getPairingsMemberId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_PairingsMember pairingsMember) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_PAIRINGSMEMBERS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pairingsMember.getPairingId());
			ps.setInt(2, pairingsMember.getMemberId());
			ps.setString(3, pairingsMember.getTeam());
			ps.setString(4, pairingsMember.getGroupName());
			ps.setInt(5, pairingsMember.getPairingsMemberId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
