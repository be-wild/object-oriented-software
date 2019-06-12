package Controller;

import java.io.IOException;


import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.swing.JOptionPane;




import DAO.Impl.OperaDAO;
import DAO.Impl.OperaDAO_Interface;
import Model.Assegnato;
import Model.Trascrizione;

import Util.DataLayerException;

import Util.TemplateManagerException;

/**
 * Servlet implementation class realizzatrascrizione
 */

public class realizzatrascrizione extends HttpServlet {
	private static final long serialVersionUID = 16789L;
	/**
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	try {
		performTask(request, response);
	} catch (DataLayerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (TemplateManagerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	public void performTask (HttpServletRequest request, HttpServletResponse response) throws IOException, DataLayerException, ServletException, TemplateManagerException, SQLException
	{

		OperaDAO_Interface queryO=new OperaDAO();
		Assegnato assegnato = new Assegnato();
		int idassegnato=(Integer.parseInt(request.getParameter("idassegnato")));
		assegnato.setIdassegnato(idassegnato);
		String data=request.getParameter("data");
        assegnato.setData(data);
		int idopera = (Integer.parseInt(request.getParameter("idopera")));
		assegnato.setIdimmagine(idopera);
        HttpSession session=request.getSession();
		int idutente=(int) session.getAttribute("id");
		assegnato.setIdutente(idutente);
		Trascrizione trascrizione=new Trascrizione();
        trascrizione.setTesto(request.getParameter("testo"));
        int idtrascrizione=queryO.inserisciNuovaTrascrizione(trascrizione); 
		assegnato.setIdtrascrizione(idtrascrizione);
        new OperaDAO().aggiornaAssegnato(assegnato);
        JOptionPane.showMessageDialog(null, "TRASCRIZIONE INSERITA", "MESSAGE", JOptionPane.WARNING_MESSAGE);
        dispatch(request, response, "mytrascrittore");
	}
	public void dispatch(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, String nextPage)
			throws ServletException, IOException {


			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);

			}
}
