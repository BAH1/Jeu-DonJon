/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combats;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import messagerie.InterfaceServ;
import personnage.Joueur;
import personnage.Monstre;
import rmiserveur.LabyrintheInterface;

/**
 *
 * @author M1stri25
 */
public class testclientCombat {
    
     public static void main(String[] args) throws NotBoundException {
		// TODO Auto-generated method stub
	try {
            InterfaceCombat combat = (InterfaceCombat)Naming.lookup("rmi://localhost:1097/combat");
            LabyrintheInterface stub=(LabyrintheInterface)Naming.lookup("rmi://localhost:1099/by");
            Joueur joueur=new Joueur();
            Monstre monstre = new Monstre("Cheikh");
            combat.attaquer(monstre, joueur);
            
            
            } catch (MalformedURLException | RemoteException | NotBoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    
}
}