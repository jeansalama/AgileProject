
package refund;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

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
        assertEquals("12.75", montant1);
    }

    @Test
    public void testToString2() {
        assertEquals("7.25", montant2);
    }

}
