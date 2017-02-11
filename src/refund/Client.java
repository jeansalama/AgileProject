
package refund;
import static jdk.nashorn.internal.runtime.JSType.isNumber;
/**
 * Class pour gerer l'infos d'un client
 * @author Max
 */
public class Client {

    private String numero;
    private Date date;
    private Contrat contrat;
     
    /**
     * Ce constructeur cree un objet Client avec tous les infos de celui ci
     * @param numero Un String de max 6 chiffres
     * @param contrat Un String de une lettre
     * @param date Un String d'une date AAAA-MM
     * @throws Exception Si une des valeurs est invalide 
     */
    public Client(String numero, Contrat contrat, Date date) throws ClientException {

        setNumero(numero);
        setContrat(contrat);
        setDate(date);
    }

    public String getNumero() {
        return numero;
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
     * @throws ClientException
     */
    public final void setNumero(String numero) throws ClientException {
            
        if (numero.length() != 6) {
            throw new ClientException("Numero du client invalide");
        }
        try{
            Integer.parseInt(numero);
        } catch(NumberFormatException nfe){
            throw new ClientException("Numero du client invalide");
        }
        this.numero = numero;
    }

    public final void setContrat(Contrat contrat) throws ClientException {
        this.contrat = contrat;
    }

    public final void setDate(Date date) throws ClientException {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Numero de client: " + numero + "\nContrat: " + contrat
                + "\nMois: " + date;
    }

}
class ClientException extends Exception {
        public ClientException(){}
    
        public ClientException(String msg){
            super(msg);
        }
    }
