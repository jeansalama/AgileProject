
package refund;
import static jdk.nashorn.internal.runtime.JSType.isNumber;
/**
 * Class pour gerer l'infos d'un client
 * @author Max
 */
public class Dossier {

    private String dossier;
    private Date date;
    private Contrat contrat;
     
    /**
     * Ce constructeur cree un objet Client avec tous les infos de celui ci
     * @param dossier Un String de max 6 chiffres
     * @param contrat Un String de une lettre
     * @param date Un String d'une date AAAA-MM
     * @throws Exception Si une des valeurs est invalide 
     */
    public Dossier(String dossier, Date date) throws DossierException {

        setDossier(dossier);
        setDate(date);
        
       
    }

    public String getDossier() {
        return dossier;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public Date getDate() {
        return date;
    }
    
      
   
    /**
     * Valide si la String a seulement 6 characteres de long et qu'il s'agit 
     * que de numeros sinon lance une exception
     * @param numero
     * @throws DossierException
     */
    public final void setDossier(String dossier) throws DossierException {
            
        if (dossier.length() != 7) {
            throw new DossierException("Dossier du client invalide");
        }
        try{
            Integer.parseInt(dossier.substring(1));
            contrat = new Contrat(dossier.substring(0, 1));
        } catch(NumberFormatException | ContratException nfe){
            throw new DossierException("Dossier du client invalide");
        }
        this.dossier = dossier;
        
    }

    public final void setDate(Date date){
        this.date = date;
    }

    @Override
    public String toString() {
        return "dossier: " + dossier + "\nMois: " + date;
    }

}
class DossierException extends Exception {
        public DossierException(){}
    
        public DossierException(String msg){
            super(msg);
        }
    }
