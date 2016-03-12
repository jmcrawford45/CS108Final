package quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.Question;

/**
 * Servlet implementation class TakePracticeQuiz
 */
@WebServlet("/TakePracticeQuiz")
public class TakePracticeQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakePracticeQuiz() {
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
		
		int quizid = Integer.parseInt(request.getParameter("quizid"));
		Quiz quiz = qm.getQuizByID(quizid);
		ArrayList<Question> pquestions = qm.getQuizQuestions(quizid);
		HashMap<Question, Integer> precord= new HashMap<Question, Integer>();
		for (Question q: pquestions) {
			precord.put(q, 0);
		}
		if (quiz.random) {
			long seed = System.nanoTime();
			Collections.shuffle(pquestions, new Random(seed));
		}
		String url;
		if (quiz.one_page){
			url = "PracticeOnePage.jsp";
		} else {
			url = "PracticeMultiPage.jsp?correct=";
			if (quiz.immediate) {
				url +="immediate";
			} else {
				url +="end";
			}
		}
		
		request.getSession().setAttribute("pquiz", quiz);
		request.getSession().setAttribute("pquestions", pquestions);
		request.getSession().setAttribute("precord", precord);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);	
		
	}

}
