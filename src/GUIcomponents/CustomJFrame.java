package GUIcomponents;


import Gestionfichier.FileHandler;
import notesElevesProfesseurs.Ecole;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;


/**
 * CustomJFrame is extending the JFrame class
 *  It contains various optimized prefabs for creating a JFrame, including a setting class iteration.
 * @author Hugues Begeot
 */
public abstract class CustomJFrame extends JFrame
{
    protected Ecole ecole;
    private final static String PATHLOGO = "./src/pictures/logo.png";
    protected final static String PATHLOGOFULL = "./src/pictures/logoFull.png";

    /**
     * default @CustomJFrame's constructor.
     * @deprecated We'll prefer using the other constructors
     * */
    public CustomJFrame()
    {
        setPreferredSize(new Dimension(500, 500));
    }



    /**
     * Regular @CustomJFrame's constructor that includes the JFrame's size.
     * <p>
     * @param title Type of the JFrame we want to create.
     * @param ecole Reference of the Ecole database
     * @param closeOnExit if true, the program is closed when we exit the JFrame
     * @param dimX width of the JFrame
     * @param dimY height of the JFrame
     * */
    protected CustomJFrame(String title, Ecole ecole, boolean closeOnExit, int dimX, int dimY)
    {
        try { setIconImage(ImageIO.read(new File(PATHLOGO)) ); }
        catch (IOException e) { System.out.println("Icon not found"); }

        setTitle(title);
        this.ecole = ecole;
        setPreferredSize(new Dimension(dimX, dimY));
        setMinimumSize(new Dimension(dimX, dimY));

        //setResizable(false);
        setAlwaysOnTop(false);
        if(closeOnExit) setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    /**
     * Centre les valeurs d'une JTable passée en argument
     * <p>
     * @param table JTable à centrer
     * */
    protected void centrerJTable(JTable table)
    {
        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();

        // centre les données de ton tableau
        custom.setHorizontalAlignment(JLabel.CENTER);

        // centre chaque cellule de ton tableau
        for (int i = 0; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setCellRenderer(custom);

        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    }


    /**
     * Si l'on ferme la fenêtre, on sauvegarde la BDD dans les fichiers .csv
     *
     * */
    protected void sauvegarderBDDApresFermeture()
    {
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                FileHandler scanner = new FileHandler();
                scanner.WriteFiles(ecole);
            }
        });
    }
}