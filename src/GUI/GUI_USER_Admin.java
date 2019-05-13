package GUI;


import GUIcomponents.CustomJFrame;
import Gestionfichier.FileHandler;
import notesElevesProfesseurs.Ecole;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Création de l'interface pour un Admin
 *
 * Cette classe hérite de {@link CustomJFrame}
 *
 * @author Hugues
 */
public class GUI_USER_Admin extends CustomJFrame
{
    private static final int DIM_X = 500;
    private static final int DIM_Y = 500;

    private JPanel panel;
    private JButton buttonProfesseur;
    private JButton buttonEleve;


    /**
     * Création de l'interface de l'Admin
     */
    public GUI_USER_Admin(Ecole ecole)
    {
        super("Admin", ecole, true, DIM_X, DIM_Y);


        buttonEleve.addActionListener(e -> { GUI_CreerPersonne frame = new GUI_CreerPersonne(ecole, "élève"); } );
        buttonProfesseur.addActionListener(e -> { GUI_CreerPersonne frame = new GUI_CreerPersonne(ecole, "professeur"); } );
        sauvegarderBDDApresFermeture();


        add(panel);
        pack();
        revalidate();
        setVisible(true);
    }
}
