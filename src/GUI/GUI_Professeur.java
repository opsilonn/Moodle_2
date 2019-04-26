package GUI;


import GUIcomponents.CustomJFrame;
import Gestionfichier.FileHandler;
import notesElevesProfesseurs.Professeur;
import notesElevesProfesseurs.School;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Fenêtre dédiée à l'utilisation du logiciel par un {@link Professeur}
 *
 * Cette classe hérite de {@link CustomJFrame}
 *
 * @author Hugues
 */
public class GUI_Professeur extends CustomJFrame
{
    private static final int dimX = 500;
    private static final int dimY = 500;

    private Professeur prof;

    private JPanel panel;
    private JButton buttonChercherEleve;
    private JLabel labelNom;
    private JLabel labelID;
    private JLabel labelMatiere;
    private JButton buttonModifier;


    public GUI_Professeur(Professeur prof, School ecole)
    {
        super("Professeur - " + prof.getPrenom() + " " + prof.getNom(), ecole, true, dimX, dimY);
        this.prof = prof;

        // On rentre tous les labels
        labelNom.setText( prof.getPrenom() + " " + prof.getNom().toUpperCase() );
        labelID.setText( String.valueOf( prof.getID() ) );
        labelMatiere.setText( prof.getMatiere().toString() );


        buttonChercherEleve.addActionListener(e -> { GUI_chercherEleve promo = new GUI_chercherEleve(ecole, prof.getMatiere()); });
        buttonModifier.addActionListener(e -> { GUI_modifierNote promo = new GUI_modifierNote(prof, ecole); });


        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                FileHandler scanner = new FileHandler();
                scanner.WriteFiles(ecole);
            }
        });

        add(panel);
        pack();
        revalidate();
        setVisible(true);
    }
}