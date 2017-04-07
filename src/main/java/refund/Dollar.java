/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

/**
 *
 * @author Billy
 */
public class Dollar      {   

    private final long cents;

    public Dollar() {
        cents = 0;
    }

    public Dollar(long cents) {
        this.cents = cents;
    }

    public Dollar(double montant) {
        cents = (long) (montant * 100);
    }
    
    public Dollar(String montant) throws ReclamationException{
        this(MontantFormat.analyserMontant(montant));
    }

    public long getCents() {
        return cents;
    }
    
    public double convertirEnDouble(){
        return cents/100.00;
    }

    public Dollar plus(Dollar montant) {
        long totalCents = cents + montant.getCents();
        return new Dollar(totalCents);
    }

    public Dollar moins(Dollar montant) {
        long totalCents = cents - montant.getCents();
        return new Dollar(totalCents);
    }

    public Dollar fois(Dollar montant) {
        long produit = Math.round(cents * montant.convertirEnDouble());
        return new Dollar(produit);
    }

    public Dollar pourcentage(double taux) {
        long resultat = Math.round(cents * taux);
        return new Dollar(resultat);
    }
    
    public boolean estSuperieurA(Dollar montant){
        return cents > montant.cents;
    }
    
    public boolean estNegatif(){
        return cents < 0;
    }

    public boolean estInferieurA(Dollar montant){
        return cents < montant.cents;
    }
    
    @Override
    public String toString() {
        String montantDollar = toString2(cents);
        
        if(cents < 0){
            montantDollar = "-" + toString2(-cents);
        }
        return montantDollar;
    }

    public String toString2(long cents) {
        String partieEntiere = "" + cents / 100;
        String partieFractionnaire = "" + cents % 100;
        if(partieFractionnaire.length() == 1){
            partieFractionnaire = "0" + partieFractionnaire;
        }
        return partieEntiere + "." + partieFractionnaire + "$";
    }

    @Override
    public boolean equals(Object obj) {

        return this == obj
                || (obj != null
                && this.getClass().equals(obj.getClass())
                && this.cents == ((Dollar) obj).cents);
    }


    

}
