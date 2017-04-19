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
 * @author jean
 */
public class CalculRemboursementsTest {

    
    @Test
    public void testCalculerRemboursementDossier1() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("B123456", date);
        Reclamation reclam = new Reclamation(0, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("40.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierSeparateurVirgule() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("B123456", date);
        Reclamation reclam = new Reclamation(0, date, "100,00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("40.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierASoin0() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("A123456", date);
        Reclamation reclam = new Reclamation(0, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("25.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierASoin100() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("A123456", date);
        Reclamation reclam = new Reclamation(100, date, "1.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("0.35$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierASoin150() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("A123456", date);
        Reclamation reclam = new Reclamation(150, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("0.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierASoin175() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("A123456", date);
        Reclamation reclam = new Reclamation(175, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("50.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierASoin200() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("A123456", date);
        Reclamation reclam = new Reclamation(200, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("25.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierASoin300() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("A123456", date);
        Reclamation reclam = new Reclamation(300, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("0.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierASoin301() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("A123456", date);
        Reclamation reclam = new Reclamation(301, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("0.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierASoin399() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("A123456", date);
        Reclamation reclam = new Reclamation(399, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("0.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierASoin400() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("A123456", date);
        Reclamation reclam = new Reclamation(400, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("0.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierASoin500() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("A123456", date);
        Reclamation reclam = new Reclamation(500, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("25.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierASoin600() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("A123456", date);
        Reclamation reclam = new Reclamation(600, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("40.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierASoin700() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("A123456", date);
        Reclamation reclam = new Reclamation(700, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("0.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierBSoin0() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("B123456", date);
        Reclamation reclam = new Reclamation(0, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("40.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierBSoin100() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("B123456", date);
        Reclamation reclam = new Reclamation(100, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("50.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierBSoin150() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("B123456", date);
        Reclamation reclam = new Reclamation(150, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("0.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierBSoin175() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("B123456", date);
        Reclamation reclam = new Reclamation(175, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("75.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierBSoin200() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("B123456", date);
        Reclamation reclam = new Reclamation(200, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("100.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierBSoin300() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("B123456", date);
        Reclamation reclam = new Reclamation(300, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("50.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierBSoin301() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("B123456", date);
        Reclamation reclam = new Reclamation(301, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("50.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierBSoin399() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("B123456", date);
        Reclamation reclam = new Reclamation(399, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("50.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierBSoin400() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("B123456", date);
        Reclamation reclam = new Reclamation(400, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("0.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierBSoin500() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("B123456", date);
        Reclamation reclam = new Reclamation(500, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("50.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierBSoin600() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("B123456", date);
        Reclamation reclam = new Reclamation(600, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("100.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierBSoin700() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("B123456", date);
        Reclamation reclam = new Reclamation(700, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("70.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierCSoin0() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("C123456", date);
        Reclamation reclam = new Reclamation(0, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("90.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierCSoin100() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("C123456", date);
        Reclamation reclam = new Reclamation(100, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("95.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierCSoin150() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("C123456", date);
        Reclamation reclam = new Reclamation(150, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("85.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierCSoin175() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("C123456", date);
        Reclamation reclam = new Reclamation(175, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("90.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierCSoin200() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("C123456", date);
        Reclamation reclam = new Reclamation(200, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("90.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierCSoin300() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("C123456", date);
        Reclamation reclam = new Reclamation(300, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("90.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierCSoin301() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("C123456", date);
        Reclamation reclam = new Reclamation(301, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("90.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierCSoin399() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("C123456", date);
        Reclamation reclam = new Reclamation(399, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("90.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierCSoin400() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("C123456", date);
        Reclamation reclam = new Reclamation(400, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("90.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierCSoin500() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("C123456", date);
        Reclamation reclam = new Reclamation(500, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("90.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierCSoin600() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("C123456", date);
        Reclamation reclam = new Reclamation(600, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("75.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierCSoin700() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("C123456", date);
        Reclamation reclam = new Reclamation(700, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("90.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierDSoin0() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("D123456", date);
        Reclamation reclam = new Reclamation(0, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("85.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierDSoin100() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("D123456", date);
        Reclamation reclam = new Reclamation(100, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("75.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierDSoin150() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("D123456", date);
        Reclamation reclam = new Reclamation(150, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("100.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierDSoin175() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("D123456", date);
        Reclamation reclam = new Reclamation(175, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("95.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierDSoin200() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("D123456", date);
        Reclamation reclam = new Reclamation(200, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("100.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierDSoin300() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("D123456", date);
        Reclamation reclam = new Reclamation(300, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("100.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierDSoin301() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("D123456", date);
        Reclamation reclam = new Reclamation(301, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("100.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierDSoin399() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("D123456", date);
        Reclamation reclam = new Reclamation(399, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("100.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierDSoin400() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("D123456", date);
        Reclamation reclam = new Reclamation(400, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("65.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierDSoin500() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("D123456", date);
        Reclamation reclam = new Reclamation(500, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("100.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierDSoin600() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("D123456", date);
        Reclamation reclam = new Reclamation(600, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("100.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierDSoin700() 
            throws DossierException, DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("D123456", date);
        Reclamation reclam = new Reclamation(700, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("90.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierESoin0() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("E123456", date);
        Reclamation reclam = new Reclamation(0, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("15.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierESoin100() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("E123456", date);
        Reclamation reclam = new Reclamation(100, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("25.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierESoin150() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("E123456", date);
        Reclamation reclam = new Reclamation(150, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("15.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierESoin175() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("E123456", date);
        Reclamation reclam = new Reclamation(175, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("20.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierESoin200() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("E123456", date);
        Reclamation reclam = new Reclamation(200, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("12.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierESoin300() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("E123456", date);
        Reclamation reclam = new Reclamation(300, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("60.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierESoin301() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("E123456", date);
        Reclamation reclam = new Reclamation(301, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("60.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierESoin399() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("E123456", date);
        Reclamation reclam = new Reclamation(399, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("60.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierESoin400() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("E123456", date);
        Reclamation reclam = new Reclamation(400, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("15.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierESoin500() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("E123456", date);
        Reclamation reclam = new Reclamation(500, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("20.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementDossierESoin600() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("E123456", date);
        Reclamation reclam = new Reclamation(600, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("15.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursementDossierESoin700() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("E123456", date);
        Reclamation reclam = new Reclamation(700, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("22.00$", remboursement.toString());
    }
    @Test
    public void testCalculerRemboursementContrat1() throws ContratException, 
            DateException, ReclamationException{
        Contrat contrat = new Contrat("B");
        Date date = new Date("2017-04-23");
        Reclamation reclam = new Reclamation(0, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, contrat);
        assertEquals("40.00$", remboursement.toString());
    }

    @Test
    public void testObtenirRemboursementMaximal() {
        Dollar montant = new Dollar(100.00);
        JSONObject regle = new JSONObject();
        regle.accumulate("taux", 0.75);
        regle.accumulate("max", 65);
        Dollar remboursementMax
                = CalculRemboursements.obtenirRemboursementMaximal(regle, montant);
        assertEquals("65.00$", remboursementMax.toString());
    }
    
    @Test
    public void testObtenirRemboursementMaximal2() {
        Dollar montant = new Dollar(100.00);
        JSONObject regle = new JSONObject();
        regle.accumulate("taux", 0.25);
        regle.accumulate("max", 65);
        Dollar remboursementMax
                = CalculRemboursements.obtenirRemboursementMaximal(regle, montant);
        assertEquals("25.00$", remboursementMax.toString());
    }

    @Test
    public void testObtenirRemboursementMaximalSansMax1() {
        Dollar montant = new Dollar(100.00);
        JSONObject regle = new JSONObject();
        regle.accumulate("taux", 0.75);
        Dollar remboursementMax
                = CalculRemboursements.obtenirRemboursementMaximal(regle, montant);
        assertEquals("75.00$", remboursementMax.toString());
    }
    
    @Test
    public void testObtenirRemboursementMaximalGrosMontant(){
        Dollar montant = new Dollar(99999.00);
        JSONObject regle = new JSONObject();
        regle.accumulate("taux", 0.75);
        regle.accumulate("max", 65);
        Dollar remboursementMax
                = CalculRemboursements.obtenirRemboursementMaximal(regle, montant);
        assertEquals("65.00$", remboursementMax.toString());
    }
    
    @Test
    public void testObtenirRemboursementMaximalMontantDecimal(){
        Dollar montant = new Dollar(99.27);
        JSONObject regle = new JSONObject();
        regle.accumulate("taux", 0.75);
        regle.accumulate("max", 65);
        Dollar remboursementMax
                = CalculRemboursements.obtenirRemboursementMaximal(regle, montant);
        assertEquals("65.00$", remboursementMax.toString());
    }
    
    @Test
    public void testObtenirRemboursementMaximalGrosMontantPetitMaximum(){
        Dollar montant = new Dollar(99999.99);
        JSONObject regle = new JSONObject();
        regle.accumulate("taux", 0.75);
        regle.accumulate("max", 25);
        Dollar remboursementMax
                = CalculRemboursements.obtenirRemboursementMaximal(regle, montant);
        assertEquals("25.00$", remboursementMax.toString());
    }
    
    @Test
    public void testCalculerRemboursementMaximalMensuelUneReclam(){
        Dollar remboursement = new Dollar(100.00);
        JSONObject regle = new JSONObject();
        regle.accumulate("maxMensuel", 250);
        Dollar remboursementMax
                = CalculRemboursements.calculerRemboursementMaximalMensuel(regle, remboursement);
        assertEquals("100.00$", remboursementMax.toString());
    }
    
    @Test
    public void testCalculerRemboursementMaximalMensuelDeuxReclams(){
        Dollar remboursement = new Dollar(100.00);
        JSONObject regle = new JSONObject();
        regle.accumulate("maxMensuel", 250);
        CalculRemboursements.calculerRemboursementMaximalMensuel(regle, remboursement);
        Dollar remboursementMax
                = CalculRemboursements.calculerRemboursementMaximalMensuel(regle, remboursement);
        assertEquals("100.00$", remboursementMax.toString());
    }
    
    @Test
    public void testCalculerRemboursementMaximalMensuelTroisReclams(){
        Dollar remboursement = new Dollar(100.00);
        JSONObject regle = new JSONObject();
        regle.accumulate("maxMensuel", 250);
        CalculRemboursements.calculerRemboursementMaximalMensuel(regle, remboursement);
        CalculRemboursements.calculerRemboursementMaximalMensuel(regle, remboursement);
        Dollar remboursementMax
                = CalculRemboursements.calculerRemboursementMaximalMensuel(regle, remboursement);
        assertEquals("50.00$", remboursementMax.toString());
    }
    
    @Test
    public void testCalculerRemboursementMaximalMensuelQuatreReclams(){
        Dollar remboursement = new Dollar(100.00);
        JSONObject regle = new JSONObject();
        regle.accumulate("maxMensuel", 250);
        CalculRemboursements.calculerRemboursementMaximalMensuel(regle, remboursement);
        CalculRemboursements.calculerRemboursementMaximalMensuel(regle, remboursement);
        CalculRemboursements.calculerRemboursementMaximalMensuel(regle, remboursement);
        Dollar remboursementMax
                = CalculRemboursements.calculerRemboursementMaximalMensuel(regle, remboursement);
        assertEquals("0.00$", remboursementMax.toString());
    }

    @Test
    public void testObtenirTauxRemboursement() {
        JSONObject regle = new JSONObject();
        regle.accumulate("taux", 0.75);
        assertTrue(0.75 == CalculRemboursements.obtenirTauxRemboursement(regle));
    }

    @Test
    public void testExtraireUneRegle() throws ContratException, DateException,
            ReclamationException {
        Contrat contrat = new Contrat("B");
        Date date = new Date("2017-04-23");
        Reclamation reclam = new Reclamation(0, date, "100.00$");

        JSONObject regle
                = CalculRemboursements.extraireUneRegle(reclam, contrat);
        String attendu = "{\n"
                + "  \"taux\": 0.5,\n"
                + "  \"max\": 40\n"
                + "}";
        assertEquals(attendu, regle.toString(2));
    }

    @Test
    public void testObtenirSoin() throws DateException, ReclamationException {
        Date date = new Date("2017-04-23");
        Reclamation reclam = new Reclamation(200, date, "100.00$");

        assertEquals("200", CalculRemboursements.obtenirSoin(reclam));
    }
    
    @Test
    public void testObtenirSoin2() throws DateException, ReclamationException {
        Date date = new Date("2017-04-23");
        Reclamation reclam = new Reclamation(345, date, "100.00$");

        assertEquals("300", CalculRemboursements.obtenirSoin(reclam));
    }
    
    @Test
    public void testObtenirSoinLimite1() throws DateException, ReclamationException {
        Date date = new Date("2017-04-23");
        Reclamation reclam = new Reclamation(399, date, "100.00$");

        assertEquals("300", CalculRemboursements.obtenirSoin(reclam));
    }
    
    @Test
    public void testObtenirSoinLimite2() throws DateException, ReclamationException {
        Date date = new Date("2017-04-23");
        Reclamation reclam = new Reclamation(300, date, "100.00$");

        assertEquals("300", CalculRemboursements.obtenirSoin(reclam));
    }
    
    @Test
    public void testObtenirSoinLimite3() throws DateException, ReclamationException {
        Date date = new Date("2017-04-23");
        Reclamation reclam = new Reclamation(400, date, "100.00$");

        assertEquals("400", CalculRemboursements.obtenirSoin(reclam));
    }
    
    @Test(expected = DateException.class)
    public void testObtenirSoinDateInvalideLettre() 
            throws DateException, ReclamationException {
        Date date = new Date("201A-04-23");
        Reclamation reclam = new Reclamation(400, date, "100.00$");

    }
    @Test(expected = DateException.class)
    public void testObtenirSoinDateInvalideSeparateur() 
            throws DateException, ReclamationException {
        Date date = new Date("2017*04-23");
        Reclamation reclam = new Reclamation(400, date, "100.00$");
    }
    
    @Test(expected = DateException.class)
    public void testObtenirSoinDateInvalideMois() 
            throws DateException, ReclamationException {
        Date date = new Date("2017-14-23");
        Reclamation reclam = new Reclamation(400, date, "100.00$");
    }
    @Test(expected = DateException.class)
    public void testObtenirSoinDateInvalideJour() 
            throws DateException, ReclamationException {
        Date date = new Date("2017-11-32");
        Reclamation reclam = new Reclamation(400, date, "100.00$");
    }
    
    @Test(expected = DateException.class)
    public void testObtenirSoinDateInvalideAnnee() throws DateException, ReclamationException {
        Date date = new Date("20177-11-32");
        Reclamation reclam = new Reclamation(400, date, "100.00$");
    }
    
    @Test(expected = ReclamationException.class)
    public void testObtenirSoinDateInvalideSansJour() throws DateException, ReclamationException {
        Date date = new Date("2017-11");
        Reclamation reclam = new Reclamation(400, date, "100.00$");
    }

    @Test(expected = NullPointerException.class)
    public void testObtenirSoinAvecReclamationNull() throws DateException,
            ReclamationException {

        assertEquals("200", CalculRemboursements.obtenirSoin(null));
    }

}
