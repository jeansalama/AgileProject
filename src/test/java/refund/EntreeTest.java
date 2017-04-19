/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Billy
 */
public class EntreeTest {
    JSONObject reclamation = new JSONObject();
   
        
       

    
    @After
    public void tearDown() {
        reclamation = null;
        
    }
    
    @Test
    public void testObtenirSoinValide() throws ReclamationException{
        int obtenu;
        reclamation.accumulate("soin", 100);
         obtenu = Entree.obtenirSoin(reclamation);
         assertEquals(100, obtenu);
    }
    
    @Test(expected = ReclamationException.class)
    public void testObtenirSoinCléeInvalide() throws ReclamationException{
        int obtenu;
        reclamation.accumulate("soins", 100);
         obtenu = Entree.obtenirSoin(reclamation);
    }
    
    @Test(expected = ReclamationException.class)
    public void testObtenirSoinCléeValeurInvalide() throws ReclamationException{
        int obtenu;
        reclamation.accumulate("soins", "aaaa");
         obtenu = Entree.obtenirSoin(reclamation);
    }
    
    @Test(expected = ReclamationException.class)
    public void testObtenirSoinValeurInvalide() throws ReclamationException{
        int obtenu;
        reclamation.accumulate("soin", "aaaaa");
         obtenu = Entree.obtenirSoin(reclamation);
    }
    
    @Test(expected = ReclamationException.class)
    public void testObtenirSoinCléeValidePasSeule() throws ReclamationException{
        int obtenu;
        reclamation.accumulate("soin aaaaa", 100);
         obtenu = Entree.obtenirSoin(reclamation);
    }

}
    

