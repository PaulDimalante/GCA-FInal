package springwork.model;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import models.GCA_Member;
import models.GCA_SignUp;
import models.GCA_Tournament;

public class SignUpBean {
	private Map<Date, GCA_Tournament> tournaments = new TreeMap<Date, GCA_Tournament>();
	private Map<Integer, Map<Integer, GCA_SignUp>> signUps = new TreeMap<Integer, Map<Integer, GCA_SignUp>>();
	private Map<Integer, GCA_Member> Members = new TreeMap<Integer, GCA_Member>();
	private Map<String, GCA_Member> MembersByName = new TreeMap<String, GCA_Member>();
	
	public Map<String, GCA_Member> getMembersByName() {
		return MembersByName;
	}
	public void setMembersByName(Map<String, GCA_Member> membersByName) {
		MembersByName = membersByName;
	}
	public Map<Integer, GCA_Member> getMembers() {
		return Members;
	}
	public void setMembers(Map<Integer, GCA_Member> members) {
		Members = members;
	}
	public Map<Date, GCA_Tournament> getTournaments() {
		return tournaments;
	}
	public void setTournaments(Map<Date, GCA_Tournament> tournaments) {
		this.tournaments = tournaments;
	}
	public Map<Integer, Map<Integer, GCA_SignUp>> getSignUps() {
		return signUps;
	}
	public void setSignUps(Map<Integer, Map<Integer, GCA_SignUp>> signUps) {
		this.signUps = signUps;
	}
}
