
package refund;

/**
 *Classe visant à créer et gérer des objets Reclamation. 
 * 
 */
public class Reclamation {
    private int soin;
    private Date date;
    private String montantReclamationS;
    private double montantReclamationDouble;

    /**
     * Vise à construire un objet de type Reclamation avec les valeurs passées 
     * en paramètre.
     * 
     * @param soin une nombre entier représentant la catégorie de soin pour 
     *             lequelle un montant est réclamé.
     * 
     * @param date la date à laquelle la réclamation est faite.
     * @param montantReclamation un nombre décimal indiquant le montant réclamé.
     * @throws ReclamationException s'il y a tentative de construction d'un 
     *                              objet Reclamation avec des valeurs invalides
     */
    public Reclamation(int soin, Date date, String montantReclamation)
            throws ReclamationException {
        
        setSoin(soin);
        this.date = date;
        setMontantReclamation(montantReclamation);
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
    public Date getDate() {
        return date;
    }
    
    /**
     * Change la valeur de l'attribut date d'un objet de type Reclamation par la
     * valeur passée en paramètre.
     * 
     * @param date la nouvelle date auquelle le soin pour lequel il y a
     * réclamation a été octroyé.
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    /**
     * Retourne la valeur de l'attribut montantReclamation de l'objet de type
     * Reclamation sur lequel cette méthode est appelée.
     * 
     * @return Le montant réclamé.  
     */
    public String getMontantReclamation() {
        return montantReclamationS;
    }
    
    /** 
     * @return double le montant de reclamation
     */
    public double getMontantReclamationDouble(){
        return montantReclamationDouble;
    }
    
    /**
     * Change la valeur de l'attribut montantReclamation d'un objet de type 
     * Reclamation par la valeur passée en paramètre, vérifie ensuite si cette
     * valeur peut être transformée en nombre décimal
     * 
     * @param montantReclam une chaîne de caractères représentant le 
     *                           nouveau montant demandé par le client.
     * 
     * @throws ReclamationException si la valeur passée en paramètre est 
     *                              invalide.
     */
    public void setMontantReclamation(String montantReclam) 
            throws ReclamationException {
        int longueur;
        int indicePoint;
        try{
            this.montantReclamationS = montantReclam;
            longueur = montantReclam.length();
            
            if(montantReclam.charAt(longueur-1) != '$')
                throw new ReclamationException("La devise doit etre en '$' "
                        + "et placee apres le montant");
            
            indicePoint = montantReclam.indexOf(".");
            
            if(indicePoint == -1 && montantReclam.contains(","))
                throw new ReclamationException("Le separateur decimal doit etre"
                        + " un point et non une virgule!");
            
            if(montantReclam.contains("-"))
                throw new ReclamationException("Le montant de la réclamation ne"
                        + " peut pas être négatif!");
            
            
            // pour rejeter 0.0$ et 0.000$, il doit obligatoirement avoir
            // deux chiffres entre '.' et '$'
            if(!montantReclam.contains(".") || indicePoint == 0 
                    ||montantReclam.substring(indicePoint + 1).length() != 3)
                throw new ReclamationException("Le montant doit etre represente"
                        + " exactement sur deux decimales apres la \"virgule\""
                        + " et au moins une decimale avant!");
            
            this.montantReclamationDouble 
                    = Double.parseDouble(montantReclam.substring(0,
                            montantReclam.length()-1));
        }catch(NumberFormatException e){
            throw new ReclamationException("Montant de réclamation ne peut être"
                    + " transformé en double.");
        }
        
    }
 
    
    /**
     *Indique si le numéro de soin passé en paramètre est valide.
     * 
     * @param soin un nombre entier représentant un numéro de soin.
     * @return true si le numéro de soin est valide, false sinon.
     */
    public static boolean validerSoin( int soin){
        return soin == 0 || 100 == soin || soin == 200 
                || (soin >= 300 && soin <= 399) || soin == 400 || soin == 500 
                || soin == 600 || soin == 700;
    }

    @Override
    public String toString() {
        return montantReclamationS;
    }
  
} // fin Reclamation

class ReclamationException extends Exception {
        public ReclamationException(){}
    
        public ReclamationException(String msg){
            super(msg);
        }
}
