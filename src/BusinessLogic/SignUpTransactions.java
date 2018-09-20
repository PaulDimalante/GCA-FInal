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
import DAO.GCA_SignUpDAO;
import DAO.GCA_TournamentDAO;
import DAO.OracleConnection;
import customExceptions.GCAException;
import enums.ACCESSRIGHTS;
import enums.ROLENAME;
import models.GCA_Function;
import models.GCA_Member;
import models.GCA_MemberRole;
import models.GCA_Role;
import models.GCA_RoleFunction;
import models.GCA_SignUp;
import models.GCA_Tournament;
import springwork.model.SignUpBean;

public class SignUpTransactions {
	public SignUpBean getMemberSignUpSheets(int clubId, int memberId, Date fromDate) {
		SignUpBean signUpBean = new SignUpBean();
		Map<Date, GCA_Tournament> tournaments = new TreeMap<Date, GCA_Tournament>();
		Map<Integer, Map<Integer, GCA_SignUp>> signUps = new TreeMap<Integer, Map<Integer, GCA_SignUp>>();
		Map<Integer, GCA_Member> members = new TreeMap<Integer, GCA_Member>();
		boolean success = false;
		Connection connection = null;

		try {
			connection = new OracleConnection().getConnection();

			tournaments = new GCA_TournamentDAO().getListByClubIdDateRange(connection, clubId, fromDate, null);
			signUps = new GCA_SignUpDAO().getListByMemberIdDateRange(connection, memberId, fromDate, null);
			GCA_Member member = new GCA_MemberDAO().get(connection, memberId);
			members.put(memberId, member);
			
			signUpBean.setTournaments(tournaments);
			signUpBean.setSignUps(signUps);
			signUpBean.setMembers(members);

			success = (tournaments != null && signUps != null);
			
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
				return signUpBean;
			}
		}
	}

	public SignUpBean getClubSignUpSheets(int clubId, Date fromDate) {
		SignUpBean signUpBean = new SignUpBean();
		Map<Date, GCA_Tournament> tournaments = new TreeMap<Date, GCA_Tournament>();
		Map<Integer,Map<Integer, GCA_SignUp>> signUps = new TreeMap<Integer, Map<Integer,GCA_SignUp>>();
		Map<Integer, GCA_Member> members = new TreeMap<Integer, GCA_Member>();
		boolean success = false;
		Connection connection = null;

		try {
			connection = new OracleConnection().getConnection();

			tournaments = new GCA_TournamentDAO().getListByClubIdDateRange(connection, clubId, fromDate, null);
			signUps = new GCA_SignUpDAO().getListByClubIdDateRange(connection, clubId, fromDate, null);
			members = new GCA_MemberDAO().getListByClubIdKeyMemberId(connection, clubId);
	
			signUpBean.setTournaments(tournaments);
			signUpBean.setSignUps(signUps);
			signUpBean.setMembers(members);

			success = (tournaments != null && signUps != null);
			
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
				return signUpBean;
			}
		}
	}

	public SignUpBean getClubMembers(int clubId) {
		SignUpBean signUpBean = new SignUpBean();
		Map<String, GCA_Member> members = new TreeMap<String, GCA_Member>();
		boolean success = false;
		Connection connection = null;

		try {
			connection = new OracleConnection().getConnection();

			members = new GCA_MemberDAO().getListByClubIdKeyMemberName(connection, clubId);
			signUpBean.setMembersByName(members);

			success = (members != null);
			
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
				return signUpBean;
			}
		}
	}

	public boolean addUpdate(GCA_SignUp signUp) {
		boolean success = false;
		Connection connection = null;

		try {
			connection = new OracleConnection().getConnection();
			
			if (signUp.getSignUpId() == 0) {
				//add
				signUp.setSignUpId(GCALib.getNextId(connection));
				signUp.setSignUpDate(new Date());
				success = new GCA_SignUpDAO().insert(connection, signUp);
			} else {
				//update
				success = new GCA_SignUpDAO().update(connection, signUp);
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

	public GCA_SignUp getBySignUpId(int signUpId) {
		GCA_SignUp signUp = new GCA_SignUp();
		boolean success = false;
		Connection connection = null;

		try {
			connection = new OracleConnection().getConnection();
			
			if (signUpId != 0) {
				signUp = new GCA_SignUpDAO().get(connection, signUpId);
				success = signUp.getSignUpId() != 0;
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
				return signUp;
			}
		}
	}
}
