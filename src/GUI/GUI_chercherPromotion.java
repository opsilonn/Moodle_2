package GUI;


import GUIcomponents.CustomJFrame;
import GUIcomponents.CustomJTextField;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Ecole;
import notesElevesProfesseurs.Promotion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 * Fenêtre dédiée à la recherche d'une {@link notesElevesProfesseurs.Promotion} dans la base de données
 * <p>
 * Cette classe hérite de {@link CustomJFrame}
 *
 * @author Hugues
 */
class GUI_chercherPromotion extends CustomJFrame {
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
    private void chercherPromotion() {
        String promoInput = fieldPromotion.getText();

        boolean X =  (ecole.getPromo(promoInput) != null);

        //setAffichage(X);
        if(X) {
            String[] columns = new String[]{"Matière", "ID", "Note"};
            Object[][] data;

            data = new Object[ecole.getPromo(promoInput).getEleves().size()][columns.length];
            int index = 0;
            for (Eleve eleve : ecole.getPromo(promoInput).getEleves()) {
                data[index][0] = eleve.getID();
                data[index][1] = eleve.getNom().toUpperCase();
                data[index][2] = eleve.getPrenom();
                index++;
            }
            promotionTable.setModel(new DefaultTableModel(data, columns));
            centrerJTable(promotionTable);
        }


    }


    /**
     * Rend visible ou non certains éléments de l'interface
     *
     * @param hasResult vaut true si l'on a trouvé un ou plusieurs résultats, sinon vaut false
     */
    private void setAffichage(boolean hasResult) {
        labelErreur.setVisible(!hasResult);
        promotion.setVisible(hasResult);
    }
}
