package test;


import GUI.*;
import GUIcomponents.CustomJFrame;
import Gestionfichier.FileHandler;
import notesElevesProfesseurs.*;
import javax.swing.*;
import java.util.Map;
import java.util.Objects;


/**
 *
 * @author Hugues
 */
public class test_Version4
{
    /**
     * Fonction de test de la version 4
     */


    private static School ecole;
    private static GUI_Login login = new GUI_Login();


    public static void main() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException
    {
        // On rempli la structure ecole
        ecole = new School();
        FileHandler scanner = new FileHandler();
        scanner.ReadFiles(ecole);

        // On affiche la fenêtre LOGIN
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        login.buttonLogin.addActionListener(e -> loginVerifier(login.fieldID.getText(), new String(login.fieldPassword.getPassword())) );
    }


    private static void loginVerifier(String stringInputID, String inputPassword)
    {
        int inputID = 0;
        Professeur userProf = null;
        Eleve userEleve = null;


        // On vérifie la taille de l'input ID : si nulle, on évite toute vérification
        if(stringInputID.length() != 0)
        {
            inputID = Integer.parseInt(stringInputID);

            // On vérifie s'il y a correspondance avec un PROFESSEUR
            for (Professeur prof : ecole.getProfesseur())
            {
                if( prof.getID() == inputID &&  Objects.equals(prof.getPassword(), inputPassword) )
                    userProf = prof;
            }


            // On vérifie s'il y a correspondance avec un ELEVE

            // On charge toutes les promotions
            for ( Map.Entry<String, Promotion> promo : ecole.getPromo().entrySet() )
            {
                // Dans chaque Promotion, on vérifie chaque élève
                for (Eleve eleve : promo.getValue().getEleves())
                {
                    if( eleve.getID() == inputID && Objects.equals(eleve.getPassword(), inputPassword) )
                        userEleve = eleve;
                }
            }
        }

        // On agit en conséquence : ouverture de fenêtre appropriée si Login réussi, sinon message d'erreur
        if(userProf != null || userEleve != null)
        {
            // Fermeture du login
            login.dispose();

            // Ouverture de la page appropriée
            CustomJFrame mainWindow;
            if( userProf != null )  mainWindow = new GUI_Professeur(userProf);
            if( userEleve != null ) mainWindow = new GUI_Eleve(userEleve);
        }
        else
            login.labelIncorrect.setVisible(true);
    }
}