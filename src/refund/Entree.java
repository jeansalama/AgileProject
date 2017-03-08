
package refund;

import java.io.IOException;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;


public class Entree {

    private ArrayList<Reclamation> reclamations = new ArrayList<>(0);
    private JSONObject infoClient;
    private Date mois;
    private Dossier client;
    private Contrat contrat;

    public Entree(String fichierEntree) throws DateException, DossierException,
            ContratException, ReclamationException {

        infoClient = (JSONObject) JSONSerializer.toJSON(fichierEntree);
        Dossier client = new Dossier(infoClient.getString("dossier"),
                new Date(infoClient.getString("mois")));
        Contrat contrat = new Contrat(client.getType());
        if (mois.contientUnJour()) {
            throw new ReclamationException();
        }
        setListeReclamation();
    }

    public ArrayList<Reclamation> getReclamation() {
        return reclamations;
    }

    public Dossier getDossier() {
        return client;
    }
    
    public Date getMois(){
        return mois;
    }

    public void setListeReclamation()
            throws DateException, ReclamationException {
        JSONArray tableau = infoClient.getJSONArray("reclamations");
        for (int i = 0; i < tableau.size(); i++) {

            JSONObject item = tableau.getJSONObject(i);
            Date date = new Date(item.getString("date"));
            validationBonMois(date);
            reclamations.add(new Reclamation(item.getInt("soin"),
                    date, item.getString("montant")));
        }
    }

    private void validationBonMois(Date date) throws ReclamationException {
        if (!mois.getMois().equals(date.getMois())
                || !mois.getAnnee().equals(date.getAnnee())) {
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
    public static String readFile(String fileName) {
        String jsonTxt = null;
        try {
            jsonTxt = Utf8File.loadFileIntoString(fileName);

        } catch (IOException ex) {
            System.out.println("Erreur lors de la lecture du fichier JSON. "
                    + ex.getLocalizedMessage());
            System.exit(1);
        }
        return jsonTxt;
    }
}
