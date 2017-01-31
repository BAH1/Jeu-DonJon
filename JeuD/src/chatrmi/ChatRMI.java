/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatrmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import messagerie.InterfaceServ;


/**
 *
 * @author elhadj
 */
public class ChatRMI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        // TODO code application logic here
         Scanner sc=new Scanner(System.in);
        InterfaceServ test=(InterfaceServ)Naming.lookup("rmi://localhost:1098/RMIT");
       
        ClientImpl c=new ClientImpl(test,"DIALLO");
        
        
        new Thread(c).start();
    }
    
}
