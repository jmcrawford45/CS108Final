package user;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tableabstraction.TableAbstraction;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		User defUser = TableAbstraction.getUser(request);
		if(defUser == null){
			RequestDispatcher dispatch = 
					request.getRequestDispatcher("Register.html");
			dispatch.forward(request, response);
			return;
		}

		Connection con = (Connection)request.getSession().getServletContext().getAttribute("connection");

		if(type.equals("image")){
			String newImage = request.getParameter("newImage");
			defUser.setImage(newImage);
			
		} else if(type.equals("status")){
			String newStatus = request.getParameter("newStatus");
			defUser.setStatus(newStatus);
		} else if(type.equals("bio")){
			String newBio = request.getParameter("newBio");
			defUser.setBio(newBio);
		} else if(type.equals("privacy")){
			boolean currentPrivacy = defUser.isPrivate();
			if(currentPrivacy){
				defUser.setPrivate(false);
			} else {
				defUser.setPrivate(true);
			}
		}
		TableAbstraction.updateUser(defUser.getDisplayName(),defUser,con);
		RequestDispatcher dispatch = request.getRequestDispatcher("HomePage.jsp");  
		dispatch.forward(request, response);
		
	}

}
