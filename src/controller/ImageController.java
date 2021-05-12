package controller;

import model.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageController {

    private Image image = Image.getInstance();


    private static final ImageController imageController = new ImageController();

    private ImageController(){}

    public static ImageController getInstance(){
        return imageController;
    }


    public BufferedImage getCurrentBufferedImage(){
        return image.getBufferedImage();
    }

    public int testPixelsAround(
            int xLocation,
            int yLocation,
            BufferedImage bufferedImage,
            int wantedColor,
            int newColor
    ){

        int modifiedPixels = 0;

        // if(bufferedImage.getRGB(xLocation, yLocation) != newColor) return 0;



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


        int numberOfNewPixelModifies;
        do{
            numberOfNewPixelModifies = 0;

            for(int rowIndex = 0; rowIndex < bufferedImageToEdit.getHeight(); rowIndex++){

                for(int columnIndex = 0; columnIndex < bufferedImageToEdit.getWidth(); columnIndex++){


                    int currentPixelColor = bufferedImageToEdit.getRGB(rowIndex, columnIndex);

                    if(rowIndex == xLocation || columnIndex == yLocation){
                        System.out.println(currentPixelColor == newRandomRgbBucketColor);
                    }

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
