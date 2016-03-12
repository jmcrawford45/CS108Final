//package loginServlets;
//
//import java.io.IOException;
//import java.sql.Connection;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import user.User;
//import tableabstraction.TableAbstraction;
//
///**
// * Servlet implementation class LoginServlet
// * handles login requests
// */
//@WebServlet("/LoginServlet")
//public class LoginServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public LoginServlet() {
//        super();
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String name = (String)request.getParameter("user");
//		String password = (String)request.getParameter("password");
//		Connection con = (Connection)request.getSession().getServletContext().getAttribute("connection");
//		User user = TableAbstraction.getUser(name,con);
//		if(user != null && user.getPassword().equals(Security.getHashed(password, user.getSalt()))){
//			request.getSession().setAttribute("user", user);
//            request.getRequestDispatcher("HomePage.jsp").forward(request, response);
//        } else {
//        	request.getRequestDispatcher("TryAgain.html").forward(request, response);
//        }
//	}
//
//}


package loginServlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.User;
import tableabstraction.TableAbstraction;

/**
 * Servlet implementation class LoginServlet
 * handles login requests
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = (String)request.getParameter("name");
		String password = (String)request.getParameter("password");
		Connection con = (Connection)request.getSession().getServletContext().getAttribute("connection");
		User user = TableAbstraction.getUser(name,con);
		if(user != null && user.getPassword().equals(Security.getHashed(password, user.getSalt()))){
			request.getSession().setAttribute("user", name);
            request.getRequestDispatcher("HomePage.jsp").forward(request, response);
        } else request.getRequestDispatcher("NoExist.html").forward(request, response);
	}

}




