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
 * Servlet implementation class FormNewQuiz
 */
@WebServlet("/FormNewQuiz")
public class FormNewQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormNewQuiz() {
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
		request.getSession().removeAttribute("newquiz");
		int quiz_id = TableAbstraction.getID(qm.con);
		
		int creator_id = Integer.parseInt(request.getServletContext().getInitParameter("userid"));
		long time = System.currentTimeMillis();
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		boolean random;
		if(request.getParameter("random") != null) {
			random = true;
		} else {
			random = false;
		}
		boolean onepage;
		boolean immediate;
		if(request.getParameter("multiple") != null) {
			onepage = false;
			if(request.getParameter("immediate") != null) {
				immediate= true;
			} else {
				immediate = false;
			}
		} else {
			onepage = true;
			immediate = false;
		}
		boolean practice;
		if(request.getParameter("practice") != null) {
			practice = true;
		} else {
			practice = false;
		}
		
		Quiz q = new Quiz(quiz_id, creator_id, category_id, description, random, onepage, immediate, time, name, practice);
		request.getSession().setAttribute("newquiz", q);
		String url = "CreateQuestions.jsp?quizid=" + quiz_id;
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
