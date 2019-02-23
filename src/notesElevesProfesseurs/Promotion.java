package notesElevesProfesseurs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Une promotion est un ensemble d'{@link Eleve}s
 */
public class Promotion {

    private String nom;
    private List<Eleve> eleves;

    private static final String NOM_PAR_DEFAUT = "Promo Scions";

    /**
     * Constructeur par défaut
     */
    public Promotion(String nom) {
        this.nom = nom;
        this.eleves = new ArrayList<Eleve>();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public Eleve rechercher(int idEleve) {
        for (Eleve e : this.eleves) {
            if (e.getID() == idEleve) {
                return e;
            }
        }
        return null;
    }

    public List<Eleve> getEleves() {
        return eleves;
    }

    public void addEleve(Eleve eleveToadd) {
        eleveToadd.setPromo(this.nom);
        eleves.add(eleveToadd);
    }
}
