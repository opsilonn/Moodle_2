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

        Eleve E = new Eleve();
        Eleve E2 = new Eleve("Jean", "névèle", new Date());

        Professeur P = new Professeur();
        Professeur P2 = new Professeur("Pro", "Faiseur", new Date(), new Matiere("216ST", "maths"));

        Matiere M = new Matiere();
        Matiere M2 = new Matiere("1234", "Hello");

        promo.addEleve(E);
        promo.addEleve(E2);
        P2.setNote(promo, 20190001, 14, 1);

        //System.out.println(E);
        System.out.println(E2);

        /*System.out.println(P);
        System.out.println(P2);

        System.out.println(promo.rechercher(20190001).getEvaluation(0));*/

    }

}
