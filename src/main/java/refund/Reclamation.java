package refund;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe visant à créer et gérer des objets Reclamation.
 *
 * @author Maxime Gagnon
 */
public class Reclamation {

    public final static String MSG_DATE_INCOMPLETE_EXCEPTION = "La date est "
            + "incomplete. Le format d'une date complete est aaaa-mm-jj.";
    
    public final static ArrayList<Integer> LISTE_SOINS = 
            new ArrayList<>(Arrays.asList(0, 100, 150, 175, 200, 400, 500, 600,
                    700));
    
    private int soin;
    private Date date;
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
        this.montantReclamation = new Dollar (montantReclam);
        verifierSiMontantValide(montantReclamation);
    }


    /**
     * @param montantReclam Un nombre décimal représentant un montant.
     * @throws ReclamationException si le montant passé en paramètre est négatif
     */
    private void verifierSiMontantValide(Dollar montantReclam)
            throws ReclamationException {

        if(montantReclam.getCents() <= 0 || montantReclam.getCents()>50000){
            throw new ReclamationException("Le montant de réclamation doit être"
                    + " supérieur à zéro et inférieur à 500$!");
        }
}

    /**
     * @param soin un nombre entier représentant un numéro de soin.
     * @return true si le numéro de soin est valide, false sinon.
     */
    public static boolean validerSoin(int soin) {
        return LISTE_SOINS.contains(soin) || (soin >= 300 && soin <= 399) ;
    }


    @Override
    public String toString() {
        return montantReclamation.toString();
    }

}

class ReclamationException extends Exception {

    public ReclamationException() {
    }

    public ReclamationException(String msg) {
        super(msg);
    }
}
