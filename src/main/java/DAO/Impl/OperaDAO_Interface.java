package DAO.Impl;

import java.util.List;

import Model.Assegnato;
import Model.Immagine;
import Model.Modifica;
import Model.Opera;
import Model.Trascrizione;
import Util.DataLayerException;

public interface OperaDAO_Interface {
	 public boolean modificaTra(int idt,String testo) throws DataLayerException;
	public boolean riassegnaT(int idu,int ida) throws DataLayerException;
	public List<Opera> visualizzaListaOperePerUtente(int id) throws DataLayerException;
	 public void disapprovaTrascrizioneModificata(int idt) throws DataLayerException;
	 public void approvaTrascrizioneModificata(String data,int idt,int idu) throws DataLayerException;
	public List<Trascrizione> visualizzaListaTrascrizioniModificateDaApprovare() throws DataLayerException;
	 public boolean modificaT(int idu,int idt) throws DataLayerException;
	public List<Immagine> visualizzaListaImmagineModificare() throws DataLayerException;
	public List<Trascrizione> visualizzaListaTrascrizioniDaApprovare() throws DataLayerException;
	public List<Immagine> visualizzaListaImmaginiNonPubblicate() throws DataLayerException;
	public boolean aggiornaAssegnato(Assegnato assegnato) throws DataLayerException;
	public List<Opera> visualizzaListaOpere()throws DataLayerException;
	public List<Immagine> visualizzaListaTrascrizioniFatte(int id)throws DataLayerException;
	public List<Trascrizione> visualizzaListaTrascrizioniModificare(int id)throws DataLayerException;
	public List<Immagine> visualizzaListaTrascrizioniDaFare(int id)throws DataLayerException;
	public String  visualizzaOperaID(int id)throws DataLayerException;
	public Opera inserisciNuovaOpera(Opera opera)throws DataLayerException;
	public boolean eliminaOpera(int id) throws DataLayerException;
	public List<Trascrizione> visualizzaListaTrascrizioni()throws DataLayerException;
	public Trascrizione visualizzaTrascrizionePerImmagine(Trascrizione trascrizione) throws DataLayerException;
	public int inserisciNuovaTrascrizione(Trascrizione trascrizione)  throws DataLayerException;
	public int eliminaTrascrizione(Trascrizione trascrizione) throws DataLayerException;
	public void approvaTrascrizione(int idi,int idt) throws DataLayerException;
	public void pubblicaTrascrizione(String testo)  throws DataLayerException;
	public void revisionaTrascrizione(String testo)  throws DataLayerException;
	public boolean modificaTrascrizione(Modifica modifica) throws DataLayerException;
	public List<Trascrizione> visualizzaListaTrascrizioniNonValidate()throws DataLayerException;
	public List<Immagine> visualizzaListaImmagine(int id)throws DataLayerException;
	public Immagine inserisciNuovaImmagine(Immagine immagine,int id)throws DataLayerException;
	public int eliminaImmagine(Immagine immagine) throws DataLayerException;
	public void assegnaImmagine(Object idu,Object idi) throws DataLayerException;
	public List<Opera> visualizzaListaOpereFiltrate(String titolo,String anno,String autore,String categoria) throws DataLayerException;
}
