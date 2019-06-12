package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import DAO.Impl.OperaDAO;
import DAO.Impl.OperaDAO_Interface;

import Model.Opera;
import Util.DataLayerException;
import Util.TemplateManagerException;
import Util.TemplateResult;

/**
 * Servlet implementation class myprofile
 */

public class allopere extends HttpServlet {
	private static final long serialVersionUID = 102831973278L;
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
			TemplateResult res = new TemplateResult(getServletContext());
			OperaDAO_Interface queryO=new OperaDAO();
			 List<Opera> dati=queryO.visualizzaListaOpere();
			Map<String,Object> data = new HashMap<String,Object>();
                data.put("opere",dati);
                res.activate("elencoopere.ftl.html", data, response);
               
}            
}

