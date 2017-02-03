/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

/**
 *
 * @author Max
 */
public class Reclamation {
    private int soin;
    private Date date;
    private String montantReclamation;

    /**
     * 
     * @param soin
     * @param date
     * @param montantReclamation 
     */
    public Reclamation(int soin, Date date, String montantReclamation) {
        this.soin = soin;
        this.date = date;
        this.montantReclamation = montantReclamation;
    }
    /**
     * 
     * @return 
     */
    public int getSoin() {
        return soin;
    }
    /**
     * 
     * @param soin 
     */
    public void setSoin(int soin) {
        this.soin = soin;
    }
    
    /**
     * 
     * @return 
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * 
     * @param date 
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    /**
     * 
     * @return 
     */
    public double getMontantReclamation() {
        return montantReclamation;
    }
    
    /**
     * 
     * @param montantReclamation 
     */
    public void setMontantReclamation(String montantReclamation) {
        if(validerMontantReclamation(montantReclamation) == true){
            this.montantReclamation = montantReclamation;
        }
    
    /**
     *
     * @param montant
     * @return
     * @throws Exception
     */
    public double montantReclamation (String montant) throws Exception {
        double montantDouble = 0.0;
        if(validerMontantReclamation(montant) == true){
            montantDouble = Double.parseDouble(montant.substring(0, montant.length() -1));
        }else{
            throw  new Exception();
        }
        return montantDouble;
    }
    
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
        
    
    
    
}

    

