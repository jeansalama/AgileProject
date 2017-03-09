package refund;

import java.io.IOException;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

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

        try {

            Entree entree = new Entree(args[0]);
            Sortie sortie = new Sortie(entree, args[1]);

        } catch (ContratException | DateException | DossierException 
                | ReclamationException e) {
            erreurDonnees(e, args[1]);

        } catch (JSONException j) {
            erreurJson(j, args[1]);

        }
    }

    private static void erreurDonnees(Exception e, String fichier) {
        JSONObject erreur = new JSONObject();
        erreur.accumulate("message", e.getMessage());
        try {
            Utf8File.saveStringIntoFile(fichier, erreur.toString(2));
        } catch (IOException ect) {
            System.out.println("Erreur avec le fichier de sortie : "
                    + ect.getLocalizedMessage());
        }
    }

    private static void erreurJson(JSONException e, String fichier) {
        JSONObject erreur = new JSONObject();
        erreur.accumulate("message",
                retourProprieteManquantes(e.getMessage()));
        try {
            Utf8File.saveStringIntoFile(fichier, erreur.toString(2));
        } catch (IOException ioe) {
            System.out.println("Erreur avec le fichier de sortie : "
                    + ioe.getLocalizedMessage());
        }
    }

    private static String retourProprieteManquantes(String msgJsonException) {

        int debutChainePropriete = msgJsonException.indexOf("\"") + 1;
        int finChainePropriete = msgJsonException.lastIndexOf("\"");
        
        String propriete = msgJsonException.substring(debutChainePropriete,
                finChainePropriete);
        if (syntaxeJsonNonValide(msgJsonException)) {
            return "Erreur dans le fichier d'entree.";
        } else {
            return "La propriete " + propriete + " est manquante.";
        }
    }

    private static boolean syntaxeJsonNonValide(String msgJsonException) {

        int debutChainePropriete = msgJsonException.indexOf("\"") + 1;
        int finChainePropriete = msgJsonException.lastIndexOf("\"");

        return debutChainePropriete == -1 || finChainePropriete == -1
                || msgJsonException.substring(0, 8).equals("Expected")
                || msgJsonException.substring(0, 7).equals("Missing")
                || msgJsonException.substring(0, 12).equals("Unterminated");
    }

}
