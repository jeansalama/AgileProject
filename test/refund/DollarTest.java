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
    public void testEquals1() {
        Dollar m1 = new Dollar(1.0);
        Dollar m2 = new Dollar(100);
        assertTrue(m1.equals(m2));
    }

    @Test
    public void testEquals2() throws ReclamationException {
        Dollar m1 = new Dollar(1.0);
        Dollar m2 = new Dollar("1.00$");
        assertTrue(m1.equals(m2));
    }

    @Test
    public void testEquals3() throws ReclamationException {
        Dollar m1 = new Dollar(1.0);
        assertTrue(m1.equals(m1));
    }

    @Test
    public void testNotEquals() {
        Dollar m1 = new Dollar(2.0);
        Dollar m2 = new Dollar(100);
        assertFalse(m1.equals(m2));
    }

    @Test
    public void testNotEquals2() throws ReclamationException {
        Dollar m1 = new Dollar(2.0);
        Dollar m2 = new Dollar("1.00$");
        assertFalse(m1.equals(m2));
    }

    @Test
    public void testNotEquals3() throws ReclamationException {
        Dollar m1 = new Dollar(1.0);
        assertFalse(m1.equals(null));
    }

    @Test
    public void testNotEquals4() throws ReclamationException {
        Dollar m1 = new Dollar(1.0);
        assertFalse(m1.equals(1.0));
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
    public void testToStringNegatif() {
        Dollar montant = new Dollar(-7.00);
        assertEquals("-7.00$", montant.toString());
    }

    @Test
    public void testToStringNegatif2() {
        Dollar montant = new Dollar(-7.03);
        assertEquals("-7.03$", montant.toString());
    }

    @Test
    public void testToStringNegatif3() {
        Dollar montant = new Dollar(-7.53);
        assertEquals("-7.53$", montant.toString());
    }

    @Test
    public void testToStringNegatif4() {
        Dollar montant = new Dollar(-27.03);
        assertEquals("-27.03$", montant.toString());
    }

    @Test
    public void testToStringNegatif5() {
        Dollar montant = new Dollar(-00027.03);
        assertEquals("-27.03$", montant.toString());
    }

    @Test
    public void testToStringNegatif6() {
        Dollar montant = new Dollar(-2703);
        assertEquals("-27.03$", montant.toString());
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

    @Test
    public void testPlusMontantsNégatifs() {
        Dollar montantUn = new Dollar(-40.00);
        Dollar montantDeux = new Dollar(10.00);
        Dollar montantTotal = new Dollar(-30.00);
        assertEquals(montantTotal, montantUn.plus(montantDeux));
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
    public void testPlusMontantAvecDevise() throws ReclamationException {
        Dollar montantUn = new Dollar("50.00$");
        Dollar montantDeux = new Dollar("03.00$");
        Dollar montantTotal = new Dollar(53.00);
        assertEquals(montantTotal, montantUn.plus(montantDeux));
    }

    @Test
    public void testMoinsMontantsRonds() {
        Dollar montantUn = new Dollar(40.00);
        Dollar montantDeux = new Dollar(40.00);
        Dollar montantTotal = new Dollar(0.00);
        assertEquals(montantTotal, montantUn.moins(montantDeux));
    }

    @Test
    public void testMoinsMontantsDecimaux() {
        Dollar montantUn = new Dollar(84.34);
        Dollar montantDeux = new Dollar(40.27);
        Dollar montantTotal = new Dollar(44.07);
        assertEquals(montantTotal, montantUn.moins(montantDeux));
    }

    @Test
    public void testMoinsMontantsNégatifs1() {
        Dollar montantUn = new Dollar(-40.00);
        Dollar montantDeux = new Dollar(10.00);
        Dollar montantTotal = new Dollar(-50.00);
        assertEquals(montantTotal, montantUn.moins(montantDeux));
    }

    @Test
    public void testMoinsMontantsNégatifs2() {
        Dollar montantUn = new Dollar(-40.00);
        Dollar montantDeux = new Dollar(-10.00);
        Dollar montantTotal = new Dollar(-30.00);
        assertEquals(montantTotal, montantUn.moins(montantDeux));
    }

    @Test(expected = Exception.class)
    public void testMoinsValeursLimites1() {
        Dollar montantUn = new Dollar(-Double.MAX_VALUE);
        System.out.println("-Double.MAX_VALUE " + -Double.MAX_VALUE);
        Dollar montantDeux = new Dollar(1.00);
        montantUn.moins(montantDeux);
    }

    @Test
    public void testMoinsValeursLimites2() {
        Dollar montantUn = new Dollar((-Double.MAX_VALUE) + 1.00);
        Dollar montantDeux = new Dollar(1.00);
        Dollar montantTotal = new Dollar(-Double.MAX_VALUE);
        assertEquals(montantTotal, montantUn.moins(montantDeux));
    }

    @Test
    public void testMoinsGrosMontants() {
        Dollar montantUn = new Dollar(464836.88);
        Dollar montantDeux = new Dollar(364839.53);
        Dollar montantTotal = new Dollar(99997.35);
        assertEquals(montantTotal, montantUn.moins(montantDeux));
    }

    @Test
    public void testMoinsMontantDebutantAvecZero() {
        Dollar montantUn = new Dollar(050.00);
        Dollar montantDeux = new Dollar(03.00);
        Dollar montantTotal = new Dollar(47.00);
        assertEquals(montantTotal, montantUn.moins(montantDeux));
    }

    @Test
    public void testMoinsMontantAvecDevise() throws ReclamationException {
        Dollar montantUn = new Dollar("50.00$");
        Dollar montantDeux = new Dollar("03.00$");
        Dollar montantTotal = new Dollar(47.00);
        assertEquals(montantTotal, montantUn.moins(montantDeux));
    }

    @Test
    public void testfoisMontantsRonds() {
        Dollar montantUn = new Dollar(40.00);
        Dollar montantDeux = new Dollar(40.00);
        Dollar montantTotal = new Dollar(1600.00);
        assertEquals(montantTotal, montantUn.fois(montantDeux));
    }

    @Test
    public void testfoisMontantsDecimaux() {
        Dollar montantUn = new Dollar(40.34);
        Dollar montantDeux = new Dollar(40.27);
        Dollar montantTotal = new Dollar(1624.49);
        assertEquals(montantTotal, montantUn.fois(montantDeux));
    }

    @Test
    public void testfoisMontantsNégatifs() {
        Dollar montantUn = new Dollar(-40.00);
        Dollar montantDeux = new Dollar(10.00);
        Dollar montantTotal = new Dollar(-400.00);
        assertEquals(montantTotal, montantUn.fois(montantDeux));
    }

    @Test(expected = Exception.class)
    public void testfoisValeursLimites1() {
        Dollar montantUn = new Dollar(Double.MAX_VALUE / 2 + 1);
        Dollar montantDeux = new Dollar(2.00);
        montantUn.fois(montantDeux);
    }

    @Test
    public void testfoisValeursLimites2() {
        Dollar montantUn = new Dollar(Double.MAX_VALUE / 2);
        Dollar montantDeux = new Dollar(2.00);
        Dollar montantTotal = new Dollar(Double.MAX_VALUE);
        assertEquals(montantTotal, montantUn.fois(montantDeux));
    }

    @Test
    public void testfoisGrosMontants() {
        Dollar montantUn = new Dollar(46836.88);
        Dollar montantDeux = new Dollar(36439.53);
        Dollar montantTotal = new Dollar(1706713893.87);
        assertEquals(montantTotal, montantUn.fois(montantDeux));

    }

    @Test
    public void testfoisMontantDebutantAvecZero() {
        Dollar montantUn = new Dollar(050.00);
        Dollar montantDeux = new Dollar(03.00);
        Dollar montantTotal = new Dollar(150.00);
        assertEquals(montantTotal, montantUn.fois(montantDeux));
    }

    @Test
    public void testPourcentageTauxRond1() {
        Dollar montantUn = new Dollar(60.00);
        Dollar montantTotal = new Dollar(15.00);
        assertEquals(montantTotal, montantUn.pourcentage(0.25));
    }

    @Test
    public void testPourcentageTauxRond2() {
        Dollar montantUn = new Dollar(60.77);
        Dollar montantTotal = new Dollar(15.19);
        assertEquals(montantTotal, montantUn.pourcentage(0.25));
    }

    @Test
    public void testPourcentageTauxDecimal1() {
        Dollar montantUn = new Dollar(60.00);
        Dollar montantTotal = new Dollar(15.34);
        assertEquals(montantTotal, montantUn.pourcentage(0.2557));
    }

    @Test
    public void testPourcentageTauxDecimal2() {
        Dollar montantUn = new Dollar(60.99);
        Dollar montantTotal = new Dollar(15.60);
        assertEquals(montantTotal, montantUn.pourcentage(0.2557));
    }

    @Test
    public void testPourcentageTauxSuperieurAUn() {
        Dollar montantUn = new Dollar(60.00);
        Dollar montantTotal = new Dollar(60.00);
        assertEquals(montantTotal, montantUn.pourcentage(1.00));
    }

    @Test
    public void testPourcentageTauxSuperieurAUnDecimal() {
        Dollar montantUn = new Dollar(60.00);
        Dollar montantTotal = new Dollar(139.80);
        assertEquals(montantTotal, montantUn.pourcentage(2.33));
    }

    @Test
    public void testPourcentageMontantNul() {
        Dollar montantUn = new Dollar();
        Dollar montantTotal = new Dollar(0.00);
        assertEquals(montantTotal, montantUn.pourcentage(0.50));
    }

    @Test
    public void testPourcentageTauxNul() {
        Dollar montantUn = new Dollar(60.00);
        Dollar montantTotal = new Dollar(0.00);
        assertEquals(montantTotal, montantUn.pourcentage(0.00));
    }

    @Test
    public void testPourcentageMontantNegatif() {
        Dollar montantUn = new Dollar(-60.00);
        Dollar montantTotal = new Dollar(-24.00);
        assertEquals(montantTotal, montantUn.pourcentage(0.40));
    }

    @Test
    public void testPourcentageTauxNegatif() {
        Dollar montantUn = new Dollar(60.00);
        Dollar montantTotal = new Dollar(-139.80);
        assertEquals(montantTotal, montantUn.pourcentage(-2.33));
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
    public void testEstSuperieurA2() {
        Dollar m2 = new Dollar(452);
        Dollar m1 = new Dollar(9023);
        assertFalse(m2.estSuperieurA(m1));
    }

    @Test
    public void testEstInferieurA() {
        Dollar cents = new Dollar(123);
        Dollar m2 = new Dollar(100);
        assertTrue(m2.estInferieurA(cents));
    }

    @Test
    public void testEstInferieurA2() {
        Dollar m1 = new Dollar(123);
        Dollar m2 = new Dollar(100);
        assertFalse(m1.estInferieurA(m2));
    }

    @Test
    public void testEstNegatif() {
        Dollar m = new Dollar(-1);
        assertTrue(m.estNegatif());
    }

    @Test
    public void testEstNegatif2() {
        Dollar m = new Dollar(1);
        assertFalse(m.estNegatif());
    }

    @Test(expected = ReclamationException.class)
    public void testDollarStringMontantFormatAnalyserReclamation1()
            throws ReclamationException {
        Dollar strMontant = new Dollar("5-645");
    }

    @Test(expected = ReclamationException.class)
    public void testDollarStringMontantSansSeparateur()
            throws ReclamationException {
        Dollar strMontant = new Dollar("5645$");
    }

    @Test(expected = ReclamationException.class)
    public void testDollarStringMontantSansDevise()
            throws ReclamationException {
        Dollar strMontant = new Dollar("56.45");
    }

    @Test(expected = ReclamationException.class)
    public void testDollarStringMontantMauvaiseDevise()
            throws ReclamationException {
        Dollar strMontant = new Dollar("56.45£");
    }

    @Test(expected = ReclamationException.class)
    public void testDollarStringMontantSymboleDollarPasDernier()
            throws ReclamationException {
        Dollar strMontant = new Dollar("564.$5");
    }

    @Test(expected = ReclamationException.class)
    public void testDollarStringMontantSansDecimalAvantSeparateur()
            throws ReclamationException {
        Dollar strMontant = new Dollar(",56$");
    }

    @Test(expected = ReclamationException.class)
    public void testDollarStringMontantSansDecimalApresSeparateur()
            throws ReclamationException {
        Dollar strMontant = new Dollar("56,$");
    }

    @Test(expected = ReclamationException.class)
    public void testDollarStringMontantAvecPlusDeDeuxDecimal()
            throws ReclamationException {
        Dollar strMontant = new Dollar("2,5645$");
    }

    @Test(expected = ReclamationException.class)
    public void testDollarStringMontantAvecMoinsDeDeuxDecimal()
            throws ReclamationException {
        Dollar strMontant = new Dollar("2,5$");
    }

    @Test
    public void testDollarStringMontantFormatAnalyserReclamation5()
            throws ReclamationException {
        Dollar strMontant = new Dollar("5.64$");
        Dollar strMontant2 = new Dollar("5,64$");
        assertEquals(strMontant, strMontant2);
    }
    @Test
    public void testDollarStringMontantRond()
            throws ReclamationException {
        Dollar strMontant = new Dollar("50.00$");
        assertEquals(5000, strMontant.getCents());
    }
    
    @Test
    public void testDollarStringMontantDecimal()
            throws ReclamationException {
        Dollar strMontant = new Dollar("55.64$");
        assertEquals(5564, strMontant.getCents());
    }
    
    @Test
    public void testDollarStringGrosMontant()
            throws ReclamationException {
        Dollar strMontant = new Dollar("99999.99$");
        assertEquals(9999999, strMontant.getCents());
    }
    
    @Test
    public void testDollarStringMontantNegatif()
            throws ReclamationException {
        Dollar strMontant = new Dollar("-55.64$");
        assertEquals(-5564, strMontant.getCents());
    }
    
    
    
    @Test
    public void testConstructeurSansArguments(){
        Dollar montantUn = new Dollar();
        assertEquals(0, montantUn.getCents());
    }
    
    @Test
    public void testConstructeurArgumentLongMontantRond(){
        Dollar montantUn = new Dollar(3000);
        assertEquals(3000, montantUn.getCents());
    }
    
    @Test
    public void testConstructeurArgumentLongGrosMontant(){
        Dollar montantUn = new Dollar(999999);
        assertEquals(999999, montantUn.getCents());
    }
    
    @Test
    public void testConstructeurArgumentLongMontantDecimal(){
        Dollar montantUn = new Dollar(3333);
        assertEquals(3333, montantUn.getCents());
    }
    @Test
    public void testConstructeurArgumentLongMontantNegatif(){
        Dollar montantUn = new Dollar(-3333);
        assertEquals(-3333, montantUn.getCents());
    }
    @Test
    public void testConstructeurArgumentLongValeursMaximales(){
        Dollar montantUn = new Dollar(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, montantUn.getCents());
    }
    
    @Test
    public void testConstructeurArgumentDoubleMontantRond(){
        Dollar montantUn = new Dollar(55.00);
        assertEquals(5500, montantUn.getCents());
    }
    @Test
    public void testConstructeurArgumentDoubleGrosMontant(){
        Dollar montantUn = new Dollar(99999.00);
        assertEquals(9999900, montantUn.getCents());
    }
    @Test
    public void testConstructeurArgumentDoubleMontantDecimal(){
        Dollar montantUn = new Dollar(99999.77);
        assertEquals(9999977, montantUn.getCents());
    }
    @Test
    public void testConstructeurArgumentDoubleMontantNegatif(){
        Dollar montantUn = new Dollar(-99999.77);
        assertEquals(-9999977, montantUn.getCents());
    }
    
}
