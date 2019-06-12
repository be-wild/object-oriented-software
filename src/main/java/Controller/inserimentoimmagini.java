package Controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.swing.JOptionPane;

import DAO.Impl.OperaDAO;
import Model.Immagine;
import Model.Opera;
import Util.DataLayerException;

import Util.TemplateManagerException;

/**
 * Servlet implementation class inserimentoimmagini
 */

public class inserimentoimmagini extends HttpServlet {
	private static final long serialVersionUID = 12367L;
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

			public void performTask (HttpServletRequest request, HttpServletResponse response) throws IOException, DataLayerException, ServletException, TemplateManagerException
			{
			   int id=(Integer.parseInt(request.getParameter("idopera")));
               Immagine immagine=new Immagine(0,null,null,null,0,null);
	           immagine.setDescrizione(request.getParameter("descrizione"));
	           immagine.setImm(request.getParameter("file"));
	           immagine.setOpera(Integer.parseInt(request.getParameter("idopera")));
                new OperaDAO().inserisciNuovaImmagine(immagine,id); 
                JOptionPane.showMessageDialog(null, "IMMAGINE INSERITA", "MESSAGE", JOptionPane.WARNING_MESSAGE);
                dispatch(request, response, "myuploader");
			}
			public void dispatch(javax.servlet.http.HttpServletRequest request,
					javax.servlet.http.HttpServletResponse response, String nextPage)
					throws ServletException, IOException {


					RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
					dispatch.forward(request, response);

					}}
