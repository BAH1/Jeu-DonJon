/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduchat;
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
    private ArrayList<InterfaceClient>tabclient;
    private ArrayList<String>registre;
    private Registre base;
    public ServerChatImpl() throws RemoteException{
       tabclient=new ArrayList<>();
       registre=new ArrayList<>();
       base=new Registre();
       base.connexionBD();
    }
    

    @Override
    public void envoyerMessageAtous(int numeroPiece) throws RemoteException {
         //To change body of generated methods, choose Tools | Templates.
         String s=new String();
         String requete;
         requete="select message from \"MESSAGEPIECE\" where numerop='"+numeroPiece+"'" ;
         System.out.println(""+requete);
         s=base.executerRequete(requete);
        
         
         for(InterfaceClient c:tabclient)
         {
             if(c.getNumeropiece()==numeroPiece)
             c.recupererMessage(s);
         }
    }

    @Override
    public void enregistrerClient(InterfaceClient c,int numero) throws RemoteException {
        //To change body of generated methods, choose Tools | Templates.
      tabclient.add(c);
    }

    @Override
    public void recevoirMessage(String message,InterfaceClient client) throws RemoteException {
        //To change body of generated methods, choose Tools | Templates.
       String requete;
       requete="INSERT INTO \"MESSAGEPIECE\" VALUES('"+client.getNumeropiece()+"','"+client.getNom()+"'";
       requete+=",CURRENT_TIMESTAMP,'"+message+"')";
       base.insertion(requete);
            
    }
    
}
