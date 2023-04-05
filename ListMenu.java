import java.awt.Color;
import java.util.Vector;

public class ListMenu extends Menu{
    public static final int DROID_LIST = 1;
    public static final int LOCATION_MENU = 2;
    public static final int LOCATION_LIST = 3;
    public static final int LOCATION_SELECT = 4;
    public static final int TASK_SELECT = 5;

    private Vector<Droid> listMenuDroidVector;
    private Vector<Location> listMenuLocationVector;
    private Vector<Integer> listMenuIntVector;
    private Droid listMenuDroid;
    private Location listMenuLocation;
    private Menu listMenuExitMenu;
    private int listMenuType;

    public void createEmptyMenu(String name, Engine engine, Vector vector, Menu exitMenu, int type){
        createEmptyMenu(name, engine);
        listMenuExitMenu = exitMenu;
        listMenuType = type;
        if(listMenuType == DROID_LIST) listMenuDroidVector = vector;
        if(listMenuType == LOCATION_MENU) listMenuDroidVector = vector;
        if(listMenuType == LOCATION_LIST) listMenuLocationVector = vector;
        if(listMenuType == LOCATION_SELECT) listMenuLocationVector = vector;
        if(listMenuType == TASK_SELECT) listMenuIntVector = vector;
        updateContent();
    }
    public void createEmptyMenu(String name, Engine engine, Vector vector, Menu exitMenu, int type, Location location){
        listMenuLocation = location;
        createEmptyMenu(name, engine, vector, exitMenu, type);
    }
    public void createEmptyMenu(String name, Engine engine, Vector vector, Menu exitMenu, int type, Droid droid){
        listMenuDroid = droid;
        createEmptyMenu(name, engine, vector, exitMenu, type);
    }
    public void assignLocation(Location location){
        listMenuLocation = location;
    }
    public void assignDroid(Droid droid){
        listMenuDroid = droid;
    }
    public void updateContent(){
        Vector<Button> buttons = new Vector<Button>();
        Textarea textarea;
        Textarea title = new Textarea();
        Button exit = new Button();
        Image image = new Image();

        if(listMenuType == DROID_LIST){
            clearMenu();

            title.createElement(getMenuName(), 0, 0, 1, 1, true, Color.CYAN);
            addElement(title);

            exit.createElement("wyjdź", 0, listMenuDroidVector.size() + 1, 1, 1, Color.RED);
            exit.addInteraction(Interaction.CHANGE_MENU);
            exit.getInteractions().get(0).setMenu(listMenuExitMenu);
            addElement(exit);

            for(int i=0;i<listMenuDroidVector.size();i++){
                buttons.add(new Button());
                buttons.get(i).createElement(listMenuDroidVector.get(i).getName(), 0, i+1, 1, 1, Color.LIGHT_GRAY);
                buttons.get(i).addInteraction(Interaction.CHANGE_MENU);
                buttons.get(i).getInteractions().get(0).setMenu(listMenuDroidVector.get(i).getMenu());
                addElement(buttons.get(i));
            }
        }
        if(listMenuType == LOCATION_MENU){
            clearMenu();

            title.createElement(listMenuLocation.getName(), 0, 0, 3, 1, true, Color.CYAN);
            addElement(title);
            
            textarea = new Textarea();
            textarea.createElement("droidy:", 0, 1, 2, 1, true, Color.LIGHT_GRAY);
            addElement(textarea);

            exit.createElement("inne lokacje", 0, listMenuDroidVector.size() + 2, 2, 1, Color.RED);
            exit.addInteraction(Interaction.CHANGE_MENU);
            exit.getInteractions().get(0).setMenu(listMenuExitMenu);
            addElement(exit);

            image = new Image();
            image.createElement(listMenuLocation.getImagePath(), 2, 1, 1, listMenuDroidVector.size() + 2);
            addElement(image);

            for(int i=0;i<listMenuDroidVector.size();i++){
                buttons.add(new Button());
                buttons.get(i).createElement(listMenuDroidVector.get(i).getName(), 0, i+2, 1, 1, Color.CYAN);
                buttons.get(i).addInteraction(Interaction.CHANGE_MENU);
                buttons.get(i).getInteractions().get(0).setMenu(listMenuDroidVector.get(i).getMenu());
                addElement(buttons.get(i));
                textarea = new Textarea();
                textarea.createElement(Droid.ORDER_NAME[listMenuDroidVector.get(i).getCurrentTask()], 1, i+2, 1, 1, true, Color.LIGHT_GRAY);
                addElement(textarea);
            }
        }
        if(listMenuType == LOCATION_LIST){
            clearMenu();

            title.createElement(getMenuName(), 0, 0, 1, 1, true, Color.CYAN);
            addElement(title);

            exit.createElement("wyjdź", 0, listMenuLocationVector.size() + 1, 1, 1, Color.RED);
            exit.addInteraction(Interaction.CHANGE_MENU);
            exit.getInteractions().get(0).setMenu(listMenuExitMenu);
            addElement(exit);

            for(int i=0;i<listMenuLocationVector.size();i++){
                buttons.add(new Button());
                buttons.get(i).createElement(listMenuLocationVector.get(i).getName(), 0, i+1, 1, 1, Color.LIGHT_GRAY);
                buttons.get(i).addInteraction(Interaction.CHANGE_MENU);
                buttons.get(i).getInteractions().get(0).setMenu(listMenuLocationVector.get(i).getMenu());
                addElement(buttons.get(i));
            }
        }
        if(listMenuType == LOCATION_SELECT){
            clearMenu();

            title.createElement(getMenuName(), 0, 0, 2, 1, true, Color.CYAN);
            addElement(title);

            for(int i=0;i<listMenuLocationVector.size();i++){
                if(listMenuLocationVector.get(i) != listMenuDroid.getLocation()){
                    if(listMenuLocationVector.get(i).getSize() == listMenuLocationVector.get(i).countDroids()){
                        textarea = new Textarea();
                        textarea.createElement(listMenuLocationVector.get(i).getName(), 0, i+1, 1, 1, true, Color.LIGHT_GRAY);
                        addElement(textarea);
    
                        textarea = new Textarea();
                        textarea.createElement("Brak wolnej przestrzeni", 1, i+1, 1, 1, true, Color.RED);
                        addElement(textarea);
                    }
                    else{
                        buttons.add(new Button());
                        buttons.get(buttons.size()-1).createElement(listMenuLocationVector.get(i).getName(), 0, i+1, 1, 1, Color.LIGHT_GRAY);
                        buttons.get(buttons.size()-1).addInteraction(Interaction.DELEGATE_DROID);
                        buttons.get(buttons.size()-1).addInteraction(Interaction.CHANGE_MENU);
                        buttons.get(buttons.size()-1).getInteractions().get(0).setDroid(listMenuDroid);
                        buttons.get(buttons.size()-1).getInteractions().get(0).setLocation(listMenuLocationVector.get(i));
                        buttons.get(buttons.size()-1).getInteractions().get(1).setMenu(listMenuDroid.getMenu());
                        addElement(buttons.get(buttons.size()-1));
    
                        textarea = new Textarea();
                        if(listMenuLocationVector.get(i).countDroids() == 0){
                            textarea.createElement("Wolna przestrzeń: " + (listMenuLocationVector.get(i).getSize() - listMenuLocationVector.get(i).countDroids()), 1, i+1, 1, 1, true, Color.GREEN);
                        }
                        else{
                            textarea.createElement("Wolna przestrzeń: " + (listMenuLocationVector.get(i).getSize() - listMenuLocationVector.get(i).countDroids()), 1, i+1, 1, 1, true, Color.YELLOW);
                        }
                        addElement(textarea);
                    }
                }
            }

            exit.createElement("anuluj", 0, listMenuLocationVector.size() + 1, 2, 1, Color.RED);
            exit.addInteraction(Interaction.CHANGE_MENU);
            exit.getInteractions().get(0).setMenu(listMenuExitMenu);
            addElement(exit);
        }
        if(listMenuType == TASK_SELECT){
            clearMenu();

            title.createElement(getMenuName(), 0, 0, 1, 1, true, Color.CYAN);
            addElement(title);

            for(int i=0;i<listMenuIntVector.size();i++){
                if(listMenuDroid.getLocation().isTaskAvaiable(i)){
                    buttons.add(new Button());
                    buttons.get(buttons.size()-1).createElement(Droid.ORDER_NAME[listMenuIntVector.get(i)], 0, i+1, 1, 1, Color.LIGHT_GRAY);
                    buttons.get(buttons.size()-1).addInteraction(Interaction.CHANGE_DROID_TASK);
                    buttons.get(buttons.size()-1).addInteraction(Interaction.CHANGE_MENU);
                    buttons.get(buttons.size()-1).getInteractions().get(0).setDroid(listMenuDroid);
                    buttons.get(buttons.size()-1).getInteractions().get(0).setInt(i);
                    buttons.get(buttons.size()-1).getInteractions().get(1).setMenu(listMenuDroid.getMenu());
                    addElement(buttons.get(buttons.size()-1));
                }
            }

            exit.createElement("anuluj", 0, listMenuIntVector.size() + 1, 1, 1, Color.RED);
            exit.addInteraction(Interaction.CHANGE_MENU);
            exit.getInteractions().get(0).setMenu(listMenuExitMenu);
            addElement(exit);
        }
    }
}