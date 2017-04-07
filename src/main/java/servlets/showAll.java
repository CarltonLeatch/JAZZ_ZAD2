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
 * Servlet implementation class showAll
 */
@WebServlet("/showAll")
public class showAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showAll() {
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
		try {
			Connection connection;			
			connection = DriverManager.getConnection(""
					+ "jdbc:hsqldb:hsql://localhost/workdb");
			UserRepository repo = new UserRepository(connection, null);
			String Login = (String) session.getAttribute("login");
			User user = new User();
			user = repo.get(Login);
			if(user.getType().equalsIgnoreCase("admin")){
			
			response.getWriter().println("<table>");
			response.getWriter().println("<tr><th>ID></th><th> Login</th>"
					+"<th>Password</th>"
					+"<th>Email</th>"
					+"<th>Typ</th>"
					+ "<th>UPGRADE</th>"
					+ "<th>DOWNGRADE</th></tr>");
			for(User users : repo.getAll()){
				response.getWriter().println("<tr><th>"+users.getId()+"</th>");
				response.getWriter().println("<th>"+users.getLogin() +"</th>"
						+ "<th>"+users.getPassword() +"</th>"
						+ "<th>"+users.getEmail() +"</th>"
						+ "<th>"+users.getType() +"</th>"
						+ "<th> <form method = 'POST' action = 'upgrade'>"
						+ "<input type='submit'name='login' id='login' value='"+users.getLogin()+"'/></form></th>"
						+ "<th> <form method = 'POST' action = 'downgrade'>"
						+ "<input type='submit'name='login' id='login' value='"+users.getLogin()+"'/></form></th></tr>");
			}
			response.getWriter().println("</table>");
			response.getWriter().println("<a href = 'Profile.jsp' >Powrót</a>");
		}else response.getWriter().println("Tu ma dostęp tylko Admin");
		
		}catch(SQLException e){
		e.printStackTrace();
	}

}
}