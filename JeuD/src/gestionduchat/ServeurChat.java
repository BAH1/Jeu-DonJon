/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduchat;


import gestionduclient.InterfaceClient;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author elhadj
 */
public interface ServeurChat extends  Remote{
    
    public void  envoyerMessageAtous(int numeroPiece) throws RemoteException;
    public void  enregistrerClient(InterfaceClient c,int numero)throws RemoteException;
    public void  recevoirMessage(String message,InterfaceClient client)throws RemoteException;
   
}
