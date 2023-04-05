import java.util.*; 
import java.nio.file.Files;

import java.nio.file.*;
import java.io.*;

public class Engine {
    private int currentMenu;
    private Vector<Menu> engineMenuVector = new Vector<Menu>();
    private Vector<Droid> engineDroidVector = new Vector<Droid>();
    private Vector<Location> engineLocationVector;
    private boolean activated = false;
    private boolean mainEngine = false;
    private boolean enginePause = false;
    private int engineDroidId = -1;
    
    private InputStream engineInput;
    private FileWriter engineOutput;
    private BufferedReader engineReader;
    private BufferedWriter engineWriter;
    private String engineFilePath;

    public void setCurrentMenu(int number){
        if(number < engineMenuVector.size()) { currentMenu = number; }
    }
    public void setCurrentMenu(Menu menu){
        if(engineMenuVector.indexOf(menu)!=-1){
            currentMenu = engineMenuVector.indexOf(menu);
        }
        else System.err.println("Menu you try to make the current menu of the engine is not a part of the engine");
    }
    public void assignMenuVector(Vector<Menu> vector){
        engineMenuVector = vector;
    }
    public void setMenu(int number, Menu menu){
        if(number < engineMenuVector.size()){ engineMenuVector.set(number,menu); }
    }
    public void addMenu(Menu menu){
        engineMenuVector.add(menu);
    }
    public Vector<Menu> getMenuVector(){
        return engineMenuVector;
    }
    public int getDroidId(){
        engineDroidId++;
        return(engineDroidId);
    }
    public Vector<Droid> getDroidVector(){
        return engineDroidVector;
    }
    public void activate(){
        activated = true;
    }
    public void deactivate(){
        activated = false;
    }
    public boolean isActive(){
        return activated;
    }
    public void setMain(){
        mainEngine = true;
    }
    public boolean isMainEngine(){
        return mainEngine;
    }
    public void pauseLoop(boolean pause){
        enginePause = pause;
    }
    public void engineLopp(){
        if(!engineMenuVector.get(currentMenu).isDisplayed()&&!enginePause){
            engineMenuVector.get(currentMenu).activateDisplay();
        }
    }
    public void setFilePath(String path){
        engineFilePath = path;
    }
    public void assignDroidVector(Vector<Droid> vector){
        engineDroidVector = vector;
    }
    public void assignLocationVector(Vector<Location> vector){
        engineLocationVector = vector;
    }
    public void loadDroidFile(Menu droidExitMenu){
        Vector<String> reader = new Vector<String>();

        for(int i=0;i<4;i++){
            reader.add(new String());
        }
        try{
            engineInput = Files.newInputStream(Paths.get(completePath(engineFilePath)));
            engineReader = new BufferedReader(new InputStreamReader(engineInput));
            reader.set(0, engineReader.readLine());
            while(reader.get(0) != null){
                engineDroidVector.add(new Droid());
                reader.set(1,engineReader.readLine());
                reader.set(2,engineReader.readLine());
                reader.set(3,engineReader.readLine());
                engineDroidVector.lastElement().createDroid(reader.get(0), Integer.parseInt(reader.get(1)), engineLocationVector.get(Integer.parseInt(reader.get(2))), droidExitMenu, engineLocationVector);
                engineDroidVector.lastElement().setTask(Integer.parseInt(reader.get(3)));
                engineDroidVector.lastElement().updateDroidMenu();
                reader.set(0,engineReader.readLine());
            }
        }
        catch (IOException x) {
            System.err.println("There's no droid input file in " + completePath(engineFilePath) + "- creating empty droid base. New file will be created after this session");
        }
    }
    public void saveDroidFile(){
        try{
            engineOutput = new FileWriter(new File(completePath(engineFilePath)));
            engineWriter = new BufferedWriter(engineOutput);
            for(int i=0;i<engineDroidVector.size();i++){
                engineWriter.write(engineDroidVector.get(i).getName() + "\n");
                engineWriter.write(engineDroidVector.get(i).getType() + "\n");
                engineWriter.write(engineLocationVector.indexOf(engineDroidVector.get(i).getLocation()) + "\n");
                engineWriter.write(engineDroidVector.get(i).getCurrentTask() + "\n");
            }  
            engineWriter.close();
        }
        catch (IOException e){
            System.err.println("Something went wrong while writing a file to " + completePath(engineFilePath) + ", this session was probably not saved");
        }
    }
    public String completePath(String path){
        String imagePath;

        imagePath = "" + Image.class.getClassLoader().getResource("");
        imagePath = imagePath.substring(6);
        for(int i=0;i<imagePath.length()-2;i++){
            if(imagePath.charAt(i) == '%' && imagePath.charAt(i+1) == '2' && imagePath.charAt(i+2) == '0'){
                imagePath = imagePath.substring(0, i) + " " + imagePath.substring(i+3);
            }
        }
        imagePath = imagePath + path;

        return imagePath;
    }
}