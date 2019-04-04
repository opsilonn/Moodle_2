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

    Map<String, Promotion> promotions;
    List<Professeur> professeurs;

    public School() {
        promotions = new HashMap();
        professeurs = new ArrayList();
    }

    public void addPromo(Promotion newPromo) {
        if (!promotions.containsKey(newPromo.getNom())) {
            promotions.put(newPromo.getNom(), newPromo);
        }
    }

    public Promotion getPromo(String nomPromo) {
        return promotions.get(nomPromo);
    }
    
    public Map<String, Promotion> getPromo(){
        return promotions;
    }

    public void addProfesseur(Professeur prof) {
        professeurs.add(prof);
    }
    
    public List<Professeur> getProfesseur(){
        return professeurs;
    }

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
            promotions.keySet().forEach((String s) -> {
                str.append(getPromo(s).toString() + "\n");
            });
        }

        if (!professeurs.isEmpty()) {
            str.append("Professeurs : " + "\n");
            professeurs.forEach((Professeur p) -> {
                str.append("\t" + p.toString() + "\n");
            });
        }

        if (str.length() == 0) {
            return "L'école ne possède aucune promotion ni professeur.";
        }

        return str.toString();
    }

}
