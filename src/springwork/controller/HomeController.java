package springwork.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import BusinessLogic.ClubTransactions;
import BusinessLogic.CourseTransactions;
import BusinessLogic.HandicapTransactions;
import BusinessLogic.MemberTransactions;
import BusinessLogic.ScoreTransactions;
import BusinessLogic.SignUpTransactions;
import BusinessLogic.TournamentTransactions;
import DAO.GCA_MemberDAO;
import DAO.GCA_SignUpDAO;
import DAO.GCA_TournamentDAO;
import UserInterface.ClubUI;
import commonFunctions.CommonFunctions;
import models.*;
import springwork.customAnnotations.PassWordVerifyValidator;
import springwork.model.Courses;
import springwork.model.EnrollForm;
import springwork.model.Handicaps;
import springwork.model.LoginBean;
import springwork.model.PairingsList;
import springwork.model.ScoresBean;
import springwork.model.SignUpBean;

@Controller
public class HomeController {

	@RequestMapping(value = "/test01", method = RequestMethod.GET)
	public ModelAndView test01Get(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";

		se.setAttribute("loggedIn", loggedIn);
		mav = new ModelAndView("test01");

		return mav;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";
		Courses courses = new CourseTransactions().getCoursesByNameAndId();

		se.setAttribute("loggedIn", loggedIn);
		se.setAttribute("courses", courses);

		mav = new ModelAndView("welcome");

		return mav;
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView welcomeGet(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		Date currentDate = (Date) new java.util.Date();
		boolean loggedIn = false;
		String message = "";
		Courses courses = new CourseTransactions().getCoursesByNameAndId();

		se.setAttribute("loggedIn", loggedIn);
		se.setAttribute("courses", courses);

		mav = new ModelAndView("welcome");
		mav.addObject("currentDate", currentDate);
		mav.addObject("currentDateFormatted", CommonFunctions.getCurrentFormatedDate(currentDate));
		mav.addObject("loggedIn", loggedIn);

		return mav;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logoutGet(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();

		se.invalidate();
		mav = new ModelAndView("welcome");

		return mav;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginGet(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";

		se.setAttribute("loggedIn", loggedIn);
		request.setAttribute("message", message);
		mav = new ModelAndView("login");

		return mav;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPost(HttpServletRequest request) {
		HttpSession se = request.getSession();
		ModelAndView mav;
		LoginBean loginBean = new LoginBean();
		MemberTransactions memberTransactions = new MemberTransactions();
		String loginId = request.getParameter("loginId");
		String passWord = (String) request.getParameter("passWord");
		int memberId = 0;
		int clubId = 0;
		String memberName = "";
		boolean loggedIn = false;
		String message = "";
		Date currentDate = (Date) new java.util.Date();

		if (memberTransactions.verifyMember(loginId, passWord)) {
			loginBean.setMemberClubs(new ClubTransactions().getClubByMemberLoginIdPassWord(loginId, passWord));

			if (request.getParameter("clubId") != null) {
				clubId = Integer.parseInt(request.getParameter("clubId"));
				loggedIn = true;
			} else if (loginBean.getMemberClubsCount() == 1) {
				loggedIn = true;
				for (Entry<String,GCA_Club> e : loginBean.getMemberClubs().entrySet()) {
					clubId = e.getValue().getClubId();
				}
			} else {
				loggedIn = false;
			}
			
			if (loggedIn) {
				loginBean.setMember(memberTransactions.getMemberByLoginIdPassWordClubId(loginId, passWord, clubId));
				memberId = loginBean.getMember().getMemberId();
				memberName = loginBean.getMember().getMemberName();
				message = "Login Successful";
				mav = new ModelAndView("home");
			} else {
				message = "You belong to multiple clubs, please select one";
				mav = new ModelAndView("login");
				mav.addObject("loginId", loginId);
				mav.addObject("passWord", passWord);
				mav.addObject("passWordOrig", passWord);
			}
			
			mav.addObject(loginBean);
			se.setAttribute("memberId", memberId);
			se.setAttribute("clubId", clubId);
		} else {
			loggedIn = false;
			message = "Login failed";
			mav = new ModelAndView("login");
			mav.addObject("loginId", loginId);
			mav.addObject("passWord", passWord);
			mav.addObject("passWordOrig", passWord);
		}

		se.setAttribute("currentDate", currentDate);
		se.setAttribute("currentDateFormatted", CommonFunctions.getCurrentFormatedDate(currentDate));
		se.setAttribute("memberName", memberName);
		se.setAttribute("loggedIn", loggedIn);
		request.setAttribute("message", message);

		return mav;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";

		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		if (loggedIn) {
			mav = new ModelAndView("home");
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);
		
		return mav;
	}
	
	@RequestMapping(value = "/signUpList", method = RequestMethod.GET)
	public ModelAndView signUpListGet(HttpServletRequest request
			,@ModelAttribute("signUp") @Valid GCA_SignUp signUp
			) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";
		SignUpBean signUpBean;
		int clubId = 0;
		int memberId = 0;
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (se.getAttribute("clubId") != null) {
			clubId = (Integer) se.getAttribute("clubId");
		}
		
		if (se.getAttribute("memberId") != null) {
			memberId = (Integer) se.getAttribute("memberId");
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");
		
		if (loggedIn && clubId != 0 && memberId != 0) {
			signUpBean = new SignUpTransactions().getMemberSignUpSheets(clubId, memberId, new Date());
			mav = new ModelAndView("signUpList");
			mav.addObject("signUpBean", signUpBean);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}
		
		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/signUpList", method = RequestMethod.POST)
	public ModelAndView signUpListPost(HttpServletRequest request
			,@ModelAttribute("signUp") @Valid GCA_SignUp signUp
			) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		boolean success = false;
//		boolean signUp = Boolean.valueOf(request.getParameter("signUp"));
		String message = "";
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		if (loggedIn) {
			mav = new ModelAndView("signUpList");
			if (success) {
				message = "Sign Up successfull";
			} else {
				message = "Sign Up cancelled";
			}
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}
		
		request.setAttribute("message", message);

		return mav;
	}

	@RequestMapping(value = "/signUpDetail", method = RequestMethod.GET)
	public ModelAndView signUpDetailGet(HttpServletRequest request
			,@ModelAttribute("signUp") @Valid GCA_SignUp signUp
			) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		boolean success = false;
		String message = "";
		int clubId = 0;
		int memberId = 0;
		int signUpId = 0;
//		GCA_SignUp signUp = new GCA_SignUp();
		Date tournamentDate = null;
		String tournamentName = "";
		String signedUpFlag = "False";
		Date signUpDate = null;
		int tournamentId = 0;
		String memberDisplayName = "";
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (se.getAttribute("clubId") != null) {
			clubId = (Integer) se.getAttribute("clubId");
		}
		
		if (se.getAttribute("memberId") != null) {
			memberId = (Integer) se.getAttribute("memberId");
		}
		
		if (request.getParameter("signUpId") != null) {
			signUpId = Integer.parseInt(request.getParameter("signUpId"));
		}
		
		if (request.getParameter("tournamentId") != null) {
			tournamentId = Integer.parseInt(request.getParameter("tournamentId"));
		}
		
		if (request.getParameter("tournamentDate") != null) {
			tournamentDate = CommonFunctions.parseDate((String) request.getParameter("tournamentDate"));
		}
		
		if (request.getParameter("tournamentName") != null) {
			tournamentName = (String) request.getParameter("tournamentName");
		}
		
		if (request.getParameter("signedUpFlag") != null) {
			signedUpFlag = (String) request.getParameter("signedUpFlag");
		}

		if (request.getParameter("signUpDate") != null) {
			signUpDate = CommonFunctions.parseDate((String) request.getParameter("signUpDate"));
		}
		
		if (request.getParameter("memberDisplayName") != null) {
			memberDisplayName = (String) request.getParameter("memberDisplayName");
		}

		loggedIn = (Boolean) se.getAttribute("loggedIn");
		
		if (loggedIn && clubId != 0 && memberId != 0) {
			if (signUpId == 0) {
				signUp.setClubId(clubId);
				signUp.setMemberId(memberId);
				signUp.setSignedUpFlag(signedUpFlag);
				signUp.setSignUpDate(new Date());
				signUp.setSignUpId(signUpId);
				signUp.setTournamentId(tournamentId);
			} else {
				signUp = new SignUpTransactions().getBySignUpId(signUpId);
			}
			
			mav = new ModelAndView("signUpDetail");
			mav.addObject("tournamentDate", tournamentDate);
			mav.addObject("tournamentName", tournamentName);
			mav.addObject("signedUpFlag", signedUpFlag);
			mav.addObject("signUp", signUp);
			mav.addObject("memberDisplayName", memberDisplayName);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}
		
		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/signUpDetail", method = RequestMethod.POST)
	public ModelAndView signUpDetailPost(HttpServletRequest request
			,@ModelAttribute("signUp") @Valid GCA_SignUp signUp
			) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		boolean success = false;
		String message = "";
		int clubId = 0;
		int memberId = 0;
		Date signUpDate = null;
		Date tournamentDate = null;
		String tournamentName = "";
		String signedUpFlag = "False";
		int tournamentId = 0;
		int signUpId = 0;
//		GCA_SignUp signUp = new GCA_SignUp();
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (se.getAttribute("clubId") != null) {
			clubId = (Integer) se.getAttribute("clubId");
		}
		
		if (se.getAttribute("memberId") != null) {
			memberId = (Integer) se.getAttribute("memberId");
		}
		
		if (request.getParameter("signUpDate") != null) {
			signUpDate = CommonFunctions.parseDate(request.getParameter("signUpDate"));
		}

		if (request.getParameter("tournamentId") != null) {
			tournamentId = Integer.parseInt(request.getParameter("tournamentId"));
		}

		if (request.getParameter("signedUpFlag") != null) {
			signedUpFlag = (String) request.getParameter("signedUpFlag");
		}
		
		if (request.getParameter("tournamentDate") != null) {
			tournamentDate = CommonFunctions.parseDate(request.getParameter("tournamentDate"));
		}

		if (request.getParameter("tournamentName") != null) {
			tournamentName = (String) request.getParameter("tournamentName");
		}

		if (request.getParameter("signUpId") != null) {
			signUpId = Integer.parseInt(request.getParameter("signUpId"));
		}

		signUp.setClubId(clubId);
		signUp.setMemberId(memberId);
		signUp.setSignedUpFlag(signedUpFlag);
		signUp.setSignUpDate(signUpDate);
		signUp.setSignUpId(signUpId);
		signUp.setTournamentId(tournamentId);
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");
		
		if (loggedIn && clubId != 0 && memberId != 0) {
			success = new SignUpTransactions().addUpdate(signUp);

			if (success) {
				message = "Update Successfull";
			} else {
				message = "Update Failed";
			}
			
			mav = new ModelAndView("signUpDetail");
			mav.addObject("signUp", signUp);
			mav.addObject("tournamentName", tournamentName);
			mav.addObject("signedUpFlag", signedUpFlag);
			mav.addObject("tournamentDate", tournamentDate);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}
		
		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/signUpListAdmin", method = RequestMethod.GET)
	public ModelAndView signUpListAdminGet(HttpServletRequest request
//			,@ModelAttribute("signUpBean") @Valid SignUpBean signUpBean
			) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";
		int clubId = 0;
		SignUpBean signUpBean = new SignUpBean();
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (se.getAttribute("clubId") != null) {
			clubId = (Integer) se.getAttribute("clubId");
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");
		
		if (loggedIn && clubId != 0) {
			signUpBean = new SignUpTransactions().getClubSignUpSheets(clubId, new Date());
			mav = new ModelAndView("signUpListAdmin");
			mav.addObject("signUpBean", signUpBean);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}
		
		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/signUpDetailAdminUpdate", method = RequestMethod.GET)
	public ModelAndView signUpDetailAdminUpdateGet(HttpServletRequest request
			,@ModelAttribute("signUp") @Valid GCA_SignUp signUp
			) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		boolean success = false;
		String message = "";
		int clubId = 0;
		int memberId = 0;
		int signUpId = 0;
//		GCA_SignUp signUp = new GCA_SignUp();
		Date tournamentDate = null;
		String tournamentName = "";
		String signedUpFlag = "False";
		Date signUpDate = null;
		int tournamentId = 0;
		String memberName = "";
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (se.getAttribute("clubId") != null) {
			clubId = (Integer) se.getAttribute("clubId");
		}
		
		if (request.getParameter("signUpId") != null) {
			signUpId = Integer.parseInt(request.getParameter("signUpId"));
		}
		
		if (request.getParameter("tournamentId") != null) {
			tournamentId = Integer.parseInt(request.getParameter("tournamentId"));
		}
		
		if (request.getParameter("tournamentDate") != null) {
			tournamentDate = CommonFunctions.parseDate((String) request.getParameter("tournamentDate"));
		}
		
		if (request.getParameter("tournamentName") != null) {
			tournamentName = (String) request.getParameter("tournamentName");
		}
		
		if (request.getParameter("signedUpFlag") != null) {
			signedUpFlag = (String) request.getParameter("signedUpFlag");
		}

		if (request.getParameter("signUpDate") != null) {
			signUpDate = CommonFunctions.parseDate((String) request.getParameter("signUpDate"));
		}

		if (request.getParameter("memberName") != null) {
			memberName = (String) request.getParameter("memberName");
		}

		memberId = signUp.getMemberId();
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");
		
		if (loggedIn && clubId != 0 && memberId != 0) {
			if (signUpId == 0) {
				signUp.setClubId(clubId);
				signUp.setMemberId(memberId);
				signUp.setSignedUpFlag(signedUpFlag);
				signUp.setSignUpDate(new Date());
				signUp.setSignUpId(signUpId);
				signUp.setTournamentId(tournamentId);
			} else {
				signUp = new SignUpTransactions().getBySignUpId(signUpId);
			}
			
			mav = new ModelAndView("signUpDetailAdminUpdate");
			mav.addObject("tournamentDate", tournamentDate);
			mav.addObject("tournamentName", tournamentName);
			mav.addObject("signedUpFlag", signedUpFlag);
			mav.addObject("signUp", signUp);
			mav.addObject("memberName", memberName);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}
		
		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/signUpDetailAdminUpdate", method = RequestMethod.POST)
	public ModelAndView signUpDetailAdminUpdatePost(HttpServletRequest request
			,@ModelAttribute("signUp") @Valid GCA_SignUp signUp
			) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		boolean success = false;
		String message = "";
		int clubId = 0;
		int memberId = 0;
		Date signUpDate = null;
		Date tournamentDate = null;
		String tournamentName = "";
		String memberName = "";
		String signedUpFlag = "False";
		int tournamentId = 0;
		int signUpId = 0;
//		GCA_SignUp signUp = new GCA_SignUp();
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (se.getAttribute("clubId") != null) {
			clubId = (Integer) se.getAttribute("clubId");
		}
		
		if (request.getParameter("memberName") != null) {
			memberName = (String) request.getParameter("memberName");
		}

		if (request.getParameter("signUpDate") != null) {
			signUpDate = CommonFunctions.parseDate(request.getParameter("signUpDate"));
		}

		if (request.getParameter("tournamentId") != null) {
			tournamentId = Integer.parseInt(request.getParameter("tournamentId"));
		}

		if (request.getParameter("signedUpFlag") != null) {
			signedUpFlag = (String) request.getParameter("signedUpFlag");
		}
		
		if (request.getParameter("tournamentDate") != null) {
			tournamentDate = CommonFunctions.parseDate(request.getParameter("tournamentDate"));
		}

		if (request.getParameter("tournamentName") != null) {
			tournamentName = (String) request.getParameter("tournamentName");
		}

		if (request.getParameter("signUpId") != null) {
			signUpId = Integer.parseInt(request.getParameter("signUpId"));
		}

		memberId = signUp.getMemberId();
		
		signUp.setClubId(clubId);
		signUp.setMemberId(memberId);
		signUp.setSignedUpFlag(signedUpFlag);
		signUp.setSignUpDate(signUpDate);
		signUp.setSignUpId(signUpId);
		signUp.setTournamentId(tournamentId);
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");
		
		if (loggedIn && clubId != 0 && memberId != 0) {
			success = new SignUpTransactions().addUpdate(signUp);

			if (success) {
				message = "Update Successfull";
			} else {
				message = "Update Failed";
			}
			
			mav = new ModelAndView("signUpDetailAdminUpdate");
			mav.addObject("signUp", signUp);
			mav.addObject("tournamentName", tournamentName);
			mav.addObject("signedUpFlag", signedUpFlag);
			mav.addObject("tournamentDate", tournamentDate);
			mav.addObject("memberName", memberName);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}
		
		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/signUpDetailAdminAdd", method = RequestMethod.GET)
	public ModelAndView signUpDetailAdminAddGet(HttpServletRequest request
			,@ModelAttribute("signUp") @Valid GCA_SignUp signUp
			) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		boolean success = false;
		String message = "";
		int clubId = 0;
		int memberId = 0;
		int signUpId = 0;
		Date tournamentDate = null;
		String tournamentName = "";
		String signedUpFlag = "False";
		Date signUpDate = null;
		int tournamentId = 0;
		Map<String,GCA_Member> members = new TreeMap<String,GCA_Member>();
		SignUpBean signUpBean = new SignUpBean();
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (se.getAttribute("clubId") != null) {
			clubId = (Integer) se.getAttribute("clubId");
		}
		
		if (request.getParameter("signUpId") != null) {
			signUpId = Integer.parseInt(request.getParameter("signUpId"));
		}
		
		if (request.getParameter("tournamentId") != null) {
			tournamentId = Integer.parseInt(request.getParameter("tournamentId"));
		}
		
		if (request.getParameter("tournamentDate") != null) {
			tournamentDate = CommonFunctions.parseDate((String) request.getParameter("tournamentDate"));
		}
		
		if (request.getParameter("tournamentName") != null) {
			tournamentName = (String) request.getParameter("tournamentName");
		}
		
		if (request.getParameter("signedUpFlag") != null) {
			signedUpFlag = (String) request.getParameter("signedUpFlag");
		}

		if (request.getParameter("signUpDate") != null) {
			signUpDate = CommonFunctions.parseDate((String) request.getParameter("signUpDate"));
		}

		loggedIn = (Boolean) se.getAttribute("loggedIn");
		
		if (loggedIn && clubId != 0) {
			if (signUpId == 0) {
				signUp.setClubId(clubId);
				signUp.setMemberId(memberId);
				signUp.setSignedUpFlag(signedUpFlag);
				signUp.setSignUpDate(new Date());
				signUp.setSignUpId(signUpId);
				signUp.setTournamentId(tournamentId);
				signUpBean = new SignUpTransactions().getClubMembers(clubId);
			} else {
				signUp = new SignUpTransactions().getBySignUpId(signUpId);
			}
			
			mav = new ModelAndView("signUpDetailAdminAdd");
			mav.addObject("tournamentDate", tournamentDate);
			mav.addObject("tournamentName", tournamentName);
			mav.addObject("signedUpFlag", signedUpFlag);
			mav.addObject("signUp", signUp);
			mav.addObject("members", signUpBean.getMembersByName());
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}
		
		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/signUpDetailAdminAdd", method = RequestMethod.POST)
	public ModelAndView signUpDetailAdminAddPost(HttpServletRequest request
			,@ModelAttribute("signUp") @Valid GCA_SignUp signUp
			) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		boolean success = false;
		String message = "";
		int clubId = 0;
		int memberId = 0;
		Date signUpDate = null;
		Date tournamentDate = null;
		String tournamentName = "";
		String signedUpFlag = "False";
		int tournamentId = 0;
		int signUpId = 0;
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (se.getAttribute("clubId") != null) {
			clubId = (Integer) se.getAttribute("clubId");
		}
		
		if (request.getParameter("signUpDate") != null) {
			signUpDate = CommonFunctions.parseDate(request.getParameter("signUpDate"));
		}

		if (request.getParameter("tournamentId") != null) {
			tournamentId = Integer.parseInt(request.getParameter("tournamentId"));
		}

		if (request.getParameter("signedUpFlag") != null) {
			signedUpFlag = (String) request.getParameter("signedUpFlag");
		}
		
		if (request.getParameter("tournamentDate") != null) {
			tournamentDate = CommonFunctions.parseDate(request.getParameter("tournamentDate"));
		}

		if (request.getParameter("tournamentName") != null) {
			tournamentName = (String) request.getParameter("tournamentDate");
		}

		if (request.getParameter("signUpId") != null) {
			signUpId = Integer.parseInt(request.getParameter("signUpId"));
		}

		memberId = signUp.getMemberId();
		
		signUp.setClubId(clubId);
		signUp.setMemberId(memberId);
		signUp.setSignedUpFlag(signedUpFlag);
		signUp.setSignUpDate(signUpDate);
		signUp.setSignUpId(signUpId);
		signUp.setTournamentId(tournamentId);
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");
		
		if (loggedIn && clubId != 0 && memberId != 0) {
			success = new SignUpTransactions().addUpdate(signUp);

			if (success) {
				message = "Update Successfull";
			} else {
				message = "Update Failed";
			}
			
			mav = new ModelAndView("signUpDetailAdminUpdate");
			mav.addObject("signUp", signUp);
			mav.addObject("tournamentName", tournamentName);
			mav.addObject("signedUpFlag", signedUpFlag);
			mav.addObject("tournamentDate", tournamentDate);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}
		
		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/pairings", method = RequestMethod.GET)
	public ModelAndView pairings(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";
		PairingsList pairingsList = new PairingsList();
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		pairingsList.setDateOfPlay(CommonFunctions.parseDate("2018-09-02"));
		pairingsList.addPairing(CommonFunctions.parseDateTime("2018-09-02 07:00:00"), "Howard, Moe", "Fine, Larry", "Howard, Curly", "Howard, Shemp");
		pairingsList.addPairing(CommonFunctions.parseDateTime("2018-09-02 07:08:00"), "Marx, Groucho", "Marx, Harpo", "Marx, Chico", "Marx, Zeppo");

		if (loggedIn) {
			mav = new ModelAndView("pairings");
			mav.addObject("PairingsList", pairingsList);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/tournamentsList", method = RequestMethod.GET)
	public ModelAndView tournamentsListGet(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";
		Map<Date,GCA_Tournament> map = new TreeMap<Date,GCA_Tournament>();
		int clubId = 0;
		int memberId = 0;

		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (se.getAttribute("clubId") != null) {
			clubId = (Integer) se.getAttribute("clubId");
		}
		
		if (se.getAttribute("memberId") != null) {
			memberId = (Integer) se.getAttribute("memberId");
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		if (loggedIn && clubId != 0 && memberId != 0) {
			map = new TournamentTransactions().getTournamentsByClubId(clubId);
			mav = new ModelAndView("tournamentsList");
			mav.addObject("tournaments",map);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}
		
		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/tournamentDetail", method = RequestMethod.GET)
	public ModelAndView tournamentDetailGet(HttpServletRequest request
			,@ModelAttribute("tournament") @Valid GCA_Tournament tournament
			) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";
		int tournamentId = 0;
				
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (request.getParameter("tournamentId") != null) {
			tournamentId = Integer.parseInt(request.getParameter("tournamentId"));
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		if (loggedIn) {
			tournament = new TournamentTransactions().getByTournamentId(tournamentId);
			mav = new ModelAndView("tournamentDetail");
			mav.addObject("tournament", tournament);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}
		
		request.setAttribute("message", message);

		return mav;
	}

	@RequestMapping(value = "/memberUpdate", method = RequestMethod.GET)
	public ModelAndView memberUpdateGet(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";
		GCA_Member member = new GCA_Member();
		int memberId = 0;
		int clubId = 0;
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (se.getAttribute("memberId") != null) {
			memberId = (Integer) se.getAttribute("memberId");
		}
		
		if (se.getAttribute("clubId") != null) {
			clubId = (Integer) se.getAttribute("clubId");
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		if (loggedIn && clubId != 0) {
			if (memberId == 0) {
				//add
				member.setClubId(clubId);
				member.setMemberId(memberId);

			} else {
				//update
				member = new MemberTransactions().getMemberByMemberId(memberId);
			}
			mav = new ModelAndView("memberUpdate");
			mav.addObject("GCA_Member",member);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/memberAdminUpdate", method = RequestMethod.GET)
	public ModelAndView memberAdminUpdateGet(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";
		GCA_Member member = new GCA_Member();
		int memberId = 0;
		int clubId = 0;
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (request.getParameter("memberId") != null) {
			memberId = Integer.parseInt(request.getParameter("memberId"));
		}
		
		if (se.getAttribute("clubId") != null) {
			clubId = (Integer) se.getAttribute("clubId");
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		if (loggedIn && clubId != 0) {
			if (memberId == 0) {
				//add
				member.setClubId(clubId);
				member.setMemberId(memberId);

			} else {
				//update
				member = new MemberTransactions().getMemberByMemberId(memberId);
			}
			mav = new ModelAndView("memberAdminUpdate");
			mav.addObject("GCA_Member",member);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/memberUpdate", method = RequestMethod.POST)
	public ModelAndView memberUpdatePost(HttpServletRequest request) {
	    ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		boolean success = false;
		String message = "";
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		int memberId = Integer.parseInt(request.getParameter("memberId"));
		int clubId = Integer.parseInt(request.getParameter("clubId"));
		String memberName = (String) request.getParameter("memberName");
		String memberNickName = (String) request.getParameter("memberNickName"); 
		String memberUsgaId = (String) request.getParameter("memberUsgaId"); 
		String memberPassWord = (String) request.getParameter("memberPassWord");
		String memberPassWordVerify = (String) request.getParameter("memberPassWordVerify");
		String memberPassWordOrig = (String) request.getParameter("memberPassWordOrig");
		boolean passWordVerified = memberPassWord.equals(memberPassWordVerify);
		if (passWordVerified && ! memberPassWord.equals(memberPassWordOrig)) {
			memberPassWord = CommonFunctions.encrypt(memberPassWord);
		}
		String memberLoginId = (String) request.getParameter("memberLoginId");
		GCA_Member member = new GCA_Member();
		
		member.setClubId(clubId);
		member.setMemberId(memberId);
		member.setMemberName(memberName);
		member.setMemberNickName(memberNickName);
		member.setMemberUsgaId(memberUsgaId);
		member.setMemberLoginId(memberLoginId);

		if (passWordVerified) {
			member.setMemberPassWord(memberPassWord);
		} else {
			member.setMemberPassWord(memberPassWordOrig);
		}
		
		if (loggedIn) {
			if (passWordVerified) {
				success = new MemberTransactions().updateMember(member);
				if (success) {
					mav = new ModelAndView("memberUpdate");
					mav.addObject("GCA_Member",member);
					message = "Member Update successfull";
				} else {
					mav = new ModelAndView("memberUpdate");
					mav.addObject("GCA_Member",member);
					message = "Member Update failed";
				}
			} else {
				mav = new ModelAndView("memberUpdate");
				mav.addObject("GCA_Member",member);
				message = "Passwords do not match";
			}
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/memberAdminUpdate", method = RequestMethod.POST)
	public ModelAndView memberAdminUpdatePost(HttpServletRequest request) {
	    ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		boolean success = false;
		String message = "";
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		int memberId = Integer.parseInt(request.getParameter("memberId"));
		int clubId = Integer.parseInt(request.getParameter("clubId"));
		String memberName = (String) request.getParameter("memberName");
		String memberNickName = (String) request.getParameter("memberNickName"); 
		String memberUsgaId = (String) request.getParameter("memberUsgaId"); 
		String memberPassWord = (String) request.getParameter("memberPassWord");
		String memberPassWordOrig = (String) request.getParameter("memberPassWordOrig");
		String memberPassWordVerify = (String) request.getParameter("memberPassWordVerify");
		boolean passWordVerified = memberPassWord.equals(memberPassWordVerify);
		if (passWordVerified && ! memberPassWord.equals(memberPassWordOrig)) {
			memberPassWord = CommonFunctions.encrypt(memberPassWord);
		}
		String memberLoginId = (String) request.getParameter("memberLoginId");
		GCA_Member member = new GCA_Member();
		
		member.setClubId(clubId);
		member.setMemberId(memberId);
		member.setMemberName(memberName);
		member.setMemberNickName(memberNickName);
		member.setMemberUsgaId(memberUsgaId);
		member.setMemberLoginId(memberLoginId);
		if (passWordVerified) {
			member.setMemberPassWord(memberPassWord);
		} else {
			member.setMemberPassWord(memberPassWordOrig);
		}
		
		if (loggedIn) {
			if (passWordVerified) {
				if (memberId == 0) {
					success = new MemberTransactions().addMember(member);
				} else {
					success = new MemberTransactions().updateMember(member);
				}
				if (success) {
					mav = new ModelAndView("memberAdminUpdate");
					mav.addObject("GCA_Member",member);
					message = "Member Update successfull";
				} else {
					mav = new ModelAndView("memberAdminUpdate");
					mav.addObject("GCA_Member",member);
					message = "Member Update failed";
				}
			} else {
				mav = new ModelAndView("memberAdminUpdate");
				mav.addObject("GCA_Member",member);
				message = "Passwords do not match";
			}
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/memberAdminDelete", method = RequestMethod.POST)
	public ModelAndView memberAdminDeletePost(HttpServletRequest request) {
	    ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		boolean success = false;
		String message = "";
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		int memberId = Integer.parseInt(request.getParameter("memberId"));
		int clubId = Integer.parseInt(request.getParameter("clubId"));
		String memberName = (String) request.getParameter("memberName");
		String memberNickName = (String) request.getParameter("memberNickName"); 
		String memberUsgaId = (String) request.getParameter("memberUsgaId"); 
		String memberPassWord = (String) request.getParameter("memberPassWord");
		String memberLoginId = (String) request.getParameter("memberLoginId");
		GCA_Member member = new GCA_Member();
		
		member.setClubId(clubId);
		member.setMemberId(memberId);
		member.setMemberName(memberName);
		member.setMemberNickName(memberNickName);
		member.setMemberUsgaId(memberUsgaId);
		member.setMemberLoginId(memberLoginId);
		member.setMemberPassWord(memberPassWord);
		
		if (loggedIn) {
			if (memberId != 0) {
				success = new MemberTransactions().deleteMember(member);
				if (success) {
					//mav = new ModelAndView("membersAdminList");
					mav = new ModelAndView("home");
					message = "Member Delete successfull";
				} else {
					mav = new ModelAndView("memberAdminUpdate");
					mav.addObject("GCA_Member",member);
					message = "Member Delete failed";
				}
			} else {
				mav = new ModelAndView("memberAdminUpdate");
				mav.addObject("GCA_Member",member);
				message = "Member does not exists - Delete failed";
			}
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/enrollClub", method = RequestMethod.GET)
	public ModelAndView enrollClubGet(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";

		if (se.getAttribute("loggedIn") != null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		mav = new ModelAndView("enrollClub");
		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/enrollClub", method = RequestMethod.POST)
	public ModelAndView enrollClubPost(HttpServletRequest request 
//			,@ModelAttribute("enrollForm") @Valid EnrollForm enrollForm,
//			BindingResult result
			) {
		
		ModelAndView mav = new ModelAndView("enrollClub");
		HttpSession se = request.getSession();
		ClubTransactions clubTransactions = new ClubTransactions();
		LoginBean loginBean = new LoginBean();
		GCA_Club club = new GCA_Club();
		GCA_Member member = new GCA_Member();
		boolean loggedIn = false;
		String clubName = request.getParameter("clubName");
		String clubUsgaId = request.getParameter("clubUsgaId");
		String clubHomeCourseName = request.getParameter("clubHomeCourseName");
		String clubCity = request.getParameter("clubCity");
		String clubState = request.getParameter("clubState");
		String memberName = request.getParameter("memberName");
		String memberNickName = request.getParameter("memberNickName");
		String memberLoginId = request.getParameter("memberLoginId");
		String memberUsgaId = request.getParameter("memberUsgaId");
		String memberPassWord = request.getParameter("memberPassWord");
		String memberPassWordVerify = request.getParameter("memberPassWordVerify");
		String memberPassWordOrig = request.getParameter("memberPassWordOrig");
		boolean passWordVerified = memberPassWord.equals(memberPassWordVerify); 
		if (passWordVerified && ! memberPassWord.equals(memberPassWordOrig)) {
			memberPassWord = CommonFunctions.encrypt(memberPassWord);
		}
		String message = "";
		boolean success = false;
		
		club.setClubName(clubName);
		club.setClubUsgaId(clubUsgaId);
		club.setClubHomeCourseName(clubHomeCourseName);
		club.setClubCity(clubCity);
		club.setClubState(clubState);

		member.setMemberName(memberName);
		member.setMemberNickName(memberNickName);
		member.setMemberUsgaId(memberUsgaId);
		member.setMemberLoginId(memberLoginId);
		if (passWordVerified) member.setMemberPassWord(memberPassWord);

		loginBean = clubTransactions.addClubAndAdministrator(club, member);
		
		if (loginBean.isLoggedIn() &&  passWordVerified) {
			loggedIn = true;
			message = "Enrollment sucessfull";
			mav = new ModelAndView("home");
		} else {
			loggedIn = false;
			message = "Enrollment failed";
			mav = new ModelAndView("enrollClub");
			mav.addObject("clubName", clubName);
			mav.addObject("clubUsgaId", clubUsgaId);
			mav.addObject("clubHomeCourseName", clubHomeCourseName);
			mav.addObject("clubCity", clubCity);
			mav.addObject("clubState", clubState);
			mav.addObject("memberName", memberName);
			mav.addObject("memberNickName", memberNickName);
			mav.addObject("memberLoginId", memberLoginId);
			mav.addObject("memberUsgaId", memberUsgaId);
			mav.addObject("memberPassword", memberPassWord);
		}

		request.setAttribute("message", message);
		se.setAttribute("clubName", clubName);
		se.setAttribute("clubId", loginBean.getClub().getClubId());
		se.setAttribute("memberLoginId", memberLoginId);
		se.setAttribute("memberId", loginBean.getMember().getMemberId());
		se.setAttribute("memberClubs", loginBean.getMemberClubs());
		se.setAttribute("loggedIn", loggedIn);

		return mav;
	}
	
	@RequestMapping(value = "/handicaps", method = RequestMethod.GET)
	public ModelAndView handicapsGet(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";
		int clubId = (Integer) se.getAttribute("clubId");
		Handicaps handicaps = new HandicapTransactions().getHandicapsByClubId(clubId);
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		if (loggedIn) {
			mav = new ModelAndView("handicaps");
			mav.addObject("handicaps", handicaps);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/handicaps", method = RequestMethod.POST)
	public ModelAndView handicapsPost(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		if (loggedIn) { 	
			message = "Scores update successfull";
			mav = new ModelAndView("handicaps");
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}

	@RequestMapping(value = "/pairingsAdmin", method = RequestMethod.GET)
	public ModelAndView pairingsAdmin(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";
		PairingsList pairingsList = new PairingsList();
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		pairingsList.setDateOfPlay(CommonFunctions.parseDate("2018-09-02"));
		pairingsList.addPairing(CommonFunctions.parseDateTime("2018-09-02 07:00:00"), "Howard, Moe", "Fine, Larry", "Howard, Curly", "Howard, Shemp");
		pairingsList.addPairing(CommonFunctions.parseDateTime("2018-09-02 07:08:00"), "Marx, Groucho", "Marx, Harpo", "Marx, Chico", "Marx, Zeppo");

		if (loggedIn) {
			mav = new ModelAndView("pairingsAdmin");
			mav.addObject("PairingsList", pairingsList);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/tournamentAdminList", method = RequestMethod.GET)
	public ModelAndView tournamentAdminListGet(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";
		Map<Date,GCA_Tournament> map = new TreeMap<Date,GCA_Tournament>();
		int clubId = 0;

		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (se.getAttribute("clubId") != null) {
			clubId = (Integer) se.getAttribute("clubId");
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		if (loggedIn && clubId != 0) {
			map = new TournamentTransactions().getTournamentsByClubId(clubId);
			mav = new ModelAndView("tournamentAdminList");
			mav.addObject("tournaments",map);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}
		
		request.setAttribute("message", message);

		return mav;
	}

	@RequestMapping(value = "/tournamentAdminUpdate", method = RequestMethod.GET)
	public ModelAndView tournamentAdminUpdateGet(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";
		int tournamentId = 0;
		GCA_Tournament tournament = new GCA_Tournament();
				
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (request.getParameter("tournamentId") != null) {
			tournamentId = Integer.parseInt(request.getParameter("tournamentId"));
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		if (loggedIn) {
			tournament = new TournamentTransactions().getByTournamentId(tournamentId);
			mav = new ModelAndView("tournamentAdminUpdate");
			mav.addObject("tournament", tournament);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}
		
		request.setAttribute("message", message);

		return mav;
	}

	@RequestMapping(value = "/tournamentAdminUpdate", method = RequestMethod.POST)
	public ModelAndView tournamentAdminUpdatePost(
			HttpServletRequest request,
			@ModelAttribute("tournament") @Valid GCA_Tournament tournament
			) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";
		boolean success = false;
		//GCA_Tournament tournament = new GCA_Tournament();
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
//		if (request.getParameter("tournament") != null) {
//			tournament = (GCA_Tournament) request.getParameter("tournament");
//		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		if (loggedIn) {
			success = new TournamentTransactions().addUpdate(tournament);
			
			if (success) {
				message = "Update Successfull";
			} else {
				message = "Update Failed";
			}
			mav = new ModelAndView("tournamentAdminUpdate");
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}
		
		request.setAttribute("message", message);

		return mav;
	}

	@RequestMapping(value = "/membersAdminList", method = RequestMethod.GET)
	public ModelAndView membersAdminListGet(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		int clubId = 0;
		String message = "";
		Map<String,GCA_Member> members = new TreeMap<String,GCA_Member>();

		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (se.getAttribute("clubId") != null) {
			clubId = (Integer) se.getAttribute("clubId");
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		if (loggedIn && clubId != 0) {
			members = new MemberTransactions().getMembersListByClubIdKeyMemberName(clubId);
			mav = new ModelAndView("membersAdminList");
			mav.addObject("members",members);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}
	
//	@RequestMapping(value = "/membersAdmin", method = RequestMethod.POST)
//	public ModelAndView membersAdminPost(HttpServletRequest request) {
//	    ModelAndView mav;
//		HttpSession se = request.getSession();
//		boolean loggedIn = false;
//		String message = "";
//
//		if (se.getAttribute("loggedIn") == null) {
//			se.setAttribute("loggedIn", loggedIn);
//		}
//		
//		loggedIn = (Boolean) se.getAttribute("loggedIn");
//
//		int memberId = Integer.parseInt(request.getParameter("memberId"));
//		int clubId = Integer.parseInt(request.getParameter("clubId"));
//		String memberName = (String) request.getParameter("memberName");
//		String memberNickName = (String) request.getParameter("memberNickName"); 
//		String memberUsgaId = (String) request.getParameter("memberUsgaId"); 
//		String memberPassWord = (String) request.getParameter("memberPassWord");
//		String memberPassWordVerify = (String) request.getParameter("memberPassWordVerify");
//		String memberPassWordOrig = (String) request.getParameter("memberPassWordOrig");
//		boolean passWordVerified = memberPassWord.equals(memberPassWordVerify);
//		if (passWordVerified && ! memberPassWord.equals(memberPassWordOrig)) {
//			memberPassWord = CommonFunctions.encrypt(memberPassWord);
//		}
//		GCA_Member member = new GCA_Member();
//		
//		member.setClubId(clubId);
//		member.setMemberId(memberId);
//		member.setMemberName(memberName);
//		member.setMemberNickName(memberNickName);
//		member.setMemberUsgaId(memberUsgaId);
//		if (passWordVerified) member.setMemberPassWord(memberPassWord);
//		
//		if (loggedIn) {
//			if (memberName.equals("") || ! passWordVerified) {
//				mav = new ModelAndView("membersAdmin");
//				mav.addObject("GCA_Member",member);
//				message = "Member Update failed";
//			} else {
//				mav = new ModelAndView("home");
//				message = "Member Update successfull";
//			}
//		} else {
//			message = "Not logged In";
//			mav = new ModelAndView("welcome");
//		}
//
//		request.setAttribute("message", message);
//
//		return mav;
//	}
	
	@RequestMapping(value = "/scores", method = RequestMethod.GET)
	public ModelAndView scoresGet(HttpServletRequest request
		,@ModelAttribute("scoresBean") @Valid ScoresBean scoresBean
		) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";
		int clubId = 0;
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (se.getAttribute("clubId") == null) {
			se.setAttribute("clubId", clubId);
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");
		clubId = (Integer) se.getAttribute("clubId");
		
		scoresBean = new ScoreTransactions().getBlankScoreCard(clubId);
		
		if (loggedIn) {
			mav = new ModelAndView("scores");
			mav.addObject("scoresBean", scoresBean);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/scores", method = RequestMethod.POST)
	public ModelAndView scoresPost(HttpServletRequest request
			,@ModelAttribute("scoresBean") @Valid ScoresBean scoresBean
			) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		int tournamentId = 0;
		int clubId = 0;
		Map<String,GCA_Member> members = new TreeMap<String,GCA_Member>();
		Map<String,GCA_Course> courses = new TreeMap<String,GCA_Course>();
		Map<Integer,Map<String, GCA_CourseTee>> courseTees = new TreeMap<Integer,Map<String, GCA_CourseTee>>();
		
		String message = "";
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (se.getAttribute("clubId") != null) {
			clubId = (Integer) se.getAttribute("clubId");
		}
		
		if (request.getAttribute("members") != null ) {
			members = (Map<String,GCA_Member>) request.getAttribute("members");
		}
		if (request.getAttribute("courses") != null ) {
			courses = (Map<String,GCA_Course>) request.getAttribute("courses");
		}
		if (request.getAttribute("courseTees") != null ) {
			courseTees = (Map<Integer,Map<String, GCA_CourseTee>>) request.getAttribute("courseTees");
		}
		if (request.getAttribute("tournamentId") != null ) {
			tournamentId = (Integer) request.getAttribute("tournamentId");
		}

		ScoresBean scoresBean2 = new ScoreTransactions().getBlankScoreCard(clubId);
		scoresBean.setMembers(scoresBean2.getMembers());
		scoresBean.setCourses(scoresBean2.getCourses());
		scoresBean.setCourseTees(scoresBean2.getCourseTees());
		scoresBean.setTournamentId(scoresBean2.getTournamentId());

		loggedIn = (Boolean) se.getAttribute("loggedIn");

		if (loggedIn) { 	
			boolean okay = false;
			
			if (clubId == 0) {
				message = "Missing clubId";
			} else if (scoresBean.getCourseId() == 0) {
				message = "Missing courseId";
			} else if (scoresBean.getCourseTeeId() == 0) {
				message = "Missing courseTeeId";
			} else {
				okay = new ScoreTransactions().postScoreCard(scoresBean);
				if (okay) {
					message = "Scores update successfull";
				} else {
					message = "Scores update failed";
				}
			}
			
			mav = new ModelAndView("scores");
			mav.addObject("scoresBean", scoresBean);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/scoresAdmin", method = RequestMethod.GET)
	public ModelAndView scoresAdminGet(HttpServletRequest request
		,@ModelAttribute("scoresBean") @Valid ScoresBean scoresBean
		) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";
		int clubId = 0;
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (se.getAttribute("clubId") == null) {
			se.setAttribute("clubId", clubId);
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");
		clubId = (Integer) se.getAttribute("clubId");
		
		scoresBean = new ScoreTransactions().getBlankScoreCard(clubId);
		
		if (loggedIn) {
			mav = new ModelAndView("scoresAdmin");
			mav.addObject("scoresBean", scoresBean);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/scoresAdmin", method = RequestMethod.POST)
	public ModelAndView scoresAdminPost(HttpServletRequest request
			,@ModelAttribute("scoresBean") @Valid ScoresBean scoresBean
			) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		int tournamentId = 0;
		int clubId = 0;
		Map<String,GCA_Member> members = new TreeMap<String,GCA_Member>();
		Map<String,GCA_Course> courses = new TreeMap<String,GCA_Course>();
		Map<Integer,Map<String, GCA_CourseTee>> courseTees = new TreeMap<Integer,Map<String, GCA_CourseTee>>();
		
		String message = "";
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (se.getAttribute("clubId") != null) {
			clubId = (Integer) se.getAttribute("clubId");
		}
		
		if (request.getAttribute("members") != null ) {
			members = (Map<String,GCA_Member>) request.getAttribute("members");
		}
		if (request.getAttribute("courses") != null ) {
			courses = (Map<String,GCA_Course>) request.getAttribute("courses");
		}
		if (request.getAttribute("courseTees") != null ) {
			courseTees = (Map<Integer,Map<String, GCA_CourseTee>>) request.getAttribute("courseTees");
		}
		if (request.getAttribute("tournamentId") != null ) {
			tournamentId = (Integer) request.getAttribute("tournamentId");
		}

		ScoresBean scoresBean2 = new ScoreTransactions().getBlankScoreCard(clubId);
		scoresBean.setMembers(scoresBean2.getMembers());
		scoresBean.setCourses(scoresBean2.getCourses());
		scoresBean.setCourseTees(scoresBean2.getCourseTees());
		scoresBean.setTournamentId(scoresBean2.getTournamentId());

		loggedIn = (Boolean) se.getAttribute("loggedIn");

		if (loggedIn) { 	
			boolean okay = false;
			
			if (clubId == 0) {
				message = "Missing clubId";
			} else if (scoresBean.getCourseId() == 0) {
				message = "Missing courseId";
			} else if (scoresBean.getCourseTeeId() == 0) {
				message = "Missing courseTeeId";
			} else {
				okay = new ScoreTransactions().postScoreCard(scoresBean);
				if (okay) {
					message = "Scores update successfull";
				} else {
					message = "Scores update failed";
				}
			}
			
			mav = new ModelAndView("scoresAdmin");
			mav.addObject("scoresBean", scoresBean);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/scoresAdminDetail", method = RequestMethod.GET)
	public ModelAndView scoresAdminDetailGet(HttpServletRequest request
			) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		int scoreId = 0;
		ScoresBean scoresBean = new ScoresBean();
		String message = "";
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (request.getAttribute("scoreId") != null ) {
			scoreId = (Integer) request.getAttribute("scoreId");
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		if (loggedIn) { 	
			boolean okay = false;
			
			if (scoreId == 0) {
				message = "Not found";
			} else {
				scoresBean = new ScoreTransactions().getScore(scoreId);
			}
			
			mav = new ModelAndView("scoresAdminDetail");
			mav.addObject("scoresBean", scoresBean);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/scoresAdminDelete", method = RequestMethod.POST)
	public ModelAndView scoresAdminPost(HttpServletRequest request
			) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		int scoreId = 0;
		String message = "";
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		if (request.getAttribute("scoreId") != null ) {
			scoreId = (Integer) request.getAttribute("scoreId");
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		if (loggedIn) { 	
			boolean okay = false;
			
			if (scoreId == 0) {
				message = "Nothing to delete";
			} else {
				okay = new ScoreTransactions().deleteScore(scoreId);
				if (okay) {
					message = "Score was successfully deleted";
				} else {
					message = "Score delete failed";
				}
			}
			
			mav = new ModelAndView("scoresAdmin");
//			mav.addObject("scoresBean", scoresBean);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/handicapsAdmin", method = RequestMethod.GET)
	public ModelAndView handicapsAdminGet(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";

		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		int clubId = (Integer) se.getAttribute("clubId");
		Handicaps handicaps = new HandicapTransactions().getHandicapsByClubId(clubId);
		
		if (loggedIn) {
			mav = new ModelAndView("handicapsAdmin");
			mav.addObject("handicaps", handicaps);
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}
	
	@RequestMapping(value = "/handicapsAdmin", method = RequestMethod.POST)
	public ModelAndView handicapsAdminPost(HttpServletRequest request) {
		ModelAndView mav;
		HttpSession se = request.getSession();
		boolean loggedIn = false;
		String message = "";
		
		if (se.getAttribute("loggedIn") == null) {
			se.setAttribute("loggedIn", loggedIn);
		}
		
		loggedIn = (Boolean) se.getAttribute("loggedIn");

		if (loggedIn) { 	
			message = "Scores update successfull";
			mav = new ModelAndView("handicapsAdmin");
		} else {
			message = "Not logged In";
			mav = new ModelAndView("welcome");
		}

		request.setAttribute("message", message);

		return mav;
	}

//--------------------------------------------------------------------------------------------------
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
//		binder.setDisallowedFields(new String[] {	"name"
//													,"address"
//												});

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@ModelAttribute("enrollForm")
	public EnrollForm createEnrollForm() {
		return new EnrollForm();
	}

	@ModelAttribute("GCA_Tournament")
	public GCA_Tournament createGCATournament() {
		return new GCA_Tournament();
	}

	@ModelAttribute("tournament")
	public GCA_Tournament createTournament() {
		return new GCA_Tournament();
	}

	@ModelAttribute("signUp")
	public GCA_SignUp createSignUp() {
		return new GCA_SignUp();
	}

	@ModelAttribute("signUpBean")
	public SignUpBean createSignUpBean() {
		return new SignUpBean();
	}

	@ModelAttribute("scoresBean")
	public ScoresBean createScoresBean() {
		return new ScoresBean();
	}

	
}