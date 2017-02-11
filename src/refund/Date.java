/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import static java.lang.Integer.parseInt;

/**
 * Classe cree et gere des objets Date.
 * @author Lucignano Veronique
 */
public class Date {

    private String date;
    private String annee;
    private String mois;
    private String jour;

    /**
     * Ce constructeur avec un parametre cree un Objet date
     * @param date est un String incluant soit annee-mois soit annee-mois-jour
     * @throws DateException si date n'est pas valide
     */
    public Date(String date) throws DateException {
        setDate(date);
    }

    public String getAnnee() {
        return annee;
    }

    public String getMois() {
        return mois;
    }

    public String getJour() {
        return jour;
    }

    public String getDate() {
        return date;
    }

    /**
     * Initialise date
     * appel methodes: validation longueur et si numerique 
     * apres divise de la date sans separateur dependamment des longueurs acceptees
     * @param date String
     * @throws DateException si pas valide
     */
    public final void setDate(String date) throws DateException {
        String[] tab;
        if (dateLongueur(date)) {
            tab = dateSepareeTabString(date);
            annee = tab[0];
            mois = tab[1];
            if (tab.length == 3) {
                jour = tab[2];
            }
            if (validerAnnee(annee)) {
                setAnnee(annee);
                if (validerMois(mois)) {
                    setMois(mois);
                    if (tab.length == 3) {
                        if (validerJour(mois, jour, annee)) {
                            setJour(jour);
                        }
                    }
                }
            }
        }else{
            throw new DateException();
        }
        this.date = date;
    }

     /**
     * Initialise annee
     * appel methode: validation longueur 
     * @param annee String
     * @throws DateException si pas valide
     */
    public void setAnnee(String annee) throws DateException {
        if (!validerAnnee(annee)) {
           throw new DateException("Annee invalide!");
        }
            this.annee = annee;
    }  
    
     /**
     * Initialise mois
     * appel methode: validation longueur 
     * @param mois String
     * @throws DateException si pas valide
     */
    public void setMois(String mois) throws DateException {
        if (!validerMois(mois)) {
           throw new DateException("Mois invalide!");
        } 
        this.mois = mois;
    }

    /**
     * Initialise jour
     * appel methode: validation longueur 
     * @param jour String
     * @throws DateException si pas valide
     */
    public final void setJour(String jour) throws DateException {
        if (!validerJour(mois, jour, annee)) {
           throw new DateException("Jour invalide!");
        }
        this.jour = jour;
    }

    /**
     * Methode qui valide la longueur de la date 
     * retourne true si valide false si non valide
     * @param date String 
     */
    private static boolean dateLongueur(String date){
        boolean estLongueur = true;
        if (!(date.length() == 7 || date.length() == 10)) {
            estLongueur = false;
        }
        return estLongueur;
    }

    /**
     * Methode qui separe la date dans un tableau de String
     * retourne un tableau de String en separant date en 3 ou 2 cases
     * les separateurs '-' sont enleves et valides
     * @param date String 
     * @throws DateException si les separateurs ne correspondent pas a '-' 
     * 
     */
       private static String[] dateSepareeTabString(String date) throws DateException{
        String[] tabDateEstSeparee;

        if ((date.indexOf('-') == -1)) {
            throw new DateException("Separateurs dans Date non valide!");
        }
        String separateurs = "[-]";
        tabDateEstSeparee = date.split(separateurs);
        return tabDateEstSeparee;
    }

    /**
     * methode qui valide la longueur l'annee
     * return true si valide
     * @param annee String
     * @throws DateException si longueur annee n'est pas valide
     */
    private boolean validerAnnee(String annee) throws DateException{
        boolean anneeValide = true;
         if (annee.length() != 4) {
            throw new DateException("Annee non valide!");
        }
        return anneeValide;
    }

    /**
     * methode qui valide la longueur du mois et si entre 1 <-> 12
     * @param mois String
     * @throws DateException si mois n'est pas valide
     */
    private boolean validerMois(String mois) throws DateException{
        boolean moisValide = true;
        if ((mois.length()) != 2 || ((parseInt(mois) < 1) || (parseInt(mois) > 12))) {
            throw new DateException("Mois non valide");
        }
        return moisValide;
    }

    /**
     * methode qui valide la longueur du jour et si entre 1 <-> 27/31 dependamment du mois
     * si mois 02 verifie si annee est bissextile (appel a la methode: anneeEstBissextile())
     * et valide le jour
     * return true si jour est valide
     * @param jour String
     * @throws DateException si jour n'est pas valide
     */
    private boolean validerJour(String mois, String jour, String annee) throws DateException{
        boolean jourValide = false;

        if ((jour.length()) != 2 || ((parseInt(jour) < 1) || (parseInt(jour) > 31))) {
            throw new DateException("Jour non valide");
        }
        try {
            int moisInt = parseInt(mois);
            int jourInt = parseInt(jour);

            if (jourInt >= 1 && jourInt <= 31) {
                if (moisInt == 1 || moisInt == 3 || moisInt == 5 || moisInt == 7 || moisInt == 8 || moisInt == 10 || moisInt == 12) {
                    if (jourInt >= 1 && jourInt <= 31) {
                        jourValide = true;
                    }    //31 jours
                } else if (moisInt == 4 || moisInt == 6 || moisInt == 9 || moisInt == 11) {
                    if (jourInt >= 1 && jourInt <= 30) {
                        jourValide = true;
                    }    //30 jours

                } else if (moisInt == 2) {

                    if (anneeEstBissextile(annee)) {
                        if (jourInt >= 1 && jourInt <= 29) {
                            jourValide = true;
                        }   //29 jours
                    } else {
                        if (jourInt >= 1 && jourInt <= 28) {
                            jourValide = true;
                        }   //28 jours
                    }
                }
            }
        } catch (NumberFormatException e) {
            throw new DateException("Jour non valide" + e);
        }
        return jourValide;
    }

    /**
     * methode qui valide si annee est bissextile
     * return true si est bissextile
     * @param annee String
     */
    private boolean anneeEstBissextile(String annee){
        boolean estBissextile = true;
        int anneeInt = parseInt(annee);

        if (!((anneeInt % 4 == 0) && (anneeInt % 100 != 0) || (anneeInt % 400 == 0))) {
            //System.out.println(anneeInt + "PAS annee bissextile");
            estBissextile = false;
        }
        return estBissextile;
    }

     /**
     * methode qui supprime le jour d'une date aaaa-mm-jj
     * date est MAJ sans le jour
     * @param date String
     * @throws DateException si date n'est pas valide
     */
    public void supprimerJourDeDate(String date) throws DateException {
         String dateTemp = "";

        if (date.length() != 10) {
            throw new DateException("Changement impossible: jour inexistant");
        }
        String[] tabTemp = new String[dateSepareeTabString(date).length - 1];

        for (int i = 0; i < tabTemp.length; i++) {
            tabTemp[i] = dateSepareeTabString(date)[i];
            if (i == 0) {
                dateTemp = (tabTemp[i] + "-");
            } else {
                dateTemp = dateTemp + (tabTemp[i]);
            }
        }
        date = dateTemp;
        setDate(this.date = date);

    }

    @Override
    public String toString() {
        return date;
    }

}//fin classe Date

class DateException extends Exception {

    public DateException() {
         System.out.println("DateException: methodes dans Date");
    }

    public DateException(String msg) {
        super(msg);
    }
}
