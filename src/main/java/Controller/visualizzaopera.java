package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Impl.OperaDAO;
import DAO.Impl.OperaDAO_Interface;
import Model.Immagine;
import Model.Opera;
import Util.DataLayerException;
import Util.TemplateManagerException;
import Util.TemplateResult;

/**
 * Servlet implementation class visualizzaopera
 */
public class visualizzaopera extends HttpServlet {
	private static final long serialVersionUID = 17L;
       
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
			int id=0;
			TemplateResult res = new TemplateResult(getServletContext());
			OperaDAO_Interface queryI=new OperaDAO();
			id = (Integer.parseInt(request.getParameter("idopera")));
			if (id!=0) {
				List<Immagine> dati=queryI.visualizzaListaImmagine(id);
			Map<String,Object> data = new HashMap<String,Object>();
                data.put("opere",dati);
                res.activate("visualizzaopera.ftl.html", data, response);
			}
               
}            
}