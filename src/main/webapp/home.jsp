<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Taxi Booking</title>
<style>
      .top-container
      {
        text-align: center;
        background-color: #0D4C92;
        color: white;
        padding:2px;
        display: flex;
        width: 100%;
      }
      button
      {
        width: 120px;
        height: 30px;
        color: white;
        border: 2px solid #4CAF50;
        font-size: 15px;
        background-color: #4CAF50; /* Green */
        transition-duration: 0.4s;
        cursor: pointer;
      }
      button:hover {
        background-color: white;
        color: black;
      }
      .login
      {
        background-image: url("https://i.pinimg.com/736x/f6/f3/85/f6f3854d01c7e2871a3328d2fecd9d44.jpg");
        background-size: cover;
        width: 100%;
        height:550px;
      }
      .container {
        margin-right: 20%;
        margin-left: 35%;
        margin-top: 2%;
        padding: 10px;  
        width:30%;
        height: 30%;
        border-style: groove;
        background: rgba(255, 255, 255, 0.4);
       border-radius: 20px;
      }    
      input[type=text],input[type=password]
      {
        display: inline-block;
        border: 2px solid #ccc;
        box-sizing: border-box;
        width: 190px;
        height: 30px;
      }
      .container-2
      {
        font-weight: bolder; 
      }
    </style>
</head>
<body>
	  <div class="top-container">
      <h1 style="margin-left: 20px;">Taxi Booking</h1>
     
  </div>
  <div class="login">
    <h2 style="text-align: center;">Customer Login Form</h2>
    <div class="container-2">
      <h3>Note ! </h3>
      <ul>
        <li> Available from 6AM to 12PM</li>
        <li> Runs from Theni to Madurai and Madurai to Theni.</li>
      </ul>
    </div>
    <div class="container">
      <form action="Login" method="post">
        <label for="uname">Username : </label>
        <input type="text" name="uname" id="uname" required>
        <br><br>
        <label for="pwd">Password : </label>
        <input type="password" name="psw" id="psw" required>
        <br><br>
        <button type="submit">Login</button>
        <button onclick="window.location.href='Register'">New Customer</button>
      </form>
    </div>
  </div>       
</body>
</html>