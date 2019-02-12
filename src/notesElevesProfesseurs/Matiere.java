/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.notesElevesProfesseurs;

/**
 *
 * @author Célia
 */
public class Matiere {
    
    private String code;
    private String nom;
    
    @Deprecated
    public Matiere(){
        this.code = "123AB";
        this.nom = "Alphabet";
    }
    
    public Matiere(String code, String nom){
        this.code = code;
        this.nom = nom;
    }
    
    @Override
    public String toString(){
        return code + " " + nom;
    }
    
}
