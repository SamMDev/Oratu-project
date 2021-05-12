package view;

import controller.ImageController;
import view.subpanels.ImageHolderPanel;
import view.subpanels.InputPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    private InputPanel inputPanel;
    private ImageHolderPanel imageHolderPanel;
    private JButton processButton;

    private final ImageController imageController = ImageController.getInstance();

    public MainFrame(){

        this.setTitle("Oratu Project");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        this.setLayout(null);


        inputPanel = new InputPanel();

        imageHolderPanel = new ImageHolderPanel(
                imageController.getCurrentBufferedImage()
        );

        processButton = new JButton();
        processButton.setBounds(900, 60, 100, 30);
        processButton.addActionListener(this);
        processButton.setText("Process");

        this.add(processButton);
        this.add(inputPanel);
        this.add(imageHolderPanel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Integer xLocation = Integer.parseInt(
                inputPanel.getXLocationTextField().getText()
        );

        Integer yLocation = Integer.parseInt(
                inputPanel.getYLocationTextField().getText()
        );

        imageController.bucketFill(
                xLocation,
                yLocation);

        imageHolderPanel.updateImage(
                imageController.getCurrentBufferedImage()
        );
    }
}
