package GUI;

import GUIcomponents.CustomJFrame;
import GUIcomponents.CustomJTextField;
import notesElevesProfesseurs.*;
import javax.swing.*;
import java.util.Map;

/**
 * Fenêtre dédiée à l'ajout d'une note
 *
 * Cette classe hérite de {@link CustomJFrame}
 *
 * @author Hugues
 */
public class GUI_modifierNote extends CustomJFrame {

    private static final int DIMX = 600;
    private static final int DIMY = 400;

    private final Professeur prof;

    private JPanel panel;

    private JComboBox<String> comboBoxPromo;
    private JComboBox<Integer> comboBoxID;
    private JTextField fieldNote;

    private JLabel labelErreur;

    private JButton buttonValider;
    private JLabel labelErreurPromo;
    private JLabel labelErreurID;
    private JComboBox<String> comboBoxNote;

    public GUI_modifierNote(Professeur prof, Ecole ecole) {
        super("Ajouter note", ecole, false, DIMX, DIMY);
        this.prof = prof;

        labelErreur.setVisible(false);
        comboBoxPromo.addActionListener(e -> setID());
        comboBoxID.addActionListener(e -> setIDNote());
        buttonValider.addActionListener(e -> setNote());

        setPromo();

        add(panel);
        pack();
        revalidate();
        setVisible(true);
    }

    private void createUIComponents() {
        fieldNote = new CustomJTextField("DECIMAL", false, 5);
    }

    /**
     * Met à jour le Dropdown contenant la liste des {@link Promotion} Si vide,
     * affiche un message d'erreur ; sinon, appelle la fonction setID
     */
    private void setPromo() {
        comboBoxPromo.removeAllItems();

        for (Map.Entry<String, Promotion> promo : ecole.getPromo().entrySet()) {
            comboBoxPromo.addItem(promo.getKey());
        }

        if (comboBoxPromo.getItemCount() == 0) {
            comboBoxPromo.setVisible(false);
            comboBoxID.setVisible(false);
            comboBoxNote.setVisible(false);
        } else {
            labelErreurPromo.setVisible(false);
            setID();
        }
    }

    /**
     * Met à jour le Dropdown contenant la liste des ID des {@link Eleve}
     * contenus dans la {@link Promotion} Si vide, affiche un message d'erreur ;
     * sinon, appelle la fonction setIDNote
     */
    private void setID() {
        comboBoxID.removeAllItems();

        for (Eleve eleve : ecole.getPromo((String) comboBoxPromo.getSelectedItem()).getEleves()) {
            comboBoxID.addItem(eleve.getID());
        }

        if (comboBoxID.getItemCount() == 0) {
            comboBoxID.setVisible(false);
            comboBoxNote.setVisible(false);
        } else {
            labelErreurID.setVisible(false);
            setIDNote();
        }
    }

    /**
     * Met à jour le Dropdown contenant la liste des ID et des notes de
     * {@link Eleve} selon la {@link Matiere} du {@link Professeur} connecté.
     * Affiche en plus une option pour ajouter une nouvelle note
     */
    private void setIDNote() {
        comboBoxNote.removeAllItems();

        int cpt = 0;

        for (Eleve eleve : ecole.getPromo((String) comboBoxPromo.getSelectedItem()).getEleves()) {
            if (comboBoxID.getSelectedItem() != null && eleve.getID() == (int) comboBoxID.getSelectedItem()) {
                for (Evaluation eval : eleve.getEvaluations(prof.getMatiere())) {
                    comboBoxNote.addItem(cpt++ + " : " + eval.getNote());
                }
            }
        }

        comboBoxNote.addItem(cpt + " : Nouvelle évaluation");
    }

    /**
     * Si la note entrée est correcte, appelle la fonction permettant d'ajouter
     * / de modifier une note chez l'{@link Eleve} connecté
     */
    private void setNote() {
        try {
            Promotion promo = ecole.getPromo((String) comboBoxPromo.getSelectedItem());
            int ID = (int) comboBoxID.getSelectedItem();
            double inputNote = Double.parseDouble(fieldNote.getText());

            // Code pour obtenir l'ID de la note : chaud chaud chaud
            String indexString = (String) comboBoxNote.getSelectedItem();
            indexString = indexString.split(" ")[0];
            int index = Integer.parseInt(indexString);

            if (inputNote < 0) {
                setLabelErreur("Note trop petite !!");
            } else if (20 < inputNote) {
                setLabelErreur("Note trop grande !!");
            } else {
                prof.setNote(promo, ID, inputNote, index);
                dispose();
            }
        } catch (NumberFormatException e) {
            setLabelErreur("Erreur de syntaxe pour la note !!");
        }
    }

    private void setLabelErreur(String text) {
        labelErreur.setVisible(true);
        labelErreur.setText(text);
    }
}
