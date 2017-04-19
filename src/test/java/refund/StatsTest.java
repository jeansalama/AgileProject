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
import static refund.Stats.LISTE_SOIN;

/**
 *
 * @author jean
 */
public class StatsTest {

    JSONObject soin;
    JSONObject soinVide;
    JSONObject soins;
    JSONObject soinsVides;
    JSONObject reclamations;
    JSONObject reclamationsVides;
    JSONObject stats;
    JSONObject statsVides;

    @Before
    public void setUp() {
        soin = new JSONObject();
        soin.accumulate("nbTotal", 3);
        soin.accumulate("montantMaximalReclame", 100.0);
        soin.accumulate("totalMontantsReclames", 400.0);

        soinVide = new JSONObject();
        soinVide.accumulate("nbTotal", 0);
        soinVide.accumulate("montantMaximalReclame", 0.0);
        soinVide.accumulate("totalMontantsReclames", 0.0);

        soinsVides = new JSONObject();
        for (String element : LISTE_SOIN) {
            soinVide = new JSONObject();
            soinVide.accumulate("nbTotal", 0);
            soinVide.accumulate("montantMaximalReclame", 0.0);
            soinVide.accumulate("totalMontantsReclames", 0.0);

            soinsVides.accumulate(element, soinVide);
        }

        soins = Stats.initialiserStatsSoins();
        soins.put("0", soin);
        soins.put("600", soin);

        reclamations = new JSONObject();
        reclamations.accumulate("traitees", 1);
        reclamations.accumulate("rejetees", 1);

        reclamationsVides = new JSONObject();
        reclamationsVides.accumulate("traitees", 0);
        reclamationsVides.accumulate("rejetees", 0);

        stats = new JSONObject();
        stats.accumulate("Reclamations", reclamations);
        stats.accumulate("Soins", soins);

        statsVides = new JSONObject();
        statsVides.accumulate("Reclamations", reclamationsVides);
        statsVides.accumulate("Soins", soinsVides);
    }

    @After
    public void teardown() {
        soin = null;
        soinVide = null;
        soins = null;
        soinsVides = null;
        reclamations = null;
        reclamationsVides = null;
        stats = null;
        statsVides = null;
    }

    @Test
    public void testReclamValide() {
        Stats.reclamValide(stats);
        JSONObject reclamations = stats.getJSONObject("Reclamations");

        JSONObject reclamationsTmp = new JSONObject();
        reclamationsTmp = new JSONObject();
        reclamationsTmp.accumulate("traitees", 2);
        reclamationsTmp.accumulate("rejetees", 1);

        assertEquals(reclamationsTmp, reclamations);
    }

    @Test
    public void testReclamRejetee() {
        Stats.reclamRejetee(stats);
        JSONObject reclamations = stats.getJSONObject("Reclamations");

        JSONObject reclamationsTmp = new JSONObject();
        reclamationsTmp = new JSONObject();
        reclamationsTmp.accumulate("traitees", 1);
        reclamationsTmp.accumulate("rejetees", 2);

        assertEquals(reclamationsTmp, reclamations);
    }

    @Test
    public void testAjoutSoinStatsMontantReclamePlusGrand() {
        Dollar montantReclame = new Dollar(200.0);
        Stats.ajoutSoinStats(0, montantReclame, stats);

        JSONObject soins = stats.getJSONObject("Soins");
        JSONObject soin = soins.getJSONObject("0");

        JSONObject soinTmp = new JSONObject();
        soinTmp.accumulate("nbTotal", 4);
        soinTmp.accumulate("montantMaximalReclame", 200.0);
        soinTmp.accumulate("totalMontantsReclames", 600.0);

        assertEquals(soinTmp, soin);
    }

    @Test
    public void testAjoutSoinStatsMontantReclamePlusPetit() {
        Dollar montantReclame = new Dollar(50.0);
        Stats.ajoutSoinStats(0, montantReclame, stats);

        JSONObject soins = stats.getJSONObject("Soins");
        JSONObject soin = soins.getJSONObject("0");

        JSONObject soinTmp = new JSONObject();
        soinTmp.accumulate("nbTotal", 4);
        soinTmp.accumulate("montantMaximalReclame", 100.0);
        soinTmp.accumulate("totalMontantsReclames", 450.0);

        assertEquals(soinTmp, soin);
    }

    @Test
    public void testExtraireStatsSoin0() {
        JSONObject soinTmp = Stats.extraireStatsSoin(0, soins);
        assertEquals(soinTmp, soin);
    }

    @Test
    public void testExtraireStatsSoin100() {
        JSONObject soinTmp = Stats.extraireStatsSoin(100, soins);
        assertEquals(soinTmp, soinVide);
    }

    @Test
    public void testExtraireStatsSoin150() {
        JSONObject soinTmp = Stats.extraireStatsSoin(150, soins);
        assertEquals(soinTmp, soinVide);
    }

    @Test
    public void testExtraireStatsSoin175() {
        JSONObject soinTmp = Stats.extraireStatsSoin(175, soins);
        assertEquals(soinTmp, soinVide);
    }

    @Test
    public void testExtraireStatsSoin200() {
        JSONObject soinTmp = Stats.extraireStatsSoin(200, soins);
        assertEquals(soinTmp, soinVide);
    }

    @Test
    public void testExtraireStatsSoin300() {
        JSONObject soinTmp = Stats.extraireStatsSoin(300, soins);
        assertEquals(soinTmp, soinVide);
    }

    @Test
    public void testExtraireStatsSoin347() {
        JSONObject soinTmp = Stats.extraireStatsSoin(347, soins);
        assertEquals(soinTmp, soinVide);
    }

    @Test
    public void testExtraireStatsSoin400() {
        JSONObject soinTmp = Stats.extraireStatsSoin(400, soins);
        assertEquals(soinTmp, soinVide);
    }

    @Test
    public void testExtraireStatsSoin500() {
        JSONObject soinTmp = Stats.extraireStatsSoin(500, soins);
        assertEquals(soinTmp, soinVide);
    }

    @Test
    public void testExtraireStatsSoin600() {
        JSONObject soinTmp = Stats.extraireStatsSoin(600, soins);
        assertEquals(soinTmp, soin);
    }

    @Test
    public void testExtraireStatsSoin700() {
        JSONObject soinTmp = Stats.extraireStatsSoin(700, soins);
        assertEquals(soinTmp, soinVide);
    }

    @Test
    public void testIncrementerNbrTotalPourUnSoin() {
        int nbrTotal = soin.getInt("nbTotal");

        Stats.incrementerNbrTotalPourUnSoin(soin);

        assertTrue(nbrTotal + 1 == soin.getInt("nbTotal"));
    }

    @Test
    public void testChangerTotalMontants() {
        Dollar montantARajouter = new Dollar(100.0);
        double total = soin.getDouble("totalMontantsReclames");
        total = total + 100.0;

        Stats.changerTotalMontants(soin, montantARajouter);
        assertTrue(total == soin.getDouble("totalMontantsReclames"));

    }

    @Test
    public void testChangerMontantMaximalPlusGrand() {
        Dollar montantMax = new Dollar(200.0);

        Stats.changerMontantMaximal(soin, montantMax);

        assertTrue(200.0 == soin.getDouble("montantMaximalReclame"));
    }

    @Test
    public void testChangerMontantMaximalPlusPetit() {
        Dollar montantMax = new Dollar(50.0);

        Stats.changerMontantMaximal(soin, montantMax);

        assertTrue(100.0 == soin.getDouble("montantMaximalReclame"));
    }

    @Test
    public void testViderStats() {
        Stats.viderStats(stats);

        assertEquals(statsVides, stats);
    }

    @Test
    public void testViderStatsReclamations() {
        Stats.viderStatsReclamations(reclamations);
        assertEquals(reclamationsVides, reclamations);
    }

    @Test
    public void testViderStatsSoins() {
        Stats.viderStatsSoins(soins);
        assertEquals(soinsVides, soins);
    }

    @Test
    public void testInitialiserStats() {

        JSONObject stats = new JSONObject();
        stats.accumulate("Reclamations", reclamationsVides);
        stats.accumulate("Soins", soinsVides);

        assertEquals(stats, Stats.initialiserStats());
    }

    @Test
    public void testInitialiserStatsSoins() {
        assertEquals(soinsVides, Stats.initialiserStatsSoins());
    }

    @Test
    public void testInitialiserStatsReclamations() {
        assertEquals(reclamationsVides, Stats.initialiserStatsReclamations());
    }

    @Test
    public void testAfficherStatsNonVides() {
        String contenu
                = "Statistiques: "
                + "\n Réclamations traitées: 1"
                + "\n Réclamations rejetées: 1"
                + "\nSoins déclarés: "
                + "\n Massothérapie (0) : "
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
        
        assertEquals(contenu, Stats.afficherStats(stats));
    }
    
    @Test
    public void testAfficherStatsVides() {
        String contenu
                = "Statistiques: "
                + "\n Réclamations traitées: 0"
                + "\n Réclamations rejetées: 0"
                + "\nSoins déclarés: "
                + "\n Massothérapie (0) : "
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
        
        assertEquals(contenu, Stats.afficherStats(statsVides));
    }

    @Test
    public void testAfficherReclamationsNonVides() {
        String contenu
                = "\n Réclamations traitées: 1"
                + "\n Réclamations rejetées: 1";

        assertEquals(contenu, Stats.afficherReclamations(reclamations));
    }

    @Test
    public void testAfficherReclamationsVides() {
        String contenu
                = "\n Réclamations traitées: 0"
                + "\n Réclamations rejetées: 0";

        assertEquals(contenu, Stats.afficherReclamations(reclamationsVides));
    }

    @Test
    public void testAfficherSoinsNonVides() {
        String contenu
                = "\nSoins déclarés: "
                + "\n Massothérapie (0) : "
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
    public void testAfficherSoinsVides() {
        String contenu
                = "\nSoins déclarés: "
                + "\n Massothérapie (0) : "
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
    public void testAfficherUnSoinNormal() {
        String contenu = "\n    total : 3"
                + "\n    montant maximal réclamé : 100.00$"
                + "\n    moyenne des montants réclamés : 133.33$";
        assertEquals(contenu, Stats.afficherUnSoin(soin));
    }

    @Test
    public void testAfficherUnSoinVide() {
        String contenu = "\n    total : 0"
                + "\n    montant maximal réclamé : 0.00$"
                + "\n    moyenne des montants réclamés : 0.00$";
        assertEquals(contenu, Stats.afficherUnSoin(soinVide));
    }

    @Test(expected = NullPointerException.class)
    public void testAfficherUnSoinNull() {
        Stats.afficherUnSoin(null);
    }

}
