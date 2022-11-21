<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.taxibooking.model.*,com.taxibooking.logic.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
 <style>
        .top-container
        {
            display: grid;
            grid-template-columns: 90% 10%;
            background-color: #0D4C92;
        }
        .A1{
            display: grid;
            grid-template-columns: 75% 25%;
            height: 500px;
        }
        .left{
            background-image: url(https://i1.trekearth.com/photos/9081/dscf0549.jpg);
            background-size:cover;
            height:550px;
        }
        .right{
            background-color:#A0E4CB; 
            padding:2px;
        }
        .container {
            margin-right: 20%;
            margin-left: 25%;
            margin-top: 8%;
            padding: 10px;  
            width:50%;
            height: 40%;
            border-style: groove;
            background: rgba(255, 255, 255, 0.2);
           border-radius: 20px;
          }      
        .btn{
            background-color: #04AA6D;
            color: white;
            padding: 10px 20px;
            border:black;
            cursor: pointer;
            width: 60%;
            margin-left: 20%;
            opacity: 0.9;
          }
          .btn:hover {
            opacity: 2;
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
            background-color: #4CAF50; 
            color: white; 
            border: 2px solid #4CAF50;
          }
          
          .button1:hover {
            background-color: white;
            color: black;
          }
          .box
          {
            border: 1px solid black;
            width: 50%;
            border-radius: 8px;
            margin-left: 25%;
            margin-top: 20px;
            text-align: center;
            padding: 3px;
          }
          input[type=text]
          {
            width: 190px;
            height: 30px;
          }
          .select{
            width: 190px;
            height: 30px;
            padding: 5px;
          }
           #alertBooked
          {
           border: solid 2px black;
           width:35%;
           border-radius: 20px;
            border-color: rgb(0, 0, 0);
            position: absolute;
                top: 230px;
                left: 260px;
            background-color: white;
            display: none;
            -webkit-box-shadow: 0 10px 6px -6px #777;
            -moz-box-shadow: 0 10px 6px -6px #777;
                 box-shadow: 0 10px 6px -6px #777;
          }
          #alertBooked p
          {
          		font-size: large;
          }
          #booked-history
          {
            width:80%;
            height: 80%;
            border: 2px solid black;
            border-radius: 15px;
            position: absolute;
            top:100px;
            left:70px;
            background-color: antiquewhite;
            display: none;
          }
          #cancel{
             font-weight:bolder; 
             cursor:pointer;
             font-size: x-large;
             position: absolute;
             left: 95%;
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
       
    </style>
    <script>
        function check()
        {
            var pick = document.getElementById("pickUp").value;
            var drop = document.getElementById("drop").value;
            if(pick==drop)
            {
                alert("pickup location and drop location can't be same");
                return false;
            }
            var time = document.getElementById("time").value;
            var mode = document.getElementById("mode").value;
        //    console.log(mode);
            time = parseInt(time);
            if(!(time>=6 && time<=12) && !(time>=1 && time<=12 && mode=="PM"))
            {
                alert("Taxi available only from 6AM to 12PM");
                return false;
            }
            return true;
        }
        function cancel()
        {
			document.getElementById("booked-history").style.display="none";
        }
        function showBooked()
        {
            document.getElementById("booked-history").style.display="block";
        }
    </script>
</head>
<body>
	<% 
	// to prevent back button after logout ... we need block caching the memory in the browser.
	
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // suite for http 1.1
	response.setHeader("pragma","no-cache");  // for older versions HTTP 1.0
	response.setHeader("expires","0");  // for proxy users
	
	Customer cust = (Customer)session.getAttribute("cust"); 
	Object adminControl = session.getAttribute("admin-control");
	if(cust==null || adminControl!=null)
	{
		if(adminControl!=null)
		{
			response.sendRedirect("admin.jsp");
			return;
		}
		response.sendRedirect("home.jsp");
		return;
	} 
	%><% TaxiImple.setCustomer(cust); %>
	 <div class="top-container">
            <h1 style="margin-left:20px; color:white">Welcome <%=cust.getUserName() %> :)</h1>
            <form action="logout">
            <button class="button button1" type="submit">Logout</button>
            </form>
        </div>     
        <br>   
        <div class="A1">
            <div class="left"> 
            <h1 style="text-align: center">Book your trip</h1>
            <form action="bookTaxi" onsubmit="return check()">
                <div class="container">
                <label for="pickUp"><b>Select PickUp Location : </b></label>
                   <select class="select" name="pickUp" id="pickUp">
                    <option value="A">Madurai</option>
                    <option value="B">Chekkanurani</option>
                    <option value="C">Usilampatty</option>
                    <option value="D">Aundipatty</option>
                    <option value="E">Theni</option>
                    <option value="F">Bodi</option>
                   </select>
                    <br><br>
                    <label for="drop"><b>Select Drop Location : </b></label>&nbsp;&nbsp;&nbsp;
                    <select class="select" name="drop" id="drop">
                    <option value="A">Madurai</option>
                    <option value="B">Chekkanurani</option>
                    <option value="C">Usilampatty</option>
                    <option value="D">Aundipatty</option>
                    <option value="E">Theni</option>
                    <option value="F">Bodi</option>
                    </select>
                    <br><br>
                    
                    <label for="time"><b>Enter PickUp Time : </b></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="text" name="time" id="time" placeholder="Taxi Available after 6'o clock" required>
                    <select name="mode" id="mode" style="height: 30px;">
                        <option>AM</option>
                        <option>PM</option>
                    </select>
                        <br>  <br>
                    <button type="submit" class="btn">Book Taxi</button>
                    </div>
            </form>
            </div>
            <div class="right">
                <fieldset>
                    <legend> <h3> Details </h3></legend>
                <p>Name : <%=cust.getUserName() %></p>
                <p>Email Id : <%=cust.getEmailId() %></p>
                <p>Last Accessed Time : <%=cust.getLastAccessedTime() %>
                </fieldset>
                <br><br>
                <button class="btn" onclick="showBooked()">View Booked History</button>
            </div>
        </div>


        <div id="booked-history">
            <span id="cancel" onclick="cancel()">&times;</span>
            <h2>Booked History : </h2>
            <br>
            <% List<History> details = TaxiImple.getHistoryBasedOnCustomer(cust.getUserName()); %>
            <% if(details.size()==0){ %>
            <h2 style="text-align:center">No History Found!</h2>
            <%} %>
            
                <table id="data">
                 <% if(details.size()>0){ %>
                    <tr>
                        <th>Date</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Amount</th>
                        <th>Taxi</th>
                    </tr>
                    <%} %>
                    <% for(History hist : details){%>
                    <tr>
                        <td><%= hist.getDate() %></td>
                        <td><%= hist.getFromLocation() %></td>
                        <td><%= hist.getToLocation() %></td>
                        <td>Rs. <%=hist.getAmount() %></td>
                        <td>Taxi <%=hist.getTaxi_id() %></td>
                    </tr>
                    <%} %>
                </table>
        </div>  
        
    
</body>
</html>