/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import java.io.IOException;

import java.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 *
 * @author Maxime
 */
public class Refund {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Saisie invalide");
            System.exit(2);
        }
        ArrayList<Reclamation> reclamations = new ArrayList<>(0);
        try {
            String loadString = readFile(args[0]);

            JSONObject infoClient = (JSONObject) JSONSerializer.toJSON(loadString);
            JSONArray tableau = infoClient.getJSONArray("reclamations");

            Contrat contrat = new Contrat(infoClient.getString("contrat"));
            Date mois = new Date(infoClient.getString("mois"));
            if (mois.contientUnJour()) {
                throw new ReclamationException();
            }
            Client client = new Client(infoClient.getString("client"),
                    contrat, mois);

            for (int i = 0; i < tableau.size(); i++) {

                JSONObject item = tableau.getJSONObject(i);
                Date date = new Date(item.getString("date"));

                if (!mois.getMois().equals(date.getMois())
                        || !mois.getAnnee().equals(date.getAnnee())) {
                    throw new ReclamationException("Les reclamations doivent "
                            + "etre du meme mois");
                }

                reclamations.add(new Reclamation(item.getInt("soin"),
                        date, item.getString("montant")));
            }

            writeFile(client, reclamations, args[1]);

        } catch (ContratException e) {
            JSONObject erreur = new JSONObject();
            erreur.accumulate("message", "Données contrat invalides");
            try {
                Utf8File.saveStringIntoFile(args[1], erreur.toString(2));
            } catch (IOException ect) {
                System.out.println("Erreur avec le fichier de sortie : " + ect.getLocalizedMessage());
            }
        } catch (DateException d) {
            JSONObject erreur = new JSONObject();
            erreur.accumulate("message", "Données date invalides");
            try {
                Utf8File.saveStringIntoFile(args[1], erreur.toString(2));
            } catch (IOException dio) {
                System.out.println("Erreur avec le fichier de sortie : " + dio.getLocalizedMessage());
            }
        } catch (ClientException c) {
            JSONObject erreur = new JSONObject();
            erreur.accumulate("message", "Données client invalides");
            try {
                Utf8File.saveStringIntoFile(args[1], erreur.toString(2));
            } catch (IOException clio) {
                System.out.println("Erreur avec le fichier de sortie : " + clio.getLocalizedMessage());
            }
        } catch (ReclamationException e) {
            JSONObject erreur = new JSONObject();
            erreur.accumulate("message", "Données reclamantion invalides");
            try {
                Utf8File.saveStringIntoFile(args[1], erreur.toString(2));
            } catch (IOException rio) {
                System.out.println("Erreur avec le fichier de sortie : " + rio.getLocalizedMessage());
            }
        } catch (JSONException j) {
            JSONObject erreur = new JSONObject();
            erreur.accumulate("message", retourProprieteManquantes(j.getMessage()));
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
     * @param reclamations tableau ArrayList
     * @param client infos du client
     * @param fileName nom du fichier sortant
     */
    public static void writeFile(Client client, ArrayList<Reclamation> reclamations, String fileName) {
        JSONObject infoClient = new JSONObject();
        JSONArray liste = new JSONArray();
        JSONObject temp = new JSONObject();

        infoClient.accumulate("client", client.getNumero());
        infoClient.accumulate("mois", client.getDate().toString());
        for (Reclamation reclam : reclamations) {
            temp.accumulate("soin", reclam.getSoin());
            temp.accumulate("date", reclam.getDate().toString());
            temp.accumulate("montant", client.getContrat().calculRemboursement(reclam));
            liste.add(temp);
            temp.clear();
        }

        infoClient.accumulate("remboursement", liste);

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

    /* private static String retourProprieteManquantes(String j) {
     return "La propriete "
     + j.substring(j.indexOf("\"") + 1, j.lastIndexOf("\""))
     + " est manquante.";
     }
     */
    private static String retourProprieteManquantes(String j) {

        int debutChainePropriete = j.indexOf("\"") + 1;
        int finChainePropriete = j.lastIndexOf("\"");

        if (j.substring(0, 8).equals("Expected")
                || j.substring(0, 7).equals("Missing")
                || j.substring(0, 12).equals("Unterminated")
                || debutChainePropriete == -1 && finChainePropriete == -1) {
            return "Il y a une erreur dans le fichier d'entree.";
        } else {
            return "La propriete "
                    + j.substring(debutChainePropriete, finChainePropriete)
                    + " est manquante.";
        }
    }

}
