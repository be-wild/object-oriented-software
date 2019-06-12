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

import DAO.Impl.OperaDAO;
import DAO.Impl.OperaDAO_Interface;
import Model.Immagine;
import Model.Trascrizione;
import Util.DataLayerException;
import Util.TemplateManagerException;
import Util.TemplateResult;

/**
 * Servlet implementation class trascrizionidamodificare
 */

public class trascrizionidamodificare extends HttpServlet {
	private static final long serialVersionUID = 16789L;
       
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
			HttpSession session=request.getSession();
			 Object idutente=session.getAttribute("id");
			TemplateResult res = new TemplateResult(getServletContext());
			OperaDAO_Interface queryI=new OperaDAO();
            List<Trascrizione> dati=queryI.visualizzaListaTrascrizioniModificare((int) idutente);
			Map<String,Object> data = new HashMap<String,Object>();
                data.put("trascrizioni",dati);
                res.activate("visualizzatrascrizionidamodificare.ftl.html", data, response);             
}            

}
