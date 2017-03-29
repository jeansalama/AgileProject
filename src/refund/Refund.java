package refund;

import net.sf.json.JSONException;

public class Refund {

    /**
     * @param args le noms des fichiers utilises
     */
    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Saisie invalide");
        } else if (args.length == 1) {
            entreeConsole(args);

        } else {
            analyserFichiers(args);
        }
    }

    /**
     *
     * @param args Les arguments entres a la console
     */
    private static void entreeConsole(String[] args) {

        if (args[0].equals("-S")) {
            System.out.println(new Stats());
        } else if (args[0].equals("-SR")) {
            Stats.viderStats();
        }

    }

    /**
     *
     * @param args Les arguments entres a la console
     */
    private static void analyserFichiers(String[] args) {

        try {
            Entree entree = new Entree(args[0]);
            Sortie sortie = new Sortie(entree, args[1]);

        } catch (ContratException | DateException | DossierException
                | ReclamationException e) {
            Stats.reclamRejete();
            Erreur.erreurDonnees(e, args[1]);

        } catch (JSONException j) {
            Stats.reclamRejete();
            Erreur.erreurJson(j, args[1]);

        }
    }
}
