/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import jeud.LabyrintheImpl;
import move.Piece;
import personnage.Joueur;

/**
 *
 * @author elhadj
 */
public class R {
    
    public static void main(String[] args) {
      
         Piece p=new Piece(1);
         p.creerUnePiece(1);
        Joueur j=new Joueur("DIALLO",p);
        
        j.getPieceJoeur().afficherLaPiece();
        j.deplacement();
        j.getPieceJoeur().afficherLaPiece();
       
        
          
    }
    
}
