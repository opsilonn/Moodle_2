package src.notesElevesProfesseurs;


import java.text.SimpleDateFormat;
import java.util.Date;


public class Eleve extends Personne
{
    public Eleve()
    {
        super();
    }


    public Eleve(String pNom, String pPrenom, Date pDateNaissance)
    {
        super(pNom, pPrenom, pDateNaissance);
    }


    /*public String toString()
    {
        return ID + "   #    Elève   # - : "
                + prenom + " " + nom
                + " , né le " + new SimpleDateFormat("dd MMM yyyy").format(dateNaissance);
    }*/
}