/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDeCombat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author elhadj
 */
public class TestClient {
      public static void main(String[] args) throws NotBoundException, MalformedURLException {
		// TODO Auto-generated method stub
	try {
            InterfaceCombattre combat = (InterfaceCombattre)Naming.lookup("rmi://localhost:1097/combat");
         
            Joueur J1 =new Joueur("bah");
            Joueur J2 =new Joueur("diallo");
            //Monstre M = new Monstre("Cheikh");
            //combat.combatMJ(monstre, bah);
            combat.combatJJ(J1, J2);
            
            
            
            
            
            } catch (MalformedURLException | RemoteException | NotBoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    
}
}
