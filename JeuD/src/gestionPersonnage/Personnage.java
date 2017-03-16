/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPersonnage;


import gestionduclient.InterfaceClient;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Scanner;


public class Personnage implements Serializable{
    private Scanner sc;
    private String nom;
    private int numeropiece;
    private Integer vieJoueur = 10;
    private Integer nbreCombat=0;
    
    private InterfaceClient client;
    public Personnage(String nom, int numeropiece) {
        this.nom = nom;
        this.numeropiece = numeropiece;
    }
    public Personnage(String nom, int numeropiece,InterfaceClient client) {
        this.nom = nom;
        this.numeropiece = numeropiece;
        this.client=client;
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
    
    public Integer getVieJoueur() {
        return vieJoueur;
    }

    public void setVie(Integer vie) {
        this.vieJoueur = vie;
        if(vieJoueur <0){
        vieJoueur=0;
        }
    }
    public void retirerVieJoueur(Integer vie) {
        this.vieJoueur -= vie;
       if(vieJoueur <0){
        vieJoueur=0;
        }
       
    }
    public void ajouterVieJoueur(Integer vie) {
        this.vieJoueur += vie;
      
    }
    
    
    
     
     public void attaquerpersonnage(Personnage p){
        p.retirerVieJoueur(1);
}
     
     
   
    
    public String toString()
    {
        return "Mr "+nom+" est dans la pièce "+numeropiece;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    
  

    public InterfaceClient getClient() {
        return client;
    }

    public void setClient(InterfaceClient client) {
        this.client = client;
    }

    
     public void afficher(String s) throws RemoteException {
       //To change body of generated methods, choose Tools | Templates.
        System.out.println(s);
    }
     public String choixJoueur() throws RemoteException
    {
       return sc.nextLine();
    }

    public int getTest() throws RemoteException {
        return client.getEtat();
    }

    public Integer getNbreCombat() {
        return nbreCombat;
    }

    public void setNbreCombat(Integer nbreCombat) {
        this.nbreCombat = nbreCombat;
    }

   
    
}
