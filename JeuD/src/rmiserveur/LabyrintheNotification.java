/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author elhadj
 */
public class LabyrintheNotification extends UnicastRemoteObject implements LabyrintheNotIfication{
   
   
    private String pseudo;

    public LabyrintheNotification(String pseudo) throws RemoteException{
        super();
        this.pseudo = pseudo;
    }
    
    @Override
    public void notification(String pseudo, int nombre) throws RemoteException {
        System.out.println("Le pseudo "+pseudo+" est avec "+nombre);
    }
    
}
