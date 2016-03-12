package quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.Question;

/**
 * Servlet implementation class TakeQuiz
 */
@WebServlet("/TakeQuiz")
public class TakeQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeQuiz() {
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
		request.getSession().removeAttribute("quiztaken");
		
		QuizManager qm = (QuizManager)request.getServletContext().getAttribute("quizmanager");
		int quiz_id = Integer.parseInt(request.getParameter("quizid"));
		Quiz q = qm.getQuizByID(quiz_id);
		
		ArrayList<Question> qs = qm.getQuizQuestions(quiz_id);
		if (q.random) {
			long seed = System.nanoTime();
			Collections.shuffle(qs, new Random(seed));
		}
		q.setQuestions(qs);
		RequestDispatcher dispatch = null;
		if (q.one_page) {
			dispatch = request.getRequestDispatcher("TakeQuizOnePage.jsp?quiz="+q.name);  			 
		} else {
			String correct;
			if (q.immediate) {
				correct = "immediate";
			} else {
				correct ="end";
			}
			
			dispatch = request.getRequestDispatcher("TakeQuizMultiPage.jsp?quiz="
			+q.name+"&index=" + 0);
		}
		request.getSession().setAttribute("quiztaken", q);
		long start = System.currentTimeMillis();
		request.setAttribute("start", start);

		dispatch.forward(request, response);
		
	}

}
