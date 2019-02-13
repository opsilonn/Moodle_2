package src.notesElevesProfesseurs;

import java.text.SimpleDateFormat;  // Format des dates
import java.util.Date;  // On préfère utiliser des classes déjà existantes en Java
import java.util.UUID;  // Pour la génération d'identifiants uniques

<<<<<<< HEAD
public class Personne {

    protected String ID;
    protected String nom;
    protected String prenom;
    protected Date dateNaissance;

    public Personne() {
=======

>>>>>>> 3def1041fb4b0165196d6d9761d4f95e90583a35
        ID = UUID.randomUUID().toString();
        dateNaissance = new Date();
    }

<<<<<<< HEAD
    public Personne(String pNom, String pPrenom, Date pDateNaissance) {
        ID = UUID.randomUUID().toString();
        nom = pNom;
        prenom = pPrenom;
        dateNaissance = pDateNaissance;
    }

    public String toString() {
        /*return ID + "    #  Personne  # - "
                + prenom + " " + nom
                + " , né le " + new SimpleDateFormat("dd MMM yyyy").format(dateNaissance);
         */
        return "(" + prenom + ", " + nom + ")";
=======
>>>>>>> 3def1041fb4b0165196d6d9761d4f95e90583a35
    }
}
