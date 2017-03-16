/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPersonnage;

import gestionduclient.ImplementationClient;
import gestionduclient.InterfaceClient;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author elhadj
 */
public class ListeClientParPiece{
    
    private HashMap<Integer,ListePersonnage>tab;
    public ListeClientParPiece()
    {
        tab=new HashMap<>();
    }
    
    // on ajoute des liste vide dans notre table de liste
  public void initialiser()
  {
     
      for(int i=1;i<10;i++)
      {
       ListePersonnage l=new ListePersonnage();
          tab.put(i, l);
      }
     
  }
  /**
   *  recupère un personnage dans la table de liste de personnage
   */
 
  public Personnage getPseudoPersonnage(String nom)
  {
     int i;
     Personnage p=new Personnage();
      boolean trouve=false;
      i=1;
      while(i<10 && !trouve)
      {
        p=tab.get(i).chercherPersonnage(nom);
    if(p!=null)
        trouve=true;
        i++;
      }
      if(trouve)
          return p;
      else 
          return null;
      
  }
  
  public ArrayList<Personnage> recupererListe(int numeroPiece)
  {
     return  tab.get(numeroPiece).getListePerso();
  }
  /**
   * 
   * @param nom pseudo du personnage
   * @return  le numero de la pièce du personnage 
   */
  public int chercherPersonnage(String nom)
  {
      int i;
      boolean trouve=false;
      i=1;
      while(i<10 && !trouve)
      {
        trouve=tab.get(i).chercherPiecePersonnnage(nom);
    if(!trouve) 
        i++;
      }
      return i;
     
  }
  /**
   * 
   * @param numeroPiece pièce dans laquelle il faut ajouter le personnage
   * @param personnage le personnage à ajouter
   * @throws RemoteException 
   * si le personnage existait il est mis à jour après une nouvelle connexion pour ne 
   * pas avoir de duplication de client 
   */
  public void ajouterClientdansPiece(int numeroPiece,Personnage personnage) throws RemoteException          
  {
      int nb=0;
       for(Personnage p:tab.get(numeroPiece).getListePerso())
       {
           if(p.getNom().equals(personnage.getNom()))
           {
             
             nb=1;
           }
              
          
       }
             if(nb==0)
              tab.get(numeroPiece).ajouterPersonnage(personnage);
             else
             {
                 deleteClient(personnage.getNom(), numeroPiece);
                 tab.get(numeroPiece).ajouterPersonnage(personnage);
             }
       
        
        
  }
  /**
   * 
   * @param nom pseudo du personnage à supprimer 
   * @param numeroPiece numero de la pièce où se trouve le personnage
   */
  public void deleteClient(String nom,int numeroPiece)
  {
       for(Personnage p:tab.get(numeroPiece).getListePerso())
       {
           if(p.getNom().equals(nom))
           {
             
           tab.get(numeroPiece).supprimerPersonnage(p);
           return ;
             
           }
       }
              
  }
       /**
        * 
        * @param numeroPiece numero de la pièce où se trouve le personnage 
        * @param client   le client qu'on veut deplacer qui se trouve dans personnage surtout pour la notification qu'on deplace le client  
        * @param numeroPieceDest la pièce où on veut le deplacer 
        * @throws RemoteException 
        */
  public void deplacerClient(int numeroPiece,InterfaceClient client,int numeroPieceDest) throws RemoteException
  {
    
      for(Personnage p:tab.get(numeroPiece).getListePerso())
      {
          
          if(p.getNom().equals(client.getNom()))
          {
              p.setNumeropiece(numeroPieceDest);
              tab.get(numeroPieceDest).ajouterPersonnage(p);
              tab.get(numeroPiece).supprimerPersonnage(p);
            
              return ;
          }
                    
      }
  }
  public void afficher(int numeroPiece)
  {
      for(Personnage p:tab.get(numeroPiece).getListePerso())
      {
          System.out.println(""+p.getNom()+""+p.getNumeropiece());   
      }
  }
    public static void main(String[] args) throws RemoteException {
        ListeClientParPiece l=new ListeClientParPiece();
        l.initialiser();
        ImplementationClient c=new ImplementationClient();
        c.setNom("DIALLO");
        Personnage p=new Personnage();
        p.setNom("DIALLO");
        p.setClient(c);
        p.setNumeropiece(1);
        l.ajouterClientdansPiece(1, p);
        l.afficher(1);
        l.deplacerClient(1, c, 2);
        p=l.getPseudoPersonnage("DIALLO");
        System.out.println(""+p.getNom()+""+p.getNumeropiece());
        
        
    }
    /**
     * 
     * @return 
     */
  
    public HashMap<Integer,ListePersonnage> getTab() {
        return tab;
    }
   /***
    * 
    * @param tab 
    */
    public void setTab(HashMap<Integer,ListePersonnage> tab) {
        this.tab = tab;
    }
}

