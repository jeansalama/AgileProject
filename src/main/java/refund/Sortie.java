package refund;

import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import static refund.CalculRemboursements.calculerRemboursement;

public class Sortie {

    private Entree entree;
    private String fichierSortie;
    private boolean prediction;
    private JSONObject infoClient;
    JSONArray liste;
    static double montantMaxMensuel;

    /**
     *
     * @param entree l'object avec l'info a sortir
     * @param fichierSortie le fichier de sortie
     */
    public Sortie(Entree entree, String fichierSortie, boolean prediction) {
        this.entree = entree;
        this.fichierSortie = fichierSortie;
        this.prediction = prediction;
        ecrireDebut();
        ecrireReclamations();
        if(!prediction){
            Stats.reclamValide();
        }
        sortirFichier();
    }
    
    public JSONObject getInfoClient(){
        return infoClient;
    }

    public void ecrireDebut() {
        infoClient = new JSONObject();
        Dossier dossier = entree.getDossier();
        infoClient.accumulate("dossier", dossier.getIdDossier());
        infoClient.accumulate("mois", dossier.getDate().toString());

    }

    public void ecrireReclamations() {
        liste = new JSONArray();
        Dollar total = ajouterReclamations();
        infoClient.accumulate("remboursement", liste);
        infoClient.accumulate("total", total.toString());

    }

    /**
     *
     * @return le montant total ajouter avec celui de la reclamation
     */
    public Dollar ajouterReclamations() {
        Dollar total = new Dollar();
        for (Reclamation reclam : entree.getListeReclamations()) {
            Dollar montant = ajouterUneReclamation(reclam);
            total = total.plus(montant);
        }
        return total;
    }

    /**
     *
     * @param reclam reclamation a ajouter
     * @return le montant de la reclamation a ajouter
     */
    public Dollar ajouterUneReclamation(Reclamation reclam) {
        JSONObject temp = new JSONObject();
        Dollar montant = calculerRemboursement(reclam, entree.getDossier());

        temp.accumulate("soin", reclam.getSoin());
        temp.accumulate("date", reclam.getDate().toString());
        temp.accumulate("montant", montant.toString());
        if(!prediction){
            Stats.ajoutSoinStats(reclam.getSoin(), 
                    reclam.getMontantReclamation());
        }
        liste.add(temp);
        temp.clear();

        return montant;
    }

    public void sortirFichier() {
        try {
            Utf8File.saveStringIntoFile(fichierSortie, infoClient.toString(2));
        } catch (IOException e) {
            System.out.println("Erreur avec le fichier de sortie : "
                    + e.getLocalizedMessage());
        }
    }
}
