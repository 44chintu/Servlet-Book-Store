package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		//Book info
		String bookName = req.getParameter("bookName");
		String bookEdition = req.getParameter("bookEdition");
		float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
		
		
		final String insert = "insert into BOOKDATA(BOOKNAME,BOOKEDITION,BOOKPRICE) values(?,?,?)";
		final String retreive = "select * from BOOKDATA";
		//Load JDBC Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//Create Connection
		try(
			Connection con = DriverManager.getConnection("jdbc:mysql:///book","xxxx","xxxx");
			PreparedStatement ps = con.prepareStatement(insert);	
			){
			ps.setString(1, bookName);
			ps.setString(2, bookEdition);
			ps.setFloat(3, bookPrice);
			
			int count = ps.executeUpdate();
			if(count==1) {
				out.print("<h2> Record registered successfully</h2>");
			}
			else {
				out.print("<h2> Record not registered</h2>");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			out.println("Sql Exception<h1>"+e.getMessage()+"</h2>");
		}
		catch(Exception e) {
			e.printStackTrace();
			out.println("<h1>"+e.getMessage()+"</h2>");
		}
		
		out.println("<a href='home.html'>Home</a>");
		out.println("<br>");
		out.println("<a href='bookList'>Book List</a>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
	
}
