/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import net.sf.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Billy
 */
public class ContratTest {

    public ContratTest() {
    }

    @Test
    public void testContratConstructeur() throws ContratException {
        Contrat contrat0 = new Contrat("B");
        assertNotNull(contrat0.getType());
    }

    @Test(expected = ContratException.class)
    public void testContratConstructeurNonValideTypeTropLong() throws ContratException {
        Contrat contrat0 = new Contrat("CC");
    }

    @Test(expected = ContratException.class)
    public void testContratConstructeurTypeNonValide() throws ContratException {
        Contrat contrat1 = new Contrat("X");
    }

    @Test(expected = ContratException.class)
    public void testContratSetTypeNonValideNull() throws ContratException {
        Contrat contrat2 = new Contrat("A");
        contrat2.setType(null);
    }

    @Test(expected = ContratException.class)
    public void testContratSetTypeNonValide() throws ContratException {
        Contrat contrat4 = new Contrat("");
        contrat4.setType("i");
    }

    @Test
    public void testContratToString() throws ContratException {
        Contrat contrat5 = new Contrat("E");
        assertEquals("E", contrat5.toString());
    }

    @Test
    public void testContratGetType() throws ContratException {
        Contrat contrat6 = new Contrat("A");
        assertEquals("A", contrat6.getType());
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

}
