/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDeCombat;

import gestionPersonnage.Personnage;
import gestionduclient.InterfaceClient;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author M1stri25
 */
public interface InterfaceCombat extends Remote {
    
    public void combattreMonstre(Personnage p) throws RemoteException;
    //public void combatMJ (Monstre m, Personnage p) throws RemoteException;
    //public void combatJJ (Personnage p1, Personnage p2) throws RemoteException;
    
    public void initMonstreSalle() throws RemoteException;
    public void combattreJoueur(Personnage p1) throws RemoteException;
}
