package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import commonFunctions.CommonFunctions;

import java.util.Map;
import customExceptions.GCAException;
import models.GCA_Member;
import systemInterfaces.GCA_MemberDAOI;

public class GCA_MemberDAO implements GCA_MemberDAOI {
	public boolean verify(Connection conn, String loginId, String password) throws GCAException {
		boolean verified = false;
		GCA_Member member = new GCA_Member();
		//String sql = SQL.VERIFY.getSQL();
		String sql = SQL.GET_MEMBERS_BY_LOGINID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
//			ps.setString(2, password);
//			rs = ps.executeQuery();
//			if (rs.next()) {
//				verified = (rs.getInt(1) > 0);
//			}
			rs = ps.executeQuery();
			while (rs.next() && ! verified) {
				String encryptPW = rs.getString(6);
				verified = CommonFunctions.chk2Encrypt(password, encryptPW);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return verified;	};

	public GCA_Member get(Connection conn, int id) throws GCAException {
		GCA_Member member = new GCA_Member();
		String sql = SQL.GET_MEMBERS_BY_ID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				//memberid,clubid,membername,membernickname,memberusgaid,memberpassword,memberloginid
				member.setMemberId(rs.getInt("memberid"));
				member.setClubId(rs.getInt("clubid"));
				member.setMemberName(rs.getString("membername"));
				member.setMemberNickName(rs.getString("membernickname"));
				member.setMemberUsgaId(rs.getString("memberUsgaId"));
				member.setMemberPassWord(rs.getString("memberpassword"));
				member.setMemberLoginId(rs.getString("memberloginid"));
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return member;
	};

	public GCA_Member getMemberByLoginIdPassWordClubId(Connection conn, String loginId, String passWord, int clubId) throws GCAException {
		GCA_Member member = new GCA_Member();
		//String sql = SQL.GET_MEMBERS_BY_LOGIN_PASSWORD_CLUBID.getSQL();
		String sql = SQL.GET_MEMBERS_BY_LOGIN_CLUBID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			//ps.setString(2, passWord);
			//ps.setInt(3, clubId);
			ps.setInt(2, clubId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//memberid,clubid,membername,membernickname,memberusgaid,memberpassword,memberloginid
				String encryptPW = rs.getString("memberpassword");
				if (CommonFunctions.chk2Encrypt(passWord, encryptPW)) {
					member.setMemberId(rs.getInt("memberid"));
					member.setClubId(rs.getInt("clubid"));
					member.setMemberName(rs.getString("membername"));
					member.setMemberNickName(rs.getString("membernickname"));
					member.setMemberUsgaId(rs.getString("memberUsgaId"));
					member.setMemberPassWord(rs.getString("memberpassword"));
					member.setMemberLoginId(rs.getString("memberloginid"));
				}
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return member;
	}


//	public Map<Integer, GCA_Member> getListAll(Connection conn) throws GCAException {
//	};

	public Map<String, GCA_Member> getListByClubIdKeyMemberName(Connection conn, int clubId) throws GCAException {
		Map<String, GCA_Member> map = new TreeMap<String, GCA_Member>();
		String sql = SQL.GET_MEMBERS_BY_CLUBID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, clubId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//memberid,clubid,membername,membernickname,memberusgaid,memberpassword,memberloginid
				GCA_Member member = new GCA_Member();
				member.setMemberId(rs.getInt("memberid"));
				member.setClubId(rs.getInt("clubid"));
				member.setMemberName(rs.getString("membername"));
				member.setMemberNickName(rs.getString("membernickname"));
				member.setMemberUsgaId(rs.getString("memberUsgaId"));
				member.setMemberPassWord(rs.getString("memberpassword"));
				member.setMemberLoginId(rs.getString("memberloginid"));
				map.put(member.getMemberDisplayName(), member);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public Map<Integer, GCA_Member> getListByClubIdKeyMemberId(Connection conn, int clubId) throws GCAException {
		Map<Integer, GCA_Member> map = new TreeMap<Integer, GCA_Member>();
		String sql = SQL.GET_MEMBERS_BY_CLUBID.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, clubId);
			rs = ps.executeQuery();
			while (rs.next()) {
				//memberid,clubid,membername,membernickname,memberusgaid,memberpassword,memberloginid
				GCA_Member member = new GCA_Member();
				member.setMemberId(rs.getInt("memberid"));
				member.setClubId(rs.getInt("clubid"));
				member.setMemberName(rs.getString("membername"));
				member.setMemberNickName(rs.getString("membernickname"));
				member.setMemberUsgaId(rs.getString("memberUsgaId"));
				member.setMemberPassWord(rs.getString("memberpassword"));
				member.setMemberLoginId(rs.getString("memberloginid"));
				map.put(member.getMemberId(), member);
			}
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return map;
	};

	public boolean insert(Connection conn, GCA_Member member) throws GCAException {
		boolean success = false;
		String sql = SQL.INSERT_MEMBERS.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, member.getMemberId());
			ps.setInt(2, member.getClubId());
			ps.setString(3, member.getMemberName());
			ps.setString(4, member.getMemberNickName());
			ps.setString(5, member.getMemberUsgaId());
			ps.setString(6, member.getMemberPassWord());
			ps.setString(7, member.getMemberLoginId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean delete(Connection conn, GCA_Member member) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_MEMBERS.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, member.getMemberId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean deleteByMemberId(Connection conn, int memberId) throws GCAException {
		boolean success = false;
		String sql = SQL.DELETE_MEMBERS.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memberId);
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};

	public boolean update(Connection conn, GCA_Member member) throws GCAException {
		boolean success = false;
		String sql = SQL.UPDATE_MEMBERS.getSQL();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, member.getClubId());
			ps.setString(2, member.getMemberName());
			ps.setString(3, member.getMemberNickName());
			ps.setString(4, member.getMemberUsgaId());
			ps.setString(5, member.getMemberPassWord());
			ps.setString(6, member.getMemberLoginId());
			ps.setInt(7, member.getMemberId());
			success = (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			throw new GCAException(e.getMessage());
		}

		return success;
	};
}
