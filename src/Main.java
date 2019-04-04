
import Gestionfichier.FileHandler;
import java.util.List;
import notesElevesProfesseurs.*;
import test.test1;

/**
 * Class main du projet
 *
 * @author Célia
 */
public class Main {

    /**
     * Fonction main du projet qui lance les classes de test
     *
     * @param argv
     */
    public static void main(String[] argv) {
        School ecole = new School();
        //test1.functiontest1();
        FileHandler scanner = new FileHandler();
        scanner.ReadFiles(ecole);
        //ecole.toString();
        List<Eleve> eleves = ecole.getPromo("2013").getEleves();
        //System.out.println(eleves.get(0));
        Bulletin.createBulletin(eleves.get(0), ecole);

        //ecole.addProfesseur(new Professeur());
        /*Promotion p = new Promotion();
        Eleve e = new Eleve();

        p.addEleve(e);
        ecole.addPromo(p);
        scanner.WriteFiles(ecole);*/
    }
}
