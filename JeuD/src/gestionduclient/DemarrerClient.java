/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduclient;

import gestionDeCombat.InterfaceCombat;
import gestionPersonnage.Personnage;
import gestionduLabyrinthe.InterfaceduLabyrinthe;
import gestionduchat.ServeurChat;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author elhadj
 */
public class DemarrerClient {
    
       public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException, InterruptedException {
       
           String choix=new String();
           String choixrester=new String();
           String msg = new String();
           String choixfuir=new String();
           String pseudo=new String();
         
         InterfaceduLabyrinthe  stub  =(InterfaceduLabyrinthe) Naming.lookup("rmi://localhost:1099/by");
         InterfaceCombat serverCombat = (InterfaceCombat)Naming.lookup("rmi://localhost:1097/combat");
         ServeurChat   serveur=(ServeurChat)Naming.lookup("rmi://localhost:1099/RMIT");
         InterfaceCombat serverCombatone=(InterfaceCombat)Naming.lookup("rmi://localhost:1099/combatserverone");
         InterfaceCombat serverCombatTwo=(InterfaceCombat)Naming.lookup("rmi://localhost:1099/combatservertwo");
          ImplementationClient client=new ImplementationClient();
          
            client.saisirPseudo();
            stub.connexion(client);
              do
            {
                client.Menu();
                choix=client.choixclient();
          
            
            if(choix.equals("1"))
            {
               do
               {
               client.afficher(stub.InformationSurlaDestination(client));
               choixrester=client.choixclient();
                
               }while(!choixrester.equals("R") && !choixrester.equals("N")&& !choixrester.equals("S") && !choixrester.equals("O")&& 
                       !choixrester.equals("E"));
                  if(!choixrester.equals("R"))
               stub.deplacerJoueur(choixrester,client);
              // serverCombat.recupererListeClient(stub.recupererListe());
               serverCombatone.recupererListeClient(stub.recupererListe());
               serverCombatTwo.recupererListeClient(stub.recupererListe());
                System.err.println("diallo");
              /*  serverCombat.combattreLemonstre(client);
             
               do
               {
                   
                    choixfuir=client.choixclient();
                      serverCombat.fuirlecombat(choixfuir, client);
                       
                 
               }while(!choixfuir.equals("q") && serverCombat.etatCombat(client));
              
                 serverCombat.reinitialiserVieDuMonstre(client);
               
             */
              if(stub.recupererNumeroPiece(client)>4)
              {
              client.combattre(serverCombatone, stub);    
              }
              else
              client.combattre(serverCombatTwo, stub);      
              
                 /* if(stub.personnageViVant(client)>0 && stub.VerificationEtat(client)!=0)
                 {
                  client.afficher("Personne dans la pièce ");
                  client.afficherPersonneDansLapiece(stub.afficherPersonnedanspiece(client));
                  if(serverCombat.verifierEtatJoueur(client)>0)
                  {
                      
                   do
                  {
                       client.afficher("Vous êtes attaqué appuyer q pour fuir");
                      choixfuir=client.choixclient();
                      serverCombat.fuirCombatJoueurAttaquer(choixfuir, client);
                      
                  }while(!choixfuir.equals("q") && serverCombat.etatcombatDuJoueur(client));
              
                  }
                 
                   if(!choixfuir.equals("q"))
                   {
                   client.afficher("Tapez le nom de celui que vous voulez attaquez");
                   client.afficher("Appuyer Entrer pour passer");
                   client.afficher("appuyer d pour pouvoir fuir si vous êtes attaqué");
                    pseudo=client.choixclient();
                  if(stub.afficherPersonnedanspiece(client).contains(pseudo) && pseudo.length()>3)
                  {
                      if(serverCombat.verifierEtatJoueur(client)>0)
                      {
                       do
                         {
                         client.afficher("Vous êtes attaqué appuyer q pour fuir");
                         choixfuir=client.choixclient();
                         serverCombat.fuirCombatJoueurAttaquer(choixfuir, client);
                      
                         }while(!choixfuir.equals("q") && serverCombat.etatcombatDuJoueur(client));
                  
                      }
                      else
                      {
                        serverCombat.combattreJoueur(client,pseudo);
                  
                        do
                       {
                   
                        choixfuir=client.choixclient();
                        serverCombat.fuirCombatEntreJoueur(choixfuir, client, pseudo);
                
                    
                              
                        }while(!choixfuir.equals("q") && serverCombat.etatcombatDuJoueur(client));
                 
                      }
                             
                  }
                  else if(pseudo.equals("d"))
                  {
                     if(serverCombat.verifierEtatJoueur(client)>0)
                      {
                       do
                         {
                         client.afficher("Vous êtes attaqué appuyer q pour fuir");
                         choixfuir=client.choixclient();
                         serverCombat.fuirCombatJoueurAttaquer(choixfuir, client);
                      
                         }while(!choixfuir.equals("q") && serverCombat.etatcombatDuJoueur(client));
                  
                      }
                      
                  }
                    
                 }
               }*/
             
                
           }
            else  if(choix.equals("2"))
            {
                                     
                                
                                  serveur.recupererListeClients(stub.recupererListe());
                                   
                                      do
                                       {
                                           if(!msg.equals("q"))
                                           {
                                           msg=client.envoyerMessage();
                                         serveur.recupererMessage(msg, client,stub.recupererNumeroPiece(client));
                                           serveur.broadcasterMessage(stub.recupererNumeroPiece(client));
                                           }
                                         
                                           
                                       }while(!msg.equals("q"));
                                      

            }
            
             
                       
                        
             
           
    
            }while(!choix.equals("3"));
       
            System.exit(0);
    }
}
