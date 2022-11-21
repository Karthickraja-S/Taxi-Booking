<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.taxibooking.model.Customer,com.taxibooking.logic.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DashBoard</title>
 <style>
        .top-container
        {
            display: grid;
            grid-template-columns: 90% 10%;
            background-color: #0D4C92;
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

          .card {
            box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
            transition: 0.2s;
            width: 90%;
            border-radius: 5px;
            text-align: center;
          }
          
          .card:hover {
            box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
          }

          .container {
            padding: 2px 16px;
          }
          .grid{
            display: grid;
            grid-template-columns: auto auto auto;
            padding: 10px;
            gap: 20px;
            
          }
          .btn
          {
          	margin-top:15px;
          	height : 40px;
          	text-align:center;
          	transition-duration: 0.4s;
          	background-color: white;
			cursor:pointer;
            color: black; 
            border: 2px solid #4CAF50;
          }
          .btn:hover {
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
            <h1 style="margin-left:20px; color:white">Welcome Admin!</h1>
            <form action="logout">
            <button class="button button1" type="submit">Logout</button>
            </form>
        </div>        
        <p style="margin-left:20px;">Last Accessed : <%=cust.getLastAccessedTime()%></p>
        <div class="grid">
            <div class="card">
                <img src="images/taxi.jpg" alt="Avatar" width="200px" height="120px" >
                <div class="container">
                <form action="TaxiEarnings">
                 <button class="btn" type="submit"> <h3><b>View Taxi Earnings</b></h3> </button>
                 </form>
                </div>
              </div>
              
              <div class="card">
                <img src="images/reset.JPG" alt="Avatar" width="200px" height="120px">
                <div class="container">
                <form action="ResetData">
                 <button class="btn" type="submit"><h3><b>Reset Taxi Position</b></h3></button>  
                 </form>
                </div>
              </div>
              
              <div class="card">
                <img src="images/add.jpg" alt="Avatar" width="200px" height="120px">
                <div class="container">
                   <form action="AddNewTaxi" method="get">
                 <button class="btn" type="submit"><h3><b>Add New Taxi</b></h3></button>
                 </form> 
                  
                </div>
              </div>

              <div class="card">
                <img src="images/map.jpg" alt="Avatar" width="200px" height="120px">
                <div class="container">
                   <form action="ViewTaxiLocation">
                 <button class="btn" type="submit"><h3><b>View Taxi Location</b></h3></button>
                 </form> 
                  
                </div>
              </div>

              <div class="card">
                <img src="images/hist.jpg" alt="Avatar" width="200px" height="120px">
                <div class="container">
                  <form action="history">
                 <button class="btn" type="submit"><h3><b>View Booked History</b></h3></button>
                 </form>  
                </div>
              </div>


        </div>
</body>
</html>