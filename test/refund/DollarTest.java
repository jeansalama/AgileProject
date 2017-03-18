package refund;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import refund.Dollar.*;

/**
 *
 * @author Jean Salama
 */
public class DollarTest {

    public DollarTest() {
    }

    Dollar montant1;
    Dollar montant2;

    @Before
    public void setUp() {
        montant1 = new Dollar(12.25);
        montant2 = new Dollar(775);
    }

    @After
    public void teardown() {
        montant1 = null;
        montant2 = null;
    }

    @Test
    public void testToString1() {
        assertEquals("12.25$", montant1.toString());
    }

    @Test
    public void testToString2() {
        assertEquals("7.75$", montant2.toString());
    }

    @Test
    public void testToString3() {
        Dollar montant = new Dollar(7.20);
        assertEquals("7.20$", montant.toString());
    }

    @Test
    public void testToString4() {
        Dollar montant = new Dollar(7.00);
        assertEquals("7.00$", montant.toString());
    }

    @Test
    public void testToString5() {
        Dollar montant = new Dollar(07.00);
        assertEquals("7.00$", montant.toString());
    }

    @Test
    public void testToString6() {
        Dollar montant = new Dollar(07.01);
        assertEquals("7.01$", montant.toString());
    }

    @Test
    public void testPlusMontantsRonds() {
        Dollar montantUn = new Dollar(40.00);
        Dollar montantDeux = new Dollar(40.00);
        Dollar montantTotal = new Dollar(80.00);
        assertEquals(montantTotal, montantUn.plus(montantDeux));
    }

    @Test
    public void testPlusMontantsDecimaux() {
        Dollar montantUn = new Dollar(40.34);
        Dollar montantDeux = new Dollar(40.27);
        Dollar montantTotal = new Dollar(80.61);
        assertEquals(montantTotal, montantUn.plus(montantDeux));
    }

    @Test(expected = Exception.class)
    public void testPlusMontantsNégatifs() {
        Dollar montantUn = new Dollar(-40.00);
        Dollar montantDeux = new Dollar(10.00);
        montantUn.plus(montantDeux);
    }

    @Test(expected = Exception.class)
    public void testPlusValeursLimites1() {
        Dollar montantUn = new Dollar(Double.MAX_VALUE);
        Dollar montantDeux = new Dollar(1.00);
        montantUn.plus(montantDeux);
    }

    @Test
    public void testPlusValeursLimites2() {
        Dollar montantUn = new Dollar(Double.MAX_VALUE - 1.00);
        Dollar montantDeux = new Dollar(1.00);
        Dollar montantTotal = new Dollar(Double.MAX_VALUE);
        assertEquals(montantTotal, montantUn.plus(montantDeux));
    }

    @Test
    public void testPlusGrosMontants() {
        Dollar montantUn = new Dollar(464836.88);
        Dollar montantDeux = new Dollar(364839.53);
        Dollar montantTotal = new Dollar(829676.41);
        assertEquals(montantTotal, montantUn.plus(montantDeux));
    }

    @Test
    public void testPlusMontantDebutantAvecZero() {
        Dollar montantUn = new Dollar(050.00);
        Dollar montantDeux = new Dollar(03.00);
        Dollar montantTotal = new Dollar(53.00);
        assertEquals(montantTotal, montantUn.plus(montantDeux));
    }

    

    @Test
    public void testEstCent() {
        Dollar cents = new Dollar();
        assertEquals(0, cents.getCents());
    }

    @Test
    public void testEstCentLong() {
        Dollar cents = new Dollar(1234);
        assertEquals(1234, cents.getCents());
    }

    @Test
    public void testEstSuperieurA() {
        Dollar cents = new Dollar(452);
        Dollar m1 = new Dollar(9023);
        assertTrue(m1.estSuperieurA(cents));
    }
    
    @Test
    public void testEstInferieurA() {
        Dollar cents = new Dollar(123);
        Dollar m2 = new Dollar(100);
        assertTrue(m2.estInferieurA(cents));
    }

}
