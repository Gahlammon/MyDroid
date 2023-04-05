import java.util.Vector;

public class Interaction {
    public static final int SHUT_DOWN_ENGINE = 0;
    public static final int DISABLE_ENGINE = 1;
    public static final int CHANGE_MENU = 2;
    public static final int CHANGE_TEXTAREA_TEXT = 3;
    public static final int CHANGE_INTERACTION_STRING_TO_TEXTAREA_INPUT = 4;
    public static final int CHANGE_BUTTON_NAME = 5;
    public static final int CHANGE_DROID_NAME = 6;
    public static final int CREATE_DROID_FOR_DROID_VECTOR = 7;
    public static final int DELEGATE_DROID = 8;
    public static final int CHANGE_DROID_TASK = 9;
    public static final int CHANGE_INTERACTION_INT = 10;
    public static final int DELETE_DROID = 11;

    private MenuElement interactionElement;
    private int operation;
    private int connectedInt;
    private String connectedString;
    private Menu connectedMenu;
    private Textarea connectedTextarea;
    private Button connectedButton;
    private Interaction connectedInteraction;
    private Droid connectedDroid;
    private Location connectedLocation;
    private Vector<Droid> connectedDroidVector;
    private Vector<Location> connectedLocationVector;

    public void setOperation(int number){
        operation = number;
    }
    public void setInt(int connection){
        connectedInt = connection;
    }
    public void setString(String connection){
        connectedString = connection;
    }
    public void setMenu(Menu connection){
        connectedMenu = connection;
    }
    public void setTextarea(Textarea connection){
        connectedTextarea = connection;
    }
    public void setButton(Button connection){
        connectedButton = connection;
    }
    public void setInteraction(Interaction connection){
        connectedInteraction = connection;
    }
    public void setDroid(Droid connection){
        connectedDroid = connection;
    }
    public void setLocation(Location connection){
        connectedLocation = connection;
    }
    public void setDroidVector(Vector<Droid> connection){
        connectedDroidVector = connection;
    }
    public void setLocationVector(Vector<Location> connection){
        connectedLocationVector = connection;
    }
    public void assignElement(MenuElement element){
        interactionElement = element;
    }
    public void performInteraction(){
        if(operation == SHUT_DOWN_ENGINE){ //
            interactionElement.getMenu().deactivateDisplay();
            interactionElement.getMenu().getEngine().deactivate();
        }
        if(operation == DISABLE_ENGINE){ //
            interactionElement.getMenu().getEngine().saveDroidFile();
            interactionElement.getMenu().deactivateDisplay();
            interactionElement.getMenu().getEngine().deactivate();
        }
        if(operation == CHANGE_MENU){ // MENU
            interactionElement.getMenu().deactivateDisplay();
            interactionElement.getMenu().getEngine().setCurrentMenu(connectedMenu);
        }
        if(operation == CHANGE_TEXTAREA_TEXT){ // STRING, TEXTAREA
            connectedTextarea.setText(connectedString);
        }
        if(operation == CHANGE_INTERACTION_STRING_TO_TEXTAREA_INPUT){ // TEXTAREA, INTERACTION
            connectedInteraction.setString(connectedTextarea.getText());
        }
        if(operation == CHANGE_BUTTON_NAME){ // BUTTON, INTERACTION
            connectedButton.setName(connectedString);
        }
        if(operation == CHANGE_DROID_NAME){ // DROID, STRING
            connectedDroid.setName(connectedString);
        }
        if(operation == CREATE_DROID_FOR_DROID_VECTOR){ // VECTOR<DROID>, VECTOR<LOCATION>, INT, STRING, MENU, LOCATION
            if(connectedLocation.countDroids() != connectedLocation.getSize()){
                Droid droid = new Droid();
                droid.createDroid(connectedString, connectedInt, connectedLocation, connectedMenu, connectedLocationVector);
                connectedDroidVector.add(droid);
            }
            else System.err.println("Unable to create the droid because the start location is full");
        }
        if(operation == DELEGATE_DROID){ // DROID, LOCATION
            connectedDroid.sendToLocation(connectedLocation);
            connectedDroid.updateDroidMenu();
        }
        if(operation == CHANGE_DROID_TASK){ // DROID, INT
            connectedDroid.setTask(connectedInt);
            connectedDroid.updateDroidMenu();
        }
        if(operation == CHANGE_INTERACTION_INT){ // INTERACTION, INT
            connectedInteraction.setInt(connectedInt);
        }
        if(operation == DELETE_DROID){ // DROID, VECTOR<DROID>
            connectedDroidVector.remove(connectedDroid);
            connectedDroid.getLocation().deleteDroid(connectedDroid);
            connectedDroid.getLocation().updateLocationMenu();
            connectedDroid.updateDroidMenu();
            connectedDroid.getExitMenu().updateContent();
        }
    }
}