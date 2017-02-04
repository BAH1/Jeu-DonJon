/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combats;

import chatrmi.ClientImpl;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;
import personnage.Joueur;
import personnage.Monstre;
import registreDe.Registre;
import rmiserveur.LabyrintheImpl;

/**
 *
 * @author M1stri25
 */
public class CombatImpl extends UnicastRemoteObject implements InterfaceCombat {
    String porte;
    Scanner sc=new Scanner(System.in);
    
    protected CombatImpl() throws RemoteException {
		super();

	}
    
    
    public void attaquer (Monstre M, Joueur j) throws RemoteException {
        System.out.println("Monstre "+M+" attaque le joueur " +j);
        j.retirerVieJoueur(1);
        System.out.println("la vie du joueur: " +j.getVieJoueur());
        M.Menu();
        Integer choix=Integer.parseInt(M.Menu());
        if(choix==1)
            {
             System.out.println("Monstre " +j+ " attaque le monstre " + M );
             M.retirerVieMonstre(1);
             System.out.println("la vie du Monstre est : " +M.getVieMonstre()); 
      
            }
        else if(choix==2)   
            { 
                LabyrintheImpl la = new LabyrintheImpl();
                   System.out.println(la.informationSurPieceCote(j.getNomjoueur()));
                  porte=sc.nextLine();
                  la.deplacerJoueur(j.getNomjoueur(), porte);
            }
            
}
    
}
