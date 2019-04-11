/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestionfichier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.logging.Level;
import java.util.logging.Logger;
import notesElevesProfesseurs.*;

/**
 *
 * @author Célia
 */
public class FileHandler {

    File fileEleve;
    File fileProf;

    private static final String FILENAME_ELEVE = "C:\\Users\\CéliaB\\Documents\\GitHub\\Moodle_2\\Moodle_2\\src\\Gestionfichier\\BasededonneesEleves.csv";
    private static final String FILENAME_PROF = "C:\\Users\\CéliaB\\Documents\\GitHub\\Moodle_2\\Moodle_2\\src\\Gestionfichier\\BasededonneesProfs.csv";

    public FileHandler() {
        fileEleve = new File(FILENAME_ELEVE);
        fileProf = new File(FILENAME_PROF);
    }

    /**
     * Fonction qui permet de lire les deux fichiers de base de données
     *
     * @param ecole représente l'ecole à remplir grâce aux fichiers
     * @return si l'opération a été réussie
     */
    public boolean ReadFiles(School ecole) {
        boolean flag;
        flag = ReadProfs(ecole);
        flag = flag && ReadEleves(ecole);
        return flag;
    }

    /**
     * Fonction qui permet de lire la base de données élèves
     *
     * @param ecole représente l'école à laquelle appartient les étudiants
     * @return
     */
    private boolean ReadEleves(School ecole) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        BufferedReader reader;
        String line;
        String[] elements;
        try {
            reader = new BufferedReader(new FileReader(fileEleve));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {

                elements = line.split(",");
                if (elements.length < 4) {
                    System.out.println("The database is not correctly organized");
                    return false;
                }

                Eleve eleve = new Eleve(elements[0], elements[1], format.parse(elements[2]), Integer.parseInt(elements[3]));
                int year = Integer.parseInt(elements[3].substring(0, 4));
                if(year == Year.now().getValue())
                {
                    int number = Integer.parseInt(elements[3].substring(5));
                    if ( number > Personne.getIndex()){
                        Personne.setIndex(number);
                    }
                } 
                
                for (int i = 5; i < elements.length; i = i + 5) {
                    Professeur correcteur = ecole.findProfesseur(elements[i + 1], elements[i + 2], elements[i + 3]);
                    if (correcteur != null) {
                        Evaluation eval = new Evaluation(eleve, correcteur, correcteur.getMatiere(), Double.parseDouble(elements[i]));
                        eleve.addEvaluation(eval);
                    } else {
                        Matiere matiere = new Matiere(elements[i + 3], elements[i + 4]);
                        correcteur = new Professeur(elements[i + 1], elements[i + 2], null, matiere);
                        Evaluation eval = new Evaluation(eleve, correcteur, correcteur.getMatiere(), Double.parseDouble(elements[i]));
                        eleve.addEvaluation(eval);
                        ecole.addProfesseur(correcteur);
                    }
                }

                ecole.addPromo(new Promotion(elements[4]));
                Promotion promo = ecole.getPromo(elements[4]);
                promo.addEleve(eleve);
            }
            reader.close();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    /**
     * Fonction qui permet de lire la base de données profs
     * @param ecole représente l'école à laquelle appartient les profs
     * @return
     */
    private boolean ReadProfs(School ecole) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        BufferedReader reader;
        String line;
        String[] elements;

        try {
            reader = new BufferedReader(new FileReader(fileProf));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                elements = line.split(",");
                Matiere matiere = new Matiere(elements[4], elements[5]);
                Professeur professeur = new Professeur(elements[0], elements[1], format.parse(elements[2]), Integer.parseInt(elements[3]), matiere);
                int year = Integer.parseInt(elements[3].substring(0, 4));
                if(year == Year.now().getValue())
                {
                    int number = Integer.parseInt(elements[3].substring(5));
                    if ( number >= Personne.getIndex()){
                        Personne.setIndex(number+1);
                    }
                }            
                             
                ecole.addProfesseur(professeur);
            }
            reader.close();
        } catch (IOException | ParseException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Fonction qui permet d'écrire dans les bases de données
     *
     * @param ecole
     * @return
     */
    public boolean WriteFiles(School ecole) {
        boolean flag;
        flag = WriteTeachers(ecole);
        flag = flag && WriteStudents(ecole);
        return flag;
    }

    /**
     * Fonction qui permet d'écrire dans la base de données profs
     *
     * @param ecole
     * @return
     */
    private boolean WriteTeachers(School ecole) {
        boolean fileExists = false;
        if (fileProf.exists()) {
            fileExists = true;
        }
        FileWriter writer;
        try {
            writer = new FileWriter(fileProf);
            writer.write("Nom,Prenom,Birthdate,ID,CodeMatiere,NomMatiere\n");

            // Write in the file each professor with a CSV functioning
            for (Professeur p : ecole.getProfesseur()) {
                writer.write(p.profToCSV());
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Fonction qui permet d'écrire dans la base de données étudiants
     *
     * @param ecole
     * @return
     */
    private boolean WriteStudents(School ecole) {

        boolean fileExists = false;
        if (fileEleve.exists()) {
            fileExists = true;
        }
        FileWriter writer;
        try {
            writer = new FileWriter(fileEleve);
            String columnsName = "Nom,Prenom,Birthdate,ID,Promotion,Evnote,EvCorrNom,EvCorrPrenom,CodeMatiere,NomMatiere";
            writer.write(columnsName);
            
            // Write in the file each professor with a CSV functioning
            for (String promo_name : ecole.getPromo().keySet()) {
                for (Eleve e : ecole.getPromo(promo_name).getEleves()) {
                    writer.write('\n' + e.elevesToCSV());
                }
            }

            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

}
