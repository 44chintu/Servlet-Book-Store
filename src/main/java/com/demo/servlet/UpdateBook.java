package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdateBook extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		int id = Integer.parseInt(req.getParameter("id"));
		String bookName = req.getParameter("bookName");
		String bookEdition = req.getParameter("bookEdition");
		Float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
		final String query = "UPDATE BOOKDATA SET BOOKNAME = ?, BOOKEDITION = ?, BOOKPRICE = ? where ID=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try(
				Connection con = DriverManager.getConnection("jdbc:mysql:///book","xxxx","xxxx");
				PreparedStatement ps = con.prepareStatement(query);
				){
				ps.setString(1, bookName);
				ps.setString(2, bookEdition);
				ps.setFloat(3, bookPrice);
				ps.setInt(4, id);
				
				int count = ps.executeUpdate();
				if(count==1) {
					out.println("Updated");
				}
				else {
					out.println("Try again");
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
}
