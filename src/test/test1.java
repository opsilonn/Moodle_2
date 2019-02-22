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
    
    public static void functiontest1(){
        Eleve E = new Eleve();
        Eleve E2 = new Eleve("Jean" , "névèle", new Date());

        Professeur P = new Professeur();
        Professeur P2 = new Professeur("Pro" , "Faiseur", new Date());


        Matiere M = new Matiere();
        Matiere M2 = new Matiere("1234","Hello");
        
        Evaluation Ev = new Evaluation();

        System.out.println(E);
        System.out.println(E2);

        System.out.println(P);
        System.out.println(P2);
        
        System.out.println(M);
        System.out.println(M2);
        
        System.out.println(Ev);
    }
    
    
}
