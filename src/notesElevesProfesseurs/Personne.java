package notesElevesProfesseurs;

import java.text.SimpleDateFormat;  // Format des dates
import java.time.Year;
import java.util.Date;  // On préfère utiliser des classes déjà existantes en Java
import java.util.UUID;  // Pour la génération d'identifiants uniques


/**
 * Classe de base pour chaque entité de type {@link Personne}
 * Ne doit pas pouvoir être instanciée en tant que telle, et est donc abstraite.
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
     * @deprecated Éviter d'utiliser ce constructeur autant que possible, les paramètres par défaut sont fixes, pas aléatoires
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
     * @param nom Nom de la personne
     * @param prenom Prénom de la personne
     * @param dateNaissance Date de naissance de la personne
     */
    public Personne (String nom, String prenom, Date dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.ID = createID();   // ID généré par une bibliothèque Java native
    }

    public Personne (String nom, String prenom, Date dateNaissance, int ID) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.ID = ID;
    }

    /* ID aura la forme YYYY0000 avec YYYY l'année d'entrée dans l'école*/
    private int createID(){
        this.index_ID ++;
        return Year.now().getValue() * 10000 + this.index_ID;
    }





    /**
     * Créé une représentation textuelle de l'instance
     * @return Représentation textuelle de l'instance
     */
    public abstract String toString ();
}
