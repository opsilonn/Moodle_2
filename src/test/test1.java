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
public class test1 {

    public static void functiontest1() {
        Promotion promo = new Promotion("TheBest");
        Matiere matiere = new Matiere("216ST", "maths");

        Eleve E = new Eleve();
        Eleve E1 = new Eleve("Buna", "Célia", new Date());
        Eleve E2 = new Eleve("Jean", "névèle", new Date());

        Professeur P = new Professeur();
        Professeur P2 = new Professeur("Pro", "Faiseur", new Date(), matiere);

        Matiere M = new Matiere();
        Matiere M2 = new Matiere("1234", "Hello");

        promo.addEleve(E);
        promo.addEleve(E1);
        promo.addEleve(E2);
        P2.setNote(promo, 20190002, 14, 1);
        P2.setNote(promo, 20190001, 18, 1);

        System.out.println(E);
        System.out.println(E2);

        System.out.println(P);
        System.out.println(P2);
        System.out.println(promo.rechercher(20190001).getEvaluation(0));
        System.out.println(promo.rechercher(20190002).getEvaluation(0));

        System.out.println("moy générale promo : " + promo.moyenneGeneralePromotion());
        System.out.println("moy promo maths: " + promo.moyennePromotion(matiere));

        /*System.out.println("\n\nPromotion: \n"+ promo);
        promo.triMoyenneCroissant();
        System.out.println("\n\nPromotion: \n"+ promo);
        promo.triMoyenneDecroissant();
        System.out.println("\n\nPromotion: \n"+ promo);
        
        promo.triMedianeCroissant();
        System.out.println("\n\nPromotion: \n"+ promo);
        promo.triMedianeDecroissant();
        System.out.println("\n\nPromotion: \n"+ promo);*/
    }

}
