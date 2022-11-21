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
import javax.sql.DataSource;

import com.taxibooking.dao.TaxiDatabaseUtil;
import com.taxibooking.model.Customer;


@WebServlet("/Register")
public class Register extends HttpServlet {
	
	@Resource(name="jdbc/taxi_booking")
	private DataSource datasource;
	
	TaxiDatabaseUtil db;
	
	public void init(ServletConfig config)
	{
			db = new TaxiDatabaseUtil(datasource);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rs = request.getRequestDispatcher("register.html");
		rs.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("uname");
		String mail = request.getParameter("email");
		String pass = request.getParameter("psw");
		
		PrintWriter out = response.getWriter();
		
		Customer cust = new Customer(name,mail,pass);
		try
		{
			db.addCustomer(cust);
			out.println("<html><script>alert('Registered Successfully.. Please Login to Continue');window.location.href='home.jsp';</script></html>");
		}
		catch(Exception e)
		{
			out.println("<html><script>alert('error');window.location.href='Register';</script></html>");
		}
	}

}
