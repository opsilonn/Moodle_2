package GUI;

import notesElevesProfesseurs.*;
import GUIcomponents.CustomJFrame;
import GUIcomponents.CustomJTextField;
import notesElevesProfesseurs.Ecole;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


/**
 * Interface permettant de créer une nouvelle  {@link Personne},  {@link Eleve} comme {@link Professeur}
 *
 * Cette classe hérite de {@link CustomJFrame}
 *
 * @author Hugues
 */
public class GUI_CreerPersonne extends CustomJFrame
{
    private static final int DIM_X = 800;
    private static final int DIM_Y = 600;
    String type;


    private JPanel panel;

    private JTextField fieldNom;
    private JTextField fieldPrenom;
    private JTextField fieldDate;
    private JTextField fieldPassword;

    private JPanel panelEleve;
    private JTextField fieldPromotion;

    private JPanel panelProf;
    private JTextField fieldMatiereID;
    private JButton buttonValider;
    private JTextField fieldMatiereNom;
    private JLabel labelErreurNom;
    private JLabel labelErreurDate;
    private JLabel labelErreurPassword;
    private JLabel labelErreurPrenom;
    private JLabel labelErreurPromotion;
    private JLabel labelErreurMatiereID;
    private JLabel labelErreurMatiereNom;


    public GUI_CreerPersonne(Ecole ecole, String type)
    {
        super("Créer " + type, ecole, false, DIM_X, DIM_Y);
        this.type = type;


        if(Objects.equals(type, "élève")) panelProf.setVisible(false);
        if(Objects.equals(type, "professeur")) panelEleve.setVisible(false);
        buttonValider.addActionListener(e -> verifierInput());
        labelErreurNom.setVisible(false);
        labelErreurPrenom.setVisible(false);
        labelErreurDate.setVisible(false);
        labelErreurPassword.setVisible(false);
        labelErreurPromotion.setVisible(false);
        labelErreurMatiereNom.setVisible(false);
        labelErreurMatiereID.setVisible(false);

        add(panel);
        pack();
        revalidate();
        setVisible(true);
    }


    private void createUIComponents()
    {
        fieldNom = new CustomJTextField("ALPHABET",  false, 30);
        fieldPrenom = new CustomJTextField("ALPHABET",  false, 30);
        fieldPassword = new CustomJTextField("ALL",  false, 20);
        // fieldDate ?

        fieldPromotion = new CustomJTextField("NUMERIC",  false, 4);

        fieldMatiereNom = new CustomJTextField("ALPHABET",  false, 30);
        fieldMatiereID = new CustomJTextField("ALPHA_NUMERIC",  false, 8);
    }


    private String Nom() { return fieldNom.getText(); }
    private String Prenom() { return fieldPrenom.getText(); }
    private String Date() { return fieldDate.getText(); }
    private String Password() { return fieldPassword.getText(); }
    private String Promotion() { return fieldPromotion.getText(); }
    private String MatiereNom() { return fieldMatiereNom.getText(); }
    private String MatiereID() { return fieldMatiereID.getText(); }



    /**
     * Vérifie les inputs. Si tous sont bon, on appelle la fonction ou creerEleve() ou creerProf()
     */
    private void verifierInput()
    {
        boolean isWrong;

        // On vérifie le Nom
        isWrong = (Nom().length() == 0);
        labelErreurNom.setVisible(isWrong);
        if(isWrong)
            return;

        // On vérifie le Prénom
        isWrong = (Prenom().length() == 0);
        labelErreurPrenom.setVisible(isWrong);
        if(isWrong)
            return;

        // On vérifie la Date
        isWrong = (Date().length() == 0);
        labelErreurDate.setVisible(isWrong);
        if(isWrong)
            return;

        // On vérifie le Mot de Passe
        isWrong = (Password().length() == 0);
        labelErreurPassword.setVisible(isWrong);
        if(isWrong)
            return;


        if(Objects.equals(type, "élève"))
        {
            // On vérifie la Promotion
            isWrong = (Promotion().length() == 0);
            labelErreurPromotion.setVisible(isWrong);
            if(isWrong)
                return;

            creerEleve();
        }
        else
        {
            // On vérifie la Matière : Nom
            isWrong = (MatiereNom().length() == 0);
            labelErreurMatiereNom.setVisible(isWrong);
            if(isWrong)
                return;

            // On vérifie la Matière : ID
            isWrong = (MatiereID().length() == 0);
            labelErreurMatiereID.setVisible(isWrong);
            if(isWrong)
                return;

            creerProf();
        }
    }


    /**
     * Crée un {@link Eleve} avec les inputs
     */
    private void creerEleve()
    {
        JOptionPane.showConfirmDialog(this, "Pensez à écrire le reste du code ! là, l'élève n'est pas ajouté à la bdd");

        Date date = new Date(); // FAUT METTRE LA DATE ICI
        Eleve eleve = new Eleve( Nom(), Prenom(), date, Password(), Promotion() );

        // INSERER CODE POUR AJOUTER ELEVE

        dispose();
    }


    /**
     * Crée un {@link Professeur} avec les inputs
     */
    private void creerProf()
    {
        Date date = new Date(); // FAUT METTRE LA DATE ICI
        Matiere matiere = new Matiere( MatiereID(), MatiereNom() );
        Professeur prof = new Professeur( Nom(), Prenom(), date, Password(), matiere );

        ecole.addProfesseur( prof );

        dispose();
    }
}
