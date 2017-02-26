package refund;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

/**
 * Classe qui gere les formats des montants d'assurances
 *
 * @author Jean Salama
 */
public class MontantFormat {

    public static String formatRemboursement(double montant) {

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');

        DecimalFormat df = new DecimalFormat("0.00$", symbols);

        return df.format(montant);
    }

    public static double analyserReclamation(String montantReclamation)
            throws ReclamationException {

        double montant;
        DecimalFormat analyseur = obtenirBonAnalyseur(montantReclamation);

        try {
            montant = analyseur.parse(montantReclamation).doubleValue();
        } catch (ParseException pe) {
            throw new ReclamationException("Le format du montant de reclamation"
                    + " est incorrect. Il doit être de la forme"
                    + " 0.00$ ou 0,00$");
        }

        return montant;
    }

    private static DecimalFormat obtenirBonAnalyseur(String montantReclamation) {

        DecimalFormatSymbols symboles = new DecimalFormatSymbols();

        symboles.setDecimalSeparator('.');
        DecimalFormat analyseur = new DecimalFormat("0.00$", symboles);

        if (montantReclamation.contains(",")) {
            symboles.setDecimalSeparator(',');
            analyseur = new DecimalFormat("0.00$", symboles);
        }
        return analyseur;
    }
}
