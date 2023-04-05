import java.awt.*;

public class MenuElement {
    final public int fill = GridBagConstraints.BOTH;
    final public double weightx = 0.5;
    final public double weighty = 0.5;
    private GridBagConstraints constraints = new GridBagConstraints();
    private int elementX = 0;
    private int elementY = 0;
    private int elementSizeX = 1;
    private int elementSizeY = 1;
    private Component element;
    private Menu elementMenu;

    public void setConstrains(int x, int y, int sizeX, int sizeY){
        elementX = x;
        elementY = y;
        elementSizeX = sizeX;
        elementSizeY = sizeY;
        constraints = new GridBagConstraints();

        //constants for entire project

        constraints.fill = fill;
        constraints.weightx = weightx;
        constraints.weighty = weighty;

        //edited by user

        constraints.gridx = elementX;
        constraints.gridy = elementY;
        constraints.gridwidth = elementSizeX;
        constraints.gridheight = elementSizeY;
    }
    public void setElement(Component component){
        element = component;
    }
    public Component getElement(){
        return element;
    }
    public GridBagConstraints getConstraints(){
        return constraints;
    }
    public void setMenu(Menu menu){
        elementMenu = menu;
    }
    public Menu getMenu(){
        return elementMenu;
    }
}