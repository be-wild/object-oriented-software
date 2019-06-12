package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import DAO.Impl.OperaDAO;
import DAO.Impl.OperaDAO_Interface;
import Util.DataLayerException;
import Util.TemplateManagerException;

/**
 * Servlet implementation class assegnat
 */

public class assegnat extends HttpServlet {
	private static final long serialVersionUID = 16667L;
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
			
			int idimmagine = (Integer.parseInt(request.getParameter("idimmagine")));
			int idtrascrizione = (Integer.parseInt(request.getParameter("idtrascrizione")));
            OperaDAO_Interface queryD=new OperaDAO();
            queryD.approvaTrascrizione(idimmagine,idtrascrizione);
           
            	JOptionPane.showMessageDialog(null, "IMMAGINE CON LA RELATIVA TRASCRIZIONE PUBBLICATA", "MESSAGE", JOptionPane.WARNING_MESSAGE);
            	dispatch(request, response, "mymanager");
            
           
		}
}