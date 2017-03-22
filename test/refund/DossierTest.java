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
    
}
