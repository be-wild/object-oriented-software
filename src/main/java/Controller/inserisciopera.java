package Controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Blob;

import DAO.Impl.OperaDAO;
import DAO.Impl.OperaDAO_Interface;
import Model.Opera;
import Util.DataLayerException;
import Util.HTMLResult;
import Util.SecurityLayer;
import Util.TemplateManagerException;
import Util.TemplateResult;

/**
 * Servlet implementation class inserisciopera
 */
public class inserisciopera extends HttpServlet {
	private static final long serialVersionUID = 19L;
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
				InputStream inputStream = null;
				Part filePart = request.getPart("file");
				inputStream = filePart.getInputStream(); 

				Opera opera=new Opera(0, null, null, null, null,null);
	            opera.setTitolo(request.getParameter("titolo"));
	            opera.setAnno(request.getParameter("anno"));
	            opera.setAutore(request.getParameter("autore"));
	            opera.setStoria(request.getParameter("storia"));
	            opera.setCategoria(request.getParameter("categoria"));
	            opera.setFile(inputStream);
                new OperaDAO().inserisciNuovaOpera(opera); 
                JOptionPane.showMessageDialog(null, "OPERA INSERITA", "MESSAGE", JOptionPane.WARNING_MESSAGE);
                dispatch(request, response, "myamministratore");
			}
			public void dispatch(javax.servlet.http.HttpServletRequest request,
					javax.servlet.http.HttpServletResponse response, String nextPage)
					throws ServletException, IOException {


					RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
					dispatch.forward(request, response);

					}
}
