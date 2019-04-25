/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notesElevesProfesseurs;

import java.util.ArrayList;

/**
 *
 * @author Célia
 */
public class Bulletin {

    /**
     * Creation d'un bulletin de notes pour un {@link Eleve}
     * @param eleve Eleve auquel appartient le bulletin
     * @param ecole Ecole auquelle appartient l'élève
     */
    public static void createBulletin(Eleve eleve, School ecole) {
        ArrayList<Matiere> matieres = new ArrayList<>();
        for (Professeur prof : ecole.getProfesseur())
        {
            matieres.add(prof.getMatiere());
        }

        System.out.println(eleve.toStringforEval());

        Promotion promo = ecole.getPromo(eleve.getPromotion());

        for (Matiere m : matieres)
        {
            try
            {
                System.out.println("\nMatière: " + m);
                System.out.print("Evaluations dans la matière: ");

                for (Evaluation eval : eleve.getEvaluations(m))
                {
                    System.out.println(eval.getNote() + " ");
                }

                System.out.println("Moyenne: " + eleve.getMediane(m));
                System.out.println("Moyenne Promo: " + promo.moyennePromotion(m));
                System.out.println("Moyenne Promo Mini: " + promo.moyenneMaxMinPromotion(m, false));
                System.out.println("Moyenne Promo Maxi: " + promo.moyenneMaxMinPromotion(m, true));
                
                
                System.out.println("\nMédiane: " + eleve.getMediane(m));
                System.out.println("Médiane Promo: " + promo.medianePromotion(m));
                System.out.println("Médiane Promo Mini: " + promo.medianeMaxMinPromotion(m, false));
                System.out.println("Médiane Promo Maxi: " + promo.medianeMaxMinPromotion(m, true));
                
            } catch (IllegalStateException e)
            {
                System.out.println("pas d'évaluation pour cette matière.");
            }
        }

        System.out.println("\nMoyenne Générale: " + eleve.getMoyenneGenerale());
        System.out.println("Médiane Générale: " + eleve.getMedianeGenerale());
        System.out.println("Moyenne Générale Promo: " + promo.moyenneGeneralePromotion());
        System.out.println("Médiane Générale Promo: " + promo.medianeGeneralePromotion());
        
        
        System.out.println("Moyenne Générale Promo MINI: " + promo.moyenneMaxMinPromotion(false));
        System.out.println("Moyenne Générale Promo MAXI: " + promo.moyenneMaxMinPromotion(true));
        System.out.println("Médiane Générale Promo MINI: " + promo.medianeMaxMinPromotion(false));
        System.out.println("Médiane Générale Promo MAXI: " + promo.medianeMaxMinPromotion(true));
    }
}
