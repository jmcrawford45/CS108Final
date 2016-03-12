package question;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.FiBQuestion.InvalidFiBException;
import question.MCMAQuestion.InvalidMCMAException;
import question.MCQuestion.InvalidMCException;
import quiz.Quiz;

/**
 * Servlet implementation class SubmitNewQuestionServlet
 */
@WebServlet("/SubmitNewQuestionServlet")
public class SubmitNewQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitNewQuestionServlet() {
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
		Quiz q = (Quiz) request.getSession().getAttribute("newquiz");
		String type = request.getParameter("type");
		if(type.equals("response-question")){
			ResponseQuestion rq = new ResponseQuestion(request.getParameter("question"), request.getParameter("answer"));
			q.addQuestion(rq);
		} else if (type.equals("fib-question")){
			String question = "";
			question += request.getParameter("pre") + "|" + request.getParameter("post");
			try {
				FiBQuestion fib = new FiBQuestion(question, request.getParameter("answer"));
				q.addQuestion(fib);
			} catch (InvalidFiBException e) {
				RequestDispatcher dispatch = request.getRequestDispatcher("FailedQuestionIssue.jsp?problem=Fill in the blank question was formatted incorrectly.");
				dispatch.forward(request, response);
			}
			
		} else if (type.equals("mc-question")){
			try {
				MCQuestion mcq = new MCQuestion(request.getParameter("question"), request.getParameter("answer"), request.getParameter("choices"));
				q.addQuestion(mcq);
			} catch (InvalidMCException e) {
				RequestDispatcher dispatch = request.getRequestDispatcher("FailedQuestionIssue.jsp?problem=The answer did not appear in choices.");
				dispatch.forward(request, response);
			}
			
		} else if (type.equals("mcma-question")){
			try {
				MCMAQuestion mcma = new MCMAQuestion(request.getParameter("question"), request.getParameter("answer"), request.getParameter("choices"));
				q.addQuestion(mcma);
			} catch (InvalidMCMAException e) {
				RequestDispatcher dispatch = request.getRequestDispatcher("FailedQuestionIssue.jsp?problem=At least one answer did not appear in choices.");
				dispatch.forward(request, response);
			}
			
		} else if (type.equals("maresponse-question")){
			Answer answers = Answer.convertStringToAnswer(request.getParameter("answer"));
			MAResponseQuestion marq = new MAResponseQuestion(request.getParameter("question"), answers);
			String orderedString = request.getParameter("ordered");
			if(orderedString == null){
				marq.getAnswer().setIfOrdered(false);
			} else marq.getAnswer().setIfOrdered(true);
			q.addQuestion(marq);
			
			
			
			
		} else if (type.equals("matching-question")){
			MatchingQuestion mq = new MatchingQuestion(request.getParameter("instructions"));
			int numPairs = Integer.parseInt(request.getParameter("numpairs"));
			for(int i = 0; i < numPairs; i ++){
				String questionIdentifier = "question" + i;
				String answerIdentifier = "answer" + i;
				ResponseQuestion currRQ = new ResponseQuestion(request.getParameter(questionIdentifier), request.getParameter(answerIdentifier));
				mq.addPair(currRQ);
			}
			q.addQuestion(mq);
			
		} else if (type.equals("pic-response-question")){
			PictureResponseQuestion prq = new PictureResponseQuestion(request.getParameter("question"), request.getParameter("answer"), request.getParameter("url"));
			q.addQuestion(prq);
			
		} else {
			RequestDispatcher dispatch = request.getRequestDispatcher("FailedQuestionIssue.jsp?problem=The question type was not recognized.");
			dispatch.forward(request, response);
			
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher("CreateQuestions.jsp");
		dispatch.forward(request, response);
	}

}
