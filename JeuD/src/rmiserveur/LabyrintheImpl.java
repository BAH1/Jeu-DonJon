/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserveur;

/**
 *
 * @author elhadj
 */

import java.beans.Statement;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.print.attribute.standard.JobOriginatingUserName;

import composition.Piece;
import registreDe.Registre;



public class LabyrintheImpl extends UnicastRemoteObject implements LabyrintheInterface {

	/**
	 * 
	 */
	 private ArrayList<Piece>pieceLab;
        
	    /**
	     * le labyrinthe est composé de pièce 
	     */
	public Connection idConnection;
        private registreDe.Registre registre;
	private static final long serialVersionUID = 1L;
   
	protected LabyrintheImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		 pieceLab=new ArrayList<>();
                 registre=new Registre();
	}
	/**
	 * 
	 * 
	 * @throws RemoteException
	 * crée les pièces juxtaposées avec au moins une porte par pièce
	 */
	public void creerLabyrinthe() throws RemoteException
    {
        for(int i=1;i<=9;i++)
        {
            Piece p=new Piece(i);
            p.creerUnePiece(i);
            pieceLab.add(p);
        }
        registre.connexionBD();
    }
	

 
	public String pieceAcote(int numeroPiece)
	{
		String requete;
		String res=new String();
	
		requete="select portepi,numpidest from \"TRAVERSER\" where numerop='"+numeroPiece+"'";
	return   	registre.executerRequetePluColonne(requete,2);
	}
	/**
         * Permet dans quel picèce se trouve le joueur
         * @param pseudo
         * @return
         * @throws RemoteException 
         */
	public String positionJoueur(String pseudo) throws RemoteException
	{   int numero,nombreJoueur;
	    String requete;
	    String affiche=new String();
        String res=new String();
        /*on verifie dans quel pièce se trouve le joueur */
	    requete="SELECT numeropi from \"SETROUVER\" where pseudopi='"+pseudo+"'";
	    
	    res=registre.executerRequete(requete);
	    if(res.length()==0)
	      {
	      requete="INSERT INTO \"SETROUVER\"VALUES('"+pseudo+"','1')";
	      registre.insertion(requete);
	     
	      Piece p=new Piece(1);
	      p.creerUnePiece(1);
	      affiche+=""+p.afficherLaPiece();
	      affiche+="\n";
	                
	      return affiche;
	    }
	    else 
	    {
	    	numero=Integer.parseInt(res);
	    	Piece p=new Piece(numero);
	    	p.creerUnePiece(numero);
	    	registre.insertion(requete);
	        
	    	   affiche+=""+p.afficherLaPiece();
		      affiche+="\n";
		     
		      return affiche;
		  
	    }
		
	}
        public void VerificationPiece(String pseudo)
        {
            String requete=new String();
        }
	/**
         * 
         * Connexion à la base de donnée à partir du pseudo
         * @param pseudo
         * @return
         * @throws RemoteException 
         */
	@Override
	public String connexion(String pseudo) throws RemoteException {
		// TODO Auto-generated method stub
		String sortie=new String();
		 String requeteVerif;
        requeteVerif="SELECT pseudo FROM \"JOUEUR\" WHERE pseudo='"+pseudo+"'";
     
   
		   //sortie=executerRequete(requeteVerif);
                   sortie=registre.executerRequete(requeteVerif);
           if(sortie.equals(pseudo)&& pseudo.length()>3)
           {
        	return "1";   
           }
           else
           {
        	   if(pseudo.length()>3)
        	   {
        	   requeteVerif="INSERT INTO \"JOUEUR\" VALUES('"+pseudo+"')";
        	   //state.execute(requeteVerif);
        	   registre.insertion(requeteVerif);
        	   
        	   return "2";
        	   }
        	   else
        		   return "0";
           }
		

   
	}
        public int nombreDeJoueurDansPiece(String pseudo)
        {
            String res=new String();
            String requete=new String();
            requete="select numeropi from \"SETROUVER\" where pseudopi='"+pseudo+"'";
            res=registre.executerRequete(requete);
            requete="select count(pseudopi) as nb from \"SETROUVER\" where numeropi='"+res+"'";
            res=registre.executerRequete(requete);
            return Integer.parseInt(res);
            
        }

   
    

        
    
    

}
