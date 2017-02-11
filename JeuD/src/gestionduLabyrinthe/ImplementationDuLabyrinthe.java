/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduLabyrinthe;

import gestionPersonnage.ListeClientParPiece;
import gestionPersonnage.ListePersonnage;
import gestionPersonnage.Personnage;
import gestiondelabaseDeDonnee.Registre;
import gestionduclient.InterfaceClient;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author elhadj
 */

public class ImplementationDuLabyrinthe extends UnicastRemoteObject implements InterfaceduLabyrinthe{
    /**
     * 
     */
    private String nom;
    private Registre registre;
    private Personnage perso; 
    private ListeClientParPiece listeclient;
    /**
     * 
     * @param nom
     * @throws RemoteException
     * @Registe pour communiquer avec la base donnée
     * @Personnage pour pouvoir recuperer un personnage
     * @listeclient pour stocker une liste de personnage dans une pièce 
     */
    
    public ImplementationDuLabyrinthe(String nom)throws RemoteException{
    
        this.nom=nom; //si on souhaite donner un nom à son labyrinthe
        registre=new Registre();
       listeclient=new ListeClientParPiece();
       // on initilialiste chaque pièce avec une liste
       listeclient.initialiser();
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
          // on verifie dans la base de donnée si le pseudo existe
           if(sortie.equals(client.getNom())&& client.getNom().length()>3)
           {
                perso=new Personnage();
                // on crée un nouveau personnage si le pseudo existe et on met à jour les différentes informations y compris sa position 
        	perso.setNom(client.getNom());
                perso.setClient(client);
                 requeteVerif="select numeropi from \"SETROUVER\" WHERE pseudopi='"+perso.getNom()+"'";
                perso.setNumeropiece(Integer.parseInt(registre.executerRequete(requeteVerif)));
                // on l'ajoute dans la pièce où il se trouvait à sa deconnexion
                listeclient.ajouterClientdansPiece(perso.getNumeropiece(), perso);
                client.afficherEtatConnexion(perso.getNumeropiece());
                // si des personnes sont présents dans la pièce une notification est envoyé à toutes ces personnes
                 notification(perso.getNumeropiece());
                   
           
                
           }
           else
           {
               /**
                * Comme c'est la première connexion on l'ajoute dans la pièce 1
                */
        	   if(client.getNom().length()>3)
        	   {
                    perso=new Personnage();
        	   requeteVerif="INSERT INTO \"JOUEUR\" VALUES('"+client.getNom()+"')";
        	 
        	   registre.insertion(requeteVerif);
                   requeteVerif="INSERT INTO \"SETROUVER\" VALUES ('"+client.getNom()+"','1')";
                   registre.insertion(requeteVerif);
        	   perso.setNom(client.getNom());
                   perso.setNumeropiece(1);
                    perso.setClient(client);
                    client.setNumeropiece(1);
                   listeclient.ajouterClientdansPiece(1, perso);
                    client.afficherEtatConnexion(perso.getNumeropiece());
                       notification(perso.getNumeropiece());
                     
        
        	   }
                   else
                   // si tout se passe mal on informe le client
                   client.afficherEtatConnexion(0);
        	   
     
           }
		

   
	}
    public void  notification(int numeroPiece) throws RemoteException
    {
       // si y' a plusieurs personnes dans une pièce ils sont informés
          if(listeclient.getTab().get(numeroPiece).getListePerso().size()>1)
          {
              for(Personnage p:listeclient.getTab().get(numeroPiece).getListePerso())
              {
                  p.getClient().afficherNotification();
              }
          }
                             
                        
                                    
        
    }
   public String InformationSurlaDestination(InterfaceClient client) throws RemoteException
    {
        
        // A chaque deplacement on l'informe de sa position et les portes pour se deplacer
    
        
     String requete="select portepi,numpidest from \"TRAVERSER\" where numerop='"
             +listeclient.chercherPersonnage(client.getNom())+"'";
       
       
       
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
        /* on recupère numero de la pièce de destination en fonction du choix
        puis on met à jour la base de donnée vers cette pièce 
        on le deplace son personne dans cette pièce dans la liste de personnage
        on envoie une notification si y' a plusieurs personnes dans 
        la pièce 
        
        
        */ 
        
        perso=listeclient.getPseudoPersonnage(client.getNom());
           requete="select numpidest from \"TRAVERSER\" where portepi='"+choix+"' and numerop='"
                          +perso.getNumeropiece()+"'";
          
           res=registre.executerRequete(requete);
           requete="UPDATE \"SETROUVER\" SET numeropi='"+res+"' where pseudopi='"
                   +perso.getNom()+"'";
                         
              listeclient.deplacerClient(perso.getNumeropiece(), client, Integer.parseInt(res));
              
        
            registre.insertion(requete);
            
            perso=listeclient.getPseudoPersonnage(client.getNom());
            client.afficherEtatConnexion(perso.getNumeropiece());
           notification(perso.getNumeropiece());
    }
    
   
    
   public void CreationDuLabyrinthe() throws RemoteException
   {
    
       
      registre.connexionBD();
   }
    // recupère le numero de la pièce pour pouvoir l'envoyer au serveur de chat 
    @Override
    public int recupererNumeroPiece(InterfaceClient client) throws RemoteException {
               
        return listeclient.chercherPersonnage(client.getNom());
    }
    // on recupère la liste des clients dans un salon pour l'envoyer au serveur de chat 
    @Override
    public ArrayList<Personnage> recupererListe(InterfaceClient client) throws RemoteException {
    return   listeclient.recupererListe(listeclient.chercherPersonnage(client.getNom()));  
    }

    
  
    
   
  
  
}
