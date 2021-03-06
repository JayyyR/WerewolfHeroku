package edu.wm.werewolf.domain;

import java.util.Date;

public class Player {

	private String id;
	private boolean isDead;
	private double lat;
	private double lng;
	private String userId;
	private boolean isWerewolf;
	private int votes;
	private boolean admin = false;
	private boolean hasVoted = false;
	private boolean killedLastNight = false;
	private Date createdDate;
	
	public Player(){
		
	}
	
	public Date getCreatedDate(){
		return createdDate;
	}
	
	public boolean getKilledLastNight(){
		return killedLastNight;
	}
	
	public void switchKilledLastNight(){
		killedLastNight = !killedLastNight;
	}
	
	public boolean getHasVoted(){
		return hasVoted;
	}
	
	public void switchHasVoted(){
		hasVoted = !hasVoted;
	}
	
	public int getVotes() {
		return votes;
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public void addVote() {
		votes+=1;
	}

	public Player(String id, boolean isDead, double lat,
			double lng, String userId, boolean isWerewolf, int votes, boolean hasVoted, boolean isadmin, boolean killedLast, Date createdDate) {
		super();
		this.id = id;
		this.isDead = isDead;
		this.lat = lat;
		this.lng = lng;
		this.userId = userId;
		this.isWerewolf = isWerewolf;
		this.votes = votes;
		this.hasVoted =hasVoted;
		admin = isadmin;
		killedLastNight=killedLast;
		this.createdDate=createdDate;
	}

	public boolean isWerewolf() {
		return isWerewolf;
	}

	public void setWerewolf(boolean isWerewolf) {
		this.isWerewolf = isWerewolf;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
