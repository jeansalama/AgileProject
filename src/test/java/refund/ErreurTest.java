package refund;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.sf.json.JSONException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class ErreurTest {

    private Exception except;
    private String fichierBidon;

    @Before
    public void setUp() {
        fichierBidon = "nomFichierBidon";
        except = new Exception("Ceci fonctionne correctement.");
    }

    @After
    public void tearDown() throws Exception {
        Path p = Paths.get(fichierBidon);
        Files.deleteIfExists(p);
        fichierBidon = null;
        except = null;
        
    }

    @Test
    public void testErreurDonnee() {
        String contenuFichier = Erreur.erreurDonnees(except, fichierBidon);
        assertEquals("{\"message\": \"Ceci fonctionne correctement.\"}",
                contenuFichier);

    }

    @Test
    public void testErreurJsonErreurFichierALire() {
        JSONException exceptJS
                = new JSONException("Ceci fonctionne correctement");
        String contenuFichier = Erreur.erreurJson(exceptJS, fichierBidon);
        assertEquals("{\"message\": \"Erreur dans le fichier d'entree.\"}",
                contenuFichier);
    }

    @Test
    public void testErreurJsonErreurProprieteManquanteExpected() {
        JSONException exceptJS = new JSONException("Expected \"proriete\"");
        String contenuFichier = Erreur.erreurJson(exceptJS, fichierBidon);
        assertEquals("{\"message\": \"Erreur dans le fichier d'entree.\"}",
                contenuFichier);
    }

    @Test
    public void testErreurJsonErreurProprieteManquanteMissing() {
        JSONException exceptJS
                = new JSONException("Missing \"proriete\"");
        String contenuFichier = Erreur.erreurJson(exceptJS, fichierBidon);
        assertEquals("{\"message\": \"Erreur dans le fichier d'entree.\"}",
                contenuFichier);
    }

    @Test
    public void testErreurJsonErreurProprieteManquanteUnterminated() {
        JSONException exceptJS = new JSONException("Unterminated \"proriete\"");
        String contenuFichier = Erreur.erreurJson(exceptJS, fichierBidon);
        assertEquals("{\"message\": \"Erreur dans le fichier d'entree.\"}",
                contenuFichier);
    }

    @Test
    public void testErreurJsonErreurProprieteManquante() {
        JSONException exceptJS = new JSONException("\" prorieteManquante \"");
        String contenuFichier = Erreur.erreurJson(exceptJS, fichierBidon);
        assertEquals("{\"message\": \"La propriete  prorieteManquante  est"
                + " manquante.\"}", contenuFichier);
    }

    @Test
    public void testRetourProprieteManquantes() {
        String obtenu = Erreur.retourProprieteManquantes("Message d'erreur"
                + " générique");
        String espere = "Erreur dans le fichier d'entree.";
        assertEquals(espere, obtenu);
    }

    @Test
    public void testRetourProprieteManquantesExpected() {
        String obtenu = Erreur.retourProprieteManquantes("Expected \"proriete\"");
        String espere = "Erreur dans le fichier d'entree.";
        assertEquals(espere, obtenu);
    }

    @Test
    public void testRetourProprieteManquantesMissing() {
        String obtenu = Erreur.retourProprieteManquantes("Missing \"proriete\"");
        String espere = "Erreur dans le fichier d'entree.";
        assertEquals(espere, obtenu);
    }

    @Test
    public void testRetourProprieteManquantesUnterminated() {
        String obtenu = Erreur.retourProprieteManquantes("Unterminated"
                + " \"proriete\"");
        String espere = "Erreur dans le fichier d'entree.";
        assertEquals(espere, obtenu);
    }

    @Test
    public void testRetourProprieteManquantesAvecPropriete() {
        String obtenu = Erreur.retourProprieteManquantes("\"proprieteManquante\"");
        String espere = "La propriete proprieteManquante est manquante.";
        assertEquals(espere, obtenu);
    }

    @Test
    public void testProprieteInexistanteTrue() {
        String msgErreur = "\"proprieteManquante\"";
        boolean obtenu = Erreur.proprieteInexistante(msgErreur);
        assertEquals(false, obtenu);
    }

    @Test
    public void testProprieteInexistanteFalseExpected() {
        String msgErreur = "Expected ";
        boolean obtenu = Erreur.proprieteInexistante(msgErreur);
        assertEquals(true, obtenu);
    }

    @Test
    public void testProprieteInexistanteFalseMissing() {
        String msgErreur = "Missing ";
        boolean obtenu = Erreur.proprieteInexistante(msgErreur);
        assertEquals(true, obtenu);
    }

    @Test
    public void testProprieteInexistanteFalseUnterminated() {
        String msgErreur = "Unterminated ";
        boolean obtenu = Erreur.proprieteInexistante(msgErreur);
        assertEquals(true, obtenu);
    }

    @Test
    public void testProprieteInexistanteManqueGuillementPropriete() {
        String msgErreur = " ProprieteManquante ";
        boolean obtenu = Erreur.proprieteInexistante(msgErreur);
        assertEquals(true, obtenu);
    }
}
