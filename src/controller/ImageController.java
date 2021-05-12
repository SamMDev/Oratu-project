package controller;

import model.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import static java.lang.Math.*;


/**
 * Provides communication between Image class and GUI
 *
 */
public class ImageController {

    private Image image = Image.getInstance();
    private static final double colorTolerance = 0.05;


    // Singleton
    private static final ImageController imageController = new ImageController();

    private ImageController(){}

    public static ImageController getInstance(){
        return imageController;
    }

    /**
     * If color difference is acceptable by percentage
     *
     * @param testedColor
     * @param mainColor
     * @return
     */
    public boolean isAcceptable(
            int testedColor,
            int mainColor
    ){
        Color color1 = new Color(testedColor);
        Color color2 = new Color(mainColor);

        double distanceBetweenColors = sqrt(
                pow((color2.getRed() - color1.getRed()) ,2) +
                pow((color2.getGreen() - color1.getGreen()),2) +
                pow((color2.getBlue() - color1.getBlue()), 2)
        );


        double percentage =
                distanceBetweenColors/
                        sqrt(
                                3*pow(255, 2)
                        );

        return percentage < colorTolerance;
    }

    /**
     * Gets buffered image from Image class
     *
     * @return  current buffered image
     */
    public BufferedImage getCurrentBufferedImage(){
        return image.getBufferedImage();
    }

    /**
     * With given pixel location, tests if pixel can fill the other
     * pixels around it
     *
     *
     * @param xLocation         pixel x location
     * @param yLocation         pixel x location
     * @param bufferedImage     on given image
     * @param wantedColor       color of pixels we want to spread a new color on
     * @param newColor          color that will be spreading
     * @return                  amount of pixels modified
     */
    public int testPixelsAround(
            int xLocation,
            int yLocation,
            BufferedImage bufferedImage,
            int wantedColor,
            int newColor
    ){
        // total amount of pixels given pixel coloured (min 0, max 4)
        int modifiedPixels = 0;

        // color of pixel to be tested (above, next to left, next to right or below)
        int colorOfTestedPixel;
        // tests pixel above
        try{
            colorOfTestedPixel = bufferedImage.getRGB(xLocation, yLocation - 1);
            if(colorOfTestedPixel == wantedColor){
                bufferedImage.setRGB(xLocation, yLocation - 1, newColor);
                modifiedPixels++;
            }
        }catch (ArrayIndexOutOfBoundsException ignored){}

        // tests pixel next to right
        try{
            colorOfTestedPixel = bufferedImage.getRGB(xLocation + 1, yLocation);
            if(colorOfTestedPixel == wantedColor){
                bufferedImage.setRGB(xLocation + 1, yLocation, newColor);
                modifiedPixels++;
            }
        }catch (ArrayIndexOutOfBoundsException ignored){}

        // tests pixel below
        try{
            colorOfTestedPixel = bufferedImage.getRGB(xLocation, yLocation + 1);
            if(colorOfTestedPixel == wantedColor){
                bufferedImage.setRGB(xLocation, yLocation + 1, newColor);
                modifiedPixels++;
            }
        }catch (ArrayIndexOutOfBoundsException ignored){}

        // tests pixel left
        try{
            colorOfTestedPixel = bufferedImage.getRGB(xLocation - 1, yLocation);
            if(colorOfTestedPixel == wantedColor){
                bufferedImage.setRGB(xLocation - 1, yLocation, newColor);
                modifiedPixels++;
            }
        }catch (ArrayIndexOutOfBoundsException ignored){}

        return modifiedPixels;

    }




    /**
     * Does bucket fill spreading from given location
     *
     * @param xLocation
     * @param yLocation
     */
    public void bucketFill(
            Integer xLocation,
            Integer yLocation)
    {
        // color to be filling the image
        int newRandomRgbBucketColor = new Color((int)(Math.random() * 0x1000000)).getRGB();

        // image
        BufferedImage bufferedImageToEdit = getCurrentBufferedImage();

        // color of a first pixel
        int selectedPixelColor = bufferedImageToEdit.getRGB(xLocation, yLocation);

        // first pixel filled
        bufferedImageToEdit.setRGB(xLocation, yLocation, newRandomRgbBucketColor);


        // how many image pixels have been modified for one loop trough image
        int numberOfNewPixelModifies;
        do{
            numberOfNewPixelModifies = 0;

            for(int rowIndex = 0; rowIndex < bufferedImageToEdit.getHeight(); rowIndex++){

                for(int columnIndex = 0; columnIndex < bufferedImageToEdit.getWidth(); columnIndex++){

                    // tested pixel color
                    int currentPixelColor = bufferedImageToEdit.getRGB(rowIndex, columnIndex);

                    // tested pixel color must be already painted to new color
                    // to spread the color
                    if(currentPixelColor == newRandomRgbBucketColor){
                        numberOfNewPixelModifies +=
                            testPixelsAround(
                                    rowIndex,
                                    columnIndex,
                                    bufferedImageToEdit,
                                    selectedPixelColor,
                                    newRandomRgbBucketColor
                            );
                    }

                }

            }

        }while(numberOfNewPixelModifies > 0);

        image.setBufferedImage(bufferedImageToEdit);

    }


}
