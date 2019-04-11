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
    private final List<Eleve> eleves;

    private static final String NOM_PAR_DEFAUT = "Promo Scions";

    /**
     * Constructeur par défaut
     */
    public Promotion() {
        this.nom = NOM_PAR_DEFAUT;
        this.eleves = new ArrayList<>();
    }

    /**
     * Initialization d'une promotion
     *
     * @param nom Nom de la promotion
     */
    public Promotion(String nom) {
        this.nom = nom;
        this.eleves = new ArrayList<>();
    }

    /**
     * Modification du nom de la promotion
     *
     * @param nom Nom de la promotion
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le nom de la promotion
     *
     * @return nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Rechercher un l'élève dans la liste eleves
     *
     * @param idEleve Numero ID de l'étudiant
     * @return l'étudiant
     */
    public Eleve rechercher(int idEleve) {
        for (Eleve e : this.eleves) {
            if (e.getID() == idEleve) {
                return e;
            }
        }
        return null;
    }

    /**
     * Retourne la liste d'étudiant présent dans la promotion
     *
     * @return
     */
    public List<Eleve> getEleves() {
        return eleves;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Promotion " + this.nom + ":\n");
        eleves.forEach((Eleve e) -> {
            str.append("\t" + e.toStringforEval()).append("\n");
        });
        return str.toString();
    }

    /**
     * Calcul de la moyenne générale de la promotion
     *
     * @return la moyenne
     */
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

    /**
     * Calcul de la moyenne pour la matière de la promotion
     *
     * @param matiere Matière pour le calcul de la moyenne
     * @return
     */
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

    /**
     * Calcul de la médiane de l'ensemble de la promotion
     *
     * @return médiane
     */
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

    /**
     * Calcul de la médiane de la promotion pour une certaine {@link Matière}
     *
     * @param matiere Matière pour le calcul de la médiane
     * @return médiane
     */
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

    /**
     *
     * @param matiere matiere pour laquelle il faut trouver la médiane
     * @param mode mode d'utilisation de la fonction: true -> trouve le max,
     * false -> trouve le min.
     * @return
     */
    public double medianeMaxMinPromotion(Matiere matiere, boolean mode) {
        double medianeToReturn = -1;
        try {
            medianeToReturn = eleves.get(0).getMediane(matiere);
        } catch (IllegalStateException exception) {
        }

        for (Eleve e : this.eleves) {
            try {
                double mediane = e.getMediane(matiere);
                if (medianeToReturn == -1) {
                    medianeToReturn = mediane;
                } else if ((mediane > medianeToReturn && mode == true) || (mediane < medianeToReturn && mode == false)) {
                    medianeToReturn = mediane;
                }
            } catch (IllegalStateException exception) {
            }
        }
        return medianeToReturn;
    }

    public double medianeMaxMinPromotion(boolean mode) {
        double medianeToReturn = -1;
        try {
            medianeToReturn = eleves.get(0).getMedianeGenerale();
        } catch (IllegalStateException exception) {
        }

        for (Eleve e : this.eleves) {
            try {
                double mediane = e.getMedianeGenerale();
                if (medianeToReturn == -1) {
                    medianeToReturn = mediane;
                } else if ((mediane > medianeToReturn && mode == true) || (mediane < medianeToReturn && mode == false)) {
                    medianeToReturn = mediane;
                }
            } catch (IllegalStateException exception) {
            }
        }
        return medianeToReturn;
    }

    public double moyenneMaxMinPromotion(Matiere matiere, boolean mode) {
        double moyenneToReturn = -1;
        try {
            moyenneToReturn = eleves.get(0).getMoyenne(matiere);
        } catch (IllegalStateException exception) {
        };

        for (Eleve e : this.eleves) {
            try {
                double moy = e.getMoyenne(matiere);
                if (moyenneToReturn == -1) {
                    moyenneToReturn = moy;
                } else if ((moy > moyenneToReturn && mode == true) || (moy < moyenneToReturn && mode == false)) {
                    moyenneToReturn = moy;
                }
            } catch (IllegalStateException exception) {
            }
        }
        return moyenneToReturn;
    }

    public double moyenneMaxMinPromotion(boolean mode) {
        double moyenneToReturn = -1;
        try {
            moyenneToReturn = eleves.get(0).getMoyenneGenerale();
        } catch (IllegalStateException exception) {
        };

        for (Eleve e : this.eleves) {
            try {
                double moy = e.getMoyenneGenerale();
                if (moyenneToReturn == -1) {
                    moyenneToReturn = moy;
                } else if ((moy > moyenneToReturn && mode == true) || (moy < moyenneToReturn && mode == false)) {
                    moyenneToReturn = moy;
                }
            } catch (IllegalStateException exception) {
            }
        }
        return moyenneToReturn;
    }

    /**
     * Ajout d'un étudiant dans la promotion
     *
     * @param eleveToadd Elève à ajouter à la promotion
     */
    public void addEleve(Eleve eleveToadd) {
        eleveToadd.setPromo(this.nom);
        eleves.add(eleveToadd);
    }

    /**
     * Tri de la promotion par la moyenne en ordre croissant
     */
    public void triMoyenneCroissant() {
        ComparatorEleve c = new ComparatorEleve();
        Collections.sort(eleves, new ComparatorEleve.ComparatorMoyenne());
    }

    /**
     * Tri de la promotion par la moyenne en ordre décroissant
     */
    public void triMoyenneDecroissant() {
        Collections.sort(eleves, new ComparatorEleve.ComparatorMoyenne().reversed());
    }

    /**
     * Tri de la promotion par la médiane en ordre croissant
     */
    public void triMedianeCroissant() {
        ComparatorEleve c = new ComparatorEleve();
        Collections.sort(eleves, new ComparatorEleve.ComparatorMediane());
    }

    /**
     * Tri de la promotion par la médiane en ordre décroissant
     */
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
