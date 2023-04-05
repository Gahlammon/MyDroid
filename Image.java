import java.awt.image.*;
import java.io.*;
import java.lang.String;
import javax.swing.*;

import javax.imageio.ImageIO;

public class Image extends MenuElement{
    private BufferedImage imageBuffered;
    private JLabel image;

    public void createElement(String path, int x, int y, int sizeX, int sizeY){
        setConstrains(x, y, sizeX, sizeY);

        try{
            imageBuffered = readImage(completePath(path));
            image = new JLabel(new ImageIcon(imageBuffered));
        }
        catch(IOException e){
            image = new JLabel("can't read file at: " + completePath(path));
        }

        setElement(image);
    }
    public BufferedImage readImage(String path) throws IOException{
        return ImageIO.read(new File(path));
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