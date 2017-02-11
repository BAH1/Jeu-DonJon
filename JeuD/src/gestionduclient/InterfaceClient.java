/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduclient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author elhadj
 */
public interface InterfaceClient extends Remote{
    public void afficherEtatConnexion(int numeroPiece)throws RemoteException;
    public void setNumeropiece(int numeropiece)throws RemoteException;
     public String getNom() throws RemoteException;
  
     public String choixclient() throws RemoteException;
     public void afficher(String s) throws RemoteException;
     public String envoyerMessage()throws RemoteException;
     public void recupererMessage(String message)throws RemoteException;
     public int getNumeropiece()throws RemoteException;
     public void afficherNotification()throws RemoteException;
     public void setNom(String nom)throws RemoteException; 
     public void setVie(int vie) throws RemoteException;
     public int getVie() throws RemoteException;
    
}
