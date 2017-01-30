/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagerie;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import rmiserveur.LabyrintheImpl;

/**
 *
 * @author M1stri25
 */
public class ServeurRmiMessagerie {
     public static void main(String[] args) throws RemoteException, MalformedURLException {
		// TODO Auto-generated method stub
		LocateRegistry.createRegistry(1098);
		MessageImpl obj=new MessageImpl();
                //obj.envoyerMessage("pseudo", "pmsg", Integer.SIZE);
              
		System.out.println(obj.toString());
	    Naming.rebind("rmi://localhost:1098/by", (Remote) obj);
	   

	}

}
