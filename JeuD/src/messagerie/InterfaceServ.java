/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagerie;

import chatrmi.ClientInterface;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 *
 * @author elhadj
 */
public interface InterfaceServ extends Remote{
    void enregistrerClient(ClientInterface client) throws RemoteException;
    String transmettreMessage(String message) throws RemoteException;
   
}
