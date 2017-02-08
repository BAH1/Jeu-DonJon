/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDeCombat;

import gestionPersonnage.Personnage;
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
             InterfaceCombattre serveur = (InterfaceCombattre)Naming.lookup("rmi://localhost:1097/combat");
                        Monstre m = new Monstre("gaagaaaaaaaa");
                        Personnage p1 = new Personnage("Mamadou", 1);
                        Personnage p2 = new Personnage("Mamdouuuu", 1);
                        serveur.combatMJ(m, p1);
                      
            
            
            
            
            
            } catch (MalformedURLException | RemoteException | NotBoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    
}
}
