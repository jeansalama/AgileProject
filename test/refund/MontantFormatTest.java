/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jean
 */
public class MontantFormatTest {
    
    public MontantFormatTest() {
    }

    
    
    /****************************************************
     * 
     * int taillePartieFractionnaire(String montantReclam)
     * 
     *****************************************************/
    
    @Test
    public void testTaillePartieFractionnaire(){
        assertEquals(MontantFormat.taillePartieFractionnaire("1.23$"), 2);
    }
    
    @Test
    public void testTaillePartieFractionnaire2(){
        assertEquals(MontantFormat.taillePartieFractionnaire("1.2345$"), 4);
    }
    
    @Test
    public void testTaillePartieFractionnaire3(){
        assertEquals(MontantFormat.taillePartieFractionnaire("1.2$"), 1);
    }
    
    @Test
    public void testTaillePartieFractionnaire4(){
        assertEquals(MontantFormat.taillePartieFractionnaire("1.$"), 0);
    }
    
    @Test
    public void testTaillePartieFractionnaire5(){
        assertEquals(MontantFormat.taillePartieFractionnaire("1$"), 0);
    }
    
    @Test(expected = NullPointerException.class)
    public void testTaillePartieFractionnaireNull(){
        MontantFormat.taillePartieFractionnaire(null);
    }
    
    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testTaillePartieFractionnaireOutOfBoundsException(){
        MontantFormat.taillePartieFractionnaire("1.");
    }
    
    /****************************************************
     * 
     * int indiceDevise(String montantReclam)
     * 
     *****************************************************/
    
    @Test
    public void testIndiceDeviseALaFin(){
        String montant = "12$";
        assertEquals(MontantFormat.indiceDevise(montant), montant.length()-1);
    }
    
    @Test
    public void testIndiceDeviseSansDevise(){
        String montant = "12";
        assertEquals(MontantFormat.indiceDevise(montant), -1);
    }
    
    @Test
    public void testIndiceDeviseAuDebut(){
        String montant = "$2";
        assertEquals(MontantFormat.indiceDevise(montant), 0);
    }
    
    @Test
    public void testIndiceDeviseAuMilieu(){
        String montant = "1$2";
        assertEquals(MontantFormat.indiceDevise(montant), 1);
    }
    
    @Test(expected = NullPointerException.class)
    public void testIndiceDeviseNull(){
        MontantFormat.indiceDevise(null);
    }
    
    /****************************************************
     * 
     * boolean validerIndiceDevise(String montantReclam)
     * 
     *****************************************************/
    
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
    public void testValiderIndiceDeviseNull() {
        MontantFormat.validerIndiceDevise(null);
    }
    
}
