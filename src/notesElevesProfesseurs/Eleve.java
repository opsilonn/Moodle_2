package notesElevesProfesseurs;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Représente un élève
 * <p>
 * Cette classe hérite de {@link Personne}
 */
public class Eleve extends Personne {

    private final List<Evaluation> evaluations;
    private String promotion;

    private static final int NB_EVALUATIONS = 10;

    /**
     * Constructeur par défaut
     *
     * @deprecated Déprécié par héritage : on ne doit pas créer de
     * {@link Personne} vide
     */
    @Deprecated
    public Eleve() {
        super();
        evaluations = new ArrayList<>();
    }

    /**
     * Créé un {@link Eleve} à partir des informations nécessaires pour la
     * création d'une {@link Personne}
     *
     * @param nom           Nom de l'{@link Eleve} à instancier ({@link Personne})
     * @param prenom        Prénom de l'{@link Eleve} à instancier ({@link Personne})
     * @param dateNaissance Date de naissance de l'{@link Eleve} à instancier
     *                      ({@link Personne})
     */
    public Eleve(String nom, String prenom, Date dateNaissance) {
        super(nom, prenom, dateNaissance);
        evaluations = new ArrayList<>();
    }


    /**
     * Créé un {@link Eleve} à partir des informations nécessaires pour la
     * création d'une {@link Personne}
     *
     * @param nom           Nom de l'{@link Eleve} à instancier ({@link Personne})
     * @param prenom        Prénom de l'{@link Eleve} à instancier ({@link Personne})
     * @param dateNaissance Date de naissance de l'{@link Eleve} à instancier
     *                      ({@link Personne})
     * @param password      Mot de passe de l'{@link Eleve}
     */
    public Eleve(String nom, String prenom, Date dateNaissance, String password, String promotion) {
        super(nom, prenom, dateNaissance, password);
        this.promotion = promotion;
        evaluations = new ArrayList<>();
    }


    /**
     * Créé un {@link Eleve} à partir des informations nécessaires pour la
     * création d'une {@link Personne}
     *
     * @param nom           Nom de l'{@link Eleve} à instancier ({@link Personne})
     * @param prenom        Prénom de l'{@link Eleve} à instancier ({@link Personne})
     * @param dateNaissance Date de naissance de l'{@link Eleve} à instancier
     *                      ({@link Personne})
     * @param ID            ID étudiant de l'{@link Eleve} à créer.
     * @param password      Mot de passe de l'{@link Eleve}
     */
    public Eleve(String nom, String prenom, Date dateNaissance, int ID, String password) {
        super(nom, prenom, dateNaissance, ID, password);
        evaluations = new ArrayList<>();
    }

    /**
     * Retourne la promotion de l'instance
     *
     * @return la promotion de l'élève
     */
    public String getPromotion() {
        return this.promotion;
    }

    /**
     * Calcul de la moyenne des évaluations de l'instance d'{@link Eleve}
     *
     * @return Moyenne des évaluations de l'instance
     * @throws IllegalStateException S'il n'existe aucune instance
     *                               d'{@link Evaluation} associée à l'instance d'{@link Eleve}
     */
    public double getMoyenneGenerale() throws IllegalStateException {
        // Cas où il n'y a pas d'évaluations
        if (this.evaluations.isEmpty()) {
            throw new IllegalStateException();
        }

        double sum = 0;
        for (Evaluation e : this.evaluations) {
            sum += e.getNote();
        }

        return sum / this.evaluations.size();
    }

    /**
     * Calcule la moyenne de l'instance d'{@link Eleve} dans la {@code matiere}
     * spécifiée
     *
     * @param matiere {@link Matiere} pour laquelle calculer la moyenne
     * @return Valeur de la moyenne dans la {@link Matiere} spécifiée
     */
    public double getMoyenne(Matiere matiere) throws IllegalStateException {

        // Cas où il n'y a pas d'évaluations
        if (this.evaluations.isEmpty()) {
            throw new IllegalStateException();
        }

        double sum = 0;
        int nb_evaluation = 0;

        for (Evaluation e : this.evaluations) {
            if (e.getMatiere().equals(matiere)) {
                sum += e.getNote();
                nb_evaluation++;
            }
        }
        if (nb_evaluation == 0) {
            throw new IllegalStateException();
        }

        return sum / nb_evaluation;
    }

    /**
     * Calcule la médiane des {@link Evaluation} de l'instance d'{@link Eleve}
     *
     * @return Valeur de la médiane des {@link Evaluation} de l'instance
     * @throws IllegalStateException Si aucune {@link Evaluation} n'est associée
     *                               à l'instance d'{@link Eleve}
     */
    public double getMedianeGenerale() throws IllegalStateException {
        // Cas où il n'y a pas d'évaluations
        if (this.evaluations.isEmpty()) {
            throw new IllegalStateException();
        }
        if (this.evaluations.size() == 1) {
            return this.evaluations.get(0).getNote();
        }

        // Tri des notes de l'étudiant par ordre ascendant
        Collections.sort(evaluations);

        int middle = evaluations.size() / 2;  // Détermination de la position de l'évaluation médiane
        if (evaluations.size() % 2 == 1) {  // Si le nb de note est impair : retourne la note du milieu
            return evaluations.get(middle).getNote();
        } else {  // Si le nb de note est pair : moyenne des deux notes du milieu
            return (evaluations.get(middle).getNote() + evaluations.get(middle - 1).getNote()) / 2;
        }

    }

    /**
     * Calculer la médiane par rapport à une matière
     *
     * @param matiere Matière dans laquelle on cherche la médiane
     * @return la médiane de la matière
     * @throws IllegalStateException lorsqu'il n'y a pas d'évaluations
     */
    public double getMediane(Matiere matiere) throws IllegalStateException {

        List<Evaluation> evalMatiere = new ArrayList<>();
        this.evaluations.stream().filter((e) -> (e.getCodeMatiere().equals(matiere.getCode()))).forEachOrdered((e) -> {
            evalMatiere.add(e);
        });

        // Cas où il n'y a pas d'évaluations
        if (evalMatiere.isEmpty()) {
            throw new IllegalStateException();
        }

        if (evalMatiere.size() == 1) {
            return evalMatiere.get(0).getNote();
        }

        // Tri des notes de l'étudiant par ordre ascendant
        Collections.sort(evalMatiere);

        int middle = evalMatiere.size() / 2;  // Détermination de la position de l'évaluation médiane
        if (evalMatiere.size() % 2 == 1) {  // Si le nb de note est impair : retourne la note du milieu
            return evalMatiere.get(middle).getNote();
        } else {  // Si le nb de note est pair : moyenne des deux notes du milieu
            return (evalMatiere.get(middle - 1).getNote() + evalMatiere.get(middle).getNote()) / 2;
        }
    }

    /**
     * Créé une représentation textuelle de l'instance de {@link Eleve}
     *
     * @return Représentation textuelle de l'instance
     */
    @Override
    public String toString() {
        return super.toString()
                + "id: " + this.ID + "\n"
                + "promotion: " + this.promotion + "\n"
                + toStringNotes();
    }

    /**
     * Retourne le set de tous les correcteurs de l'élève
     *
     * @return Set de tous les correcteurs de l'élève
     */
    public Set<Professeur> getCorrecteurs() {
        HashSet<Professeur> correcteurs = new HashSet<>();
        this.evaluations.forEach((e) -> {
            correcteurs.add(e.getCorrecteur());
        });

        return correcteurs;
    }

    /**
     * Retourne toutes les évaluations de l'Eleve {@link Eleve}
     *
     * @return la liste d'évaluations
     */
    public List<Evaluation> getEvaluation() {
        try {
            return evaluations;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Retourne l'évaluation à l'index donné
     *
     * @param index Index de l'évaluation de l'Eleve {@link Eleve}
     * @return l'Evaluation correspondante à l'index
     */
    public Evaluation getEvaluation(int index) {
        try {
            return evaluations.get(index);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Ajout d'une évaluation à l'étudiant
     *
     * @param evaluation Evaluation de l'étudiant à ajouter
     */
    public void addEvaluation(Evaluation evaluation) {
        if (this.evaluations.size() < NB_EVALUATIONS) {
            this.evaluations.add(evaluation);
        } else {
            System.out.println("This student already has " + NB_EVALUATIONS + " evaluations.");
        }
    }

    /**
     * Assignation de l'attribut promotion pour l'élève
     *
     * @param promo Nom de la promotion de l'élève
     */
    public void setPromo(String promo) {
        this.promotion = promo;
    }

    /**
     * Retourne les {@link Evaluation}s d'une {@link Matiere} particulière
     *
     * @param matiere {@link Matiere} recherchée
     * @return l'ensemble des {@link Evaluation} d'une matière
     * @throws IllegalStateException Quand il n'y a pas d'évaluations dans la
     *                               {@link Matiere} demandée
     */
    public List<Evaluation> getEvaluations(Matiere matiere) throws IllegalStateException {
        ArrayList<Evaluation> eval_matiere = new ArrayList<>();
        for (Evaluation eval : this.evaluations) {
            if (eval.getCodeMatiere().equals(matiere.getCode())) {
                eval_matiere.add(eval);
            }
        }
        if (eval_matiere.isEmpty()) {
            throw new IllegalStateException();
        }

        return eval_matiere;
    }

    /**
     * Retourne une liste de toutes les {@link Matiere} suivies par l'élève
     * {@link Eleve}
     *
     * @return liste de toutes les {@link Matiere} suivies par l'élève
     * {@link Eleve}
     */
    public List<Matiere> getMatiere() {
        List<Matiere> matieres = new ArrayList<>();

        for (Evaluation eval : evaluations) {
            if (!matieres.contains(eval.getMatiere())) {
                matieres.add(eval.getMatiere());
            }
        }

        return matieres;
    }

    /**
     * Créé une représentation textuelle des notes de {@link Eleve}
     *
     * @return représentation textuelle de l'élève selon Personne
     */
    private String toStringNotes() {
        if (this.evaluations.isEmpty()) {
            return "pas d'évaluation présente\n";
        }

        StringBuilder str = new StringBuilder();
        str.append("notes:  ");
        this.evaluations.forEach((Evaluation eval) -> {
            str.append(eval.getCodeMatiere()).append(" ").append(eval.getNote()).append("\n\t");
        });
        try {
            str.append("moyenne = ").append(this.getMoyenneGenerale()).append("\n");
            str.append("mediane = ").append(this.getMedianeGenerale()).append("\n");
            str.append("correcteur(s): ").append(this.getCorrecteurs()).append("\n");
            return str.toString();
        } catch (IllegalStateException e) {
            return "pas d'évaluation présente\n";
        }
    }

    /**
     * Créé une représentation textuelle de l'instance de {@link Eleve}
     *
     * @return représentation textuelle de l'élève selon Personne
     */
    public String toStringforEval() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Eleve)) {
            return false;
        }
        Eleve e = (Eleve) o;
        return e.ID == ID;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + promotion.hashCode();
    }

    /**
     * Fonction retournant une représentation textuelle en CSV d'un élève
     *
     * @return la représentation textuelle
     */
    public String elevesToCSV() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String stringCSV = this.nom + ","
                + this.prenom + ","
                + dateFormat.format(this.dateNaissance) + ","
                + this.ID + ","
                + this.password + ","
                + this.promotion;

        for (Evaluation eval : this.evaluations) {
            stringCSV += "," + eval.EvalToCSV();
        }

        return stringCSV;
    }
}
