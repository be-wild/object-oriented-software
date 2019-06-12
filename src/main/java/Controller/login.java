package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Impl.UtenteDAO;
import DAO.Impl.UtenteDAO_Interface;
import Util.TemplateResult;
import Model.User;
import Util.DataLayerException;
import Util.HTMLResult;
import Util.SecurityLayer;
import Util.TemplateManagerException;



/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 102831973230L;
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



	

		public void performTask (HttpServletRequest request, HttpServletResponse response) throws IOException, DataLayerException, ServletException, TemplateManagerException
		{
			TemplateResult res = new TemplateResult(getServletContext());
           Map<String,Object> data=new HashMap();
			UtenteDAO_Interface queryU=new UtenteDAO();
	        String username=request.getParameter("username");
	        String password=request.getParameter("password");
	        User user=queryU.credenzialiUtente(username, password);
	        UtenteDAO_Interface queryD=new UtenteDAO();
		
			if(user != null) {
				SecurityLayer.createSession(request, user.getUsername(), user.getId(), user.getTipo());
                HttpSession session= request.getSession(true);
				UtenteDAO_Interface queryID=new UtenteDAO();	
				UtenteDAO_Interface queryT=new UtenteDAO();	
				String tipo=queryT.ottieniTipo(username,password);
				int id=queryID.ottieniID(username,password);
			    List<User> dati=queryD.visualizzaDati(username,password);
				switch (tipo) {
				case "amministratore":
					data.put("dati",dati);
					res.activate( "amministratore.ftl.html",data,response);
					session.setAttribute("id",id);
			       break;
				case "manager":
					data.put("dati", dati);
					res.activate( "manager.ftl.html",data,response);
					session.setAttribute("id",id);
					break;
				case "uploader":
					data.put("dati", dati);
					res.activate( "uploader.ftl.html",data,response );
					session.setAttribute("id",id);
					break;
				case "trascrittore":
					data.put("dati", dati);
					res.activate( "trascrittore.ftl.html",data,response );
					session.setAttribute("id",id);
					break;
				case "utentebase":
					session.setAttribute("id",id);
					data.put("dati", dati);
					res.activate("profiloutente.ftl.html", data, response);
					break;
				}
		
		}
			else {
				 request.setAttribute("exception", new Exception("Login failed"));
		            action_error(request, response);
			}
		}
}

