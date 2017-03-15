package refund;

import java.io.IOException;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class Refund {

    /**
     * @param args le noms des fichiers utilises
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Saisie invalide");
        } else if (args.length == 1) {
            entreeConsole(args);

        } else {
            try {

                Entree entree = new Entree(args[0]);
                Sortie sortie = new Sortie(entree, args[1]);

            } catch (ContratException | DateException | DossierException
                    | ReclamationException e) {
                Entree.reclamRejete();
                erreurDonnees(e, args[1]);

            } catch (JSONException j) {
                erreurJson(j, args[1]);

            }
        }

    }

    private static void entreeConsole(String[] args) {

        if (args[0].equals("-S")) {
            System.out.println(new Stats());
        } else if (args[0].equals("-SR")) {
            Entree.viderStats();
        }

    }

    /**
     *
     * @param e une exception qui a ete lancee
     * @param fichier le nom du fichier de sortie
     */
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

    /**
     *
     * @param e une exception qui a ete lancee
     * @param fichier le nom du fichier de sortie
     */
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

    /**
     *
     * @param msgJsonException le message d'exception
     * @return le message pour la propriete Json appropriee
     */
    private static String retourProprieteManquantes(String msgJsonException) {

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
    private static boolean proprieteInexistante(String msgJsonException) {

        int debutChainePropriete = msgJsonException.indexOf("\"") + 1;
        int finChainePropriete = msgJsonException.lastIndexOf("\"");

        return debutChainePropriete == -1 || finChainePropriete == -1
                || msgJsonException.substring(0, 8).equals("Expected")
                || msgJsonException.substring(0, 7).equals("Missing")
                || msgJsonException.substring(0, 12).equals("Unterminated");
    }

}
