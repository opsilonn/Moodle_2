package notesElevesProfesseurs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Professeur extends Personne
{
    //List<String> matière;


    public Professeur()
    {
        super();
    }


    public Professeur(String pNom, String pPrenom, Date pDateNaissance)
    {
        super(pNom, pPrenom, pDateNaissance);
    }
}
