package refund;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class Entree {

    public final static String MSG_MOIS_EXCEPTION = "Le mois est invalide. "
            + "Le format d'un mois accepte est aaaa-mm.";

    private ArrayList<Reclamation> listeReclamations = new ArrayList<>(0);
    private JSONObject infoClient;
    private Dossier client;

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

        client = new Dossier(infoClient.getString("dossier"),
                new Date(infoClient.getString("mois")));
        if (client.getDate().contientUnJour()) {
            throw new ReclamationException(MSG_MOIS_EXCEPTION);
        }
        setListeReclamations();
    }

    public ArrayList<Reclamation> getListeReclamations() {
        return listeReclamations;
    }

    public Dossier getDossier() {
        return client;
    }

    public void setListeReclamations()
            throws DateException, ReclamationException {
        JSONArray tableauReclamations = infoClient.getJSONArray("reclamations");
        for (int i = 0; i < tableauReclamations.size(); i++) {

            JSONObject reclamation = tableauReclamations.getJSONObject(i);
            ajouterUneReclamation(reclamation);
        }
    }

    /**
     *
     * @param reclamation Un object Json representant une reclamation
     * @throws DateException
     * @throws ReclamationException
     */
    private void ajouterUneReclamation(JSONObject reclamation)
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
    private int obtenirSoin(JSONObject reclam) throws ReclamationException {
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
     * paramètre n'a pas le même mois que celle du client
     */
    private void validationBonMois(Date date) throws ReclamationException {
        if (!client.getDate().getMois().equals(date.getMois())
                || !client.getDate().getAnnee().equals(date.getAnnee())) {
            throw new ReclamationException("Les reclamations doivent "
                    + "etre du meme mois");
        }
    }

    /**
     * Lit le fichier inputfile avec l'info du client
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
