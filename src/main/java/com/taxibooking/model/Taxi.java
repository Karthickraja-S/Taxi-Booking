package com.taxibooking.model;

public class Taxi {
	private int id;
    private char currentSpot; //where taxi is now
    private int freeTime; // when taxi becomes free
    private int totalEarnings; // total earnings of taxi
    private char OriginalSpot;
    
    
	public Taxi(int id, char currentSpot, int freeTime, int totalEarnings) {
		this.id = id;
		this.currentSpot = currentSpot;
		this.freeTime = freeTime;
		this.totalEarnings = totalEarnings;
	}
	
	
	public Taxi(int id, char currentSpot, char OriginalSpot) {
		this.id = id;
		this.currentSpot = currentSpot;
		this.OriginalSpot = OriginalSpot;
	}


	public char getOriginalSpot() {
		return OriginalSpot;
	}


	public void setOriginalSpot(char originalSpot) {
		OriginalSpot = originalSpot;
	}


	@Override
	public String toString() {
		return "Taxi [id=" + id + ", currentSpot=" + currentSpot + ", freeTime=" + freeTime + ", totalEarnings="
				+ totalEarnings + "]";
	}
	 
	public Taxi(int id , int totalEarnings)
	{
		this.id = id;
		this.totalEarnings = totalEarnings;
	}

	public Taxi(int id, String OriginalSpot) {
		this.id=id;
		this.OriginalSpot = OriginalSpot.charAt(0);
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getCurrentSpot() {
		return currentSpot;
	}
	public void setCurrentSpot(char currentSpot) {
		this.currentSpot = currentSpot;
	}
	public int getFreeTime() {
		return freeTime;
	}
	public void setFreeTime(int freeTime) {
		this.freeTime = freeTime;
	}
	public int getTotalEarnings() {
		return totalEarnings;
	}
	public void setTotalEarnings(int totalEarnings) {
		this.totalEarnings = totalEarnings;
	}
}
