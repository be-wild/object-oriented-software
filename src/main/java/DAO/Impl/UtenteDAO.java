package DAO.Impl;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import Model.Assegnato;
import Model.Immagine;
import Model.Trascrizione;
import Model.User;
import Util.DataLayerException;
import Util.Database;

/**
 * @author Andrea
 *
 */

public class UtenteDAO implements UtenteDAO_Interface {
	private static final String GET_UTENTI="SELECT nome,cognome,eta,sesso,download,tipo,username,email,richiestat FROM user ";	
	private static final String GET_UTENTIALL="SELECT * FROM user WHERE tipo!='amministratore' ";
	private static final String GET_UTENTIT="SELECT * FROM user WHERE richiestat='si' AND tipo='utentebase'";
	private static final String GET_UTENTITRASCRITTORE="SELECT * FROM user WHERE tipo='trascrittore'";
	private static final String GET_UTENTID="SELECT * FROM user WHERE richiestad='si' AND download='0' ";
    private static final String SET_UTENTE="INSERT INTO user (nome,cognome,eta,sesso,download,tipo,username,email,password,richiestat,professione,titolo,anniesp,esplavorative,richiestad) VALUES(?,?,?,?,false,'utentebase',?,?,?,'no',?,?,?,?,'no') ";
	private static final String REMOVE_UTENTE="DELETE FROM user WHERE id=?";
	private static final String PROMUOVI="UPDATE user SET richiestat='si' WHERE id=?";
	private static final String PROMUOVILIVELLO="UPDATE user SET livello=? WHERE id=?";
	private static final String UTENTETRASCRITTORE="UPDATE user SET tipo='trascrittore',livello='1' WHERE id=?";
	private static final String UTENTEDOWNLOAD="UPDATE user SET download='1' WHERE id=?";
	private static final String SET_DOWNLOAD="UPDATE user SET richiestad='si' WHERE id=?";
	private static final String ESISTENTE="SELECT username FROM user WHERE username=?";
	private static final String GET_DATI="SELECT nome,cognome,eta,sesso,username,email,password,download,tipo,richiestat,professione,titolo,anniesp,esplavorative,richiestad,livello FROM user WHERE	username=? AND password=? ";
	private static final String DOWNLOAD_OPERA = "SELECT opera.file FROM opera WHERE opera.idopera=? ";
	private static final String GET_TIPO="SELECT tipo FROM user WHERE tipo=? ";
	private static final String GET_TIPO1="SELECT tipo FROM user WHERE username=? AND password=? ";
	private static final String GET_CREDENZIALI="SELECT * FROM user WHERE username=? AND password=? ";
	private static final String GET_ID="SELECT id FROM user WHERE username=? AND password=? ";
	private static final String GET_DATI_ID="SELECT nome,cognome,eta,sesso,username,email,password,download,tipo,richiestat,professione,titolo,anniesp,esplavorative,livello FROM user WHERE	id=? ";
    private static final String VERIFICA_DOWNLOAD ="SELECT download FROM user WHERE id=?";
	private static final String GET_DATI_ID_AMMINISTRATORE="SELECT nome,cognome,tipo FROM user WHERE id=? ";
	private static final String TUTTIASSEGNATO="SELECT immagine.imm,immagine.descrizione,assegnato.data,user.nome,user.cognome,assegnato.idassegnato,user.livello,trascrizione.testo FROM assegnato,user,immagine,trascrizione WHERE immagine.idimmagine=assegnato.idimmagine AND user.id=assegnato.idutente AND trascrizione.idtrascrizione=trascrizione.idtrascrizione AND immagine.idtrascrizione=trascrizione.idtrascrizione";

	@Override
	public List<User> visualizzaListaUtentiAssegnati() throws DataLayerException {
		List<User> utente= new ArrayList();
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(TUTTIASSEGNATO)) {
	                try (ResultSet rset = ps.executeQuery()) {
	                    while (rset.next()) {
	                    	Immagine immagine=new Immagine(rset.getString("imm"),rset.getString("descrizione"));
		                	Assegnato assegnato=new Assegnato(rset.getInt("idassegnato"),rset.getString("data"));
		                	Trascrizione trascrizione=new Trascrizione(rset.getString("testo"));
	                        utente.add(new User(rset.getString("nome"), rset.getString("cognome"),rset.getInt("livello"),immagine,assegnato,trascrizione));
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("GET UTENTI", ex);
	        }
		return utente;
}
	@Override
	public List<User> visualizzaListaUtenti() throws DataLayerException {
		List<User> utente= new ArrayList();
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(GET_UTENTI)) {
	                try (ResultSet rset = ps.executeQuery()) {
	                    while (rset.next()) {
	                        utente.add(new User(rset.getString("nome"), rset.getString("cognome"),rset.getInt("eta"),rset.getString("sesso"),rset.getBoolean("download"),rset.getString("tipo"),rset.getString("username"),rset.getString("email"),rset.getString("richiestat")));
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("GET UTENTI", ex);
	        }
		return utente;
}
	@Override
	public List<User> visualizzaListaUtentiAll() throws DataLayerException {
		List<User> utente= new ArrayList();
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(GET_UTENTIALL)) {
	                try (ResultSet rset = ps.executeQuery()) {
	                    while (rset.next()) {
	                        utente.add(new User(rset.getInt("id"),rset.getString("nome"), rset.getString("cognome"),rset.getInt("eta"),rset.getString("sesso"),rset.getBoolean("download"),rset.getString("tipo"),rset.getString("username"),rset.getString("email"),rset.getString("richiestat"),rset.getString("professione"),rset.getString("richiestad"),rset.getString("titolo"),rset.getInt("anniesp"),rset.getString("esplavorative")));
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("GET UTENTI", ex);
	        }
		return utente;
}
	@Override
	public List<User> visualizzaListaUtentiT() throws DataLayerException {
		List<User> utente= new ArrayList();
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(GET_UTENTIT)) {
	                try (ResultSet rset = ps.executeQuery()) {
	                    while (rset.next()) {
	                        utente.add(new User(rset.getInt("id"),rset.getString("nome"), rset.getString("cognome"),rset.getInt("eta"),rset.getString("sesso"),rset.getBoolean("download"),rset.getString("tipo"),rset.getString("username"),rset.getString("email"),rset.getString("richiestat"),rset.getString("professione"),rset.getString("richiestad"),rset.getString("titolo"),rset.getInt("anniesp"),rset.getString("esplavorative")));
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("GET UTENTI", ex);
	        }
		return utente;
}
	@Override
	public List<User> visualizzaListaUtentiTrascrittore() throws DataLayerException {
		List<User> utente= new ArrayList();
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(GET_UTENTITRASCRITTORE)) {
	                try (ResultSet rset = ps.executeQuery()) {
	                    while (rset.next()) {
	                        utente.add(new User(rset.getInt("id"),rset.getString("nome"), rset.getString("cognome"),rset.getInt("eta"),rset.getString("sesso"),rset.getBoolean("download"),rset.getString("tipo"),rset.getString("username"),rset.getString("email"),rset.getString("richiestat"),rset.getString("professione"),rset.getString("richiestad"),rset.getString("titolo"),rset.getInt("anniesp"),rset.getString("esplavorative"),rset.getInt("livello")));
	                        System.out.println(utente);
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("GET UTENTI", ex);
	        }
		return utente;
}
	@Override
	public List<User> visualizzaListaUtentiD() throws DataLayerException {
		List<User> utente= new ArrayList();
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(GET_UTENTID)) {
	                try (ResultSet rset = ps.executeQuery()) {
	                    while (rset.next()) {
	                        utente.add(new User(rset.getInt("id"),rset.getString("nome"), rset.getString("cognome"),rset.getInt("eta"),rset.getString("sesso"),rset.getBoolean("download"),rset.getString("tipo"),rset.getString("username"),rset.getString("email"),rset.getString("richiestat"),rset.getString("professione"),rset.getString("richiestad"),rset.getString("titolo"),rset.getInt("anniesp"),rset.getString("esplavorative")));
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("GET UTENTI", ex);
	        }
		return utente;
}
	@Override
	public User inserisciNuovoUtente (User utente) throws DataLayerException{
		 

	        try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(SET_UTENTE, Statement.RETURN_GENERATED_KEYS)) {
	                //Prepare statement
	                ps.setString(1, utente.getNome());
	                ps.setString(2, utente.getCognome());
	                ps.setInt(3,  utente.getEta());
	                ps.setString(4, utente.getSesso());
	                ps.setString(5, utente.getUsername());
	                ps.setString(6, utente.getEmail());
	                ps.setString(7, utente.getPassword());
	                ps.setString(8, utente.getProfessione());
	                ps.setString(9, utente.getTitolo());
	                ps.setInt(10, utente.getAnniesp());
	                ps.setString(11, utente.getEsplavorative());
	                ps.executeUpdate();
	                
	                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	                    if (generatedKeys.next()) {
	                        utente.setId(generatedKeys.getInt(1));
	                    } else {
	                        throw new DataLayerException("RECUPERO UTENTE");
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("NUOVO UTENTE", ex);
	        }

	        return utente;
	    }
	 @Override
	    public boolean eliminaUtente(int id) throws DataLayerException {
            PreparedStatement ps;
	        boolean ok = false;
	        try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(REMOVE_UTENTE);
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
	    public boolean utenteTrascrittore(int id) throws DataLayerException {
         PreparedStatement ps;
	        boolean ok = false;
	        try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(UTENTETRASCRITTORE);
	            ps.setInt(1, id);
	            if (ps.executeUpdate() == 1) {
	 	           
		            ok=true;
		            ps.close();
		            connection.close();
		            }    
	        } catch (SQLException ex) {
	            throw new DataLayerException("Error promotion", ex);
	        }
	        return ok;
	    }
	 @Override
	    public boolean utenteDownload(int id) throws DataLayerException {
      PreparedStatement ps;
	        boolean ok = false;
	        try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(UTENTEDOWNLOAD);
	            ps.setInt(1, id);
	            if (ps.executeUpdate() == 1) {
	 	           
		            ok=true;
		            ps.close();
		            connection.close();
		            }    
	        } catch (SQLException ex) {
	            throw new DataLayerException("Error promotion", ex);
	        }
	        return ok;
	    }
	 @Override
	    public boolean promuoviUtente(Object id) throws DataLayerException {
		 PreparedStatement ps;
		 boolean ok=false;
         try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(PROMUOVI);
	            ps.setInt(1, (int) id);
	            if (ps.executeUpdate() == 1) {
	           
	            ok=true;
	            ps.close();
	            connection.close();
	            }    
	        } catch (SQLException e) {
	            throw new DataLayerException("ERROR PROMOTION", e);
	        }
         return ok;
	    }
	 @Override
	    public boolean promuoviLivello(String livello,int id) throws DataLayerException {
		 PreparedStatement ps;
		 boolean ok=false;
      try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(PROMUOVILIVELLO);
	            ps.setString(1,livello);
	            ps.setInt(2,id);
	            if (ps.executeUpdate() == 1) {
	           
	            ok=true;
	            ps.close();
	            connection.close();
	            }    
	        } catch (SQLException e) {
	            throw new DataLayerException("ERROR PROMOTION", e);
	        }
      return ok;
	    }
	 @Override
	 public boolean utenteEsistente(String username) throws DataLayerException {
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(ESISTENTE)) {
	                //Prepare statement
	                ps.setString(1, username);

	                try (ResultSet rset = ps.executeQuery()){
	                    if (rset.next()) {
	                        return true;
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("USERNAME UTENTE", ex);
	        }
	        
	        return false;
	    }
	 @Override
		public List<User> visualizzaDati(String username1,String password1) throws DataLayerException {
			List<User> utente= new ArrayList<User>();
			 try (Connection connection = Database.getConnection()) {
		            try (PreparedStatement ps = connection.prepareStatement(GET_DATI)) {
		            	ps.setString(1, username1);
	                	ps.setString(2, password1);
		                try (ResultSet rset = ps.executeQuery()) {
		                    while (rset.next()) {
		                    
		                    	String nome=rset.getString("nome");
		                    	String cognome=rset.getString("cognome");
		                    	int eta=rset.getInt("eta");
		                    	String sesso=rset.getString("sesso");
		                    	String tipo=rset.getString("tipo");
		                    	Boolean download=rset.getBoolean("download");
		                    	String username=rset.getString("username");
		                    	String email=rset.getString("email");
		                    	String password=rset.getString("password");
		                    	String richiestat=rset.getString("richiestat");
		                    	String professione=rset.getString("professione");
		                    	String titolo=rset.getString("titolo");
		                    	int anniesp=rset.getInt("anniesp");
		                    	int livello=rset.getInt("livello");
		                    	String esplavorative=rset.getString("esplavorative");
		                        utente.add(new User(nome,cognome,eta,sesso,tipo,download,username,email,password,richiestat,professione,titolo,anniesp,esplavorative,livello));
		                    }
		                }
		            }
		        } catch (SQLException ex) {
		            throw new DataLayerException("error", ex);
		        }
			return utente;
	}
	 
	 @Override
	    public InputStream downloadOpera(int id) throws DataLayerException {
	    InputStream inputStream = null;
            try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(DOWNLOAD_OPERA)) {
	                //Prepare query
	                ps.setInt(1, id);
	                try (ResultSet rset = ps.executeQuery()) {
	                    if (rset.next()) {
	                        Blob blob = rset.getBlob("file");
	                        inputStream = blob.getBinaryStream();
	                    }
	                }
	            }

	        } catch (SQLException e) {
	            throw new DataLayerException("DOWNLOAD OPERA", e);
	        }

	        return inputStream;
	    }
	 @Override
		public List<User> visualizzaTipo(String tipo) throws DataLayerException {
			List<User> utente= new ArrayList();
			 try (Connection connection = Database.getConnection()) {
		            try (PreparedStatement ps = connection.prepareStatement(GET_TIPO)) {
		                try (ResultSet rset = ps.executeQuery()) {
		                    while (rset.next()) {
		                        utente.add(new User(rset.getString("tipo")));
		                    }
		                }
		            }
		        } catch (SQLException ex) {
		            throw new DataLayerException("GET TIPO", ex);
		        }
			return utente;
	}
	 @Override
	 public User credenzialiUtente(String username,String password) throws DataLayerException {
		 User user=null;
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(GET_CREDENZIALI)) {
	                //Prepare statement
	                ps.setString(1, username);
	                ps.setString(2, password);
	                try (ResultSet rset = ps.executeQuery()) {

	                    if (rset.next()) {
	                        user = new User(
	                                rset.getString("tipo"),
	                                rset.getString("username"),
	                                rset.getInt("id")
	                        );
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("CREDENZIALI UTENTE", ex);
	        }
	        return user;
}
	 @Override
	 public String ottieniTipo(String username,String password) throws DataLayerException {
		 String tipo=null;
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(GET_TIPO1)) {
	                //Prepare statement
	                ps.setString(1, username);
	                ps.setString(2, password);
	                try (ResultSet rset = ps.executeQuery()) {

	                    if (rset.next()) {
	                        tipo =rset.getString("tipo");
	                        
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("CREDENZIALI UTENTE", ex);
	        }
		return tipo;
		
}	
	 @Override
	 public int ottieniID(String username,String password) throws DataLayerException {
		 int id=0;
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(GET_ID)) {
	                //Prepare statement
	                ps.setString(1, username);
	                ps.setString(2, password);
	                try (ResultSet rset = ps.executeQuery()) {

	                    if (rset.next()) {
	                        id =rset.getInt("id");
	                        
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("ID UTENTE", ex);
	        }
		return id;	 
}
	 @Override
		public List<User> visualizzaDatiPerID(Object id) throws DataLayerException {
			List<User> utente= new ArrayList<User>();
			 try (Connection connection = Database.getConnection()) {
		            try (PreparedStatement ps = connection.prepareStatement(GET_DATI_ID)) {
		            	ps.setInt(1, (int) id);
		                try (ResultSet rset = ps.executeQuery()) {
		                    while (rset.next()) {
		                    
		                    	String nome=rset.getString("nome");
		                    	String cognome=rset.getString("cognome");
		                    	int eta=rset.getInt("eta");
		                    	String sesso=rset.getString("sesso");
		                    	String tipo=rset.getString("tipo");
		                    	Boolean download=rset.getBoolean("download");
		                    	String username=rset.getString("username");
		                    	String email=rset.getString("email");
		                    	String password=rset.getString("password");
		                    	String richiestat=rset.getString("richiestat");
		                    	String professione=rset.getString("professione");
		                    	String titolo=rset.getString("titolo");
		                    	int anniesp=rset.getInt("anniesp");
		                    	int livello=rset.getInt("livello");
		                    	String esplavorative=rset.getString("esplavorative");
		                        utente.add(new User(nome,cognome,eta,sesso,tipo,download,username,email,password,richiestat,professione,titolo,anniesp,esplavorative,livello));
		                    }
		                }
		            }
		        } catch (SQLException ex) {
		            throw new DataLayerException("error", ex);
		        }
			return utente;
	}
	 @Override
	 public int verificaDownload(int id) throws DataLayerException {
		 int ok=0;
		 try (Connection connection = Database.getConnection()) {
	            try (PreparedStatement ps = connection.prepareStatement(VERIFICA_DOWNLOAD)) {
	                //Prepare statement
	                ps.setInt(1, id);
	                try (ResultSet rset = ps.executeQuery()) {

	                    if (rset.next()) {
	                        ok =rset.getInt("download");
	                        
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            throw new DataLayerException("error", ex);
	        }
		return ok;	 
}
	 @Override
	    public boolean downloadUtente(int id) throws DataLayerException {
		 PreparedStatement ps;
		 boolean ok=false;
      try (Connection connection = Database.getConnection()) {
	            ps = connection.prepareStatement(SET_DOWNLOAD);
	            ps.setInt(1, (int) id);
	            if (ps.executeUpdate() == 1) {
	           
	            ok=true;
	            ps.close();
	            connection.close();
	            }    
	        } catch (SQLException e) {
	            throw new DataLayerException("ERROR PROMOTION", e);
	        }
      return ok;
	    }
	 @Override
		public List<User> visualizzaDatiPerIDAmministratore(Object id) throws DataLayerException {
		 List<User> utente= new ArrayList();
			 try (Connection connection = Database.getConnection()) {
		            try (PreparedStatement ps = connection.prepareStatement(GET_DATI_ID_AMMINISTRATORE)) {
		            	ps.setInt(1, (int) id);
		                try (ResultSet rset = ps.executeQuery()) {
		                    while (rset.next()) {
		                    
		                    	String nome=rset.getString("nome");
		                    	String cognome=rset.getString("cognome");
		                    	String tipo=rset.getString("tipo");
		                        utente.add(new User(nome,cognome,tipo));
		                    }
		                }
		            }
		        } catch (SQLException ex) {
		            throw new DataLayerException("error", ex);
		        }
			return utente;
	}
}
