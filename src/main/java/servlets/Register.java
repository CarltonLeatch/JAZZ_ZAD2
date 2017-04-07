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

import repositories.UserRepository;
import domain.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		String cPassword = request.getParameter("cPassword");
		String Email = request.getParameter("email");
		if(Login.equalsIgnoreCase("") || Password.equalsIgnoreCase("") || cPassword.equalsIgnoreCase("") || Email.equalsIgnoreCase("") || !cPassword.equals(Password))
			response.getWriter().println("Formularz uzupełniony niepoprawnie");
		else {		
	    	User u = new User();
	    	u.setLogin(Login);
	    	u.setPassword(Password);
	    	u.setEmail(Email);
	    
	    	
	    	
	    	
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
				
				if(valid(Login))
				{
					repo.add(u);				
					response.sendRedirect("index.jsp");
				}else response.getWriter().println("Konto z takim loginem juz istnieje");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

	}

	public boolean valid(String Login){
		try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (Exception e) {
            System.out.println("ERROR: failed to load HSQLDB JDBC driver.");
            e.printStackTrace();
        }
    	
    	try {
			Connection connection;				
			connection = DriverManager.getConnection(""
					+ "jdbc:hsqldb:hsql://localhost/workdb");
			UserRepository repo = new UserRepository(connection, null);
			boolean valid = false;
			for(User users : repo.getAll()){
				if(users.getLogin().equals(Login))
					return false;
				
			}
    	}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
	
	}
	
}
