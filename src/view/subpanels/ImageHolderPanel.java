package view.subpanels;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class ImageHolderPanel extends JPanel {

    private BufferedImage bufferedImage;
    private JLabel label;

    public ImageHolderPanel(
            BufferedImage bufferedImage
    ){
        this.bufferedImage = bufferedImage;

        this.label = new JLabel(new ImageIcon(bufferedImage));
        this.label.setHorizontalAlignment(SwingConstants.CENTER);
        this.label.setVerticalAlignment(SwingConstants.CENTER);
        this.label.setSize(
                bufferedImage.getWidth(),
                bufferedImage.getHeight());


        this.setBackground(Color.gray);

        this.setBounds(0, 60, 1000, 1000);
        this.setLayout(null);


        this.add(label);
        this.repaint();


        this.setVisible(true);
    }


    public void updateImage(
            BufferedImage bufferedImage
    ){
        this.label.setIcon(new ImageIcon(bufferedImage));
        repaint();
    }


}
