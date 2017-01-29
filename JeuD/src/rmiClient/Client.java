/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiClient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import personnage.Joueur;
import rmiserveur.LabyrintheInterface;

/**
 *
 * @author elhadj
 */
public class Client {
    
    
    public static void main(String[] args) throws NotBoundException {
		// TODO Auto-generated method stub
	try {
            LabyrintheInterface stub=(LabyrintheInterface)Naming.lookup("rmi://localhost:1099/by");
            Joueur joueur=new Joueur();
           String res;
            do
            {
            	joueur.Saisir();
                res=stub.connexion(joueur.getNomjoueur());
                if(Integer.parseInt(res)==1)
                	System.out.println("Bienvenue Dans le labyrinthe"); 
                else if(Integer.parseInt(res)==2)
                	System.out.println("Bienvenue dans le labyrinth"
                			+ "Prochaine Inscription avec ce pseudo ");
              
            }while(Integer.parseInt(res)==0);
            
              System.out.println(stub.positionJoueur(joueur.getNomjoueur()));
		//System.out.println(""+lab.afficher(1000.0));
	} catch (MalformedURLException | RemoteException | NotBoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

    
}
