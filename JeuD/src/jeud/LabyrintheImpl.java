/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeud;

import java.util.ArrayList;
import java.util.Scanner;
import move.Piece;
import personnage.Joueur;

/**
 *
 * @author elhadj
 */
public class LabyrintheImpl {
    
    private ArrayList<Piece>pieceLab;
    /**
     * le labyrinthe est composé de pièce 
     */
    public LabyrintheImpl()
    {
        pieceLab=new ArrayList<>();
    }
    public void creerLabyrinthe()
    {
        for(int i=1;i<=9;i++)
        {
            Piece p=new Piece(i);
            p.creerUnePiece(i);
            pieceLab.add(p);
        }
        for(Piece pi:pieceLab)
        {
            pi.afficherLaPiece();
        }
    }
    
   
    
    
}
