package notesElevesProfesseurs;

/**
 * Classe représentant une matière.
 *
 * Chaque matière possède un code (qui agit comme identifiant), et un nom.
 * @author Célia
 */
public class Matiere {
    
    private String code;  // Identifiant de la matière
    private String nom;  // Nom de la matière

    private static final String CODE_PAR_DEFAUT = "123AB";
    private static final String NOM_PAR_DEFAUT = "Alphabet";


    /**
     * Créé une matière avec les noms et codes par défaut
     */
    @Deprecated
    public Matiere() {
        this.code = CODE_PAR_DEFAUT;
        this.nom = NOM_PAR_DEFAUT;
    }


    /**
     * Créé une matière avec un code et un nom
     * @param code Code à donner à la nouvelle {@link Matiere}
     * @param nom Nom de la nouvelle {@link Matiere}
     */
    public Matiere (String code, String nom){
        this.code = code;
        this.nom = nom;
    }


    /**
     * Créé une représentation textuelle de l'instance
     * @return Représentation textuelle de l'instance
     */
    @Override
    public String toString(){
        return code + " " + nom;
    }
    
}
