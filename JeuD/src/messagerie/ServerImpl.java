/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagerie;

import chatrmi.ClientImpl;
import chatrmi.ClientInterface;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author elhadj
 */
public class ServerImpl extends UnicastRemoteObject implements InterfaceServ{
     private ClientInterface c;
    private ArrayList<ClientInterface>clients;
    public ArrayList<String>register;
    public ServerImpl() throws RemoteException {
      clients=new ArrayList<>();
      register=new ArrayList<>();
      
    }

    
    @Override
    public synchronized void  enregistrerClient(ClientInterface client) throws RemoteException {
        //To change body of generated methods, choose Tools | Templates.
        this.clients.add(client);
    }

    @Override
     public synchronized String transmettreMessage(String message) throws RemoteException {
         //To change body of generated methods, choose Tools | Templates.
         register.add(message);
        
         /*
         for(ClientInterface c:clients)
         {
             c.recupererMessage(message);
         }*/
         String msg=new String();
         for(String m:register)
         {
             msg+=""+m+"\n";
         }
         return msg;
    }

 
}
