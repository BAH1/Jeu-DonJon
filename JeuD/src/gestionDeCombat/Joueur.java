/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDeCombat;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author elhadj
 */
public class Joueur implements Serializable{
    private Integer vieJoueur = 10;
	private String nomjoueur;

    private String motdepasse;
    /**
     * 
     * @param nomjoueur
     * @param pieceJoeur
     * @param  motdepasse
     * on crée un joueur à partir de son pseudo et la pièce dans laquelle il se trouve
     */
    
   public Joueur(String nomjoueur) {
       this.nomjoueur = nomjoueur;
      
       
   }
   public Joueur()
   {
       
   }
   
   public void Saisir()
   {
	   Scanner sc=new Scanner(System.in);
	   System.out.println("Veiller Votre Pseudo");
	   setNomjoueur(sc.nextLine());
	   
	   
   }
   public String Menu()
   {
       Scanner sc=new Scanner(System.in);
       System.out.println("****1. Pour attaquer*****************************");
       System.out.println("****1.Pour Vous deplacez******");
       System.out.println("****2.Envoyer un message***********");
       System.out.println("****3. Pour quitter***************");
       return sc.nextLine();
   }
 /***
  * 
  * @param nomjoueur
  * recuperer et mettre à jour les attributs de joueur
  */
   public void setNomjoueur(String nomjoueur) {
       this.nomjoueur = nomjoueur;
   }

   
  

   public String getNomjoueur() {
       return nomjoueur;
   }

    public Integer getVieJoueur() {
        return vieJoueur;
    }

    public void setVie(Integer vie) {
        this.vieJoueur = vie;
    }
    public void retirerVieJoueur(Integer vie) {
        this.vieJoueur -= vie;
        System.out.println("la vie du joueur "+nomjoueur+":"+vieJoueur);
    }
    public void ajouterVieJoueur(Integer vie) {
        this.vieJoueur += vie;
       System.out.println("la vie du joueur "+nomjoueur+":"+vieJoueur);
    }
    
    public void attaquerMonstre(Monstre M, Joueur j){
        System.out.println("Joueur"+j+"attaque le Monstre"+M);
        M.retirerVieMonstre(1);
}
    
     public String Menureduit()
   {
       Scanner sc=new Scanner(System.in);
       System.out.println("****2. Pour Vous deplacez (Fuir le combat)******");
       System.out.println("****3.Envoyer un message************************");
       System.out.println("****4. Pour quitter*****************************");
       return sc.nextLine();
   }


    
}