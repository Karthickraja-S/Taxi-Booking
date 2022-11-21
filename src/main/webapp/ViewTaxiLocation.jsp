<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.taxibooking.model.*,java.util.*,com.taxibooking.logic.TaxiImple" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Taxi Location</title>
 <style>
        #tttt
        {
            display: grid;
            grid-template-columns: 90% 10%;
            margin-top: 10px;
            margin-left:10px;
            margin-right:10px;
            background-color: #0D4C92;
            height: 70px;
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
       .box
       {
        border: 1px solid black;
        width: 14%;
        border-radius: 28px;
       }
       .box:hover
       {
        background-color: aqua;
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
		 <div id="tttt">
        <h1 style="margin-left:20px;margin-top:15px; color:white">Taxi Location Details  </h1>
        <button class="button button1" onclick="window.location.href='admin.jsp'">Back</button>
    </div>   
    <% List<Taxi> taxiAtSpotA = (List<Taxi>)session.getAttribute("taxi-A");
    	List<Taxi> taxiAtSpotB = (List<Taxi>)session.getAttribute("taxi-B");
    	List<Taxi> taxiAtSpotC = (List<Taxi>)session.getAttribute("taxi-C");
    	List<Taxi> taxiAtSpotD = (List<Taxi>)session.getAttribute("taxi-D");
    	List<Taxi> taxiAtSpotE = (List<Taxi>)session.getAttribute("taxi-E");
    	List<Taxi> taxiAtSpotF = (List<Taxi>)session.getAttribute("taxi-F");
    %>
    <div>
        <h3>&nbsp;&nbsp;&nbsp;O.S : Original Spot</h3>
        <img src="images/route.png" style="margin-top:100px;margin-left:50px"  width="90%" alt="route_image">
        <br><br>
      <div style="display: flex;">
        <div class="box" style="margin-left: 3%;"><br>
        	<%if(taxiAtSpotA.size()==0){ %>
        	<b>&nbsp;&nbsp;No Taxis Present Here</b>
        	<%}
        	else {
        		for(Taxi t : taxiAtSpotA){%>
            <ul>
            <li>Taxi <%=t.getId() %> , O.S = <%=TaxiImple.getExactLocation(t.getOriginalSpot())%></li>
            </ul>
            <%}} %>
        </div>
        <div class="box" style="margin-left: 1%;"><br>
           <%if(taxiAtSpotB.size()==0){ %>
        	<b>&nbsp;&nbsp;No Taxis Present Here</b>
        	<%}
        	else {
        		for(Taxi t : taxiAtSpotB){%>
            <ul>
            <li>Taxi <%=t.getId() %> , O.S = <%=TaxiImple.getExactLocation(t.getOriginalSpot()) %></li>
            </ul>
            <%}} %>
        </div>
        <div class="box" style="margin-left: 2%;"><br>
           <%if(taxiAtSpotC.size()==0){ %>
        	<b>&nbsp;&nbsp;No Taxis Present Here</b>
        	<%}
        	else {
        		for(Taxi t : taxiAtSpotC){%>
            <ul>
            <li>Taxi <%=t.getId() %> , O.S = <%=TaxiImple.getExactLocation(t.getOriginalSpot()) %></li>
            </ul>
            <%}} %>
        </div>
        <div class="box" style="margin-left: 1%;"><br>
           <%if(taxiAtSpotD.size()==0){ %>
        	<b>&nbsp;&nbsp;No Taxis Present Here</b>
        	<%}
        	else {
        		for(Taxi t : taxiAtSpotD){%>
            <ul>
            <li>Taxi <%=t.getId() %> , O.S = <%=TaxiImple.getExactLocation(t.getOriginalSpot()) %></li>
            </ul>
            <%}} %>
        </div>
        <div class="box" style="margin-left: 2%;"><br>
           <%if(taxiAtSpotE.size()==0){ %>
        	<b>&nbsp;&nbsp;No Taxis Present Here</b>
        	<%}
        	else {
        		for(Taxi t : taxiAtSpotE){%>
            <ul>
            <li>Taxi <%=t.getId() %> , O.S = <%=TaxiImple.getExactLocation(t.getOriginalSpot()) %></li>
            </ul>
            <%}} %>
        </div>
        <div class="box" style="margin-left: 2%;"><br>
           <%if(taxiAtSpotF.size()==0){ %>
        	<b>&nbsp;&nbsp;No Taxis Present Here</b>
        	<%}
        	else {
        		for(Taxi t : taxiAtSpotF){%>
            <ul>
            <li>Taxi <%=t.getId() %> , O.S = <%=TaxiImple.getExactLocation(t.getOriginalSpot()) %></li>
            </ul>
            <%}} %>
        </div>
      </div>  
    </div>
   
    <br>   
</body>
</html>