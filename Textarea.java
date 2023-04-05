import java.awt.*;
import javax.swing.JTextArea;

public class Textarea extends MenuElement{
    private JTextArea textarea;
    private String textareaText = "deffaultText";
    private Color textareaColor;

    public void createElement(String name, int x, int y, int sizeX, int sizeY, Boolean locked, Color color){
        setConstrains(x, y, sizeX, sizeY);

        textareaText = name;
        textareaColor = color;

        textarea = new JTextArea(textareaText);
        textarea.setBackground(textareaColor);
        textarea.setEditable(!locked);
        setElement(textarea);
    }
    public void setText(String text){
        textareaText = text;
        textarea.setText(text);
    }
    public String getText(){
        return textarea.getText();
    }
}