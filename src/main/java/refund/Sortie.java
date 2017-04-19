package refund;

import java.io.IOException;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import static refund.CalculRemboursements.calculerRemboursement;

public class Sortie {

    private Entree entree;
    private String fichierSortie;
    private boolean prediction;
    private JSONObject infoClient;
    private JSONArray liste;

    /**
     *
     * @param entree l'object avec l'info a sortir
     * @param fichierSortie le fichier de sortie
     */
    public Sortie(Entree entree, String fichierSortie, boolean prediction) {
        liste = new JSONArray();
        setEntree(entree);
        setFichierSortie(fichierSortie);
        setPrediction(prediction);
        setInfoClient(ecrireDebut(entree.getDossier()));
        setInfoClient(ecrireReclamations(entree.getListeReclamations(),
                entree.getDossier()));
        activerStatsDebut(prediction);
        sortirFichier();
    }
    
    public Sortie(){
        entree = new Entree();
        liste = new JSONArray();
        infoClient = new JSONObject();
        prediction = true;
       
    }
            
    public Entree getEntree(){
        return entree;
    }
    
    public JSONArray getListe(){
        return liste;
    }
    
    public String getFichierSortie(){
        return fichierSortie;
    }
    /**
     * 
     * @param entree le fichier entrant
     */    
    public void setEntree(Entree entree){
        this.entree = entree;
    }
    /**
     * 
     * @param infoClient l'information du client
     */
    public void setInfoClient(JSONObject infoClient){
        this.infoClient = infoClient;
    }
    /**
     * 
     * @param fichierSortie le nom du fichier de sortie
     */
    public void setFichierSortie(String fichierSortie){
        this.fichierSortie = fichierSortie;
    }
    
    /**
     * 
     * @param prediction true si le mode prediction est active 
     */
    public void setPrediction(boolean prediction){
        this.prediction = prediction;
    }
            
    /**
     * 
     * @param prediction True si le mode prediction est active
     * @return true si les stats ont été modifiée
     */
    public boolean activerStatsDebut(boolean prediction){
        boolean statsActive = false;
        if(!prediction){
            Stats.reclamValide();
            statsActive = true;
        }
        return statsActive;
      
    }
    /**
     * 
     * @param dossier le dossier a ecrire sur le fichier
     * @return l'objet JSON modifie
     */
    public JSONObject ecrireDebut(Dossier dossier) {
        JSONObject fichier = new JSONObject();
        fichier.accumulate("dossier", dossier.getIdDossier());
        fichier.accumulate("mois", dossier.getDate().toString());
        return fichier;

    }
    /**
     * 
     * @param reclamations Une liste de reclamations
     * @param dossier 
     * @return 
     */
    public JSONObject ecrireReclamations(ArrayList<Reclamation> reclamations,
            Dossier dossier) {
        Dollar total = ajouterReclamations(reclamations, dossier);
        infoClient.accumulate("remboursement", liste);
        infoClient.accumulate("total", total.toString());
        
        return infoClient;
    }

    /**
     *
     * @return le montant total ajouter avec celui de la reclamation
     */
    public Dollar ajouterReclamations(ArrayList<Reclamation> reclamations, 
            Dossier dossier) {
        Dollar total = new Dollar();
        for (Reclamation reclam : reclamations) {
            Dollar montant = ajouterUneReclamation(reclam, dossier);
            total = total.plus(montant);
        }
        return total;
    }

    /**
     *
     * @param reclam reclamation a ajouter
     * @return le montant de la reclamation a ajouter
     */
    public Dollar ajouterUneReclamation(Reclamation reclam, Dossier dossier) {
        JSONObject temp = new JSONObject();
        Dollar montant = calculerRemboursement(reclam, dossier);

        temp.accumulate("soin", reclam.getSoin());
        temp.accumulate("date", reclam.getDate().toString());
        temp.accumulate("montant", montant.toString());
        activerStatsReclam(reclam);
        liste.add(temp);
        temp.clear();

        return montant;
    }
    /**
     * 
     * @param reclam Une reclamation
     * @return True si les stats sont enregistrees
     */
    public boolean activerStatsReclam(Reclamation reclam){
        boolean statsActive = false;
        if(!prediction){
            Stats.ajoutSoinStats(reclam.getSoin(), 
                    reclam.getMontantReclamation());
            statsActive = true;
        }
        return statsActive;
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
