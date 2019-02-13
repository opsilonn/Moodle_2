package src.notesElevesProfesseurs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Professeur extends Personne
{
    List<Matiere> matiere;


    public Professeur()
    {
        super();
        matiere = new ArrayList<Matiere>();
    }


<<<<<<< HEAD
    public Professeur(String pNom, String pPrenom, Date pDateNaissance)
    {
        super(pNom, pPrenom, pDateNaissance);
        matiere = new ArrayList<Matiere>();
    }


    /*public String toString()
    {
        return ID + "   # Professeur # - : "
                + prenom + " " + nom
                + " , né le " + new SimpleDateFormat("dd MMM yyyy").format(dateNaissance);
    }*/
=======
>>>>>>> 3def1041fb4b0165196d6d9761d4f95e90583a35
}
