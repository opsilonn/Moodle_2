/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notesElevesProfesseurs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Célia
 */
public class Ecole {

    private final Map<String, Promotion> promotions;
    private final List<Professeur> professeurs;

    /**
     * Constructeur d'une école
     */
    public Ecole() {
        promotions = new HashMap();
        professeurs = new ArrayList();
    }

    /**
     * Ajoute une {@link Promotion} à l'école
     *
     * @param newPromo Nouvelle {@link Promotion} à ajouter
     */
    public void addPromo(Promotion newPromo) {
        if (!promotions.containsKey(newPromo.getNom())) {
            promotions.put(newPromo.getNom(), newPromo);
        }
    }

    /**
     * Retourne la {@link Promotion} recherchée
     *
     * @param nomPromo Nom de la {@link Promotion} recherchée
     * @return la {@link Promotion} recherchée
     */
    public Promotion getPromo(String nomPromo) {
        return promotions.get(nomPromo);
    }

    /**
     * Retourne la totalité des promotions de l'école
     *
     * @return toutes les {@link Promotion}
     */
    public Map<String, Promotion> getPromo() {
        return promotions;
    }

    /**
     * Ajoute un professeur à l'école
     *
     * @param prof {@link Professeur} à ajouter à l'éoole
     */
    public void addProfesseur(Professeur prof) {
        professeurs.add(prof);
    }

    /**
     * Retourne l'intégralité des professeurs de l'école
     *
     * @return La liste de {@link Professeur} de l'école
     */
    public List<Professeur> getProfesseur() {
        return professeurs;
    }

    /**
     * Retourne le {@link Professeur} correspondant à l'ID entré
     *
     * @param ID ID du {@link Professeur} recherché
     * @return Le {@link Professeur} correspondant
     */
    public Professeur getProfesseur(int ID) {
        for (Professeur prof : professeurs) {
            if (prof.getID() == ID) {
                return prof;
            }
        }
        return null;
    }

    /**
     * Cherche un professeur dans l'école
     *
     * @param nom Nom du professeur
     * @param prenom Prenom du professeur
     * @param matiereCode Code de la matière enseignée
     * @return le professeur
     */
    public Professeur findProfesseur(String nom, String prenom, String matiereCode) {
        for (Professeur p : professeurs) {
            if (p.getNom().equals(nom) && p.getPrenom().equals(prenom) && p.getMatiere().getCode().equals(matiereCode)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        if (!promotions.isEmpty()) {
            promotions.keySet().forEach((String s) -> str.append(getPromo(s).toString()).append("\n"));
        }

        if (!professeurs.isEmpty()) {
            str.append("Professeurs : " + "\n");
            professeurs.forEach((Professeur p) -> str.append("\t").append(p.toString()).append("\n"));
        }

        if (str.length() == 0) {
            return "L'école ne possède aucune promotion ni professeur.";
        }
        return str.toString();
    }

    /**
     * Retourne la liste des matières enseignées à l'{@link Ecole}
     * @return la liste de {@link Matiere} présente à l'école
     */
    public List<Matiere> getMatiere() {
        List<Matiere> matieres = new ArrayList<>();

        for (Professeur p : professeurs) {
            if (!matieres.contains(p.getMatiere())) {
                matieres.add(p.getMatiere());
            }
        }
        return matieres;
    }
}
