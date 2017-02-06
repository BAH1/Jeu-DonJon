/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPersonnage;

/**
 *
 * @author elhadj
 */
public class Personnage {
    private String nom;
    private int numeropiece;

    public Personnage(String nom, int numeropiece) {
        this.nom = nom;
        this.numeropiece = numeropiece;
    }
    public  Personnage()
    {
        
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumeropiece() {
        return numeropiece;
    }

    public void setNumeropiece(int numeropiece) {
        this.numeropiece = numeropiece;
    }
    public String toString()
    {
        return "Mr "+nom+" est dans la pièce "+numeropiece;
    }
    
    
}
