package Model;

/**
 * @author Andrea
 *
 */

public class Immagine {
	private int idimmagine;
	private String descrizione;
	private String imm;
	private int opera;
	private Trascrizione trascrizione;
	private String pubblicata;
	private String data;
	private String testo;
	private int idtrascrizione;
	private int idassegnato;
    private Opera opera1;
public Immagine(int idimmagine,int idassegnato, String data,String descrizione,String imm) {
	this.setId(idimmagine);
	this.setIdassegnato(idassegnato);
	this.setData(data);
	this.setImm(imm);
	this.setDescrizione(descrizione);
}
public Immagine(String descrizione,String imm,Trascrizione trascrizione) {
	this.setDescrizione(descrizione);
	this.setImm(imm);
	this.setTrascrizione(trascrizione);
}

	public Immagine(int idimmagine, String descrizione, String imm, String pubblicata, int opera, Trascrizione  trascrizione) {
		this.setDescrizione(descrizione);
		this.setImm(imm);
		this.setTrascrizione(trascrizione);
		this.setId(idimmagine);
		this.setPubblicata(pubblicata);
		this.setOpera(opera);
}
	public Immagine(String data, String descrizione, String imm, String testo) {
		this.setDescrizione(descrizione);
		this.setImm(imm);
		this.setData(data);
		this.setTesto(testo);
	}
	public Immagine(String data, String descrizione, String imm) {
		this.setDescrizione(descrizione);
		this.setImm(imm);
		this.setData(data);	}
	public Immagine() {
		// TODO Auto-generated constructor stub
	}
	public Immagine(String imm, String descrizione) {
		this.setDescrizione(descrizione);
		this.setImm(imm);
	}
	public Immagine(int idimmagine, String descrizione, String imm, Opera opera1) {
		this.setDescrizione(descrizione);
		this.setImm(imm);
		this.setId(idimmagine);
		this.setOpera1(opera1);
	}
	public Immagine(int idimmagine, String descrizione, String imm) {
		this.setDescrizione(descrizione);
		this.setImm(imm);
		this.setId(idimmagine);
	}
	public Immagine(int id, String descrizione, String imm, Trascrizione trascrizione, Opera opera1) {
		this.setDescrizione(descrizione);
		this.setImm(imm);
		this.setId(id);
		this.setOpera1(opera1);
		this.setTrascrizione(trascrizione);
	}
	public int getId() {
		return idimmagine;
	}
	public void setId(int idimmagine) {
		this.idimmagine = idimmagine;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getImm() {
		return imm;
	}
	public void setImm(String imm2) {
		this.imm =imm2;
	}
	public Trascrizione getTrascrizione() {
		return trascrizione;
	}
	public void setTrascrizione(Trascrizione trascrizione) {
		this.trascrizione = trascrizione;
	}

    @Override
    public String toString() {
        return "Immagine{" + "idimmagine=" + idimmagine + ",idassegnato=" + idassegnato + ",imm=" + imm + ", descrizione=" + descrizione + ",opera=" + opera + ",opera1=" + opera1 + ",data=" + data + ",testo=" + testo + ",trascrizione=" + trascrizione + '}';
    }
	public String getPubblicata() {
		return pubblicata;
	}
	public void setPubblicata(String pubblicata) {
		this.pubblicata = pubblicata;
	}
	public int getOpera() {
		return opera;
	}
	public void setOpera(int opera) {
		this.opera = opera;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getIdtrascrizione() {
		return idtrascrizione;
	}
	public void setIdtrascrizione(int idtrascrizione) {
		this.idtrascrizione = idtrascrizione;
	}
	public int getIdassegnato() {
		return idassegnato;
	}
	public void setIdassegnato(int idassegnato) {
		this.idassegnato = idassegnato;
	}
	public Opera getOpera1() {
		return opera1;
	}
	public void setOpera1(Opera opera1) {
		this.opera1 = opera1;
	}
	

}
