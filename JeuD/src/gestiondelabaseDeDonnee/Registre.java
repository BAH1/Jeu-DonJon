/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondelabaseDeDonnee;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elhadj
 */
public class Registre {
    
    private String requete;
    public Connection idConnection;
    public Registre ()
    {
        
    }
    
    public void connexionBD() throws RemoteException {
		// TODO Auto-generated method stub
		try
		{
		 Class.forName("org.postgresql.Driver");
	      //System.out.println("Driver O.K.");

	      String url = "jdbc:postgresql://localhost:5433/DIALLO";
	      String user = "postgres";
	      String passwd = "diallo";

	      
		
			idConnection = DriverManager.getConnection(url, user, passwd);
			
		      System.out.println("Connexion BD okkkk !!!");         

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     	     
		
	}
    public int recupererViePersonnage(String pseudo)
    {
        String requeteVerif;
          requeteVerif="select viejoueur from \"JOUEUR\" where pseudo='"+pseudo+"'";
            return   Integer.parseInt(executerRequete(requeteVerif));
    }
    public void mettreAjourvieJoueur(String pseudo,int viejoueur)
    {
     String requete;
     requete="UPDATE \"JOUEUR\" SET viejoueur='"+viejoueur+"' where pseudo='"+pseudo+"'";
        System.err.println(""+requete);
     insertion(requete);
    }
  
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
    public String MenuPourSeDeplacer(String requete)
    {
        try {
            String res=new String();
            java.sql.Statement state;
            state = idConnection.createStatement();
            ResultSet resultat = state.executeQuery(requete);
            
            ResultSetMetaData resultMeta = resultat.getMetaData();
            res+="*****PORTE**********PIECE SUIVANTE***********";
            while(resultat.next())
            {
           res+="\n**********************************\n";
                
            
            //On affiche le nom des colonnes
            for(int i = 1; i <= resultMeta.getColumnCount(); i++)
            {
                
                  res+="\t"+resultat.getObject(i).toString()+ "\t *";
            }     
            
              res+="\n**********************************";
            }
            return res;
         } catch (SQLException ex) {
            Logger.getLogger(Registre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ErreurDeTraitement";
  
    }
    public ArrayList<String> executerTraiter(String requete,int pos)
    {
        
        try {
            String res=new String();
            java.sql.Statement state;
            ArrayList<String>contenuColonne=new ArrayList<>();
            state = idConnection.createStatement();
            ResultSet resultat = state.executeQuery(requete);
            
            ResultSetMetaData resultMeta = resultat.getMetaData();
           
            while(resultat.next())
            {
           
                
            
            //On affiche le nom des colonnes
           // for(int i = 1; i <= resultMeta.getColumnCount(); i++)
            //{
                
              contenuColonne.add(resultat.getObject(pos).toString());
           // }     
            
              
            }
            return contenuColonne;
         } catch (SQLException ex) {
            Logger.getLogger(Registre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return null;
    }
    
  
}
