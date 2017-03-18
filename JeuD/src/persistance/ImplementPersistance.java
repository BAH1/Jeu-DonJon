/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import gestiondelabaseDeDonnee.Registre;
import gestionduclient.InterfaceClient;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author elhadj
 */
public class ImplementPersistance extends UnicastRemoteObject implements InterfacePersistance{
    
    
    private Registre base;
    public  ImplementPersistance()throws RemoteException
    {
     base=new Registre();
     base.connexionBD();
    }
    @Override
    public void mettreAjourvieJoueur(String pseudo,int viejoueur) throws RemoteException {
     String requete;
     requete="UPDATE \"JOUEUR\" SET viejoueur='"+viejoueur+"' where pseudo='"+pseudo+"'";
     base.insertion(requete);
    }
    
    
    
    
}
