/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Gestionfichier.FileHandler;
import java.util.List;
import notesElevesProfesseurs.Bulletin;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.School;

/**
 *
 * @author Célia
 */
public class test_Version3 {
    
    public void main(){
        School ecole = new School();
        FileHandler scanner = new FileHandler();
        scanner.ReadFiles(ecole);
        ecole.toString();
        
        List<Eleve> eleves = ecole.getPromo("2013").getEleves();
        System.out.println(eleves.get(0));
        Bulletin.createBulletin(eleves.get(0), ecole);
    }
    
}
