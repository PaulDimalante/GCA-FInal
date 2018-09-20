package UserInterface;

import java.util.Scanner;

import models.GCA_Member;

public class MemberUI {
	public GCA_Member promptMember(Scanner scanner, int clubId) {
		GCA_Member member = new GCA_Member();

		System.out.printf("Enter Member's Name, Nick Name, USGA ID, Password%n");
		member.setClubId(clubId);
		member.setMemberName(scanner.nextLine());
		member.setMemberNickName(scanner.nextLine());
		member.setMemberUsgaId(scanner.nextLine());
		member.setMemberPassWord(scanner.nextLine());

		return member;
	}
}
