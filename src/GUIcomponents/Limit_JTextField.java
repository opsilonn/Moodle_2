package GUIcomponents;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

class Limit_JTextField extends PlainDocument
{
    private final int limit;

    Limit_JTextField(int limit)
    {
        super();
        this.limit = limit;
    }

    Limit_JTextField(int limit, boolean upper)
    {
        super();
        this.limit = limit;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException
    {
        if (str == null)
            return;

        if ((getLength() + str.length()) <= limit)
            super.insertString(offset, str, attr);
    }
}