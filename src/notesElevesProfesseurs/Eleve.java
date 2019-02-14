package notesElevesProfesseurs;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Représente un élève
 *
 * Cette classe hérite de {@link Personne}
 */
public class Eleve extends Personne
{
    /**
     * Constructeur par défaut
     *
     * @deprecated Déprécié par héritage : on ne doit pas créer de {@link Personne} vide
     */
    @Deprecated
    public Eleve ()
    {
        super();
    }


    /**
     * Créé un {@link Eleve} à partir des informations nécessaires pour la création d'une {@link Personne}
     * @param nom Nom de l'{@link Eleve} à instancier ({@link Personne})
     * @param prenom Prénom de l'{@link Eleve} à instancier ({@link Personne})
     * @param dateNaissance Date de naissance de l'{@link Eleve} à instancier ({@link Personne})
     */
    public Eleve (String nom, String prenom, Date dateNaissance) {
        super(nom, prenom, dateNaissance);
    }
}