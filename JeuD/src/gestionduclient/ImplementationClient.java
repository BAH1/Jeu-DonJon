/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduclient;

import gestionDeCombat.InterfaceCombat;
import gestionduLabyrinthe.InterfaceduLabyrinthe;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 *
 * @author elhadj
 */
public class ImplementationClient extends UnicastRemoteObject implements InterfaceClient,Serializable{
    
    private String nom;
    private int numeropiece;
    private Scanner sc;
    private int vie;
    private int etat=0;
    public ImplementationClient()throws RemoteException
    {
        super();
      sc=new Scanner(System.in);
      vie=10;
    }
    
    public void saisirPseudo()
    {
       do
       {
        System.out.println("Entrer votre pseudo");
        nom=sc.nextLine();
        if(nom.length()<3)
               System.out.println("Votre pseudo est trop court");
       }while(nom.length()<3);   
       }
        

    public String getNom() throws RemoteException{
        return nom;
    }

    public void setNom(String nom)throws RemoteException{
        this.nom = nom;
    }

    public int getNumeropiece()throws RemoteException{
        return numeropiece;
    }
  
    public void setNumeropiece(int numeropiece)throws RemoteException{
        this.numeropiece = numeropiece;
    }
    public void afficherPersonneDansLapiece(String personne) throws RemoteException
    {
        String s=new String();
        String t[];
       
         t=personne.split(" ");
            for(int i=0;i<t.length;i++)
            {
                
                if(!t[i].equals(getNom()))
                    s+=" "+t[i];
            }
            System.out.println(s);
    }
    @Override
    public void afficherEtatConnexion(int numeroPiece) throws RemoteException {
         //To change body of generated methods, choose Tools | Templates.
         if(numeroPiece!=0)
         {
          System.out.println("Bienvenue");
           System.out.println("Vous êtes dans la pièce "+numeroPiece);   
         }
         else 
             System.out.println("Votre pseudo est trop court");
         
    }
    public void MenuAttaque()
    {
        System.out.println("Entrer le nom du joueur que vous désirez attaquer");
         
    }
    public void Menu() 
    {
        System.out.println("1.pour se deplacer ou Rester dans la meme piece");
         System.out.println("2.pour chatter ");
         System.out.println("3.pour quitter ");
        
        
    }
    public String choixclient() throws RemoteException
    {
       return sc.nextLine();
    }

    @Override
    public void afficher(String s) throws RemoteException {
       //To change body of generated methods, choose Tools | Templates.
        System.out.println(s);
    }

    @Override
    public String envoyerMessage() throws RemoteException {
        //To change body of generated methods, choose Tools | Templates.
        Scanner sc=new Scanner(System.in);
        String monMessage=new String();
        String test=new String();
        System.out.println("1: Entrer votre message");
        System.out.println("2:Entrer q pour quittez");
        
        
         monMessage+="["+nom+"] ";
           test=sc.nextLine();
           if(!test.equals("q"))
          return      monMessage+=test;
           else 
           return test;
   
        
    }

    @Override
    public void recupererMessage(String message) throws RemoteException {
       //To change body of generated methods, choose Tools | Templates.
       System.out.println(message);
       
    }

    

    @Override
    public void afficherNotification() throws RemoteException {
     //To change body of generated methods, choose Tools | Templates.
        System.out.println("D'autre joueurs sont présents dans la pièce");
    }
    
    public int getVie() throws RemoteException{
        return vie;
    }

    public void setVie(int vie) throws RemoteException{
        this.vie = vie;
    }

    public int getEtat() throws RemoteException{
        return etat;
    }

    public void setEtat(int etat) throws RemoteException{
        this.etat = etat;
    }
  
    public void combattre(InterfaceCombat serverCombat,InterfaceduLabyrinthe stub) throws RemoteException
    {
        String choixfuirM=new String();
        String choixfuir=new String();
        String pseudo=new String();
        
                serverCombat.combattreLemonstre(this);
                do
               {
                   
                   
                    choixfuir=this.choixclient();
                      serverCombat.fuirlecombat(choixfuir, this);
                       
                 
               }while(!choixfuir.equals("q") && serverCombat.etatCombat(this));
               if(choixfuir.equals("q"))
                 serverCombat.reinitialiserVieDuMonstre(this);
       
                  if(stub.personnageViVant(this)>0 && stub.VerificationEtat(this)!=0)
                 {
                  afficher("Personne dans la pièce ");
                  afficherPersonneDansLapiece(stub.afficherPersonnedanspiece(this));
                  
                  if(serverCombat.verifierEtatJoueur(this)>0)
                  {
                      
                   do
                  {
                       this.afficher("Vous êtes attaqué appuyer q pour fuir");
                      choixfuirM=choixclient();
                      serverCombat.fuirCombatJoueurAttaquer(choixfuirM, this);
                      
                  }while(!choixfuirM.equals("q") && serverCombat.etatcombatDuJoueur(this));
              
                  }
                 
                   if(!choixfuirM.equals("q"))
                   {
                   afficher("Tapez le nom de celui que vous voulez attaquez");
                   afficher("Appuyer Entrer pour passer");
                   afficher("appuyer d pour pouvoir fuir si vous êtes attaqué");
                    pseudo=choixclient();
                  if(stub.afficherPersonnedanspiece(this).contains(pseudo) && pseudo.length()>3)
                  {
                      if(serverCombat.verifierEtatJoueur(this)>0)
                      {
                       do
                         {
                         afficher("Vous êtes attaqué appuyer q pour fuir");
                         choixfuirM=choixclient();
                         serverCombat.fuirCombatJoueurAttaquer(choixfuirM, this);
                      
                         }while(!choixfuirM.equals("q") && serverCombat.etatcombatDuJoueur(this));
                  
                      }
                      else
                      {
                        serverCombat.combattreJoueur(this,pseudo);
                  
                        do
                       {
                  
                        choixfuirM=this.choixclient();
                        serverCombat.fuirCombatEntreJoueur(choixfuirM, this, pseudo);
                
                    
                              
                        }while(!choixfuirM.equals("q") && serverCombat.etatcombatDuJoueur(this));
                 
                      }
                             
                  }
                  else if(pseudo.equals("d"))
                  {
                     if(serverCombat.verifierEtatJoueur(this)>0)
                      {
                       do
                         {
                         this.afficher("Vous êtes attaqué appuyer q pour fuir");
                         choixfuirM=this.choixclient();
                         serverCombat.fuirCombatJoueurAttaquer(choixfuirM, this);
                      
                         }while(!choixfuirM.equals("q") && serverCombat.etatcombatDuJoueur(this));
                  
                      }
                      
                  }
                    
                 }
               }
    }
    public void deconnect() throws RemoteException{
        
        System.exit(0);
    
    }
}
