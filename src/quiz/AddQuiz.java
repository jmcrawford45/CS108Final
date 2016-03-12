package quiz;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.Question;
import question.QuestionManager;

/**
 * Servlet implementation class AddQuiz
 */
@WebServlet("/AddQuiz")
public class AddQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuiz() {
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
		Quiz q = (Quiz)request.getSession().getAttribute("newquiz");
		
		qm.addQuiz(q);
		QuestionManager qsm = new QuestionManager(qm.con);
		String ids = "";
		for(int i = 0; i <q.getNumQuestions(); i++) {
			Question qs = q.getQuestionbyIndex(i);
			int id = qsm.addQuestion(qs);
			ids += id + "|";
		}
		qm.setQuizQuestions(q.id, ids);
		

		RequestDispatcher rd = request.getRequestDispatcher("QuizSummary.jsp?quiz_id="+q.id);
		rd.forward(request, response);
		
	}

}
