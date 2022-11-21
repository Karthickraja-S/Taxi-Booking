package com.taxibooking.controller;

import java.io.IOException;
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
import com.taxibooking.model.History;
import com.taxibooking.model.Taxi;

@WebServlet("/history")
public class history extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name="jdbc/taxi_booking")
	private DataSource datasource;
	
	TaxiDatabaseUtil db;
	
	 public void init(ServletConfig config)
	 {
		 db = new TaxiDatabaseUtil(datasource);
	 }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    List<History> history = db.getBookedHistory();
		// worked :	System.out.println(taxiDetails);
			HttpSession session = request.getSession();
			session.setAttribute("taxi-his", history);
			response.sendRedirect("bookedHistory.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
