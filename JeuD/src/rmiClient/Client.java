/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiClient;

import chatrmi.ClientImpl;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import messagerie.InterfaceServ;
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
            InterfaceServ chat=(InterfaceServ)Naming.lookup("rmi://localhost:1098/RMIT");
            LabyrintheInterface stub=(LabyrintheInterface)Naming.lookup("rmi://localhost:1099/by");
            Joueur joueur=new Joueur();
            Scanner sc=new Scanner(System.in);
           String res,choix2;
           int choix;
            ClientImpl c = null;
            do
            {
            	joueur.Saisir();
                res=stub.connexion(joueur.getNomjoueur());
                if(Integer.parseInt(res)==1)
                	System.out.println("Bienvenue Dans le labyrinthe"); 
                else if(Integer.parseInt(res)==2)
                	System.out.println("Bienvenue dans le labyrinthe"
                			+ "\n Prochaine COnnexion avec ce pseudo ");
              
            }while(Integer.parseInt(res)==0);
           
              System.out.println(stub.positionJoueur(joueur.getNomjoueur()));
              choix=Integer.parseInt(joueur.Menu());
             if(choix==2)
             {
                   c=new ClientImpl(chat,joueur.getNomjoueur());
                   new Thread(c).start();
                   c.afficher();
             }
             else if(choix==1)
             {
                  System.out.println(stub.informationSurPieceCote(joueur.getNomjoueur()));
                  
                  choix2=sc.nextLine();
                  stub.deplacerJoueur(joueur.getNomjoueur(), choix2);
                  
             }
            
                 
              
            
              
       
       
        
        
             new Thread(c).start();    
            
              
	} catch (MalformedURLException | RemoteException | NotBoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

    
}
