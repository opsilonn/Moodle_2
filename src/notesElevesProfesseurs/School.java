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
public class School {

    private Map<String, Promotion> promotions;
    private List<Professeur> professeurs;

    /**
     * Constructeur d'une école
     */
    public School() {
        promotions = new HashMap();
        professeurs = new ArrayList();
    }

    /**
     * Ajoute une {@link Promotion} à l'école
     * @param newPromo Nouvelle {@link Promotion} à ajouter
     */
    public void addPromo(Promotion newPromo) {
        if (!promotions.containsKey(newPromo.getNom())) {
            promotions.put(newPromo.getNom(), newPromo);
        }
    }

    /**
     * Retourne la {@link Promotion} recherchée
     * @param nomPromo Nom de la {@link Promotion} recherchée
     * @return la {@link Promotion} recherchée
     */
    public Promotion getPromo(String nomPromo) {
        return promotions.get(nomPromo);
    }

    /**
     * Retourne la totalité des promotions de l'école
     * @return toutes les {@link Promotion}
     */
    public Map<String, Promotion> getPromo() {
        return promotions;
    }

    /**
     * Ajoute un professeur à l'école
     * @param prof {@link Professeur} à ajouter à l'éoole
     */
    public void addProfesseur(Professeur prof) {
        professeurs.add(prof);
    }

    /**
     * Retourne l'intégralité des professeurs de l'école
     * @return La liste de {@link Professeur} de l'école
     */
    public List<Professeur> getProfesseur() {
        return professeurs;
    }

    /**
     * Cherche un professeur dans l'école
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

        if (!promotions.isEmpty())
        {
            promotions.keySet().forEach((String s) -> str.append(getPromo(s).toString()).append("\n"));
        }

        if (!professeurs.isEmpty())
        {
            str.append("Professeurs : " + "\n");
            professeurs.forEach((Professeur p) -> str.append("\t").append(p.toString()).append("\n"));
        }

        if (str.length() == 0) {
            return "L'école ne possède aucune promotion ni professeur.";
        }
        return str.toString();
    }
}
