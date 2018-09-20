package BusinessLogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import DAO.GCA_ClubDAO;
import DAO.GCA_FunctionDAO;
import DAO.GCA_MemberDAO;
import DAO.GCA_MemberRoleDAO;
import DAO.GCA_RoleDAO;
import DAO.GCA_RoleFunctionDAO;
import DAO.GCA_TournamentDAO;
import DAO.OracleConnection;
import customExceptions.GCAException;
import enums.ACCESSRIGHTS;
import enums.ROLENAME;
import models.GCA_Club;
import models.GCA_Function;
import models.GCA_MemberRole;
import models.GCA_Role;
import models.GCA_RoleFunction;
import models.GCA_Tournament;

public class TournamentTransactions {
	public GCA_Tournament getByTournamentId(int tournamentId) {
		GCA_Tournament tournament = new GCA_Tournament();
		boolean success = false;
		Connection connection = null;

		try {
			connection = new OracleConnection().getConnection();

			if (tournamentId != 0) {
				tournament = new GCA_TournamentDAO().get(connection, tournamentId);
				success = (tournament.getTournamentId() != 0);
			}
			
			if (success) {
				connection.commit();
			} else {
				connection.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			connection.rollback();
		} catch (IOException e) {
			e.printStackTrace();
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} catch (GCAException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				return tournament;
			}
		}
	}
	
	public boolean addUpdate(GCA_Tournament tournament) {
		boolean success = false;
		Connection connection = null;

		try {
			connection = new OracleConnection().getConnection();

			if (tournament.getTournamentId() == 0) {
				//add
				tournament.setTournamentId(GCALib.getNextId(connection));
				success = new GCA_TournamentDAO().insert(connection, tournament);
			} else {
				//update
				success = new GCA_TournamentDAO().update(connection, tournament);
			}
			
			if (success) {
				connection.commit();
			} else {
				connection.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			connection.rollback();
		} catch (IOException e) {
			e.printStackTrace();
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} catch (GCAException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				return success;
			}
		}
	}
	
	public Map<Date, GCA_Tournament> getTournamentsByClubId(int clubId) {
		Map<Date, GCA_Tournament> map = new TreeMap<Date, GCA_Tournament>();
		boolean success = false;
		Connection connection = null;
		Date fromDate, thruDate;
		java.util.Date currDate;

		currDate = new java.util.Date();
		
		try {
			connection = new OracleConnection().getConnection();
	
//			map = new GCA_TournamentDAO().getListByClubIdDateRange(connection, clubId, fromDate, thruDate);
			map = new GCA_TournamentDAO().getListByClubId(connection, clubId);
			success = (map != null);
	
			if (success) {
				connection.commit();
			} else {
				connection.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			connection.rollback();
		} catch (IOException e) {
			e.printStackTrace();
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} catch (GCAException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				return map;
			}
		}
	}
	
	public Map<Date, GCA_Tournament> getTournamentsResultsByClubId(int clubId) {
		Map<Date, GCA_Tournament> map = new TreeMap<Date, GCA_Tournament>();
		boolean success = false;
		Connection connection = null;
		Date fromDate, thruDate;
		java.util.Date currDate;

		currDate = new java.util.Date();
		
		try {
			connection = new OracleConnection().getConnection();
	
//			map = new GCA_TournamentDAO().getListByClubIdMemberIdDateRange(connection, clubId, fromDate, thruDate);
			map = new GCA_TournamentDAO().getListByClubId(connection, clubId);
			success = (map != null);
	
			if (success) {
				connection.commit();
			} else {
				connection.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			connection.rollback();
		} catch (IOException e) {
			e.printStackTrace();
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} catch (GCAException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				return map;
			}
		}
	}
	
}
