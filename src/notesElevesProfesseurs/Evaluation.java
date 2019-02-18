package notesElevesProfesseurs;

public class Evaluation implements Comparable<Evaluation>
{
    private double note;
    private Eleve auteur;
    private Professeur correcteur;
    private Matiere matiere;


    private static final double NOTE_PAR_DEFAUT = 10.0;  // #NoMagicNumbers


    /**
     * Constructeur par défaut.
     * @deprecated Déprécié dans le sens où il vaut mieux ne pas l'utiliser
     */
    @Deprecated  // On évite de créer des évaluations vides
    public Evaluation()
    {
        this.note = NOTE_PAR_DEFAUT;
        this.auteur = new Eleve();
        this.correcteur = new Professeur();
        this.matiere = new Matiere();
    }


    /**
     * Instancie une évaluation à partir de son auteur ({@link Eleve}) et de son correcteur ({@link Professeur})
     * @param auteur {@link Eleve} à l'origine de l'évaluation
     * @param correcteur {@link Professeur} correcteur
     */
   
    public Evaluation(Eleve auteur, Professeur correcteur, Matiere matiere, double note)
    {
        this.note = note;
        this.auteur = auteur;
        this.correcteur = correcteur;
        this.matiere = matiere;
    }
    
    // retourne la valeur de la note de l'évaluation
    public double getNote(){
        return note;
    }
    
    //retourne le correcteur de l'évaluation
    public double getCorrecteur(){
        return note;
    }


    /**
     * Créé une représentation textuelle de l'instance
     * @return Représentation textuelle de l'instance
     */
    @Override
    public String toString()
    {
        return "(" + auteur + " " 
                + correcteur + " " 
                + matiere + " " 
                + note + ")";
    }

    @Override
    public int compareTo(Evaluation o) {
        if (this.note == o.getNote())
            return 0;
        if (this.note < o.getNote())
            return -1;
        return 1;        
    }
}