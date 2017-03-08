/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import static refund.CalculRemboursements.calculerRemboursement;
import static refund.MontantFormat.formatRemboursement;

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
        ecrireReclamations();
        sortirFichier();
    }
    
    private void ecrireDebut(){
        
        infoClient.accumulate("dossier", entree.getDossier().getDossier());
        infoClient.accumulate("mois", entree.getDossier().getDate().toString());
        
    }
    private void ecrireReclamations(){
        String total = formatRemboursement(ajouterReclamations());       
        infoClient.accumulate("remboursement", liste);
        infoClient.accumulate("total", total);
        
    }
    
    private double ajouterReclamations(){
        
        double total = 0;
        double montant;
        for (Reclamation reclam : entree.getReclamation()) {
            montant = ajouterUneReclamation(reclam);
            total = total + montant; 
        }
        return total;
    }

    private double ajouterUneReclamation(Reclamation reclam) {
        JSONObject temp = new JSONObject();
        double montant = calculerRemboursement(reclam, entree.getDossier());
        
        temp.accumulate("soin", reclam.getSoin());
        temp.accumulate("date", reclam.getDate().toString());
        temp.accumulate("montant", formatRemboursement(montant));
        
        liste.add(temp);
        temp.clear();
        
        return montant;
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
