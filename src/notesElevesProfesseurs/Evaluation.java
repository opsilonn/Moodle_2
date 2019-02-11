package notesElevesProfesseurs;

public class Evaluation
{
    private double note;
    Eleve auteur;
    Professeur correcteur;


    public Evaluation()
    {
        note = 10;
        auteur = new Eleve();
        correcteur = new Professeur();
    }


    public Evaluation(Eleve pAuteur, Professeur pCorrecteur)
    {
        note = 10;
        auteur = pAuteur;
        correcteur = pCorrecteur;
    }


    public String toString()
    {
        return "Cé Pô Implaimantay";
    }
}