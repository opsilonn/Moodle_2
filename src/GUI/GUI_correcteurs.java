package GUI;


import GUIcomponents.CustomJFrame;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Evaluation;
import notesElevesProfesseurs.Professeur;
import notesElevesProfesseurs.School;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Set;


public class GUI_correcteurs extends CustomJFrame
{
    private static final int dimX = 500;
    private static final int dimY = 500;

    private JPanel panel;

    private JLabel labelErreur;

    private JScrollPane correcteursScrollPane;
    private JTable correcteursTable;

    public GUI_correcteurs(Eleve eleve, School ecole)
    {
        super("Liste Correcteurs", ecole, true, dimX, dimY);

        Set<Professeur> correcteur = eleve.getCorrecteurs();

        if(correcteur.isEmpty())
        {
            correcteursScrollPane.setVisible(false);
            correcteursTable.setVisible(false);
        }
        else
        {
            labelErreur.setVisible(false);

            // table INFORMATION
            String[] columns = new String[] {"Matière", "ID - Matière", "Correcteur"};
            Object [][] data = new Object [correcteur.size()][columns.length];

            int index = 0;
            for (Professeur prof : correcteur)
            {
                data[index][0] = prof.getMatiere().getNom();
                data[index][1] = prof.getMatiere().getCode();
                data[index][2] = prof.getPrenom() + " " + prof.getNom().toUpperCase();
                index++;
            }

            DefaultTableModel model = new DefaultTableModel(data, columns);
            correcteursTable.setModel(model);
        }

        add(panel);
        pack();
        revalidate();
        setVisible(true);
    }
}
