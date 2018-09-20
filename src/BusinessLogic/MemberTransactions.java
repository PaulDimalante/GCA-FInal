package BusinessLogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import DAO.GCA_FunctionDAO;
import DAO.GCA_HandicapDAO;
import DAO.GCA_HandicapScoreDAO;
import DAO.GCA_MemberDAO;
import DAO.GCA_MemberRoleDAO;
import DAO.GCA_RoleDAO;
import DAO.GCA_RoleFunctionDAO;
import DAO.GCA_ScoreDAO;
import DAO.GCA_ScoreHoleDAO;
import DAO.GCA_SignUpDAO;
import DAO.GCA_TournamentResultDAO;
import DAO.GCA_TournamentResultHoleDAO;
import DAO.OracleConnection;
import UserInterface.MemberUI;
import customExceptions.GCAException;
import enums.ROLENAME;
import models.GCA_Function;
import models.GCA_Member;
import models.GCA_MemberRole;
import models.GCA_Role;
import models.GCA_RoleFunction;
import models.GCA_Score;
import models.GCA_ScoreHole;
import systemInterfaces.GCA_MemberDAOI;

public class MemberTransactions {
	private Map<Integer, GCA_Member> member = new TreeMap<Integer, GCA_Member>();
	
	public boolean verifyMember(String loginId, String passWord) {
		boolean verified = false;
		Connection connection = null;
		
		try {
			connection = new OracleConnection().getConnection();
			verified = new GCA_MemberDAO().verify(connection, loginId, passWord);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (GCAException e) {
			e.printStackTrace();
		}

		return verified;
	}
	
	public GCA_Member getMemberByLoginIdPassWordClubId(String loginId, String passWord, int clubId) {
		GCA_Member member = new GCA_Member();
		Connection connection = null;
		
		try {
			connection = new OracleConnection().getConnection();
			member = new GCA_MemberDAO().getMemberByLoginIdPassWordClubId(connection, loginId, passWord, clubId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (GCAException e) {
			e.printStackTrace();
		}

		return member;
	}
	
	public GCA_Member getMemberByMemberId(int memberId) {
		GCA_Member member = new GCA_Member();
		Connection connection = null;
		
		try {
			connection = new OracleConnection().getConnection();
			member = new GCA_MemberDAO().get(connection, memberId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (GCAException e) {
			e.printStackTrace();
		}

		return member;
	}
	
	public boolean addMember(GCA_Member member) {
		boolean success = false;
		Connection connection = null;
		
		try {
			connection = new OracleConnection().getConnection();
			member.setMemberId(GCALib.getNextId(connection));
			success = new GCA_MemberDAO().insert(connection, member);
			
			//get club player roleid
			GCA_Role playerRole = new GCA_RoleDAO().getByClubIdRoleName(connection, member.getClubId(),
					ROLENAME.PLAYER.getValue());
					
			//add member as club player
			GCA_MemberRole memberRole = new GCA_MemberRole();
			memberRole.setMemberRoleId(GCALib.getNextId(connection));
			memberRole.setMemberId(member.getMemberId());
			memberRole.setRoleId(playerRole.getRoleId());
			success = success && new GCA_MemberRoleDAO().insert(connection, memberRole);
			
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
	
	public boolean updateMember(GCA_Member member) {
		boolean success = false;
		Connection connection = null;
		
		try {
			connection = new OracleConnection().getConnection();
			success = new GCA_MemberDAO().update(connection, member);
			
			//get club player roleid
			GCA_Role playerRole = new GCA_RoleDAO().getByClubIdRoleName(connection, member.getClubId(),
					ROLENAME.PLAYER.getValue());
					
			//add member as club player
			GCA_MemberRole memberRole = new GCA_MemberRole();
			memberRole.setMemberRoleId(GCALib.getNextId(connection));
			memberRole.setMemberId(member.getMemberId());
			memberRole.setRoleId(playerRole.getRoleId());
			success = success && new GCA_MemberRoleDAO().insert(connection, memberRole);
			
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
	
	public boolean deleteMember(GCA_Member member) {
		boolean success = false;
		Connection connection = null;
		
		try {
			connection = new OracleConnection().getConnection();

			int memberId = member.getMemberId();
			success = new GCA_ScoreHoleDAO().deleteByMemberId(connection, memberId);
			success = success && new GCA_ScoreDAO().deleteByMemberId(connection, memberId);
			success = success && new GCA_MemberRoleDAO().deleteByMemberId(connection, memberId);
			success = success && new GCA_HandicapDAO().deleteByMemberId(connection, memberId);
			success = success && new GCA_SignUpDAO().deleteByMemberId(connection, memberId);
			success = success && new GCA_MemberDAO().deleteByMemberId(connection, memberId);
			
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
	
	public Map<String,GCA_Member> getMembersListByClubIdKeyMemberName (int clubId) {
		Map<String,GCA_Member> map = new TreeMap<String,GCA_Member>();
		Connection connection = null;
		
		try {
			connection = new OracleConnection().getConnection();
			map = new GCA_MemberDAO().getListByClubIdKeyMemberName(connection, clubId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (GCAException e) {
			e.printStackTrace();
		}

		return map;
	}

	public Map<Integer,GCA_Member> getMembersListByClubIdKeyMemberId (int clubId) {
		Map<Integer,GCA_Member> map = new TreeMap<Integer,GCA_Member>();
		Connection connection = null;
		
		try {
			connection = new OracleConnection().getConnection();
			map = new GCA_MemberDAO().getListByClubIdKeyMemberId(connection, clubId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (GCAException e) {
			e.printStackTrace();
		}

		return map;
	}

}
