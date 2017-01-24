/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeud;
import java.sql.*;
/**
 *
 * @author M1stri25
 */

public class ConnexionBD {
    
   String urlPilote = "com.mysql.jdbc.Driver"; // chemin pour charger le pilote
   String urlBaseDonnees = "jdbc:mysql://localhost:3306/chat"; // chemin de connexion a la base de données
   Connection con;
   
   public ConnexionBD()
   {
       // chargement du pilote
       try{
           Class.forName(urlPilote);                
       }catch(ClassNotFoundException ex){
           System.out.println(ex);
       }
       
       // connexion a la base de données
       try{
           con = (Connection) DriverManager.getConnection(urlBaseDonnees,"root","");
           
       }catch(SQLException ex){
           System.out.println(ex);
       }
       
   }
   
   public Connection obtenirConnexion()
   {
       return con;
   }
    
}