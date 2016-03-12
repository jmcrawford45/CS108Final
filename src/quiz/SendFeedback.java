package quiz;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tableabstraction.TableAbstraction;

/**
 * Servlet implementation class Feedback
 */
@WebServlet("/SendFeedback")
public class SendFeedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendFeedback() {
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
		QuizManager qm = (QuizManager)request.getServletContext().getAttribute("quizmanager");
		int qid = Integer.parseInt(request.getParameter("quizid"));
		Quiz q = qm.getQuizByID(qid);
		int user_id = Integer.parseInt(request.getParameter("userid"));
		int rating = 0;
		if (request.getParameter("rating") != null) {
			rating = Integer.parseInt(request.getParameter("rating"));
		}
		
		String review = request.getParameter("review");
		long time = System.currentTimeMillis();
		int id = TableAbstraction.getID(qm.con);
		qm.addFeedback(id, q.id, user_id, rating, review, time);
		RequestDispatcher rd = request.getRequestDispatcher("QuizList.jsp");
		rd.forward(request, response);
	}

}
