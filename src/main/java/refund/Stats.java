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

    public static final String[] TITRES = {"\n Massothérapie (0) : ",
        "\n Ostéopathie (100) : ", "\n Kinésithérapie (150) : ",
        "\n Médecin généraliste privé (175) : ",
        "\n Psychologie individuelle (200) : ",
        "\n Soins dentaires (300-399) : ",
        "\n Naturopathie, acuponcture (400) : ", "\n Chiropratie (500) : ",
        "\n Physiothérapie (600) : ", "\n Orthophonie, ergothérapie (700) : "};
    public static final String[] LISTE_SOIN = {"0", "100", "150", "175", "200",
        "300", "400", "500", "600", "700"};
    private JSONObject reclamations;
    private JSONObject soins;
    public static JSONObject stats = chargerStats();

    public Stats() {

        reclamations = stats.getJSONObject("Reclamations");
        soins = stats.getJSONObject("Soins");
    }

    public static void reclamRejete() {
        JSONObject reclamations = stats.getJSONObject("Reclamations");
        int total = reclamations.getInt("rejetees");
        total++;
        reclamations.put("rejetees", total);
        sauverStats();
    }

    public static void reclamValide() {
        JSONObject reclamations = stats.getJSONObject("Reclamations");
        int total = reclamations.getInt("traitees");
        total++;
        reclamations.put("traitees", total);
        sauverStats();
    }

    /**
     *
     * @param numeroSoin entier representant le soin a ajoute au Statistiques
     * @param montantReclame
     */
    public static void ajoutSoinStats(int numeroSoin, Dollar montantReclame) {
        JSONObject soins = stats.getJSONObject("Soins");
        JSONObject soin;
        if (numeroSoin >= 300 && numeroSoin < 400) {
            numeroSoin = 300;
        }
        String typeSoin = numeroSoin + "";
        soin = soins.getJSONObject(typeSoin);

        incrementerNbrTotalPourUnSoin(soin);

        changerMontantMaximal(soin, montantReclame);

        changerTotalMontants(soin, montantReclame);

        sauverStats();
    }

    public static void incrementerNbrTotalPourUnSoin(JSONObject soin) {
        int total = soin.getInt("nbTotal");
        total++;
        soin.put("nbTotal", total);
    }

    public static void changerTotalMontants(JSONObject soin,
            Dollar montantReclame) {
        Dollar totalMontants
                = new Dollar(soin.getDouble("totalMontantsReclames"));
        totalMontants = totalMontants.plus(montantReclame);
        soin.put("totalMontantsReclames", totalMontants.convertirEnDouble());
    }

    public static void changerMontantMaximal(JSONObject soin,
            Dollar montantReclame) {
        Dollar montantMax
                = new Dollar(soin.getDouble("montantMaximalReclame"));

        if (montantMax.estInferieurA(montantReclame)) {
            montantMax = montantReclame;
        }

        soin.put("montantMaximalReclame", montantMax.convertirEnDouble());
    }

    public static void viderStats() {
        viderStats(stats);
    }

    public static void viderStats(JSONObject stats) {
        viderStatsReclamations(stats.getJSONObject("Reclamations"));
        viderStatsSoins(stats.getJSONObject("Soins"));

        sauverStats();

        System.out.println("Le fichier contenant les statistiques a été "
                + "réinitialisé correctement.");
    }

    public static void viderStatsReclamations(JSONObject reclamations) {
        String[] listeReclam = {"traitees", "rejetees"};
        for (String element : listeReclam) {
            reclamations.put(element, 0);
        }
    }

    public static void viderStatsSoins(JSONObject soins) {
        JSONObject soin;

        for (String element : LISTE_SOIN) {
            soin = new JSONObject();
            soin.accumulate("nbTotal", 0);
            soin.accumulate("montantMaximalReclame", 0.0);
            soin.accumulate("totalMontantsReclames", 0.0);

            soins.put(element, soin);
        }

    }

    public static JSONObject initialiserStats() {

        JSONObject stats = new JSONObject();
        JSONObject reclamations = initialiserStatsReclamations();
        stats.accumulate("Reclamations", reclamations);
        JSONObject soins = initialiserStatsSoins();
        stats.accumulate("Soins", soins);

        return stats;
    }

    /**
     *
     * @return JSONObject modelisant les statistiques des soins
     */
    public static JSONObject initialiserStatsSoins() {
        JSONObject soins = new JSONObject();
        JSONObject soin;

        for (String element : LISTE_SOIN) {
            soin = new JSONObject();
            soin.accumulate("nbTotal", 0);
            soin.accumulate("montantMaximalReclame", 0.0);
            soin.accumulate("totalMontantsReclames", 0.0);

            soins.accumulate(element, soin);
        }
        return soins;
    }

    /**
     *
     * @return JSONObject modelisant les statistiques des reclamations
     */
    public static JSONObject initialiserStatsReclamations() {
        JSONObject reclamations = new JSONObject();
        String[] listeReclam = {"traitees", "rejetees"};
        for (String element : listeReclam) {
            reclamations.accumulate(element, 0);
        }

        return reclamations;
    }

    public static JSONObject chargerStats() {
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
                + afficherSoins(soins);

    }

    public static String afficherSoins(JSONObject soins) {
        String temp = "";
        for (int i = 0; i < TITRES.length; i++) {
            temp = temp + TITRES[i]
                    + afficherUnSoin(soins.getJSONObject(LISTE_SOIN[i]));
        }
        return temp;
    }

    public static String afficherUnSoin(JSONObject soin) {

        int total = soin.getInt("nbTotal");
        Dollar montantMax = new Dollar(soin.getDouble("montantMaximalReclame"));
        double totalMontants = soin.getDouble("totalMontantsReclames");
        Dollar moyenneMontants = new Dollar();

        if (total != 0) {
            moyenneMontants = new Dollar(totalMontants / total);
        }

        return "\n    total : " + total
                + "\n    montant maximal réclamé : " + montantMax
                + "\n    moyenne des montants réclamés : " + moyenneMontants;
    }
}
