/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import static refund.CalculRemboursements.calculerRemboursement;

/**
 *
 * @author Max
 */
public class SortieTest {
    
    Entree entree;
    Dossier dossier;
    Reclamation reclam1;
    Reclamation reclam2;
    Reclamation reclam3;
    
    String fichierSortie;
    boolean prediction;
    Sortie sortie;   
    @Before
    public void setUp() throws DossierException, DateException, 
            ReclamationException{
        sortie = new Sortie(new MockEntree(), "testRefunds.json", true);     
        
    }
    @After
    public void tearDown() throws Exception{
        Path p = Paths.get("testRefunds.json");
        Files.deleteIfExists(p);
    }
    
    @Test
    public void testSortieEntree()throws Exception{
        MockEntree entree = new MockEntree();       
        assertEquals(entree.toString(), sortie.getEntree().toString());
         
    }
    
    @Test 
    public void testSortieFichierSortie(){
        assertEquals("testRefunds.json", sortie.getFichierSortie());
    }
          
       
    @Test
    public void testEcrireDebut() throws Exception{
        Sortie sortie = new Sortie();
        JSONObject fichier = new JSONObject();
        JSONObject vide = new JSONObject();
        fichier = sortie.ecrireDebut(new Dossier("A123456", new Date("2017-10")));
        vide.accumulate("dossier", "A123456");
        vide.accumulate("mois", "2017-10");
        assertEquals(fichier.toString(), vide.toString());
    }
       
    @Test
    public void testecrireReclamations(){
        ArrayList<Reclamation> liste = new ArrayList<>();
        Sortie sortieTemp = new Sortie();
        Sortie sortieTemp2 = new Sortie();
        JSONObject temp = sortieTemp.ecrireReclamations(liste, dossier);
        JSONObject test = new JSONObject();
        Dollar total = sortieTemp2.ajouterReclamations(liste, dossier);
        test.accumulate("remboursement", sortieTemp2.getListe());
        test.accumulate("total", total.toString());
        
        assertEquals(temp, test);
        
    }    
    
    @Test 
    public void testAjouterReclamations(){
        ArrayList<Reclamation> liste = new ArrayList<>();
        Sortie sortie = new Sortie();
        Sortie temp = new Sortie();
        Dollar test = temp.ajouterReclamations(liste, dossier);
        Dollar dollar = new Dollar();
        for(Reclamation reclam: liste){
            Dollar montant = sortie.ajouterUneReclamation(reclam, dossier);
            dollar = test.plus(montant);
        }
        assertEquals(test.toString(), dollar.toString());
    }
    
    @Test
    public void testAjouterUneReclamationMontant() throws Exception{
        Dollar temp = sortie.ajouterUneReclamation(new Reclamation(100, 
                new Date("2017-01-20"), "100,00$"), 
                new Dossier("A123456", new Date("2017-10")));
        Dollar montant = new Dollar("35.00$");
        
        assertEquals(montant, temp);
    }
      
   
    
    
    
    
    
           
       
}
