/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduchat;


import gestionPersonnage.ListeClientParPiece;
import gestionPersonnage.Personnage;
import gestionduclient.InterfaceClient;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author elhadj
 */
public interface ServeurChat extends  Remote{
    
   
    public void recupererListeClient(ArrayList<Personnage> liste,int numeroP) throws RemoteException;
    public void broadcasterMessage() throws RemoteException;
    public void recupererMessage(String message,InterfaceClient client)throws RemoteException;
 
   
}
