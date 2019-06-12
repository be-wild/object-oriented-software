package Model;



/**
 * @author Andrea
 *
 */

public class User {
	private int id;
	private String nome;
	private String cognome;
	private int eta;
	private String sesso;
	private String username;
	private String email;
	private String password;
	private boolean download;
	private String tipo;
	private String richiestat;
	private String professione;
	private String titolo;
	private int anniesp;
	private String esplavorative;
	private String richiestad;
	private int livello;
	private Assegnato assegnato;
	private Trascrizione trascrizione;
	private Immagine immagine;
 public User() {
	 super();
 }
 public User(String tipo) {
	 this.setTipo(tipo);
 }
 public User(String username, String password) {
	 this.setNome(username);
	 this.setCognome(password);
 }
 public User(String nome, String cognome, int eta, String sesso,boolean download,String tipo,String username,String email,String richiestat) {
	 this.setNome(nome);
	 this.setCognome(cognome);
	 this.setEta(eta);
	 this.setSesso(sesso);
	 this.setTipo(tipo);
	 this.setDownload(download);
	 this.setUsername(username);
	 this.setEmail(email);
	 this.setRichiestat(richiestat);
 }
 public User(String tipo, String username,int id) {
	 this.setId(id);
	 this.setUsername(username);
	 this.setTipo(tipo);
 }
 public User(String nome, String cognome, int eta, String sesso,boolean download, String tipo,String username) {
	 this.setNome(nome);
	 this.setCognome(cognome);
	 this.setEta(eta);
	 this.setSesso(sesso);
	 this.setTipo(tipo);
	 this.setDownload(download);
	 this.setUsername(username);
	 
 }
 public User(String nome, String cognome, int eta, String sesso,String tipo, String username, String password) {
	 this.setNome(nome);
	 this.setCognome(cognome);
	 this.setEta(eta);
	 this.setSesso(sesso);
	 this.setTipo(tipo);
	 this.setUsername(username);
	 this.setPassword(password);
 }
 public User(String nome, String cognome, int eta, String sesso,String tipo,Boolean download, String username,String email,String password, String richiestat,String professione,String titolo,int anniesp,String esplavorative, int livello) {
	 this.setNome(nome);
	 this.setCognome(cognome);
	 this.setEta(eta);
	 this.setSesso(sesso);
	 this.setTipo(tipo);
	 this.setUsername(username);
	 this.setEmail(email);
	 this.setPassword(password);
	 this.setDownload(download);
	 this.setRichiestat(richiestat);
	 this.setProfessione(professione);
	 this.setTitolo(titolo);
	 this.setAnniesp(anniesp);
	 this.setEsplavorative(esplavorative);
	 this.setLivello(livello);
 }

public User(int id, String nome, String cognome, int livello){
	 this.setId(id);
	this.setNome(nome);
	 this.setCognome(cognome);
	 this.setLivello(livello);
}
public User(int id, String nome, String cognome, int eta, String sesso, boolean download, String tipo,
		String username, String email, String richiestat, String professione, String richiestad, String titolo, int anniesp,
		String esplavorative) {
	 this.setId(id);
	 this.setNome(nome);
	 this.setCognome(cognome);
	 this.setEta(eta);
	 this.setSesso(sesso);
	 this.setTipo(tipo);
	 this.setUsername(username);
	 this.setEmail(email);
	 this.setDownload(download);
	 this.setRichiestat(richiestat);
	 this.setProfessione(professione);
	 this.setTitolo(titolo);
	 this.setAnniesp(anniesp);
	 this.setEsplavorative(esplavorative);
	 this.setRichiestad(richiestad);

}
public User(int id, String nome, String cognome, int eta, String sesso, boolean download, String tipo,
		String username, String email, String richiestat, String professione, String richiestad, String titolo, int anniesp,
		String esplavorative,int livello) {
	this.setId(id);
	this.setNome(nome);
	 this.setCognome(cognome);
	 this.setEta(eta);
	 this.setSesso(sesso);
	 this.setTipo(tipo);
	 this.setUsername(username);
	 this.setEmail(email);
	 this.setDownload(download);
	 this.setRichiestat(richiestat);
	 this.setProfessione(professione);
	 this.setTitolo(titolo);
	 this.setAnniesp(anniesp);
	 this.setEsplavorative(esplavorative);
	 this.setRichiestad(richiestad);
	 this.setLivello(livello);
}
public User(String nome2, String cognome2, String tipo2) {
	this.setNome(nome2);
	 this.setCognome(cognome2);
	 this.setTipo(tipo2);}
public User(String nome, String cognome, int livello,String tipo) {
	this.setNome(nome);
	 this.setCognome(cognome);
	 this.setLivello(livello);
	 this.setTipo(tipo);
}
public User(String nome, String cognome, int livello, Immagine immagine, Assegnato assegnato,Trascrizione trascrizione) {
	this.setNome(nome);
	this.setCognome(cognome);
	this.setLivello(livello);
	this.setImmagine(immagine);
	this.setTrascrizione(trascrizione);
	this.setAssegnato(assegnato);
}
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getCognome() {
	return cognome;
}

public void setCognome(String cognome) {
	this.cognome = cognome;
}

public int getEta() {
	return eta;
}

public void setEta(int eta) {
	this.eta = eta;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getSesso() {
	return sesso;
}

public void setSesso(String sesso) {
	this.sesso = sesso;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getTipo() {
	return tipo;
}

public void setTipo(String tipo) {
	this.tipo = tipo;
}

public boolean isDownload() {
	return download;
}

public void setDownload(boolean download) {
	this.download = download;
}
public String getRichiestat() {
	return richiestat;
}
public void setRichiestat(String richiestat) {
	this.richiestat = richiestat;
}
public String getProfessione() {
	return professione;
}
public void setProfessione(String professione) {
	this.professione = professione;
}
public String getTitolo() {
	return titolo;
}
public void setTitolo(String titolo) {
	this.titolo = titolo;
}
public int getAnniesp() {
	return anniesp;
}
public void setAnniesp(int anniesp) {
	this.anniesp = anniesp;
}
public String getEsplavorative() {
	return esplavorative;
}
public void setEsplavorative(String esplavorative) {
	this.esplavorative = esplavorative;
}
@Override
public String toString() {
    return "User{" + "id=" + id + "," + "nome=" + nome + ", cognome=" + cognome + ", eta=" + eta + ", sesso=" + sesso + ",username=" + username + ",email=" + email + ",password=" + password + ",download=" + download + ",tipo= " + tipo + ",richiestat=" + richiestat+",professione= " + professione +",titolo= " + titolo +",anniesp= " + anniesp +",livello= " + livello +",richiestad= " + richiestad +",immagine= " + immagine +",trascrizione= " + trascrizione +",assegnato= " + assegnato +", esplavorative=" + esplavorative +  '}';
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getRichiestad() {
	return richiestad;
}
public void setRichiestad(String richiestad) {
	this.richiestad = richiestad;
}
public int getLivello() {
	return livello;
}
public void setLivello(int livello) {
	this.livello = livello;
}
public Assegnato getAssegnato() {
	return assegnato;
}
public void setAssegnato(Assegnato assegnato) {
	this.assegnato = assegnato;
}
public Trascrizione getTrascrizione() {
	return trascrizione;
}
public void setTrascrizione(Trascrizione trascrizione) {
	this.trascrizione = trascrizione;
}
public Immagine getImmagine() {
	return immagine;
}
public void setImmagine(Immagine immagine) {
	this.immagine = immagine;
}
}
