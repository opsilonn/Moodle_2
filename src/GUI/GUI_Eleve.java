package GUI;


import GUIcomponents.CustomJFrame;
import notesElevesProfesseurs.Eleve;
import javax.swing.*;


public class GUI_Eleve extends CustomJFrame
{
    private static final int dimX = 500;
    private static final int dimY = 500;

    private Eleve eleve;

    private int ID;
    private JPanel panel;
    private JButton button1;
    private JLabel labelNom;
    private JLabel labelID;
    private JLabel labelMoyenne;
    private JLabel labelPromotion;

    public GUI_Eleve(Eleve eleve)
    {
        super("Eleve - " + eleve.getPrenom() + " " + eleve.getNom(), true, dimX, dimY);
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


        add(panel);
        pack();
        revalidate();
        setVisible(true);
    }
}
