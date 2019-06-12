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
import Util.TemplateResult;

/**
 * Servlet implementation class assegnaatrascrittore
 */

public class assegnaatrascrittore extends HttpServlet {
	private static final long serialVersionUID = 1110L;
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

	public void dispatch(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, String nextPage)
			throws ServletException, IOException {


			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);

			}
		public void performTask (HttpServletRequest request, HttpServletResponse response) throws IOException, DataLayerException, ServletException, TemplateManagerException
		{

			
			HttpSession session=request.getSession();
			int idutente=(Integer.parseInt(request.getParameter("idtrascrittore")));
			Object idimmagine=session.getAttribute("idimmagine");
			OperaDAO_Interface queryID=new OperaDAO();
			queryID.assegnaImmagine(idutente, idimmagine);
			JOptionPane.showMessageDialog(null, "IMMAGINE ASSEGNATA", "MESSAGE", JOptionPane.WARNING_MESSAGE);
         	dispatch(request, response, "mymanager");
	
               
}     
}