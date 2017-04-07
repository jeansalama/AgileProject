/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import java.text.DecimalFormat;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jean
 */
public class MontantFormatTest {

    public MontantFormatTest() {
    }

    /**************************************************
     *
     * double analyserMontant(String montantReclamation) throws
     * ReclamationException
     *
     ***************************************************/
    
    @Test
    public void testAnalyserReclamationMontantValide()
            throws ReclamationException {
        assertTrue(MontantFormat.analyserMontant("1,25$") == 1.25);
    }

    @Test
    public void testAnalyserReclamationMontantNegatifValide()
            throws ReclamationException {
        assertTrue(MontantFormat.analyserMontant("-1,25$") == -1.25);
    }

    @Test(expected = ReclamationException.class)
    public void testAnalyserReclamationMontantSansDevise()
            throws ReclamationException {
        MontantFormat.analyserMontant("1,25");
    }

    @Test(expected = ReclamationException.class)
    public void testAnalyserReclamationPasUnNombre()
            throws ReclamationException {
        MontantFormat.analyserMontant("1*25$");
    }

    @Test(expected = ReclamationException.class)
    public void testAnalyserReclamationMontantChaineVide()
            throws ReclamationException {
        MontantFormat.analyserMontant("");
    }

    @Test(expected = NullPointerException.class)
    public void testAnalyserReclamationArgumentNull()
            throws ReclamationException {
        MontantFormat.analyserMontant(null);
    }

    /**************************************************
     *
     * DecimalFormat obtenirBonAnalyseur(String montant)
     *
     **************************************************/
    
    @Test
    public void tesObtenirBonAnalyseurPoint() {
        DecimalFormat analyseur = MontantFormat.obtenirBonAnalyseur("1.2");
        char separateur
                = analyseur.getDecimalFormatSymbols().getDecimalSeparator();
        assertEquals(separateur, '.');
    }

    @Test
    public void tesObtenirBonAnalyseurVirgule() {
        DecimalFormat analyseur = MontantFormat.obtenirBonAnalyseur("1,2");
        char separateur
                = analyseur.getDecimalFormatSymbols().getDecimalSeparator();
        assertEquals(separateur, ',');
    }

    @Test
    public void tesObtenirBonAnalyseurParDefaut() {
        DecimalFormat analyseur = MontantFormat.obtenirBonAnalyseur("15");
        char separateur
                = analyseur.getDecimalFormatSymbols().getDecimalSeparator();
        assertEquals(separateur, '.');
    }

    /**************************************************
     *
     * void verifierFormatMontant(String montantReclam) throws
     * ReclamationException
     *
     ****************************************************/
    
    @Test
    public void testVerifierFormatMontantValide() throws ReclamationException {
        MontantFormat.verifierFormatMontant("1.25$");
    }

    @Test
    public void testVerifierFormatMontantValide2() throws ReclamationException {
        MontantFormat.verifierFormatMontant("1,25$");
    }

    @Test
    public void testVerifierFormatMontantValide3() throws ReclamationException {
        MontantFormat.verifierFormatMontant("1.00$");
    }

    @Test
    public void testVerifierFormatMontantValide4() throws ReclamationException {
        MontantFormat.verifierFormatMontant("1,00$");
    }

    @Test
    public void testVerifierFormatMontantValide5() throws ReclamationException {
        MontantFormat.verifierFormatMontant("0,00$");
    }

    @Test
    public void testVerifierFormatMontantValide6() throws ReclamationException {
        MontantFormat.verifierFormatMontant("0000,00$");
    }

    @Test(expected = ReclamationException.class)
    public void testVerifierFormatMontantInvalideSansDevise()
            throws ReclamationException {
        MontantFormat.verifierFormatMontant("2,58");
    }

    @Test(expected = ReclamationException.class)
    public void testVerifierFormatMontantInvalideAutreDevise()
            throws ReclamationException {
        MontantFormat.verifierFormatMontant("2,58£");
    }

    @Test(expected = ReclamationException.class)
    public void testVerifierFormatMontantInvalideSansSeparateur()
            throws ReclamationException {
        MontantFormat.verifierFormatMontant("258$");
    }

    @Test(expected = ReclamationException.class)
    public void testVerifierFormatMontantInvalideSansPartieEntiere()
            throws ReclamationException {
        MontantFormat.verifierFormatMontant(",58$");
    }

    @Test(expected = ReclamationException.class)
    public void testVerifierFormatMontantInvalideSansPartieFractionnaire()
            throws ReclamationException {
        MontantFormat.verifierFormatMontant("2.$");
    }

    @Test(expected = ReclamationException.class)
    public void testVerifierFormatMontantInvalidePartieFractionnaireCourte()
            throws ReclamationException {
        MontantFormat.verifierFormatMontant("2.1$");
    }

    @Test(expected = ReclamationException.class)
    public void testVerifierFormatMontantInvalidePartieFractionnaireLongue()
            throws ReclamationException {
        MontantFormat.verifierFormatMontant("2.123$");
    }

    /****************************************************
     *
     * int indiceSeparateurDecimal(String montantReclam)
     *
     ****************************************************/
    
    @Test
    public void testIndiceSeparateurDecimalPoint() {
        assertEquals(MontantFormat.indiceSeparateurDecimal("1.2$"), 1);
    }

    @Test
    public void testIndiceSeparateurDecimalPoint2() {
        assertEquals(MontantFormat.indiceSeparateurDecimal("1569.2$"), 4);
    }

    @Test
    public void testIndiceSeparateurDecimalVirgule() {
        assertEquals(MontantFormat.indiceSeparateurDecimal("159,2$"), 3);
    }

    @Test
    public void testIndiceSeparateurDecimalVirgule2() {
        assertEquals(MontantFormat.indiceSeparateurDecimal("15,2$"), 2);
    }

    @Test
    public void testIndiceSeparateurDecimalInexistant() {
        assertEquals(MontantFormat.indiceSeparateurDecimal("15692$"), -1);
    }

    @Test(expected = NullPointerException.class)
    public void testIndiceSeparateurDecimalArgumentNull() {
        MontantFormat.indiceSeparateurDecimal(null);
    }

    /***************************************************
     *
     * boolean contientSeparateurDecimal(String montantReclam)
     *
     ***************************************************/
    
    @Test
    public void testContientSeparateurDecimalPoint() {
        assertTrue(MontantFormat.contientSeparateurDecimal("1.2"));
    }

    @Test
    public void testContientSeparateurDecimalVirgule() {
        assertTrue(MontantFormat.contientSeparateurDecimal("1,2"));
    }

    @Test
    public void testNeContientPasSeparateurDecimal() {
        assertFalse(MontantFormat.contientSeparateurDecimal("12"));
    }

    @Test(expected = NullPointerException.class)
    public void testContientSeparateurDecimalArgumentNull() {
        MontantFormat.contientSeparateurDecimal(null);
    }

    /**************************************************
     *
     * int taillePartieEntiere(String montantReclam)
     *
     **************************************************/
    
    @Test
    public void testTaillePartieEntiere() {
        assertEquals(MontantFormat.taillePartieEntiere("1.23$"), 1);
    }

    @Test
    public void testTaillePartieEntiere2() {
        assertEquals(MontantFormat.taillePartieEntiere("10.23$"), 2);
    }

    @Test
    public void testTaillePartieEntiere3() {
        assertEquals(MontantFormat.taillePartieEntiere("0010.23$"), 4);
    }

    @Test
    public void testTaillePartieEntiere4() {
        assertEquals(MontantFormat.taillePartieEntiere("0.23$"), 1);
    }

    @Test
    public void testTaillePartieEntiere5() {
        assertEquals(MontantFormat.taillePartieEntiere(".23$"), 0);
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testTaillePartieEntiereIndexOutOfBoundsException() {
        MontantFormat.taillePartieEntiere("25$");
    }

    @Test(expected = NullPointerException.class)
    public void testTaillePartieEntiereArgumentNull() {
        MontantFormat.taillePartieEntiere(null);
    }

    /***************************************************
     *
     * int taillePartieFractionnaire(String montantReclam)
     *
     ****************************************************/
    
    @Test
    public void testTaillePartieFractionnaire() {
        assertEquals(MontantFormat.taillePartieFractionnaire("1.23$"), 2);
    }

    @Test
    public void testTaillePartieFractionnaire2() {
        assertEquals(MontantFormat.taillePartieFractionnaire("1.2345$"), 4);
    }

    @Test
    public void testTaillePartieFractionnaire3() {
        assertEquals(MontantFormat.taillePartieFractionnaire("1.2$"), 1);
    }

    @Test
    public void testTaillePartieFractionnaire4() {
        assertEquals(MontantFormat.taillePartieFractionnaire("1.$"), 0);
    }

    @Test
    public void testTaillePartieFractionnaire5() {
        assertEquals(MontantFormat.taillePartieFractionnaire("1$"), 0);
    }

    @Test(expected = NullPointerException.class)
    public void testTaillePartieFractionnaireArgumentNull() {
        MontantFormat.taillePartieFractionnaire(null);
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testTaillePartieFractionnaireOutOfBoundsException() {
        MontantFormat.taillePartieFractionnaire("1.");
    }

    /***************************************************
     *
     * int indiceDevise(String montantReclam)
     *
     ***************************************************/
    
    @Test
    public void testIndiceDeviseALaFin() {
        String montant = "12$";
        assertEquals(MontantFormat.indiceDevise(montant), montant.length() - 1);
    }

    @Test
    public void testIndiceDeviseSansDevise() {
        String montant = "12";
        assertEquals(MontantFormat.indiceDevise(montant), -1);
    }

    @Test
    public void testIndiceDeviseAuDebut() {
        String montant = "$2";
        assertEquals(MontantFormat.indiceDevise(montant), 0);
    }

    @Test
    public void testIndiceDeviseAuMilieu() {
        String montant = "1$2";
        assertEquals(MontantFormat.indiceDevise(montant), 1);
    }

    @Test(expected = NullPointerException.class)
    public void testIndiceDeviseArgumentNull() {
        MontantFormat.indiceDevise(null);
    }

    /***************************************************
     *
     * boolean validerIndiceDevise(String montantReclam)
     *
     ****************************************************/
    
    @Test
    public void testValiderIndiceDeviseTrue() {
        assertTrue(MontantFormat.validerIndiceDevise("12$"));
    }

    @Test
    public void testValiderIndiceDeviseFalse() {
        assertFalse(MontantFormat.validerIndiceDevise("$12"));
    }

    @Test
    public void testValiderIndiceDeviseFalse2() {
        assertFalse(MontantFormat.validerIndiceDevise("12"));
    }

    @Test
    public void testValiderIndiceDeviseFalse3() {
        assertFalse(MontantFormat.validerIndiceDevise("1$2"));
    }

    @Test(expected = NullPointerException.class)
    public void testValiderIndiceDeviseArgumentNull() {
        MontantFormat.validerIndiceDevise(null);
    }

}
