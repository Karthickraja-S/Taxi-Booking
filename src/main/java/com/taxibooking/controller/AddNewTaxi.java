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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.taxibooking.dao.TaxiDatabaseUtil;
import com.taxibooking.model.Taxi;


@WebServlet("/AddNewTaxi")
public class AddNewTaxi extends HttpServlet {
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
		int newTaxiId = db.getTaxiCount();
		session.setAttribute("taxi-id", newTaxiId);
		
		System.out.println("New taxi Id : "+newTaxiId);
		response.sendRedirect("addNewTaxi.jsp");

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		PrintWriter out = response.getWriter();
		
		int taxiId = db.getTaxiCount();
		
		String currSpot = request.getParameter("currSpot");
		
		String OriSpot = request.getParameter("OriSpot");
		
		Taxi newTaxi = new Taxi(taxiId,currSpot.charAt(0),OriSpot.charAt(0));
		
		db.insertTaxi(newTaxi);
		
		out.print("<html><script>alert('Taxi has been added SuccessFully');"
				+ "window.location.href='admin.jsp'</script></html>");
		
	}

}
