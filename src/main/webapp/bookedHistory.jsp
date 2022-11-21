<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.taxibooking.model.*,com.taxibooking.dao.TaxiDatabaseUtil,javax.annotation.Resource,javax.sql.DataSource,
java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Taxi Earnings</title>
<style>
		 .top-container{
            display: grid;
            grid-template-columns: 90% 10%;
            background-color: #0D4C92;
        }
        #data {
  			font-family: Arial, Helvetica, sans-serif;
  			border-collapse: collapse;
  			width: 100%;
  			text-align:center;
		}

		#data td, #data th {
  			border: 1px solid #ddd;
  			padding: 8px;
		}

		#data tr:nth-child(even){background-color: #f2f2f2;}

		#data tr:hover {background-color: #ddd;}

		#data th {
  			padding-top: 12px;
  			padding-bottom: 12px;
  			background-color: #04AA6D;
  			color: white;
		}
       
          .button {
            background-color: #4CAF50; /* Green */
            color: white;
            text-align: center;
            font-size: 15px;
            transition-duration: 0.4s;
            cursor: pointer;
            width: 80px;
            height: 30px;
            margin-top: 22px;
          }
          
          .button1 {
            background-color: white; 
            color: black; 
            border: 2px solid #4CAF50;
          }
          
          .button1:hover {
            background-color: #4CAF50;
            color: white;
          }
</style>
</head>
<body>
<% 
	// to prevent back button after logout ... we need block caching the memory in the browser.
	
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // suite for http 1.1
	response.setHeader("pragma","no-cache");  // for older versions HTTP 1.0
	response.setHeader("expires","0");  // for proxy users
	
	Customer cust = (Customer)session.getAttribute("cust"); 
	Object adminControl = session.getAttribute("admin-control");
	if(cust==null || adminControl==null)
	{
		response.sendRedirect("home.jsp");
		return;
	} 
	%>
	 <div class="top-container">
            <h1 style="margin-left:20px; color:white">Customer Booked History</h1>
            <button class="button button1" onclick="window.location.href='admin.jsp'">Back</button>
       </div>
       
       <% List<History> details = (List<History>) session.getAttribute("taxi-his"); 
       
       %>
       <h3>Total Number Of Records : <%=details.size() %></h3>
       <table id="data">
       <tr>
       <th>Date</th>
       	<th>Taxi</th>
       	<th>Customer Name</th>
       	<th>From</th>
       	<th>To</th>
       	<th>Pickup Time</th>
       	<th>Drop Time</th>
       	<th>Amount</th>
       </tr>
       <% for(History hist : details){ %>
       <tr>
       <td><%=hist.getDate() %></td>
       <td>TAXI <%=hist.getTaxi_id() %></td>
       <td> <%=hist.getcustName() %></td>
       <td> <%=hist.getFromLocation() %></td>
       <td> <%=hist.getToLocation() %></td>
       <td> <% int ptime = hist.getPickUpTime(); 
       			if(ptime<12)out.print(ptime+" AM");
       			else if (ptime==12)out.print(ptime+" PM");
       			else out.print(ptime-12+" PM"); %> </td>
       <td> <%int time = hist.getDropTime(); 
			if(time<12) out.print(time+" AM");
			else if(time==12)out.print(time+" PM");
			else out.print(time-12+" PM"); %></td>
       <td> Rs. <%=hist.getAmount() %></td>
       </tr>
       <% } %>
       
       </table>
      
      
       
</body>
</html>