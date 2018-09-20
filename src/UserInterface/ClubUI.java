package UserInterface;

import java.util.Scanner;

import models.GCA_Club;

public class ClubUI {
	public GCA_Club promptClub(Scanner scanner) {
		GCA_Club club = new GCA_Club();
		
		System.out.printf("Enter Club Name, Home Course, City, State, USGA ID%n");
		
		club.setClubName(scanner.nextLine());
		club.setClubHomeCourseName(scanner.nextLine());
		club.setClubCity(scanner.nextLine());
		club.setClubState(scanner.nextLine());
		club.setClubUsgaId(scanner.nextLine());
		
		return club;
	}
}
