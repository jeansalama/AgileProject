/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refund;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import net.sf.json.*;




/**
 * Cette classe sert a modeliser un contrat d'assurence
 * @author Jean Salama
 */
public class Contrat {
    // contient les règles du remboursement
    private static JSONObject regles = chargerRegles(); 
    
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
        double montantReclamation = reclam.getMontantReclamationDouble();
        String soin = reclam.getSoin() + "";
        if(reclam.getSoin() >= 300 && reclam.getSoin() < 400)
            soin = "300";
        JSONObject regle = regles.getJSONObject(soin).getJSONObject(type);
        double tauxRemb = regle.getDouble("taux");
        remboursement = montantReclamation*tauxRemb;
        if(regle.has("max") && remboursement > regle.getDouble("max")){
            remboursement = regle.getDouble("max");
        }
        // deux decimales apres la virgule
        DecimalFormat df = new DecimalFormat("#.##");
        
        return df.format(remboursement) + "$";
    }

    @Override
    public String toString() {
        return "" + type;
    }
    
    
    /**
     * Cette methode indique si le type de contrat est valide ou non :
     * cad si c'est un String d'un caractere parmi 'A','B','C' ou 'D'
     * @param type un String representant le type d'un contrat
     * @return boolean true si le type est valide, false sinon
     */
    private boolean estTypeValide(String type){
        return type != null && type.length() == 1 
                && "ABCD".contains(type);
    }
    
    /**
     * Cette methode "charge" le contenu des regles de remboursement
     * pour tout type de contrat et de soin et le retourne dans 
     * un objet de type JSONObject
     * @return JSONObject une representation des regles de remboursement
     */
    private static JSONObject chargerRegles(){
        String stringJson;
        JSONObject regles = new JSONObject();
        try{
            stringJson = Utf8File.loadFileIntoString("Contrats.json");
            regles = (JSONObject) JSONSerializer.toJSON(stringJson);
        } catch(FileNotFoundException fnfe){
            System.out.println(fnfe.getMessage());
        } catch(IOException ioe){
            System.out.println(ioe.getMessage());
        } 
       return regles; 
    }
    
    
}

class ContratException extends Exception {
    public ContratException(){}
    
    public ContratException(String msg){
        super(msg);
    }
}
