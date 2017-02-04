/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combats;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import messagerie.ServerImpl;
import rmiserveur.LabyrintheImpl;

/**
 *
 * @author M1stri25
 */
public class LacerServerCombat {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
		// TODO Auto-generated method stub
		LocateRegistry.createRegistry(1097);
		CombatImpl comb=new CombatImpl();
		System.out.println(comb.toString());
	    Naming.rebind("rmi://localhost:1097/combat", comb);
          
	}
    
}
