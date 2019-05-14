package GUI;

import GUIcomponents.CustomJFrame;
import notesElevesProfesseurs.Ecole;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Evaluation;
import notesElevesProfesseurs.Matiere;
import notesElevesProfesseurs.Stats_Bulletin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Fenêtre dédiée à l'utilisation du logiciel par un {@link Eleve}
 *
 * Cette classe hérite de {@link CustomJFrame}
 *
 * @author Hugues
 */
class GUI_USER_Eleve extends CustomJFrame
{
    private static final int DIM_X = 800;
    private static final int DIM_Y = 500;

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
    private JButton buttonStats;

    private DecimalFormat df = new DecimalFormat("#.##");

    /**
     * Création de l'interface pour un {@link Eleve}
     *
     * @param eleve - {@link Eleve} connecté
     * @param ecole - {@link Ecole} où étudie l'{@link Eleve}
     */
    public GUI_USER_Eleve(Eleve eleve, Ecole ecole)
    {
        super("Eleve - " + eleve.getPrenom() + " " + eleve.getNom(), ecole, true, DIM_X, DIM_Y);

        // On rentre tous les labels
        setInformations(eleve);


        // On donne aux JButtons leurs listeners
        buttonCorrecteur.addActionListener(e -> { new GUI_correcteurs(eleve, ecole); });
        buttonPromotion.addActionListener(e -> { new GUI_chercherPromotion(ecole); });
        buttonStats.addActionListener(e -> { Stats_Bulletin.main(eleve, ecole); });



        // On affiche en conséquence tous les champs de l'élève
        List<Evaluation> evaluations = eleve.getEvaluation();
        if (evaluations.size() == 0)
        {
            bulletin.setVisible(false);
            bulletinValeurs.setVisible(false);
            bulletinPromo.setVisible(false);
            bulletinPromoValeurs.setVisible(false);
        }
        else
        {
            labelErreur.setVisible(false);

            setBulletin(eleve, evaluations);
            setBulletinComparatif(eleve, evaluations);
        }


        add(panel);
        pack();
        revalidate();
        setVisible(true);
    }


    /**
     * Remplit les champs de l'identité de l'{@link Eleve} connecté
     */
    private void setInformations(Eleve eleve)
    {
        labelNom.setText(eleve.getPrenom() + " " + eleve.getNom().toUpperCase());
        labelID.setText(String.valueOf(eleve.getID()));
        labelPromotion.setText(eleve.getPromotion());
        try
        {
            labelMoyenne.setText(df.format(eleve.getMoyenneGenerale()));
        }
        catch (IllegalStateException e)
        {
            labelMoyenne.setText("Non définie");
        }
    }


    /**
     * Remplit les champs des notes de l'{@link Eleve} connecté
     */
    private void setBulletin(Eleve eleve, List<Evaluation> evaluations)
    {
        // table INFORMATION
        String[] columns = new String[]{"Matière", "ID - Matière", "Note", "ID-Note", "Correcteur"};
        Object[][] data = new Object[evaluations.size()][columns.length];
        int index = 0;

        for (Evaluation eval : evaluations)
        {
            data[index][0] = eval.getMatiere().getNom();
            data[index][1] = eval.getCodeMatiere();
            data[index][2] = String.valueOf(eval.getNote());
            data[index][3] = eval.getID();
            data[index][4] = eval.getCorrecteur().toString();
            index++;
        }

        bulletinValeurs.setModel(new DefaultTableModel(data, columns));
        centrerJTable(bulletinValeurs);
    }


    /**
     * Remplit les champs comparatifs des notes de l'{@link Eleve} connecté
     */
    private void setBulletinComparatif(Eleve eleve, List<Evaluation> evaluations)
    {
        // table COMPARAISON
        String[] columns = new String[]{"Matière", "ID - Matière", "Votre moyenne", "Minimum", "Moyenne Promotion", "Maximum"};
        Object[][] data = new Object[eleve.getMatiere().size()][columns.length];
        int index = 0;

        for (Matiere matiere : eleve.getMatiere())
        {
            data[index][0] = matiere.getNom();
            data[index][1] = matiere.getCode();
            data[index][2] = formatNumber(eleve.getMoyenne(matiere));
            data[index][3] = formatNumber(ecole.getPromo(eleve.getPromotion()).moyenneMaxMinPromotion(matiere, false));
            data[index][4] = formatNumber(ecole.getPromo(eleve.getPromotion()).moyennePromotion(matiere));
            data[index][5] = formatNumber(ecole.getPromo(eleve.getPromotion()).moyenneMaxMinPromotion(matiere, true));
            index++;
        }

        bulletinPromoValeurs.setModel(new DefaultTableModel(data, columns));
        centrerJTable(bulletinPromoValeurs);
    }

    private String formatNumber(double number){
        return df.format(number);
    }
}
