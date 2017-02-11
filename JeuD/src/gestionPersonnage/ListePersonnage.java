/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPersonnage;

import java.util.ArrayList;

/**
 *
 * @author elhadj
 */
public class ListePersonnage {
    private ArrayList<Personnage>listePerso;
    public ListePersonnage()
    {
        listePerso=new ArrayList<>();
    }
    
    public ArrayList<Personnage> getListePerso() {
        return listePerso;
    }

    public void setListePerso(ArrayList<Personnage> listePerso) {
        this.listePerso = listePerso;
    }
    /**
     *  Ajouter un personnage dans la liste de personnage
     * @param personnage 
     */
    public void ajouterPersonnage(Personnage personnage)
    {
       
        listePerso.add(personnage);
        
    }
    /**
     * supprimer le personnage de la liste de personnage
     * @param personnage 
     */
    public void supprimerPersonnage(Personnage personnage)
    {
        listePerso.remove(personnage);
    }
    /**
     * chercher un personnage en fonction de son pseudo
     * @param pseudo
     * @return 
     */
    public Personnage chercherPersonnage(String pseudo)
    {
       for(Personnage p:listePerso)
        {
            if(p.getNom().equals(pseudo))
            {
               return p;
                
            }
        }
        return null;
    }
    /**
     * Aide à chercher la pièce où se trouve le personnage
     * @param pseudo
     * @return 
     */
    public boolean chercherPiecePersonnnage(String pseudo)
    {
        boolean trouve=false;
        for(Personnage p:listePerso)
        {
            if(p.getNom().equals(pseudo))
            {
               return  trouve=true;
                
            }
        }
        return trouve;
    }
    
   
   
}
