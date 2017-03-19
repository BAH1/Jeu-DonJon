/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduLabyrinthe;

/**
 *
 * @author elhadj
 */

import gestionPersonnage.Personnage;
import java.util.ArrayList;

public class Piece {

	
	 private int numeroPiece;

     /**
   * numero de la pièce
   */
   
    private ArrayList<String>nomPorte;
     
    /*
    *nom de la porte qui peut être NORD ou SUD ou ESTou OUEST.
    */
    private String porte[]={"NORD","SUD","EST","OUEST"};
    /*
    *
    Toute les portes dispobibles
    */
    private ArrayList<Personnage>personneDansLapiece;
            
    
    public Piece(Integer numeroPiece) {
        
        this.numeroPiece = numeroPiece;
        this.nomPorte=new ArrayList<>();
        personneDansLapiece=new ArrayList<>();
        creerUnePiece(numeroPiece);
               
    }
    public int trouverUnPersonne(String nom)
    {  
        int i;
        boolean trouve=false;
        i=0;
        
        while(i<personneDansLapiece.size()&&!trouve)
        {
            if(personneDansLapiece.get(i).getNom().equals(nom))
                trouve=true;
            i++;
        }
        if(trouve==true)
            return i;
        else 
            return 0;
        
    }
    public void AjouterPersonnage(Personnage personnage)
    {
        personneDansLapiece.add(personnage);
    }
    public void afficherPersonneDanspiece()
    {
        for(Personnage p:personneDansLapiece)
            System.out.println(""+p.toString());
    }
    
    
   /**
    * 
    * @param numeroPiece 
    * On initialise les portes de chaque pièce en fonction du numero de pièce
    * le labyrinthe est composé de 9 pièces 3*3
    */
    public void creerUnePiece(Integer numeroPiece)
    {
        
        if(numeroPiece==1 || numeroPiece==2)
        {
          for(int i=1;i<porte.length;i++)
          {
              
              nomPorte.add(porte[i]);
          
          }
             
        }
        
        else if(numeroPiece==4 || numeroPiece==7)
        {
              for(int i=0;i<porte.length-1;i++)
              nomPorte.add(porte[i]);
            
        }
          else if(numeroPiece==5 || numeroPiece==8)
        {
            for(int i=0;i<porte.length;i++)
              nomPorte.add(porte[i]);
        }
        else 
        {
          
              
              nomPorte.add(porte[3]);
              nomPorte.add(porte[1]);
           
        }
    }
    
    /**
     * 
     * @param numero 
     * 
     */
    
     public String   afficherLaPiece()
     {
            String s=new String();
            s+="Vous êtes dans la pièce "+numeroPiece;
            s+="\n";
            s+="Portes de la pièce:";
            for(String p:nomPorte)
            {
                s+="  "+p;
            }
            return s;
        }

    public void setNumeroPiece(int numeroPiece) {
        this.numeroPiece = numeroPiece;
    }

    public void setNomPorte(ArrayList<String> nomPorte) {
        this.nomPorte = nomPorte;
    }

    public int getNumeroPiece() {
        return numeroPiece;
    }
    

    public ArrayList<String> getNomPorte() {
        return nomPorte;
    }
    

    public String[] getPorte() {
        return porte;
    }
 
 
}
