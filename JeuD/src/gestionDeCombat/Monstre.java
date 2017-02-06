/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDeCombat;

import java.util.Scanner;

/**
 *
 * @author elhadj
 */
public class Monstre {
    
     private String nomMonstre;
    private Integer vieMonstre = 5;

    public Monstre(String pnomMonstre) {
        this.nomMonstre = pnomMonstre;
    }

    
    
    public String getNomMonstre() {
        return nomMonstre;
    }

    public void setNomMonstre(String nomMonstre) {
        this.nomMonstre = nomMonstre;
    }
    
    public String Menu()
   {
       Scanner sc=new Scanner(System.in);
       System.out.println("****1. Pour attaquer*****************************");
       System.out.println("****2. Pour Vous deplacez (Fuir le combat)******");
       System.out.println("****3.Envoyer un message************************");
       System.out.println("****4. Pour quitter*****************************");
       return sc.nextLine();
   }
    
    public void retirerVieMonstre(Integer vie) {
        this.vieMonstre -= vie;
    }
    public void ajouterVieJoueur(Integer vie) {
        this.vieMonstre += vie;
    }

    public Integer getVieMonstre() {
        return vieMonstre;
    }

    public void setVieMonstre(Integer vieMonstre) {
        this.vieMonstre = vieMonstre;
    }
    
   
    
}
