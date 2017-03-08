package refund;

import java.io.FileNotFoundException;
import java.io.IOException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 *
 * @author Jean Salama
 */
public class CalculRemboursements {

    private static final JSONObject REGLES_REMBOURSEMENT
            = chargerReglesRemboursement();

    private static JSONObject chargerReglesRemboursement() {
        String stringJson;
        JSONObject regles = new JSONObject();
        try {
            stringJson = Utf8File.loadFileIntoString("Contrats.json");
            regles = (JSONObject) JSONSerializer.toJSON(stringJson);
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return regles;
    }

    public static double calculerRemboursement(Reclamation reclam,
            Dossier dossier) {
        Contrat contrat = dossier.getContrat();

        return calculerRemboursement(reclam, contrat);
    }

    public static double calculerRemboursement(Reclamation reclam,
            Contrat contrat) {

        double montantReclamation = reclam.getMontantReclamationDouble();
        JSONObject regle = extraireUneRegle(reclam, contrat);

        double remboursement
                = obtenirRemboursementMaximal(regle, montantReclamation);

        return remboursement;
    }

    private static double obtenirRemboursementMaximal(JSONObject regle,
            double montantReclamation) {
        double tauxRemb = obtenirTauxRemboursement(regle);
        double remboursement = montantReclamation * tauxRemb;

        if (regle.has("max") && remboursement > regle.getDouble("max")) {
            remboursement = regle.getDouble("max");
        }
        return remboursement;
    }

    private static double obtenirTauxRemboursement(JSONObject regle) {
        return regle.getDouble("taux");
    }

    private static JSONObject extraireUneRegle(Reclamation reclam,
            Contrat contrat) {
        String soin = obtenirSoin(reclam);
        JSONObject reglesParSoin = REGLES_REMBOURSEMENT.getJSONObject(soin);

        JSONObject regle = reglesParSoin.getJSONObject(contrat.getType());

        return regle;
    }

    private static String obtenirSoin(Reclamation reclam) {
        String soin = reclam.getSoin() + "";

        if (reclam.getSoin() >= 300 && reclam.getSoin() < 400) {
            soin = "300";
        }
        return soin;
    }

}
