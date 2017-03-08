/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        //if (args.length != 2) {
        //    System.out.println("Saisie invalide");
        //    System.exit(2);
        //}

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

    private static void erreurJson(Exception j, String fichier) {
        JSONObject erreur = new JSONObject();
        erreur.accumulate("message",
                retourProprieteManquantes(j.getMessage()));
        try {
            Utf8File.saveStringIntoFile(fichier, erreur.toString(2));
        } catch (IOException e) {
            System.out.println("Erreur avec le fichier de sortie : " + e.getLocalizedMessage());
        }
    }

    private static String retourProprieteManquantes(String j) {

        int debutChainePropriete = j.indexOf("\"") + 1;
        int finChainePropriete = j.lastIndexOf("\"");

        if (debutChainePropriete == -1 || finChainePropriete == -1
                || j.substring(0, 8).equals("Expected")
                || j.substring(0, 7).equals("Missing")
                || j.substring(0, 12).equals("Unterminated")) {
            return "Erreur dans le fichier d'entree.";
        } else {
            return "La propriete "
                    + j.substring(debutChainePropriete, finChainePropriete)
                    + " est manquante.";
        }
    }

}
