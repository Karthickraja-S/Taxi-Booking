package com.taxibooking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.taxibooking.dao.TaxiDatabaseUtil;
import com.taxibooking.logic.TaxiImple;
import com.taxibooking.model.Customer;
import com.taxibooking.model.History;
import com.taxibooking.model.Taxi;


@WebServlet("/bookTaxi")
public class bookTaxi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name="jdbc/taxi_booking")
	private DataSource datasource;
	
	TaxiDatabaseUtil db;
	
	 public void init(ServletConfig config)
	 {
		 db = new TaxiDatabaseUtil(datasource);
	 }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object logged = session.getAttribute("cust");
		if(logged==null)
		{
			response.sendRedirect("home.jsp");
			return;
		}
		String pickUp = request.getParameter("pickUp");
		String drop = request.getParameter("drop");
		String timeInput = request.getParameter("time");
		String mode = request.getParameter("mode");
		int time = Integer.parseInt(timeInput);
		if(mode.equals("PM") && time!=12)
		{
			time += 12;
		}
		
		PrintWriter out = response.getWriter();
		System.out.println("Input Request : "+pickUp+" "+drop+" "+time);
		
		List<Taxi> allTaxis = db.getTaxi();
		
		List<Taxi> freeTaxisAvailable = TaxiImple.getFreeTaxis(allTaxis, time, pickUp.charAt(0));
		System.out.println("Free Taxi List : "+freeTaxisAvailable);
		
		if(freeTaxisAvailable.size()==0)
		{
			out.println("<html><script>alert('No free Taxis Available Right Now');"
					+ "window.location.href='welcome.jsp';</script></html>");
		}
		 //sort taxis based on earnings 
        Collections.sort(freeTaxisAvailable,(a,b)->a.getTotalEarnings() - b.getTotalEarnings()); 
        // the lower earned will be first. If two taxis near to customer , then the taxi with
        // lower earnings will be the priority.
        
        System.out.println("After sort : Free Taxi List : "+freeTaxisAvailable);

        Customer cust = TaxiImple.getCustomer(); 
        
        History TaxiBooked = TaxiImple.bookTaxi(cust.getUserName(), pickUp.charAt(0), drop.charAt(0), 
        		time, freeTaxisAvailable);
        // inserting the history
        try {
        db.insertHistory(TaxiBooked);
        }catch(Exception e) {System.out.println(e);}
        
        // updating the taxi's next stop and his earnings
        db.updateTaxiData(TaxiBooked.getTaxi_id(),drop , TaxiBooked.getAmount(),TaxiBooked.getDropTime());

        out.println("<html><script>alert('Taxi Booked. Taxi ID : "+TaxiBooked.getTaxi_id()+" Amount : "+
        		TaxiBooked.getAmount()+"'"
        		+ ");"
				+ "window.location.href='welcome.jsp';</script></html>");
        
        
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
