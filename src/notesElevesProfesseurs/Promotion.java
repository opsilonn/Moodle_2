package notesElevesProfesseurs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Une promotion est un ensemble d'{@link Eleve}s
 */
public class Promotion extends ArrayList<Eleve> {

    private String nom;

    private static final String NOM_PAR_DEFAUT = "Promo Scions";

    /**
     * Constructeur par défaut
     */
    public Promotion(String nom) {
        this.nom = nom;
    }
    
    public void setNom(String nom){
        this.nom = nom;
    }
    
    public String getNom(){
        return this.nom;
    }
}
