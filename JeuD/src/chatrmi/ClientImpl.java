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
    private String msg;
    public ClientImpl(InterfaceServ chatserver, String name) throws RemoteException
    {
        this.chatserver = chatserver;
        this.name = name;
        chatserver.enregistrerClient(this);
    }
    public void afficher()
    {
        Scanner sc=new Scanner(System.in);
        
        
       System.out.println("Entrer votre message ");
       msg+=""+name+" :";
            msg+=""+sc.nextLine();
        
    }

    

    @Override
    public void run() {
        //To change body of generated methods, choose Tools | Templates.
        
           
            

                try {
               if(msg!=null)     
               {
                msg=chatserver.transmettreMessage(msg);
                System.out.print(""+msg);   
               }
                
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        
        
        
    }

    
}
