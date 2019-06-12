package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Util.SecurityLayer;


/**
 * Servlet implementation class logout
 */
public class logout extends HttpServlet {
	private static final long serialVersionUID = 10L;
       
	  Map<String,Object> data= new HashMap<String,Object>();
	  public void dispatch(javax.servlet.http.HttpServletRequest request,
				javax.servlet.http.HttpServletResponse response, String nextPage)
				throws ServletException, IOException {


				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
				dispatch.forward(request, response);

				}
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
                              throws ServletException, IOException {
			
			 try {
		            SecurityLayer.disposeSession(request);
		            dispatch(request, response, "homePage");
		        } catch (IOException ex) {
		            request.setAttribute("exception", ex);
		            System.out.println("NON VA");
		        }
	}
}
