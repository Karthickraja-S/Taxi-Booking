package com.taxibooking.model;

public class History {
		private int taxi_id;
		private String custName;
		private String fromLocation;
		private String toLocation;
		private int pickUpTime;
		private int dropTime;
		private int amount;
		private String date;
		
		public String getDate() {
			return date;
		}


		public void setDate(String date) {
			this.date = date;
		}


		public History(String date, String fromLocation, String toLocation, int amount,int taxi_id ) {
			this.taxi_id = taxi_id;
			this.fromLocation = fromLocation;
			this.toLocation = toLocation;
			this.amount = amount;
			this.date = date;
		}


		public History(String date , int taxi_id, String custName, String fromLocation, String toLocation, int pickUpTime, int dropTime,
				int amount) {
			this.date=date;
			this.taxi_id = taxi_id;
			this.custName = custName;
			this.fromLocation = fromLocation;
			this.toLocation = toLocation;
			this.pickUpTime = pickUpTime;
			this.dropTime = dropTime;
			this.amount = amount;
		}
		

		@Override
		public String toString() {
			return "History [taxi_id=" + taxi_id + ", custName=" + custName + ", fromLocation=" + fromLocation
					+ ", toLocation=" + toLocation + ", pickUpTime=" + pickUpTime + ", dropTime=" + dropTime
					+ ", amount=" + amount + ", date=" + date + "]";
		}


		public int getTaxi_id() {
			return taxi_id;
		}
		public void setTaxi_id(int taxi_id) {
			this.taxi_id = taxi_id;
		}
		public String getcustName() {
			return custName;
		}
		public void setcustName(String custName) {
			this.custName = custName;
		}
		public String getFromLocation() {
			return fromLocation;
		}
		public void setFromLocation(String fromLocation) {
			this.fromLocation = fromLocation;
		}
		public String getToLocation() {
			return toLocation;
		}
		public void setToLocation(String toLocation) {
			this.toLocation = toLocation;
		}
		public int getPickUpTime() {
			return pickUpTime;
		}
		public void setPickUpTime(int pickUpTime) {
			this.pickUpTime = pickUpTime;
		}
		public int getDropTime() {
			return dropTime;
		}
		public void setDropTime(int dropTime) {
			this.dropTime = dropTime;
		}
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}
		
		
}
