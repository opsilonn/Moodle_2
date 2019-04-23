package GUIcomponents;


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
    protected final static String pathLogo = "./src/pictures/logo.png";
    protected final static String pathLogoFull = "./src/pictures/logoFull.png";

    /**
     * default @CustomJFrame's constructor.
     * @deprecated We'll prefer using the other constructors
     * */
    public CustomJFrame()
    {
        setPreferredSize(new Dimension(500, 500));
    }


    /**
     * Regular @CustomJFrame's constructor.
     * <p>
     * @param title Type of the JFrame we want to create.
     * */
    public CustomJFrame(String title)
    {
        try { setIconImage( ImageIO.read( new File(pathLogo)) ); }
        catch (IOException e) { System.out.println("Icon not found"); }

        setTitle(title);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(300, 300));

        //setResizable(false);
        setAlwaysOnTop(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    /**
     * Regular @CustomJFrame's constructor that includes the JFrame's size.
     * <p>
     * @param title Type of the JFrame we want to create.
     * @param dimX width of the JFrame
     * @param dimY height of the JFrame
     * */
    public CustomJFrame(String title, boolean closeOnExit, int dimX, int dimY)
    {
        try { setIconImage( ImageIO.read( new File(pathLogo)) ); }
        catch (IOException e) { System.out.println("Icon not found"); }

        setTitle(title);
        setPreferredSize(new Dimension(dimX, dimY));
        setMinimumSize(new Dimension(dimX, dimY));

        //setResizable(false);
        setAlwaysOnTop(false);
        if(closeOnExit) setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}