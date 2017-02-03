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
    private String date;
    private String montantReclamation;

    /**
     * 
     * @param soin
     * @param date
     * @param montantReclamation 
     */
    public Reclamation(int soin, String date, String montantReclamation) {
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
    public String getDate() {
        return date;
    }
    
    /**
     * 
     * @param date 
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    /**
     * 
     * @return 
     */
    public String getMontantReclamation() {
        return montantReclamation;
    }
    
    /**
     * 
     * @param montantReclamation 
     * @throws java.lang.Exception 
     */
    public void setMontantReclamation(String montantReclamation) throws Exception {
        if(!validerMontantReclamation(montantReclamation)){
            throw new Exception();
        }
        this.montantReclamation = montantReclamation;
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

    

