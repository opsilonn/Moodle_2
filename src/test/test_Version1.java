/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Date;
import notesElevesProfesseurs.*;

/**
 *
 * @author Célia
 */
public class test_Version1 {

    /**
     * Fonction de test de la version 1
     */
    public static void main() {

        System.out.println("Création d'une promotion : 2021\n");
        Promotion promo = new Promotion("2021");

        System.out.println("Création de 3 étudiants : ");
        Eleve E = new Eleve("Don", "Jean", new Date(1997, 3, 30));
        Eleve E1 = new Eleve("Buna", "Marie", new Date(1998, 12, 9));
        Eleve E2 = new Eleve("Ravin", "Cassandra", new Date(1998, 7, 2));
        System.out.println(E.toStringforEval());
        System.out.println(E1.toStringforEval());
        System.out.println(E2.toStringforEval());

        System.out.println("\nCréation de 2 professeurs : ");
        Matiere matiere = new Matiere("220CT", "physique");
        Matiere matiere2 = new Matiere("216ST", "maths");
        Professeur P1 = new Professeur("Leboucher", "Michel", new Date(1970, 8, 4), matiere);
        Professeur P2 = new Professeur("Pichot", "Jeanne", new Date(1965, 5, 23), matiere2);
        System.out.println(P1.profToString());
        System.out.println(P2.profToString());

        System.out.println("\nAttribution des étudiants à la Promotion 2021.");
        promo.addEleve(E);
        promo.addEleve(E1);
        promo.addEleve(E2);

        System.out.println("Recherche de l'étudiant portant l'ID : 20190002");
        System.out.println("Résultat :\n" + promo.rechercher(20190002));

        System.out.println("\nAttribution d'une note de 14 à l'étudiant Ravin Cassandra du professeur Pichot Jeanne.");
        P2.setNote(promo, 20190002, 14, 1);
        System.out.println(promo.rechercher(20190002).getEvaluation(0));
        System.out.println("\nAttribution d'une note de 16 à l'étudiant Ravin Cassandra du professeur Leboucher Michel.");
        P1.setNote(promo, 20190002, 15, 2);
        System.out.println(promo.rechercher(20190002).getEvaluation(1));

        System.out.println("\nAttribution d'une note de 18 à l'étudiant Buna Marie du professeur Pichot Jeanne.");
        P2.setNote(promo, 20190001, 18, 1);
        System.out.println(promo.rechercher(20190001).getEvaluation(0));

        System.out.println(E);
        System.out.println(E1);
        System.out.println(E2);

        System.out.println("\n\nmoy générale promo : " + promo.moyenneGeneralePromotion());
        System.out.println("moy promo maths: " + promo.moyennePromotion(matiere2));
        System.out.println("moy promo physique: " + promo.moyennePromotion(matiere));

        System.out.println("\nAffichage de la Promotion 2021.\n" + promo);
        promo.triMoyenne(true);
        System.out.println("\nTri par moyenne croissante de la promotion: \n" + promo);
        promo.triMoyenne(false);
        System.out.println("\nTri par moyenne décroissante de la promotion: \n" + promo);

        promo.triMediane(true);
        System.out.println("\nTri par médiane croissante de la promotion: \n" + promo);
        promo.triMediane(false);
        System.out.println("\nTri par médiane décroissante de la promotion: \n" + promo);
    }
}
