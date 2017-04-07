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
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
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
		String logon = (String) session.getAttribute("login");
		try {
			Connection connection;			
			connection = DriverManager.getConnection(""
					+ "jdbc:hsqldb:hsql://localhost/workdb");
			UserRepository repo = new UserRepository(connection, null);
			
			
			response.getWriter().println("<table>");
			response.getWriter().println("<tr><th> Login</th>"
					+"<th>Password</th>"
					+"<th>Email</th>"
					+"<th>Typ</th></tr>");
			User users = new User();
				users = repo.get(logon);
				response.getWriter().println("<tr>");
				response.getWriter().println("<th>"+users.getLogin() +"</th>"
						+ "<th>"+users.getPassword() +"</th>"
						+ "<th>"+users.getEmail() +"</th>"
						+ "<th>"+users.getType() +"</th> </tr>");
			
			response.getWriter().println("</table>");
			response.getWriter().println("<a href = 'Profile.jsp' >Powrót</a>");
	}catch(SQLException e){
		e.printStackTrace();
	}
		
	}

}
