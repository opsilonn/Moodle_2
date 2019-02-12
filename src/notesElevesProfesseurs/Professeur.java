package notesElevesProfesseurs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Représente un professeur.
 *  Cette classe hérite de {@link Personne}.
 */
public class Professeur extends Personne
{
    //List<String> matière;


    /**
     * Constructeur par défaut, instancie un {@link Professeur} à partir d'une {@link Personne} par défaut
     */
    public Professeur()
    {
        super();
    }


    /**
     * Instancie un {@link Professeur} à partir des informations d'une personne (nom, prénom, date de naissance)
     * @param nom Nom de la {@link Personne} à instancier
     * @param prenom Prénom de la {@link Personne} à instancier
     * @param dateNaissance Date de naissance de la {@link Personne} à instancier
     */
    public Professeur(String nom, String prenom, Date dateNaissance)
    {
        super(nom, prenom, dateNaissance);
    }
}
