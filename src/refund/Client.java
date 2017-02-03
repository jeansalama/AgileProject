
package refund;
import static jdk.nashorn.internal.runtime.JSType.isNumber;
/**
 * Class pour gerer l'infos d'un client
 * @author Max
 */
public class Client {

    private String numero;
    private String contrat;
    private String date;
     
    /**
     * Ce constructeur cree un objet Client avec tous les infos de celui ci
     * @param numero Un String de max 6 chiffres
     * @param contrat Un String de une lettre
     * @param date Un String d'une date AAAA-MM
     * @throws Exception Si une des valeurs est invalide 
     */
    public Client(String numero, String contrat, String date) throws Exception {

        setNumero(numero);
        setContrat(contrat);
        setDate(date);
    }

    public String getNumero() {
        return numero;
    }

    public String getContrat() {
        return contrat;
    }

    public String getDate() {
        return date;
    }

    /**
     * Valide si la String a seulement 6 characteres de long et qu'il s'agit 
     * que de numeros sinon lance une exception
     * @param numero
     * @throws Exception
     */
    public final void setNumero(String numero) throws Exception {
            
        if (numero.length() > 6 || !isNumber(numero)) {
            throw new Exception();
        }
        this.numero = numero;
    }

    public final void setContrat(String contrat) throws Exception {
        String valide = "ABCD";
        if (!valide.contains(contrat) || contrat.length() > 1 || contrat.isEmpty()) {
            throw new Exception();
        }
        this.contrat = contrat;
    }

    public final void setDate(String date) throws Exception {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Numero de client: " + numero + "\nContrat: " + contrat
                + "\nMois: " + date;
    }

}
