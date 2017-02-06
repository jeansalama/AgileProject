/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import java.io.IOException;
import net.sf.json.*;
import java.util.ArrayList;

/**
 *
 * @author Maxime
 */
public class Refund {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Reclamation> reclamations = new ArrayList<>(0);
        try {
            String loadString = readFile(args[0]);
            JSONObject infoClient = (JSONObject) JSONSerializer.toJSON(loadString);
            JSONArray tableau = infoClient.getJSONArray("reclamations");
            Contrat contrat = new Contrat(infoClient.getString("contrat"));
            Client client = new Client(infoClient.getString("client"),
                    contrat, infoClient.getString("mois"));

            for (int i = 0; i > tableau.size(); i++) {
                JSONObject item = tableau.getJSONObject(i);
                reclamations.add(new Reclamation(item.getInt("soin"),
                        item.getString("date"), item.getString("montant")));
            }

            writeFile(client, args[1]);

        } catch (Exception j) {
            JSONObject erreur = new JSONObject();
            erreur.accumulate("message", "Données invalides");
            try {
                Utf8File.saveStringIntoFile(args[1], erreur.toString(2));
            } catch (IOException e) {
                System.out.println("Erreur avec le fichier de sortie : " + e.getLocalizedMessage());
            }
        }
    }

    /**
     * Ecrit un fichier .JSON avec les infos du client et le montant du
     * remboursement pour chaque reclamation
     *
     * @param client infos du client
     * @param fileName nom du fichier sortant
     */
    public static void writeFile(Client client, String fileName) {
        JSONObject infoClient = new JSONObject();
        infoClient.accumulate("client", client.getNumero());
        infoClient.accumulate("mois", client.getDate());
        //ajouter l'info des remboursements ici
        try {
            Utf8File.saveStringIntoFile(fileName, infoClient.toString(2));
        } catch (IOException e) {
            System.out.println("Erreur avec le fichier de sortie : " + e.getLocalizedMessage());
        }

    }

    /**
     * Lit le fichier inputfile avec l'info du client
     *
     * @param fileName fichier avec les infos
     * @return retourne un String en format .JSON
     */
    private static String readFile(String fileName) {
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
