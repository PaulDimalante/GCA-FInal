package systemInterfaces;

import java.sql.Connection;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Flight;

public interface GCA_FlightDAOI {
	enum SQL {
		GET_GCA_FLIGHTS_BY_ID("select flightid,tournamentid,flightname,fromhandicap,tohandicap "
				+ "from GCA_Flights where flightid = ?"),
		GET_GCA_FLIGHTS_BY_TOURNAMENTID("select flightid,tournamentid,flightname,fromhandicap,tohandicap "
				+ "from GCA_Flights where tournamentid = ?"),
		INSERT_GCA_FLIGHTS("insert into GCA_Flights (flightid,tournamentid,flightname,fromhandicap,tohandicap) "
				+ "values(?,?,?,?,?)"),
		DELETE_GCA_FLIGHTS("delete from GCA_Flights where flightid = ?"),
		UPDATE_GCA_FLIGHTS("update GCA_Flights set tournamentid=?,flightname=?,fromhandicap=?,tohandicap=?) "
				+ "where flightid = ?"),
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

	public GCA_Flight get(Connection conn, int id) throws GCAException;

	//public Map<Integer, GCA_Flight> getListAll(Connection conn) throws GCAException;

	public Map<Integer, GCA_Flight> getListByTournamentId(Connection conn, int id) throws GCAException;

	public boolean insert(Connection conn, GCA_Flight o) throws GCAException;

	public boolean delete(Connection conn, GCA_Flight o) throws GCAException;

	public boolean update(Connection conn, GCA_Flight o) throws GCAException;
}
