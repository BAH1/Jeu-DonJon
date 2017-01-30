/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registreDe;

import java.util.ArrayList;

/**
 *
 * @author elhadj
 */
public class ColonneRegistre {
    
    
    private ArrayList<String>nomColonne;
    public ColonneRegistre()
    {
        nomColonne=new ArrayList<>();
    }

    public ArrayList<String> getNomColonne() {
        return nomColonne;
    }

    public void setNomColonne(ArrayList<String> nomColonne) {
        this.nomColonne = nomColonne;
    }
    public void ajouter(String valeur)
    {
        this.nomColonne.add(valeur);
    }
    public void afficherValeur()
    {
       for(String s:nomColonne)
            System.out.println(" "+s);
    }
}
