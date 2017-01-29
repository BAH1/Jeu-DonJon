package rmiserveur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;


public interface LabyrintheInterface extends Remote{
	
	public Double afficher(Double mt)throws RemoteException;
	public Date getDate() throws RemoteException;
	public void afficher()throws RemoteException;
	public void connexionBD() throws RemoteException;
	public String connexion(String name) throws RemoteException;
	public String positionJoueur(String pseudo) throws RemoteException;
	
}
