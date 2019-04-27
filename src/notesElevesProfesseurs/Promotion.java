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
     * @return la liste des étudiants
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
    public double moyenneGeneralePromotion() throws IllegalStateException {
        double sum = 0;
        int size = eleves.size();
        for (Eleve e : this.eleves) {
            try {
                sum += e.getMoyenneGenerale();
            } catch (IllegalStateException exception) {
                size--;
            }
        }
        if (size == 0) {
            throw new IllegalStateException();
        }

        return sum / size;
    }

    /**
     * Calcul de la moyenne pour la matière de la promotion
     *
     * @param matiere Matière pour le calcul de la moyenne
     * @return la moyenne de la promotion
     */
    public double moyennePromotion(Matiere matiere) throws IllegalStateException {
        double sum = 0;
        int size = eleves.size();
        for (Eleve e : this.eleves) {
            try {
                sum += e.getMoyenne(matiere);
            } catch (IllegalStateException exception) {
                size--;
            }
        }

        if (size == 0) {
            throw new IllegalStateException();
        }

        return sum / size;
    }

    /**
     * Calcul de la médiane de l'ensemble de la {@link Promotion}
     *
     * @return médiane générale de la promotion
     */
    public double medianeGeneralePromotion() throws IllegalStateException {
        ArrayList<Double> medianes = new ArrayList<>();
        for (Eleve e : this.eleves) {
            try {
                medianes.add(e.getMedianeGenerale());
            } catch (IllegalStateException ignored) {
            }
        }

        // Cas où il n'y a pas de médianes
        if (medianes.isEmpty()) {
            throw new IllegalStateException();
        }
        if (medianes.size() == 1) {
            return medianes.get(0);
        }

        // Tri des notes de la promotion par ordre ascendant
        Collections.sort(medianes);

        int middle = medianes.size() / 2;  // Détermination de la position de l'évaluation médiane
        if (middle % 2 == 1) {  // Si le nb de note est impair : retourne la note du milieu
            return medianes.get(middle);
        } else {  // Si le nb de note est pair : moyenne des deux notes du milieu
            return (medianes.get(middle) + medianes.get(middle - 1)) / 2;
        }
    }

    /**
     * Calcul de la médiane de la promotion pour une certaine {@link Matiere}
     *
     * @param matiere Matière pour le calcul de la médiane
     * @return médiane
     */
    public double medianePromotion(Matiere matiere) throws IllegalStateException {
        ArrayList<Double> medianes = new ArrayList<>();
        for (Eleve e : this.eleves) {
            try {
                medianes.add(e.getMediane(matiere));
            } catch (IllegalStateException ignored) {
            }
        }

        // Cas où il n'y a pas de médianes
        if (medianes.isEmpty()) {
            throw new IllegalStateException();
        }
        if (medianes.size() == 1) {
            return medianes.get(0);
        }

        // Tri des notes de la promotion par ordre ascendant
        Collections.sort(medianes);

        int middle = medianes.size() / 2;  // Détermination de la position de l'évaluation médiane
        if (middle % 2 == 1) {  // Si le nb de note est impair : retourne la note du milieu
            return medianes.get(middle);
        } else {  // Si le nb de note est pair : moyenne des deux notes du milieu
            return (medianes.get(middle) + medianes.get(middle - 1)) / 2;
        }
    }

    /**
     * Calcul de la Médiane Générale minimale ou maximale de la
     * {@link Promotion} sur la {@link Matiere} renseignée
     *
     * @param matiere matiere pour laquelle il faut trouver la médiane
     * @param mode mode d'utilisation de la fonction: true = trouve le max,
     * false = trouve le min.
     * @return la médiane max ou min de la promotion sur la matière demandée
     */
    public double medianeMaxMinPromotion(Matiere matiere, boolean mode) {
        double medianeToReturn = -1;
        try {
            medianeToReturn = eleves.get(0).getMediane(matiere);
        } catch (IllegalStateException ignored) {
        }

        for (Eleve e : this.eleves) {
            try {
                double mediane = e.getMediane(matiere);
                if (medianeToReturn == -1) {
                    medianeToReturn = mediane;
                } else if ((mediane > medianeToReturn && mode == true) || (mediane < medianeToReturn && mode == false)) {
                    medianeToReturn = mediane;
                }
            } catch (IllegalStateException ignored) {
            }
        }
        return medianeToReturn;
    }

    /**
     * Calcul de la Médiane Générale minimale ou maximale de la
     * {@link Promotion} sur la {@link Matiere} renseignée
     *
     * @param mode mode d'utilisation de la fonction: true = trouve le max,
     * false = trouve le min.
     * @return la médiane max ou min de la promotion sur la matière demandée
     */
    public double medianeMaxMinPromotion(boolean mode) {
        double medianeToReturn = -1;
        try {
            medianeToReturn = eleves.get(0).getMedianeGenerale();
        } catch (IllegalStateException ignored) {
        }

        for (Eleve e : this.eleves) {
            try {
                double mediane = e.getMedianeGenerale();
                if (medianeToReturn == -1) {
                    medianeToReturn = mediane;
                } else if ((mediane > medianeToReturn && mode == true) || (mediane < medianeToReturn && mode == false)) {
                    medianeToReturn = mediane;
                }
            } catch (IllegalStateException ignored) {
            }
        }
        return medianeToReturn;
    }

    /**
     * Calcul de la Moyenne minimale ou maximale de la {@link Promotion} sur la
     * {@link Matiere} renseignée
     *
     * @param matiere - {@link Matiere} dans laquelle nous cherchons la moyenne
     * min ou max
     * @param mode - mode d'utilisation de la fonction: true = trouve le max,
     * false = trouve le min.
     * @return la moyenne générale max ou min de la promotion dans la matière
     * renseignée
     */
    public double moyenneMaxMinPromotion(Matiere matiere, boolean mode) {
        double moyenneToReturn = -1;
        try {
            moyenneToReturn = eleves.get(0).getMoyenne(matiere);
        } catch (IllegalStateException ignored) {
        };

        for (Eleve e : this.eleves) {
            try {
                double moy = e.getMoyenne(matiere);
                if (moyenneToReturn == -1) {
                    moyenneToReturn = moy;
                } else if ((moy > moyenneToReturn && mode == true) || (moy < moyenneToReturn && mode == false)) {
                    moyenneToReturn = moy;
                }
            } catch (IllegalStateException ignored) {
            }
        }
        return moyenneToReturn;
    }

    /**
     * Calcul de la moyenne Générale minimale ou maximale de la promotion
     *
     * @param mode mode d'utilisation de la fonction: true = trouve le max,
     * false = trouve le min.
     * @return la moyenne générale max ou min de la promotion
     */
    public double moyenneMaxMinPromotion(boolean mode) {
        double moyenneToReturn = -1;
        try {
            moyenneToReturn = eleves.get(0).getMoyenneGenerale();
        } catch (IllegalStateException ignored) {
        };

        for (Eleve e : this.eleves) {
            try {
                double moy = e.getMoyenneGenerale();
                if (moyenneToReturn == -1) {
                    moyenneToReturn = moy;
                } else if ((moy > moyenneToReturn && mode == true) || (moy < moyenneToReturn && mode == false)) {
                    moyenneToReturn = moy;
                }
            } catch (IllegalStateException ignored) {
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
     * Tri de la promotion par la moyenne en ordre croissant ou décroissant en fonction du mode
     *
     * @param mode - si mode == true alors tri croissant si mode == false alors
     * tri décroissant
     */
    public void triMoyenne(boolean mode) {
        if (mode) {
            eleves.sort(new ComparatorEleve.ComparatorMoyenne());
        } else {
            eleves.sort(new ComparatorEleve.ComparatorMoyenne().reversed());
        }

    }

    /**
     * Tri de la promotion par la médiane en ordre croissant ou décroissant en
     * fonction du mode
     *
     * @param mode - si mode == true alors tri croissant si mode == false alors tri
     * décroissant
     */
    public void triMediane(boolean mode) {
        if (mode) {
            eleves.sort(new ComparatorEleve.ComparatorMediane());
        } else {
            eleves.sort(new ComparatorEleve.ComparatorMediane().reversed());
        }

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
