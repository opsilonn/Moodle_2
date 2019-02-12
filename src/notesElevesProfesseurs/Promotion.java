package notesElevesProfesseurs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * Ensemble d'{@link Eleve}s.
 */
public class Promotion
{
    private ArrayList<Eleve> listeEleve;
    private String nom;
    private int annee;


    private static final String NOM_PAR_DEFAUT = "Promo Sion";
    private static final int ANNEE_PAR_DEFAUT = 1234;


    /**
     * Constructeur par défaut
     */
    public Promotion()
    {
        this.listeEleve = new ArrayList<>();
        this.nom = NOM_PAR_DEFAUT;
        this.annee = ANNEE_PAR_DEFAUT;
    }


    /**
     * Instancie une {@link Promotion} avec un nom de promotion et une année
     * @param nom Nom de la promotion
     * @param annee Année de la promotion
     */
    public Promotion(String nom, int annee)
    {
        this.listeEleve = new ArrayList<>();
        this.nom = nom;
        this.annee = annee;
    }


    /**
     * Créé une représentation textuelle de l'instance
     * @return Représentation textuelle de l'instance
     */
    public String toString()
    {
        return "Cé Pô Implaimantay";
    }
}
