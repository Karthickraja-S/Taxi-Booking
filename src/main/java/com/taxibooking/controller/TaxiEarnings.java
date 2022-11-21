package com.taxibooking.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.*;

import com.taxibooking.dao.TaxiDatabaseUtil;
import com.taxibooking.model.Taxi;


@WebServlet("/TaxiEarnings")
public class TaxiEarnings extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name="jdbc/taxi_booking")
	private DataSource datasource;
	
	TaxiDatabaseUtil db;
	
	 public void init(ServletConfig config)
	 {
		 db = new TaxiDatabaseUtil(datasource);
	 }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Taxi> taxiDetails = db.getTaxiEarnings();
	// worked :	System.out.println(taxiDetails);
		HttpSession session = request.getSession();
		session.setAttribute("taxi-det", taxiDetails);
		response.sendRedirect("earnings.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
