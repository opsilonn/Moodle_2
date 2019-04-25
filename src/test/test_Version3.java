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
import notesElevesProfesseurs.Ecole;

/**
 *
 * @author Célia
 */
public class test_Version3 {
    
    /**
     * Fonction de test de la version 3
     */
    public static void main(){
        Ecole ecole = new Ecole();
        FileHandler scanner = new FileHandler();
        scanner.ReadFiles(ecole);
        
        List<Eleve> eleves = ecole.getPromo("2022").getEleves();
        Bulletin.createBulletin(eleves.get(0), ecole);
        Bulletin.createBulletin(eleves.get(1), ecole);
        Bulletin.createBulletin(eleves.get(2), ecole);
    }
    
}
