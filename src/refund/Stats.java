/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import net.sf.json.JSONObject;

/**
 *
 * @author Max
 */
public class Stats {
    private JSONObject reclamations;
    private JSONObject soins;
    
    public Stats(){
       
        reclamations = Entree.stats.getJSONObject("Reclamations");
        soins = Entree.stats.getJSONObject("Soins");
    }
    @Override
    public String toString(){
        return "Statistiques: \n Réclamations traitées: " 
                + reclamations.getInt("traitees") + "\n Réclamations rejetées: " 
                + reclamations.getInt("rejetees") + "\nSoins déclarés: "
                + afficherSoins();
        
    }
    private String afficherSoins(){
        return "\n Massothérapie: "+soins.getInt("0") 
                + "\n Ostéopathie: "+soins.getInt("100")
                + "\n Psychologie individuelle: "+soins.getInt("200")
                + "\n Soins dentaires: "+soins.getInt("300")
                + "\n Naturopathie, acuponcture: "+soins.getInt("400")
                + "\n Chiropratie: "+soins.getInt("500")
                + "\n Physiothérapie: "+soins.getInt("600")
                + "\n Orthophonie, ergothérapie: "+soins.getInt("700");
    }
}
