import java.awt.*;
import javax.swing.JButton;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.util.*; 

public class Button extends MenuElement implements ActionListener {
    private JButton button;
    private String buttonName = "deffaultButtonName";
    private Color buttonColor;
    private Vector<Interaction> buttonInteractions = new Vector<Interaction>();

    public void createElement(String name, int x, int y, int sizeX, int sizeY, Color color){
        setConstrains(x, y, sizeX, sizeY);

        buttonInteractions = new Vector<Interaction>();
        buttonName = name;
        buttonColor = color;

        button = new JButton(buttonName);
        button.setBackground(buttonColor);
        button.addActionListener(this);
        setElement(button);
    }
    public void setName(String name){
        buttonName = name;
        button.setText(name);
    }
    public void addInteraction(int operation){
        buttonInteractions.add(new Interaction());
        buttonInteractions.get(buttonInteractions.size() - 1).assignElement(this);
        buttonInteractions.get(buttonInteractions.size() - 1).setOperation(operation);
    }
    public Vector<Interaction> getInteractions(){
        return buttonInteractions;
    }
    public void interact(){
        for(int i=0;i<buttonInteractions.size();i++){
            buttonInteractions.get(i).performInteraction();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        getMenu().getEngine().pauseLoop(true);
        if(getMenu().isDisplayed()) {interact();}
        getMenu().getEngine().pauseLoop(false);
    }
}