package notesElevesProfesseurs;

import java.util.Date;

/**
 * Représente un professeur
 *
 * Chaque professeur possède une {@link Matiere} qu'il enseigne
 *
 * Cette classe hérite de {@link Personne}
 */
public class Professeur extends Personne {

    private final Matiere matiere; // Matière enseigné

    /**
     * Constructeur par défaut pour un {@link Professeur} Créé une liste de
     * {@link Matiere}s vide et une {@link Personne} vide
     */
    @Deprecated  // On doit éviter de créer des Professeurs vides
    public Professeur() {
        super();
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
     * @param matiere Matiere enseignée par le Professeur à instancier
     */
    public Professeur(String nom, String prenom, Date dateNaissance, Matiere matiere) {
        super(nom, prenom, dateNaissance);
        this.matiere = matiere;
    }

    public Professeur(String nom, String prenom, Date dateNaissance, int ID, Matiere matiere) {
        super(nom, prenom, dateNaissance, ID);
        this.matiere = matiere;
    }

    public Matiere getMatiere() {
        return this.matiere;
    }

    /**
     * Retourne l'Etudiant rechercher
     *
     * @param promo promotion de l'élève rechercher
     * @param idEleve ID de l'élève rechercher
     * @return l'étudiant
     */
    public Eleve rechercher(Promotion promo, int idEleve) {
        return promo.rechercher(idEleve);
    }

    /**
     * Mets une note à un étudiant
     *
     * @param promo promotion de l'élève rechercher
     * @param idEleve ID de l'élève rechercher
     * @param note valeur de la note à ajouter
     * @param index index de la note à modifier
     * @throws IllegalStateException
     */
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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Professeur)) {
            return false;
        }
        Professeur p = (Professeur) o;
        return p.ID == ID;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + matiere.hashCode();
    }

    public String profToCSV() {
        return this.nom + ","
                + this.prenom + ","
                + this.dateNaissance + ","
                + this.ID + ","
                + this.matiere.getCode() + ","
                + this.matiere.getNom() + "\n";
    }

}
