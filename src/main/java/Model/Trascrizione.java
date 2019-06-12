package Model;

import java.util.List;

/**
 * @author Andrea
 *
 */

public class Trascrizione {
	private int idtrascrizione;
	private String testo;
	private String validata;
	private String modificata;
	private String pubblicata;
    private Modifica modifica;
	private Immagine immagine;
	private Opera opera;
	private User utente;
	private Assegnato assegnato;
	
	public Trascrizione(int idtrascrizione) {
		super();
		this.setId(idtrascrizione);
			}
	
	public Trascrizione(int idtrascrizione, String testo,String validata, String modificata, String pubblicata) {
	super();
	this.setId(idtrascrizione);
	this.setTesto(testo);
	this.modificata=modificata;
	this.validata=validata;
	this.pubblicata=pubblicata;
		}
	public Trascrizione(int idtrascrizione, String testo) {
		super();
		this.setId(idtrascrizione);
		this.setTesto(testo);
			}
	public Trascrizione( String testo) {
		super();
		this.setTesto(testo);
			}

	public Trascrizione() {
		// TODO Auto-generated constructor stub
	}

	public Trascrizione(Immagine immagine, String testo, int id, Modifica modifica) {
		this.setImmagine(immagine);
		this.setTesto(testo);
		this.setModifica(modifica);
		this.setId(id);
	}

	public Trascrizione(Assegnato assegnato, String testo, int id, Immagine immagine, Opera opera, User utente) {
		this.setAssegnato(assegnato);
		this.setTesto(testo);
		this.setImmagine(immagine);
		this.setOpera(opera);
		this.setUtente(utente);
		this.setId(id);
	}

	public Trascrizione(String testo, int id) {
		this.setId(id);
		this.setTesto(testo);
	}

	public Trascrizione(Modifica modifica, String testo, int id, Immagine immagine, Opera opera, User utente) {
		this.setModifica(modifica);
		this.setTesto(testo);
		this.setImmagine(immagine);
		this.setOpera(opera);
		this.setUtente(utente);
		this.setId(id);
	}

	public Trascrizione(int id, String testo, Immagine immagine) {
		this.setId(id);
		this.setImmagine(immagine);
		this.setTesto(testo);
	}

	public int getId() {
		return idtrascrizione;
	}

	public int setId(int id) {
		return this.idtrascrizione = id;
	}
	public Modifica getModifica() {
		return modifica;
	}

	public void setModifica(Modifica modifica) {
		  this.modifica = modifica;
	}
	public Immagine getImmagine() {
		return immagine;
	}

	public void setImmagine(Immagine immagine) {
		  this.immagine = immagine;
	}
	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}
	
	/**
	 * @return true if the review is approved
	 * 		false otherwise.
	 * */
	public String itsValidated() {
		return validata;
	}
	
	/**
	 * @return true if the review is modified
	 * 		false otherwise.
	 * */
	public String itsModified() {
		return modificata;
	}
	
	/**
	 * @return true if the review is modified
	 * 		false otherwise.
	 * */
	public String itsPubblicated() {
		return pubblicata;
	}

	 @Override
	    public String toString() {
	        return "Trascrizione{" + "idtrascrizione=" + idtrascrizione + ",testo=" + testo + ",validata=" + validata + ", pubblicata=" + pubblicata + ",modificata=" + modificata + ",modifica=" + modifica + ",assegnato=" + assegnato + ",opera=" + opera + ",utente=" + utente + ",immagine=" + immagine + '}';
	    }

	public Opera getOpera() {
		return opera;
	}

	public void setOpera(Opera opera) {
		this.opera = opera;
	}

	public User getUtente() {
		return utente;
	}

	public void setUtente(User utente) {
		this.utente = utente;
	}

	public Assegnato getAssegnato() {
		return assegnato;
	}

	public void setAssegnato(Assegnato assegnato) {
		this.assegnato = assegnato;
	}

	
}
