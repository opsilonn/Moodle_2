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
     * Donne la valeur de l'{@code ID} (identifiant unique) de l'instance d'{@link Eleve}
     * @return Valeur de la propriété {@code ID} de l'instance
     */
    public int getID () {
        return this.ID;
    }


    /**
     * Calcul de la moyenne des évaluations de l'instance d'{@link Eleve}
     * @return Moyenne des évaluations de l'instance
     * @throws IllegalStateException S'il n'existe aucune instance d'{@link Evaluation} associée à l'instance d'{@link Eleve}
     */
    public double getMoyenneGenerale () throws IllegalStateException {
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


    /* Moyenne de l'étudiant par matière */
    public double moyenne (Matiere matiere){
        int sum = 0;
        int nb_evaluation = 0;
        for (Evaluation e : this.evaluations)
        {
            if (e.getCodeMatiere() == matiere.getCode()){
                sum += e.getNote();
                nb_evaluation ++;
            }
        }
        return sum / nb_evaluation;
    }

    /* Calcul de la médiane des evaluations de l'étudiant */
    public double mediane () {
        // cas où il n'y a pas d'évaluations
        if (this.evaluations.isEmpty()) {
            throw new IllegalStateException();
        }

        // tri des notes de l'étudiant par ordre ascendant
        Collections.sort(evaluations);

        int middle = evaluations.size() / 2;
        if (middle % 2 == 1) // si le nb de note est impair : retourne la note du milieu
        {
            return evaluations.get(middle).getNote();
        } else {
            // si le nb de note est pair : moyenne des deux notes du milieu
            return (evaluations.get(middle - 1).getNote()
                    + evaluations.get(middle).getNote()) / 2;
        }

    }


    /**
     * Créé une représentation textuelle de l'instance de {@link Eleve}
     * @return Représentation textuelle de l'instance
     */
    @Override
    public String toString () {
        // TODO: Ajouter l'affichage des notes

        return String.format(
            "Eleve #%s : %s %s, né(e) le %s",
            this.ID,
            this.nom.toUpperCase(), this.prenom,
            new SimpleDateFormat("dd MMM yyyy").format(this.dateNaissance)
        );
    }


    /**
     *  Retourne le set de tous les correcteurs de l'étudiant
     */
    public Set<Professeur> getCorrecteurs () {
        HashSet correcteurs = new HashSet<Professeur>();
        for (Evaluation e : this.evaluations) {
            correcteurs.add(e.getCorrecteur());
        }

        return correcteurs;
    }
}
