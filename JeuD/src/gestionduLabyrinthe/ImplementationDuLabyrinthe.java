/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduLabyrinthe;

import gestionPersonnage.Personnage;
import gestiondelabaseDeDonnee.Registre;
import gestionduchat.ServerChatImpl;
import gestionduchat.ServeurChat;
import gestionduclient.ImplementationClient;
import gestionduclient.InterfaceClient;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elhadj
 */
public class ImplementationDuLabyrinthe extends UnicastRemoteObject implements InterfaceduLabyrinthe{
    private String nom;
    private Registre registre;
    private ArrayList<Piece>pieceduLabyrinthe;
    private Personnage perso; 
    private ArrayList<InterfaceClient>clients;
    
   
    
    public ImplementationDuLabyrinthe(String nom)throws RemoteException{
    
        this.nom=nom; //si on souhaite donner un nom à son labyrinthe
        pieceduLabyrinthe=new ArrayList<>();
        registre=new Registre();
        perso=new Personnage();//
        clients=new ArrayList<>();
  }

    
    /**
     * 
     * 
     * @param client
     * @throws RemoteException
     * 
     * Prend une interface du client en argument et permet d'ajouter ce client dans la base donnée 
     */
    public void connexion(InterfaceClient client) throws RemoteException {
		// TODO Auto-generated method stub
		String sortie=new String();
		 String requeteVerif;
                 
        requeteVerif="SELECT pseudo FROM \"JOUEUR\" WHERE pseudo='"+client.getNom()+"'";
     
         
		
                   sortie=registre.executerRequete(requeteVerif);
           if(sortie.equals(client.getNom())&& client.getNom().length()>3)
           {
        	perso.setNom(client.getNom());
                requeteVerif="select numeropi from \"SETROUVER\" WHERE pseudopi='"+client.getNom()+"'";
                System.out.println("la requete "+requeteVerif);
                perso.setNumeropiece(Integer.parseInt(registre.executerRequete(requeteVerif)));
                client.afficherEtatConnexion(perso.getNumeropiece());
                client.setNumeropiece(perso.getNumeropiece());
                ajouterClient(client);
                notification(client);
        
                   
           
                
           }
           else
           {
               /**
                * Comme c'est la première connexion on l'ajoute dans la pièce 1
                */
        	   if(client.getNom().length()>3)
        	   {
        	   requeteVerif="INSERT INTO \"JOUEUR\" VALUES('"+client.getNom()+"')";
        	 
        	   registre.insertion(requeteVerif);
                   requeteVerif="INSERT INTO \"SETROUVER\" VALUES ('"+client.getNom()+"','1')";
                   registre.insertion(requeteVerif);
        	   perso.setNom(client.getNom());
                    client.setNumeropiece(1);
                   
                    client.afficherEtatConnexion(client.getNumeropiece());
                       ajouterClient(client);
                       notification(client);
                     
        
        	   }
                   else
                   
                   client.afficherEtatConnexion(0);
        	   
     
           }
		

   
	}
    public void  notification(InterfaceClient client) throws RemoteException
    {
        String requeteVerif;
        requeteVerif="select count(*) as nb from \"SETROUVER\" WHERE numeropi='"+client.getNumeropiece()+"'";
                        if(Integer.parseInt(registre.executerRequete(requeteVerif))>1)
                        {
                              for(InterfaceClient c:clients)
                              {
                                
                                   if(c.getNumeropiece()==client.getNumeropiece())
                                   {
                                        c.afficherNotification();
                                   }
                                  
                              }
                             System.out.println("le nombre de client est "+clients.size());
                              
                             
                        }
                                    
        
    }
    public String InformationSurlaDestination(InterfaceClient client) throws RemoteException
    {
        String requete="select portepi,numpidest from \"TRAVERSER\" where numerop='"+client.getNumeropiece()+"'";
        System.out.println("la requete est"+requete);
        requete=registre.MenuPourSeDeplacer(requete);
        requete+="\n TAPEZ N POUR NORD \n TAPEZ S POUR SUD \n TAPEZ O pour OUEST \n TAPEZ E pour EST";
        return requete;
    }
    
    public void deplacerJoueur(String choix,InterfaceClient client) throws RemoteException
    {
        String res;
        if(choix.equals("N"))
            choix+="ORD";
        else if(choix.equals("S"))
            choix+="UD";
        else if(choix.equals("E"))
            choix+="ST";
        else 
            choix+="UEST";
        String requete;
           requete="select numpidest from \"TRAVERSER\" where portepi='"+choix+"' and numerop='"+perso.getNumeropiece()+"'";
          
           res=registre.executerRequete(requete);
           requete="UPDATE \"SETROUVER\" SET numeropi='"+res+"' where pseudopi='"+client.getNom()+"'";
            
        
            registre.insertion(requete);
            retirerClient(client);
            
            perso.setNumeropiece(Integer.parseInt(res));
           client.setNumeropiece(Integer.parseInt(res));
           client.setNom(perso.getNom());
           ajouterClient(client);
           client.afficherEtatConnexion(client.getNumeropiece());
           notification(client);
    }
    
    public int chercherPersonnage(String nom)
    {
        int numerop,i;
        numerop=0;
        i=0;
        while(i<pieceduLabyrinthe.size() && numerop==0)
        {
            
            numerop=pieceduLabyrinthe.get(i).trouverUnPersonne(nom);
            
            i++;
        }
            
        return numerop;
    }
   public void CreationDuLabyrinthe() throws RemoteException
   {
       for(int i=1;i<=9;i++)
        {
            Piece p=new Piece(i);
            p.creerUnePiece(i);
           pieceduLabyrinthe.add(p);
        }
       
      registre.connexionBD();
   }
   /**
    * 
    * @param client
    * @throws RemoteException
    * On ajoute les clients dans une table.
    */
   public void ajouterClient(InterfaceClient client)throws RemoteException
    {
        clients.add(client);
                
     }
   public void retirerClient(InterfaceClient client) throws RemoteException
   {
      clients.remove(client);
    
   }
   

  
}
