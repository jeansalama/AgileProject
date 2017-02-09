/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import static java.lang.Integer.parseInt;

/**
 * Classe cree et gere des objets Date.
 *
 * @author Lucignano Veronique
 */
public class Date {

    private String date = "9999";
    private String annee;
    private String mois;
    private String jour;

    /**
     * Ce constructeur cree une date avec son type
     *
     * @param type un String d'un caractere parmi 'A', 'B', 'C', 'D'
     * @throws ContratException si le type du contrat n'est pas valide
     */
    public Date() throws DateException {
        // System.out.println("constructeur sans parametre : " + this.date);
        this.date = date;
        this.annee = annee;
        this.mois = mois;
        this.jour = jour;

    }

    public Date(String date) throws DateException, Exception {
        setDate(date);
        this.date = date;
        this.annee = annee;
        this.mois = mois;
        this.jour = jour;
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

    public String getDate() throws Exception {
        if ("9999".equals(this.date)) {
            date = "";
        } else if ("".equals(this.jour)) {
            date = this.annee + "-" + this.mois;
        } else {
            date = this.annee + "-" + this.mois + "-" + this.jour;
        }
        return date;
    }

    public final void setDate(String date) throws Exception {

        if (dateLongueur(date) == true) {
            dateSepareeTabString(date);

            annee = dateSepareeTabString(date)[0];
            mois = dateSepareeTabString(date)[1];
            if ((dateSepareeTabString(date)).length == 3) {
                jour = dateSepareeTabString(date)[2];
            }
            if (validerAnnee(annee)) {
                setAnnee(annee);
                if (validerMois(mois)) {
                    setMois(mois);
                    if ((dateSepareeTabString(date)).length == 3) {
                        if (validerJour(mois, jour, annee)) {
                            setJour(jour);
                        }
                    }
                }
            }
        }
        this.date = date;
    }

    public void setAnnee(String annee) throws DateException {
        if (validerAnnee(annee)) {
            this.annee = annee;
        }
    }

    public void setMois(String mois) throws DateException {
        if (validerMois(mois)) {
            this.mois = mois;
        }
    }

    public final void setJour(String jour) throws DateException {
        if (validerJour(mois, jour, annee)) {
            this.jour = jour;
        }
    }

    private static boolean dateLongueur(String date) throws DateException {
        boolean estLongueur = true;

        if (!(date.length() == 7 || date.length() == 10) || date == "9999") {
            System.out.println("Date non valide");
            System.exit(1);
        }
        return estLongueur;
    }

       private static String[] dateSepareeTabString(String date) throws DateException {
        String[] tabDateEstSeparee;

        if (!(date.indexOf('-') > -1)) {
            throw new DateException("Date non valide separateurs");
        }
        String separateurs = "[-]";
        tabDateEstSeparee = date.split(separateurs);
        return tabDateEstSeparee;
    }

    private boolean validerAnnee(String annee) throws DateException{
        boolean anneeValide = true;
        if ((annee.length()) != 4 || parseInt(annee) < 2000) {
            System.exit(0);
        }
        return anneeValide;
    }

    private boolean validerMois(String mois) throws DateException{
        boolean moisValide = true;
        if ((mois.length()) != 2 || ((parseInt(mois) < 1) || (parseInt(mois) > 12))) {
            System.exit(0);
        }
        return moisValide;
    }

    private boolean validerJour(String mois, String jour, String annee) throws DateException{
        boolean jourValide = false;

        if ((jour.length()) != 2 || ((parseInt(jour) < 1) || (parseInt(jour) > 31))) {
            System.exit(0);
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
            System.out.println(e);
        }
        return jourValide;
    }

    private boolean anneeEstBissextile(String annee) throws DateException{
        boolean estBissextile = true;
        int anneeInt = parseInt(annee);

        if (!((anneeInt % 4 == 0) && (anneeInt % 100 != 0) || (anneeInt % 400 == 0))) {
            //System.out.println(anneeInt + "PAS annee bissextile");
            estBissextile = false;
        }
        return estBissextile;
    }

    public void supprimerJourDeDate(String date) throws Exception {
        // d3 = new Date2("2017-05");
        String dateTemp = "";

        if (date.length() != 10) {
            System.out.println("Changement impossible: jour inexistant");
            System.exit(1);
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
        if ((this.date).length() == 7) {
            date = annee + "-" + mois;
        } else if ((this.date).length() == 10) {
            date = annee + "-" + mois + "-" + jour;
        } else {
            if (date.equals("")) {
                date = "9999";
            }
        }
        return "Date : " + date;
    }

}//fin classe Date

class DateException extends Exception {

    public DateException() {
    }

    public DateException(String msg) {
        super(msg);
    }
}
