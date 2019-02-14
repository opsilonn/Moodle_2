package notesElevesProfesseurs;

import java.text.SimpleDateFormat;  // Format des dates
import java.util.Date;  // On préfère utiliser des classes déjà existantes en Java
import java.util.UUID;  // Pour la génération d'identifiants uniques


/**
 * Classe de base pour chaque entité de type {@link Personne}
 * Ne doit pas pouvoir être instanciée en tant que telle, et est donc abstraite.
 */
public abstract class Personne {

    protected String ID;
    protected String nom;
    protected String prenom;
    protected Date dateNaissance;

    private static final String NOM_PAR_DEFAUT = "Doe";
    private static final String PRENOM_PAR_DEFAUT = "John";


    /**
     * Constructeur par défaut
     * @deprecated Éviter d'utiliser ce constructeur autant que possible, les paramètres par défaut sont fixes, pas aléatoires
     */
    @Deprecated  // On évite de créer des Personnes vides
    public Personne() {
        this.nom = NOM_PAR_DEFAUT;
        this.prenom = PRENOM_PAR_DEFAUT;
        this.ID = UUID.randomUUID().toString();  // ID généré par une bibliothèque Java native
        this.dateNaissance = new Date();
    }


    /**
     * Constructeur pour une {@link Personne}
     * @param nom Nom de la personne
     * @param prenom Prénom de la personne
     * @param dateNaissance Date de naissance de la personne
     */
    public Personne (String nom, String prenom, Date dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.ID = UUID.randomUUID().toString();   // ID généré par une bibliothèque Java native
    }


    /**
     * Créé une représentation textuelle de l'instance
     * @return Représentation textuelle de l'instance
     */
    public String toString () {
        return String.format(
            "Personne #%s : %s %s, né(e) le %s",
            this.ID,
            this.nom.toUpperCase(), this.prenom,
            new SimpleDateFormat("dd MMM yyyy").format(this.dateNaissance)
        );
    }
}
