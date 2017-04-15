
package refund;

import java.io.*;
import net.sf.json.JSONException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class ErreurTest {
     private Exception except;
    private String fichierBidon;
    
    @Before
    public void setUp(){
    fichierBidon = "nomFichierBidon.txt";
    except = new Exception("Ceci fonctionne correctement.");
    }
    @After
    public void tearDown(){
    fichierBidon = null;
    except = null;
    }
    
    @Test
    public void testErreurDonnee() 
            throws FileNotFoundException, IOException{
        String contenuFichier;
        Erreur.erreurDonnees(except,fichierBidon);
        BufferedReader sortie = 
                new BufferedReader(new FileReader(fichierBidon));
        contenuFichier = sortie.readLine();
        sortie.close();
        assertEquals("{\"message\": \"Ceci fonctionne correctement.\"}",
                contenuFichier);
        
    }
   @Test
   public void testErreurJsonErreurFichierALire()
           throws FileNotFoundException, IOException{
       String contenuFichier;
       JSONException exceptJS =
               new JSONException("Ceci fonctionne correctement");
        Erreur.erreurJson(exceptJS,fichierBidon);
        BufferedReader sortie =
                new BufferedReader(new FileReader(fichierBidon));
        contenuFichier = sortie.readLine();
        sortie.close();
        assertEquals("{\"message\": \"Erreur dans le fichier d'entree.\"}",
                contenuFichier);
   }
   
   @Test
   public void testErreurJsonErreurProprieteManquanteExpected() 
           throws FileNotFoundException, IOException{
       String contenuFichier;
       JSONException exceptJS = new JSONException("Expected \"proriete\"");
        Erreur.erreurJson(exceptJS,fichierBidon);
        BufferedReader sortie =
                new BufferedReader(new FileReader(fichierBidon));
        contenuFichier = sortie.readLine();
        sortie.close();
        assertEquals("{\"message\": \"Erreur dans le fichier d'entree.\"}",
                contenuFichier);
   }
   
   @Test
   public void testErreurJsonErreurProprieteManquanteMissing()
           throws FileNotFoundException, IOException{
       String contenuFichier;
       JSONException exceptJS =
               new JSONException("Missing \"proriete\"");
        Erreur.erreurJson(exceptJS,fichierBidon);
        BufferedReader sortie =
                new BufferedReader(new FileReader(fichierBidon));
        contenuFichier = sortie.readLine();
        sortie.close();
        assertEquals("{\"message\": \"Erreur dans le fichier d'entree.\"}",
                contenuFichier);
   }
   
   @Test
   public void testErreurJsonErreurProprieteManquanteUnterminated() 
           throws FileNotFoundException, IOException{
       String contenuFichier;
       JSONException exceptJS = new JSONException("Unterminated \"proriete\"");
        Erreur.erreurJson(exceptJS,fichierBidon);
        BufferedReader sortie = 
                new BufferedReader(new FileReader(fichierBidon));
        contenuFichier = sortie.readLine();
        sortie.close();
        assertEquals("{\"message\": \"Erreur dans le fichier d'entree.\"}",
                contenuFichier);
   }
   
   @Test
   public void testErreurJsonErreurProprieteManquante()
           throws FileNotFoundException, IOException{
       String contenuFichier;
       JSONException exceptJS = new JSONException("\" prorieteManquante \"");
        Erreur.erreurJson(exceptJS,fichierBidon);
        BufferedReader sortie = 
                new BufferedReader(new FileReader(fichierBidon));
        contenuFichier = sortie.readLine();
        sortie.close();
        assertEquals("{\"message\": \"La propriete  prorieteManquante  est"
                + " manquante.\"}", contenuFichier);
   }
   
   @Test
   public void testRetourProprieteManquantes(){
      String obtenu = Erreur.retourProprieteManquantes("Message d'erreur"
              +" générique");
      String espere = "Erreur dans le fichier d'entree.";
        assertEquals(espere,obtenu);
   }
   
   @Test
   public void testRetourProprieteManquantesExpected(){
      String obtenu = Erreur.retourProprieteManquantes("Expected \"proriete\"");
      String espere = "Erreur dans le fichier d'entree.";
        assertEquals(espere,obtenu);
   }
   
   @Test
   public void testRetourProprieteManquantesMissing(){
      String obtenu = Erreur.retourProprieteManquantes("Missing \"proriete\"");
      String espere = "Erreur dans le fichier d'entree.";
        assertEquals(espere,obtenu);
   }
   
   
   @Test
   public void testRetourProprieteManquantesUnterminated(){
      String obtenu = Erreur.retourProprieteManquantes("Unterminated"
              + " \"proriete\"");
      String espere = "Erreur dans le fichier d'entree.";
        assertEquals(espere,obtenu);
   }
   
  @Test
   public void testRetourProprieteManquantesAvecPropriete(){
      String obtenu = Erreur.retourProprieteManquantes("\"proprieteManquante\"");
      String espere = "La propriete proprieteManquante est manquante.";
        assertEquals(espere,obtenu);
   }
   
   @Test
   public void testProprieteInexistanteTrue(){
      String msgErreur = "\"proprieteManquante\"";
      boolean obtenu = Erreur.proprieteInexistante(msgErreur);
        assertEquals(false,obtenu);
   }
   
   @Test
   public void testProprieteInexistanteFalseExpected(){
      String msgErreur = "Expected ";
      boolean obtenu = Erreur.proprieteInexistante(msgErreur);
        assertEquals(true,obtenu);
   }
   
   @Test
   public void testProprieteInexistanteFalseMissing(){
      String msgErreur = "Missing ";
      boolean obtenu = Erreur.proprieteInexistante(msgErreur);
        assertEquals(true,obtenu);
   }
   @Test
   public void testProprieteInexistanteFalseUnterminated(){
      String msgErreur = "Unterminated ";
      boolean obtenu = Erreur.proprieteInexistante(msgErreur);
        assertEquals(true,obtenu);
   }
   
   @Test
   public void testProprieteInexistanteManqueGuillementPropriete(){
      String msgErreur = " ProprieteManquante ";
      boolean obtenu = Erreur.proprieteInexistante(msgErreur);
        assertEquals(true,obtenu);
   }
}
