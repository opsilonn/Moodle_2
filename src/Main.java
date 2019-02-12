import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Personne;
import notesElevesProfesseurs.Professeur;

import java.util.Date;

public class Main
{
    public static void main(String[] argv)
    {
        Eleve E = new Eleve();
        Eleve E2 = new Eleve("Jean" , "névèle", new Date());

        Professeur P = new Professeur();
        Professeur P2 = new Professeur("Pro" , "Faiseur", new Date());

        System.out.println(E);
        System.out.println(E2);

        System.out.println(P);
        System.out.println(P2);
    }
}
