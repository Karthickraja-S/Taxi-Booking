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
import com.taxibooking.model.Taxi;


@WebServlet("/ViewTaxiLocation")
public class ViewTaxiLocation extends HttpServlet {
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
		Object logged = session.getAttribute("admin-control");
		if(logged==null)
		{
			response.sendRedirect("home.jsp");
			return;
		}
		
		for(int i=0;i<6;i++)
		{
			char ch = (char)(i+65);
			List<Taxi> taxiDet = db.getTaxiBySpot(ch);
			String key = "taxi-"+ch;
			session.setAttribute(key, taxiDet);
		}
		response.sendRedirect("ViewTaxiLocation.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
