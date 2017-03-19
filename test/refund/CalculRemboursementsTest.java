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

    public CalculRemboursementsTest() {
    }

    
    @Test
    public void testCalculerRemboursement1() throws DossierException, 
            DateException, ReclamationException{
        Date date = new Date("2017-04-23");
        Dossier dossier = new Dossier("B123456", date);
        Reclamation reclam = new Reclamation(0, date, "100.00$");
        Dollar remboursement 
                =  CalculRemboursements.calculerRemboursement(reclam, dossier);
        assertEquals("40.00$", remboursement.toString());
    }
    
    @Test
    public void testCalculerRemboursement2() throws ContratException, 
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
    public void testObtenirRemboursementMaximalSansMax() {
        Dollar montant = new Dollar(100.00);
        JSONObject regle = new JSONObject();
        regle.accumulate("taux", 0.75);

        Dollar remboursementMax
                = CalculRemboursements.obtenirRemboursementMaximal(regle, montant);
        assertEquals("75.00$", remboursementMax.toString());
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

    @Test(expected = NullPointerException.class)
    public void testObtenirSoinAvecReclamationNull() throws DateException,
            ReclamationException {

        assertEquals("200", CalculRemboursements.obtenirSoin(null));
    }

}
