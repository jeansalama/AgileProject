package refund;

/**
 * Classe visant à créer et gérer des objets Reclamation.
 *
 * @author Maxime Gagnon
 */
public class Reclamation {

    public final static String MSG_DATE_INCOMPLETE_EXCEPTION = "La date est "
            + "incomplete. Le format d'une date complete est aaaa-mm-jj.";
    
    private int soin;
    private Date date;
    private String montantReclamationString;
    private Dollar montantReclamation;
    
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
            throw new ReclamationException(MSG_DATE_INCOMPLETE_EXCEPTION);
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

    public Dollar getMontantReclamation() {
        return montantReclamation;
    }

    public void setSoin(int soin) throws ReclamationException {

        if (!validerSoin(soin)) {
            throw new ReclamationException("La donnee soin est invalide !");
        }
        this.soin = soin;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMontantReclamation(String montantReclam)
            throws ReclamationException {
        this.montantReclamationString =  montantReclam;
        this.montantReclamation = new Dollar (montantReclam);
        verifierSiNegatif(montantReclam);
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
     * @param soin un nombre entier représentant un numéro de soin.
     * @return true si le numéro de soin est valide, false sinon.
     */
    public static boolean validerSoin(int soin) {
        return soin == 0 || 100 == soin || soin == 200
                || (soin >= 300 && soin <= 399) || soin == 400 || soin == 500
                || soin == 600 || soin == 700 || soin == 150 || soin == 175;
    }


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
