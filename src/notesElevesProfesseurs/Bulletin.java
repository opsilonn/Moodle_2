/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notesElevesProfesseurs;

import java.text.DecimalFormat;

/**
 * Classe matérialisant un bulletin étudiant
 *
 * @author Célia
 */
class Bulletin {

    /**
     * Creation d'un bulletin de notes pour un {@link Eleve}
     *
     * @param eleve Eleve auquel appartient le bulletin
     * @param ecole Ecole auquelle appartient l'élève
     */
    public static void createBulletin(Eleve eleve, Ecole ecole) {
        StringBuilder ss = new StringBuilder();

        ss.append("Bulletin de l'étudiant " + eleve.getNom().toUpperCase() + " " + eleve.getPrenom().toUpperCase() + "\n");
        ss.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
        ss.append("|  Matière            |           Evaluations         | Moy Eleve | Moy Promo | Moy Min | Moy Max | Med Eleve | Med Promo | Med Min | Med Max |\n");
        ss.append("|---------------------|-------------------------------|-----------|-----------|---------|---------|-----------|-----------|---------|---------|\n");
        Promotion promo = ecole.getPromo(eleve.getPromotion());

        for (Matiere m : ecole.getMatiere()) {
            try {
                ss.append("| " + m);
                int length = m.toString().length();
                for (int i = 0; i < 19 - length; i++) {
                    ss.append(" ");
                }

                String evaluation = " |";
                for (Evaluation eval : eleve.getEvaluations(m)) {
                    evaluation += " " + eval.getNote();
                }
                int evalLength = evaluation.length();
                for (int i = 0; i < 32 - evalLength; i++) {
                    evaluation += " ";
                }
                
                ss.append(evaluation);
                ss.append(" |    " + format_output(eleve.getMoyenne(m)) + "   |   ");
                ss.append(format_output(promo.moyennePromotion(m)) + "    |   ");
                ss.append(format_output(promo.moyenneMaxMinPromotion(m, false)) + "  |   ");
                ss.append(format_output(promo.moyenneMaxMinPromotion(m, true)) + "  |    ");
                ss.append(format_output(eleve.getMediane(m)) + "   |   ");
                ss.append(format_output(promo.medianePromotion(m)) + "    |   ");
                ss.append(format_output(promo.medianeMaxMinPromotion(m, false)) + "  |   ");
                ss.append(format_output(promo.medianeMaxMinPromotion(m, true)) + "  |\n");
            } catch (IllegalStateException e) {
                ss.append(" | pas d'évaluation              |           |           |         |         |           |           |         |         |\n");
            }
        }

        ss.append("|-----------------------------------------------------|-----------|-----------|---------|---------|-----------|-----------|---------|---------|\n");

        try {
            ss.append("| Général:                                            |    " + format_output(eleve.getMoyenneGenerale()) + "   |   ");
            ss.append(format_output(promo.moyenneGeneralePromotion()) + "    |   ");
            ss.append(format_output(promo.moyenneMaxMinPromotion(false)) + "  |   ");
            ss.append(format_output(promo.moyenneMaxMinPromotion(true)) + "  |    ");
            ss.append(format_output(eleve.getMedianeGenerale()) + "   |   ");
            ss.append(format_output(promo.medianeGeneralePromotion()) + "    |   ");
            ss.append(format_output(promo.medianeMaxMinPromotion(false)) + "  |   ");
            ss.append(format_output(promo.medianeMaxMinPromotion(true)) + "  |\n");
        } catch (IllegalStateException e) {
            ss.append("|  Général:                                           |----NAN----|-----------|---------|---------|----NAN----|-----------|---------|---------|\n");
        }
        ss.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");

        System.out.println(ss.toString());
        Stats_Bulletin.main(eleve, ecole);
    }

    private static String format_output(double note) {
        DecimalFormat df = new DecimalFormat("#.#");
        String formattedString = df.format(note);
        int length = formattedString.length();

        for (int i = 0; i < 4 - length; i++) {
            formattedString += " ";
        }
        return formattedString;
    }

}
