/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import gestionduclient.InterfaceClient;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author elhadj
 */
public interface InterfacePersistance extends Remote{
    
    public void mettreAjourvieJoueur(String pseudo,int viejoueur)throws RemoteException;
    
}
