/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDeCombat;

import gestionPersonnage.Personnage;
import gestiondelabaseDeDonnee.Registre;
import gestionduclient.InterfaceClient;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elhadj
 */
public class CombatJoueur implements Runnable{
    
    private String pseudo;
    private InterfaceClient client;
     private ArrayList<Personnage> liste;
     private Registre base;

    public CombatJoueur(String pseudo, InterfaceClient client, ArrayList<Personnage> liste,Registre base) throws RemoteException {
        this.pseudo = pseudo;
        this.client = client;
        this.liste = liste;
        this.base=base;
    }
    public Personnage chercherPersonnage(String pseudo)
    {
          for(Personnage p:liste)
        {
            if(p.getNom().equals(pseudo))
            {
               return p;
                
            }
        }
        return null;
 
      }
       
     public void combattreJoueur() throws RemoteException, InterruptedException {
        Random r=new Random();
        String choix=new String();
          Personnage p1=new Personnage();
        p1=chercherPersonnage(client.getNom());
        Personnage p2 = new Personnage();
        p2=chercherPersonnage(pseudo);
         
        if(p1.getNumeropiece()==p2.getNumeropiece())
        {
            p1.setVie(base.recupererViePersonnage(p1.getNom()));
            p2.setVie(base.recupererViePersonnage(p2.getNom())); 
         
            if(p1.getVieJoueur()!=0 && p2.getVieJoueur()!=0)
            {
               p1.getClient().afficher("Debut contre "+p2.getNom());
            p2.getClient().afficher("Debut contre "+p1.getNom());
            p1.setNbreCombat(p1.getNbreCombat()+1);
            p2.setNbreCombat(p2.getNbreCombat()+1);
               do
            {
                int d = r.nextInt(3);
                
                if(d>1)
                {
                    TimeUnit.SECONDS.sleep(2);
                    String am = p2.getNom()+" attaque "+p1.getNom();
                    p1.getClient().afficher(am);
                    p2.attaquerpersonnage(p1);
                    p1.getClient().afficher("Votre nombre de vie est "+p1.getVieJoueur());
                    p1.getClient().afficher("Tapez q pour fuir");
               
                }
                else 
                {
                    TimeUnit.SECONDS.sleep(2);
                    String aj =p1.getNom()+" attaque "+p2.getNom();
                    p2.getClient().afficher(aj); 
                    p1.getClient().afficher(aj);
                  
                    p1.attaquerpersonnage(p2);
                    String vm = "Nombre de vie du Joueur  "+p2.getNom()+" "+p2.getVieJoueur();
                    p2.getClient().afficher(vm);
                    p1.getClient().afficher(vm);
                }
                
            }while(p1.getVieJoueur()!=0 && p2.getVieJoueur()!=0);
            p1.setNbreCombat(p1.getNbreCombat()-1);
            p2.setNbreCombat(p2.getNbreCombat()-1);
          
             
            if(p1.getVieJoueur()==0)
            {
             
                p1.getClient().afficher("vous etes mort: "+p1.getNom());
                p2.getClient().afficher("Votre nombre de vie avant "+p2.getVieJoueur());
              
                p2.ajouterVieJoueur(1);
                p2.getClient().afficher("Votre nombre de vie apres "+p2.getVieJoueur());
                p1.getClient().afficher("Tapez entrer pour continuer");
                
                base.mettreAjourvieJoueur(p2.getNom(), p2.getVieJoueur());
                base.mettreAjourvieJoueur(p1.getNom(), p1.getVieJoueur());
            }
            else 
            {
                
                p2.getClient().afficher("Vous etes mort "+p2.getNom());
                p1.getClient().afficher("Votre nombre de vie avant "+p1.getVieJoueur());
                p1.ajouterVieJoueur(1);
                p1.getClient().afficher("votre nombre de vie après  "+p1.getVieJoueur());
                p1.getClient().afficher("Tapez entrer pour continuer");
                base.mettreAjourvieJoueur(p1.getNom(), p1.getVieJoueur());
                base.mettreAjourvieJoueur(p2.getNom(), p2.getVieJoueur());
            }
    
        }
         else if(p1.getVieJoueur()==0)
         {
                p1.getClient().afficher("Deplacer vous dans une autre pièce ");
               
         }
         else 
         {
             p2.getClient().afficher("Deplacer vous dans une autre pièce ");
             p1.getClient().afficher("Son personnage est déjà mort");
         }
                        
        }
      
            
        
      
    }
    
    @Override
    public void run() {
        try {
            //To change body of generated methods, choose Tools | Templates.
            combattreJoueur();
            Thread.currentThread().interrupt();
        } catch (RemoteException ex) {
            
            Logger.getLogger(CombatJoueur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            System.err.println("Combat arrêté");
        }
    }
    
    
}
