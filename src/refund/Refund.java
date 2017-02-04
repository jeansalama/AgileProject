/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import java.io.IOException;
import net.sf.json.*;

/**
 *
 * @author Maxime
 */
public class Refund {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Client client;
        try {
            String loadString = readFile(args[0]);
            JSONObject infoClient = (JSONObject) JSONSerializer.toJSON(loadString);
            client = getClient(infoClient);
            writeFile(client, args[1]);

        } catch (Exception j) {
            System.out.println("Commande invalide ");
        }
    }

    /**
     * Ecrit un fichier .JSON avec les infos du client et le montant du
     * remboursement pour chaque reclamation
     *
     * @param client infos du client
     * @param fileName nom du fichier sortant
     */
    private static void writeFile(Client client, String fileName) {
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

    /**
     * Recupere l'info du client du fichier .JSON
     *
     * @param fichier l'info du client
     * @return retourne un Client avec son info
     */
    private static Client getClient(JSONObject fichier) {
        JSONArray tableau = fichier.getJSONArray("reclamations");
        Client client = null;
        try {
            Contrat contrat = new Contrat(fichier.getString("contrat"));
            client = new Client(fichier.getString("client"),
                    contrat , fichier.getString("mois"));
        } catch (Exception e) {
            JSONObject erreur = new JSONObject();
            erreur.accumulate("message", "DonnÃ©es invalides");
            try {
                Utf8File.saveStringIntoFile("refunds.json", erreur.toString(2));
            } catch (IOException f) {
                System.out.println("Erreur avec le fichier de sortie : " + f.getLocalizedMessage());

            }

        }

        return client;
    }
}
