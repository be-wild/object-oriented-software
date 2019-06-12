package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Impl.OperaDAO;
import DAO.Impl.OperaDAO_Interface;
import DAO.Impl.UtenteDAO;
import DAO.Impl.UtenteDAO_Interface;
import Model.Opera;
import Model.User;
import Util.DataLayerException;

import Util.TemplateManagerException;
import Util.TemplateResult;

/**
 * Servlet implementation class verificadownload
 */
public class verificadownload extends HttpServlet {
	private static final long serialVersionUID = 11L;
	public void dispatch(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, String nextPage)
			throws ServletException, IOException {


			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);

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
			UtenteDAO_Interface queryD=new UtenteDAO();
			HttpSession session=request.getSession();
			int idutente=(int) session.getAttribute("id");
			int verifica=queryD.verificaDownload(idutente);
		    if(verifica==1) {
		    	OperaDAO_Interface queryO=new OperaDAO();
				 List<Opera> dati=queryO.visualizzaListaOpere();
				Map<String,Object> data = new HashMap<String,Object>();
	                data.put("opere",dati);
	                res.activate("downloadopere.ftl.html", data, response);
		    }
			
			else {
				UtenteDAO_Interface queryID=new UtenteDAO();
				 List<User> dati=queryID.visualizzaDatiPerID(idutente);
				Map<String,Object> data = new HashMap<String,Object>();
	                data.put("dati",dati);
	                res.activate("richiedidownload.ftl.html", data, response);
			}
		}
}
