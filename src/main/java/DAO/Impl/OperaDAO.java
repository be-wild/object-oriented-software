package DAO.Impl;

import java.io.InputStream;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

import Util.DataLayerException;
import Model.Assegnato;
import Model.Immagine;
import Model.Modifica;
import Model.Opera;
import Model.Trascrizione;
import Model.User;
import Util.Database;

/**
 * @author Andrea
 *
 */

public class OperaDAO implements OperaDAO_Interface{
	private static final String GETOPEREPERUTENTE="SELECT opera.titolo,immagine.descrizione,immagine.imm,trascrizione.testo  FROM opera,trascrizione,assegnato,immagine WHERE assegnato.idutente=? AND assegnato.idimmagine=immagine.idimmagine AND assegnato.idtrascrizione=trascrizione.idtrascrizione AND trascrizione.pubblicata='si' AND immagine.idopera=opera.idopera ";
	private static final String GET_IMMAGINEMODIFICARE="SELECT trascrizione.idtrascrizione,immagine.idimmagine,immagine.descrizione,immagine.imm,trascrizione.testo,opera.titolo FROM opera,trascrizione,immagine WHERE opera.idopera=immagine.idopera AND immagine.idtrascrizione=trascrizione.idtrascrizione AND trascrizione.pubblicata='si' AND immagine.pubblicata='si' AND trascrizione.modificata='no' ";
	private static final String GET_OPERE="SELECT idopera,titolo,autore,anno,storia,categoria FROM Opera ";
	private static final String GET_OPERA_ID="SELECT titolo FROM Opera WHERE idopera=? ";
	private static final String SET_OPERA="INSERT INTO Opera (titolo,autore,anno,storia,categoria,file) VALUES(?,?,?,?,?,?) ";
	private static final String REMOVE_OPERA="DELETE FROM Opera WHERE idopera=? ";
	private static final String GET_TRASCRIZIONI="SELECT trascrizione.testo,trascrizione.idtrascrizione,immagine.imm,immagine.descrizione FROM trascrizione,immagine WHERE trascrizione.pubblicata='si' AND immagine.idtrascrizione=trascrizione.idtrascrizione";
	private static final String GET_TRASCRIZIONI_PER_IMMAGINE="SELECT testo FROM Trascrizione,Immagine WHERE Id.Opera=Id_Opera.Immagine AND pubblicata.Trascrizione=true ";
	private static final String SET_TRASCRIZIONE="INSERT INTO trascrizione(testo,validata,modificata,pubblicata) VALUES(?,'no','no','no') ";
	private static final String SET_TRASCRIZIONEOPERA="UPDATE assegnato SET idtrascrizione=? WHERE idimmagine=? AND idutente=? AND data=? AND idassegnato=?; ";
	private static final String APPROVA_TRASCRIZIONEMODIFICATA="UPDATE trascrizione,modifica SET modifica.buonfine='si',trascrizione.testo=modifica.nuovo_testo,trascrizione.modificata='no' WHERE modifica.data=? AND trascrizione.idtrascrizione=? AND modifica.idutente=?";
	private static final String REMOVE_TRASCRIZIONE="DELETE FROM Trascrizione WHERE idtrascrizione=? ";
	private static final String APPROVA_TRASCRIZIONE="UPDATE trascrizione,immagine SET immagine.pubblicata='si',trascrizione.validata='si',trascrizione.pubblicata='si' WHERE immagine.idimmagine=? AND trascrizione.idtrascrizione=?";
	private static final String PUBBLICA_TRASCRIZIONE="UPDATE Trascrizione SET pubblicata=true WHERE id=?";
	private static final String REVISIONA_TRASCRIZIONE="UPDATE Trascrizione SET modificata=true AND testo=testo WHERE id=?";
	private static final String GET_TRASCRIZIONI_NONVALIDATE="SELECT testo,id,descrizione FROM Trascrizione;Immagine WHERE validata= And idimmagine.Immagine=idtrascrizione.Trascrizione";
	private static final String GET_IMMAGINE="SELECT immagine.descrizione,immagine.imm,trascrizione.testo FROM immagine,trascrizione WHERE immagine.idopera=? AND immagine.idtrascrizione=trascrizione.idtrascrizione AND trascrizione.pubblicata='si' AND immagine.pubblicata='si' ";
	private static final String GET_IMMAGINENONPUBBLICATE="SELECT immagine.idimmagine,immagine.descrizione,immagine.imm,opera.idopera,opera.titolo FROM immagine,opera WHERE  pubblicata='no' AND immagine.idopera=opera.idopera";
    private static final String SET_IMMAGINE="INSERT INTO Immagine (descrizione,imm,pubblicata,idopera) VALUES(?,?,'no',?)";
	private static final String REMOVE_IMMAGINE="DELETE FROM Immagine WHERE idimmagine=?";
	private static final String ASSEGNA_IMMAGINE="INSERT INTO assegnato (data,idimmagine,idutente) VALUES(CURRENT_DATE(),?,?) ";
	private static final String FILTRA_OPERE="SELECT idopera,titolo,autore,anno,storia,categoria FROM Opera WHERE titolo=? OR autore=? OR anno=? OR categoria=? ";
	private static final String TRASCRIZIONIFATTE="SELECT assegnato.data,immagine.descrizione,immagine.imm,trascrizione.testo FROM assegnato,immagine,trascrizione WHERE assegnato.idutente=? AND assegnato.idimmagine=immagine.idimmagine AND immagine.idtrascrizione=trascrizione.idtrascrizione AND trascrizione.pubblicata='si' AND trascrizione.validata='si'";
	private static final String TRASCRIZIONIDAFARE="SELECT immagine.idimmagine,assegnato.data,immagine.descrizione,immagine.imm,assegnato.idassegnato FROM assegnato,immagine WHERE assegnato.idutente=? AND assegnato.idimmagine=immagine.idimmagine  AND immagine.pubblicata='no' ";
	private static final String TRASCRIZIONIDAMODIFICARE="SELECT immagine.imm,immagine.descrizione,trascrizione.testo,modifica.idmodifica,modifica.data,modifica.nuovo_testo,modifica.idtrascrizione FROM immagine,trascrizione,modifica WHERE modifica.idutente=? AND modifica.idtrascrizione=immagine.idtrascrizione AND trascrizione.idtrascrizione=immagine.idtrascrizione AND trascrizione.idtrascrizione=modifica.idtrascrizione AND modifica.buonfine!='si'  AND trascrizione.modificata='no' ";
	private static final String INSERISCINUOVOTESTO="UPDATE modifica,trascrizione SET modifica.nuovo_testo=?,trascrizione.modificata='si'  WHERE  modifica.idtrascrizione=trascrizione.idtrascrizione AND trascrizione.modificata='no' AND modifica.idtrascrizione=?  AND modifica.data=?";
	private static final String TRASCRIZIONIDAMODIFICARE1="SELECT immagine.idimmagine,trascrizione.idtrascrizione,modifica.data,trascrizione.testo,modifica.nuovo_testo,immagine.descrizione,immagine.imm,opera.titolo,user.id,user.nome,user.cognome,user.livello FROM modifica,immagine,opera,trascrizione,user  WHERE trascrizione.idtrascrizione=immagine.idtrascrizione AND  modifica.idutente=user.id AND modifica.idtrascrizione=trascrizione.idtrascrizione AND immagine.idopera=opera.idopera AND trascrizione.modificata='si' AND modifica.buonfine='no' ";
	private static final String TRASCRIZIONIDAAPPROVARE="SELECT user.tipo,immagine.idimmagine,trascrizione.idtrascrizione,assegnato.data,trascrizione.testo,immagine.descrizione,immagine.imm,opera.titolo,user.nome,user.cognome,user.livello FROM assegnato,immagine,opera,trascrizione,user  WHERE assegnato.idimmagine=immagine.idimmagine AND assegnato.idutente=user.id AND assegnato.idtrascrizione=trascrizione.idtrascrizione AND immagine.idopera=opera.idopera AND immagine.pubblicata='no' AND trascrizione.validata='no' ";
    private static final String MODIFICATRASCRIZIONE="INSERT INTO modifica (data,idutente,idtrascrizione,buonfine) VALUES (CURRENT_DATE(),?,?,'no') ";
	private static final String RIASSEGNAT="UPDATE assegnato SET idutente=?,idtrascrizione='0' WHERE idassegnato=? ";
    private static final String DISAPPROVA_TRASCRIZIONEMODIFICATA="UPDATE trascrizione SET modificata='no' WHERE trascrizione.idtrascrizione=? ";
	private static final String MODIFICATRA="UPDATE trascrizione SET testo=? WHERE idtrascrizione=? ";

    @Override
    public boolean modificaTra(int idt,String testo) throws DataLayerException {
  PreparedStatement ps;
        boolean ok = false;
        try (Connection connection = Database.getConnection()) {
            ps = connection.prepareStatement(MODIFICATRA);
            ps.setInt(2, idt);
            ps.setString(1, testo);
            if (ps.executeUpdate() == 1) {
 	           
	            ok=true;
	            ps.close();
	            connection.close();
	            }    
        } catch (SQLException ex) {
            throw new DataLayerException("Error ", ex);
        }
        return ok;
    }
    @Override
	    public boolean modificaT(int idu,int idt) throws DataLayerException {
      PreparedStatement ps;
	        boolean ok = false;
	        try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(MODIFICATRASCRIZIONE);
	            ps.setInt(1, idu);
	            ps.setInt(2, idt);
	            if (ps.executeUpdate() == 1) {
	 	           
		            ok=true;
		            ps.close();
		            connection.close();
		            }    
	        } catch (SQLException ex) {
	            throw new DataLayerException("Error ", ex);
	        }
	        return ok;
	    }
		 @Override
		    public boolean riassegnaT(int idu,int ida) throws DataLayerException {
	      PreparedStatement ps;
		        boolean ok = false;
		        try (Connection connection = Database.getConnection()) {
		            ps = connection.prepareStatement(RIASSEGNAT);
		            ps.setInt(1, idu);
		            ps.setInt(2, ida);
		            if (ps.executeUpdate() == 1) {
		 	           
			            ok=true;
			            ps.close();
			            connection.close();
			            }    
		        } catch (SQLException ex) {
		            throw new DataLayerException("Error ", ex);
		        }
		        return ok;
		    }
	@Override
	public List<Trascrizione> visualizzaListaTrascrizioniDaApprovare() throws DataLayerException {
		List<Trascrizione> trascrizioni= new ArrayList();
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(TRASCRIZIONIDAAPPROVARE)) {
	                try (ResultSet rset = ps.executeQuery()) {
	                    while (rset.next()) {
	                    	Assegnato assegnato=new Assegnato(rset.getString("data"));
	                    	Immagine immagine=new Immagine(rset.getInt("idimmagine"),rset.getString("descrizione"),rset.getString("imm"));
	                    	Opera opera=new Opera(rset.getString("titolo"));
	                    	User utente=new User(rset.getString("nome"),rset.getString("cognome"),rset.getInt("livello"),rset.getString("tipo"));
	                        trascrizioni.add(new Trascrizione(assegnato,rset.getString("testo"),rset.getInt("idtrascrizione"), immagine,opera,utente));
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("GET TRASCRIZIONI", ex);
	        }
		return trascrizioni;
}
	@Override
	public List<Trascrizione> visualizzaListaTrascrizioniModificateDaApprovare() throws DataLayerException {
		List<Trascrizione> trascrizioni= new ArrayList();
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(TRASCRIZIONIDAMODIFICARE1)) {
	                try (ResultSet rset = ps.executeQuery()) {
	                    while (rset.next()) {
	                    	Modifica modifica=new Modifica(rset.getString("data"),rset.getString("nuovo_testo"));
	                    	Immagine immagine=new Immagine(rset.getInt("idimmagine"),rset.getString("descrizione"),rset.getString("imm"));
	                    	Opera opera=new Opera(rset.getString("titolo"));
	                    	User utente=new User(rset.getInt("id"),rset.getString("nome"),rset.getString("cognome"),rset.getInt("livello"));
	                        trascrizioni.add(new Trascrizione(modifica,rset.getString("testo"),rset.getInt("idtrascrizione"), immagine,opera,utente));
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("GET TRASCRIZIONI", ex);
	        }
		return trascrizioni;
}
	@Override
	public List<Opera> visualizzaListaOpere() throws DataLayerException {
		List<Opera> opere= new ArrayList();
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(GET_OPERE)) {
	                try (ResultSet rset = ps.executeQuery()) {
	                    while (rset.next()) {
	                        opere.add(new Opera(rset.getInt("idopera"),rset.getString("titolo"), rset.getString("autore"),rset.getString("anno"),rset.getString("storia"),rset.getString("categoria")));
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("GET OPERE", ex);
	        }
		return opere;
}
	@Override
	public List<Opera> visualizzaListaOperePerUtente(int id) throws DataLayerException {
		List<Opera> opere= new ArrayList();
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(GETOPEREPERUTENTE)) {
	            	ps.setInt(1, id);
	                try (ResultSet rset = ps.executeQuery()) {
	                    while (rset.next()) {
	                    	Trascrizione trascrizione=new Trascrizione(rset.getString("testo"));
	                    	Immagine immagine=new Immagine(rset.getString("imm"),rset.getString("descrizione"));
	                        opere.add(new Opera(rset.getString("titolo"),trascrizione,immagine));
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("GET OPERE", ex);
	        }
		return opere;
}
	@Override
	public List<Immagine> visualizzaListaImmaginiNonPubblicate() throws DataLayerException {
		List<Immagine> immagine= new ArrayList();
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(GET_IMMAGINENONPUBBLICATE)) {
	                try (ResultSet rset = ps.executeQuery()) {
	                    while (rset.next()) {
	                    	Opera opera=new Opera(rset.getInt("idopera"),rset.getString("titolo"));
	                        immagine.add(new Immagine(rset.getInt("idimmagine"),rset.getString("descrizione"), rset.getString("imm"),opera));
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("GET OPERE", ex);
	        }
		return immagine;
}
	@Override
	public List<Immagine> visualizzaListaTrascrizioniFatte(int id) throws DataLayerException {
		List<Immagine> opere= new ArrayList();
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(TRASCRIZIONIFATTE)) {
	            	ps.setInt(1, id);
	                try (ResultSet rset = ps.executeQuery()) {
	                    while (rset.next()) {
	                        opere.add(new Immagine(rset.getString("data"),rset.getString("descrizione"), rset.getString("imm"),rset.getString("testo")));
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("GET OPERE", ex);
	        }
		return opere;
}
	@Override
	public List<Trascrizione> visualizzaListaTrascrizioniModificare(int id) throws DataLayerException {
		List<Trascrizione> trascrizione= new ArrayList();
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(TRASCRIZIONIDAMODIFICARE)) {
	            	ps.setInt(1, id);
	                try (ResultSet rset = ps.executeQuery()) {
	                    while (rset.next()) {
	                    	Immagine immagine=new Immagine(rset.getString("imm"),rset.getString("descrizione"));
	                    	Modifica modifica=new Modifica(rset.getInt("idmodifica"),rset.getString("data"),rset.getString("nuovo_testo"),rset.getInt("idtrascrizione"));
	                        trascrizione.add(new Trascrizione(immagine,rset.getString("testo"),rset.getInt("idtrascrizione"),modifica));
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("GET OPERE", ex);
	        }
		return trascrizione;
}
	@Override
	public List<Immagine> visualizzaListaTrascrizioniDaFare(int id) throws DataLayerException {
		List<Immagine> opere= new ArrayList();
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(TRASCRIZIONIDAFARE)) {
	            	ps.setInt(1, id);
	                try (ResultSet rset = ps.executeQuery()) {
	                    while (rset.next()) {
	                        opere.add(new Immagine(rset.getInt("idimmagine"),rset.getInt("idassegnato"),rset.getString("data"),rset.getString("descrizione"), rset.getString("imm")));
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("GET OPERE", ex);
	        }
		return opere;
}
	@Override
	public String visualizzaOperaID(int id) throws DataLayerException {
		String titolo="";
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(GET_OPERA_ID)) {
	            	ps.setInt(1, id);
	                try (ResultSet rset = ps.executeQuery()) {
	                    while (rset.next()) {
                         titolo=rset.getString("titolo");
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("error", ex);
	        }
		return titolo;
}
	@Override
	public Opera inserisciNuovaOpera (Opera opera) throws DataLayerException{

	        try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(SET_OPERA, Statement.RETURN_GENERATED_KEYS)) {
	                //Prepare statement
	                ps.setString(1, opera.getTitolo());
	                ps.setString(2, opera.getAutore());
	                ps.setString(3, (String) opera.getAnno());
	                ps.setString(4, opera.getStoria());
	                ps.setString(5, opera.getCategoria());
	                ps.setBlob(6, opera.getFile());

	                ps.executeUpdate();
	                
	                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	                    if (generatedKeys.next()) {
	                        opera.setIdopera(generatedKeys.getInt(1));
	                    } else {
	                        throw new DataLayerException("RECUPERO OPERA");
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("NUOVA OPERA", ex);
	        }

	        return opera;
	    }
	 @Override
	    public boolean eliminaOpera(int id) throws DataLayerException {
		   PreparedStatement ps;
	        boolean ok = false;
	        try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(REMOVE_OPERA);
	            ps.setInt(1, id);
	            if (ps.executeUpdate() == 1) {
	 	           
		            ok=true;
		            ps.close();
		            connection.close();
		            }    
	        } catch (SQLException ex) {
	            throw new DataLayerException("Error remove user", ex);
	        }
	        return ok;
	    }

	 @Override
	 public List<Trascrizione> visualizzaListaTrascrizioni() throws DataLayerException {
			List<Trascrizione> trascrizioni= new ArrayList();
			 try (Connection connection = Database.getConnection()) {
		            try (PreparedStatement ps = connection.prepareStatement(GET_TRASCRIZIONI)) {
		                try (ResultSet rset = ps.executeQuery()) {
		                    while (rset.next()) {
		                    	Immagine immagine=new Immagine(rset.getString("imm"),rset.getString("descrizione"));
		                        trascrizioni.add(new Trascrizione(rset.getInt("idtrascrizione"),rset.getString("testo"),immagine));
		                    }
		                }
		            }
		        } catch (SQLException ex) {
		            throw new DataLayerException("GET TRASCRIZIONI", ex);
		        }
			return trascrizioni;
	}
	 @Override
	 public Trascrizione visualizzaTrascrizionePerImmagine(Trascrizione trascrizione) throws DataLayerException{
		 PreparedStatement ps;
	     ResultSet rset;

	        try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(GET_TRASCRIZIONI_PER_IMMAGINE);
	            ps.setInt(1, trascrizione.getId());
	            rset = ps.executeQuery();

	            if (rset.next()) {

	                trascrizione.setTesto(rset.getString("testo"));
	                
	            }

	            ps.close();
	            connection.close();
	        } catch (SQLException ex) {
	            throw new DataLayerException("GET TRANSCRIPTION", ex);
	        }
	        return trascrizione;
	    }
	 @Override
		public int inserisciNuovaTrascrizione (Trascrizione trascrizione) throws DataLayerException{
            int id=0;
		        try (Connection connection = Database.getConnection()) {
		            try (PreparedStatement ps = connection.prepareStatement(SET_TRASCRIZIONE, Statement.RETURN_GENERATED_KEYS)) {
		                //Prepare statement
		                ps.setString(1, trascrizione.getTesto());
		               
		                ps.executeUpdate();
		                
		                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
		                    if (generatedKeys.next()) {
		                    	id=trascrizione.setId(generatedKeys.getInt(1));
		                        
		                    } else {
		                        throw new DataLayerException("RECUPERO TRASCRIZIONE");
		                    }
		                }
		            }
		        } catch (SQLException ex) {
		            throw new DataLayerException("NUOVA TRASCRIZIONE", ex);
		        }

		        return id;
		    }
	 @Override
	    public boolean aggiornaAssegnato(Assegnato assegnato) throws DataLayerException {
         PreparedStatement ps;
	        boolean ok = false;
	        try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(SET_TRASCRIZIONEOPERA);
	            ps.setInt(1, assegnato.getIdtrascrizione());
	            ps.setString(4, assegnato.getData());
	            ps.setInt(2, assegnato.getIdimmagine());
	            ps.setInt(3, assegnato.getIdutente());
	            ps.setInt(5, assegnato.getIdassegnato());
	            if (ps.executeUpdate() == 1) {
	 	           
		            ok=true;
		            ps.close();
		            connection.close();
		            }    
	        } catch (SQLException ex) {
	            throw new DataLayerException("Error ", ex);
	        }
	        return ok;
	    }
	 @Override
	    public int eliminaTrascrizione(Trascrizione trascrizione) throws DataLayerException {

	        PreparedStatement ps;
	        int result = 0;
	        try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(REMOVE_TRASCRIZIONE);
	            ps.setString(1, trascrizione.getTesto());

	            result = ps.executeUpdate();

	            ps.close();
	            connection.close();
	        } catch (SQLException ex) {
	            throw new DataLayerException("Error remove transcription", ex);
	        }
	        return result;
	    }
	 @Override
	 public void approvaTrascrizione(int idi,int idt) throws DataLayerException{
		   PreparedStatement ps;
            try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(APPROVA_TRASCRIZIONE);
	            ps.setInt(1, idi);
	            ps.setInt(2, idt);
	            if (ps.executeUpdate() == 1) {
	            System.out.println("The transcription has been approvated");    
	            ps.close();
	            connection.close();
	            }    
	        } catch (SQLException e) {
	            throw new DataLayerException("ERRORE APPROVATION", e);
	        }

	    }	
	 @Override
	 public void approvaTrascrizioneModificata(String data,int idt,int idu) throws DataLayerException{
		   PreparedStatement ps;
            try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(APPROVA_TRASCRIZIONEMODIFICATA);
	            ps.setString(1, data);
	            ps.setInt(2, idt);
	            ps.setInt(3, idu);
	            if (ps.executeUpdate() == 1) {
	            System.out.println("The transcription has been modified");    
	            ps.close();
	            connection.close();
	            }    
	        } catch (SQLException e) {
	            throw new DataLayerException("ERRORE APPROVATION", e);
	        }

	    }	
	 @Override
	 public void disapprovaTrascrizioneModificata(int idt) throws DataLayerException{
		   PreparedStatement ps;
            try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(DISAPPROVA_TRASCRIZIONEMODIFICATA);
	            ps.setInt(1, idt);
	            if (ps.executeUpdate() == 1) {
	            System.out.println("The transcription has been modified");    
	            ps.close();
	            connection.close();
	            }    
	        } catch (SQLException e) {
	            throw new DataLayerException("ERRORE APPROVATION", e);
	        }

	    }	
	 @Override
	 public void pubblicaTrascrizione(String testo)  throws DataLayerException{
		 PreparedStatement ps;
         try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(PUBBLICA_TRASCRIZIONE);
	            if (ps.executeUpdate() == 1) {
	            System.out.println("The transcription has been pubblicated");    
	            ps.close();
	            connection.close();
	            }    
	        } catch (SQLException e) {
	            throw new DataLayerException("ERRORE PUBBLICATION", e);
	        }	 
	 }
	 @Override
	 public void revisionaTrascrizione(String testo)  throws DataLayerException{
		   PreparedStatement ps;
           try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(REVISIONA_TRASCRIZIONE);
	            if (ps.executeUpdate() == 1) {
	            System.out.println("The transcription has been modified");    
	            ps.close();
	            connection.close();
	            }    
	        } catch (SQLException e) {
	            throw new DataLayerException("Error", e);
	        }

	    }
	 @Override
	 public boolean modificaTrascrizione(Modifica modifica)  throws DataLayerException {
		 PreparedStatement ps;
		 boolean ok=false;
         try (Connection connection = Database.getConnection()) {  
               ps = connection.prepareStatement(INSERISCINUOVOTESTO);
            ps.setString(1, modifica.getNuovo_testo());
           	ps.setInt(2, modifica.getIdtrascrizione());
           	ps.setString(3, modifica.getData());
           
                if (ps.executeUpdate() == 1) {
	            	ok=true;
	            System.out.println("The transcription has been modified");    
	            ps.close();
	            connection.close();
	            }    
	        } catch (SQLException e) {
	            throw new DataLayerException("Error", e);
	        }	
         return ok;
	 }
	 @Override
	 public List<Trascrizione> visualizzaListaTrascrizioniNonValidate() throws DataLayerException {
			List<Trascrizione> trascrizioni= new ArrayList();
			 try (Connection connection = Database.getConnection()) {
		            try (PreparedStatement ps = connection.prepareStatement(GET_TRASCRIZIONI_NONVALIDATE)) {
		                try (ResultSet rset = ps.executeQuery()) {
		                    while (rset.next()) {
		                        trascrizioni.add(new Trascrizione(rset.getInt("id"),rset.getString("testo")));
		                    }
		                }
		            }
		        } catch (SQLException ex) {
		            throw new DataLayerException("GET TRASCRIZIONI", ex);
		        }
			return trascrizioni;
	}
	 @Override
		public List<Immagine> visualizzaListaImmagine(int id) throws DataLayerException {
			List<Immagine> listaopere= new ArrayList();
			 try (Connection connection = Database.getConnection()) {
		            try (PreparedStatement ps = connection.prepareStatement(GET_IMMAGINE)) {
		            	ps.setInt(1, (int) id);
		                try (ResultSet rset = ps.executeQuery()) {
		                    while (rset.next()) {
		                    	Trascrizione trascrizione=new Trascrizione (rset.getString("testo"));
		                        listaopere.add(new Immagine(rset.getString("descrizione"), rset.getString("imm"),trascrizione));
		                    }
		                }
		            }
		        } catch (SQLException ex) {
		            throw new DataLayerException("GET Immagini", ex);
		        }
			return listaopere;
	}
	 @Override
		public List<Immagine> visualizzaListaImmagineModificare() throws DataLayerException {
			List<Immagine> listaopere= new ArrayList();
			 try (Connection connection = Database.getConnection()) {
		            try (PreparedStatement ps = connection.prepareStatement(GET_IMMAGINEMODIFICARE)) {
		                try (ResultSet rset = ps.executeQuery()) {
		                    while (rset.next()) {
		                    	Trascrizione trascrizione=new Trascrizione (rset.getString("testo"),rset.getInt("idtrascrizione"));
		                    	Opera opera=new Opera(rset.getString("titolo"));
		                        listaopere.add(new Immagine(rset.getInt("idimmagine"),rset.getString("descrizione"), rset.getString("imm"),trascrizione,opera));
		                    }
		                }
		            }
		        } catch (SQLException ex) {
		            throw new DataLayerException("GET Immagini", ex);
		        }
			return listaopere;
	}
	 @Override
		public Immagine inserisciNuovaImmagine (Immagine immagine,int id) throws DataLayerException{
			 System.out.println("imm"+immagine);

		        try (Connection connection = Database.getConnection()) {
		            try (PreparedStatement ps = connection.prepareStatement(SET_IMMAGINE, Statement.RETURN_GENERATED_KEYS)) {
		                //Prepare statement
		                ps.setString(1, immagine.getDescrizione());
		                ps.setString(2, immagine.getImm());
                        ps.setInt(3, id);

		                ps.executeUpdate();
		                
		                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
		                    if (generatedKeys.next()) {
		                        immagine.setId(generatedKeys.getInt(1));
		                    } else {
		                        throw new DataLayerException("RECUPERO IMMAGINE");
		                    }
		                }
		            }
		        } catch (SQLException ex) {
		            throw new DataLayerException("NUOVA IMMAGINE", ex);
		            
		        }
		        return immagine;
		    }
	 @Override
	    public int eliminaImmagine(Immagine immagine) throws DataLayerException {

	        PreparedStatement ps;
	        int result = 0;
	        try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(REMOVE_IMMAGINE);
	            ps.setString(1, immagine.getDescrizione());

	            result = ps.executeUpdate();

	            ps.close();
	            connection.close();
	        } catch (SQLException ex) {
	            throw new DataLayerException("Error remove image", ex);
	        }
	        return result;
	    }
	 @Override
	 public void assegnaImmagine(Object idu,Object idi) throws DataLayerException {
		 PreparedStatement ps;
         try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(ASSEGNA_IMMAGINE);
	            ps.setInt(1, (int) idi);
	           	ps.setInt(2, (int) idu);
	            if (ps.executeUpdate() == 1) {
	            System.out.println("The transcription has been assigned");    
	            ps.close();
	            connection.close();
	            }    
	        } catch (SQLException e) {
	            throw new DataLayerException("Error", e);
	        }	 
	 }
	 @Override
		public List<Opera> visualizzaListaOpereFiltrate(String titolo,String autore,String anno,String categoria) throws DataLayerException {
			List<Opera> opere= new ArrayList();
			 try (Connection connection = Database.getConnection()) {
		            try (PreparedStatement ps = connection.prepareStatement(FILTRA_OPERE)) {
		            	ps.setString(1, titolo);
		            	ps.setString(2, autore);
		            	ps.setString(3, anno);
		            	ps.setString(4, categoria);
		                try (ResultSet rset = ps.executeQuery()) {
		                    while (rset.next()) {
		                        opere.add(new Opera(rset.getInt("idopera"),rset.getString("titolo"), rset.getString("autore"),rset.getString("anno"),rset.getString("storia"),rset.getString("categoria")));
		                    }
		                }
		            }
		        } catch (SQLException ex) {
		            throw new DataLayerException("error", ex);
		        }
			return opere;
	}
}
	 
	 
	 
	 