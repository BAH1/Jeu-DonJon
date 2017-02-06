/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduchat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author elhadj
 */
public class Serveur {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(1098);
       ServerChatImpl s=new ServerChatImpl();
        System.out.println(""+s.toString());
         Naming.rebind("rmi://localhost:1098/RMIT",s); 
    }
       
}
