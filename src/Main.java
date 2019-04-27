
import test.*;

import javax.swing.*;

/**
 * Class main du projet
 *
 * @author Célia
 */
public class Main {

    /**
     * Fonction main du projet qui lance les classes de test
     *
     * @param argv Any argument sent via the console
     * @throws java.lang.ClassNotFoundException
     * @throws javax.swing.UnsupportedLookAndFeelException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    public static void main(String[] argv) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        //test_Version1.main();
        //System.out.println("____________________________________________");
        test_Version2.main();
        System.out.println("____________________________________________");
        test_Version3.main();

        test_Version4.main();
    }
}
