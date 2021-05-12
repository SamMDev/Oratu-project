package view.subpanels;

import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
public class InputPanel extends JPanel {

    private JTextField xLocationTextField;
    private JTextField yLocationTextField;

    public InputPanel(){

        this.setBounds(0, 0, 1000, 60);
        this.setLayout(null);

        xLocationTextField = new JTextField();
        xLocationTextField.setBounds(0, 0, 900, 30);

        yLocationTextField = new JTextField();
        yLocationTextField.setBounds(0, 30, 900, 30);


        this.add(xLocationTextField);
        this.add(yLocationTextField);

        this.setVisible(true);
    }
}
