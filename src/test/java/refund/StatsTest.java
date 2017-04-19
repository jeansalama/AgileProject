/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author jean
 */
public class StatsTest {
    
    JSONObject soin;
    JSONObject soinVide;
    JSONObject soins;
    JSONObject soinsVides;
    
    @Before
    public void setUp(){
        soin = new JSONObject();
        soin.accumulate("nbTotal", 3);
        soin.accumulate("montantMaximalReclame", 100.0);
        soin.accumulate("totalMontantsReclames", 400.0);
        
        soinVide = new JSONObject();
        soinVide.accumulate("nbTotal", 0);
        soinVide.accumulate("montantMaximalReclame", 0.0);
        soinVide.accumulate("totalMontantsReclames", 0.0);
        
        soinsVides = Stats.initialiserStatsSoins();
        
        soins = Stats.initialiserStatsSoins();
        soins.put("0", soin);
        soins.put("600", soin);
    }
    
    @After
    public void teardown(){
        soin = null;
    }
    
    @Test
    public void testAfficherSoinsNonVides(){
        String contenu = "\n Massothérapie (0) : "
                + "\n    total : 3"
                + "\n    montant maximal réclamé : 100.00$"
                + "\n    moyenne des montants réclamés : 133.33$"
        + "\n Ostéopathie (100) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$"
        + "\n Kinésithérapie (150) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$"
        + "\n Médecin généraliste privé (175) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$"
        + "\n Psychologie individuelle (200) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$"
        + "\n Soins dentaires (300-399) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$"
        + "\n Naturopathie, acuponcture (400) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$"
        + "\n Chiropratie (500) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$"
        + "\n Physiothérapie (600) : "
                + "\n    total : 3"
                + "\n    montant maximal réclamé : 100.00$"
                + "\n    moyenne des montants réclamés : 133.33$"
        + "\n Orthophonie, ergothérapie (700) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$";
        assertEquals(contenu, Stats.afficherSoins(soins));
    }
    
    @Test
    public void testAfficherSoinsVides(){
        String contenu = "\n Massothérapie (0) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$"
        + "\n Ostéopathie (100) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$"
        + "\n Kinésithérapie (150) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$"
        + "\n Médecin généraliste privé (175) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$"
        + "\n Psychologie individuelle (200) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$"
        + "\n Soins dentaires (300-399) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$"
        + "\n Naturopathie, acuponcture (400) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$"
        + "\n Chiropratie (500) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$"
        + "\n Physiothérapie (600) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$"
        + "\n Orthophonie, ergothérapie (700) : "
                + "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$";
        assertEquals(contenu, Stats.afficherSoins(soinsVides));
    }
    
    @Test
    public void testAfficherUnSoinNormal(){
        String contenu = "\n    total : 3"
                + "\n    montant maximal réclamé : 100.00$"
                + "\n    moyenne des montants réclamés : 133.33$";
        assertEquals(contenu, Stats.afficherUnSoin(soin));
    }
    
    @Test
    public void testAfficherUnSoinVide(){
        String contenu = "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$";
        assertEquals(contenu, Stats.afficherUnSoin(soinVide));
    }
    
    @Test(expected = NullPointerException.class)
    public void testAfficherUnSoinNull(){
        Stats.afficherUnSoin(null);
    }
    
}
