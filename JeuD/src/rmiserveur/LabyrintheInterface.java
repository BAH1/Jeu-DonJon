package rmiserveur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;


public interface LabyrintheInterface extends Remote{
	
	

        public String connexion(String name) throws RemoteException;
	public String positionJoueur(String pseudo) throws RemoteException;
        public String informationSurPieceCote(String pseudo) throws RemoteException;
         public void deplacerJoueur(String pseudo,String porte) throws RemoteException;
       
	
}
