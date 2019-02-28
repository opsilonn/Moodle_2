package notesElevesProfesseurs;

import java.time.Year;
import java.util.Date;  // On préfère utiliser des classes déjà existantes en Java

/**
 * Classe de base pour chaque entité de type {@link Personne} Ne doit pas
 * pouvoir être instanciée en tant que telle, et est donc abstraite.
 */
public abstract class Personne {

    protected int ID;
    protected String nom;
    protected String prenom;
    protected Date dateNaissance;

    private static final String NOM_PAR_DEFAUT = "Doe";
    private static final String PRENOM_PAR_DEFAUT = "John";
    private static int index_ID = -1;

    /**
     * Constructeur par défaut
     *
     * @deprecated Éviter d'utiliser ce constructeur autant que possible, les
     * paramètres par défaut sont fixes, pas aléatoires
     */
    @Deprecated  // On évite de créer des Personnes vides
    public Personne() {
        this.nom = NOM_PAR_DEFAUT;
        this.prenom = PRENOM_PAR_DEFAUT;
        this.ID = createID();  // ID généré par une bibliothèque Java native
        this.dateNaissance = new Date();
    }

    /**
     * Constructeur pour une {@link Personne}
     *
     * @param nom Nom de la personne
     * @param prenom Prénom de la personne
     * @param dateNaissance Date de naissance de la personne
     */
    public Personne(String nom, String prenom, Date dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.ID = createID();   // ID généré par une bibliothèque Java native
    }

    /**
     * Créé une instance de {@link Personne} en se basant sur son {@code nom},
     * son {@code prenom}, sa {@code dateNaissance} et son
     *
     * @param nom Nom de la {@link Personne} à créer
     * @param prenom Prénom de la {@link Personne} à créer
     * @param dateNaissance Date de naissance de la {@link Personne} à créer
     * @param ID Identifiant unique de la {@link Personne} à créer
     */
    public Personne(String nom, String prenom, Date dateNaissance, int ID) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.ID = ID;
    }

    /**
     * Créé un ID pour l'instance, l'ID est de la forme YYYY0000 avec YYYY
     * l'année d'entrée dans l'école
     */
    private int createID() {
        return Year.now().getValue() * 10000 + ++Personne.index_ID;  // Génère l'ID
    }

    /**
     * Créé une représentation textuelle de l'instance
     *
     * @return Représentation textuelle de l'instance
     */
    @Override
    public String toString() {
        return "(" + this.nom + ", " + this.prenom + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Personne)) {
            return false;
        }
        Personne p = (Personne) o;
        return p.ID == ID;
    }

    @Override
    public int hashCode() {
        return nom.hashCode() + prenom.hashCode() + dateNaissance.hashCode();
    }
}
