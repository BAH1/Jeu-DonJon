/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDeCombat;

import gestionPersonnage.Personnage;
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
public class CombatM implements Runnable{
    
    private ArrayList<Personnage> liste;
    private ArrayList<Monstre>lesMonstre;
    private Monstre m;
    private InterfaceClient client;
    private boolean etatcombat;
    Personnage p;
   public CombatM(ArrayList<Personnage>liste,ArrayList<Monstre>lesMonstres,InterfaceClient client)
   {
       this.liste=liste;
       this.lesMonstre=lesMonstres;
       this.client=client;
      
   }
     public boolean isEtatcombat() {
        return etatcombat;
    }

    public void setEtatcombat(boolean etatcombat) {
        this.etatcombat = etatcombat;
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
   public void mettreAjourEtatPersonnage(String pseudo,boolean etat) throws RemoteException
      {
          for(Personnage p:liste)
        {
            if(p.getNom().equals(pseudo))
            {
              if(etat==true)
               p.setTest(1);
              else 
               p.setTest(0);
            }
            return;
        }
        
   
      }
  
    
   public void combattreMonstre(InterfaceClient client) throws RemoteException, InterruptedException {
        Random r=new Random();
         p=new Personnage();
        p=chercherPersonnage(client.getNom());
         m = lesMonstre.get(p.getNumeropiece());
         p.getClient().afficher("Debut contre "+m.getNomMonstre());
          etatcombat=false;
            do
            {
                int d = r.nextInt(4);
                
                if(d>2)
                {
                    TimeUnit.SECONDS.sleep(5);
                    String am = m.getNomMonstre()+" attaque "+p.getNom();
                    p.getClient().afficher(am);
                    m.attaquerPersonnage(p);
                    p.getClient().afficher("Votre nombre de vie est "+p.getVieJoueur());
                    p.getClient().afficher("Tapez q pour fuir");
                   
                }
                else 
                {
                    TimeUnit.SECONDS.sleep(5);
                    String aj =p.getNom()+" attaque "+m.getNomMonstre();
                    p.getClient().afficher(aj);
                    m.retirerVieMonstre(1);
                    String vm = "Nombre de vie du monstre  "+m.getVieMonstre();
                    p.getClient().afficher(vm);
                    p.getClient().afficher("Tapez q pour fuir");
                    
                }
                
            }while(p.getVieJoueur()!=0 && m.getVieMonstre()!=0);
             etatcombat=true;
            if(m.getVieMonstre()==0)
            {
             
                p.getClient().afficher("Monstre is dead: "+m.getNomMonstre());
                p.getClient().afficher("Votre nombre de vie avant "+p.getVieJoueur());
                p.ajouterVieJoueur(1);
                p.getClient().afficher("Votre nombre de vie apres "+p.getVieJoueur());
                
                
            }
            else 
            {
                
                p.getClient().afficher("Client is dead "+p.getNom());
                p.getClient().afficher("Votre nombre de vie avant "+m.getVieMonstre());
                m.setVieMonstre(1);
                p.getClient().afficher("La vie du monstre apres "+m.getVieMonstre());
                
            }
            
        }
     
    
    
    
    
    @Override
    public void run() {
        try {
          
            combattreMonstre(client);
              Thread.currentThread().interrupt();
        } catch (RemoteException ex) {
            Logger.getLogger(CombatM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            System.err.println("le combat est bien arrêté");
        }
   
     
     
    }

  
    
}
