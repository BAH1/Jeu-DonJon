/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduLabyrinthe;

import gestionduclient.InterfaceClient;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author elhadj
 * Interface à partir de laquelle les utilisateurs pourront acceder aux méthodes du serveur
 */
public interface InterfaceduLabyrinthe extends Remote{
    
     public void connexion(InterfaceClient client) throws RemoteException;
     //public void ajouterClient(InterfaceClient client)throws RemoteException;
      public String InformationSurlaDestination(InterfaceClient client) throws RemoteException;
       public void deplacerJoueur(String choix,InterfaceClient client) throws RemoteException;
        public void retirerClient(InterfaceClient client) throws RemoteException;
}

