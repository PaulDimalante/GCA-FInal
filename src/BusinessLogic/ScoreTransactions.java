package BusinessLogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import DAO.*;
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
import models.GCA_Score;
import models.GCA_ScoreHole;
import springwork.model.Courses;
import springwork.model.ScoresBean;

public class ScoreTransactions {
	public ScoresBean getAllScores(int clubId) {
		ScoresBean scoresBean = new ScoresBean();
		Map<String,GCA_Member> memberMap = new TreeMap<String,GCA_Member>();
		Map<String,GCA_Course> courseMap = new TreeMap<String,GCA_Course>();
		Map<Integer,Map<String, GCA_CourseTee>> courseTeeMap = new TreeMap<Integer,Map<String, GCA_CourseTee>>();
		Connection connection = null;
		boolean success = false;
		
		try {
			connection = new OracleConnection().getConnection();

			memberMap = new GCA_MemberDAO().getListByClubIdKeyMemberName(connection, clubId);
			courseMap = new GCA_CourseDAO().getListByName(connection);
			courseTeeMap = new GCA_CourseTeeDAO().getListAll(connection);
			scoresBean.setMembers(memberMap);
			scoresBean.setCourses(courseMap);
			scoresBean.setCourseTees(courseTeeMap);
			scoresBean.setClubId(clubId);
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
				return scoresBean;
			}
		}
	}
	
	public ScoresBean getBlankScoreCard(int clubId) {
		ScoresBean scoresBean = new ScoresBean();
		Map<String,GCA_Member> memberMap = new TreeMap<String,GCA_Member>();
		Map<String,GCA_Course> courseMap = new TreeMap<String,GCA_Course>();
		Map<Integer,Map<String, GCA_CourseTee>> courseTeeMap = new TreeMap<Integer,Map<String, GCA_CourseTee>>();
		Connection connection = null;
		boolean success = false;
		
		try {
			connection = new OracleConnection().getConnection();

			memberMap = new GCA_MemberDAO().getListByClubIdKeyMemberName(connection, clubId);
			courseMap = new GCA_CourseDAO().getListByName(connection);
			courseTeeMap = new GCA_CourseTeeDAO().getListAll(connection);
			scoresBean.setMembers(memberMap);
			scoresBean.setCourses(courseMap);
			scoresBean.setCourseTees(courseTeeMap);
			scoresBean.setClubId(clubId);
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
				return scoresBean;
			}
		}

	}

	public ScoresBean getScore(int scoreId) {
		ScoresBean scoresBean = new ScoresBean();
		Map<String,GCA_Member> memberMap = new TreeMap<String,GCA_Member>();
		Map<String,GCA_Course> courseMap = new TreeMap<String,GCA_Course>();
		Map<Integer,Map<String, GCA_CourseTee>> courseTeeMap = new TreeMap<Integer,Map<String, GCA_CourseTee>>();
		GCA_Score score = new GCA_Score();
		Map<Integer,GCA_ScoreHole> scoreHoleMap = new TreeMap<Integer,GCA_ScoreHole>();
		GCA_Member member = new GCA_Member();
		int memberId,clubId;
		Connection connection = null;
		boolean success = false;
		
		try {
			if (scoreId > 0) {
				connection = new OracleConnection().getConnection();
	
				GCA_MemberDAO memberDAO = new GCA_MemberDAO();
				member = memberDAO.get(connection, score.getMemberId());
				
				memberMap = memberDAO.getListByClubIdKeyMemberName(connection, member.getClubId());
				courseMap = new GCA_CourseDAO().getListByName(connection);
				courseTeeMap = new GCA_CourseTeeDAO().getListAll(connection);
				score = new GCA_ScoreDAO().get(connection, scoreId);
				scoreHoleMap = new GCA_ScoreHoleDAO().getListByScoreId(connection, scoreId);
	
				scoresBean.updateByScore(score);
				scoresBean.setMembers(memberMap);
				scoresBean.setCourses(courseMap);
				scoresBean.setCourseTees(courseTeeMap);
				scoresBean.setClubId(member.getClubId());
				
				int p = 0;
				scoresBean.setMemberId(member.getMemberId(), p);
				scoresBean.setScoreId(scoreId,p);
				
				int frontNine=0,backNine=0,overAll=0;
				for (Entry<Integer,GCA_ScoreHole> e : scoreHoleMap.entrySet()) {
					GCA_ScoreHole sh = e.getValue();
					int h = sh.getHole() - 1;
					scoresBean.setHoleScoreId(sh.getScoreHoleId(),p,h);
					scoresBean.setHoleScore(sh.getScore(),p,h);
					scoresBean.setxOut(sh.getxOut(),p,h);
					if (h>=0 && h<9) {
						frontNine += sh.getScore();
					}
					if (h>=9 && h<18) {
						backNine += sh.getScore();
					}
					overAll += sh.getScore();
				}
				scoresBean.setFrontNine(frontNine,p);
				scoresBean.setBackNine(backNine,p);
				scoresBean.setOverAll(overAll,p);
	
				success = true;
			}
			
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
				return scoresBean;
			}
		}

	}

	public boolean postScoreCard(ScoresBean scoresBean) {
		boolean success = false;
		Connection connection = null;
		int[] scoreId = new int[scoresBean.getNumberOfMembers()];
		int[][] scoreHoleId = new int[scoresBean.getNumberOfMembers()][18];
		
		try {
			connection = new OracleConnection().getConnection();

			for (int p=0; p<scoresBean.getNumberOfMembers(); p++) {
				if (scoresBean.getMemberId()[p] != 0) {
					String op = "";
					GCA_Score score = new GCA_Score();
					score.setMemberId(scoresBean.getMemberId()[p]);
					score.setCourseId(scoresBean.getCourseId());
					score.setCourseTeeId(scoresBean.getCourseTeeId());
					score.setDateOfRound(scoresBean.getDateOfRound());
					score.setGroupName(scoresBean.getGroupName());
					score.setRoundNo(scoresBean.getRoundNo());
					score.setTeam(scoresBean.getTeam());
					score.setTournamentFlag(scoresBean.getTournamentFlag());
					score.setTournamentId(scoresBean.getTournamentId());
					
					if (scoresBean.getScoreId()[p] == 0) {
						op = "add";
						score.setScoreId(GCALib.getNextId(connection));
						success = new GCA_ScoreDAO().insert(connection, score);
					}	else {
						op = "update";
						score.setScoreId(scoresBean.getScoreId()[p]);
						success = new GCA_ScoreDAO().update(connection, score);
					}
					
					scoreId[p] = score.getScoreId();
					
					for (int h=0; h<18; h++) {
						GCA_ScoreHole scoreHole = new GCA_ScoreHole();
						scoreHole.setScoreId(scoreId[p]);
						scoreHole.setHole(h+1);
						scoreHole.setScore(scoresBean.getHoleScore()[p][h]);
						scoreHole.setxOut(scoresBean.getxOut()[p][h]);
						
						if (op.equals("add") || scoresBean.getHoleScoreId()[p][h] == 0) {
							scoreHole.setScoreHoleId(GCALib.getNextId(connection));
							success = success && (new GCA_ScoreHoleDAO().insert(connection, scoreHole));
						} else {
							scoreHole.setScoreHoleId(scoresBean.getHoleScoreId()[p][h]);
							success = success && (new GCA_ScoreHoleDAO().update(connection, scoreHole));
						}
						
						scoreHoleId[p][h] = scoreHole.getScoreHoleId();
					}
				}
			}
			
			if (success) {
				scoresBean.setScoreId(scoreId);
				scoresBean.setHoleScoreId(scoreHoleId);
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
				return success;
			}
		}

	}

	public boolean deleteScore(int scoreId) {
		boolean success = false;
		Connection connection = null;
		
		try {
			connection = new OracleConnection().getConnection();

			if (scoreId != 0) {
				GCA_ScoreHoleDAO scoreHoleDAO = new GCA_ScoreHoleDAO();
				success = scoreHoleDAO.deleteByScoreId(connection, scoreId);
				
				GCA_ScoreDAO scoreDAO = new GCA_ScoreDAO();
				GCA_Score score = scoreDAO.get(connection, scoreId);
				success = success && scoreDAO.delete(connection,  score);
			}

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
				return success;
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
