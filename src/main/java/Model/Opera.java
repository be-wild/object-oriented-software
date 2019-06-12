package Model;

import java.io.InputStream;
import java.sql.Blob;

/**
 * @author Andrea
 *
 */





public class Opera {
	private int idopera;
	private String titolo;
	private String anno;
	private String autore;
	private String storia;
	private String categoria; 
	private InputStream file; 
	private Trascrizione trascrizione;
	private Immagine immagine;
	
	
	public Opera(int idopera,String titolo, String autore,String anno, String storia,String categoria,InputStream file) {
		this.setIdopera(idopera);
		this.setTitolo(titolo);
		this.setAnno(anno);
		this.setAutore(autore);
		this.setStoria(storia);
		this.setCategoria(categoria);
		this.setFile(file);
	}
	public Opera(String titolo, String anno,String autore, String storia,String categoria,InputStream file) {
		this.setTitolo(titolo);
		this.setAnno(anno);
		this.setAutore(autore);
		this.setStoria(storia);
		this.setCategoria(categoria);
		this.setFile(file);
	}
	public Opera(int idopera,String titolo, String autore,String anno, String storia,String categoria) {
		this.setIdopera(idopera);
		this.setTitolo(titolo);
		this.setAnno(anno);
		this.setAutore(autore);
		this.setStoria(storia);
		this.setCategoria(categoria);
	}
	
	public Opera() {
		// TODO Auto-generated constructor stub
	}
	public Opera(int idopera, String titolo) {
		this.setIdopera(idopera);
		this.setTitolo(titolo);
	}
	public Opera(String titolo) {
		this.setTitolo(titolo);
	}
	public Opera(String testo, Trascrizione trascrizione, Immagine immagine) {
		this.setTitolo(testo);
		this.setTrascrizione(trascrizione);
		this.setImmagine(immagine);
	}
	public int getIdopera() {
		return idopera;
	}

	public void setIdopera(int id) {
		this.idopera = id;
	}
	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getStoria() {
		return storia;
	}

	public void setStoria(String storia) {
		this.storia = storia;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	@Override
	public String toString() {
	    return "Opera{" + "idopera=" + idopera + "," + "autore=" + autore + ", storia=" + storia + ", anno=" + anno + ", categoria=" + categoria + ",trascrizione=" + trascrizione + ",immagine=" + immagine + ",  titolo=" + titolo +  '}';
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public InputStream getFile() {
		return file;
	}

	public void setFile(InputStream file) {
		this.file = file;
	}
	public Immagine getImmagine() {
		return immagine;
	}
	public void setImmagine(Immagine immagine) {
		this.immagine = immagine;
	}
	public Trascrizione getTrascrizione() {
		return trascrizione;
	}
	public void setTrascrizione(Trascrizione trascrizione) {
		this.trascrizione = trascrizione;
	}
}
