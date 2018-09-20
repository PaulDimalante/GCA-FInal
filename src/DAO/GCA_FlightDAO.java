package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Flight;
import systemInterfaces.GCA_FlightDAOI;

public class GCA_FlightDAO implements GCA_FlightDAOI {
	public boolean verify(Connection conn, String name, String password) throws GCAException {
		return false;	};

	public GCA_Flight get(Connection conn, int id) throws GCAException {
		GCA_Flight flight = new GCA_Flight();
		String sql = SQL.GET_GCA_FLIGHTS_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				flight.setFlightId(rs.getInt("flightid"));
				flight.setTournamentId(rs.getInt("courseid"));
				flight.setFlightName(rs.getString("flightname"));
				flight.setFromHandicap(rs.getInt("fromhandicap"));
				flight.setToHandicap(rs.getInt("tohandicap"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return flight;
	};

//	public Map<Integer, GCA_Flight> getListAll(Connection conn) throws GCAException {
////	};

	public Map<Integer, GCA_Flight> getListByTournamentId(Connection conn, int id) throws GCAException {
		Map<Integer, GCA_Flight> map = new TreeMap<Integer, GCA_Flight>();
		String sql = SQL.GET_GCA_FLIGHTS_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				GCA_Flight flight = new GCA_Flight();
				flight.setFlightId(rs.getInt("flightid"));
				flight.setTournamentId(rs.getInt("courseid"));
				flight.setFlightName(rs.getString("flightname"));
				flight.setFromHandicap(rs.getInt("fromhandicap"));
				flight.setToHandicap(rs.getInt("tohandicap"));
				map.put(flight.getFlightId(), flight);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_Flight o) throws GCAException {
		boolean success = false;
		GCA_Flight flight = new GCA_Flight();
		String sql = SQL.INSERT_GCA_FLIGHTS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, flight.getFlightId());
			ps.setInt(2, flight.getTournamentId());
			ps.setString(3, flight.getFlightName());
			ps.setInt(4, flight.getFromHandicap());
			ps.setInt(5, flight.getToHandicap());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_Flight o) throws GCAException {
		boolean success = false;
		GCA_Flight flight = new GCA_Flight();
		String sql = SQL.DELETE_GCA_FLIGHTS.getSQL();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, flight.getFlightId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_Flight o) throws GCAException {
		boolean success = false;
		GCA_Flight flight = new GCA_Flight();
		String sql = SQL.INSERT_GCA_FLIGHTS.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, flight.getTournamentId());
			ps.setString(2, flight.getFlightName());
			ps.setInt(3, flight.getFromHandicap());
			ps.setInt(4, flight.getToHandicap());
			ps.setInt(5, flight.getFlightId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
