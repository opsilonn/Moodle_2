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
 *
 * Cette classe hérite de {@link Personne}
 */
public class Eleve extends Personne {

    private List<Evaluation> evaluations;
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
     * @param nom Nom de l'{@link Eleve} à instancier ({@link Personne})
     * @param prenom Prénom de l'{@link Eleve} à instancier ({@link Personne})
     * @param dateNaissance Date de naissance de l'{@link Eleve} à instancier
     * ({@link Personne})
     */
    public Eleve(String nom, String prenom, Date dateNaissance) {
        super(nom, prenom, dateNaissance);
        evaluations = new ArrayList<>();
    }

    /**
     * Donne la valeur de l'{@code ID} (identifiant unique) de l'instance
     * d'{@link Eleve}
     *
     * @return Valeur de la propriété {@code ID} de l'instance
     */
    public int getID() {
        return this.ID;
    }

    /**
     * Calcul de la moyenne des évaluations de l'instance d'{@link Eleve}
     *
     * @return Moyenne des évaluations de l'instance
     * @throws IllegalStateException S'il n'existe aucune instance
     * d'{@link Evaluation} associée à l'instance d'{@link Eleve}
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
    public double getMoyenne(Matiere matiere) {
        double sum = 0;
        int nb_evaluation = 0;

        for (Evaluation e : this.evaluations) {
            if (e.getCodeMatiere().equals(matiere.getCode())) {  // TODO: Utiliser plutôt  e.getMatiere().equals(matiere)
                sum += e.getNote();
                nb_evaluation++;
            }
        }

        return sum / nb_evaluation;
    }

    /**
     * Calcule la médiane des {@link Evaluation} de l'instance d'{@link Eleve}
     *
     * @return Valeur de la médiane des {@link Evaluation} de l'instance
     * @throws IllegalStateException Si aucune {@link Evaluation} n'est associée
     * à l'instance d'{@link Eleve}
     */
    public double getMediane() throws IllegalStateException {
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
        if (middle % 2 == 1) {  // Si le nb de note est impair : retourne la note du milieu
            return evaluations.get(middle).getNote();
        } else {  // Si le nb de note est pair : moyenne des deux notes du milieu
            return (evaluations.get(middle - 1).getNote() + evaluations.get(middle).getNote()) / 2;
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
        //+ this.evaluations.get(0).getNote();
    }

    /**
     * Retourne le set de tous les correcteurs de l'étudiant
     */
    public Set<Professeur> getCorrecteurs() {
        HashSet correcteurs = new HashSet<Professeur>();
        for (Evaluation e : this.evaluations) {
            correcteurs.add(e.getCorrecteur());
        }

        return correcteurs;
    }

    public Evaluation getEvaluation(int index) {
        try {
            return evaluations.get(index);
        } catch (Exception e) {
            return null;
        }
    }

    public void addEvaluation(Evaluation evaluation) {
        this.evaluations.add(evaluation);
    }

    public void setPromo(String promo) {
        this.promotion = promo;
    }

    public String toStringNotes() {
        StringBuilder str = new StringBuilder();
        str.append("notes: ");
        for (Evaluation eval : this.evaluations) {
            str.append(eval.getCodeMatiere()).append(" ").append(eval.getNote()).append("\n");
        }
        try {
            str.append("moyenne = ").append(this.getMoyenneGenerale()).append("\n");
            str.append("mediane = ").append(this.getMediane()).append("\n");
            str.append("correcteur(s): ").append(this.getCorrecteurs()).append("\n");
            return str.toString();
        } catch (IllegalStateException e) {
            return "pas d'évaluation présente\n";
        }
    }
}
