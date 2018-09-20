package BusinessLogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import DAO.GCA_CourseDAO;
import DAO.GCA_CourseTeeDAO;
import DAO.GCA_HandicapDAO;
import DAO.GCA_MemberDAO;
import DAO.OracleConnection;
import customExceptions.GCAException;
import models.GCA_Course;
import models.GCA_CourseTee;
import models.GCA_Handicap;
import models.GCA_Member;
import springwork.model.Courses;
import springwork.model.Handicaps;

public class HandicapTransactions {
	public Handicaps getHandicapsByClubId(int clubId) {
		Handicaps handicaps = new Handicaps();
		Map<String,GCA_Member> membersMap = new TreeMap<String,GCA_Member>();
		Map<Integer,GCA_Handicap> handicapsMap = new TreeMap<Integer,GCA_Handicap>();
		Connection connection = null;
		boolean success = false;
		
		try {
			connection = new OracleConnection().getConnection();

			//add club and club adminstrator
			membersMap = new GCA_MemberDAO().getListByClubIdKeyMemberName(connection, clubId);
			handicapsMap = new GCA_HandicapDAO().getListByClubId(connection, clubId);
			handicaps.setMembers(membersMap);
			handicaps.setHandicaps(handicapsMap);
			success = true;
			
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
				return handicaps;
			}
		}

	}

}
