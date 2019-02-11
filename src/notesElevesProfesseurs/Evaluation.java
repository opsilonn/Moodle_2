package notesElevesProfesseurs;

public class Evaluation
{
    private double note;
    Eleve auteur;
    Professeur correcteur;

    Evaluation()
    {
        note = 10;
        auteur = new Eleve();
        correcteur = new Professeur();
    }
}