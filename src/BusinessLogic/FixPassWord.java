package BusinessLogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import DAO.GCA_ClubDAO;
import DAO.GCA_MemberDAO;
import DAO.OracleConnection;
import commonFunctions.CommonFunctions;
import customExceptions.GCAException;
import models.GCA_Club;
import models.GCA_Member;

public class FixPassWord {

	public static void main(String[] args) {
		Connection connection = null;
		GCA_ClubDAO clubDAO = new GCA_ClubDAO();
		GCA_MemberDAO memberDAO = new GCA_MemberDAO();
		Map<String,GCA_Club> clubs = new TreeMap<String,GCA_Club>();
		boolean ok = true;
		
		try {
			connection = new OracleConnection().getConnection();
			clubs = clubDAO.getListAll(connection);
			for (Entry<String,GCA_Club> c : clubs.entrySet()) {
				int clubId = c.getValue().getClubId();
				Map<String,GCA_Member> members = memberDAO.getListByClubIdKeyMemberName(connection, clubId);
				for (Entry<String,GCA_Member> e : members.entrySet()) {
					GCA_Member member = e.getValue();
					member.setMemberPassWord(CommonFunctions.encrypt("password"));
					ok = ok && memberDAO.update(connection,member);
				}
			}
			if (ok) {
				connection.commit();
			} else {
				connection.rollback();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (GCAException e) {
			e.printStackTrace();
		} 
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(ok);
	}
}
