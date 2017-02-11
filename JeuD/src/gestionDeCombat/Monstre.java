/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDeCombat;

import gestionPersonnage.Personnage;
import gestionduclient.InterfaceClient;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author elhadj
 */
public class Monstre  implements Serializable{
    
    private String nomMonstre;
    private Integer vieMonstre = 5;
    private boolean etatMonstre;
    public Monstre(String pnomMonstre) {
        this.nomMonstre = pnomMonstre;
        vieMonstre=5;
        etatMonstre=false;
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
        if(vieMonstre<0){
        vieMonstre=0;
        }
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
    
     public void attaquerPersonnage(Personnage p) throws RemoteException{
        p.setVie(p.getVieJoueur()-1);
}

    public boolean isEtatMonstre() {
        return etatMonstre;
    }

    public void setEtatMonstre(boolean etatMonstre) {
        this.etatMonstre = etatMonstre;
    }
   
    
}
