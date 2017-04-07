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

    public final static String MSG_DATE_EXCEPTION = "La date est invalide. "
            + "Le format de date accepte est aaaa-mm ou aaaa-mm-jj.";
    private String date;
    private String annee;
    private String mois;
    private String jour;

    /**
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

    public final void setDate(String date) throws DateException {
        if (date == null) {
            throw new DateException(MSG_DATE_EXCEPTION);
        }
        validerFormatDate(date);
        this.date = date;
    }

    public void setAnnee(String annee) throws DateException {
        if (!validerAnnee(annee)) {
            throw new DateException(MSG_DATE_EXCEPTION);
        }
        this.annee = annee;
    }

    public void setMois(String mois) throws DateException {
        if (!validerMois(mois)) {
            throw new DateException(MSG_DATE_EXCEPTION);
        }
        this.mois = mois;
    }

    public final void setJour(String jour) throws DateException {
        if (!validerJour(mois, jour, annee)) {
            throw new DateException(MSG_DATE_EXCEPTION);
        }
        this.jour = jour;
    }

    /**
     * 
     * @return vrai si la longueur de la date est de 10 caracteres sinon faux
     */
    public boolean contientUnJour() {
        return date.length() == 10;
    }

    /**
     *
     * @param date
     * @throws DateException si date pas numerique ou mauvaise longueuer
     */
    private void validerFormatDate(String date) throws DateException {
        String[] tab;
        if (validerLongueurDate(date)) {
            tab = SeparerDateTableauString(date);
            setAnnee(tab[0]);
            setMois(tab[1]);
            if (tab.length == 3) {
                setJour(tab[2]);
            }
        } else {
            throw new DateException(MSG_DATE_EXCEPTION);
        }
    }

    /**
     *
     * @param date
     * @return vrai si longueur est valide sinon faux
     */
    private boolean validerLongueurDate(String date) {
        boolean estLongueur = true;
        if (!(date.length() == 7 || date.length() == 10)) {
            estLongueur = false;
        }
        return estLongueur;
    }

  /**
   * 
   * @param date
   * @return la date separee dans un tableau de chaine de caracteres
   * @throws DateException si les separateurs ne correspondent pas a '-'
     */
    private static String[] SeparerDateTableauString(String date)
            throws DateException {
        String[] tabDateEstSeparee;

        if ((date.indexOf('-') == -1)) {
            throw new DateException(MSG_DATE_EXCEPTION);
        }
        String separateurs = "[-]";
        tabDateEstSeparee = date.split(separateurs);
        return tabDateEstSeparee;
    }

    /**
     *
     * @param annee
     * @return
     * @throws DateException si annee pas numerique ou mauvaise longueuer
     */
    private boolean validerAnnee(String annee) throws DateException {
        boolean anneeValide = true;
        try {
            if (annee.length() != 4 || parseInt(annee) < 2000) {
                throw new DateException(MSG_DATE_EXCEPTION);
            }
        } catch (NumberFormatException nfe) {
            throw new DateException(MSG_DATE_EXCEPTION);
        }
        return anneeValide;
    }

    /**
     * 
     * @param mois
     * @return
     * @throws DateException si mois pas numerique ou mauvaise longueuer
     */
    private boolean validerMois(String mois) throws DateException {
        boolean moisValide = true;
        try {
            if ((mois.length()) != 2 || ((parseInt(mois) < 1)
                    || (parseInt(mois) > 12))) {
                throw new DateException(MSG_DATE_EXCEPTION);
            }
        } catch (NumberFormatException e) {
            throw new DateException(MSG_DATE_EXCEPTION);
        }
        return moisValide;
    }

    /**
     *
     * @param mois
     * @param jour
     * @param annee
     * @return
     * @throws DateException si jour pas numerique ou mauvaise longueuer
     */
    private boolean validerJour(String mois, String jour, String annee)
            throws DateException {
        boolean jourValide = false;
        try {
            if ((jour.length()) != 2 || ((parseInt(jour) < 1)
                    || (parseInt(jour) > 31))) {
                throw new DateException(MSG_DATE_EXCEPTION);
            }
            jourValide = validerJourParMois(parseInt(jour), parseInt(mois),
                    parseInt(annee), jourValide);

        } catch (NumberFormatException e) {
            throw new DateException(MSG_DATE_EXCEPTION);
        }
        return jourValide;
    }

    /**
     *
     * @param jourInt
     * @param moisInt
     * @param anneeInt
     * @param jourValide
     * @return vrai si jour valide par rapport au mois sinon faux
     */
    private boolean validerJourParMois(int jourInt, int moisInt,
            int anneeInt, boolean jourValide) {
        if (moisInt == 4 || moisInt == 6 || moisInt == 9 || moisInt == 11
                && jourInt >= 1 && jourInt <= 30) {
            jourValide = true;
        } else if (moisInt == 2) {
            jourValide = validerJourParAnnee(anneeInt, jourInt, jourValide);
        } else {
            jourValide = true;
        }
        return jourValide;
    }

    /**
     *
     * @param anneeInt
     * @param jourInt
     * @param jourValide
     * @return vrai si jour valide par rapport a l'annee sinon faux
     */
    private boolean validerJourParAnnee(int anneeInt, int jourInt,
            boolean jourValide) {
        if (estAnneeBissextile(anneeInt) && (jourInt >= 1 && jourInt <= 29)) {
            jourValide = true;
        } else {
            if (jourInt >= 1 && jourInt <= 28) {
                jourValide = true;
            }
        }
        return jourValide;
    }

    /**
     *
     * @param anneeInt
     * @return vrai si c'est une annee bissextile sinon faux
     */
    private boolean estAnneeBissextile(int anneeInt) {
        boolean estBissextile = true;
        if (!((anneeInt % 4 == 0) && (anneeInt % 100 != 0)
                || (anneeInt % 400 == 0))) {
            estBissextile = false;
        }
        return estBissextile;
    }

    @Override
    public String toString() {
        return date;
    }

}//fin classe Date

class DateException extends Exception {

    public DateException() {
    }

    public DateException(String msg) {
        super(msg);
    }
}