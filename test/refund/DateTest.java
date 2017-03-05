/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Billy
 */
public class DateTest {

    public DateTest() {
    }

    /**
     * Test of getAnnee method, of class Date.
     */
    @Test
    public void testGetAnnee() throws DateException, NullPointerException {
        Date instance = new Date("2017-03-09");
        assertEquals("2017", instance.getAnnee());

    }

    /**
     * Test of getMois method, of class Date.
     */
    @Test
    public void testGetMois() throws DateException {
        Date d5 = new Date("2017-07-11");
        assertEquals("07", d5.getMois());
    }

    /**
     * Test of getJour method, of class Date.
     */
    @Test
    public void testGetJour() throws DateException {
        Date d4 = new Date("2017-12-10");
        assertEquals("10", d4.getJour());
    }

    /**
     * Test of getDate method, of class Date.
     */
    @Test
    public void testGetDate() throws DateException {
        Date d3 = new Date("2017-12-12");
        assertEquals("2017-12-12", d3.getDate());
    }

    /**
     * Test of setDate method, of class Date.
     */
    @Test
    public void testSetDate() throws Exception, NullPointerException {
        Date d2 = new Date("2017-01-05");
        d2.setDate("2017-11-19");
        assertEquals("2017-11-19", d2.getDate());
    }

    /**
     * Test of setAnnee method, of class Date.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetAnnee() throws Exception {
        Date d7 =  new Date("2017-01-01");
        d7.setAnnee("2018");
        assertEquals("get/set", "2018", d7.getAnnee());

    }

    /*
    @Test
   public void testGetterSetter() {
      System.out.println("Run @Test testGetterSetter"); // for illustration
      int value = 33;
      number1.setNumber(value);
      assertEquals("error in getter/setter", value, number1.getNumber());
   }
    
    
    */
    /**
     * Test of setMois method, of class Date.
     */
    @Test
    public void testSetMois() throws Exception {
        Date d6 =  new Date("2017-10-13");
        d6.setMois("08");
        assertEquals("08", d6.getMois());
      
    }

    /**
     * Test of setJour method, of class Date.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetJour() throws Exception {
        Date d8 =  new Date("2017-03-03");
        d8.setJour("04");
        assertEquals("04", d8.getJour());
    }

    /**
     * Test of contientUnJour method, of class Date.
     * @throws refund.DateException
     */
    @Test
    public void testNeContientPasUnJour() throws DateException {
        Date d5 = new Date("2017-06");
        assertFalse(d5.contientUnJour());
    }
    
     @Test
    public void testContientUnJour() throws DateException {
        Date d5 = new Date("2017-06-02");
       assertTrue(d5.contientUnJour());
    }

    /**
     * Test of toString method, of class Date.
     * @throws refund.DateException
     */
    @Test
    public void testToString() throws DateException {
        Date d1 = new Date("2017-02-02");
        assertEquals("2017-02-02", d1.toString());
    } 

}
