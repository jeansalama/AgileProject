package refund;

/**
 * Cette classe sert a modeliser un contrat d'assurence
 *
 * @author Jean Salama
 */
public class Contrat {

    public static final String MSG_CONTRAT_EXCEPTION 
            = "Le contrat du client est invalide";
    
    private String type;

    /**
     * @param type un String d'un caractere parmi 'A', 'B', 'C', 'D', 'E'.
     * @throws ContratException si le type du contrat n'est pas valide
     */
    public Contrat(String type) throws ContratException {
        setType(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws ContratException {
        if (!estTypeValide(type)) {
            throw new ContratException(MSG_CONTRAT_EXCEPTION);
        }
        this.type = type;
    }

    @Override
    public String toString() {
        return "" + type;
    }

    /**
     * @param type un String representant le type d'un contrat
     * @return boolean true si le type est valide (cad si c'est un String 
     * d'un caractere parmi 'A','B','C', 'D'ou 'E'), false sinon
     */
    private boolean estTypeValide(String type) {
        return type != null && type.length() == 1
                && "ABCDE".contains(type);
    }

}

class ContratException extends Exception {

    public ContratException() {
    }

    public ContratException(String msg) {
        super(msg);
    }
}
