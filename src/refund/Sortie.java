/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Max
 */
public class Sortie {
    
    private Entree entree;
    private String fichierSortie;
    private JSONObject infoClient = new JSONObject();
    JSONArray liste = new JSONArray();
    
    public Sortie(Entree entree, String fichierSortie){
        this.entree = entree;
        this.fichierSortie = fichierSortie;
        ecrireDebut();
        ecrireReclamation();
        sortirFichier();
    }
    
    private void ecrireDebut(){
        
        infoClient.accumulate("client", entree.getDossier());
        infoClient.accumulate("mois", entree.getMois().toString());
        
    }
    private void ecrireReclamation(){
        ajoutReclamation();       
        infoClient.accumulate("remboursement", liste);
        
    }
    
    private void ajoutReclamation(){
        JSONObject temp = new JSONObject();
        for (Reclamation reclam : entree.getReclamation()) {
            temp.accumulate("soin", reclam.getSoin());
            temp.accumulate("date", reclam.getDate().toString());
            temp.accumulate("montant", 
                    entree.getDossier().getContrat().calculRemboursement(reclam));
            liste.add(temp);
            temp.clear();
        }
    }
    private void sortirFichier(){
         try {
            Utf8File.saveStringIntoFile(fichierSortie, infoClient.toString(2));
        } catch (IOException e) {
            System.out.println("Erreur avec le fichier de sortie : " 
                    + e.getLocalizedMessage());
        }
    }
}
