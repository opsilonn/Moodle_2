package notesElevesProfesseurs;

import java.text.SimpleDateFormat;  // Format des dates
import java.util.Date;  // On préfère utiliser des classes déjà existantes en Java
import java.util.UUID;  // Pour la génération d'identifiants uniques


/**
 * Représente une classe Personne
 * Cette classe existe car elle caractérise les informations communes à un {@link Eleve} et un {@link Professeur}.
 * Ces deux classes héritent donc de {@link Personne}.
 */
public abstract class Personne
{
    protected String ID;
    protected String nom;
    protected String prenom;
    protected Date   dateNaissance;


    private static final String NOM_PAR_DEFAUT = "Doe";  // #NoMagicValues
    private static final String PRENOM_PAR_DEFAUT = "John";  // #NoMagicValues


    /**
     * Constructeur par défaut
     */
    public Personne()
    {
        ID = UUID.randomUUID().toString();
        nom = NOM_PAR_DEFAUT;
        prenom = PRENOM_PAR_DEFAUT;
        dateNaissance = new Date();
    }


    /**
     * Instancie une personne en se basant sur son nom, son prénom et sa date de naissance
     * @param nom Nom de la personne à instancier
     * @param prenom Prénom de la personne à instancier
     * @param dateNaissance Date de naissance de la personne à instancier
     */
    protected Personne(String nom, String prenom, Date dateNaissance)
    {
        this.ID = UUID.randomUUID().toString();
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }


    /**
     * Créé une représentation textuelle de l'instance
     * @return Représentation textuelle de l'instance
     */
    public String toString()
    {
        return String.format(
          "%s    #  Personne  # - %s %s, né le %s",
          this.ID, this.prenom, this.nom,
          new SimpleDateFormat("dd MM yyyy").format(this.dateNaissance)
        );
    }
}
