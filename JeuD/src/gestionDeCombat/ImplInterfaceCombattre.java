/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDeCombat;

import gestionPersonnage.Personnage;
import gestionduLabyrinthe.ImplementationDuLabyrinthe;
import gestionduLabyrinthe.Piece;
import gestionduclient.InterfaceClient;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author M1stri
 */
public class ImplInterfaceCombattre extends UnicastRemoteObject implements InterfaceCombat{
    
   
   private String tabNameMonstre[]={"CELL","BOUBOU","BOUBOUGENTIL","GOKUBLACK",
   "DRAGON","DRAGONBLACK","DINO","NGORO","CHIVA","TATA"};
    
    private ArrayList<Monstre>lesMonstre;
    String porte;
    private ArrayList<Personnage>lesPersonnages;
    
      public ImplInterfaceCombattre() throws RemoteException {
		super();
                lesMonstre=new ArrayList<>();
                lesPersonnages = new ArrayList<>();
               
	}
    
     public void initMonstreSalle() throws RemoteException
    {
      for(int i=0;i<10;i++)
      {
         lesMonstre.add(new Monstre(tabNameMonstre[i]));
      }
    }
     
      public void initPersonnageSalle() throws RemoteException
    {
      for(int i=0;i<10;i++)
      {
         lesPersonnages.add(new Personnage());
      }
    }
    
    @Override
    
    public void combattreMonstre(Personnage p) throws RemoteException {
        Random r=new Random();
        String choix=new String();
        Monstre m = lesMonstre.get(p.getNumeropiece());
        //Personnage p = new Personnage();
         
        if(m.isEtatMonstre()==false)
        {
            p.afficher("Debut contre "+m.getNomMonstre());
            m.setEtatMonstre(true);
            do
            {
                int d = r.nextInt(4);
                
                if(d>2)
                {
                    String am = m.getNomMonstre()+" attaque "+p.getNom();
                    p.getClient().afficher(am);
                    m.attaquerPersonnage(p);
                    p.getClient().afficher("Votre nombre de vie est "+p.getVieJoueur());
                    p.afficher("Tapez q pour fuir");
                   // choix=p.choixJoueur();
                }
                else 
                {
                    String aj =p.getNom()+" attaque "+m.getNomMonstre();
                    p.getClient().afficher(aj);
                    m.retirerVieMonstre(1);
                    String vm = "Nombre de vie du monstre  "+m.getVieMonstre();
                    p.getClient().afficher(vm);
                }
                
            }while(p.getVieJoueur()!=0 && m.getVieMonstre()!=0 && !choix.equals("q"));
             
            if(m.getVieMonstre()==0)
            {
             
                p.getClient().afficher("Monstre is dead: "+m.getNomMonstre());
                p.getClient().afficher("Votre nombre de vie avant "+p.getVieJoueur());
              
                p.ajouterVieJoueur(1);
                p.getClient().afficher("Votre nombre de vie apres "+p.getVieJoueur());
                
                
            }
            else if(p.getVieJoueur()==0)
            {
                
                p.getClient().afficher("Client is dead "+p.getNom());
                p.getClient().afficher("Votre nombre de vie avant "+m.getVieMonstre());
                m.setVieMonstre(1);
                p.getClient().afficher("La vie du monstre apres "+m.getVieMonstre());
                
            }
            
        }
      
    }
    /*
    public void combatMJ (Monstre m, Personnage p) throws RemoteException  {
            Random rand = new Random();
         
           m.Menu();
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
                  
          //  porte=sc.nextLine();
                  //la.deplacerJoueur(j.getNomjoueur(), porte);
    }
 if(choix==3) // envoyer Message
    { 
    
 }
 if(choix==4) //Quitter
            
 {
     
            }
}*/

    
    public void combattreJoueur(Personnage p1) throws RemoteException {
        Random r=new Random();
        String choix=new String();
        Personnage p2 = lesPersonnages.get(p1.getNumeropiece());
 
        if(p1.isEtatpersonnage()==false &&  p2.isEtatpersonnage()==false)
        {
            p1.getClient().afficher("Debut contre "+p2.getNom());
            p2.getClient().afficher("Debut contre "+p1.getNom());
            p1.setEtatpersonnage(true);
            p2.setEtatpersonnage(true);
            do
            {
                int d = r.nextInt(4);
                
                if(d>2)
                {
                    String am = p2.getNom()+" attaque "+p1.getNom();
                    p1.getClient().afficher(am);
                    p2.attaquerpersonnage(p1);
                    p1.getClient().afficher("Votre nombre de vie est "+p1.getVieJoueur());
                    p1.afficher("Tapez q pour fuir");
                   // choix=p.choixJoueur();
                }
                else 
                {
                    String aj =p1.getNom()+" attaque "+p2.getNom();
                    p2.getClient().afficher(aj); 
                    p1.getClient().afficher(aj);
                  
                    p2.retirerVieJoueur(1);
                    String vm = "Nombre de vie du Joueur  "+p2.getNom();
                    p2.getClient().afficher(vm);
                    p1.getClient().afficher(vm);
                }
                
            }while(p1.getVieJoueur()!=0 && p2.getVieJoueur()!=0 && !choix.equals("q"));
             
            if(p1.getVieJoueur()==0)
            {
             
                p1.getClient().afficher("vous etes mort: "+p1.getNom());
                p2.getClient().afficher("Votre nombre de vie avant "+p2.getVieJoueur());
              
                p2.ajouterVieJoueur(1);
                p2.getClient().afficher("Votre nombre de vie apres "+p2.getVieJoueur());
                
                
            }
            else if(p2.getVieJoueur()==0)
            {
                
                p2.getClient().afficher("Vous etes mort "+p2.getNom());
                p1.getClient().afficher("Votre nombre de vie avant "+p1.getNom());
                p1.setVie(1);
                p1.getClient().afficher("votre vie apres "+p1.getVieJoueur());
                
            }
            
        }
      
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
        
}*/



    
    }
