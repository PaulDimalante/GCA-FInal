package springwork.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import commonFunctions.CommonFunctions;

public class PairingsList {
	private Date dateOfPlay;
	private Map<Date,Pairing> pairingsList = new TreeMap<Date,Pairing>();

	public Map<Date, Pairing> getPairingsList() {
		return pairingsList;
	}

	public void setPairingsList(Map<Date, Pairing> pairingsList) {
		this.pairingsList = pairingsList;
	}
	
	public Date getDateOfPlay() {
		return dateOfPlay;
	}

	public void setDateOfPlay(Date dateOfPlay) {
		this.dateOfPlay = dateOfPlay;
	}

	public String getDateOfPlayFormatted() {
		return CommonFunctions.formatDate(this.dateOfPlay);
	}

	public void addPairing(Date teeTime, String player1, String player2, String player3, String player4) {
		this.pairingsList.put(teeTime, new Pairing(teeTime, player1, player2, player3, player4));
	}
}
