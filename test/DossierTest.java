
import org.junit.*;
import static org.junit.Assert.assertEquals;
import refund.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Maxime
 */
public class DossierTest {
    
    private Dossier dossier;

    @Test
    public final void testIdDossier1() throws Exception {
        
        dossier = new Dossier("A000001", new Date("2017-02"));
        assertEquals("A000001", dossier.getIdDossier());
    }
}
