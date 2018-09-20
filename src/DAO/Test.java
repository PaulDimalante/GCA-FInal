package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map;

import BusinessLogic.MemberTransactions;
import UserInterface.MemberUI;
import models.GCA_Member;

public class Test {

	public static void main(String[] args) {
		MemberTransactions dbg = new MemberTransactions();
		dbg.verifyMember("Dimalante, Paul", "password");
	}
}
