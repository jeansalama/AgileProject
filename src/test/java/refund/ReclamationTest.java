package refund;

import org.junit.*;
import static org.junit.Assert.assertEquals;



public class ReclamationTest {
    Date date1;
    String montant1;
    int soin;
    Reclamation reclam;
    
    

    @Before
    public void setUp() throws DateException, ReclamationException {
        montant1 = "100.00$";
        date1 = new Date("2017-03-09");
        soin = 100;
        reclam = new Reclamation(soin, date1, montant1);
        
    }

    @After
    public void teardown() {
        montant1 = null;
        date1= null;
        soin = 0;
        reclam = null;
        
    }
    
    
    @Test
    public void testGetMontantReclamation() throws ReclamationException{
        Dollar obtenu = reclam.getMontantReclamation();
        Dollar espere = new Dollar("100.00$");
        assertEquals(espere, obtenu);
    }
    
    @Test
    public void testSetMontantReclamationMontantValide() throws ReclamationException{
        reclam.setMontantReclamation("150.00$");
        Dollar obtenu = reclam.getMontantReclamation();
        Dollar espere = new Dollar("150.00$");
        assertEquals(espere, obtenu);
    }
    
    @Test
    public void testSetMontantReclamationMontantValideSeparateurVirgule() 
            throws ReclamationException{
        reclam.setMontantReclamation("150,00$");
        Dollar obtenu = reclam.getMontantReclamation();
        Dollar espere = new Dollar("150,00$");
        assertEquals(espere, obtenu);
    }
    
    @Test(expected = ReclamationException.class)
    public void testSetMontantReclamationMontantDecimalesInvalide() throws ReclamationException{
        reclam.setMontantReclamation("100.0$");
        
    }
    
    @Test(expected = ReclamationException.class)
    public void testSetMontantReclamationMontantSeparateurInvalide() throws ReclamationException{
        reclam.setMontantReclamation("100*00$");
        
    }
    
    @Test(expected = ReclamationException.class)
    public void testSetMontantReclamationMontantDeviseInvalide() throws ReclamationException{
        reclam.setMontantReclamation("100.00£");
        
    }
    
    @Test(expected = ReclamationException.class)
    public void testSetMontantReclamationMontantAvecTropGrosMontant1() 
            throws ReclamationException{
        
        reclam.setMontantReclamation("500.01$");
        
    }
    
    @Test(expected = ReclamationException.class)
    public void testSetMontantReclamationMontantAvecTropGrosMontant2() 
            throws ReclamationException{
        
        reclam.setMontantReclamation("500000.00$");
        
    }
    
    @Test(expected = ReclamationException.class)
    public void testSetMontantReclamationMontantInvalideZero() 
            throws ReclamationException{
        
        reclam.setMontantReclamation("0.00$");
        
    }
    
    @Test
    public void testSetDateValide() throws ReclamationException, DateException{
        reclam.setDate(new Date("2017-03-09"));
        Date obtenue = reclam.getDate();
        Date esperee = new Date("2017-03-09");
        assertEquals(esperee.getDate(), obtenue.getDate());
    }
    
    
    @Test
    public void testGetDate() throws ReclamationException, DateException{
        Date obtenue = reclam.getDate();
        Date esperee = date1;
        assertEquals(esperee, obtenue);
    }
    
   
    @Test(expected = DateException.class)
    public void testSetDateInvalideBisextile() throws DateException{
        reclam.setDate(new Date("2017-02-29"));  
    }
    
    @Test(expected = DateException.class)
    public void testSetDateInvalideSeparateur() throws DateException{
        reclam.setDate(new Date("2017*02-28"));  
    }
    @Test(expected = DateException.class)
    public void testSetDateAnneeInvalide1() throws DateException{
        reclam.setDate(new Date("20177-02-28"));  
    }
    
    @Test(expected = DateException.class)
    public void testSetDateAnneeInvalide2() throws DateException{
        reclam.setDate(new Date("201&-02-28"));  
    }
    @Test(expected = DateException.class)
    public void testSetDateMoisInvalide1() throws DateException{
        reclam.setDate(new Date("2017-00-28"));  
    }
    @Test(expected = DateException.class)
    public void testSetDateMoisInvalide2() throws DateException{
        reclam.setDate(new Date("2017-0/-28"));  
    }
    @Test(expected = DateException.class)
    public void testSetDateJourInvalide1() throws DateException{
        reclam.setDate(new Date("2017-02-44"));  
    }
    @Test(expected = DateException.class)
    public void testSetDateJourInvalide2() throws DateException{
        reclam.setDate(new Date("2017-02-0*"));  
    }
    @Test
    public void testToString(){
        String obtenu = reclam.toString();
        String espere = "100.00$";
        assertEquals(espere, obtenu);
    }
    
    @Test
    public void testSetSoinValide() throws ReclamationException{
        reclam.setSoin(0);
        int obtenu = reclam.getSoin();
        int espere = 0;
        assertEquals(espere, obtenu);  
    }
    
    @Test(expected = ReclamationException.class)
    public void testSetSoinInvalide1() throws ReclamationException{
        reclam.setSoin(5);  
    }
    
}
