/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduLabyrinthe;

import gestionPersonnage.ListeClientParPiece;
import gestionPersonnage.ListePersonnage;
import gestionPersonnage.Personnage;
import gestionduclient.InterfaceClient;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author elhadj
 * Interface à partir de laquelle les utilisateurs pourront acceder aux méthodes du serveur
 */
public interface InterfaceduLabyrinthe extends Remote{
    
    public void connexion(InterfaceClient client) throws RemoteException;
     //public void ajouterClient(InterfaceClient client)throws RemoteException;
    public String InformationSurlaDestination(InterfaceClient client) throws RemoteException;
       public int deplacerJoueur(String choix,InterfaceClient client) throws RemoteException;
       public int  recupererNumeroPiece(InterfaceClient client)throws RemoteException;
       public ArrayList<Personnage> recupererListe()throws RemoteException;
      public ArrayList<Personnage> recupererListeParNumero(InterfaceClient client) throws RemoteException;
        public String afficherPersonnedanspiece(InterfaceClient client) throws RemoteException;
     public int personnageViVant(InterfaceClient client) throws RemoteException;
      public int VerificationEtat(InterfaceClient client) throws RemoteException;
      //  public void Deconnexion(InterfaceClient client)throws RemoteException;*/
}

