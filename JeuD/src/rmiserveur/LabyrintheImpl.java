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



public class LabyrintheImpl extends UnicastRemoteObject implements LabyrintheInterface {

	/**
	 * 
	 */
	 private ArrayList<Piece>pieceLab;
	    /**
	     * le labyrinthe est composé de pièce 
	     */
	public Connection idConnection;
	private static final long serialVersionUID = 1L;
   
	protected LabyrintheImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		 pieceLab=new ArrayList<>();
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
    }
	

	public Double afficher(Double mt) throws RemoteException {
		// TODO Auto-generated method stub
		return mt*1000;
	}

	public Date getDate() throws RemoteException {
		// TODO Auto-generated method stub
		return new Date();
	}

	@Override
	public void afficher() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("je suis inteligent");
		
	}
	/**
	 * 
	 * Connexion à la base donnée 
	 */

	@Override
	public void connexionBD() throws RemoteException {
		// TODO Auto-generated method stub
		try
		{
		 Class.forName("org.postgresql.Driver");
	      //System.out.println("Driver O.K.");

	      String url = "jdbc:postgresql://localhost:5433/DIALLO";
	      String user = "postgres";
	      String passwd = "diallo";

	      Connection conn;
		
			conn = DriverManager.getConnection(url, user, passwd);
			 idConnection = conn;
		      System.out.println("Connexion BD okkkk !!!");         

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     	     
		
	}
        /**
         * 
         * Executer une requête pour recuperer plusieurs colonnes de la table
         * @param requete
         * @param nbcolonne
         * @return 
         */
    public String executerRequetePluColonne(String requete,int nbcolonne)
    {
    	int i;
    	ArrayList<String>tab=new ArrayList<>();
    	String res=new String();
		java.sql.Statement state;
		try {
			state = idConnection.createStatement();
			 ResultSet resultat = state.executeQuery(requete);
			 System.out.println(requete);
			 while(resultat.next())
             {  
				 for(i=1;i<=nbcolonne;i++)
				 {   if(i!=nbcolonne)
					 tab.add(" ("+resultat.getString(i)+":") ; 
				 else
					 tab.add(" "+resultat.getString(i)+" ");
				 }
                  
                 
             }
		   
       	 for(String s:tab)
       	 {
       		 res+=""+s;
       	 }
       	 return res;
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "Erreur Traitement";
		
    }
    /**
     * recupère une seule colonne d'une table
     * @param requete
     * @return 
     */
	public String executerRequete(String requete)
	{
		String res=new String();
		java.sql.Statement state;
		try {
			state = idConnection.createStatement();
			 ResultSet resultat = state.executeQuery(requete);
			 while(resultat.next())
             {
                  res= resultat.getString(1);
             }
		   
       	 return res;
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "Erreur Traitement";
		
	
		
	}
        /**
         * 
         * INSERTIION DANS LA BASE DE DONNÉE À PARTIR D'UNE REQUÊTE
         */
	public void insertion(String requete)
	{
		String res=new String();
		java.sql.Statement state;
		 try {
			state = idConnection.createStatement();
			 state.execute(requete);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 		
	}
        /**
         * Recupère les pièces suivantes et les portes pour y acceder
         * @param numeroPiece
         * @return 
         */
	public String pieceAcote(int numeroPiece)
	{
		String requete;
		String res=new String();
	
		requete="select portepi,numpidest from \"TRAVERSER\" where numerop='"+numeroPiece+"'";
	return   	executerRequetePluColonne(requete,2);
	}
	/**
         * Permet dans quel picèce se trouve le joueur
         * @param pseudo
         * @return
         * @throws RemoteException 
         */
	public String positionJoueur(String pseudo) throws RemoteException
	{   int numero;
	    String requete;
	    String affiche=new String();
        String res=new String();
	    requete="SELECT numeropi from \"SETROUVER\" where pseudopi='"+pseudo+"'";
	    
	    res=executerRequete(requete);
	    if(res.length()==0)
	      {
	      requete="INSERT INTO \"SETROUVER\"VALUES('"+pseudo+"','1')";
	      insertion(requete);
	     
	      Piece p=new Piece(1);
	      p.creerUnePiece(1);
	      affiche+=""+p.afficherLaPiece();
	      affiche+="\n";
	      affiche+="Porte:Pièce Suivante \n";
	      affiche+=pieceAcote(1);
	      return affiche;
	    }
	    else 
	    {
	    	numero=Integer.parseInt(res);
	    	Piece p=new Piece(numero);
	    	p.creerUnePiece(numero);
	    	insertion(requete);
	        
	    	   affiche+=""+p.afficherLaPiece();
		      affiche+="\n";
		      affiche+="Porte:Pièce Suivante \n";
		      affiche+=pieceAcote(numero);
		      return affiche;
		  
	    }
		
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
     
   
		   sortie=executerRequete(requeteVerif);
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
        	   insertion(requeteVerif);
        	   
        	   return "2";
        	   }
        	   else
        		   return "0";
           }
		

   
	}

}
