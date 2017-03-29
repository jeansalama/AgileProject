package refund;

import java.io.FileNotFoundException;
import java.io.IOException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 *
 * @author Max
 */
public class Stats {

    private JSONObject reclamations;
    private JSONObject soins;
    public static JSONObject stats = chargerStats();

    public Stats() {

        reclamations = stats.getJSONObject("Reclamations");
        soins = stats.getJSONObject("Soins");
    }

    public static void reclamRejete() {
        JSONObject temp = stats.getJSONObject("Reclamations");
        int total = temp.getInt("rejetees");
        total++;
        temp.put("rejetees", total);
        sauverStats();
    }

    public static void reclamValide() {
        JSONObject temp = stats.getJSONObject("Reclamations");
        int total = temp.getInt("traitees");
        total++;
        temp.put("traitees", total);
        sauverStats();
    }

    /**
     *
     * @param soin entier representant le soin a ajoute au Statistiques
     */
    public static void ajoutSoinStats(int soin) {
        JSONObject temp = stats.getJSONObject("Soins");
        if (soin >= 300 && soin < 400) {
            soin = 300;
        }
        String type = soin + "";
        int total = temp.getInt(type);
        total++;
        temp.put(type, total);
        sauverStats();
    }

    public static void viderStats() {
        String[] listeReclam = {"traitees", "rejetees"};
        String[] listeSoin = {"0", "100", "150", "175", "200", "300", "400",
            "500", "600", "700"};

        for (String element : listeReclam) {
            stats.getJSONObject("Reclamations").put(element, 0);
        }
        for (String element : listeSoin) {
            stats.getJSONObject("Soins").put(element, 0);
        }
        sauverStats();
        System.out.println("Le fichier contenant les statistiques a été "
                + "réinitialisé correctement.");
    }

    private static JSONObject initialiserStats() {

        JSONObject stats = new JSONObject();

        initialiserStatsReclamations(stats);
        initialiserStatsSoins(stats);

        return stats;
    }

    /**
     *
     * @param stats l'objets .json representant les statistiques
     */
    private static void initialiserStatsSoins(JSONObject stats) {
        JSONObject soins = new JSONObject();
        String[] listeSoin = {"0", "100", "150", "175", "200", "300", "400",
            "500", "600", "700"};
        for (String element : listeSoin) {
            soins.accumulate(element, 0);
        }
        stats.accumulate("Soins", soins);
    }

    /**
     *
     * @param stats l'objets .json representant les statistiques
     */
    private static void initialiserStatsReclamations(JSONObject stats) {
        JSONObject reclamations = new JSONObject();
        String[] listeReclam = {"traitees", "rejetees"};
        for (String element : listeReclam) {
            reclamations.accumulate(element, 0);
        }
        stats.accumulate("Reclamations", reclamations);
    }

    private static JSONObject chargerStats() {
        String contenu;
        JSONObject stats = new JSONObject();
        try {
            contenu = Utf8File.loadFileIntoString("Statistiques.json");
            stats = (JSONObject) JSONSerializer.toJSON(contenu);
        } catch (FileNotFoundException fnfe) {
            stats = initialiserStats();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return stats;
    }

    public static void sauverStats() {
        try {
            Utf8File.saveStringIntoFile("Statistiques.json",
                    Stats.stats.toString(2));
        } catch (IOException e) {
            System.out.println("Erreur avec le fichier de sortie : "
                    + e.getLocalizedMessage());
        }
    }

    @Override
    public String toString() {
        return "Statistiques: \n Réclamations traitées: "
                + reclamations.getInt("traitees") + "\n Réclamations rejetées: "
                + reclamations.getInt("rejetees") + "\nSoins déclarés: "
                + afficherSoins();

    }

    private String afficherSoins() {
        return "\n Massothérapie: " + soins.getInt("0")
                + "\n Ostéopathie: " + soins.getInt("100")
                + "\n Kinésithérapie: " + soins.getInt("150")
                + "\n Médecin généraliste privé: " + soins.getInt("175")
                + "\n Psychologie individuelle: " + soins.getInt("200")
                + "\n Soins dentaires: " + soins.getInt("300")
                + "\n Naturopathie, acuponcture: " + soins.getInt("400")
                + "\n Chiropratie: " + soins.getInt("500")
                + "\n Physiothérapie: " + soins.getInt("600")
                + "\n Orthophonie, ergothérapie: " + soins.getInt("700");
    }
}
