/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserveur;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author elhadj
 */
public interface LabyrintheNotIfication extends Remote{
    
    public void notification(String pseudo,int nombre)throws RemoteException;
   
}
