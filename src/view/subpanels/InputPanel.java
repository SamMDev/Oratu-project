package view.subpanels;

import lombok.Getter;

import javax.swing.*;

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
