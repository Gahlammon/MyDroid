import java.awt.*;
import javax.swing.JFrame;
import java.util.*; 

public class MenuDisplayer {
    private final int windowLocationX = 600;
    private final int windowLocationY = 300;

    private JFrame frame;
    private Vector<MenuElement> elements;
    private Menu menuDisplayed;

    public void prepareFrame(Menu menu){
        menuDisplayed = menu;
        frame = new JFrame(menuDisplayed.getMenuName());
        elements = menuDisplayed.getMenuElements();

        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridBagLayout());
        for(int i=0;i<elements.size();i++){
            frame.getContentPane().add(elements.get(i).getElement(),elements.get(i).getConstraints());
        }
        frame.setLocation(windowLocationX, windowLocationY);
        frame.pack();
        frame.setResizable(false);
    }
    public void setActive(boolean active){
        frame.setEnabled(active);
        frame.setVisible(active);
    }
}