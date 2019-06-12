package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class operains
 */
public class operains extends HttpServlet {
	private static final long serialVersionUID = 167L;
	@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
dispatch(request, response, "templates/inserisciopera.ftl.html");
}
	public void dispatch(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, String nextPage)
			throws ServletException, IOException {


			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);

			}
}