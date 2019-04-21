/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notesElevesProfesseurs;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Classe matérialisant un bulletin étudiant
 *
 * @author Célia
 */
public class Bulletin {

    /**
     * Creation d'un bulletin de notes pour un {@link Eleve}
     *
     * @param eleve Eleve auquel appartient le bulletin
     * @param ecole Ecole auquelle appartient l'élève
     */
    public static void createBulletin(Eleve eleve, School ecole) {
        StringBuilder ss = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.##");

        ArrayList<Matiere> matieres = new ArrayList<>();
        for (Professeur prof : ecole.getProfesseur()) {
            matieres.add(prof.getMatiere());
        }

        ss.append("Bulletin de l'étudiant " + eleve.getNom().toUpperCase() + " " + eleve.getPrenom().toUpperCase() + "\n");
        ss.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
        ss.append("|  Matière            |           Evaluations         | Moy Eleve | Moy Promo | Moy Min | Moy Max | Med Eleve | Med Promo | Med Min | Med Max |\n");
        ss.append("|---------------------|-------------------------------|-----------|-----------|---------|---------|-----------|-----------|---------|---------|\n");
        Promotion promo = ecole.getPromo(eleve.getPromotion());

        for (Matiere m : matieres) {
            try {
                ss.append("| " + m);

                int length = m.toString().length();
                if (length < 19) {
                    for (int i = 0; i < 19 - length; i++) {
                        ss.append(" ");
                    }
                }

                ss.append(" |");

                String evaluation = "";
                for (Evaluation eval : eleve.getEvaluations(m)) {
                    evaluation += " " + eval.getNote();
                }
                if (evaluation.length() < 55 && evaluation.length() > 0) {
                    for (int i = 0; i < 55 - evaluation.length(); i++) {
                        evaluation += " ";
                    }
                }
                ss.append(evaluation);
                ss.append(" |    " + eleve.getMoyenne(m) + "   |   ");
                ss.append(df.format(promo.moyennePromotion(m)) + "   |    ");
                ss.append(df.format(promo.moyenneMaxMinPromotion(m, false)) + "    |    ");
                ss.append(df.format(promo.moyenneMaxMinPromotion(m, true)) + "   |");

                ss.append("    " + eleve.getMediane(m) + "   |   ");
                ss.append(df.format(promo.medianePromotion(m)) + "   |    ");
                ss.append(df.format(promo.medianeMaxMinPromotion(m, false)) + "    |    ");
                ss.append(df.format(promo.medianeMaxMinPromotion(m, true)) + "   |\n");
            } catch (IllegalStateException e) {
                ss.append(" pas d'évaluation              |           |           |         |         |           |           |         |         |\n");
            }
        }

        ss.append("|-----------------------------------------------------|-----------|-----------|---------|---------|-----------|-----------|---------|---------|\n");

        try {
            ss.append("| Général:                                            |    " + eleve.getMoyenneGenerale());
            ss.append("   |   " + df.format(promo.moyenneGeneralePromotion()));
            ss.append("   |    " + df.format(promo.moyenneMaxMinPromotion(false)) + "    |    ");
            ss.append(df.format(promo.moyenneMaxMinPromotion(true)) + "   |    ");
            ss.append(eleve.getMedianeGenerale());
            ss.append("   |   " + df.format(promo.medianeGeneralePromotion()));
            ss.append("   |    " + df.format(promo.medianeMaxMinPromotion(false)) + "    |    ");
            ss.append(df.format(promo.medianeMaxMinPromotion(true)) + "   |\n");
        } catch (IllegalStateException e) {
            ss.append("|  Général:                                         |----NAN----|-----------|---------|---------|----NAN----|-----------|---------|---------|\n");
        }
        ss.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");

        System.out.println(ss.toString());
        Stats_Bulletin.main(eleve,ecole);
    }

}
