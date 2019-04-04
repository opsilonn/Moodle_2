import Gestionfichier.FileHandler;
import notesElevesProfesseurs.School;
import test.test1;

/**
 * Class main du projet
 * @author Célia
 */
public class Main
{

    /**
     * Fonction main du projet qui lance les classes de test
     * @param argv
     */
    public static void main(String[] argv)
    {
        School ecole = new School();
        //test1.functiontest1();
        FileHandler scanner = new FileHandler();
        scanner.ReadFiles(ecole);
        
        scanner.WriteFiles(ecole);
    }
}
