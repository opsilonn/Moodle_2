package GUIcomponents;


import notesElevesProfesseurs.Ecole;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
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
    protected final static String PATHLOGO = "./src/pictures/logo.png";
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
    public CustomJFrame(String title, Ecole ecole, boolean closeOnExit, int dimX, int dimY)
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
}