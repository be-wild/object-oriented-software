package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Impl.UtenteDAO;
import DAO.Impl.UtenteDAO_Interface;

import Model.User;
import Util.DataLayerException;
import Util.SecurityLayer;
import Util.TemplateManagerException;
import Util.TemplateResult;

/**
 * Servlet implementation class visualizzatrascrizionem
 */

public class visualizzatrascrizionem extends HttpServlet {
	private static final long serialVersionUID = 1789L;
       
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


		public void performTask (HttpServletRequest request, HttpServletResponse response) throws IOException, DataLayerException, ServletException, TemplateManagerException
		{
			SecurityLayer.createSession(request);
            HttpSession session= request.getSession(true);
            int idtrascrizione=(Integer.parseInt(request.getParameter("idtrascrizione")));
			TemplateResult res = new TemplateResult(getServletContext());
			UtenteDAO_Interface queryI=new UtenteDAO();
            List<User> dati=queryI.visualizzaListaUtentiTrascrittore();
			Map<String,Object> data = new HashMap<String,Object>();
                data.put("utenti",dati);
                session.setAttribute("idtrascrizione",idtrascrizione);
                res.activate("elencotrascrittorimodifica.ftl.html", data, response);
               
}            
}