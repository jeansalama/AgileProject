package refund;

import java.io.FileNotFoundException;
import java.io.IOException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import static refund.Sortie.montantMaxMensuel;

/**
 *
 * @author Jean Salama
 */
public class CalculRemboursements {

    private static final JSONObject REGLES_REMBOURSEMENT
            = chargerReglesRemboursement();

    /**
     * @return JSONObject, "modelisant" les regles du contrat
     */
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

    /**
     * @param reclam une reclamation du dossier client
     * @param dossier le dossier du client
     * @return double, le remboursement de cette reclamation
     */
    public static double calculerRemboursement(Reclamation reclam,
            Dossier dossier) {
        Contrat contrat = dossier.getContrat();

        return calculerRemboursement(reclam, contrat);
    }

    /**
     * @param reclam une reclamation du dossier client
     * @param contrat le contrat du client
     * @return double, le remboursement (maximal) de cette reclamation
     */
    public static double calculerRemboursement(Reclamation reclam,
            Contrat contrat) {

        double montantReclamation = reclam.getMontantReclamationDouble();
        JSONObject regle = extraireUneRegle(reclam, contrat);

        double remboursement
                = obtenirRemboursementMaximal(regle, montantReclamation);

        return remboursement;
    }

    /**
     * @param regle une regle de remboursement specifique
     * @param montantReclamation
     * @return double, le remboursement maximal etant donne le montant de
     * reclamation fourni et la regle de rembourssement
     */
    private static double obtenirRemboursementMaximal(JSONObject regle,
            double montantReclamation) {
        double tauxRemb = obtenirTauxRemboursement(regle);
        double remboursement = montantReclamation * tauxRemb;

        if (regle.has("max") && remboursement > regle.getDouble("max")) {
            remboursement = regle.getDouble("max");
        }
        remboursement = calculerRemboursementMaximalMensuel(regle, remboursement);
        return remboursement;
    }

    private static double calculerRemboursementMaximalMensuel
        (JSONObject regle, double remboursement) {
            
        if (regle.has("maxMensuel")) {
            montantMaxMensuel = regle.getDouble("maxMensuel");

            if (remboursement <= montantMaxMensuel) {
                montantMaxMensuel = montantMaxMensuel - remboursement;
            } else {
                remboursement = remboursement - montantMaxMensuel;
            }
        }
        return remboursement;
    }

    /**
     * @param regle une regle de remboursement specifique
     * @return double, le taux de remboursement selon cette regle
     */
    private static double obtenirTauxRemboursement(JSONObject regle) {
        return regle.getDouble("taux");
    }

    /**
     * @param reclam une reclamation du dossier client
     * @param contrat le contrat du client
     * @return JSONObject, une regle specifique au type de contrat et de la
     * reclamtion donnee
     */
    private static JSONObject extraireUneRegle(Reclamation reclam,
            Contrat contrat) {
        String soin = obtenirSoin(reclam);
        JSONObject reglesParSoin = REGLES_REMBOURSEMENT.getJSONObject(soin);

        JSONObject regle = reglesParSoin.getJSONObject(contrat.getType());

        return regle;
    }

    /**
     * @param reclam une reclamation du dossier client
     * @return String, le type de soin de la reclamation
     */
    private static String obtenirSoin(Reclamation reclam) {
        String soin = reclam.getSoin() + "";

        if (reclam.getSoin() >= 300 && reclam.getSoin() < 400) {
            soin = "300";
        }
        return soin;
    }

}
