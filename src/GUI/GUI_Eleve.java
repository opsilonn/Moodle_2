package GUI;

import GUIcomponents.CustomJFrame;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Evaluation;
import notesElevesProfesseurs.Matiere;
import notesElevesProfesseurs.Ecole;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Fenêtre dédiée à l'utilisation du logiciel par un {@link Eleve}
 *
 * Cette classe hérite de {@link CustomJFrame}
 *
 * @author Hugues
 */
public class GUI_Eleve extends CustomJFrame {

    private static final int DIMX = 500;
    private static final int DIMY = 500;

    private Eleve eleve;

    private JPanel panel;

    private JLabel labelNom;
    private JLabel labelID;
    private JLabel labelPromotion;
    private JLabel labelMoyenne;

    private JButton buttonCorrecteur;
    private JButton buttonPromotion;

    private JLabel labelErreur;

    private JScrollPane bulletin;
    private JTable bulletinValeurs;
    private JScrollPane bulletinPromo;
    private JTable bulletinPromoValeurs;

    /**
     * Création de l'interface pour un {@link Eleve}
     *
     * @param eleve - {@link Eleve} connecté
     * @param ecole - {@link Ecole} où étudie l'{@link Eleve}
     */
    public GUI_Eleve(Eleve eleve, Ecole ecole) {
        super("Eleve - " + eleve.getPrenom() + " " + eleve.getNom(), ecole, true, DIMX, DIMY);
        this.eleve = eleve;

        // On rentre tous les labels
        labelNom.setText(eleve.getPrenom() + " " + eleve.getNom().toUpperCase());
        labelID.setText(String.valueOf(eleve.getID()));
        labelPromotion.setText(eleve.getPromotion());
        try {
            labelMoyenne.setText(String.valueOf(eleve.getMoyenneGenerale()));
        } catch (IllegalStateException e) {
            labelMoyenne.setText("Non définie");
        }

        // On donne aux JButtons leurs listeners
        buttonCorrecteur.addActionListener(e -> {
            GUI_correcteurs correcteur = new GUI_correcteurs(eleve, ecole);
        });
        buttonPromotion.addActionListener(e -> {
            GUI_chercherPromotion promo = new GUI_chercherPromotion(ecole);
        });

        // On affiche toutes les notes de l'élève
        List<Evaluation> evaluations = eleve.getEvaluation();
        if (evaluations.size() == 0) {
            bulletin.setVisible(false);
            bulletinValeurs.setVisible(false);
            bulletinPromo.setVisible(false);
            bulletinPromoValeurs.setVisible(false);
        } else {
            labelErreur.setVisible(false);

            // table INFORMATION
            String[] columns = new String[]{"Matière", "ID - Matière", "Note", "ID-Note", "Correcteur"};
            Object[][] data = new Object[evaluations.size()][columns.length];

            int index = 0;
            for (Evaluation eval : evaluations) {
                data[index][0] = eval.getMatiere().getNom();
                data[index][1] = eval.getCodeMatiere();
                data[index][2] = String.valueOf(eval.getNote());
                data[index][3] = eval.getID();
                data[index][4] = eval.getCorrecteur().toString();
                index++;
            }

            DefaultTableModel model = new DefaultTableModel(data, columns);
            bulletinValeurs.setModel(model);

            System.out.println(eleve.getMatiere());

            // table COMPARAISON
            columns = new String[]{"Matière", "ID - Matière", "Votre moyenne", "Minimum", "Moyenne Promotion", "Maximum"};
            data = new Object[eleve.getMatiere().size()][columns.length];

            index = 0;
            for (Matiere matiere : eleve.getMatiere()) {
                data[index][0] = matiere.getNom();
                data[index][1] = matiere.getCode();
                data[index][2] = eleve.getMoyenne(matiere);
                data[index][3] = ecole.getPromo(eleve.getPromotion()).moyenneMaxMinPromotion(matiere, false);
                data[index][4] = ecole.getPromo(eleve.getPromotion()).moyennePromotion(matiere);
                data[index][5] = ecole.getPromo(eleve.getPromotion()).moyenneMaxMinPromotion(matiere, true);
                index++;
            }

            model = new DefaultTableModel(data, columns);
            bulletinPromoValeurs.setModel(model);
        }

        add(panel);
        pack();
        revalidate();
        setVisible(true);
    }
}
