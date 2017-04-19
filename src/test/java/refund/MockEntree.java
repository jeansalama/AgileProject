/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import java.util.ArrayList;

/**
 *
 * @author Max
 */
public class MockEntree extends Entree{
    Dossier dossier;
    ArrayList<Reclamation> listeReclamation = new ArrayList<>();
    
    public MockEntree (){
        
    }
    
    public MockEntree(String fichierEntree) throws DateException, DossierException,
            ContratException, ReclamationException {
        dossier = new Dossier("A123456", new Date("2017-10"));
        listeReclamation.add(new Reclamation(100, new Date("2017-10-01"), 
                "100,00$"));
    }
            
    @Override
    public Dossier getDossier(){
        return dossier;
    }
    
    @Override
    public ArrayList<Reclamation> getListeReclamations(){
        return listeReclamation;
    }
    
    @Override
    public String toString(){
        return dossier.toString() + " " + listeReclamation.toString();
    }
}
