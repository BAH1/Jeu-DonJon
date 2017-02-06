/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduclient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 *
 * @author elhadj
 */
public class ImplementationClient extends UnicastRemoteObject implements InterfaceClient{
    
    private String nom;
    private int numeropiece;
    private Scanner sc;
    public ImplementationClient()throws RemoteException
    {
        super();
      sc=new Scanner(System.in);
    }
    
    public void saisirPseudo()
    {
       
        System.out.println("Entrer votre pseudo");
        nom=sc.nextLine();
    }

    public String getNom() throws RemoteException{
        return nom;
    }

    public void setNom(String nom)throws RemoteException{
        this.nom = nom;
    }

    public int getNumeropiece()throws RemoteException{
        return numeropiece;
    }

    public void setNumeropiece(int numeropiece)throws RemoteException{
        this.numeropiece = numeropiece;
    }

    @Override
    public void afficherEtatConnexion(int numeroPiece) throws RemoteException {
         //To change body of generated methods, choose Tools | Templates.
         if(numeroPiece!=0)
         {
          System.out.println("Bienvenue");
           System.out.println("Vous êtes dans la pièce "+numeroPiece);   
         }
         else 
             System.out.println("Votre pseudo est trop court");
         
    }
    public void Menu() throws RemoteException
    {
        System.out.println("1.pour se deplacer ");
         System.out.println("2.pour chatter ");
          System.out.println("3.Quitter ");
        
    }
    public String choixclient() throws RemoteException
    {
       return sc.nextLine();
    }

    @Override
    public void afficher(String s) throws RemoteException {
       //To change body of generated methods, choose Tools | Templates.
        System.out.println(s);
    }

    @Override
    public String envoyerMessage() throws RemoteException {
        //To change body of generated methods, choose Tools | Templates.
        Scanner sc=new Scanner(System.in);
        String monMessage=new String();
        System.out.println("Entrer votre message");
         monMessage+="["+nom+"] ";
    
        return    monMessage+=sc.nextLine();
   
        
    }

    @Override
    public void recupererMessage(String message) throws RemoteException {
       //To change body of generated methods, choose Tools | Templates.
       System.out.println(message);
       
    }

    @Override
    public void seConnecterAuChat() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afficherNotification() throws RemoteException {
     //To change body of generated methods, choose Tools | Templates.
        System.out.println("D'autre joueurs sont présents dans la pièce");
    }
  
    
}
