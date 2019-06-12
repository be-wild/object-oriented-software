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

import DAO.Impl.UtenteDAO;
import DAO.Impl.UtenteDAO_Interface;
import Util.DataLayerException;
import Util.TemplateManagerException;

/**
 * Servlet implementation class livelloaumentato
 */
public class livelloaumentato extends HttpServlet {
	private static final long serialVersionUID = 16666642L;
       
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
			int idutente=(int) session.getAttribute("idutente");
			String livello=request.getParameter("livello");
            UtenteDAO_Interface queryD=new UtenteDAO();
            queryD.promuoviLivello(livello,idutente);
            	JOptionPane.showMessageDialog(null, "TRASCRITTORE PROMOSSO AL NUOVO LIVELLO", "MESSAGE", JOptionPane.WARNING_MESSAGE);
            	dispatch(request, response, "mymanager");
          
		}
}