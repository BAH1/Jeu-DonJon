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
public class CombatM implements Runnable{
    
    private ArrayList<Personnage> liste;
    private ArrayList<Monstre>lesMonstre;
    private Monstre m;
    private InterfaceClient client;
    private boolean etatcombat;
   private   Personnage p;
    private String choixclient; 
    private Registre base;
   public CombatM(ArrayList<Personnage>liste,ArrayList<Monstre>lesMonstres,InterfaceClient client,Registre base) throws RemoteException
   {
       this.liste=liste;
       this.lesMonstre=lesMonstres;
       this.client=client;
       this.base=base;
      
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
  
   public void mettreAjourEtatPersonnage(String pseudo,int etat) throws RemoteException
      {
          for(Personnage p:liste)
        {
            if(p.getNom().equals(pseudo))
            {
            
               p.getClient().setEtat(etat);
               return;  
            }
            
        }
        
   
      }
    public void ajouterVieMonstre(int numeroPiece)
    {
        lesMonstre.get(numeroPiece).setVieMonstre(lesMonstre.get(numeroPiece).getVieMonstre()+1);
    
    }
      public void retirerVieMonstre(int numeroPiece)
    {
        if(lesMonstre.get(numeroPiece).getVieMonstre()!=0)
        lesMonstre.get(numeroPiece).setVieMonstre(lesMonstre.get(numeroPiece).getVieMonstre()-1);
      
    }
     public void ajouterViePersonnage(String nom)
     {
              for(Personnage p:liste)
        {
            if(p.getNom().equals(nom))
            {
            
              p.ajouterVieJoueur(1);
              return;   
            }
           
        }
        
         
     }
     public void retirerviePersonnage(String nom)
     {
               for(Personnage p:liste)
        {
            if(p.getNom().equals(nom))
            {
            
              p.retirerVieJoueur(1);
              return;   
            }
           
        }
        
     }
    public void informationSurMonstre(int numero,boolean etat)
    {
        lesMonstre.get(numero).setEtatMonstre(etat);
    }
     public String afficherString(int numero)
      {
         String s=new String();
         s+=lesMonstre.get(numero).getNomMonstre();
         return s;
      }
   public synchronized void combattreMonstre(InterfaceClient client) throws RemoteException, InterruptedException {
        Random r=new Random();
         p=new Personnage();
        p=chercherPersonnage(client.getNom());
         m = lesMonstre.get(p.getNumeropiece());
         p.setVie(base.recupererViePersonnage(p.getNom()));
       
            
              if(p.getVieJoueur()!=0)
              {
                 p.getClient().afficher("Debut contre "+afficherString(p.getNumeropiece()));
              
              m.setNbreAdversaire(m.getNbreAdversaire()+1);
                do
            {
                
                int d = r.nextInt(4);
             
               
                if(d>2)
                {
                    TimeUnit.SECONDS.sleep(8);
                    String am =""+m.getNomMonstre()+" attaque "+p.getNom();
                    p.getClient().afficher(am);
                    m.attaquerPersonnage(p);
                  
                    p.getClient().afficher("Votre nombre de vie est "+p.getVieJoueur());
                    p.getClient().afficher("Tapez q pour fuir");
                   
                }
                else 
                {
                    TimeUnit.SECONDS.sleep(8);
                    String aj =p.getNom()+" attaque "+m.getNomMonstre();
                    p.getClient().afficher(aj);
                    m.retirerVieMonstre(1);
                     
                    String vm = "Nombre de vie du monstre  "+m.getVieMonstre();
                    p.getClient().afficher(vm);
                    p.getClient().afficher("Tapez q pour fuir");
                    
                }
                 }while(p.getVieJoueur()!=0 && m.getVieMonstre()!=0);
              m.setNbreAdversaire(m.getNbreAdversaire()-1);  
            if(m.getVieMonstre()==0)
            {
             
                p.getClient().afficher("Monstre is dead: "+m.getNomMonstre());
                p.getClient().afficher("Votre nombre de vie avant "+p.getVieJoueur());
               p.ajouterVieJoueur(1);
               
                p.getClient().afficher("Votre nombre de vie apres "+p.getVieJoueur());
                p.getClient().afficher("Tapez entrer pour continuer");
                base.mettreAjourvieJoueur(p.getNom(), p.getVieJoueur());
            }
            else 
            {
                
                p.getClient().afficher("Vous avez perdu "+p.getNom());
                p.getClient().afficher("Nombre de vie du monstre "+m.getVieMonstre());
                 
                m.setVieMonstre(m.getVieMonstre()+1);
                p.getClient().afficher("La vie du monstre apres "+m.getVieMonstre());
                p.getClient().afficher("Tapez entrer pour continuer");
                base.mettreAjourvieJoueur(p.getNom(), p.getVieJoueur());
            }    
          }
          else 
          {
                  p.getClient().afficher("Votre personnage est Mort deplacer vous dans une autre pièce");
          }
          if(m.getNbreAdversaire()==0)
              m.setVieMonstre(5);
           
        
          }

    @Override
    public void run() {
        try {
            //To change body of generated methods, choose Tools | Templates.
            combattreMonstre(client);
            Thread.currentThread().interrupt();
        } catch (RemoteException ex) {
            Logger.getLogger(CombatM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            System.err.println("Interrompre");
           
             
           
              
        }
       
    }

    public Monstre getM() {
        return m;
    }

    public void setM(Monstre m) {
        this.m = m;
    }

    public Personnage getP() {
        return p;
    }

    public void setP(Personnage p) {
        this.p = p;
    }
         
          
        }
     
    
    
    
    
   

  
    

