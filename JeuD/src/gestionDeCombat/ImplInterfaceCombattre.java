/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDeCombat;

import com.mysql.jdbc.TimeUtil;
import gestionPersonnage.ListePersonnage;
import gestionPersonnage.Personnage;
import gestiondelabaseDeDonnee.Registre;
import gestionduLabyrinthe.ImplementationDuLabyrinthe;
import gestionduLabyrinthe.Piece;
import gestionduclient.InterfaceClient;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    private Registre base;
      public ImplInterfaceCombattre(Registre base) throws RemoteException {
		super();
                lesMonstre=new ArrayList<>();
              
                listeCombat=new HashMap<>();
                listeCombatPersonne=new HashMap<>();
                listeC=new ArrayList<>();
                initialisation();
                this.base=base;
               
               
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

      
      public void fuirCombatJoueurAttaquer(String choix,InterfaceClient client) throws RemoteException
      {
          String t[];
          Personnage p1=chercherPersonnage(client.getNom());
          if(choix.equals("q") && p1.getVieJoueur()!=0)
          {
              
              
              Iterator<Thread> iterator=listeCombatPersonne.get(p1.getNumeropiece()).iterator();
              while(iterator.hasNext())
          {
              Thread thread=iterator.next();
               if(thread.getName().contains(p1.getNom()))
              {
                  t=thread.getName().split(" ");
                  for(int i=0;i<t.length;i++)
                  {
                      if(!client.getNom().equals(t[i]))
                      {
                          Personnage p2=chercherPersonnage(t[i]);
                          p2.getClient().afficher("Votre nombre de vie est :"+p2.getVieJoueur());
                          p2.setNbreCombat(p2.getNbreCombat()-1);
                          if(p2.getNbreCombat()==0)
                         {
                   
                          p2.setVie(p2.getVieJoueur()+1);
                          p2.getClient().afficher("Votre Nombre de vie après   "+p2.getVieJoueur());
                          base.mettreAjourvieJoueur(p2.getNom(), p2.getVieJoueur());
                          }
                      }
                  }
                  
                  thread.interrupt();
                  p1.setNbreCombat(p1.getNbreCombat()-1);
                   iterator.remove(); 
                  
              }
          }
              p1.getClient().afficher("Votre nombre de vie est :"+p1.getVieJoueur());
              base.mettreAjourvieJoueur(p1.getNom(), p1.getVieJoueur());
          
              /*
              for(Thread thread:listeCombatPersonne.get(p1.getNumeropiece()))
             {
              if(thread.getName().contains(p1.getNom()))
              {
                  t=thread.getName().split(" ");
                  for(int i=0;i<t.length;i++)
                  {
                      if(!client.getNom().equals(t[i]))
                      {
                          Personnage p2=chercherPersonnage(t[i]);
                          p2.getClient().afficher("Votre nombre de vie est :"+p2.getVieJoueur());
                          p2.setNbreCombat(p2.getNbreCombat()-1);
                          if(p2.getNbreCombat()==0)
                         {
                   
                          p2.setVie(p2.getVieJoueur()+1);
                          p2.getClient().afficher("Votre Nombre de vie après   "+p2.getVieJoueur());
                          base.mettreAjourvieJoueur(p2.getNom(), p2.getVieJoueur());
                          }
                      }
                  }
                  
                  thread.interrupt();
                  p1.setNbreCombat(p1.getNbreCombat()-1);
                  
                  
              }
             }*/
         }
      }
      public void fuirCombatEntreJoueur(String choix,InterfaceClient client,String pseudo) throws RemoteException
      {
          Personnage p1=chercherPersonnage(client.getNom());
          if(choix.equals("q") && p1.getVieJoueur()!=0)
          {
              
              Personnage p2=chercherPersonnage(pseudo);
             Iterator<Thread> iterator=listeCombatPersonne.get(p1.getNumeropiece()).iterator();
            
          while(iterator.hasNext())
          {
              Thread thread=iterator.next();
           
               if(thread.getName().contains(p1.getNom()))
               {
                   
                    thread.interrupt();
                    p1.setNbreCombat(p1.getNbreCombat()-1);
                    p2.setNbreCombat(p2.getNbreCombat()-1);
         
                   iterator.remove();
                   
               }
                   
          }
      
              /*
              for(Thread thread:listeCombatPersonne.get(p1.getNumeropiece()))
             {
              if(thread.getName().contains(p1.getNom()))
              {
                  thread.interrupt();
                  p1.setNbreCombat(p1.getNbreCombat()-1);
                  p2.setNbreCombat(p2.getNbreCombat()-1);
              }
             }*/
              if(p2.getNbreCombat()==0)
              {
                 p1.getClient().afficher("Fuite de "+p1.getNom());
                 p1.getClient().afficher("Nombre de vie avant de "+p1.getNom()+" "+p1.getVieJoueur());
                 p2.getClient().afficher("Nombre de vie avant de "+p2.getNom()+" "+p2.getVieJoueur());
                 p2.setVie(p2.getVieJoueur()+1);
                 p1.getClient().afficher("Nombre de vie après de "+p1.getNom()+" "+p1.getVieJoueur());
                 p2.getClient().afficher("Nombre de vie après de "+p2.getNom()+" "+p2.getVieJoueur());
                 base.mettreAjourvieJoueur(p1.getNom(), p1.getVieJoueur());  
                 base.mettreAjourvieJoueur(p2.getNom(), p2.getVieJoueur());
              }
              else 
              {
                 p1.getClient().afficher("Fuite de "+p1.getNom());
                 p1.getClient().afficher("Nombre de vie avant de "+p1.getNom()+" "+p1.getVieJoueur());
                 p1.getClient().afficher("Nombre de vie avant de "+p2.getNom()+" "+p2.getVieJoueur());
                 p1.getClient().afficher("Malheureusement pour toi vous etiez plusieurs sur lui ");
                 base.mettreAjourvieJoueur(p1.getNom(), p1.getVieJoueur());
              }
              
                  
          }
      }
    
      
      public void fuirlecombat(String choix,InterfaceClient client) throws RemoteException
      {
          Monstre m;
          int i=0;
          Personnage p=chercherPersonnage(client.getNom());
 
          if(choix.equals("q") && p.getVieJoueur()!=0)
          {
                               m=lesMonstre.get(p.getNumeropiece());
             Iterator<Thread> iterator=listeCombat.get(p.getNumeropiece()).iterator();
          while(iterator.hasNext())
          {
              Thread combat=iterator.next();
               if(combat.getName().equals(p.getNom()))
               {
                   System.err.println("nb av"+m.getNbreAdversaire());
                   combat.interrupt();
                   m.setNbreAdversaire(m.getNbreAdversaire()-1);
                   iterator.remove();
                   
               }
                   
          }
      
                   System.err.println("M"+m.getNomMonstre()+" "+m.getNbreAdversaire());
         
             if(m.getNbreAdversaire()==0)
             {
              p.getClient().afficher("Fuite de "+p.getNom());
              p.getClient().afficher("Nombre de vie du monstre avant "+m.getVieMonstre());
              m.setVieMonstre(m.getVieMonstre()+1);
              p.getClient().afficher("Nombre de vie du monstre Après "+m.getVieMonstre());
              base.mettreAjourvieJoueur(p.getNom(), p.getVieJoueur());
       
             }
             else 
             {
                  p.getClient().afficher("Fuite de "+p.getNom());
                  p.getClient().afficher("Nombre de vie du monstre avant "+m.getVieMonstre());
                  p.getClient().afficher("Malheureusement pour toi et dire que vous étiez 2 contre lui ");
                  base.mettreAjourvieJoueur(p.getNom(), p.getVieJoueur());
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
           
          CombatJoueur co=new CombatJoueur(pseudo, client, liste,this.base);
          Thread combJoueur=new Thread(co);
          combJoueur.start();
          
          nomThread=client.getNom()+" "+pseudo;
          combJoueur.setName(nomThread);
           listeCombatPersonne.get(p.getNumeropiece()).add(combJoueur);
           
      }
      public void combattreLemonstre(InterfaceClient client) throws RemoteException
      {
          Personnage p=chercherPersonnage(client.getNom());
          Monstre m=lesMonstre.get(p.getNumeropiece());
         
          combatMonstre=new CombatM(liste, lesMonstre,client,this.base);
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
     
    
    
  
  


    
    }
