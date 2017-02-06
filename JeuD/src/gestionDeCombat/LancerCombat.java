/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDeCombat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author elhadj
 */
public class LancerCombat {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
		// TODO Auto-generated method stub
		LocateRegistry.createRegistry(1097);
		      ImplInterfaceCombattre comb=new ImplInterfaceCombattre();
		System.out.println(comb.toString());
	    Naming.rebind("rmi://localhost:1097/combat", comb);
          
	}

    
}
