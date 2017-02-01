/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserveur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import messagerie.ServerImpl;

/**
 *
 * @author elhadj
 */
public class ServeurRMI {


    public static void main(String[] args) throws RemoteException, MalformedURLException {
		// TODO Auto-generated method stub
		LocateRegistry.createRegistry(1099);
		LabyrintheImpl obj=new LabyrintheImpl();
                obj.creerLabyrinthe();
              
		System.out.println(obj.toString());
	    Naming.rebind("rmi://localhost:1099/by", obj);
           
        ServerImpl s=new ServerImpl();
        System.out.println(""+s.toString());
        LocateRegistry.createRegistry(1098);
        Naming.rebind("rmi://localhost:1098/RMIT",s);
         
        
	   

	}

    
}
