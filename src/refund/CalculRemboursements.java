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

    private static final JSONObject REGLES_REMBOURSEMENT = chargerReglesRemboursement();

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

    public static String calculRemboursement(Reclamation reclam,
            Contrat contrat) {
        double remboursement;
        double montantReclamation = reclam.getMontantReclamationDouble();
        String soin = reclam.getSoin() + "";

        if (reclam.getSoin() >= 300 && reclam.getSoin() < 400) {
            soin = "300";
        }

        JSONObject regle
                = REGLES_REMBOURSEMENT.getJSONObject(soin).getJSONObject(contrat.getType());
        double tauxRemb = regle.getDouble("taux");

        remboursement = montantReclamation * tauxRemb;

        if (regle.has("max") && remboursement > regle.getDouble("max")) {
            remboursement = regle.getDouble("max");
        }

        return MontantFormat.formatRemboursement(remboursement);
    }

}
