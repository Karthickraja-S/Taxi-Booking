package com.taxibooking.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import javax.sql.DataSource;


import com.taxibooking.model.Taxi;
import com.taxibooking.model.Customer;
import com.taxibooking.model.History;

public class TaxiDatabaseUtil {
	private DataSource dataSource;
	
	public TaxiDatabaseUtil(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	public void addCustomer(Customer cust) throws Exception
	{
		Connection mycon=null;
		PreparedStatement myst = null;
		
		try {
			mycon = dataSource.getConnection();
			String query = "insert into customer(userName , emailId , password) values(?,?,?)";
			
			myst = mycon.prepareStatement(query);
			
			myst.setString(1, cust.getUserName());
			myst.setString(2, cust.getEmailId());
			myst.setString(3, cust.getPassword());
			
			myst.execute();
			
		}
		catch(Exception e) {
			throw new Exception();
		}
		finally
		{
			close(mycon,myst,null);
		}
	}
	public Customer getCustomer(String uname , String psw) 
	{
		
		Connection mycon=null;
		PreparedStatement myst = null;
		ResultSet rs = null;
		
		try {
			mycon = dataSource.getConnection();
			String query = "select * from customer where username = ? and password = ?";
			
			myst = mycon.prepareStatement(query);
			
			myst.setString(1, uname);
			myst.setString(2, psw);
			
	// worked : 	
			System.out.println(query+" "+myst.toString());
			 rs =  myst.executeQuery();
			 if(rs.next())
			 {
				 Customer cust = new Customer(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				 return cust;
			 }	
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally
		{
			close(mycon,myst,rs);
		}
		return null;
	}

	public void updateTime(Customer logged, String time) {
		Connection mycon=null;
		PreparedStatement myst = null;

		
		try {
			mycon = dataSource.getConnection();
			String query = "update customer set lastAccessTime = ? where emailId = ?";
			
			myst = mycon.prepareStatement(query);
			myst.setString(1, time);
			myst.setString(2, logged.getEmailId());
			
	// worked : 	
			System.out.println(query+" "+myst.toString());
			myst.execute();

			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally
		{
			close(mycon,myst,null);
		}
		
	}

	public List<Taxi> getTaxiEarnings() {
		Connection mycon=null;
		PreparedStatement myst = null;
		ResultSet rs = null;
		
		try {
			mycon = dataSource.getConnection();
			String query = "select taxi_id,totalEarnings from taxi";
			
			myst = mycon.prepareStatement(query);
			
			List<Taxi> taxiDetails= new ArrayList<>();
			rs = myst.executeQuery();
			while(rs.next())
			{
				//System.out.println(rs.getString(1)+" "+rs.getString(2));
				taxiDetails.add(new Taxi(Integer.parseInt(rs.getString(1)),Integer.parseInt(rs.getString(2))));
			}
			return taxiDetails;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally
		{
			close(mycon,myst,rs);
		}
		return null;
	}


	public List<History> getBookedHistory() {
		Connection mycon=null;
		PreparedStatement myst = null;
		ResultSet rs = null;
		
		try {
			mycon = dataSource.getConnection();
			String query = "select * from history";
			
			myst = mycon.prepareStatement(query);
			
			List<History> taxiHist= new ArrayList<>();
			rs = myst.executeQuery();
			while(rs.next())
			{
				//System.out.println(rs.getString(1)+" "+rs.getString(2));
				taxiHist.add(new History(rs.getString(8),Integer.parseInt(rs.getString(1)),rs.getString(2) , 
						rs.getString(3),rs.getString(4),
						Integer.parseInt(rs.getString(5)),
						Integer.parseInt(rs.getString(6)),Integer.parseInt(rs.getString(7))));
			}
			return taxiHist;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally
		{
			close(mycon,myst,rs);
		}
		return null;
	}

	public List<Taxi> getTaxi() {
		Connection mycon=null;
		PreparedStatement myst = null;
		ResultSet rs = null;
		
		try {
			mycon = dataSource.getConnection();
			String query = "select * from taxi";
			
			myst = mycon.prepareStatement(query);
			
			List<Taxi> taxiDetails= new ArrayList<>();
			rs = myst.executeQuery();
			while(rs.next())
			{
			//	System.out.println(rs.getString(1)+" "+rs.getString(2).charAt(0)+" "+rs.getString(3)+" "+rs.getString(4));
				taxiDetails.add(new Taxi(Integer.parseInt(rs.getString(1)),rs.getString(2).charAt(0),
						Integer.parseInt(rs.getString(3)),Integer.parseInt(rs.getString(4))));
			}
			return taxiDetails;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally
		{
			close(mycon,myst,rs);
		}
		return null;
	}

	public void insertHistory(History taxiBooked) throws Exception {
		
		Connection mycon=null;
		PreparedStatement myst = null;
		
		try {
			mycon = dataSource.getConnection();
			String query = "insert into history values(?,?,?,?,?,?,?,?)";
			
			myst = mycon.prepareStatement(query);
			
			myst.setInt(1, taxiBooked.getTaxi_id());
			myst.setString(2, taxiBooked.getcustName());
			myst.setString(3, taxiBooked.getFromLocation());
			myst.setString(4, taxiBooked.getToLocation());
			myst.setInt(5, taxiBooked.getPickUpTime());
			myst.setInt(6, taxiBooked.getDropTime());
			myst.setInt(7, taxiBooked.getAmount());
			myst.setString(8, taxiBooked.getDate());
			
			myst.execute();
			
		}
		catch(Exception e) {
			throw new Exception();
		}
		finally
		{
			close(mycon,myst,null);
		}
		
	}

	public void updateTaxiData(int id,String dropPoint, int amount,int dropTime) {
		Connection mycon=null;
		PreparedStatement myst = null;

		
		try {
			mycon = dataSource.getConnection();
			String query = "update taxi set totalEarnings = totalEarnings+?,currentSpot=?,freeTime=? where taxi_id = ?";
			
			myst = mycon.prepareStatement(query);
			myst.setInt(1, amount);
			myst.setString(2, dropPoint);
			myst.setInt(3, dropTime);
			myst.setInt(4, id);
			
	// worked : 	
			System.out.println(query+" "+myst.toString());
			myst.execute();

			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally
		{
			close(mycon,myst,null);
		}
		
	}
	
	public void resetTaxiData() {
		Connection mycon=null;
		PreparedStatement myst = null;

		
		try {
			mycon = dataSource.getConnection();
			String query = "update taxi set currentSpot = originalSpot,freeTime=6";
			
			myst = mycon.prepareStatement(query);
			
			
	// worked : 	
			System.out.println(query+" "+myst.toString());
			myst.execute();

			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally
		{
			close(mycon,myst,null);
		}
		
	}
	
	private void close(Connection mycon, PreparedStatement myst, ResultSet rs) {
		 try
		 {
			 if(mycon!=null)
			 {
				 mycon.close();
			 }
			 if(myst!=null)
			 {
				 myst.close();
			 }
			 if(rs!=null)
			 {
				 rs.close();
			 }
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
		
	}
	
	public int getTaxiCount() {
		Connection mycon=null;
		PreparedStatement myst = null;
		ResultSet rs = null;
		
		try {
			mycon = dataSource.getConnection();
			String query = "select count(*) as count from taxi";
			
			myst = mycon.prepareStatement(query);
			
			int count=0;
			
			rs = myst.executeQuery();
			while(rs.next())
			{
				count = Integer.parseInt(rs.getString(1));
			}
			return count+1;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally
		{
			close(mycon,myst,rs);
		}
		return 0;
	}

	public void insertTaxi(Taxi newTaxi) {
		
		Connection mycon=null;
		PreparedStatement myst = null;
		
		try {
			mycon = dataSource.getConnection();
			String query = "insert into taxi(taxi_id,currentSpot,originalSpot) values(?,?,?)";
			
			myst = mycon.prepareStatement(query);
			
			myst.setInt(1, newTaxi.getId());
			myst.setString(2, newTaxi.getCurrentSpot()+"");
			myst.setString(3, newTaxi.getOriginalSpot()+"");
			
			myst.execute();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally
		{
			close(mycon,myst,null);
		}
		
	}

	public List<Taxi> getTaxiBySpot(char ch) {
		Connection mycon=null;
		PreparedStatement myst = null;
		ResultSet rs = null;
		
		try {
			mycon = dataSource.getConnection();
			String query = "select taxi_id,originalSpot from taxi where currentSpot=?";
			
			
			myst = mycon.prepareStatement(query);
			myst.setString(1, ch+"");
			
			List<Taxi> taxiDet = new ArrayList<>();
			
			rs = myst.executeQuery();
			while(rs.next())
			{
				taxiDet.add(new Taxi(Integer.parseInt(rs.getString(1)) , rs.getString(2)));
			}
			return taxiDet;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally
		{
			close(mycon,myst,rs);
		}
		return null;
	}
	
}
