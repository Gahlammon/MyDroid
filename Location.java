import java.util.Vector;

public class Location {
    private String locationName;
    private int locationSize;
    private Vector<Droid> locationDroids;
    private ListMenu locationMenu;
    private Menu locationExitMenu;
    private String locationImagePath;
    private Vector<Boolean> avaiableTasks;

    public void create(String name, int size, Menu exitMenu, String imagePath){
        locationName = name;
        locationSize = size;
        locationDroids = new Vector<Droid>();
        locationExitMenu = exitMenu;
        locationImagePath = imagePath;
        avaiableTasks = new Vector<Boolean>();
        for(int i=0;i<Droid.ORDERS;i++){
            if(i==0) avaiableTasks.add(true); else avaiableTasks.add(false);
        }

        createLocationMenu();
    }
    public void createLocationMenu(){
        locationMenu = new ListMenu();
        locationExitMenu.getEngine().addMenu(locationMenu);
        updateLocationMenu();
    }
    public void updateLocationMenu(){
        locationMenu.createEmptyMenu(locationName, locationExitMenu.getEngine(), locationDroids, locationExitMenu, ListMenu.LOCATION_MENU, this);
    }
    public void setTaskAvaiable(int task, boolean avaiable){
        avaiableTasks.set(task, avaiable);
    }
    public boolean isTaskAvaiable(int task){
        return avaiableTasks.get(task);
    }
    public String getName(){
        return locationName;
    }
    public String getImagePath(){
        return locationImagePath;
    }
    public ListMenu getMenu(){
        return locationMenu;
    }
    public int getSize(){
        return locationSize;
    }
    public void assignDroid(Droid droid){
        if(locationDroids.size() != locationSize){
            locationDroids.add(droid);
            if(!avaiableTasks.get(droid.getCurrentTask())){
                droid.setTask(Droid.WAITING_FOR_ORDERS);
            }
        }
        else System.err.println("Location you sended the droid to is full");
    }
    public void sendDroid(Droid droid, Location location){
        try{
            location.assignDroid(locationDroids.get(locationDroids.indexOf(droid)));
            locationDroids.remove(droid);
        }
        catch(Exception e){
            System.err.println("Droid is not in a location you sending it from");
        }
    }
    public void deleteDroid(Droid droid){
        locationDroids.remove(droid);
    }
    public boolean containDroid(Droid droid){
        return locationDroids.contains(droid);
    }
    public int countDroids(){
        return locationDroids.size();
    }
}