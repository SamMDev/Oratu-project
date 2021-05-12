package controller;

import model.Image;

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

        int modifiedPixelsInRow = 0;


        //if(bufferedImage.getRGB(xLocation, yLocation) != wantedColor) return 0;

        int colorOfTestedPixel;

        // tests pixel above
        try{
            colorOfTestedPixel = bufferedImage.getRGB(xLocation, yLocation - 1);
            if(colorOfTestedPixel == wantedColor){
                bufferedImage.setRGB(xLocation, yLocation - 1, newColor);
            }
        }catch (ArrayIndexOutOfBoundsException ignored){}

        // tests pixel next to right
        try{
            colorOfTestedPixel = bufferedImage.getRGB(xLocation + 1, yLocation);
            if(colorOfTestedPixel == wantedColor){
                bufferedImage.setRGB(xLocation + 1, yLocation, newColor);
                modifiedPixelsInRow++;
            }
        }catch (ArrayIndexOutOfBoundsException ignored){}

        // tests pixel below
        try{
            colorOfTestedPixel = bufferedImage.getRGB(xLocation, yLocation + 1);
            if(colorOfTestedPixel == wantedColor){
                bufferedImage.setRGB(xLocation, yLocation + 1, newColor);
            }
        }catch (ArrayIndexOutOfBoundsException ignored){}

        // tests pixel left
        try{
            colorOfTestedPixel = bufferedImage.getRGB(xLocation - 1, yLocation);
            if(colorOfTestedPixel == wantedColor){
                bufferedImage.setRGB(xLocation - 1, yLocation, newColor);
                modifiedPixelsInRow++;
            }
        }catch (ArrayIndexOutOfBoundsException ignored){}

        System.out.println(modifiedPixelsInRow);
        return modifiedPixelsInRow;

    }







    public void bucketFill(
            Integer xLocation,
            Integer yLocation)
    {

        int newRandomRgbBucketColor = (int)(Math.random() * 0x1000000);

        BufferedImage bufferedImageToEdit = getCurrentBufferedImage();

        int selectedPixelColor = bufferedImageToEdit.getRGB(xLocation, yLocation);

        // first pixel filled
        bufferedImageToEdit.setRGB(xLocation, yLocation, newRandomRgbBucketColor);


        // down from the point
        for(int rowIndex = yLocation; rowIndex < bufferedImageToEdit.getHeight(); rowIndex++){

            // right from the point
            for(int columnIndex = xLocation; columnIndex < bufferedImageToEdit.getWidth(); columnIndex++){
                int modifiedInARow = 0;
                modifiedInARow +=
                        testPixelsAround(
                        columnIndex,
                        rowIndex,
                        bufferedImageToEdit,
                        selectedPixelColor,
                        newRandomRgbBucketColor
                );

                if(modifiedInARow == 0) break;

            }

            // left from the point
            for(int columnIndex = xLocation - 1; columnIndex >= 0; columnIndex--){
                int modifiedInARow = 0;
                modifiedInARow +=
                testPixelsAround(
                        columnIndex,
                        rowIndex,
                        bufferedImageToEdit,
                        selectedPixelColor,
                        newRandomRgbBucketColor
                );

                if(modifiedInARow == 0) break;
            }

        }

        // up from the point
        for(int rowIndex = yLocation - 1; rowIndex >= 0; rowIndex--){
            // right from the point
            for(int columnIndex = xLocation; columnIndex < bufferedImageToEdit.getWidth(); columnIndex++){
                int modifiedInARow = 0;

                modifiedInARow +=
                testPixelsAround(
                        columnIndex,
                        rowIndex,
                        bufferedImageToEdit,
                        selectedPixelColor,
                        newRandomRgbBucketColor
                );

                if(modifiedInARow == 0) break;
            }

            // left from the point
            for(int columnIndex = xLocation - 1; columnIndex >= 0; columnIndex--){
                int modifiedInARow = 0;

                modifiedInARow +=
                testPixelsAround(
                        columnIndex,
                        rowIndex,
                        bufferedImageToEdit,
                        selectedPixelColor,
                        newRandomRgbBucketColor
                );

                if(modifiedInARow == 0) break;
            }

        }

        image.setBufferedImage(bufferedImageToEdit);

    }


}
