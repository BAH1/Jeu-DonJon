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

/**
 *
 * @author elhadj
 */
public class ServerChatImpl extends UnicastRemoteObject implements ServeurChat{
    
    private ArrayList<String>registre;
    private Registre base;
    private ArrayList<Personnage> liste;
    private int numeropiece;
    public ServerChatImpl()throws RemoteException{
       registre=new ArrayList<>();
       base=new Registre();
       base.connexionBD();
       liste=new ArrayList<>();
    }
    
    public void broadcasterMessage() throws RemoteException
    {
         String s=new String();
         String requete;
         requete="select message from \"MESSAGEPIECE\" where numerop='"+this.numeropiece+"'" ;
     
         s=base.executerRequete(requete);
         for(Personnage p:liste)
         {
             p.getClient().afficher(s);
         }
    }

   
   
    @Override
    public void recupererListeClient(ArrayList<Personnage> liste,int numeroP) throws RemoteException {
     this.liste=liste;
     this.numeropiece=numeroP;
    }

    @Override
    public void recupererMessage(String message, InterfaceClient client) throws RemoteException {
      String requete;
       requete="INSERT INTO \"MESSAGEPIECE\" VALUES('"+this.numeropiece+"','"+client.getNom()+"'";
       requete+=",CURRENT_TIMESTAMP,'"+message+"')";
       base.insertion(requete);
   
    }
    

    
    
}
