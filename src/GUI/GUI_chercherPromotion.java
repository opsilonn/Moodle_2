package GUI;


import GUIcomponents.CustomJFrame;
import GUIcomponents.CustomJTextField;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.School;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 * Fenêtre dédiée à la recherche d'une {@link notesElevesProfesseurs.Promotion} dans la base de données
 *
 * Cette classe hérite de {@link CustomJFrame}
 *
 * @author Hugues
 */
public class GUI_chercherPromotion extends CustomJFrame
{
    private static final int dimX = 600;
    private static final int dimY = 400;


    private JPanel panel;


    private JTextField fieldPromotion;
    private JButton buttonValider;

    private JLabel labelErreur;

    private JTable promotionTable;
    private JScrollPane promotion;


    public GUI_chercherPromotion(School ecole)
    {
        super("Chercher Promotion", ecole, false, dimX, dimY);
        this.ecole = ecole;


        setAffichage(false, false);
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
        String[] columns = new String[] {"Matière", "ID", "Note"};
        Object [][] data;

        if(ecole.getPromo(promoInput) == null)
        {
            setAffichage(true, false);
            data = new Object [0][columns.length];
        }
        else
        {
            setAffichage(false, true);
            data = new Object [ecole.getPromo(promoInput).getEleves().size()][columns.length];

            int index = 0;
            for (Eleve eleve : ecole.getPromo(promoInput).getEleves())
            {
                data[index][0] = eleve.getID();
                data[index][1] = eleve.getNom().toUpperCase();
                data[index][2] = eleve.getPrenom();
                index++;
            }
        }

        DefaultTableModel model = new DefaultTableModel(data, columns);
        promotionTable.setModel(model);
    }


    /**
     * Rend visible ou non certains éléments de l'interface
     *
     * @param bLabelErreur determine si l'on affiche ou non le JLabel Erreur
     * @param bPromotion determine si l'on affiche ou non la JTable et le JScrollPane affichant la {@link Promotion}
     */
    public void setAffichage(boolean bLabelErreur, boolean bPromotion)
    {
        labelErreur.setVisible(bLabelErreur);
        promotion.setVisible(bPromotion);
        promotionTable.setVisible(bPromotion);
    }
}
