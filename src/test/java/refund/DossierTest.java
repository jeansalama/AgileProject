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
 * @author Billy
 */
public class DossierTest {

    public DossierTest() {
    }

    @Test(expected = DossierException.class)
    public void testContatTyeNonValideSetIdDossier1() throws DossierException, DateException {
        Date date = new Date("2017-02-03");
        Dossier d1 = new Dossier("A123456", date);
        d1.setIdDossier("Z123456");
    }

    @Test(expected = DossierException.class)
    public void testContatTyeNonValideSetIdDossier2() throws DossierException, DateException {
        Date date = new Date("2017-02-03");
        Dossier d1 = new Dossier("A123456", date);
        d1.setIdDossier("-123456");
    }

    @Test
    public void testDossierGetDate() throws DateException, DossierException {
        Dossier d3 = new Dossier("B654321", new Date("2017-02-07"));
        Date date = d3.getDate();
        assertEquals(date, d3.getDate());
    }

    @Test(expected = DossierException.class)
    public void testSetIdDossierDifferentDeLongueurSept1() throws DateException, DossierException {
        Dossier d4 = new Dossier("C333333", new Date("2017-12-17"));
        d4.setIdDossier("C3333337");
    }

    @Test(expected = DossierException.class)
    public void testSetIdDossierDifferentDeLongueurSept2() throws DateException, DossierException {
        Dossier d4 = new Dossier("C3333337", new Date("2017-12-01"));
        d4.setIdDossier("A1233");
    }

    @Test
    public void testDossierToString() throws DossierException, DateException {
        Dossier d5 = new Dossier("B456789", new Date("2017-08-08"));
        assertEquals(("dossier: " + "B456789" + "\nMois: " + d5.getDate()), d5.toString());
    }
    
    @Test
    public void testDossierGetIdDossier()throws DossierException, DateException{
        Dossier d6 = new Dossier("C123456", new Date("2017-03-03"));
       
        assertEquals("C123456", d6.getIdDossier());
    }
}
