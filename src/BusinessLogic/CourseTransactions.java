package BusinessLogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import DAO.*;
import DAO.GCA_ClubDAO;
import DAO.GCA_CourseDAO;
import DAO.GCA_CourseTeeDAO;
import DAO.GCA_FunctionDAO;
import DAO.GCA_MemberDAO;
import DAO.GCA_MemberRoleDAO;
import DAO.GCA_RoleDAO;
import DAO.GCA_RoleFunctionDAO;
import DAO.OracleConnection;
import UserInterface.MemberUI;
import customExceptions.GCAException;
import enums.ACCESSRIGHTS;
import enums.ROLENAME;
import models.GCA_Club;
import models.GCA_Course;
import models.GCA_CourseTee;
import models.GCA_Function;
import models.GCA_Member;
import models.GCA_MemberRole;
import models.GCA_Role;
import models.GCA_RoleFunction;
import springwork.model.Courses;

public class CourseTransactions {
	public Courses getCoursesByNameAndId() {
		Courses courses = new Courses();
		Map<String,GCA_Course> courseByName = new TreeMap<String,GCA_Course>();
		Map<Integer,GCA_Course> courseById = new TreeMap<Integer,GCA_Course>();
		Map<Integer,Map<String, GCA_CourseTee>> courseTeeMap = new TreeMap<Integer,Map<String, GCA_CourseTee>>();
		Connection connection = null;
		boolean success = false;
		
		try {
			connection = new OracleConnection().getConnection();

			courseByName = new GCA_CourseDAO().getListByName(connection);
			courseById = new GCA_CourseDAO().getListAll(connection);
			courseTeeMap = new GCA_CourseTeeDAO().getListAll(connection);
			courses.setCoursesByName(courseByName);
			courses.setCoursesById(courseById);
			courses.setCourseTees(courseTeeMap);
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
				return courses;
			}
		}
	}

	public Courses getCoursesByName() {
		Courses courses = new Courses();
		Map<String,GCA_Course> courseMap = new TreeMap<String,GCA_Course>();
		Map<Integer,Map<String, GCA_CourseTee>> courseTeeMap = new TreeMap<Integer,Map<String, GCA_CourseTee>>();
		Connection connection = null;
		boolean success = false;
		
		try {
			connection = new OracleConnection().getConnection();

			courseMap = new GCA_CourseDAO().getListByName(connection);
			courseTeeMap = new GCA_CourseTeeDAO().getListAll(connection);
			courses.setCoursesByName(courseMap);
			courses.setCourseTees(courseTeeMap);
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
				return courses;
			}
		}
	}

	public Map<Integer,Map<String, GCA_CourseTee>> getCourseTeesByCourseId(int courseId) {
		Map<Integer,Map<String, GCA_CourseTee>> map = new TreeMap<Integer,Map<String, GCA_CourseTee>>();
		Connection connection = null;
		boolean success = false;
		
		try {
			connection = new OracleConnection().getConnection();

			//add club and club adminstrator
			map = new GCA_CourseTeeDAO().getListByCourseId(connection, courseId);
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
				return map;
			}
		}

	}
}
