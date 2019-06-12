package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Impl.UtenteDAO;
import DAO.Impl.UtenteDAO_Interface;
import Model.User;
import Util.DataLayerException;
import Util.FailureResult;
import Util.HTMLResult;
import Util.SecurityLayer;
import Util.TemplateManagerException;
import Util.TemplateResult;

/**
 * Servlet implementation class registration
 */

public class registration extends HttpServlet {
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

		public void performTask (HttpServletRequest request, HttpServletResponse response) throws IOException, DataLayerException, ServletException, TemplateManagerException
		{
			TemplateResult res = new TemplateResult(getServletContext());
			User utente = new User();
            utente.setNome(request.getParameter("nome"));
            utente.setCognome(request.getParameter("cognome"));
            utente.setEta(Integer.parseInt(request.getParameter("eta")));
            utente.setSesso(request.getParameter("sesso"));
            utente.setUsername(request.getParameter("username"));
            utente.setEmail(request.getParameter("email"));
            utente.setPassword(request.getParameter("password"));
            utente.setProfessione(request.getParameter("professione"));
            utente.setTitolo(request.getParameter("titolo"));
            utente.setAnniesp(Integer.parseInt(request.getParameter("anniesperienzelavorative")));
            utente.setEsplavorative(request.getParameter("esperienzelavorative"));
            UtenteDAO_Interface queryC=new UtenteDAO();
            UtenteDAO_Interface queryD=new UtenteDAO();
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            boolean esiste=queryC.utenteEsistente(username);
            if (esiste==false) {
                new UtenteDAO().inserisciNuovoUtente(utente);
                SecurityLayer.createSession(request, utente.getUsername(), utente.getId(), utente.getTipo());
                HttpSession session= request.getSession(true);
                UtenteDAO_Interface queryID=new UtenteDAO();
                int id=queryID.ottieniID(username,password);
                Map<String,Object> data = new HashMap<String,Object>();
                List<User> dati=queryD.visualizzaDati(username,password);
                data.put("dati",dati);
                session.setAttribute("id",id);
                res.activate("profiloutente.ftl.html", data, response);
               
}
            
            else {
				 request.setAttribute("exception", new Exception("Username non disponibile"));
				 action_error(request, response);   
			}
		}
}
