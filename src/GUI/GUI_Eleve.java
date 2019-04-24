package GUI;


import GUIcomponents.CustomJFrame;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Evaluation;
import notesElevesProfesseurs.School;
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
public class GUI_Eleve extends CustomJFrame
{
    private static final int dimX = 500;
    private static final int dimY = 500;


    private Eleve eleve;


    private JPanel panel;

    private JLabel labelNom;
    private JLabel labelID;
    private JLabel labelPromotion;
    private JLabel labelMoyenne;

    private JButton buttonCorrecteur;

    private JScrollPane bulletin;
    private JTable bulletinValeurs;
    private JLabel labelNote;
    private JButton buttonPromotion;


    public GUI_Eleve(Eleve eleve, School ecole)
    {
        super("Eleve - " + eleve.getPrenom() + " " + eleve.getNom(), ecole, true, dimX, dimY);
        this.eleve = eleve;


        // On rentre tous les labels
        labelNom.setText( eleve.getPrenom() + " " + eleve.getNom().toUpperCase() );
        labelID.setText( String.valueOf( eleve.getID() ) );
        labelPromotion.setText( eleve.getPromotion() );
        try
        {
            labelMoyenne.setText( String.valueOf( eleve.getMoyenneGenerale() ) );
        }
        catch(IllegalStateException e)
        {
            labelMoyenne.setText(  "Non définie" );
        }


        // On donne aux JButtons leurs listeners
        buttonCorrecteur.addActionListener(e -> System.out.println("YO !!"));
        buttonPromotion.addActionListener(e -> { GUI_chercherPromotion promo = new GUI_chercherPromotion(ecole); });


        // On affiche toutes les notes de l'élève
        List<Evaluation> evaluations = eleve.getEvaluation();
        if(evaluations.size() == 0)
        {
            bulletin.setVisible(false);
            bulletinValeurs.setVisible(false);
        }
        else
        {
            labelNote.setVisible(false);

            //headers for the table
            String[] columns = new String[] {"Matière", "ID", "Note", "Correcteur"};
            Object [][] data = new Object [evaluations.size()][columns.length];

            int index = 0;
            for (Evaluation eval : evaluations)
            {
                data[index][0] = eval.getMatiere();
                data[index][1] = eval.getCodeMatiere();
                data[index][2] = String.valueOf( eval.getNote() );
                data[index][3] = eval.getCorrecteur().toString();
                index++;
            }


            //create table model with data
            DefaultTableModel model = new DefaultTableModel(data, columns);
            bulletinValeurs.setModel(model);
        }

        add(panel);
        pack();
        revalidate();
        setVisible(true);
    }
}
