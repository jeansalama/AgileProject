/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import net.sf.json.JSONObject;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author Max
 */
public class SortieTest {

    String fichierSortie;
    boolean prediction;
    Sortie sortie;
    ArrayList<Reclamation> listeReclamations;
    Reclamation reclamation;
    Dossier dossier;

    @Before
    public void setUp() throws Exception {
        sortie = new Sortie(new MockEntree("test"), "testRefunds.json", true);
        
        dossier = new Dossier("A123456", new Date("2017-10"));
        
        listeReclamations = new ArrayList<>();
        reclamation = new Reclamation(200, new Date("2017-10-20"), "100,00$");
        listeReclamations.add(reclamation);
        listeReclamations.add(reclamation);
        
    }

    @After
    public void tearDown() throws Exception {
        sortie = null;
        listeReclamations =null;
        reclamation = null;
        Path p = Paths.get("testRefunds.json");
        Files.deleteIfExists(p);
    }

    @Test
    public void testSortieEntree() throws Exception {
        MockEntree entree = new MockEntree("test");
        assertEquals(entree.toString(), sortie.getEntree().toString());

    }

    @Test
    public void testSortieFichierSortie() {
        assertEquals("testRefunds.json", sortie.getFichierSortie());
    }

    @Test
    public void testEcrireDebut() throws Exception {
        Sortie sortie = new Sortie();
        
        JSONObject fichier = sortie.ecrireDebut(dossier);
        
        JSONObject vide = new JSONObject();
        vide.accumulate("dossier", "A123456");
        vide.accumulate("mois", "2017-10");
        
        assertEquals(fichier.toString(), vide.toString());
    }

    @Test
    public void testecrireReclamations() throws Exception {
        Sortie sortieTemp = new Sortie();
        Sortie sortieTemp2 = new Sortie();
        
        JSONObject temp = sortieTemp.ecrireReclamations(listeReclamations,
                dossier);
        
        JSONObject test = new JSONObject();
        Dollar total = sortieTemp2.ajouterReclamations(listeReclamations,
                dossier);
        test.accumulate("remboursement", sortieTemp2.getListe());
        test.accumulate("total", total.toString());

        assertEquals(temp, test);
    }

    @Test
    public void testAjouterReclamations() throws Exception {
        
        Sortie sortie = new Sortie();
        Sortie sortieTmp = new Sortie();
        
        Dollar montantTotal = sortieTmp.ajouterReclamations(listeReclamations,
                dossier);
        
        Dollar total = new Dollar();
        for (Reclamation reclam : listeReclamations) {
            Dollar montant = sortie.ajouterUneReclamation(reclam, dossier);
            total = total.plus(montant);
        }
        assertEquals(montantTotal.toString(), total.toString());
    }

    @Test
    public void testAjouterUneReclamationMontant() throws ReclamationException, 
            DateException, DossierException  {
        Sortie sortie = new Sortie();
        
        Dollar montantAjoute = sortie.ajouterUneReclamation(reclamation,
                dossier);
        
        Dollar montant = new Dollar("25.00$");

        assertEquals(montant, montantAjoute);
    }

}
