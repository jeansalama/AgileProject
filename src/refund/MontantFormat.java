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

    public static final String MSG_ERREUR_FORMAT = "Le format du montant "
            + "de reclamation est incorrect. Il doit être de la forme"
            + " 0.00$ ou 0,00$";

    /**
     * Cette methode formatte un montant en double en une chaine de caractere
     * representant ce montant en dollars a deux decimales pres avec le
     * caractere '.' comme separateur decimal : 123.451 sera transformee en
     * "123.45$"
     *
     * @param montant a formatter
     * @return String la chaine de caractere representant le montant avec les
     * specifications mentionnees ci-dessus
     */
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
        verifierFormatMontant(montantReclamation);

        try {
            montant = analyseur.parse(montantReclamation).doubleValue();
        } catch (ParseException pe) {
            throw new ReclamationException(MSG_ERREUR_FORMAT);
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

    private static void verifierFormatMontant(String montantReclam)
            throws ReclamationException {

        if (!contientSeparateurDecimal(montantReclam)
                || !validerIndiceDevise(montantReclam)
                || taillePartieEntiere(montantReclam) < 1
                || taillePartieDecimale(montantReclam) != 2) {

            throw new ReclamationException(MSG_ERREUR_FORMAT);
        }
    }

    private static int indiceSeparateurDecimal(String montantReclam) {
        int indice;

        if (montantReclam.contains(".")) {
            indice = montantReclam.indexOf('.');
        } else {
            indice = montantReclam.indexOf(',');
        }

        return indice;
    }

    private static boolean contientSeparateurDecimal(String montantReclam) {

        return indiceSeparateurDecimal(montantReclam) != -1;
    }

    private static int taillePartieEntiere(String montantReclam) {

        int indice = indiceSeparateurDecimal(montantReclam);

        String partieEntiere = montantReclam.substring(0, indice);

        return partieEntiere.length();
    }

    private static int taillePartieDecimale(String montantReclam) {

        int indiceSeparateur = indiceSeparateurDecimal(montantReclam);
        int indiceDevise = indiceDevise(montantReclam);

        String partieDecimale = montantReclam.substring(indiceSeparateur + 1,
                indiceDevise);

        return partieDecimale.length();
    }

    private static int indiceDevise(String montantReclam) {
        return montantReclam.indexOf("$");
    }

    private static boolean validerIndiceDevise(String montantReclam) {
        return indiceDevise(montantReclam) == montantReclam.length() - 1;
    }

}
