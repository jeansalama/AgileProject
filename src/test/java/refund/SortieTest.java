/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import net.sf.json.JSONObject;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author Max
 */
public class SortieTest {
    
    Entree entree;
    String fichierSortie;
    boolean prediction;
    Sortie sortie;
    JSONObject fichier = new JSONObject();
    
    @Before
    public void setUp() throws DossierException, DateException, 
            ReclamationException{
        entree = new MockEntree();
        fichierSortie = "refunds.json";
        prediction = false;
        sortie = new Sortie(entree, fichierSortie, prediction);
        
    }
    
    @Test
    public void testEcrireDebut(){
        sortie.ecrireDebut();
        fichier.accumulate("dossier", "A123456");
        fichier.accumulate("mois", "2017-10");
        assertEquals(fichier.toString(), sortie.getInfoClient().toString());
        
    }
    
    @Test
    public void testAjouterReclamations(){
        Dollar temp = new Dollar(3.50);
        assertEquals(temp, sortie.ajouterReclamations());
    }
           
       
}
