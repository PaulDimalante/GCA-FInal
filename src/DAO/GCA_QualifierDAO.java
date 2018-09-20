package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Qualifier;
import systemInterfaces.GCA_QualifierDAOI;

public class GCA_QualifierDAO implements GCA_QualifierDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_Qualifier get(Connection conn, int id) throws GCAException {
		GCA_Qualifier qualifier = new GCA_Qualifier();
		String sql = SQL.GET_GCA_QUALIFIERS_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				//qualifierid,tournamentid,memberid
				qualifier.setQualifierId(rs.getInt("qualifierid"));
				qualifier.setTournamentId(rs.getInt("tournamentid"));
				qualifier.setMemberId(rs.getInt("Memberid"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return qualifier;
	};

//	public Map<Integer, GCA_Qualifier> getListAll(Connection conn) throws GCAException {
////	};

	public Map<Integer, GCA_Qualifier> getListByTournamentId(Connection conn, int tournamentId) throws GCAException {
		Map<Integer, GCA_Qualifier> map = new TreeMap<Integer, GCA_Qualifier>();
		String sql = SQL.GET_GCA_QUALIFIERS_BY_TOURNAMENTID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tournamentId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//qualifierid,tournamentid,memberid
				GCA_Qualifier qualifier = new GCA_Qualifier();
				qualifier.setQualifierId(rs.getInt("qualifierid"));
				qualifier.setTournamentId(rs.getInt("tournamentid"));
				qualifier.setMemberId(rs.getInt("Memberid"));
				map.put(qualifier.getQualifierId(), qualifier);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_Qualifier qualifier) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_GCA_QUALIFIERS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qualifier.getQualifierId());
			ps.setInt(2, qualifier.getTournamentId());
			ps.setInt(3, qualifier.getMemberId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_Qualifier qualifier) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_GCA_QUALIFIERS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qualifier.getQualifierId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_Qualifier qualifier) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_GCA_QUALIFIERS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qualifier.getTournamentId());
			ps.setInt(2, qualifier.getMemberId());
			ps.setInt(3, qualifier.getQualifierId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
