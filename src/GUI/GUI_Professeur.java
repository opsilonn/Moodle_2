package GUI;


import GUIcomponents.CustomJFrame;
import Gestionfichier.FileHandler;
import notesElevesProfesseurs.Ecole;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Professeur;
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
class GUI_Professeur extends CustomJFrame
{
    private static final int DIMX = 500;
    private static final int DIMY = 500;

    private JPanel panel;
    private JButton buttonChercherEleve;
    private JLabel labelNom;
    private JLabel labelID;
    private JLabel labelMatiere;
    private JButton buttonModifier;

    /**
     * Création de l'interface pour un {@link Eleve}
     *
     * @param prof - {@link Professeur} connecté
     * @param ecole - {@link Ecole} où étudie l'{@link Eleve}
     */
    public GUI_Professeur(Professeur prof, Ecole ecole)
    {
        super("Professeur - " + prof.getPrenom() + " " + prof.getNom(), ecole, true, DIMX, DIMY);


        // On rentre tous les labels
        labelNom.setText(prof.getPrenom() + " " + prof.getNom().toUpperCase());
        labelID.setText(String.valueOf(prof.getID()));
        labelMatiere.setText(prof.getMatiere().toString());

        buttonChercherEleve.addActionListener(e -> { GUI_chercherEleve promo = new GUI_chercherEleve(ecole, prof.getMatiere()); });
        buttonModifier.addActionListener(e -> { GUI_modifierNote promo = new GUI_modifierNote(prof, ecole); });


        // Si l'on ferme la fenêtre, on sauvegarde la BDD dans les fichiers .csv
        addWindowListener(new WindowAdapter()
        {
            @Override
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
