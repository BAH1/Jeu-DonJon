/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registreDe;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	      Connection conn;
		
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
    
       
}
