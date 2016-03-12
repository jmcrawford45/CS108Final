package user;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tableabstraction.TableAbstraction; 

/**
 * Servlet implementation class EditUsers
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
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
		String type = request.getParameter("type");
		String name = request.getParameter("user");
		
		Connection con = (Connection)request.getSession().getServletContext().getAttribute("connection");
		User toEdit = TableAbstraction.getUser(name, con);
		if(type.equals("promote")){
			System.out.println(name + type);
			toEdit.promoteToAdmin();
			TableAbstraction.updateUser(name, toEdit, con);
		}else {
			User toDelete = TableAbstraction.getUser(name, con);
			for(String s: toDelete.getFriends()){
				User toUpdate = TableAbstraction.getUser(s, con);
				if(toUpdate != null){
					toUpdate.removeFriend(name);
					TableAbstraction.updateUser(s, toUpdate, con);  
				}
			}
			TableAbstraction.deleteUser(name,  con);
		}
        request.getRequestDispatcher("AdminPage.jsp").forward(request, response);

	}

}