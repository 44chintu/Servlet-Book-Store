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
@WebServlet("/deleteurl")
public class DeleteBook extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		int id = Integer.parseInt(req.getParameter("id"));
		final String query = "delete from BOOKDATA where ID=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try(
			Connection con = DriverManager.getConnection("jdbc:mysql:///book","xxxx","xxxx");
			PreparedStatement ps = con.prepareStatement(query);
			){
			ps.setInt(1, id);
			int count = ps.executeUpdate();
			if(count==1) {
				out.println("Deleted Record");
			}
			else {
				out.println("Not Deleted");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
}
