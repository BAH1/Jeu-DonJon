/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatrmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import messagerie.InterfaceServ;

/**
 *
 * @author elhadj
 */
public class ClientImpl extends UnicastRemoteObject implements ClientInterface,Runnable{
   
    private InterfaceServ chatserver;
    private String name;

    public ClientImpl(InterfaceServ chatserver, String name) throws RemoteException
    {
        this.chatserver = chatserver;
        this.name = name;
        chatserver.enregistrerClient(this);
    }
    

    

    @Override
    public void run() {
        //To change body of generated methods, choose Tools | Templates.
        Scanner sc=new Scanner(System.in);
        String msg=new String();
        
        System.out.println("Entrer votre message ");
        while(true)
        {  
            msg+=""+name+" :";
            msg+=""+sc.nextLine();
            try {
                msg=chatserver.transmettreMessage(msg);
                System.out.print(""+msg);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
        
    }

    
}
