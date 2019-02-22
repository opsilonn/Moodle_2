package notesElevesProfesseurs;

public class Evaluation implements Comparable<Evaluation>
{
    private double note;
    private Eleve auteur;
    private Professeur correcteur;
    private Matiere matiere;
    private int id;

    private static int _compteur_evaluations = 0;


    private static final double NOTE_PAR_DEFAUT = 10.0;  // #NoMagicNumbers


    /**
     * Constructeur par défaut.
     * @deprecated Déprécié dans le sens où il vaut mieux ne pas l'utiliser
     */
    @Deprecated  // On évite de créer des évaluations vides
    public Evaluation()
    {
        this(new Eleve(), new Professeur(), new Matiere(),  NOTE_PAR_DEFAUT);
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
        this.id = Evaluation._compteur_evaluations++;
    }


		/**
		 * Retourne la valeur de la note de l'évaluation
		 * @return Valeur de la note de l'évaluation
		 */
	    public double getNote() {
	        return this.note;
	    }


    //retourne le correcteur de l'évaluation
    public double getCorrecteur(){
        return note;
    }
    
    public String getCodeMatiere(){
        return this.matiere.getCode();
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