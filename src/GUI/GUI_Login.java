package GUI;


import GUIcomponents.*;
import javax.swing.*;
import java.awt.*;


/**
 * GUI_Login is extending the CustomJFrame class.
 *  It corresponds to the Login's JFrame (connect with ID and Password).
 * @author Hugues Begeot
 */
public class GUI_Login extends CustomJFrame
{
    private static final int dimX = 500;
    private static final int dimY = 500;

    private JPanel panel;
    private JPanel panelLogo;
    private JPanel panelInputs;
    private JPanel panelForm;

    private JLabel labelLogo;

    public JTextField fieldID;
    public JPasswordField fieldPassword;

    public JButton buttonLogin;
    public JLabel labelIncorrect;


    public GUI_Login()
    {
        super("Login", true, dimX, dimY);


        // Adds the logo image
        ImageIcon imageIcon = new ImageIcon(pathLogoFull); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance((int) (dimX * 0.9), dimY/3,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        labelLogo.setIcon(imageIcon);


        labelIncorrect.setVisible(false);


        add(panel);
        pack();
        revalidate();
        setVisible(true);
    }

    private void createUIComponents()
    {
        fieldID = new CustomJTextField("NUMERIC",  false, 8);
        fieldPassword = new CustomJTextField("ALL",  true, 20);
    }
}
