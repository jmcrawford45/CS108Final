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

import question.Answer;
import question.Question;

/**
 * Servlet implementation class CheckPracticeSP
 */
@WebServlet("/CheckPracticeSP")
public class CheckPracticeSP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckPracticeSP() {
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
		ArrayList<Question> pqs = (ArrayList<Question>)request.getSession().getAttribute("pquestions");
		HashMap<Question, Integer> pr = (HashMap<Question, Integer>)request.getSession().getAttribute("precord");
		for (int i = pqs.size()- 1; i >=0; i--) {
			Question q = pqs.get(i);

			String inpt = request.getParameter("input"+ i);
			Answer a = new Answer(inpt);
			if(q.isCorrectAnswer(a)) {
				
				int n = pr.get(q) + 1;
				pr.put(q, n);
				if(n >= 3) {
					pqs.remove(i);
				}
			}
		}
	
		
		Quiz q = (Quiz)request.getSession().getAttribute("pquiz");
		if (q.random){
			long seed = System.nanoTime();
			Collections.shuffle(pqs, new Random(seed));
		}
		
		String url;
		request.getSession().removeAttribute("pquestions");
		request.getSession().removeAttribute("precord");
		if(pqs.isEmpty()) {
			url = "PracticeOver.jsp?quiz_id=" + q.id;
			request.getSession().removeAttribute("pquiz");
		} else {
			url = "PracticeOnePage.jsp";	
			request.getSession().setAttribute("pquestions", pqs);
			request.getSession().setAttribute("precord", pr);
		}
		
	
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

}
