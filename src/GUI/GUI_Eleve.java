package GUI;

import GUIcomponents.CustomJFrame;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.School;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;


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


        buttonCorrecteur.addActionListener(e -> System.out.println("YO !!"));


        //headers for the table
        String[] columns = new String[] {"Id", "Nom", "Prénom", "some random Number", "Sexe"};

        int MAX = 50;
        Object [][] data = new Object [MAX][5];

        for(int i = 0; i < MAX; i++)
        {
            data[i][0] = i;
            switch(i%3)
            {
                case 0:
                    data[i][1] = "BEGEOT";
                    data[i][2] = "Hugues";
                    data[i][4] = "H";
                    break;

                case 1:
                    data[i][1] = "BRIAND";
                    data[i][2] = "Camille";
                    data[i][4] = "H";
                    break;

                case 2:
                    data[i][1] = "BUNOUF";
                    data[i][2] = "Célia";
                    data[i][4] = "F";
                    break;


                default:
                    data[i][1] = "DOE";
                    data[i][2] = "JOHN";
                    data[i][4] = "X";
                    break;
            }

            data[i][3] = ThreadLocalRandom.current().nextInt(0, 100 );
        }




        //create table model with data
        DefaultTableModel model = new DefaultTableModel(data, columns);
        bulletinValeurs.setModel(model);

        add(panel);
        pack();
        revalidate();
        setVisible(true);
    }
}
