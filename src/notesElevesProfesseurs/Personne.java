package notesElevesProfesseurs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Personne
{
    protected String ID;
    protected String nom;
    protected String prenom;
    protected Date dateNaissance;



    public Personne()
    {
        ID = UUID.randomUUID().toString();
        nom = "Doe";
        prenom = "John";
        dateNaissance = new Date();
    }


    public Personne(String pNom, String pPrenom, Date pDateNaissance)
    {
        ID = UUID.randomUUID().toString();
        nom = pNom;
        prenom = pPrenom;
        dateNaissance = pDateNaissance;
    }


    public String toString()
    {
        return ID + " - "
                + prenom + " " + nom
                + " , né le " + new SimpleDateFormat("dd MMM yyyy").format(dateNaissance);
    }
}
