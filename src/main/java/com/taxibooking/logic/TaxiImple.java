package com.taxibooking.logic;

import com.taxibooking.dao.TaxiDatabaseUtil;
import com.taxibooking.model.Customer;
import com.taxibooking.model.History;
import com.taxibooking.model.Taxi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.sql.DataSource;

public class TaxiImple {		
	
	private static Customer custLogged=null;
	
	public static void setCustomer(Customer cust)
	{
		custLogged = cust;
	}
	
	public static Customer getCustomer()
	{
		return custLogged;
	}
	
	public static List<Taxi> getFreeTaxis(List<Taxi> taxis,int pickupTime,char pickupPoint)
    {
        List<Taxi> freeTaxis = new ArrayList<Taxi>();
        for(Taxi t : taxis)
        {   
       //taxi should be free
       //taxi should have enough time to reach customer before pickuptime
	   // considering that each spot have 15km distance and covers a time of 1hour. 
	   // example if taxi freeTime is 6 and pickup time is 9 and currentSpot is A and pickupPoint is C ,
       // then A->B->C covers 2hours so
 	   // he can go to C in 8 time. thats the condition we are checking below ( if the taxi can go within pickup time or not )
            if(t.getFreeTime() <= pickupTime && (Math.abs((t.getCurrentSpot() - '0') - (pickupPoint - '0')) <= pickupTime - t.getFreeTime()))
            freeTaxis.add(t);

        }
        return freeTaxis;
    }
	
	public static History bookTaxi(String customerName,char pickupPoint,char dropPoint,int pickupTime,List<Taxi> freeTaxis)
    {
        // to find nearest
        int min = 999;

        //distance between pickup and drop
        int distanceBetweenpickUpandDrop = 0;

        //this trip earning
        int earning = 0;

        //when taxi will be free next
        int nextfreeTime = 0;

        //where taxi is after trip is over
        char nextSpot = 'Z';

        //booked taxi
        Taxi bookedTaxi = null;

        //all details of current trip as string
        String tripDetail = "";
        
        for(Taxi t : freeTaxis)
        {
        	// each unit have a distance of 15Kms.
        	
            int distanceBetweenCustomerAndTaxi = Math.abs((t.getCurrentSpot() - '0') - (pickupPoint - '0')) * 15;
            if(distanceBetweenCustomerAndTaxi < min)
            {
                bookedTaxi = t;
                //distance between pickup and drop = (drop - pickup) * 15KM
                distanceBetweenpickUpandDrop = Math.abs((dropPoint - '0') - (pickupPoint - '0')) * 15;
                
                //trip earning = 100 + (distanceBetweenpickUpandDrop-5) * 10
                earning = (distanceBetweenpickUpandDrop-5) * 10 + 100;
                
                // assigning the distance of taxi to min.
                min = distanceBetweenCustomerAndTaxi;
                
                //drop time calculation
                int dropTime  = pickupTime + distanceBetweenpickUpandDrop/15;
                
                //when taxi will be free next
                nextfreeTime = dropTime;

                //taxi will be at drop point after trip
                nextSpot = dropPoint;

            }
            
        }

        //setting history object to allotted taxi
        String date = String.valueOf(new java.util.Date());
        
		date = date.substring(4,10)+" "+date.substring(24,28);
		
        History taxiBooked = new History(date,bookedTaxi.getId(),customerName,getExactLocation(pickupPoint),
        		getExactLocation(dropPoint),pickupTime,nextfreeTime,earning);
        
        System.out.println("Booked Details : "+taxiBooked);
        System.out.println("Taxi " + bookedTaxi.getId() + " booked");
        
        return taxiBooked;

    }

	public static String getExactLocation(char pickupPoint) {
		HashMap<Character,String> hm = new HashMap<>();
		hm.put('A', "Madurai");
		hm.put('B', "Chekkanurani");
		hm.put('C', "Usilampatty");
		hm.put('D', "Aundipatty");
		hm.put('E', "Theni");
		hm.put('F', "Bodi");
		
		return hm.get(pickupPoint);
	}
	
	public static List<History> getHistoryBasedOnCustomer(String name)
	{
		List<History> histOfCustomer = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/taxibooking","root","your_password");			
			Statement st = con.createStatement();   // to give any query we need statement		
			String Query = "select bookedDate,fromLoc,toLoc,amount,taxi_id from history where custName = '"+name+"';";  // string has '' in between inputs.
			ResultSet rs = st.executeQuery(Query);
			
			while(rs.next())
			{
				histOfCustomer.add(new History(rs.getString(1),rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)),
						Integer.parseInt(rs.getString(5))));
			}
			return histOfCustomer;
			}
			catch(Exception e) {
				System.out.println(e);
			}
		
		return histOfCustomer;
	}

}
