<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.taxibooking.model.Customer" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New Taxi</title>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
     integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
          .form{
            width: 33%;
            padding: 20px;
            margin-top: 90px;
            margin-left: 30%; 
            border: 1px solid black;
          }
    </style>
    <script>
        function myfun(){
        var x = document.getElementById("same");
        if(x.checked == true)
        {
            document.getElementById("OriSpot").value = document.getElementById("currSpot").value;

        }
       
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
	if(cust==null || adminControl==null)
	{
		response.sendRedirect("home.jsp");
		return;
	} 
	%>
	 <div id="tttt">
        <h1 style="margin-left:20px;margin-top:15px; color:white">Add new Taxi</h1>
        <button class="button button1" onclick="window.location.href='admin.jsp'">Back</button>
    </div>     
    <form action="AddNewTaxi" method="post" class="form">

        <label for="id">Taxi ID : </label>
        <input type="number" id="id" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"
        value=<%=session.getAttribute("taxi-id") %> disabled>
        <br>

        <label for="curr">Current spot : </label>
        <select name = "currSpot" id="currSpot" class="form-control form-control-sm">
            <option value="A">Madurai</option>
            <option value="B">Chekkanurani</option>
            <option value="C">Usilampatty</option>
            <option value="D">Aundipatty</option>
            <option value="E">Theni</option>
            <option value="F">Bodi</option>
        </select>
        <br>
        <input type="checkbox"  id="same" onclick="myfun()">Same For Original spot
        <br><br>
        <label for="original">Original spot : </label>
        <select name = "OriSpot" id="OriSpot" class="form-control form-control-sm">
            <option value="A">Madurai</option>
            <option value="B">Chekkanurani</option>
            <option value="C">Usilampatty</option>
            <option value="D">Aundipatty</option>
            <option value="E">Theni</option>
            <option value="F">Bodi</option>
        </select>
        <br>
        <button class="btn btn-primary" type="submit"> Add Taxi</button>
    </form>
    <br>   
</body>
</html>