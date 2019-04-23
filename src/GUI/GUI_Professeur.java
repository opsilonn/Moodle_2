package GUI;


import GUIcomponents.CustomJFrame;
import notesElevesProfesseurs.Professeur;
import javax.swing.*;


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

    public GUI_Professeur(Professeur prof)
    {
        super("Professeur - " + prof.getPrenom() + " " + prof.getNom(), true, dimX, dimY);
        this.prof = prof;

        // On rentre tous les labels
        labelNom.setText( prof.getPrenom() + " " + prof.getNom().toUpperCase() );
        labelID.setText( String.valueOf( prof.getID() ) );
        labelMatiere.setText( prof.getMatiere().toString() );

        add(panel);
        pack();
        revalidate();
        setVisible(true);
    }
}