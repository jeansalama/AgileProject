package refund;

/**
 * Class pour gerer l'infos d'un client
 *
 * @author Max
 */
public class Dossier {

    private String idDossier;
    private Date date;
    private Contrat contrat;

    /**
     * @param idDossier Un String d'une lettre (type du contrat) 
     * suivie de 6 chiffres
     * @param date Une Date de format AAAA-MM
     * @throws DossierException Si une des valeurs est invalide
     */
    public Dossier(String idDossier, Date date) throws DossierException {
        
        setIdDossier(idDossier);
        setDate(date);
    }

    public String getIdDossier() {
        return idDossier;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public Date getDate() {
        return date;
    }

    /**
     * @param idDossier Un String d'une lettre (type du contrat) 
     * suivie de 6 chiffres
     * @throws DossierException si le format du idDossier est invalide
     */
    public final void setIdDossier(String idDossier) throws DossierException {

        if (idDossier.length() != 7) {
            throw new DossierException("Dossier du client invalide");
        }
        try {
            Integer.parseInt(idDossier.substring(1));
            contrat = new Contrat(idDossier.substring(0, 1));
        } catch (NumberFormatException | ContratException nfe) {
            throw new DossierException("Dossier du client invalide");
        }
        this.idDossier = idDossier;

    }

    public final void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "dossier: " + idDossier + "\nMois: " + date;
    }

}

class DossierException extends Exception {

    public DossierException() {
    }

    public DossierException(String msg) {
        super(msg);
    }
}
