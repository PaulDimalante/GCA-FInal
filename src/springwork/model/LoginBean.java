package springwork.model;

import java.util.Map;
import java.util.TreeMap;

import models.GCA_Club;
import models.GCA_Member;

public class LoginBean {
	private GCA_Member member = new GCA_Member();
	private Map<String, GCA_Club> memberClubs = new TreeMap<String, GCA_Club>();
	private boolean loggedIn;
	private GCA_Club club;
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public GCA_Club getClub() {
		return club;
	}
	public void setClub(GCA_Club club) {
		this.club = club;
	}
	/**
	 * @return the gcaMember
	 */
	public GCA_Member getMember() {
		return member;
	}
	/**
	 * @param member the member to set
	 */
	public void setMember(GCA_Member member) {
		this.member = member;
	}
	/**
	 * @return the memberClubs
	 */
	public Map<String, GCA_Club> getMemberClubs() {
		return memberClubs;
	}
	/**
	 * @param memberClubs the memberClubs to set
	 */
	public void setMemberClubs(Map<String, GCA_Club> memberClubs) {
		this.memberClubs = memberClubs;
	}
	public int getMemberClubsCount() {
		int count = this.memberClubs.size();
		return count;
	}
}
