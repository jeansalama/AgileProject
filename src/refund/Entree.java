
package refund;

import java.io.IOException;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;


public class Entree {

    public final static String MSG_MOIS_EXCEPTION = "Mois invalide. "
            + "Format d'un mois valide: aaaa-mm.";
    
    private ArrayList<Reclamation> listeReclamations = new ArrayList<>(0);
    private JSONObject infoClient;
    private Dossier client;
   
    public Entree(String fichierEntree) throws DateException, DossierException,
            ContratException, ReclamationException {

        infoClient = (JSONObject)JSONSerializer.toJSON(lireFichier(fichierEntree));
        
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
            Date date = new Date(reclamation.getString("date"));
            validationBonMois(date);
            ajouterUneReclamation(reclamation, date);
        }
    }

    private void ajouterUneReclamation(JSONObject reclamation, Date date) 
            throws ReclamationException {
        int soin;
        String msg;
        
        if(reclamation.containsKey("soin")){
            try{
                soin = reclamation.getInt("soin");
            } catch(JSONException e){
                msg = e.getMessage();
                throw new ReclamationException("La donnee soin est invalide !" + msg);
            }
        } else {
            throw new ReclamationException("La propriete soin est manquante!!!");
        }
        
        String montant = reclamation.getString("montant");
        listeReclamations.add(new Reclamation(soin, date, montant));
    }

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
            System.exit(1);
        }
        return jsonTxt;
    }
}
