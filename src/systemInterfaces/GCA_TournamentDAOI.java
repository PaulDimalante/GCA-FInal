package systemInterfaces;

import java.sql.Connection;
import java.util.Date;
import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Tournament;

public interface GCA_TournamentDAOI {
	enum SQL {
		GET_GCA_TOURNAMENTS_BY_ID("select tournamentid,"
				+ "clubid,"
				+ "tournamentname,"
				+ "alwaysflag,"
				+ "tournamentstartdate,"
				+ "tournamentenddate,"
				+ "courseid,"
				+ "strokematchrule,"
				+ "handicaprule,"
				+ "handicapreductionpct,"
				+ "teamsize,"
				+ "groupflag,"
				+ "pointsflag,"
				+ "pointsfrontnine,"
				+ "pointsbacknine,"
				+ "pointsoverall,"
				+ "inflightflag,"
				+ "qualifierflag,"
				+ "qualifyingtournamentid "
			+ "from gca_tournaments "
			+ "where tournamentid = ?"),
		GET_GCA_TOURNAMENTS_BY_CLUBID("select tournamentid,"
				+ "clubid,"
				+ "tournamentname,"
				+ "alwaysflag,"
				+ "tournamentstartdate,"
				+ "tournamentenddate,"
				+ "courseid,"
				+ "strokematchrule,"
				+ "handicaprule,"
				+ "handicapreductionpct,"
				+ "teamsize,"
				+ "groupflag,"
				+ "pointsflag,"
				+ "pointsfrontnine,"
				+ "pointsbacknine,"
				+ "pointsoverall,"
				+ "inflightflag,"
				+ "qualifierflag,"
				+ "qualifyingtournamentid "
			+ "from gca_tournaments "
			+ "where clubid = ?"),
		GET_GCA_TOURNAMENTS_BY_CLUBID_DATE_RANGE("select tournamentid,"
				+ "clubid,"
				+ "tournamentname,"
				+ "alwaysflag,"
				+ "tournamentstartdate,"
				+ "tournamentenddate,"
				+ "courseid,"
				+ "strokematchrule,"
				+ "handicaprule,"
				+ "handicapreductionpct,"
				+ "teamsize,"
				+ "groupflag,"
				+ "pointsflag,"
				+ "pointsfrontnine,"
				+ "pointsbacknine,"
				+ "pointsoverall,"
				+ "inflightflag,"
				+ "qualifierflag,"
				+ "qualifyingtournamentid "
			+ "from gca_tournaments "
			+ "where clubid = ? "
			+ "and tournamentstartdate <= ? "
			+ "and tournamentenddate >= ? "
			),
		INSERT_GCA_TOURNAMENTS("insert into gca_tournaments "
				+ "(tournamentid,"
				+ "clubid,"
				+ "tournamentname,"
				+ "alwaysflag,"
				+ "tournamentstartdate,"
				+ "tournamentenddate,"
				+ "courseid,"
				+ "strokematchrule,"
				+ "handicaprule,"
				+ "handicapreductionpct,"
				+ "teamsize,"
				+ "groupflag,"
				+ "pointsflag,"
				+ "pointsfrontnine,"
				+ "pointsbacknine,"
				+ "pointsoverall,"
				+ "inflightflag,"
				+ "qualifierflag,"
				+ "qualifyingtournamentid) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"),
		DELETE_GCA_TOURNAMENTS("delete from gca_tournaments where tournamentid = ?"),
		UPDATE_GCA_TOURNAMENTS("update gca_tournaments set "
				+ "clubid=?,"
				+ "tournamentname=?,"
				+ "alwaysflag=?,"
				+ "tournamentstartdate=?,"
				+ "tournamentenddate=?,"
				+ "courseid=?,"
				+ "strokematchrule=?,"
				+ "handicaprule=?,"
				+ "handicapreductionpct=?,"
				+ "teamsize=?,"
				+ "groupflag=?,"
				+ "pointsflag=?,"
				+ "pointsfrontnine=?,"
				+ "pointsbacknine=?,"
				+ "pointsoverall=?,"
				+ "inflightflag=?,"
				+ "qualifierflag=?,"
				+ "qualifyingtournamentid=? "
				+ "where tournamentid = ?"),
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

	public GCA_Tournament get(Connection conn, int id) throws GCAException;

//	public Map<Date, GCA_Tournament> getListAll(Connection conn) throws GCAException;

	public Map<Date, GCA_Tournament> getListByClubId(Connection conn, int clubId) throws GCAException;

	public Map<Date, GCA_Tournament> getListByClubIdDateRange(Connection conn, int clubId, Date fromDate, Date thruDate) throws GCAException;

	public boolean insert(Connection conn, GCA_Tournament o) throws GCAException;

	public boolean delete(Connection conn, GCA_Tournament o) throws GCAException;

	public boolean update(Connection conn, GCA_Tournament o) throws GCAException;
}
