package Controller;

import java.io.IOException;

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
import DAO.Impl.UtenteDAO;
import DAO.Impl.UtenteDAO_Interface;
import Model.Modifica;
import Util.DataLayerException;
import Util.TemplateManagerException;
import Util.TemplateResult;

/**
 * Servlet implementation class modificatrascrizione
 */

public class modificatrascrizione extends HttpServlet {
	private static final long serialVersionUID = 1567899L;
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
			int idtrascrizione=(Integer.parseInt(request.getParameter("idtrascrizione")));
			String data=request.getParameter("data");
			Modifica modifica=new Modifica();
			modifica.setNuovo_testo(request.getParameter("testo_modificato"));
			modifica.setIdtrascrizione(idtrascrizione);
			modifica.setData(data);
           OperaDAO_Interface queryD=new OperaDAO();
           queryD.modificaTrascrizione(modifica);
            	JOptionPane.showMessageDialog(null, "MODIFICA APPROVATA,ORA E' IN FASE DI  ANALISI", "MESSAGE", JOptionPane.WARNING_MESSAGE); 
            	dispatch(request, response, "mytrascrittore");
          
		}
}
