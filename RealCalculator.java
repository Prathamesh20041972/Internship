import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RealCalculator extends JFrame implements ActionListener {

    JTextField display;
    String operator = "";
    double num1 = 0, num2 = 0;

    RealCalculator() {
        setTitle("Real Calculator");
        setSize(350, 500);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        String[] buttons = {
                "C", "%", "/", "*",
                "7", "8", "9", "-",
                "4", "5", "6", "+",
                "1", "2", "3", "=",
                "0", ".", "", ""
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            display.setText(display.getText() + command);
        } 
        else if (command.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        } 
        else if (command.equals("C")) {
            display.setText("");
            num1 = num2 = 0;
            operator = "";
        } 
        else if (command.equals("+") || command.equals("-") 
                || command.equals("*") || command.equals("/") 
                || command.equals("%")) {

            num1 = Double.parseDouble(display.getText());
            operator = command;
            display.setText("");
        } 
        else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            double result = 0;

            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": 
                    if (num2 != 0)
                        result = num1 / num2;
                    else {
                        display.setText("Error");
                        return;
                    }
                    break;
                case "%": result = num1 % num2; break;
            }

            display.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        new RealCalculator();
    }
}
