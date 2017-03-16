/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDeCombat;

import com.mysql.jdbc.TimeUtil;
import gestionPersonnage.ListePersonnage;
import gestionPersonnage.Personnage;
import gestionduLabyrinthe.ImplementationDuLabyrinthe;
import gestionduLabyrinthe.Piece;
import gestionduclient.InterfaceClient;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M1stri
 */
public class ImplInterfaceCombattre extends UnicastRemoteObject implements InterfaceCombat{
    
   
   private String tabNameMonstre[]={"CELL","BOUBOU","BOUBOUGENTIL","GOKUBLACK",
   "DRAGON","DRAGONBLACK","DINO","NGORO","CHIVA","ZORO","TORO"};
    
    private ArrayList<Monstre>lesMonstre;
    String porte;
    private ArrayList<Personnage> liste;
    private int numeroPiece;
    private CombatM combatMonstre;
    private Thread t;
    private ArrayList<Thread>listeC;
     private ArrayList<Thread>listeCP;
    private HashMap<Integer,ArrayList<Thread>> listeCombatPersonne;
    private HashMap<Integer,ArrayList<Thread>>listeCombat;
      public ImplInterfaceCombattre() throws RemoteException {
		super();
                lesMonstre=new ArrayList<>();
              
                listeCombat=new HashMap<>();
                listeCombatPersonne=new HashMap<>();
                listeC=new ArrayList<>();
                initialisation();
               
	}
      public void initialisation()
      {
        for(int i=0;i<10;i++)
        { 
            listeC=new ArrayList<>();
            listeCP=new ArrayList<>();
            listeCombat.put(i, listeC);
            listeCombatPersonne.put(i, listeCP);
        }
      }
     
      
      public void reinitialiserVieDuMonstre(InterfaceClient client) throws RemoteException
      {
          int i=0;
           Personnage p=chercherPersonnage(client.getNom());
            for(Thread combat:listeCombat.get(p.getNumeropiece()))
          {
            
            
               if(combat.isAlive())
                   i++;
             
          }
         if(i==0)
         {
             lesMonstre.get(p.getNumeropiece()).setVieMonstre(5);
         }
           
      }
      public int verifierEtatJoueur(InterfaceClient client) throws RemoteException
     {
         Personnage pers=chercherPersonnage(client.getNom());
         
         return pers.getNbreCombat();
     }

      
      
      public void fuirCombatEntreJoueur(String choix,InterfaceClient client,String pseudo) throws RemoteException
      {
          if(choix.equals("q"))
          {
              Personnage p1=chercherPersonnage(client.getNom());
              Personnage p2=chercherPersonnage(pseudo);
              for(Thread thread:listeCombatPersonne.get(p1.getNumeropiece()))
             {
              if(thread.getName().contains(p1.getNom()))
              {
                  thread.interrupt();
                  p1.setNbreCombat(p1.getNbreCombat()-1);
                  p2.setNbreCombat(p2.getNbreCombat()-1);
              }
             }
              if(p2.getNbreCombat()==0)
              {
                 p1.getClient().afficher("Fuite de "+p1.getNom());
                 p1.getClient().afficher("Nombre de vie avant de "+p1.getNom()+""+p1.getVieJoueur());
                 p1.getClient().afficher("Nombre de vie avant de "+p2.getNom()+""+p2.getVieJoueur());
                 p2.setVie(p2.getVieJoueur()+1);
                  p1.getClient().afficher("Nombre de vie après de "+p1.getNom()+""+p1.getVieJoueur());
                 p1.getClient().afficher("Nombre de vie après de "+p2.getNom()+""+p2.getVieJoueur());
              }
              else 
              {
                 p1.getClient().afficher("Fuite de "+p1.getNom());
                 p1.getClient().afficher("Nombre de vie avant de "+p1.getNom()+""+p1.getVieJoueur());
                 p1.getClient().afficher("Nombre de vie avant de "+p2.getNom()+""+p2.getVieJoueur());
                 p1.getClient().afficher("Malheureusement pour toi vous etiez plusieurs sur lui ");
              }
              
                  
          }
      }
      public void fuirlecombat(String choix,InterfaceClient client) throws RemoteException
      {
          Monstre m;
          int i=0;
          if(choix.equals("q"))
          {
                Personnage p=chercherPersonnage(client.getNom());
                m=lesMonstre.get(p.getNumeropiece());
            
                
          for(Thread combat:listeCombat.get(p.getNumeropiece()))
          {
             if(combat.getName().equals(p.getNom()))
             {
                
              combat.interrupt();
              m.setNbreAdversaire(m.getNbreAdversaire()-1);
              
                 
           
             }
             
          }
             if(m.getNbreAdversaire()==0)
             {
              p.getClient().afficher("Fuite de "+p.getNom());
              p.getClient().afficher("Nombre de vie du monstre avant "+m.getVieMonstre());
              m.setVieMonstre(m.getVieMonstre()+1);
              p.getClient().afficher("Nombre de vie du monstre Après "+m.getVieMonstre());
       
             }
             else 
             {
                  p.getClient().afficher("Fuite de "+p.getNom());
                  p.getClient().afficher("Nombre de vie du monstre avant "+m.getVieMonstre());
                  p.getClient().afficher("Malheureusement pour toi et dire que vous étiez 2 contre lui ");
             }
               
          }
      }
      /*Combat avec le monstre */
      public boolean etatCombat(InterfaceClient client) throws RemoteException
      {
           Personnage p=chercherPersonnage(client.getNom());
          for(Thread combat:listeCombat.get(p.getNumeropiece()))
          {
             if(combat.getName().equals(p.getNom()))
             {
                return combat.isAlive();
             }
          }
          return false;
      }
     
      public boolean etatcombatDuJoueur(InterfaceClient client) throws RemoteException
      {
          Personnage pa=chercherPersonnage(client.getNom());
          for(Thread thread:listeCombatPersonne.get(pa.getNumeropiece()))
          {
              if(thread.getName().contains(pa.getNom()))
                  return thread.isAlive();
          }
         
          return false;
      }
      public void combattreJoueur(InterfaceClient client,String pseudo)throws RemoteException
      {
          String nomThread=new String();
           Personnage p=chercherPersonnage(client.getNom());
           
          CombatJoueur co=new CombatJoueur(pseudo, client, liste);
          Thread combJoueur=new Thread(co);
          combJoueur.start();
          
          nomThread=client.getNom()+""+pseudo;
          combJoueur.setName(nomThread);
           listeCombatPersonne.get(p.getNumeropiece()).add(combJoueur);
           
      }
      public void combattreLemonstre(InterfaceClient client) throws RemoteException
      {
          Personnage p=chercherPersonnage(client.getNom());
          Monstre m=lesMonstre.get(p.getNumeropiece());
         
          combatMonstre=new CombatM(liste, lesMonstre,client);
             t=new Thread(combatMonstre);
             t.start();
             t.setName(p.getNom());
             
             listeCombat.get(p.getNumeropiece()).add(t);
           
       
           /*CombatMonstre comba=new CombatMonstre(lesMonstre, liste, client);
           Thread th=new Thread(comba);
           th.start();*/
           
        
        
      }
      
      
      
      
      
      public void recupererListeClient(ArrayList<Personnage> liste)throws RemoteException
      {
          this.liste=liste;
          
      }
      public void notification() throws RemoteException
      {
          if(liste.size()>1)
          {
              for(Personnage p:liste)
              {
                  p.getClient().afficher(listePersonnage());
              }
          }
      }
      public int nombreDeJoueur() throws RemoteException
      {
          return liste.size();
      }
      public String listePersonnage()throws RemoteException
      {
          String s=new String();
          s+="Liste Pesonnages :\n";
          for(Personnage p:liste)
              s+=""+p.getNom()+"\n";
          return s;
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
   
    
     public void initMonstreSalle() throws RemoteException
    {
      for(int i=0;i<=10;i++)
      {
        lesMonstre.add(new Monstre(tabNameMonstre[i]));
        
      }
    }
     
      
    /*
    @Override
    
    public void combattreMonstre(InterfaceClient client) throws RemoteException {
        Random r=new Random();
        String choix=new String();
        Personnage p=new Personnage();
        p=chercherPersonnage(client.getNom());
        Monstre m = lesMonstre.get(p.getNumeropiece());
        
         
        if(m.isEtatMonstre()==false)
        {
            p.getClient().afficher("Debut contre "+m.getNomMonstre());
            m.setEtatMonstre(true);
            mettreAjourEtatPersonnage(p.getNom(), true);
            do
            {
                int d = r.nextInt(4);
                
                if(d>2)
                {
                    String am = m.getNomMonstre()+" attaque "+p.getNom();
                    p.getClient().afficher(am);
                    m.attaquerPersonnage(p);
                    
                    p.getClient().afficher("Votre nombre de vie est "+p.getVieJoueur());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ImplInterfaceCombattre.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    p.getClient().afficher("Tapez q pour fuir ");
                   choix=p.getClient().choixclient();
                }
                else 
                {
                    String aj =p.getNom()+" attaque "+m.getNomMonstre();
                    p.getClient().afficher(aj);
                    m.retirerVieMonstre(1);
                    String vm = "Nombre de vie du monstre  "+m.getVieMonstre();
                    p.getClient().afficher(vm);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ImplInterfaceCombattre.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     p.getClient().afficher("Tapez q pour fuir ");
                     choix=p.getClient().choixclient();
                }
                
            }while(p.getVieJoueur()!=0 && m.getVieMonstre()!=0 && !choix.equals("q"));
             m.setEtatMonstre(false);
             mettreAjourEtatPersonnage(p.getNom(), false);
            if(m.getVieMonstre()==0)
            {
             
                p.getClient().afficher("Monstre is dead: "+m.getNomMonstre());
                p.getClient().afficher("Votre nombre de vie avant "+p.getVieJoueur());
                if(!choix.equals("q"))
                {
                p.ajouterVieJoueur(1);
                p.getClient().afficher("Votre nombre de vie apres "+p.getVieJoueur());
                    
                }
                else 
                    p.getClient().afficher("Votre nombre de vie apres "+p.getVieJoueur());
                
                
            }
            else if(p.getVieJoueur()==0)
            {
                
                p.getClient().afficher("Clien is dead "+p.getNom());
                p.getClient().afficher("Votre nombre de vie avant "+m.getVieMonstre());
                m.setVieMonstre(1);
                p.getClient().afficher("La vie du monstre apres "+m.getVieMonstre());
                
            }
            
        }
      
    }
      
    public void combattreJoueur(String pseudo,InterfaceClient client) throws RemoteException {
        Random r=new Random();
        String choix=new String();
          Personnage p1=new Personnage();
        p1=chercherPersonnage(client.getNom());
        Personnage p2 = new Personnage();
        p2=chercherPersonnage(pseudo);
      
        
        
        if(p2.getTest()!=1)
        {
            p1.getClient().afficher("Debut contre "+p2.getNom());
            p2.getClient().afficher("Debut contre "+p1.getNom());
             mettreAjourEtatPersonnage(p1.getNom(),true);
             mettreAjourEtatPersonnage(p2.getNom(),true);
            do
            {
                int d = r.nextInt(4);
                
                if(d>2)
                {
                    String am = p2.getNom()+" attaque "+p1.getNom();
                    p1.getClient().afficher(am);
                    p2.attaquerpersonnage(p1);
                    p1.getClient().afficher("Votre nombre de vie est "+p1.getVieJoueur());
                    p1.getClient().afficher("Tapez q pour fuir");
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
             mettreAjourEtatPersonnage(p1.getNom(),true);
             mettreAjourEtatPersonnage(p2.getNom(),true);
             
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
      
    }*/
    
    
    
  
  


    
    }
