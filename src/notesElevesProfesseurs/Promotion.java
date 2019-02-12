package src.notesElevesProfesseurs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Promotion
{
    ArrayList<Eleve> listeEleve;
    String nom;
    int annee;

    public Promotion()
    {
        listeEleve = new ArrayList<>();
        nom = "Promo Sion";
        annee = 1234;
    }


    public Promotion(String pNom, int pAnnee)
    {
        listeEleve = new ArrayList<>();
        nom = pNom;
        annee = pAnnee;
    }


    public String toString()
    {
        return "Cé Pô Implaimantay";
    }
}
