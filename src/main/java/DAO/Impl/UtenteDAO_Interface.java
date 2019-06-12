package DAO.Impl;

import Util.DataLayerException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import Model.Immagine;
import Model.User;

public interface UtenteDAO_Interface {
	public List<User> visualizzaListaUtentiAssegnati() throws DataLayerException;
	public boolean promuoviLivello(String livello,int id) throws DataLayerException;
	public List<User> visualizzaListaUtentiTrascrittore() throws DataLayerException;
	public List<User> visualizzaListaUtenti()throws DataLayerException;
	public List<User> visualizzaListaUtentiAll()throws DataLayerException;
	public List<User> visualizzaListaUtentiT()throws DataLayerException;
	public List<User> visualizzaListaUtentiD()throws DataLayerException;
	public boolean utenteTrascrittore(int id) throws DataLayerException;
	public boolean utenteDownload(int id) throws DataLayerException;
	public User inserisciNuovoUtente(User utente)throws DataLayerException;
	public boolean eliminaUtente(int id) throws DataLayerException;
	public boolean promuoviUtente(Object id) throws DataLayerException;
	public boolean utenteEsistente(String username) throws DataLayerException;
	public List<User> visualizzaDati(String username,String password)throws DataLayerException;
	public InputStream downloadOpera(int id)throws DataLayerException, IOException;
	public List<User> visualizzaTipo(String tipo)throws DataLayerException;
	public User credenzialiUtente(String username,String password) throws DataLayerException;
	public String ottieniTipo(String username,String password) throws DataLayerException;
	public int ottieniID(String username,String password) throws DataLayerException;
	public List<User> visualizzaDatiPerID(Object id)throws DataLayerException;
	public List<User> visualizzaDatiPerIDAmministratore(Object id)throws DataLayerException;
	public int verificaDownload(int id) throws DataLayerException;
	public boolean downloadUtente(int id) throws DataLayerException;
}

