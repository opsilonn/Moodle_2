package GUI;


import GUIcomponents.CustomJFrame;
import GUIcomponents.CustomJTextField;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Ecole;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;


/**
 * Fenêtre dédiée à la recherche d'une {@link notesElevesProfesseurs.Promotion} dans la base de données
 * <p>
 * Cette classe hérite de {@link CustomJFrame}
 *
 * @author Hugues
 */
class GUI_chercherPromotion extends CustomJFrame
{
    private static final int DIMX = 600;
    private static final int DIMY = 400;


    private JPanel panel;


    private JTextField fieldPromotion;
    private JButton buttonValider;

    private JLabel labelErreur;

    private JTable promotionTable;
    private JScrollPane promotion;


    public GUI_chercherPromotion(Ecole ecole)
    {
        super("Chercher Promotion", ecole, false, DIMX, DIMY);
        this.ecole = ecole;

        setDisplay(true);
        buttonValider.addActionListener( e -> chercherPromotion() );

        add(panel);
        pack();
        revalidate();
        setVisible(true);
    }


    private void createUIComponents()
    {
        fieldPromotion = new CustomJTextField("NUMERIC", false, 4);
    }


    /**
     * Quand activée, affiche les résultats en conséquence :
     * si la Promotion est trouvée (et comporte au moins un {@link Eleve}, on l'affiche
     * si elle n'est pas trouvée / est vide, on affiche un message d'erreur
     */
    private void chercherPromotion()
    {
        String promoInput = fieldPromotion.getText();

        boolean hasResult =  (ecole.getPromo(promoInput) != null);


        setDisplay(hasResult);


        if(hasResult)
        {
            List<Eleve> promotion = ecole.getPromo(promoInput).getEleves();
            String[] columns = new String[]{"Matière", "ID", "Note"};
            Object[][] data = new Object[promotion.size()][columns.length];

            int index = 0;
            for (Eleve eleve : promotion)
            {
                data[index][0] = eleve.getID();
                data[index][1] = eleve.getNom().toUpperCase();
                data[index][2] = eleve.getPrenom();
                index++;
            }
            promotionTable.setModel( new DefaultTableModel(data, columns) );
            centrerJTable(promotionTable);
        }
    }


    /**
     * Affiche ou cache des éléments de l'interface en conséquence de l'input ;
     * si vrai, on affiche les résultats ;
     * si faux, on affiche un message d'erreur
     * @param hasResult booléen qui vaut true si l'input de l'utilisateur correspond effectivement à une promotion
     */
    private void setDisplay(boolean hasResult)
    {
        labelErreur.setVisible(!hasResult);
        promotion.setVisible(hasResult);
    }
}
