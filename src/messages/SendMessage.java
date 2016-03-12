package messages;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tableabstraction.TableAbstraction;
import user.User;

/**
 * Servlet implementation class SendMessage
 */
@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessage() {
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
		String to = request.getParameter("toUser");
		String from = request.getParameter("fromUser");
		String message = request.getParameter("message");
		//User defUser = (User)request.getSession().getAttribute("user");//correct//
		User defUser = TableAbstraction.getUser(request);
		if(defUser == null){
			RequestDispatcher dispatch = 
					request.getRequestDispatcher("Register.html");
			dispatch.forward(request, response);
			return;
		}
		if(to.equals(defUser.getDisplayName())){
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(""
					+ "Error.jsp?title=Self Message Error&message=You Can't Message Yourself!");
			dispatch.forward(request, response);
			return;
		}
		Connection con = (Connection)request.getSession().getServletContext().getAttribute("connection");
		User toUser = tableabstraction.TableAbstraction.getUser(to, con);
		if(toUser == null){
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(""
					+ "Error.jsp?title=Doesn't Exist&message=This User Does not Exist!");
			dispatch.forward(request, response);
			return;
		}
		if(toUser.isPrivate()){
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(""
					+ "Error.jsp?title=Private&message=This User is Private!");
			dispatch.forward(request, response);
			return;
		}
		System.out.println(toUser.getDisplayName());

		TextMessage msg = new TextMessage(from,to,message);
		toUser.addMessageToInbox(msg);
		TableAbstraction.updateUser(to,toUser,con);  
		
		RequestDispatcher dispatch = request.getRequestDispatcher("DisplayInbox.jsp");  
		dispatch.forward(request, response); 
 
		
	}

}
