/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduclient;

import gestionDeCombat.InterfaceCombattre;
import gestionDeCombat.Monstre;
import gestionPersonnage.Personnage;
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
                
            }
            else  if(Integer.parseInt(choix)==2)
            {
               ServeurChat   serveur=(ServeurChat)Naming.lookup("rmi://localhost:1099/RMIT");
                                       serveur.enregistrerClient(client,client.getNumeropiece());
                                      do
                                       {
                                           if(!msg.equals("q"))
                                           {
                                           msg=client.envoyerMessage();
                                           serveur.recevoirMessage(msg,client);
                                           serveur.envoyerMessageAtous(client.getNumeropiece());
                                              
                                           }
                                         
                                           
                                       }while(!msg.equals("q"));
                                      

            }
             else  if(Integer.parseInt(choix)==3){ 
             InterfaceCombattre serveur = (InterfaceCombattre)Naming.lookup("rmi://localhost:1097/combat");
                        Monstre M = new Monstre("gaagaaaaaaaa");
                        Personnage p1 = new Personnage(client.getNom(), 1);
                        Personnage p2 = new Personnage(client.getNom(), 1);
                        serveur.combatMJ(M, p1);
             }
           
    
            }while(Integer.parseInt(choix)!=4);
            System.exit(0);
    }
}
