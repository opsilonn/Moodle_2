/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notesElevesProfesseurs;

import java.util.Comparator;

/**
 *
 * @author Célia
 */
public class ComparatorEleve {

    public static class ComparatorMoyenne implements Comparator<Eleve> {

        @Override
        public int compare(Eleve o1, Eleve o2) {
            double moy1, moy2;
            try {
                moy1 = o1.getMoyenneGenerale();
            } catch (IllegalStateException e) {
                return -1;
            }
            try {
                moy2 = o2.getMoyenneGenerale();
            } catch (IllegalStateException e) {
                return 1;
            }

            if (moy1 == moy2) {
                return 0;
            }

            if (moy1 < moy2) {
                return -1;
            }

            return 1;
        }
    }

    /**
     * Comparateur d'Eleve en fonction de la médiane
     */
    public static class ComparatorMediane implements Comparator<Eleve> {

        @Override
        public int compare(Eleve o1, Eleve o2) {
            double med1, med2;
            try {
                med1 = o1.getMedianeGenerale();
            } catch (IllegalStateException e) {
                return -1;
            }
            try {
                med2 = o2.getMedianeGenerale();
            } catch (IllegalStateException e) {
                return 1;
            }

            if (med1 == med2) {
                return 0;
            }

            if (med1 < med2) {
                return -1;
            }

            return 1;
        }
    }

}
