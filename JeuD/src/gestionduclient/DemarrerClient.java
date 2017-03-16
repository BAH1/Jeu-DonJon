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
          ImplementationClient client=new ImplementationClient();
          
            client.saisirPseudo();
            stub.connexion(client);
              do
            {
                client.Menu();
                choix=client.choixclient();
          
            
            if(Integer.parseInt(choix)==1)
            {
               client.afficher(stub.InformationSurlaDestination(client));
               choixrester=client.choixclient();
               if(!choixrester.equals("r"))
               stub.deplacerJoueur(choixrester,client); 
             serverCombat.recupererListeClient(stub.recupererListe());
              /*  serverCombat.combattreLemonstre(client);
             
               do
               {
                   
                    choixfuir=client.choixclient();
                      serverCombat.fuirlecombat(choixfuir, client);
                       
                 
               }while(!choixfuir.equals("q") && serverCombat.etatCombat(client));
              
                 serverCombat.reinitialiserVieDuMonstre(client);
             */
                  if(stub.recupererListeParNumero(client).size()>1)
                 {
                  client.afficher("Personne dans la pièce ");
                  client.afficher(stub.afficherPersonnedanspiece(client));
                  if(serverCombat.verifierEtatJoueur(client)>1)
                  {
                      
                   do
                  {
                       client.afficher("Vous êtes attaqué appuyer q pour fuir");
                      choixfuir=client.choixclient();
                      serverCombat.fuirCombatEntreJoueur(choixfuir, client, pseudo);
                      
                  }while(!choixfuir.equals("q") && serverCombat.etatcombatDuJoueur(client));
              
                  }
                 
                      
                  client.afficher("Tapez le nom de celui que vous voulez attaquez ou q pour passer ");
                  pseudo=client.choixclient();
                  if(stub.afficherPersonnedanspiece(client).contains(pseudo))
                  {
                      if(serverCombat.verifierEtatJoueur(client)>1)
                      {
                          
                      }
                     serverCombat.combattreJoueur(client,pseudo);
                  
                  do
                  {
                    
                      choixfuir=client.choixclient();
                      serverCombat.fuirCombatEntreJoueur(choixfuir, client, pseudo);
                      
                  }while(!choixfuir.equals("q") && serverCombat.etatcombatDuJoueur(client));
                    
                 }
               }
             
                
           }
            else  if(Integer.parseInt(choix)==2)
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
            
             
                       
                        
             
           
    
            }while(Integer.parseInt(choix)!=3);
       
            System.exit(0);
    }
}
