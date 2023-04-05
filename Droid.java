import java.awt.Color;
import java.util.Vector;

public class Droid {
    public static final String[] TYPE_NAME = {"R0","R1","R2","R3","R4","R5","R6","BB"};
    public static final String[] TYPE_PATH = {"images/R0.png","images/R1.png","images/R2.png","images/R3.png","images/R4.png","images/R5.png","images/R6.png","images/BB.png"};

    public static final int WAITING_FOR_ORDERS = 0;
    public static final int FIXING_HYPERDRIVE = 1;
    public static final int CONTROLLING_WEAPON_SYSTEMS = 2;
    public static final int REPAIRING_HULL_DAMAGE = 3;
    public static final int PILOTING = 4;
    public static final int CONTROLLING_SHIP_SYSTEMS = 5;
    public static final String[] ORDER_NAME = {"oczekuje na polecenia","naprawia hipernapęd","obsługuje uzbrojenie","reperuje uszkodzenia kadłuba","pilotuje statek","nadzoruje systemy statku"};
    public static final int ORDERS = 6;

    private String droidName;
    private int droidType;
    private int droidCurrentTask;
    private Location droidLocation;
    private Menu droidMenu;
    private Menu droidExitMenu;
    private ListMenu droidSelectLocationMenu;
    private ListMenu droidSelectTaskMenu;
    private Vector<Location> droidAvaiableLocations;
    private int droidId;

    public void createDroid(String name, int type, Location location, Menu exitMenu, Vector<Location> avaiableLocations){
        droidName = name;
        droidType = type;
        droidCurrentTask = 0;
        droidLocation = location;
        location.assignDroid(this);
        droidExitMenu = exitMenu;
        droidAvaiableLocations = avaiableLocations;
        droidId = droidExitMenu.getEngine().getDroidId();

        createDroidMenu();
    }
    public void createDroidMenu(){
        droidMenu = new Menu();
        droidExitMenu.getEngine().addMenu(droidMenu);
        updateDroidMenu();
    }
    public void updateDroidMenu(){
        Textarea name = new Textarea();
        Textarea type = new Textarea();
        Textarea rename = new Textarea();
        Textarea textarea = new Textarea();
        Button renameButton = new Button();
        Button exitButton = new Button();
        Button button = new Button();
        Image image = new Image();
        Vector<Integer> intVector = new Vector<Integer>();

        droidMenu.createEmptyMenu(droidName, droidExitMenu.getEngine());

        image.createElement(TYPE_PATH[droidType], 2, 0, 1, 7);
        droidMenu.addElement(image);

        name.createElement(droidName, 0, 0, 2, 1, true, Color.CYAN);
        droidMenu.addElement(name);

        type.createElement(TYPE_NAME[droidType], 0, 1, 2, 1, true, Color.LIGHT_GRAY);
        droidMenu.addElement(type);

        rename.createElement("nowa nazwa", 0, 2, 1, 1, false, Color.WHITE);
        droidMenu.addElement(rename);

        renameButton.createElement("przenazwij", 1, 2, 1, 1, Color.CYAN);
        renameButton.addInteraction(Interaction.CHANGE_INTERACTION_STRING_TO_TEXTAREA_INPUT);
        renameButton.addInteraction(Interaction.CHANGE_DROID_NAME);
        renameButton.addInteraction(Interaction.CHANGE_INTERACTION_STRING_TO_TEXTAREA_INPUT);
        renameButton.addInteraction(Interaction.CHANGE_TEXTAREA_TEXT);
        renameButton.getInteractions().get(0).setInteraction(renameButton.getInteractions().get(1));
        renameButton.getInteractions().get(0).setTextarea(rename);
        renameButton.getInteractions().get(1).setDroid(this);
        renameButton.getInteractions().get(2).setInteraction(renameButton.getInteractions().get(3));
        renameButton.getInteractions().get(2).setTextarea(rename);
        renameButton.getInteractions().get(3).setTextarea(name);
        droidMenu.addElement(renameButton);

        textarea = new Textarea();
        textarea.createElement("obecna lokalizacja:", 0, 3, 1, 1, true, Color.CYAN);
        droidMenu.addElement(textarea);
        
        button = new Button();
        button.createElement(droidLocation.getName(), 0, 4, 1, 1, Color.WHITE);
        button.addInteraction(Interaction.CHANGE_MENU);
        button.getInteractions().get(0).setMenu(droidLocation.getMenu());
        droidMenu.addElement(button);

        droidSelectLocationMenu = new ListMenu();
        droidSelectLocationMenu.createEmptyMenu("Wybór lokalizacji", droidExitMenu.getEngine(), droidAvaiableLocations, droidMenu, ListMenu.LOCATION_SELECT, this);
        droidExitMenu.getEngine().addMenu(droidSelectLocationMenu);

        button = new Button();
        button.createElement("wyślij do...", 1, 3, 1, 2, Color.GREEN);
        button.addInteraction(Interaction.CHANGE_MENU);
        button.getInteractions().get(0).setMenu(droidSelectLocationMenu);
        droidMenu.addElement(button);

        textarea = new Textarea();
        textarea.createElement("obecne zadanie:", 0, 5, 1, 1, true, Color.CYAN);
        droidMenu.addElement(textarea);
        
        textarea = new Textarea();
        textarea.createElement(Droid.ORDER_NAME[droidCurrentTask], 0, 6, 1, 1, true, Color.LIGHT_GRAY);
        droidMenu.addElement(textarea);

        for(int i=0;i<Droid.ORDERS;i++){
            intVector.add(i);
        }
        droidSelectTaskMenu = new ListMenu();
        droidSelectTaskMenu.createEmptyMenu("Wybór polecenia", droidExitMenu.getEngine(), intVector, droidMenu, ListMenu.TASK_SELECT, this);
        droidExitMenu.getEngine().addMenu(droidSelectTaskMenu);

        button = new Button();
        button.createElement("wydaj polecenie...", 1, 5, 1, 2, Color.GREEN);
        button.addInteraction(Interaction.CHANGE_MENU);
        button.getInteractions().get(0).setMenu(droidSelectTaskMenu);
        droidMenu.addElement(button);

        exitButton.createElement("usuń droida", 2, 7, 1, 1, Color.RED);
        exitButton.addInteraction(Interaction.CHANGE_MENU);
        exitButton.addInteraction(Interaction.DELETE_DROID);
        exitButton.getInteractions().get(0).setMenu(droidExitMenu);
        exitButton.getInteractions().get(1).setDroid(this);
        exitButton.getInteractions().get(1).setDroidVector(droidExitMenu.getEngine().getDroidVector());
        droidMenu.addElement(exitButton);

        exitButton = new Button();
        exitButton.createElement("inne droidy", 0, 7, 2, 1, Color.RED);
        exitButton.addInteraction(Interaction.CHANGE_MENU);
        exitButton.getInteractions().get(0).setMenu(droidExitMenu);
        droidMenu.addElement(exitButton);
    }
    public void sendToLocation(Location location){
        droidLocation.sendDroid(this, location);
        droidLocation = location;
    }
    public void setTask(int task){
        droidCurrentTask = task;
    }
    public void setName(String name){
        droidName = name;
    }
    public String getName(){
        return droidName;
    }
    public int getType(){
        return droidType;
    }
    public int getCurrentTask(){
        return droidCurrentTask;
    }
    public Location getLocation(){
        return droidLocation;
    }
    public Menu getMenu(){
        return droidMenu;
    }
    public Menu getExitMenu(){
        return droidExitMenu;
    }
}