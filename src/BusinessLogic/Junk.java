package BusinessLogic;

import java.util.Map;
import java.util.TreeMap;
import models.GCA_Course;
import models.GCA_CourseTee;

public class Junk {
	public static void main(String[] args) {
		String name="memberId[0]";
		String n = "Dimalate, Paul"; 
		int i=13;
		String outStr = "";
		outStr = String.format("<form:option name=%s value=%d > %s</form:option>",name,i,n);
		outStr = String.format("<form:option name=\"%s\" value=\"%d\">%s</form:option>",name,i,n);
		outStr = String.format("<form:option name=\"%s\" value=\"%d%\"> %s</form:option>",name,i,n);
		System.out.println(outStr);
	}
	
	public Map<String,GCA_Course> getCoursesByName() {
		Map<String,GCA_Course> map = new TreeMap<String,GCA_Course>();
		boolean success = false;
		
		Map<Integer,Map<String,GCA_CourseTee>> courseTeeAll = new TreeMap<Integer,Map<String,GCA_CourseTee>>();
		Map<String,GCA_CourseTee> courseTee = new TreeMap<String,GCA_CourseTee>();
		courseTee = courseTeeAll.get(1);
		
		for (Map.Entry<String, GCA_Course> e :map.entrySet()) {
			e.getKey();
			e.getValue();
		}
		return map;
	}
}
