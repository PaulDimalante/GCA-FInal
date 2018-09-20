package springwork.model;
 
import java.util.Map;
import java.util.TreeMap;

import models.GCA_Handicap;
import models.GCA_Member;

public class Handicaps {
	private Map<String, GCA_Member> members = new TreeMap<String, GCA_Member>();
	private Map<Integer, GCA_Handicap> handicaps = new TreeMap<Integer, GCA_Handicap>();
	/**
	 * @return the members
	 */
	public Map<String, GCA_Member> getMembers() {
		return members;
	}
	/**
	 * @param members the members to set
	 */
	public void setMembers(Map<String, GCA_Member> members) {
		this.members = members;
	}
	/**
	 * @return the handicaps
	 */
	public Map<Integer, GCA_Handicap> getHandicaps() {
		return handicaps;
	}
	/**
	 * @param handicaps the handicaps to set
	 */
	public void setHandicaps(Map<Integer, GCA_Handicap> handicaps) {
		this.handicaps = handicaps;
	}
	
	
}
