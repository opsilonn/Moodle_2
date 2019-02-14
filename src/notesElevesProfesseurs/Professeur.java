package notesElevesProfesseurs;

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
public class Professeur extends Personne
{
    private List<Matiere> matieres;  // Matières enseignées


    /**
     * Constructeur par défaut pour un {@link Professeur}
     * Créé une liste de {@link Matiere}s vide et une {@link Personne} vide
     */
    @Deprecated  // On doit éviter de créer des Professeurs vides
    public Professeur()
    {
        super();
        this.matieres = new ArrayList<Matiere>();
    }


    /**
     * Constructeur pour un professeur.
     *
     * Reprend exactement le constructeur de {@link Personne}
     * @param nom Nom du Professeur à instancier ({@link Personne})
     * @param prenom Prénom du Professeur à instancier ({@link Personne})
     * @param dateNaissance Date de naissance du Professeur à instancier ({@link Personne})
     */
    public Professeur(String nom, String prenom, Date dateNaissance)
    {
        super(nom, prenom, dateNaissance);
        matieres = new ArrayList<Matiere>();
    }
}
