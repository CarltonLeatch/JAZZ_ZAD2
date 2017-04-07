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

/**
 * Servlet implementation class upgrade
 */
@WebServlet("/upgrade")
public class upgrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upgrade() {
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
		
		HttpSession session = request.getSession();
		//String Login = (String) session.getAttribute("login");
		// TODO Auto-generated method stub
		try {
			Connection connection;			
			connection = DriverManager.getConnection(""
					+ "jdbc:hsqldb:hsql://localhost/workdb");
			UserRepository repo = new UserRepository(connection, null);
			String Login = request.getParameter("login");
			
			
			repo.withLogin(Login);
			response.sendRedirect("Profile.jsp");
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
