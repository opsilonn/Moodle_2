import notesElevesProfesseurs.Personne;

import java.util.Date;

public class Main
{
    public static void main(String[] argv)
    {
        Personne p1 = new Personne();
        Personne p2 = new Personne("Adam" , "Eve", new Date());

        System.out.println(p1);
        System.out.println(p2);
    }
}
