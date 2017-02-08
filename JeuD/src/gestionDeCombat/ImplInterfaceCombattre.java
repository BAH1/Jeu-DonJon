/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDeCombat;

import gestionduLabyrinthe.ImplementationDuLabyrinthe;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author elhadj
 */
public class ImplInterfaceCombattre extends UnicastRemoteObject implements InterfaceCombattre{
    
   private  ArrayList<Monstre>lesMonstre;
   private String tabNameMonstre[]={"CELL","BOUBOU","BOUBOUGENTIL","GOKUBLACK",
   "DRAGON","DRAGONBLACK","DINO","NGORO","CHIVA"};
    
    
     String porte;
    Scanner sc=new Scanner(System.in);
    
      public ImplInterfaceCombattre() throws RemoteException {
		super();
                lesMonstre=new ArrayList<>();
	}
    
    public void InitMonstreSalle()
    {
      for(int i=0;i<10;i++)
      {
         lesMonstre.add(new Monstre(tabNameMonstre[i]));
      }
    }
    public void combatMJ (Monstre M, Joueur j) throws RemoteException {
        System.out.println("Monstre "+M+" attaque le joueur " +j);
       // j.retirerVieJoueur(1);
       
       
       
        System.out.println("la vie du joueur: " +j.getVieJoueur());
        M.Menu();
        Integer choix=Integer.parseInt(M.Menu());
        System.out.println(choix);
            while(choix==1){
             System.out.println("Joueur " +j+ " attaque le monstre " + M );
             M.retirerVieMonstre(1);
             System.out.println("la vie du Monstre est : " +M.getVieMonstre());
                if(M.getVieMonstre()>0){ 
                        System.out.println("Monstre "+M+" attaque le joueur " +j);
                        j.retirerVieJoueur(1);
                        System.out.println("la vie du joueur: " +j.getVieJoueur());
                        M.Menu();
                        choix=Integer.parseInt(M.Menu());
                }else{ 
                System.out.println("Le Monstre est Mort");
                j.ajouterVieJoueur(1);
                j.Menureduit();
                }
      
            }
            if(choix==2)   //deplacement
            { 
                  ImplementationDuLabyrinthe la=new ImplementationDuLabyrinthe("Savane");
                  
                  porte=sc.nextLine();
                  //la.deplacerJoueur(j.getNomjoueur(), porte);
            }
            if(choix==3) // envoyer Message
            { 
            }
            if(choix==4) //Quitter
            { 
            }
            
}
    
    public void combatJJ (Joueur j1, Joueur j2) throws RemoteException{
        j1.Menu();
        Integer choix=Integer.parseInt(j1.Menu());
        while(choix==1){
            if(j1.getVieJoueur()>0 && j2.getVieJoueur()>0){
                    System.out.println("le joueur " +j1.getNomjoueur()+ " attaque le joueur " +j2.getNomjoueur());
                    j2.retirerVieJoueur(1);
                    System.out.println("le joueur " +j2.getNomjoueur()+ " attaque le joueur " +j1.getNomjoueur());
                    j1.retirerVieJoueur(1);
                    j1.Menu();
                    choix=Integer.parseInt(j1.Menu());
            
            }
            else if (j1.getVieJoueur()<=0) { 
                System.out.println(j1.getVieJoueur());
                System.out.println("[ "+j1.getNomjoueur()+" ] : Vous avez perdu le combat contre " +j2.getNomjoueur());
                j2.ajouterVieJoueur(1);
                break;
                
            }
            else if (j2.getVieJoueur()<=0) { 
               System.out.println(j2.getVieJoueur());
                System.out.println("[ "+j2.getNomjoueur()+" ] : Vous avez perdu le combat contre " +j2.getNomjoueur());
                j1.ajouterVieJoueur(1);
                break;
            }
        
        }
        if(choix==2) //appelle de la methode deplacer
        {
        
        }
        else if(choix == 3) // chat
        { 
        
        }
        else if (choix==4)
        { 
        
        }
        
}
    
}
