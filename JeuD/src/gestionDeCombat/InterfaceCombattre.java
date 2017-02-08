/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDeCombat;

import gestionPersonnage.Personnage;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author elhadj
 */
public interface InterfaceCombattre extends Remote{
    
    public void combatMJ (Monstre m, Personnage p) throws RemoteException;
    //public void combatJJ (Personnage p1, Personnage p2) throws RemoteException; 
    //public void a()throws RemoteException;
}
