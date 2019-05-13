package test;

import GUI.*;
import Gestionfichier.FileHandler;
import notesElevesProfesseurs.*;
import javax.swing.*;

/**
 * Fonction de test de la version .4
 *
 * @author Hugues
 */
public class test_Version4 {

    public static void main() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        // On rempli la structure ecole
        Ecole ecole = new Ecole();
        FileHandler scanner = new FileHandler();
        scanner.ReadFiles(ecole);
        System.out.println(ecole.toString());

        // On affiche la fenêtre LOGIN
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        GUI_Login login = new GUI_Login(ecole);
    }
}
