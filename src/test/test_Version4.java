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
    private static GUI_Login login;


    public static void main() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException
    {
        // On rempli la structure ecole
        ecole = new School();
        FileHandler scanner = new FileHandler();
        scanner.ReadFiles(ecole);

        // On affiche la fenêtre LOGIN
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        login = new GUI_Login(ecole);
    }
}