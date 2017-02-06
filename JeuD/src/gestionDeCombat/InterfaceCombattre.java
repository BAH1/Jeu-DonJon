/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDeCombat;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author elhadj
 */
public interface InterfaceCombattre extends Remote{
    
    public void combatMJ (Monstre M, Joueur j) throws RemoteException;
    public void combatJJ (Joueur j1, Joueur j2) throws RemoteException; 
}
