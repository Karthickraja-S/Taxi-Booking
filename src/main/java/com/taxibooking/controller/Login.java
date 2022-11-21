package com.taxibooking.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.taxibooking.dao.TaxiDatabaseUtil;
import com.taxibooking.model.Customer;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	@Resource(name="jdbc/taxi_booking")
	private DataSource datasource;
	
	TaxiDatabaseUtil db;
	
	 public void init(ServletConfig config)
	    {
		 db = new TaxiDatabaseUtil(datasource);
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String uname = request.getParameter("uname");
			String pass = request.getParameter("psw");
			
			PrintWriter out = response.getWriter();
			
			Customer logged = db.getCustomer(uname, pass);
			System.out.println("Cust : "+logged);
			
			// check for admin or normal customer
			
			if(logged!=null && logged.getMode().equals("normal"))
			{	
				// creating a session for the user and making him to proceed further.
				HttpSession session = request.getSession();
				session.setAttribute("cust", logged);
				
				// to update the currTime as logged time.
				System.out.println("Date and time : "+new java.util.Date());
				String time = String.valueOf(new java.util.Date());
				db.updateTime(logged, time.substring(0,19)); 

				response.sendRedirect("welcome.jsp");
			}
			else if(logged!=null && logged.getMode().equals("admin"))
			{
				// send to admin portal
				HttpSession session = request.getSession();
				session.setAttribute("cust", logged);
				session.setAttribute("admin-control", true);
				String time = String.valueOf(new java.util.Date());
				db.updateTime(logged, time.substring(0,19)); 
				response.sendRedirect("admin.jsp");
			}
			else
			{
				out.println("<html><script>alert('Incorrect details');window.location.href='home.jsp';</script></html>");  
				
			}
	}

}
