package Controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Util.DataLayerException;
import Util.FailureResult;
import Util.HTMLResult;
import Util.SecurityLayer;
import Util.SecurityLayerException;
import Util.StreamResult;
import Util.TemplateManagerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import DAO.Impl.OperaDAO_Interface;
import DAO.Impl.UtenteDAO;
import DAO.Impl.UtenteDAO_Interface;
import Model.Opera;


/**
 * Servlet implementation class download
 */
public class operadownload extends HttpServlet {
	private static final long serialVersionUID = 1111L;
	private OperaDAO_Interface OperaDao;
	private OperaDAO_Interface ImmagineDao;
	private void action_error(HttpServletRequest request, HttpServletResponse response) {
        if (request.getAttribute("exception") != null) {
            (new FailureResult(getServletContext())).activate((Exception) request.getAttribute("exception"), request, response);
        } else {
            (new FailureResult(getServletContext())).activate((String) request.getAttribute("message"), request, response);
        }
    }

    private void performTask(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateManagerException, SecurityLayerException, DataLayerException {

        
    	int idopera = (Integer.parseInt(request.getParameter("idopera")));
        int fileLength;
        UtenteDAO_Interface queryD=new UtenteDAO();
        try {
            InputStream fileInputStream = queryD.downloadOpera(idopera);

            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline; filename=opera.pdf");
            fileLength = fileInputStream.available();
            response.setContentLength(fileLength);

            OutputStream responseOutputStream = response.getOutputStream();

            int bytes;
            while ((bytes = fileInputStream.read()) != -1) {
                responseOutputStream.write(bytes);
            }
            fileInputStream.close();
        } catch (DataLayerException e) {
            request.setAttribute("message", "Data access exception: " + e.getMessage());
            action_error(request, response);
        }
    }


     /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
			performTask(request, response);
		} catch (TemplateManagerException | SecurityLayerException | DataLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
			performTask(request, response);
		} catch (TemplateManagerException | SecurityLayerException | DataLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

