package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import DAO.Impl.OperaDAO;
import DAO.Impl.OperaDAO_Interface;
import Util.DataLayerException;
import Util.HTMLResult;
import Util.TemplateManagerException;
import Util.TemplateResult;

/**
 * Servlet implementation class modificatabella
 */
public class modificatabella extends HttpServlet {
	private static final long serialVersionUID = 17777L;
	private void action_error(HttpServletRequest request, HttpServletResponse response) {
	    //assumiamo che l'eccezione sia passata tramite gli attributi della request
	    //we assume that the exception has been passed using the request attributes
	    Exception exception = (Exception) request.getAttribute("exception");
	    String message;
	    if (exception != null && exception.getMessage() != null) {
	        message = exception.getMessage();
	    } else {
	        message = "Unknown error";
	    }
	    HTMLResult result = new HTMLResult(getServletContext());
	    result.setTitle("ERROR");
	    result.setBody("<p>" + message + "</p>");
	    try {
	        result.activate(request, response);
	    } catch (IOException ex) {
	        //if error page cannot be sent, try a standard HTTP error message
	        //se non possiamo inviare la pagina di errore, proviamo un messaggio di errore HTTP standard
	        try {
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
	        } catch (IOException ex1) {
	            //if ALSO this error status cannot be notified, write to the server log
	            //se ANCHE questo stato di errore non può essere notificato, scriviamo sul log del server
	            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex1);
	        }
	    }
	}
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
	   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				performTask(request, response);
			} catch (DataLayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TemplateManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			/**
			* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			*/

			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			try {
				performTask(request, response);
			} catch (DataLayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TemplateManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			public void dispatch(javax.servlet.http.HttpServletRequest request,
					javax.servlet.http.HttpServletResponse response, String nextPage)
					throws ServletException, IOException {


					RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
					dispatch.forward(request, response);

					}
			

			public void performTask (HttpServletRequest request, HttpServletResponse response) throws IOException, DataLayerException, ServletException, TemplateManagerException
			{
				HttpSession session=request.getSession();
				int idu=(Integer.parseInt(request.getParameter("idtrascrittore")));
				int  idt=(int) session.getAttribute("idtrascrizione");
				OperaDAO_Interface queryID=new OperaDAO();
				queryID.modificaT(idu,idt);
				JOptionPane.showMessageDialog(null, "UTENTE ABILITATO ALLA MODIFICA", "MESSAGE", JOptionPane.WARNING_MESSAGE);
	         	dispatch(request, response, "mymanager");
	               
	}            
}


  