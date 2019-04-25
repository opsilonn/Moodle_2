/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Gestionfichier.FileHandler;
import java.util.Date;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Matiere;
import notesElevesProfesseurs.Professeur;
import notesElevesProfesseurs.Promotion;
import notesElevesProfesseurs.Ecole;

/**
 *
 * @author Célia
 */
public class test_Version2 {

    /**
     * Fonction de test de la version 2
     */
    public static void main() {
        Ecole ecole = new Ecole();
        FileHandler scanner = new FileHandler();
        scanner.ReadFiles(ecole);

        System.out.println("Affichage des données lues sur le fichier csv :\n" + ecole.toString());

        ecole.addProfesseur(new Professeur("Doe", "Jack", new Date(), new Matiere("278DS", "informatique")));
        Promotion p = new Promotion("2022");

        System.out.println("\nCréation de 3 étudiants : ");
        Eleve E = new Eleve("Petit", "Jacqueline", new Date());
        Eleve E1 = new Eleve("Martin", "Roxanne", new Date());
        Eleve E2 = new Eleve("Leroux", "Thomas", new Date());
        System.out.println(E.toStringforEval());
        System.out.println(E1.toStringforEval());
        System.out.println(E2.toStringforEval());

        p.addEleve(E);
        p.addEleve(E1);
        p.addEleve(E2);
        ecole.addPromo(p);
        System.out.println("Ecriture des données sur le fichier csv :");
        scanner.WriteFiles(ecole);

        scanner.ReadFiles(ecole);
        System.out.println("Affichage des nouvelles données lues sur le fichier csv :\n" + ecole.toString());

    }

}
