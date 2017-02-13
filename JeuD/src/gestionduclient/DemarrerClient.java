/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduclient;

import gestionDeCombat.InterfaceCombat;
import gestionPersonnage.ListePersonnage;
import gestionduLabyrinthe.InterfaceduLabyrinthe;
import gestionduchat.ServeurChat;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author elhadj
 */
public class DemarrerClient {
    
       public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
       
           String choix=new String();
           String msg = new String();
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
               stub.deplacerJoueur(client.choixclient(),client); 
              serverCombat.recupererListeClient(stub.recupererListe(client), stub.recupererNumeroPiece(client));
              serverCombat.combattreMonstre(client);
              if(serverCombat.nombreDeJoueur()>1)
              {
                  client.afficher(serverCombat.listePersonnage());
                  client.MenuAttaque();
                  serverCombat.combattreJoueur(client.choixclient(), client);
              }
                
           }
            else  if(Integer.parseInt(choix)==2)
            {
                                     
                                 serveur.recupererListeClient(stub.recupererListe(client),stub.recupererNumeroPiece(client));
                                      do
                                       {
                                           if(!msg.equals("q"))
                                           {
                                           msg=client.envoyerMessage();
                                         serveur.recupererMessage(msg, client);
                                           serveur.broadcasterMessage();
                                           }
                                         
                                           
                                       }while(!msg.equals("q"));
                                      

            }
            
             
                       
                        
             
           
    
            }while(Integer.parseInt(choix)!=3);
       
            System.exit(0);
    }
}
