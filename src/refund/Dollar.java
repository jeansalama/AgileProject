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
public class Dollar implements Comparable {

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

    public long getCents() {
        return cents;
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
        long produit = cents * montant.getCents();
        return new Dollar(produit);
    }

    public Dollar pourcentage(double taux) {
        long resultat = Math.round(cents * taux);
        return new Dollar(resultat);
    }
    
    public boolean estSuperieurA(Dollar montant){
        return cents > montant.cents;
    }

    @Override
    public String toString() {
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

    @Override
    public int compareTo(Object t) {
        long difference = cents - ((Dollar) t).cents;
        return (int)difference;
    }

    

}
