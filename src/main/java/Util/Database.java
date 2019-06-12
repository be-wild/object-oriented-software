package Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * @author Andrea
 *
 */

public class Database {
   
   private static DataSource ds;
   
   static{
       try {          
         InitialContext ctx = new InitialContext();
           ds = (DataSource) ctx.lookup("java:comp/env/jdbc/web-library");
       } catch (NamingException ex) {
         Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "ERRORE INIZIALIZZAZIONE DATABASE", ex);
       }
   }
   
   public static Connection getConnection() throws DataLayerException {
       try {
           return ds.getConnection();
       } catch (SQLException ex) {
           throw new DataLayerException("ERRORE CONNESSIONE DATABASE", ex);
       }
   }
}