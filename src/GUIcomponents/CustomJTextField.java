package GUIcomponents;


import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Segment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Cette classe est customisée et permet d'accueillir un meilleur  {@link JPasswordField}.
 *
 * Le texte entré est centré, on peut choisir le type d'input, et si l'input doit être caché ou non.
 *
 * Cette classe hérite de {@link JPasswordField}
 *
 * @author Hugues
 */
public class CustomJTextField extends JPasswordField
{
    // "NUMERIC",
    // "DECIMAL",
    // "LOWER_ALPHABET",
    // "UPPER_ALPHABET",
    // "ALPHABET",
    // "ALPHA_NUMERIC",
    // "ALL"

    private String type;
    private boolean password;


    public CustomJTextField(String type, boolean password, int maxChar)
    {
        this.type = type;
        this.password = password;
        if(0 < maxChar) setDocument(new Limit_JTextField(maxChar));
        setHorizontalAlignment(JTextField.CENTER);
        validInput();

        // Pour cacher si mot de passe
        setEchoChar('*');
        if(!password) passwordUnset();
    }


    private void validInput()
    {
        addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char ch = e.getKeyChar();
                if (!isCorrect(ch) && ch != '\b') e.consume();
            }
        });
    }
    private boolean isCorrect(char ch)
    {
        boolean numeric = ch >= '0' && ch <= '9';
        boolean decimal = numeric || ch == '.';
        boolean lowLetter = ch >= 'a' && ch <= 'z';
        boolean upLetter = ch >= 'A' && ch <= 'Z';

        if(type.equals("NUMERIC"))           return numeric;
        if(type.equals("DECIMAL"))           return decimal;
        if(type.equals("LOWER_ALPHABET"))    return lowLetter;
        if(type.equals("UPPER_ALPHABET"))    return upLetter;
        if(type.equals("ALPHABET"))          return lowLetter || upLetter;
        if(type.equals("ALPHA_NUMERIC"))     return numeric || lowLetter || upLetter;

        return true; // in case of all accepted
    }


    /**
     * Returns the text contained in this <code>TextComponent</code>.
     * @return the text
     */
    public String Field()
    {
        Document doc = getDocument();
        Segment txt = new Segment();
        try { doc.getText(0, doc.getLength(), txt); /* use the non-String API */}
        catch ( BadLocationException e) { return null; }

        char[] retValue = new char[txt.count];
        System.arraycopy(txt.array, txt.offset, retValue, 0, txt.count);
        return new String(retValue);
    }


    public int length() { return Field().length(); }


    public void hideOrReveal()
    {
        if(password)
        {
            if(echoCharIsSet()) passwordUnset();
            else                passwordSet();
        }
    }
    private void passwordUnset()
    {
        setEchoChar((char)0);
    }
    private void passwordSet()
    {
        setEchoChar('*');
    }
}