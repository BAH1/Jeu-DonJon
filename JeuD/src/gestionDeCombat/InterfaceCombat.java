/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDeCombat;

import gestionPersonnage.ListePersonnage;
import gestionPersonnage.Personnage;
import gestionduclient.InterfaceClient;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author M1stri25
 */
public interface InterfaceCombat extends Remote {
    
    //public void combattreMonstre(InterfaceClient client) throws RemoteException;
     public void recupererListeClient(ArrayList<Personnage> liste,int numeroPiece) throws RemoteException;
      public int nombreDeJoueur() throws RemoteException;
      public void notification() throws RemoteException;
      public String listePersonnage()throws RemoteException;
  //    public void combattreJoueur(String pseudo,InterfaceClient client) throws RemoteException;
      public void combattreLemonstre(InterfaceClient client) throws RemoteException;
       public boolean etatCombat ()throws RemoteException;
       public void fuirCombat ()throws RemoteException;
}
