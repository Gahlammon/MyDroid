import java.util.*; 

public class Menu {
    private Vector<MenuElement> menuElements = new Vector<MenuElement>();
    private String menuName = "deffaultMenuName";
    private Engine menuEngine;
    private boolean menuDisplayed = false;
    private MenuDisplayer menuDisplayer;

    public void createEmptyMenu(String name, Engine engine){
        menuName = name;
        menuEngine = engine;
        clearMenu();
    }
    public void clearMenu(){
        menuElements = new Vector<MenuElement>();
        menuDisplayer = new MenuDisplayer();
    }
    public void addElement(MenuElement element){
        element.setMenu(this);
        menuElements.add(element);
    }
    public String getMenuName(){
        return menuName;
    }
    public int getMenuElementsNumber(){
        return menuElements.size();
    }
    public Vector<MenuElement> getMenuElements(){
        return menuElements;
    }
    public void activateDisplay(){
        updateContent();
        menuDisplayer.prepareFrame(this);
        menuDisplayer.setActive(true);
        menuDisplayed = true;
    }
    public boolean isDisplayed(){
        return menuDisplayed;
    }
    public void setEngine(Engine engine){
        menuEngine = engine;
    }
    public Engine getEngine(){
        return menuEngine;
    }
    public void deactivateDisplay(){
        menuDisplayed = false;
        menuDisplayer.setActive(false);
    }
    public String getName(){
        return menuName;
    }
    public void updateContent(){}
}