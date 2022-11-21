package com.taxibooking.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.taxibooking.dao.TaxiDatabaseUtil;


@WebServlet("/ResetData")
public class ResetData extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource(name="jdbc/taxi_booking")
	private DataSource datasource;
	
	TaxiDatabaseUtil db;
	
	 public void init(ServletConfig config)
	 {
		 db = new TaxiDatabaseUtil(datasource);
	 }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			db.resetTaxiData();
			PrintWriter out = response.getWriter();
			
			out.println("<html><script>alert('All taxis have been reset to their original position and time..');"
					+ "window.location.href='admin.jsp';</script></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
