import java.awt.Color;
import java.util.*; 

public class Main {
  public static void main(String[] args){
    Vector<Menu> menuVector = new Vector<Menu>();
    Vector<Droid> droidVector = new Vector<Droid>();
    Vector<Location> locationVector = new Vector<Location>();

    Engine mainEngine = new Engine();
    ListMenu droidList = new ListMenu();
    ListMenu locationList = new ListMenu();
    Menu createDroid = new Menu();
    Menu mainMenu = new Menu();

    Button button = new Button();
    Button okButton = new Button();
    Textarea titleTextarea = new Textarea();
    Textarea textarea = new Textarea();
    Image image = new Image();

    // Menu setup

    for(int i=0;i<18;i++){
      menuVector.add(new Menu());
    }

    // Main menu setup

    mainMenu.createEmptyMenu("APLIKACJA DO ZARZĄDZANIA DROIDAMI ASTROMECHANICZNYMI", mainEngine);
    image = new Image();
    image.createElement("images/LOGO.png", 0, 0, 1, 1);
    mainMenu.addElement(image);
    textarea = new Textarea();
    textarea.createElement("myDroid - APLIKACJA DO ZARZĄDZANIA DROIDAMI ASTROMECHANICZNYMI", 0, 1, 1, 1, true, Color.LIGHT_GRAY);
    mainMenu.addElement(textarea);
    button = new Button();
    button.createElement("lista droidów", 0, 2, 1, 1, Color.CYAN);
    button.addInteraction(Interaction.CHANGE_MENU);
    button.getInteractions().get(0).setMenu(droidList);
    mainMenu.addElement(button);
    button = new Button();
    button.createElement("dodaj droida", 0, 3, 1, 1, Color.GREEN);
    button.addInteraction(Interaction.CHANGE_MENU);
    button.getInteractions().get(0).setMenu(createDroid);
    mainMenu.addElement(button);
    button = new Button();
    button.createElement("lista lokacji", 0, 4, 1, 1, Color.CYAN);
    button.addInteraction(Interaction.CHANGE_MENU);
    button.getInteractions().get(0).setMenu(locationList);
    mainMenu.addElement(button);
    button = new Button();
    button.createElement("wyjdź", 0, 5, 1, 1, Color.RED);
    button.addInteraction(Interaction.DISABLE_ENGINE);
    mainMenu.addElement(button);

    mainEngine.addMenu(mainMenu);
    
    // Location vector setup

    for(int i=0;i<6;i++){
      locationVector.add(new Location());
    }

    // Location menu setup

    locationList.createEmptyMenu("Lokacje:", mainEngine, locationVector, mainMenu, ListMenu.LOCATION_LIST);
    mainEngine.addMenu(locationList);

    // Droid menu setup

    droidList.createEmptyMenu("Droidy:", mainEngine, droidVector, mainMenu, ListMenu.DROID_LIST);
    mainEngine.addMenu(droidList);

    // Location setup

    locationVector.get(0).create("Magazyn Droidów", 10, locationList, "images/magazine.png");
    locationVector.get(1).create("Zewnętrzna Studzienka Techniczna", 8, locationList, "images/top_hull.png");
    locationVector.get(1).setTaskAvaiable(Droid.FIXING_HYPERDRIVE, true);
    locationVector.get(1).setTaskAvaiable(Droid.REPAIRING_HULL_DAMAGE, true);
    locationVector.get(2).create("Dok Astromechaniczny 1", 1, locationList, "images/socket.png");
    locationVector.get(2).setTaskAvaiable(Droid.CONTROLLING_WEAPON_SYSTEMS, true);
    locationVector.get(2).setTaskAvaiable(Droid.REPAIRING_HULL_DAMAGE, true);
    locationVector.get(2).setTaskAvaiable(Droid.CONTROLLING_SHIP_SYSTEMS, true);
    locationVector.get(3).create("Dok Astromechaniczny 2", 1, locationList, "images/socket.png");
    locationVector.get(3).setTaskAvaiable(Droid.CONTROLLING_WEAPON_SYSTEMS, true);
    locationVector.get(3).setTaskAvaiable(Droid.REPAIRING_HULL_DAMAGE, true);
    locationVector.get(3).setTaskAvaiable(Droid.CONTROLLING_SHIP_SYSTEMS, true);
    locationVector.get(4).create("Kokpit", 3, locationList, "images/cockpit.png");
    locationVector.get(4).setTaskAvaiable(Droid.CONTROLLING_WEAPON_SYSTEMS, true);
    locationVector.get(4).setTaskAvaiable(Droid.CONTROLLING_SHIP_SYSTEMS, true);
    locationVector.get(4).setTaskAvaiable(Droid.PILOTING, true);
    locationVector.get(5).create("Poziomy Techniczne", 12, locationList, "images/interior.png");
    locationVector.get(5).setTaskAvaiable(Droid.FIXING_HYPERDRIVE, true);
    locationVector.get(5).setTaskAvaiable(Droid.CONTROLLING_SHIP_SYSTEMS, true);

    // Droid creating menu setup

    createDroid.createEmptyMenu("Tworzenie droida", mainEngine);
    textarea = new Textarea();
    textarea.createElement("nazwa:", 0, 0, 4, 1, true, Color.CYAN);
    createDroid.addElement(textarea);
    titleTextarea = new Textarea();
    titleTextarea.createElement("Nowy droid", 0, 1, 4, 1, false, Color.WHITE);
    createDroid.addElement(titleTextarea);
    textarea = new Textarea();
    textarea.createElement("wybór typu:", 0, 2, 3, 1, true, Color.CYAN);
    createDroid.addElement(textarea);
    textarea = new Textarea();
    textarea.createElement("R0", 3, 2, 1, 1, true, Color.LIGHT_GRAY);
    createDroid.addElement(textarea);  
    okButton = new Button();
    okButton.createElement("utwórz", 0, 5, 4, 1, Color.CYAN);
    okButton.addInteraction(Interaction.CHANGE_INTERACTION_STRING_TO_TEXTAREA_INPUT);
    okButton.addInteraction(Interaction.CREATE_DROID_FOR_DROID_VECTOR);
    okButton.addInteraction(Interaction.CHANGE_MENU);
    okButton.getInteractions().get(0).setTextarea(titleTextarea);
    okButton.getInteractions().get(0).setInteraction(okButton.getInteractions().get(1));
    okButton.getInteractions().get(1).setDroidVector(droidVector);
    okButton.getInteractions().get(1).setLocationVector(locationVector);
    okButton.getInteractions().get(1).setMenu(droidList);
    okButton.getInteractions().get(1).setLocation(locationVector.get(0));
    okButton.getInteractions().get(1).setInt(0);
    okButton.getInteractions().get(2).setMenu(droidList);
    createDroid.addElement(okButton);
    for(int i=0;i<2;i++){
      for(int j=0;j<4;j++){
        button = new Button();
        button.createElement(Droid.TYPE_NAME[i*4+j], j, i+3, 1, 1, Color.GREEN);
        button.addInteraction(Interaction.CHANGE_INTERACTION_INT);
        button.addInteraction(Interaction.CHANGE_TEXTAREA_TEXT);
        button.getInteractions().get(0).setInt(i*4+j);
        button.getInteractions().get(0).setInteraction(okButton.getInteractions().get(1));
        button.getInteractions().get(1).setString(Droid.TYPE_NAME[i*4+j]);
        button.getInteractions().get(1).setTextarea(textarea);;
        createDroid.addElement(button);
      }
    }
    button = new Button();
    button.createElement("anuluj", 0, 6, 4, 1, Color.RED);
    button.addInteraction(Interaction.CHANGE_MENU);
    button.getInteractions().get(0).setMenu(mainMenu);
    createDroid.addElement(button);
    mainEngine.addMenu(createDroid);

    // Main engine initialization

    mainEngine.setMain();
    mainEngine.setFilePath("droids.txt");
    mainEngine.assignDroidVector(droidVector);
    mainEngine.assignLocationVector(locationVector);
    mainEngine.loadDroidFile(droidList);
    droidList.updateContent();
    mainEngine.setCurrentMenu(mainMenu);
    mainEngine.activate();

    // Main loop

    while(mainEngine.isActive()){
      mainEngine.engineLopp();
    }

    // Quit

    System.exit(0);
  }
}