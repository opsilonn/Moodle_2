package notesElevesProfesseurs;

import java.time.Year;
import java.util.Date;  // On préfère utiliser des classes déjà existantes en Java

/**
 * Classe de base pour chaque entité de type {@link Personne} Ne doit pas
 * pouvoir être instanciée en tant que telle, et est donc abstraite.
 */
public abstract class Personne
{
    protected int ID;
    protected String password;
    protected String nom;
    protected String prenom;
    protected Date dateNaissance;

    private static final String NOM_PAR_DEFAUT = "Doe";
    private static final String PRENOM_PAR_DEFAUT = "John";
    private static int index_ID = -1;
    private static String PASSWORD_PAR_DEFAUT = "1";

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
        this.ID = createID(); 
        this.dateNaissance = new Date();
        this.password = PASSWORD_PAR_DEFAUT;
    }
    
    /**
     * Set l'index_ID static de la classe 
     * @param index - la valeur à donnée à l'index_ID
     */
    public static void setIndex(int index){
        index_ID = index;
    }
    
    /**
     * Retourne le ID_static de la classe 
     * @return the index_ID of a person
     */
    public static int getIndex(){
        return index_ID;
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
        this.password = PASSWORD_PAR_DEFAUT;
    }

    /**
     * Créé une instance de {@link Personne} en se basant sur son {@code nom},
     * son {@code prenom}, sa {@code dateNaissance} et son
     *
     * @param nom Nom de la {@link Personne} à créer
     * @param prenom Prénom de la {@link Personne} à créer
     * @param dateNaissance Date de naissance de la {@link Personne} à créer
     * @param ID Identifiant unique de la {@link Personne} à créer
     * @param password Mot de passe lié à la {@link Personne}
     */
    public Personne(String nom, String prenom, Date dateNaissance, int ID, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.ID = ID;
        this.password = password;
    }

    /**
     * Créé un ID pour l'instance, l'ID est de la forme YYYY0000 avec YYYY
     * l'année d'entrée dans l'école
     */
    private int createID() {
        return Year.now().getValue() * 10000 + ++Personne.index_ID;  // Génère l'ID
    }

    /**
     * Donne la valeur de l'{@code ID} (identifiant unique) de l'instance
     * d'{@link Personne}
     *
     * @return Valeur de la propriété {@code ID} de l'instance
     */
    public int getID() {
        return this.ID;
    }

    /** @return Valeur de la propriété {@code password} de l'instance */
    public String getPassword() {
        return this.password;
    }

    /** @return Valeur de la propriété {@code nom} de l'instance */
    public String getNom() {
        return this.nom;
    }

    /** @return Valeur de la propriété {@code prenom} de l'instance */
    public String getPrenom() {
        return this.prenom;
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
        return nom.hashCode() + prenom.hashCode() + dateNaissance.hashCode() + ID;
    }
}
