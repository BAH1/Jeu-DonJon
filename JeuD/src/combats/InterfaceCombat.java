/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combats;

import java.rmi.Remote;
import java.rmi.RemoteException;
import personnage.Joueur;
import personnage.Monstre;

/**
 *
 * @author M1stri25
 */
public interface InterfaceCombat extends Remote{
    
    public void attaquer (Monstre M, Joueur j) throws RemoteException;
    
}
