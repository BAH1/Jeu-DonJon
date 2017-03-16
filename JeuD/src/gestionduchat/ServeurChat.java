/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduchat;


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
 */
public interface ServeurChat extends  Remote{
    
   
  
    public void broadcasterMessage(int numeropiece) throws RemoteException;
    public void recupererMessage(String message,InterfaceClient client,int numeroP)throws RemoteException;
    public void recupererListeClients(ArrayList<Personnage>  liste) throws RemoteException;
   
}
