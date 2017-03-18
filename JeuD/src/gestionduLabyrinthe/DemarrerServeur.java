/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionduLabyrinthe;
import gestionDeCombat.ImplInterfaceCombattre;
import gestionDeCombat.Monstre;
import gestionduchat.ServerChatImpl;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elhadj
 */
public class DemarrerServeur {
    
    
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        /**
         * on lance le serveur sur le port 99
         */
        ArrayList<ImplInterfaceCombattre>server=new ArrayList<>();
        
        LocateRegistry.createRegistry(1099);
        ImplementationDuLabyrinthe lab=new ImplementationDuLabyrinthe("bourourhe");
        lab.CreationDuLabyrinthe();
        System.out.println(""+lab.toString());
        /**
         * on créé un objet vers lequel on va lier le client,on lance parallèlement le serveur de chat sur le port 1098
         */
      Naming.rebind("rmi://localhost:1099/by", lab);
      ServerChatImpl s=new ServerChatImpl(lab.getRegistre());
      System.out.println(""+s.toString());
       try {
             Naming.rebind("rmi://localhost:1099/RMIT",s);
        } catch (MalformedURLException ex) {
      Logger.getLogger(ImplementationDuLabyrinthe.class.getName()).log(Level.SEVERE, null, ex);
      }
                    
      LocateRegistry.createRegistry(1097);
     ImplInterfaceCombattre comb=new ImplInterfaceCombattre(lab.getRegistre());
      Naming.rebind("rmi://localhost:1097/combat", comb);
       comb.initMonstreSalle();
      System.out.println(comb.toString());
      for(int i=0;i<2;i++)
        {
         server.add(new ImplInterfaceCombattre(lab.getRegistre()));
        }
        
          Naming.rebind("rmi://localhost:1099/combatserverone",server.get(0) );
          server.get(0).initMonstreSalle();
          
          System.out.println("creation "+server.get(0).toString());  
           Naming.rebind("rmi://localhost:1099/combatservertwo", server.get(1));
           server.get(1).initMonstreSalle();
          System.out.println("creation2 "+server.get(1).toString());  
    }
    
}
