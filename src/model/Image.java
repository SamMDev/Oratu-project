package model;

import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Getter
@Setter
public class Image extends JPanel {

    private BufferedImage bufferedImage;

    // Singleton
    private static final Image image = new Image();
    public static Image getInstance(){
        return image;
    }
    private Image() {
        try{
            bufferedImage = ImageIO.read(new File("image.bmp"));
        }catch (IOException e){
            System.err.println();
        }
    }


}
