package GUI;

import GUIcomponents.CustomJFrame;
import GUIcomponents.CustomJTextField;
import notesElevesProfesseurs.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Map;

/**
 * Fenêtre dédiée à la fonction de recherche d'un {@link Eleve}
 *
 * Cette classe hérite de {@link CustomJFrame}
 */
class GUI_chercherEleve extends CustomJFrame {

    private static final int DIMX = 600;
    private static final int DIMY = 400;

    private final Matiere matiere;

    private JPanel panel;

    private JTextField fieldID;
    private JButton buttonChercher;

    private JLabel labelErreur;

    private JPanel panelResultat;
    private JLabel labelID;
    private JLabel labelNom;
    private JLabel labelPrenom;
    private JTable tableNotes;
    private JLabel labelErreur2;

    public GUI_chercherEleve(Ecole ecole, Matiere matiere) {
        super("Chercher élève", ecole, false, DIMX, DIMY);
        this.ecole = ecole;
        this.matiere = matiere;

        labelErreur.setVisible(false);
        panelResultat.setVisible(false);
        labelErreur2.setVisible(false);
        tableNotes.setVisible(false);
        buttonChercher.addActionListener(e -> chercherEleve());

        add(panel);
        pack();
        revalidate();
        setVisible(true);
    }

    private void createUIComponents() {
        fieldID = new CustomJTextField("NUMERIC", false, 8);
    }

    /**
     * Quand activée, affiche les résultats en conséquence : si l'{@link Eleve}
     * est trouvé, on l'affiche ses informations sinon, on affiche un message
     * d'erreur
     */
    private void chercherEleve() {
        if (fieldID.getText().length() == 0) {
            labelErreur.setVisible(true);
            panelResultat.setVisible(false);
        } else {
            Eleve eleve = null;
            int IDinput = Integer.parseInt(fieldID.getText());

            for (Map.Entry<String, Promotion> promo : ecole.getPromo().entrySet()) {
                for (Eleve e : promo.getValue().getEleves()) {
                    if (e.getID() == IDinput) {
                        eleve = e;
                    }
                }
            }

            if (eleve == null) {
                labelErreur.setVisible(true);
                panelResultat.setVisible(false);
            } else {
                labelErreur.setVisible(false);
                panelResultat.setVisible(true);

                labelID.setText(String.valueOf(eleve.getID()));
                labelNom.setText(eleve.getNom().toUpperCase());
                labelPrenom.setText(eleve.getPrenom());

                try{
                    eleve.getEvaluations(matiere);
                    labelErreur2.setVisible(false);
                    tableNotes.setVisible(true);

                    String[] columns = new String[]{"ID", "Note"};
                    Object[][] data = new Object[eleve.getEvaluations(matiere).size()][columns.length];

                    int index = 0;
                    for (Evaluation eval : eleve.getEvaluations(matiere)) {
                        data[index][0] = eval.getID();
                        data[index][1] = String.valueOf(eval.getNote());
                        index++;
                    }

                    //create table model with data
                    DefaultTableModel model = new DefaultTableModel(data, columns);
                    tableNotes.setModel(model);

                }
                catch (IllegalStateException e){
                    labelErreur2.setVisible(true);
                    tableNotes.setVisible(false);
                }
            }
        }
    }
}
