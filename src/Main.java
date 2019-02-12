import src.notesElevesProfesseurs.*;

import java.util.Date;

public class Main
{
    public static void main(String[] argv)
    {
        Personne p1 = new Personne();
        Personne p2 = new Personne("Jean" , "Kevin", new Date());

        Eleve E = new Eleve();
        Eleve E2 = new Eleve("Jean" , "névèle", new Date());

        Professeur P = new Professeur();
        Professeur P2 = new Professeur("Pro" , "Faiseur", new Date());

        Matiere M = new Matiere();
        Matiere M2 = new Matiere("1234","Hello");
        
        Evaluation Ev = new Evaluation();
                

        System.out.println(p1);
        System.out.println(p2);

        System.out.println(E);
        System.out.println(E2);

        System.out.println(P);
        System.out.println(P2);
        
        System.out.println(M);
        System.out.println(M2);
        
         System.out.println(Ev);
    }
}
