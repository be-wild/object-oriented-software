package Model;

public class Assegnato {
	private int idassegnato;
	private String data;
	private int idimmagine;
	private int idutente;
	private int idtrascrizione;
	public Assegnato(String data) {
		this.setData(data);
	}
	public Assegnato() {
		// TODO Auto-generated constructor stub
	}
	public Assegnato(int id, String data) {
		this.setIdassegnato(id);
		this.setData(data);
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getIdassegnato() {
		return idassegnato;
	}
	public void setIdassegnato(int idassegnato) {
		this.idassegnato = idassegnato;
	}
	public int getIdimmagine() {
		return idimmagine;
	}
	public void setIdimmagine(int idimmagine) {
		this.idimmagine = idimmagine;
	}
	public int getIdutente() {
		return idutente;
	}
	public void setIdutente(int idutente) {
		this.idutente = idutente;
	}
	public int getIdtrascrizione() {
		return idtrascrizione;
	}
	public void setIdtrascrizione(int idtrascrizione) {
		this.idtrascrizione = idtrascrizione;
	}

	public int setIdtrascrizione1(int idtrascrizione) {
		return this.idtrascrizione = idtrascrizione;
	}
	 @Override
	    public String toString() {
	        return "Assegnato{" + "idassegnato=" + idassegnato + ",data=" + data + ", idimmagine=" + idimmagine + ",idutente=" + idutente + ",idtrascrizione=" + idtrascrizione + '}';
	    }
	public int getIdtrascrizione1(int int1) {
		// TODO Auto-generated method stub
		return int1;
	}
}
