package notesElevesProfesseurs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Chaque promotion possède un ensemble d'{@link Eleve}s
 * 
 * Chaque promotion possède un nom
 */
public class Promotion {

    private String nom;
    private List<Eleve> eleves;

    private static final String NOM_PAR_DEFAUT = "Promo Scions";

    /**
     * Constructeur par défaut
     */
    public Promotion() {
        this.nom = NOM_PAR_DEFAUT;
        this.eleves = new ArrayList<>();
    }

    public Promotion(String nom) {
        this.nom = nom;
        this.eleves = new ArrayList<>();
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

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Eleve e : eleves) {
            str.append(e.toStringforEval() + "\n");
        }
        return str.toString();
    }

    public double moyenneGeneralePromotion() {
        double sum = 0;
        int size = eleves.size();
        for (Eleve e : this.eleves) {
            try {
                sum += e.getMoyenneGenerale();
            } catch (IllegalStateException exception) {
                size--;
            }
        }
        return sum / size;
    }

    public double moyennePromotion(Matiere matiere) {
        double sum = 0;
        int size = eleves.size();
        for (Eleve e : this.eleves) {
            try {
                sum += e.getMoyenne(matiere);
            } catch (IllegalStateException exception) {
                size--;
            }
        }
        return sum / size;
    }

    public double medianeGeneralePromotion() {
        double sum = 0;
        int size = eleves.size();
        for (Eleve e : this.eleves) {
            try {
                sum += e.getMedianeGenerale();
            } catch (IllegalStateException exception) {
                size--;
            }
        }
        return sum / size;
    }
    
    public double medianePromotion(Matiere matiere) {
        double sum = 0;
        int size = eleves.size();
        for (Eleve e : this.eleves) {
            try {
                sum += e.getMediane(matiere);
            } catch (IllegalStateException exception) {
                size--;
            }
        }
        return sum / size;
    }

    public void addEleve(Eleve eleveToadd) {
        eleveToadd.setPromo(this.nom);
        eleves.add(eleveToadd);
    }

    public void triMoyenneCroissant() {
        ComparatorEleve c = new ComparatorEleve();
        Collections.sort(eleves, new ComparatorEleve.ComparatorMoyenne());
    }

    public void triMoyenneDecroissant() {
        Collections.sort(eleves, new ComparatorEleve.ComparatorMoyenne().reversed());
    }

    public void triMedianeCroissant() {
        ComparatorEleve c = new ComparatorEleve();
        Collections.sort(eleves, new ComparatorEleve.ComparatorMediane());
    }

    public void triMedianeDecroissant() {
        Collections.sort(eleves, new ComparatorEleve.ComparatorMediane().reversed());
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Promotion)) {
            return false;
        }
        Promotion p = (Promotion) o;
        return p.nom.equals(nom);
    }

    @Override
    public int hashCode() {
        return nom.hashCode();
    }
}
