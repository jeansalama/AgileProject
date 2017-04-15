package refund;

import java.io.IOException;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 *
 * @author Max
 */
public class Erreur {

    /**
     *
     * @param e une exception qui a ete lancee
     * @param fichier le nom du fichier de sortie
     */
    public static String erreurDonnees(Exception e, String fichier) {
        JSONObject erreur = new JSONObject();
        erreur.accumulate("message", e.getMessage());
        String contenuErreur = erreur.toString(2);
        try {
            Utf8File.saveStringIntoFile(fichier, contenuErreur);
        } catch (IOException ect) {
            System.out.println("Erreur avec le fichier de sortie : "
                    + ect.getLocalizedMessage());
        }
        return contenuErreur;
    }

    /**
     *
     * @param e une exception qui a ete lancee
     * @param fichier le nom du fichier de sortie
     */
    public static String erreurJson(JSONException e, String fichier) {
        JSONObject erreur = new JSONObject();
        erreur.accumulate("message",
                retourProprieteManquantes(e.getMessage()));
        String contenuErreur = erreur.toString(2);
        try {
            Utf8File.saveStringIntoFile(fichier, contenuErreur);
        } catch (IOException ioe) {
            System.out.println("Erreur avec le fichier de sortie : "
                    + ioe.getLocalizedMessage());
        }
        return contenuErreur;
    }

    /**
     *
     * @param msgJsonException le message d'exception
     * @return le message pour la propriete Json appropriee
     */
    public static String retourProprieteManquantes(String msgJsonException) {

        int debutChainePropriete = msgJsonException.indexOf("\"") + 1;
        int finChainePropriete = msgJsonException.lastIndexOf("\"");
        String msg;
        String propriete;
        if (proprieteInexistante(msgJsonException)) {
            msg = "Erreur dans le fichier d'entree.";
        } else {
            propriete = msgJsonException.substring(debutChainePropriete,
                    finChainePropriete);
            msg = "La propriete " + propriete + " est manquante.";
        }
        return msg;
    }

    /**
     *
     * @param msgJsonException
     * @return vrai si la propriete Json existe
     */
    public static boolean proprieteInexistante(String msgJsonException) {

        int debutChainePropriete = msgJsonException.indexOf("\"") + 1;
        int finChainePropriete = msgJsonException.lastIndexOf("\"");

        return debutChainePropriete == -1 || finChainePropriete == -1
                || msgJsonException.substring(0, 8).equals("Expected")
                || msgJsonException.substring(0, 7).equals("Missing")
                || msgJsonException.substring(0, 12).equals("Unterminated");
    }
}
