/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import static refund.Entree.*;

/**
 *
 * @author Billy
 */
public class EntreeTest {

    JSONObject reclamation = new JSONObject();

    @After
    public void tearDown() throws Exception{
        reclamation = null;
    }

    @Test
    public void testObtenirSoinValide() throws ReclamationException {
        int obtenu;
        reclamation.accumulate("soin", 100);
        obtenu = Entree.obtenirSoin(reclamation);
        assertEquals(100, obtenu);
    }

    @Test(expected = ReclamationException.class)
    public void testObtenirSoinCléeInvalide() throws ReclamationException {
        int obtenu;
        reclamation.accumulate("soins", 100);
        obtenu = Entree.obtenirSoin(reclamation);
    }

    @Test(expected = ReclamationException.class)
    public void testObtenirSoinCléeValeurInvalide() throws ReclamationException {
        int obtenu;
        reclamation.accumulate("soins", "aaaa");
        obtenu = Entree.obtenirSoin(reclamation);
    }

    @Test(expected = ReclamationException.class)
    public void testObtenirSoinValeurInvalide() throws ReclamationException {
        int obtenu;
        reclamation.accumulate("soin", "aaaaa");
        obtenu = Entree.obtenirSoin(reclamation);
    }

    @Test(expected = ReclamationException.class)
    public void testObtenirSoinCléeValidePasSeule() throws ReclamationException {
        int obtenu;
        reclamation.accumulate("soin aaaaa", 100);
        obtenu = Entree.obtenirSoin(reclamation);
    }

    @Test
    public void testValiderNbrJourAccepteDansReclamation() throws ReclamationException {
        ArrayList testJourNbrReclamation = new ArrayList(9);
        testJourNbrReclamation.add("09, 03, 03, 05, 03, 08, 06, 08, 03");
        validerNbrJourAccepteDansReclamation(testJourNbrReclamation);
    }

    @Test(expected=DateException.class)
    public void testAjouterUneReclamationException() throws Exception, DateException, ReclamationException {
        Entree entree = new Entree();
        Date date = new Date("2017-01-03");
        
        reclamation.accumulate("soin", 100);
        reclamation.accumulate("date", date);
        reclamation.accumulate("montant", "10.00$");

        entree.ajouterUneReclamation(reclamation);
    }
    
    @Test
    public void testAjouterUneReclamationListe()throws Exception{
        Entree entree = new Entree();
        Reclamation reclam = new Reclamation(100, new Date("2017-01-11"), "100.00$");
        ArrayList<Reclamation> liste = new ArrayList<>();
        liste.add(reclam);
        JSONObject temp = new JSONObject();
        temp.accumulate("soin", 100);
        temp.accumulate("date", "2017-01-11");
        temp.accumulate("montant", "100.00$");
        entree.ajouterUneReclamation(temp);
        assertEquals(entree.getListeReclamations().toString(), liste.toString());
                
    }
    
    @Test(expected=ReclamationException.class)
    public void testValiderNbrJoursReclamesException() 
            throws ReclamationException, DateException {
        Reclamation reclam = new Reclamation(100, new Date("2017-01-11"), "100.00$");
        ArrayList<Reclamation> liste = new ArrayList<>();
        liste.add(reclam);
        liste.add(reclam);
        Entree.validerNbrJoursReclames(liste, reclam, 3);
    }
    
    @Test(expected=ReclamationException.class)
    public void testValidationBonMoisException() throws Exception{
        Entree entree = new Entree();
        Date date = new Date("2017-02-11");
        entree.validationBonMois(date);
        
    }
    
    @Test
    public void testLireFichier()throws IOException{
        JSONObject temp = new JSONObject();
        temp.accumulate("test", 0);
        Utf8File.saveStringIntoFile("test.json", temp.toString(2));
        String test = lireFichier("test.json");
        Path p = Paths.get("test.json");
        Files.delete(p);
        assertEquals("{\"test\": 0}", test);
         
    }
    
    @Test
    public void testLireFichierException(){
        String temp = lireFichier("test.json");
        assertEquals(temp, null);
    }
}
