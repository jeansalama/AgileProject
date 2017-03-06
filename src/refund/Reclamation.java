package refund;

/**
 * Classe visant à créer et gérer des objets Reclamation.
 *
 * @author Maxime Gagnon
 */
public class Reclamation {

    private int soin;
    private Date date;
    private String montantReclamationString;
    private double montantReclamationDouble;

    /**
     * @param soin une nombre entier représentant la catégorie de soin pour
     * lequelle un montant est réclamé.
     * @param date la date à laquelle la réclamation est faite.
     * @param montantReclamation un nombre décimal indiquant le montant réclamé.
     * @throws ReclamationException s'il y a tentative de construction d'un
     * objet Reclamation avec des valeurs invalides
     */
    public Reclamation(int soin, Date date, String montantReclamation)
            throws ReclamationException {

        setSoin(soin);
        if (!date.contientUnJour()) {
            throw new ReclamationException();
        }
        this.date = date;
        setMontantReclamation(montantReclamation);
    }

    public int getSoin() {
        return soin;
    }

    public Date getDate() {
        return date;
    }

    public String getMontantReclamation() {
        return montantReclamationString;
    }

    public double getMontantReclamationDouble() {
        return montantReclamationDouble;
    }

    public void setSoin(int soin) throws ReclamationException {

        if (!validerSoin(soin)) {
            throw new ReclamationException("Donnee soin invalide !");
        }
        this.soin = soin;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMontantReclamation(String montantReclam)
            throws ReclamationException {

        int longueur = montantReclam.length();
        this.montantReclamationString = montantReclam;
        contientDevise(montantReclam, longueur);
        verifierSiNegatif(montantReclam);
        setMontantReclamationDouble(montantReclam);
    }

    private void setMontantReclamationDouble(String montantReclam)
            throws ReclamationException {

        try {
            this.montantReclamationDouble
                    = Double.parseDouble(montantReclam.substring(0,
                            montantReclam.length() - 1));
        } catch (NumberFormatException e) {
            throw new ReclamationException("Montant de réclamation ne peut être"
                    + " transformé en double.");
        }
    }

    /**
     * @param montantReclam Un nombre décimal représentant un montant.
     * @throws ReclamationException si le montant passé en paramètre est négatif
     */
    private void verifierSiNegatif(String montantReclam)
            throws ReclamationException {

        if (montantReclam.contains("-")) {
            throw new ReclamationException("Le montant de la réclamation ne"
                    + " peut pas être négatif!");
        }
    }

    /**
     *
     * @param montantReclam Un nombre décimal représentant un montant.
     * @param longueur la longueur de la chaine de caractères du montant.
     * @throws ReclamationException si la devise n'est pas '$'
     */
    private void contientDevise(String montantReclam, int longueur)
            throws ReclamationException {

        if (montantReclam.charAt(longueur - 1) != '$') {
            throw new ReclamationException("La devise doit etre en '$' "
                    + "et placee apres le montant.");
        }
    }

    /**
     * @param soin un nombre entier représentant un numéro de soin.
     * @return true si le numéro de soin est valide, false sinon.
     */
    public static boolean validerSoin(int soin) {
        return soin == 0 || 100 == soin || soin == 200
                || (soin >= 300 && soin <= 399) || soin == 400 || soin == 500
                || soin == 600 || soin == 700 || soin == 150 || soin == 175;
    }

    /**
     *
     * @return une représentation sous forme de chaîne de caractère du montant
     * de réclamation.
     */
    @Override
    public String toString() {
        return montantReclamationString;
    }

}

class ReclamationException extends Exception {

    public ReclamationException() {
    }

    public ReclamationException(String msg) {
        super(msg);
    }
}
