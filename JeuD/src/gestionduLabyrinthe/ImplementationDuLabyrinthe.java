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
import java.util.HashMap;

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
    private ArrayList<Piece>pieces;
  
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
       pieces=new ArrayList();
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
                 int vie;
                 
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
                 requeteVerif="select viejoueur from \"JOUEUR\" where pseudo='"+perso.getNom()+"'";
                 vie=Integer.parseInt(registre.executerRequete(requeteVerif));
                System.err.println("la vie du joueur"+vie);
                if(vie==0)
                {
                    perso.setVie(10);
                    registre.mettreAjourvieJoueur(perso.getNom(), 10);
                }
                    
                else
                {
                    perso.setVie(vie);
                    registre.mettreAjourvieJoueur(perso.getNom(), vie);
                }
                    
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
                    registre.mettreAjourvieJoueur(client.getNom(), 10);
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
                  try {
                      p.getClient().afficherNotification();
                  } catch (Exception e) {
                  }
                  
              }
          }
                             
                        
                                    
        
    }
   public String InformationSurlaDestination(InterfaceClient client) throws RemoteException
    {
        
        // A chaque deplacement on l'informe de sa position et les portes pour se deplacer
    
        
     String requete="select portepi,numpidest from \"TRAVERSER\" where numerop='"
             +listeclient.chercherPersonnage(client.getNom())+"'";
       
       
       
        requete=registre.MenuPourSeDeplacer(requete);
        requete+="\n TAPEZ N POUR NORD \n TAPEZ S POUR SUD \n TAPEZ O pour OUEST \n TAPEZ E pour EST \n TAPEZ R POUR RESTER";
       
   
        return requete;
    }
    
    public int deplacerJoueur(String choix,InterfaceClient client) throws RemoteException
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
        int j=0;
        Piece p = pieces.get(recupererNumeroPiece(client)-1);
             
               for(String porte:p.getNomPorte())
               {
                   if(porte.equals(choix)){ 
                       j=1;
                   }
                           
               }
        
    if (j==1)
    {
        perso=listeclient.getPseudoPersonnage(client.getNom());
           requete="select numpidest from \"TRAVERSER\" where portepi='"+choix+"' and numerop='"
                          +perso.getNumeropiece()+"'";
           
          
           res=registre.executerRequete(requete);
           requete="UPDATE \"SETROUVER\" SET numeropi='"+res+"' where pseudopi='"
                   +perso.getNom()+"'";
                       
            listeclient.deplacerClient(perso.getNumeropiece(), client, Integer.parseInt(res));
              
        
            registre.insertion(requete);
            
            perso=listeclient.getPseudoPersonnage(client.getNom());
           
            if(registre.recupererViePersonnage(perso.getNom())==0)
            {
                registre.mettreAjourvieJoueur(perso.getNom(), 10);
            }
            client.afficherEtatConnexion(perso.getNumeropiece());
           notification(perso.getNumeropiece());
    }
        
        return j;
    }
    
   
    
   public void CreationDuLabyrinthe() throws RemoteException
   {
    
      registre.connexionBD();
      
       for (int i = 1; i < 10; i++) {
           
           pieces.add(new Piece(i));

           
       }
   }
    // recupère le numero de la pièce pour pouvoir l'envoyer au serveur de chat 
    @Override
    public int recupererNumeroPiece(InterfaceClient client) throws RemoteException {
               
        return listeclient.chercherPersonnage(client.getNom());
    }
    // on recupère la liste des clients dans un salon pour l'envoyer au serveur de chat 
    

    @Override
    public ArrayList<Personnage>  recupererListe() throws RemoteException {
       //To change body of generated methods, choose Tools | Templates.
       ArrayList<Personnage>res=new ArrayList<>();
       for(int i=1;i<10;i++)
       {
           for(Personnage p:listeclient.recupererListe(i))
           {
               res.add(p);
           }
       }
       return res;
       
    }
    
    public String afficherPersonnedanspiece(InterfaceClient client) throws RemoteException
    {
        String res=new String();
        for(Personnage p:listeclient.recupererListe(listeclient.chercherPersonnage(client.getNom())))
        {
            if(registre.recupererViePersonnage(p.getNom())!=0)  
            res+=" "+p.getNom();
        }
      
            return res;
    }
     public int VerificationEtat(InterfaceClient client) throws RemoteException
     {
         return registre.recupererViePersonnage(client.getNom());
     }
     public int personnageViVant(InterfaceClient client) throws RemoteException
     {
         int res=0;
         for(Personnage p:listeclient.recupererListe(listeclient.chercherPersonnage(client.getNom())))
        {
            if(registre.recupererViePersonnage(p.getNom())!=0 && !client.getNom().equals(p.getNom()))  
             res++;
        }
      
          return res;
     }
     public ArrayList<Personnage> recupererListeParNumero(InterfaceClient client) throws RemoteException {
    return   listeclient.recupererListe(listeclient.chercherPersonnage(client.getNom()));  
    }

    public Registre getRegistre() {
        return registre;
    }

    public void setRegistre(Registre registre) {
        this.registre = registre;
    }

   
  
  
}
