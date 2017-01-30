/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author elhadj
 */
package personnage;
import java.io.Serializable;
import java.util.Scanner;

public class Joueur implements Serializable {
	
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

   
  

}
