package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import repositories.UserRepository;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */ 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		
		String Login = request.getParameter("login");
		String Password = request.getParameter("password");
		
		if(Login.equalsIgnoreCase("") || Password.equalsIgnoreCase("")) response.getWriter().println("Uzupełnij wszystkie pola");
		else {
	    	
	    	try {
	            Class.forName("org.hsqldb.jdbcDriver");
	        } catch (Exception e) {
	            System.out.println("ERROR: failed to load HSQLDB JDBC driver.");
	            e.printStackTrace();
	            return;
	        }
	    	
	    	try {
				Connection connection;			
				connection = DriverManager.getConnection(""
						+ "jdbc:hsqldb:hsql://localhost/workdb");
				UserRepository repo = new UserRepository(connection, null);
								
				for(User users : repo.getAll()){
					
					String logon = users.getLogin();
					String passwo = users.getPassword();
					
					if(logon.equalsIgnoreCase(Login) && passwo.equals(Password)){
						session.setAttribute("login", Login);
						response.sendRedirect("Profile.jsp");
						} 
					
				}
				response.getWriter().println("Logowanie nie powiodło się");
					
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		}
		
		
		
	}
	
	

}
