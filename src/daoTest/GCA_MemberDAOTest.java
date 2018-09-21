package daoTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import BusinessLogic.GCALib;
import DAO.GCA_MemberDAO;
import DAO.OracleConnection;
import commonFunctions.CommonFunctions;
import customExceptions.GCAException;
import models.GCA_Member;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GCA_MemberDAOTest {
	static GCA_MemberDAO memberDAO;
	static Connection connection;
	static GCA_Member testMember;
	
	@BeforeClass
	public static void beforeClass() {
		memberDAO = new GCA_MemberDAO();
		testMember = new GCA_Member();
		PreparedStatement ps;
		ResultSet rs;
		String sql;
		int rc;
		int clubId = 0;
		
		try {
			connection = new OracleConnection().getConnection();
		} catch (ClassNotFoundException | IOException | SQLException e1) {
			e1.printStackTrace();
		}
		
		//get clubId
		try {
			sql = String.format("select clubid from gca_clubs where clubname='%s'","Clearview Mens Club");
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				clubId = rs.getInt("clubid");
			} else {
				clubId = 0;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Unexpected exception");
		} 
		
		testMember.setMemberId(GCALib.getNextId(connection));
		testMember.setMemberName("test");
		testMember.setMemberLoginId("test");
		testMember.setMemberNickName(" ");
		testMember.setMemberPassWord("password");
		testMember.setMemberUsgaId("123456789");
		testMember.setClubId(clubId);

		//remove test data
		try {
			sql = String.format("delete from gca_members where membername='%s'",testMember.getMemberName());
			ps = connection.prepareStatement(sql);
			rc = ps.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Unexpected exception");
		} 
	}
	
	@Test
	public void test010Insert() {
		GCA_Member member, tempMember;
		
		try {
			//test to see member does not exists
			member = memberDAO.get(connection, testMember.getMemberId());
			Assert.assertNotEquals(testMember.getMemberId(),member.getMemberId());

			//encrypt password
			String encryptPW = CommonFunctions.encrypt(testMember.getMemberPassWord());
			tempMember = testMember.deepCopy(testMember);
			tempMember.setMemberPassWord(encryptPW);

			//test insert member
			boolean flag = memberDAO.insert(connection, tempMember);
			connection.commit();

			member = memberDAO.get(connection, testMember.getMemberId());
			Assert.assertEquals(testMember.getMemberId(),member.getMemberId());
		} catch (GCAException | SQLException e) {
			e.printStackTrace();
			fail("Unexpected exception");
		}
		
	}

	@Test
	public void test020Verify() {
		try {
			//test verify
			boolean flag = memberDAO.verify(connection, testMember.getMemberLoginId(), testMember.getMemberPassWord());
			Assert.assertEquals(true, flag);
		} catch (GCAException e) {
			e.printStackTrace();
			fail("Unexpected exception");
		}
	}

	@Test
	public void test030Get() {
		GCA_Member member;
		
		//test get
		try {
			member = memberDAO.get(connection, testMember.getMemberId());
			Assert.assertEquals(testMember.getMemberId(),member.getMemberId());
		} catch (GCAException e) {
			e.printStackTrace();
			fail("Unexpected exception");
		}
	}

	@Test
	public void test040Update() {
		GCA_Member member;
		
		try {
			//test update
			testMember.setMemberNickName("Nick");
			boolean flag = memberDAO.update(connection, testMember);
			member = memberDAO.get(connection, testMember.getMemberId());
			Assert.assertEquals(testMember.getMemberNickName(),member.getMemberNickName());
			connection.commit();
		} catch (GCAException | SQLException e) {
			e.printStackTrace();
			fail("Unexpected exception");
		}
	}

	@Test
	public void test050Delete() {
		GCA_Member member;
		
		try {
			//test to see member exists
			member = memberDAO.get(connection, testMember.getMemberId());
			Assert.assertEquals(testMember.getMemberId(),member.getMemberId());

			//test insert member
			boolean flag = memberDAO.delete(connection, testMember);
			member = memberDAO.get(connection, testMember.getMemberId());
			Assert.assertNotEquals(testMember.getMemberId(),member.getMemberId());
			connection.commit();
		} catch (GCAException | SQLException e) {
			e.printStackTrace();
			fail("Unexpected exception");
		}
	}

	@AfterClass
	public static void afterClass() {
		try {
			connection.rollback();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Unexpected exception");
		}
	}
}
