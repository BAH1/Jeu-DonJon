/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduchat;
import gestionPersonnage.ListeClientParPiece;
import gestionPersonnage.ListePersonnage;
import gestionPersonnage.Personnage;
import gestiondelabaseDeDonnee.Registre;
import gestionduclient.InterfaceClient;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author elhadj
 */
public class ServerChatImpl extends UnicastRemoteObject implements ServeurChat{
    
    private ArrayList<String>registre;
    private Registre base;
    private ArrayList<Personnage>  listeP;
    public ServerChatImpl(Registre base)throws RemoteException{
       registre=new ArrayList<>();
       this.base=base;
       listeP=new ArrayList<>();
    }
    
    public void broadcasterMessage(int numeropiece) throws RemoteException
    {
        String s=new String();
         String requete;
         requete="select message from \"MESSAGEPIECE\" where numerop='"+numeropiece+"'" ;
     
         s=base.executerRequete(requete);
       
        for(Personnage p:listeP)
        {
            if(p.getNumeropiece()==numeropiece)
            {
               
             p.getClient().afficher(s);   
            }
             
        }
        
    }

   
   
    

    @Override
    public void recupererMessage(String message, InterfaceClient client,int numeroP) throws RemoteException {
      String requete;
       requete="INSERT INTO \"MESSAGEPIECE\" VALUES('"+numeroP+"','"+client.getNom()+"'";
       requete+=",CURRENT_TIMESTAMP,'"+message+"')";
       base.insertion(requete);
   
    }

    @Override
    public void recupererListeClients(ArrayList<Personnage>  liste) throws RemoteException {
         //To change body of generated methods, choose Tools | Templates.
       
        this.listeP=liste;
    }
    

    
    
}
