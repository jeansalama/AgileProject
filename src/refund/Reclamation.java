
package refund;

/**
 *Classe visant à créer et gérer des objets Reclamation. 
 * 
 */
public class Reclamation {
    private int soin;
    private String date;
    private String montantReclamation;

    /**
     * Vise à construire un objet de type Reclamation avec les valeurs passées 
     * en paramètre.
     * 
     * @param soin une nombre entier représentant la catégorie de soin pour 
     *             lequelle un montant est réclamé.
     * 
     * @param date la date à laquelle la réclamation est faite.
     * @param montantReclamation un nombre décimal indiquant le montant réclamé.
     */
    public Reclamation(int soin, String date, String montantReclamation) {
        this.soin = soin;
        this.date = date;
        this.montantReclamation = montantReclamation;
    }
    /**
     * Retourne la valeur de l'attribut soin de l'objet de type Reclamation sur 
     * lequel cette méthode est appelée.
     * 
     * @return le numéro de soin.
     */
    public int getSoin() {
        return soin;
    }
    /**
     * Change la valeur de l'attribut soin d'un objet de type Reclamation par la
     * valeur passée en paramètre.
     * 
     * @param soin une nombre entier représentant la catégorie de soin pour 
     *             lequelle un montant est réclamé.
     * 
     * @throws ReclamationException Exception Si la valeur passée en paramètre 
     * ne fait pas partie des catégories de soins couverts.
     */
    public void setSoin(int soin) throws ReclamationException {
        if(!validerSoin(soin)){
            throw new ReclamationException("numéro de soin non valide.");
        }
        this.soin = soin;
    }
    
    /**
     * Retourne la valeur de l'attribut date de l'objet de type Reclamation sur 
     * lequel cette méthode est appelée.
     * 
     * @return la date auquelle le soin pour lequel il y a réclamation a été 
     * octroyé.
     */
    public String getDate() {
        return date;
    }
    
    /**
     * Change la valeur de l'attribut date d'un objet de type Reclamation par la
     * valeur passée en paramètre.
     * 
     * @param date la nouvelle date auquelle le soin pour lequel il y a
     * réclamation a été octroyé.
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    /**
     * Retourne la valeur de l'attribut montantReclamation de l'objet de type
     * Reclamation sur lequel cette méthode est appelée.
     * 
     * @return Le montant réclamé.  
     */
    public String getMontantReclamation() {
        return montantReclamation;
    }
    
    /* 
     * @return double le montant de reclamation
     */
    public double getMontantReclamationDouble(){
        return Double.parseDouble(montantReclamation.substring(0, 
                montantReclamation.length() -1));
    }
    
    /**
     * Change la valeur de l'attribut montantReclamation d'un objet de type 
     * Reclamation par la valeur passée en paramètre.
     * 
     * @param montantReclamation une chaîne de caractères représentant le 
     *                           nouveau montant demandé par le client.
     * 
     * @throws ReclamationException si la valeur passée en paramètre est 
     *                              invalide.
     */
    public void setMontantReclamation(String montantReclamation) 
            throws ReclamationException {
        
        if(!validerMontantReclamation(montantReclamation)){
            throw new ReclamationException("montant de réclamation invalide.");
        }
        this.montantReclamation = montantReclamation;
    }
    
    /**
     *Indique si la valeur passée en paramètre est un montant de réclamation 
     * valide.
     * 
     * @param montant une chaîne de caractère représentant un montant.
     * @return true si le montant est valide, false sinon.
     */
    public static boolean validerMontantReclamation(String montant) {
        boolean montantValide = false;
        
        if(montant.contains(".") && (montant.charAt(montant.length() -1) == '$')
                && (montant.charAt(0)> 0 && montant.charAt(0)<= 9)){
            if(estUnDouble(montant.substring(0, montant.length()-1)) == true){
                montantValide = true;
            } 
        }
        return montantValide;
    }

    /**
     *Indique si la valeur passée en paramètre représente un nombre décimal.
     * 
     * @param montant une chaîne de caractère représentant un montant.
     * @return true si la chaîne de caractères représente un montant, false 
     *         sinon.
     */
    public static boolean estUnDouble(String montant){
        boolean estDouble = true;
        double montantDouble;
        try{
            montantDouble = Double.parseDouble(montant);
        }catch(NumberFormatException e){
            estDouble = false;
        }
        
        return estDouble;
    }
    
    /**
     *Tranforme une chaîne de caractères représentant un montant d'argent 
     *en nombre décimal.
     * 
     * @param montant une chaine de caractères représentant un montant.
     * @return un nombre décimal représentant le montant réclamé.
     * @throws ReclamationException si la chaîne de caractères n'est pas valide.
     */
    public double montantReclamation (String montant) throws ReclamationException {
       double montantDouble = 0.0;
        if(validerMontantReclamation(montant) == true){
            montantDouble = Double.parseDouble(montant.substring(0, montant.length() -1));
        }else{
            throw  new ReclamationException("Montant de réclamation invalide");
        }
        return montantDouble;
    }
    
    /**
     *Indique si le numéro de soin passé en paramètre est valide.
     * 
     * @param soin un nombre entier représentant un numéro de soin.
     * @return true si le numéro de soin est valide, false sinon.
     */
    public static boolean validerSoin( int soin){
        boolean soinValide = false;
        
        if(soin == 0 || 100 == soin || soin == 200 || (soin >= 300 
                && soin <= 399) || soin == 400 || soin == 500 || soin == 600 
                || soin == 700){
            
            soinValide = true;
        }
        return soinValide;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "soin=" + soin + ", date=" + date 
                + ", montantReclamation=" + montantReclamation + '}';
    }
    
    class ReclamationException extends Exception {
        public ReclamationException(){}
    
        public ReclamationException(String msg){
            super(msg);
        }
    }
    
    
        
}
