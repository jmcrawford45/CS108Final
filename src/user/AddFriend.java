package user;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tableabstraction.TableAbstraction;

/**
 * Servlet implementation class AddFriend
 */
@WebServlet("/AddFriend")
public class AddFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriend() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int index = Integer.parseInt(request.getParameter("index"));		
		String toAdd = request.getParameter("toAdd"); 
		
		User defUser = TableAbstraction.getUser(request);
		if(defUser == null){
			RequestDispatcher dispatch = 
					request.getRequestDispatcher("Register.html");
			dispatch.forward(request, response);
			return;
		}

		defUser.deleteMessage(index);
		

		Connection con = (Connection)request.getSession().getServletContext().getAttribute("connection");
		User toAddUser = tableabstraction.TableAbstraction.getUser(toAdd, con);
		defUser.addFriend(toAdd);
		
		
		toAddUser.addFriend(defUser.getDisplayName());
		TableAbstraction.updateUser(defUser.getDisplayName(),defUser,con);
		TableAbstraction.updateUser(toAdd,toAddUser,con);
		FriendEntry entry = new FriendEntry(defUser.getDisplayName(),toAdd);
		defUser.updateLogs(entry, con, new ArrayList<String>());
		toAddUser.updateLogs(entry, con, defUser.getFriends());

		RequestDispatcher dispatch = request.getRequestDispatcher("DisplayUser.jsp?user="+toAddUser.getDisplayName());  
		dispatch.forward(request, response);  

	}

}
