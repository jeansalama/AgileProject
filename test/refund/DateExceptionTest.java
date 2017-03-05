/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import org.junit.Test;
import static org.junit.Assert.*;
import refund.DateException;
/**
 *
 * @author Billy
 */
public class DateExceptionTest {
    
    public DateExceptionTest() {
    }

    @Test
    public void testSomeMethod() {
     
    }
    @Test(expected=DateException.class)
    public void testDateNull() throws DateException{
         Date d9 = new Date();
        d9.getDate();
    }
    
    @Test(expected=DateException.class)
    public void testDateNonNombreAnnee() throws DateException{
        Date d10 = new Date("20i1-05-06");
        d10.getAnnee();
    }
   
     @Test(expected=DateException.class)
     public void testDateNonNombreMois() throws DateException{
        Date d10 = new Date("2017-o5-06");
        d10.getMois();
    }
     
      @Test(expected=DateException.class)
      public void testDateNonNombreJour() throws DateException{
        Date d10 = new Date("20i1-05-0p");
        d10.getJour();
    }
  
     
}
