/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personnage;

import java.util.Scanner;
import move.Piece;

/**
 *
 * @author elhadj
 */
public class Joueur {
    
    private String nomjoueur;
     private Piece pieceJoeur; 
     /**
      * 
      * @param nomjoueur
      * @param pieceJoeur
      * on crée un joueur à partir de son pseudo et la pièce dans laquelle il se trouve
      */
    public Joueur(String nomjoueur, Piece pieceJoeur) {
        this.nomjoueur = nomjoueur;
        this.pieceJoeur = pieceJoeur;
    }
  /***
   * 
   * @param nomjoueur
   * recuperer et mettre à jour les attributs de joueur
   */
    public void setNomjoueur(String nomjoueur) {
        this.nomjoueur = nomjoueur;
    }

    public void setPieceJoeur(Piece pieceJoeur) {
        this.pieceJoeur = pieceJoeur;
    }
   

    public String getNomjoueur() {
        return nomjoueur;
    }

    public Piece getPieceJoeur() {
        return pieceJoeur;
    }
    
    
    public void deplacement()
    {
        Scanner sc=new Scanner(System.in);
        String choix=new String();
        Piece p=this.getPieceJoeur();
       if(p.getNumeroPiece()==1)
       {
           
           
           System.out.println("Tapez E pour vous rendre à la pièce numero 2");
           System.out.println("Tapez S pour vous rendre à la pièce numero 4");
           choix=sc.nextLine();
           if(choix.equals("E"))
           {
              
               p.setNumeroPiece(2);
               this.setPieceJoeur(p);
           }
           else if(choix.equals("S"))
           {
             p.setNumeroPiece(4);
             p.creerUnePiece(2);
             this.setPieceJoeur(p);
           }
       }
    }

    
    
    
}
