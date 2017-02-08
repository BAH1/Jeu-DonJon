/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDeCombat;

import gestionPersonnage.Personnage;
import gestionduLabyrinthe.ImplementationDuLabyrinthe;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
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
    
    
      public ImplInterfaceCombattre() throws RemoteException {
		super();
                lesMonstre=new ArrayList<>();
	}
    
/*    public void InitMonstreSalle()
    {
      for(int i=0;i<10;i++)
      {
         lesMonstre.add(new Monstre(tabNameMonstre[i]));
      }
    }*/
    
    public void combatMJ (Monstre m, Personnage p) throws RemoteException  {
            Random rand = new Random();
      /*      m.Menu();
            Integer choix=Integer.parseInt(m.Menu());
        System.out.println(choix);
    if(choix==1)   {  
    while(choix==1){
        
        if(m.isEtatMonstre()==false)
        {
            m.setEtatMonstre(true);
              
                int d = rand.nextInt(10);
                if(d<=5){
                          if(m.getVieMonstre()>0){ 
                                System.out.println("Monstre "+m.getNomMonstre()+" attaque Personnage " +p.getNom());
                                m.attaquerPersonnage(p);
                            }
                          else
                          {
                                System.out.println("Le Monstre est deja Mort");
                                p.ajouterVieJoueur(1);
                                p.Menureduit();
                          }
                }
                else{
                     if(p.getVieJoueur()>0){
                        System.out.println("Personnage "+p.getNom()+" attaque Monstre " +m.getNomMonstre());
                        p.attaquerMonstre(m);
                     }
                }
        
         }
        else
        { System.out.println("pas de monstre dispo!!!!!!!");}
   
        m.setEtatMonstre(false);
        m.Menu();
        choix=Integer.parseInt(m.Menu());     
    
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
     
            }*/
            
}
 /*   
    public void combatJJ (Personnage p1, Personnage p2) throws RemoteException{
        Random rand = new Random();
        p1.Menu();
        Integer choix=Integer.parseInt(p1.Menu());
    if(choix==1) 
        {   
        while(choix==1){
        
                if(p1.isEtatpersonnage()==false &&  p2.isEtatpersonnage()==false)
                        {
                            p1.setEtatpersonnage(true);
                            p2.setEtatpersonnage(true);
                            int d = rand.nextInt(10);
                                if(d<=5)
                                {
            
                                            if(p1.getVieJoueur()>0){
                                                System.out.println("attaque du personnage " +p2.getNom());
                                                p1.attaquerpersonnage(p2);
                                            }
                                            else{ 
                                                   System.out.println(p1.getVieJoueur());
                                                   System.out.println("[ "+p1.getNom()+" ] : Vous avez perdu le combat");
                                                   p2.ajouterVieJoueur(1);
                                            }
                                }
                                else
                                {  
                                            if(p2.getVieJoueur()>0){
                                                System.out.println("attaque du personnage " +p1.getNom());
                                                p2.attaquerpersonnage(p1);
                                            }
                                            else{ 
                                                   System.out.println(p2.getVieJoueur());
                                                   System.out.println("[ "+p2.getNom()+" ] : Vous avez perdu le combat");
                                                   p2.ajouterVieJoueur(1);
                                            }
                                }
                                                
                                                                     
                
                        }
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

    @Override
    public void a() throws RemoteException {
        System.out.println("a");
    }
*/
   
    
    }
