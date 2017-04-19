/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Max
 */
public class RefundTest {
    String[] argsTest = new String[3];
    
    
    @Test
    public void testFormatterNomFichiersArgumentsValides(){
        String[] obtenu;
        String[] espere =  new String[3];
        espere[0] = "a.json";
        espere[1] = "b.json";
        espere[2] = "c.json";
        argsTest[0] = "a";
        argsTest[1] = "b";
        argsTest[2] = "c";
        obtenu = Refund.formatterNomsFichiers(argsTest);
        Assert.assertArrayEquals(espere,obtenu);  
    }
    
    @Test
    public void testFormatterNomFichiersDejaFormattes(){
       String[] obtenu;
        String[] espere =  new String[3];
        espere[0] = "a.json";
        espere[1] = "b.json";
        espere[2] = "c.json";
        argsTest[0] = "a.json";
        argsTest[1] = "b.json";
        argsTest[2] = "c.json";
        obtenu = Refund.formatterNomsFichiers(argsTest);
        Assert.assertArrayEquals(espere,obtenu); 
          
    }
    
}
