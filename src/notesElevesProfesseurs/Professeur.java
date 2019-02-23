package notesElevesProfesseurs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Représente un professeur
 *
 * Chaque professeur possède une liste de {@link Matiere}s qu'il enseigne
 *
 * Cette classe hérite de {@link Personne}
 */
public class Professeur extends Personne {

    //private List<Matiere> matieres;  // Matières enseignées
    private Matiere matiere; // Matière enseigné

    /**
     * Constructeur par défaut pour un {@link Professeur} Créé une liste de
     * {@link Matiere}s vide et une {@link Personne} vide
     */
    @Deprecated  // On doit éviter de créer des Professeurs vides
    public Professeur() {
        super();
        //this.matieres = new ArrayList<Matiere>();
        matiere = new Matiere("216ST", "mathématiques");
    }

    /**
     * Constructeur pour un professeur.
     *
     * Reprend exactement le constructeur de {@link Personne}
     *
     * @param nom Nom du Professeur à instancier ({@link Personne})
     * @param prenom Prénom du Professeur à instancier ({@link Personne})
     * @param dateNaissance Date de naissance du Professeur à instancier
     * ({@link Personne})
     */
    public Professeur(String nom, String prenom, Date dateNaissance, Matiere matiere) {
        super(nom, prenom, dateNaissance);
        this.matiere = matiere;
    }

    /**
     * Créé une représentation textuelle de l'instance de {@link Professeur}
     *
     * @return Représentation textuelle de l'instance
     */
    /*@Override
    public String toString() {
        return String.format(
            "Professeur #%s : %s %s, né(e) le %s",
            this.ID,
            this.nom.toUpperCase(), this.prenom,
            new SimpleDateFormat("dd MMM yyyy").format(this.dateNaissance)
        );
    }*/
    public Eleve rechercher(Promotion promo, int idEleve) {
        return promo.rechercher(idEleve);
    }

    public void setNote(Promotion promo, int idEleve, int note, int index) throws IllegalStateException {
        Eleve eleve = this.rechercher(promo, idEleve);
        if (eleve == null) {
            throw new IllegalStateException();
        }

        Evaluation eval = eleve.getEvaluation(index);
        if (eval != null) {
            eval.setNote(note);
        } else {
            eleve.addEvaluation(new Evaluation(eleve, this, matiere, note));
        }
    }
}
