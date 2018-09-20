package BusinessLogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import DAO.GCA_ClubDAO;
import DAO.GCA_DBFunctionsDAO;
import DAO.GCA_FunctionDAO;
import DAO.GCA_MemberDAO;
import DAO.GCA_MemberRoleDAO;
import DAO.GCA_RoleDAO;
import DAO.GCA_RoleFunctionDAO;
import DAO.OracleConnection;
import UserInterface.ClubUI;
import UserInterface.MemberUI;
import customExceptions.GCAException;
import enums.ACCESSRIGHTS;
import enums.ROLENAME;
import models.GCA_Club;
import models.GCA_Function;
import models.GCA_Member;
import models.GCA_MemberRole;
import models.GCA_Role;
import models.GCA_RoleFunction;
import springwork.model.LoginBean;

public class ClubTransactions {
	private Map<String, GCA_Club> clubs = new TreeMap<String, GCA_Club>();
	
	public LoginBean addClubAndAdministrator(GCA_Club club, GCA_Member member) {
		LoginBean loginBean = new LoginBean();
		boolean success = false;
		Connection connection = null;

		try {
			connection = new OracleConnection().getConnection();

			//add club and club adminstrator
			club.setClubId(GCALib.getNextId(connection));
			member.setMemberId(GCALib.getNextId(connection));
			member.setClubId(club.getClubId());
			
			success = new GCA_ClubDAO().insert(connection, club);
			success = success && new GCA_MemberDAO().insert(connection, member);

			//add club role of adminstrator
			GCA_Role adminRole = new GCA_Role();
			adminRole.setRoleId(GCALib.getNextId(connection));
			adminRole.setClubId(club.getClubId());
			adminRole.setRoleName(ROLENAME.ADMINSTRATOR.getValue());
			success = success && new GCA_RoleDAO().insert(connection, adminRole);

			//get list of all system functions
			Map<String, GCA_Function> functions = new GCA_FunctionDAO().getListAll(connection);

			//add functions to role of adminstrator
			for (Entry<String,GCA_Function> e : functions.entrySet()) {
				GCA_RoleFunction roleFunction = new GCA_RoleFunction();
				roleFunction.setRoleFunctionId(GCALib.getNextId(connection));
				roleFunction.setRoleId(adminRole.getRoleId());
				roleFunction.setFunctionId(e.getValue().getFunctionId());
				roleFunction.setAccessRights(ACCESSRIGHTS.ALL.getValue());
				success = success && new GCA_RoleFunctionDAO().insert(connection, roleFunction);
			}

			//add club role of player
			GCA_Role playerRole = new GCA_Role();
			playerRole.setRoleId(GCALib.getNextId(connection));
			playerRole.setClubId(club.getClubId());
			playerRole.setRoleName(ROLENAME.PLAYER.getValue());
			success = success && new GCA_RoleDAO().insert(connection, playerRole);

			//add functions to role of player
			for (Entry<String,GCA_Function> e : functions.entrySet()) {
				GCA_RoleFunction roleFunction = new GCA_RoleFunction();
				roleFunction.setRoleFunctionId(GCALib.getNextId(connection));
				roleFunction.setRoleId(playerRole.getRoleId());
				roleFunction.setFunctionId(e.getValue().getFunctionId());
				String access;
				if (e.getKey().equals("Sign up")) {
					access = ACCESSRIGHTS.SELF_ONLY.getValue();
				} else {
					access = ACCESSRIGHTS.READONLY.getValue(); 
				}
				roleFunction.setAccessRights(access);
				success = success && new GCA_RoleFunctionDAO().insert(connection, roleFunction);
			}
			
			//add member as club's adminstrator
			GCA_MemberRole memberRole = new GCA_MemberRole();
			memberRole.setMemberRoleId(GCALib.getNextId(connection));
			memberRole.setMemberId(member.getMemberId());
			memberRole.setRoleId(adminRole.getRoleId());
			success = success && new GCA_MemberRoleDAO().insert(connection, memberRole);
			
			loginBean.setClub(club);
			loginBean.setMember(member);
			loginBean.setLoggedIn(success);
			loginBean.setMemberClubs(clubs);
			
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
				return loginBean;
			}
		}
	}
	
	public Map<String, GCA_Club> getClubByMemberLoginIdPassWord(String memberLoginId, String memberPassWord) {
		Map<String, GCA_Club> map = new TreeMap<String, GCA_Club>();
		boolean success = false;
		Connection connection = null;
		
		try {
			connection = new OracleConnection().getConnection();

			map = new GCA_ClubDAO().getListByMemberLoginIdPassWord(connection, memberLoginId, memberPassWord);
			success = (clubs != null);

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
