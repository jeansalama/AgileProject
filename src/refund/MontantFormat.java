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
     * @param montant a formatter
     * @return String la chaine de caractere representant le montant en dollars
     * a deux decimales pres avec le caractere '.' ou ',' comme separateur
     * decimal : 123.451 sera transformee en "123.45$"
     */
    public static String formatRemboursement(double montant) {

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');

        DecimalFormat df = new DecimalFormat("0.00$", symbols);

        return df.format(montant);
    }

    /**
     *
     * @param montantReclamation un String representant un montant en dollars
     * @return double, la valeur de montanReclamation
     * @throws ReclamationException si dans montantReclamation une de ces
     * conditions n'est pas respectee : il n'y a pas de separateur decimal ('.'
     * ou ','). La devise '$' n'est pas le dernier caractere. Il n'y a pas de
     * decimale avant le separateur (partie entiere). Il n'y a pas exactement
     * deux decimales apres le separateur (partie fractionnaire)
     */
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

    /**
     * @param montant un String representant un montant en dollars
     * @return DecimalFormat, un analyseur permettant la conversion du String
     * montant en sa valeur en double (en tenant compte de son separateur
     * decimal)
     */
    public static DecimalFormat obtenirBonAnalyseur(String montant) {

        DecimalFormatSymbols symboles = new DecimalFormatSymbols();

        symboles.setDecimalSeparator('.');
        DecimalFormat analyseur = new DecimalFormat("0.00$", symboles);

        if (montant.contains(",")) {
            symboles.setDecimalSeparator(',');
            analyseur = new DecimalFormat("0.00$", symboles);
        }
        return analyseur;
    }

    /**
     * @param montantReclam un String representant un montant en dollars
     * @throws ReclamationException si dans montantReclam une de ces conditions
     * n'est pas respectee : il n'y a pas de separateur decimal ('.' ou ','). La
     * devise '$' n'est pas le dernier caractere. Il n'y a pas de decimale avant
     * le separateur (partie entiere). Il n'y a pas exactement deux decimales
     * apres le separateur (partie fractionnaire)
     */
    public static void verifierFormatMontant(String montantReclam)
            throws ReclamationException {

        if (!contientSeparateurDecimal(montantReclam)
                || !validerIndiceDevise(montantReclam)
                || taillePartieEntiere(montantReclam) < 1
                || taillePartieFractionnaire(montantReclam) != 2) {

            throw new ReclamationException(MSG_ERREUR_FORMAT);
        }
    }

    /**
     * @param montantReclam un String representant un montant en dollars
     * @return int, l'indice du separateur decimal de montantReclam
     */
    public static int indiceSeparateurDecimal(String montantReclam) {
        int indice;

        if (montantReclam.contains(".")) {
            indice = montantReclam.indexOf('.');
        } else {
            indice = montantReclam.indexOf(',');
        }

        return indice;
    }

    /**
     * @param montantReclam un String representant un montant en dollars
     * @return boolean, vrai montantReclam contient un separateur decimal ('.'
     * ou ','), false sinon
     */
    public static boolean contientSeparateurDecimal(String montantReclam) {

        return indiceSeparateurDecimal(montantReclam) != -1;
    }

    /**
     * @param montantReclam un String representant un montant en dollars
     * @return int, le nombre de decimales avant la "virgule"
     */
    public static int taillePartieEntiere(String montantReclam) {

        int indice = indiceSeparateurDecimal(montantReclam);

        String partieEntiere = montantReclam.substring(0, indice);

        return partieEntiere.length();
    }

    /**
     * @param montantReclam un String representant un montant en dollars
     * @return int, le nombre de decimales entre la "virgule" et la devise 
     */
    public static int taillePartieFractionnaire(String montantReclam) {

        int indiceSeparateur = indiceSeparateurDecimal(montantReclam);
        int indiceDevise = indiceDevise(montantReclam);

        String partieDecimale
                = montantReclam.substring(indiceSeparateur + 1, indiceDevise);
        
        int taille = partieDecimale.length();
        if(indiceSeparateur == -1){
            taille = 0;
        }

        return taille;
    }

    /**
     * @param montantReclam un String representant un montant en dollars
     * @return int l'indice de la devise ('$') dans montantReclam
     */
    public static int indiceDevise(String montantReclam) {
        return montantReclam.indexOf("$");
    }

    /**
     * @param montantReclam un String representant un montant en dollars
     * @return boolean, vrai si la devise '$' est le dernier caractere de
     * montantReclam, false sinon
     */
    public static boolean validerIndiceDevise(String montantReclam) {
        return indiceDevise(montantReclam) == montantReclam.length() - 1;
    }

}
