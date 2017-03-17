
package refund;

import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import static refund.MontantFormat.formatRemboursement;
import static refund.CalculRemboursements.calculerRemboursement;

public class Sortie {

    private Entree entree;
    private String fichierSortie;
    private JSONObject infoClient = new JSONObject();
    JSONArray liste = new JSONArray();
    static double montantMaxMensuel;

    /**
     * 
     * @param entree l'object avec l'info a sortir
     * @param fichierSortie le fichier de sortie
     */
    public Sortie(Entree entree, String fichierSortie) {
        this.entree = entree;
        this.fichierSortie = fichierSortie;
        ecrireDebut();
        ecrireReclamations();
        Entree.reclamValide();
        sortirFichier();
    }

    private void ecrireDebut() {
        Dossier dossier = entree.getDossier();
        infoClient.accumulate("dossier", dossier.getIdDossier());
        infoClient.accumulate("mois", dossier.getDate().toString());

    }

    private void ecrireReclamations() {
        Dollar total = ajouterReclamations();
        infoClient.accumulate("remboursement", liste);
        infoClient.accumulate("total", total.toString());

    }
    /**
     * 
     * @return le montant total ajouter avec celui de la reclamation
     */
    private double ajouterReclamations() {

        double total = 0;
        double montant;
        for (Reclamation reclam : entree.getListeReclamations()) {
            montant = ajouterUneReclamation(reclam);
            total = total + montant;
        }
        return total;
    }
    /**
     * 
     * @param reclam reclamation a ajouter
     * @return le montant de la reclamation a ajouter
     */
    private Dollar ajouterUneReclamation(Reclamation reclam) {
        JSONObject temp = new JSONObject();
        Dollar montant = calculerRemboursement(reclam, entree.getDossier());

        temp.accumulate("soin", reclam.getSoin());
        temp.accumulate("date", reclam.getDate().toString());
        temp.accumulate("montant", montant.toString());
        Entree.ajoutSoinStats(reclam.getSoin());
        liste.add(temp);
        temp.clear();

        return montant;
    }

    private void sortirFichier() {
        try {
            Utf8File.saveStringIntoFile(fichierSortie, infoClient.toString(2));
        } catch (IOException e) {
            System.out.println("Erreur avec le fichier de sortie : "
                    + e.getLocalizedMessage());
        }
    }
    public static void sauverStats() {
        try {
            Utf8File.saveStringIntoFile("Statistiques.json", 
                            Entree.stats.toString(2));
        } catch (IOException e) {
            System.out.println("Erreur avec le fichier de sortie : "
                    + e.getLocalizedMessage());
        }
    }
}
