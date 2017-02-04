/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

/**
 * Cette classe sert a modeliser un contrat d'assurence
 * @author Jean Salama
 */
public class Contrat {
    // contient les règles du remboursement
    private static String[][] regles = chargerRegles(); 
    
    private String type; // type du contrat

    /**
     * Ce constructeur cree un contrat avec son type
     * @param type un String d'un caractere parmi 'A', 'B', 'C', 'D'
     * @throws ContratException si le type du contrat n'est pas valide
     */
    public Contrat(String type) throws ContratException {
        setType(type);                
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws ContratException{
        if(!estTypeValide(type))
            throw new ContratException("Type de contrat invalide !");        
        this.type = type;
    }
    
    /**
     * Cette methode calcule le remboursement etant donne une reclamation
     * @param reclam une reclamation valide
     * @return String le remboursement concatene a '$'
     */
    public String calculRemboursement(Reclamation reclam){
        double remboursement = 0;
        int soin = reclam.getSoin();
        
        return remboursement + "$";
    }
    
    /**
     * 
     * @param type 
     * @return boolean true si le type est valide, false sinon
     */
    private boolean estTypeValide(String type){
        return type != null && type.length() == 1 
                && "ABCD".contains(type);
    }
    
    /**
     * 
     * @return String[][] une representation des regles de remboursement
     *                    sous forme de double tableaux
     */
    private static String[][] chargerRegles(){
        return new String[9][5];
    }
    
    
}

class ContratException extends Exception {
    public ContratException(){}
    
    public ContratException(String msg){
        super(msg);
    }
}
