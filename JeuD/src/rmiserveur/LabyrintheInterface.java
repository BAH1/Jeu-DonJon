package rmiserveur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;


public interface LabyrintheInterface extends Remote{
	
	
	//public void connexionBD() throws RemoteException;
        public String connexion(String name) throws RemoteException;
	public String positionJoueur(String pseudo) throws RemoteException;
        public void envoyerNotification(String pseudo,LabyrintheNotification l)throws RemoteException;
       
	
}
