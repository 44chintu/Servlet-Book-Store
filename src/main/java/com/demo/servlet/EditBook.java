package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/editScreen")
public class EditBook extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		int id = Integer.parseInt(req.getParameter("id"));
		final String query = "select * from BOOKDATA where ID=?";
		
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
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int I_D = rs.getInt(1);
				String bookName = rs.getString(2);
				String bookEdition = rs.getString(3);
				Float bookPrice = Float.parseFloat(rs.getString(4));
				//edit available book
				out.println("<h2>Edit Book Details</h2>");
				out.println("<form action='update' method='post'>");
				out.println("<input type='hidden' name='id' value='" + id + "'>");
				out.println("Book Name: ");
				out.println("<input type='text' name='bookName' value='" + bookName + "'><br>");
				out.println("Book Edition: ");
			    out.println("<input type='text' name='bookEdition' value='" + bookEdition + "'><br>");
			    out.println("Book Price: ");
			    out.println("<input type='number' name='bookPrice' step='0.01' value='" + bookPrice + "'><br>");
			    out.println("<input type='submit' value='Update'>");
			    out.println("</form>");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
