package refund;

import net.sf.json.JSONException;

public class Refund {

    private static boolean prediction;

    /**
     * @param args le noms des fichiers utilises
     */
    public static void main(String[] args) {
       if (args.length == 3 && args[0].equals("-p")) {
            entrerPrediction(args);
        } else if (args.length == 1) {
            entrerConsole(args);
        } else if (args.length == 2) {
            args = formatterNomsFichiers(args);
            analyserFichiers(args);
        } else {
            System.out.println("Saisie invalide");
        }
    }

    /**
     *
     * @param args Les arguments entres a la console
     */
    private static void entrerPrediction(String[] args) {
            prediction = true;
            args[0] = args[1];
            args[1] = args[2];
            args = formatterNomsFichiers(args);
            analyserFichiers(args);
       
    }

    /**
     *
     * @param args Les arguments entres a la console
     */
    private static void entrerConsole(String[] args) {

        if (args[0].equals("-S")) {
            System.out.println(new Stats());
        } else if (args[0].equals("-SR")) {
            Stats.viderStats();
        } else {
            System.out.println("-S pour afficher les statistiques ou -SR pour "
                    + "les réinitialiser.");
        }
    }

    /**
     *
     * @param args Les arguments entres a la console
     */
    private static void analyserFichiers(String[] args) {

        try {
            Entree entree = new Entree(args[0]);
            Sortie sortie = new Sortie(entree, args[1], prediction);

        } catch (ContratException | DateException | DossierException
                | ReclamationException e) {
            statsRejetee();
            Erreur.erreurDonnees(e, args[1]);

        } catch (JSONException j) {
            statsRejetee();
            Erreur.erreurJson(j, args[1]);

        }
    }

    private static void statsRejetee() {
        if (!prediction) {
            Stats.reclamRejete();
        }
    }

    private static String[] formatterNomsFichiers(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String temp = args[i].toLowerCase();
            if (!temp.contains(".json")) {
                args[i] = args[i] + ".json";
            }
        }
        return args;
    }
}
