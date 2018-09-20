package springwork.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EnrollForm {
//	@NotNull
//	@Size(min = 5, max = 30, message = "cannot be blank")
	private String clubName;
	
	private String clubUsgaId;
	private String clubHomeCourseName;
	private String clubCity;
	private String clubState;

//	@NotNull
//	@Size(min = 1, message = "cannot be blank")
	private String memberName;

//	@NotNull
	private String memberNickName;

//	@NotNull
//	@Size(min = 1, message = "cannot be blank")
	private String memberLoginId;

//	@NotNull
	private String memberUsgaId;

//	@NotNull
//	@Size(min = 1, message = "cannot be blank")
	private String memberPassWord;

//	@NotNull
	//@PassWordVerifyValidator
	private String memberPassWordVerify;

	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public String getClubUsgaId() {
		return clubUsgaId;
	}
	public void setClubUsgaId(String clubUsgaId) {
		this.clubUsgaId = clubUsgaId;
	}
	public String getClubHomeCourseName() {
		return clubHomeCourseName;
	}
	public void setClubHomeCourseName(String clubHomeCourseName) {
		this.clubHomeCourseName = clubHomeCourseName;
	}
	public String getClubCity() {
		return clubCity;
	}
	public void setClubCity(String clubCity) {
		this.clubCity = clubCity;
	}
	public String getClubState() {
		return clubState;
	}
	public void setClubState(String clubState) {
		this.clubState = clubState;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberNickName() {
		return memberNickName;
	}
	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}
	public String getMemberLoginId() {
		return memberLoginId;
	}
	public void setMemberLoginId(String memberLoginId) {
		this.memberLoginId = memberLoginId;
	}
	public String getMemberUsgaId() {
		return memberUsgaId;
	}
	public void setMemberUsgaId(String memberUsgaId) {
		this.memberUsgaId = memberUsgaId;
	}
	public String getMemberPassWord() {
		return memberPassWord;
	}
	public void setMemberPassWord(String memberPassWord) {
		this.memberPassWord = memberPassWord;
	}
	public String getMemberPassWordVerify() {
		return memberPassWordVerify;
	}
	public void setMemberPassWordVerify(String memberPassWordVerify) {
		this.memberPassWordVerify = memberPassWordVerify;
	}

}
