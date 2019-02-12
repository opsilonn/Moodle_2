package notesElevesProfesseurs;


import java.text.SimpleDateFormat;
import java.util.Date;  // On favorise l'utilisation des classes déjà existantes en Java


/**
 * Classe représentant un élève.
 *  Cette classe hérite de {@link Personne}
 */
public class Eleve extends Personne
{
    /**
     * Constructeur par défaut : créé une instance vide
     */
    public Eleve()
    {
        super();
    }


    /**
     * Constructeur prenant en compte le nom, le prénom
     * et la date de naissance de l'élève à instancier
     * @param nom Nom de la {@link Personne} qui définit l'élève
     * @param prenom Prénom de la {@link Personne} qui définit l'élève
     * @param dateDeNaissance {@link Personne} qui définit l'élève
     */
    public Eleve(String nom, String prenom, Date dateDeNaissance)
    {
        super(nom, prenom, dateDeNaissance);
    }


    /**
     * Créé une représentation textuelle de l'instance
     * @return Représentation textuelle de l'instance
     */
    public String toString()
    {
        return String.format(
            "%s   #    Eleve   # - : %s %s, né le %s",
            this.ID,
            this.prenom,
            this.nom,
            new SimpleDateFormat("dd MM yyyy").format(this.dateNaissance)
        );
    }
}