package refund;

import java.io.IOException;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class Entree {

    public final static String MSG_MOIS_EXCEPTION = "Le mois est invalide. "
            + "Le format d'un mois accepte est aaaa-mm.";

    private ArrayList<Reclamation> listeReclamations = new ArrayList<>();
    private ArrayList<String> jourNbrReclamation = new ArrayList<>();
    private JSONObject infoClient;
    private static Dossier dossier;

    /**
     *
     * @param fichierEntree Le nom du fichier detenant les donnees
     * @throws DateException
     * @throws DossierException
     * @throws ContratException
     * @throws ReclamationException
     */
    public Entree(String fichierEntree) throws DateException, DossierException,
            ContratException, ReclamationException {
        infoClient = (JSONObject) JSONSerializer.toJSON(
                lireFichier(fichierEntree));

        dossier = new Dossier(infoClient.getString("dossier"),
                new Date(infoClient.getString("mois")));
        if (dossier.getDate().contientUnJour()) {
            throw new ReclamationException(MSG_MOIS_EXCEPTION);
        }
        setListeReclamations();
    }

    public Entree() throws DateException, DossierException {
        dossier = new Dossier("A123456", new Date("2017-01-01"));
    }

    public ArrayList<Reclamation> getListeReclamations() {
        return listeReclamations;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setInfoClient(JSONObject infoClient) {
        this.infoClient = new JSONObject();
        this.infoClient = infoClient;
    }

    public void setListeReclamations()
            throws DateException, ReclamationException {
        JSONArray tableauReclamations = infoClient.getJSONArray("reclamations");

        for (int i = 0; i < tableauReclamations.size(); i++) {

            JSONObject reclamation = tableauReclamations.getJSONObject(i);

            Date dateJour = new Date(reclamation.getString("date"));
            jourNbrReclamation.add(dateJour.getJour());

            ajouterUneReclamation(reclamation);
        }

        validerNbrJoursAccepteDansReclamation(jourNbrReclamation);
    }

    public static void validerNbrJoursAccepteDansReclamation(
            ArrayList<String> jourNbrReclamation) throws ReclamationException {
        
        for (int i = 0; i < jourNbrReclamation.size(); i++) {
            String jour = jourNbrReclamation.get(i);
            validerNbrJoursReclames(jourNbrReclamation, jour);
        }
    }

    public static void validerNbrJoursReclames(ArrayList<String> jourNbrReclamation1, 
            String jour) throws ReclamationException {
        int compteur = 0;
        for (int i = 0; i < jourNbrReclamation1.size(); i++) {
            if (jour.equals(jourNbrReclamation1.get(i))) {
                compteur = compteur + 1;
            }
            if (compteur > 4) {
                throw new ReclamationException(
                        "Vous avez plus de 4 reclamations pour la date : "
                        + dossier.getDate() + "-" + jour);
            }
        }
    }

    /**
     *
     * @param reclamation Un object Json representant une reclamation
     * @throws DateException
     * @throws ReclamationException
     */
    public void ajouterUneReclamation(JSONObject reclamation)
            throws DateException, ReclamationException {
        int soin = obtenirSoin(reclamation);
        Date date = new Date(reclamation.getString("date"));
        validationBonMois(date);
        String montant = reclamation.getString("montant");
        listeReclamations.add(new Reclamation(soin, date, montant));
    }

    /**
     *
     * @param reclam Un object Json representant une reclamation
     * @return le numero du soin de la reclamation prise en parametre
     * @throws ReclamationException
     */
    public static int obtenirSoin(JSONObject reclam) throws ReclamationException {
        int soin;
        if (reclam.containsKey("soin")) {
            try {
                soin = reclam.getInt("soin");
            } catch (JSONException e) {
                throw new ReclamationException("La donnee soin est invalide !");
            }
        } else {
            throw new ReclamationException("La propriete soin est manquante.");
        }
        return soin;
    }

    /**
     *
     * @param date Une date formatter
     * @throws ReclamationException si la date de la reclamation prise en
     * parametre n'a pas le meme mois que celle du dossier
     */
    public void validationBonMois(Date date) throws ReclamationException {
        if (!dossier.getDate().getMois().equals(date.getMois())
                || !dossier.getDate().getAnnee().equals(date.getAnnee())) {
            throw new ReclamationException("Les reclamations doivent "
                    + "etre du meme mois");
        }
    }

    /**
     * Lit le fichier inputfile avec l'info du dossier
     *
     * @param fileName fichier avec les infos
     * @return retourne un String en format .JSON
     */
    public static String lireFichier(String fileName) {
        String jsonTxt = null;
        try {
            jsonTxt = Utf8File.loadFileIntoString(fileName);
        } catch (IOException ex) {
            System.out.println("Erreur lors de la lecture du fichier JSON. "
                    + ex.getLocalizedMessage());
        }
        return jsonTxt;
    }
}
