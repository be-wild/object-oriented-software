package Model;

public class Modifica {
	private int idmodifica;
	private String data;
	private String nuovo_testo;
	private int idutente;
	private int idtrascrizione;
	public Modifica(int idmodifica, String data, String nuovo_testo, int idtrascrizione) {
    this.setData(data);
    this.setNuovo_testo(nuovo_testo);
    this.setIdtrascrizione(idtrascrizione);
    this.setIdmodifica(idmodifica);
	}
	public Modifica() {
		// TODO Auto-generated constructor stub
	}
	public Modifica(String data, String nuovo_testo) {
	this.setData(data);
	this.setNuovo_testo(nuovo_testo);
	}
	public int getIdmodifica() {
		return idmodifica;
	}
	public void setIdmodifica(int idmodifica) {
		this.idmodifica = idmodifica;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getIdutente() {
		return idutente;
	}
	public void setIdutente(int idutente) {
		this.idutente = idutente;
	}
	public String getNuovo_testo() {
		return nuovo_testo;
	}
	public void setNuovo_testo(String nuovo_testo) {
		this.nuovo_testo = nuovo_testo;
	}
	public int getIdtrascrizione() {
		return idtrascrizione;
	}
	public void setIdtrascrizione(int idtrascrizione) {
		this.idtrascrizione = idtrascrizione;
	}
	 @Override
	    public String toString() {
	        return "Modifica{" + "idmodifica=" + idmodifica + ",data=" + data + ",nuovo_testo=" + nuovo_testo + ", idutente=" + idutente + ",idtrascrizione=" + idtrascrizione + '}';
	    }
}
