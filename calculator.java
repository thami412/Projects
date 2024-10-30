/*
 * Travis Hamilton
 * CIT-130-Z01
 * 17 July 2024
 * GUI Calculator Assignment
 */
package OOP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calculator extends JFrame implements ActionListener {
    private JTextField display;
    private double firstNumber = 0, secondNumber = 0, result = 0;
    private char operator;

    public calculator() {
        // Create a new invisible JFrame with a title
        super("Example Calculator");

        // Set size, default close operation, and layout
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        // Make panels for each row and set colors
        JPanel r1 = new JPanel();
        r1.setBackground(Color.RED);
        JPanel r2 = new JPanel();
        r2.setBackground(Color.GREEN);
        JPanel r3 = new JPanel();
        r3.setBackground(Color.BLUE);
        JPanel r4 = new JPanel();
        r4.setBackground(Color.YELLOW);
        JPanel r5 = new JPanel();
        r5.setBackground(Color.CYAN);

        // Create all buttons
        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");
        JButton b7 = new JButton("7");
        JButton b8 = new JButton("8");
        JButton b9 = new JButton("9");
        JButton b0 = new JButton("0");
        JButton plus = new JButton("+");
        JButton minus = new JButton("-");
        JButton divide = new JButton("/");
        JButton multiply = new JButton("*");
        JButton clear = new JButton("C");
        JButton equals = new JButton("=");

        // Set up row of button panel layouts
        r1.setLayout(new GridLayout(1, 1)); // only gets JTextField
        r2.setLayout(new GridLayout(1, 4)); // each panel gets 4 buttons
        r3.setLayout(new GridLayout(1, 4));
        r4.setLayout(new GridLayout(1, 4));
        r5.setLayout(new GridLayout(1, 4));

        // Add the JTextField to row 1
        display = new JTextField();
        display.setEditable(false); // Make the display non-editable
        r1.add(display);

        // Add the buttons
        // Row 2
        r2.add(b1);
        r2.add(b2);
        r2.add(b3);
        r2.add(plus);

        // Row 3
        r3.add(b4);
        r3.add(b5);
        r3.add(b6);
        r3.add(minus);

        // Row 4
        r4.add(b7);
        r4.add(b8);
        r4.add(b9);
        r4.add(divide);

        // Row 5
        r5.add(clear);
        r5.add(b0);
        r5.add(equals);
        r5.add(multiply);

        // Add panels to the JFrame
        add(r1);
        add(r2);
        add(r3);
        add(r4);
        add(r5);

        // Add action listeners
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b0.addActionListener(this);
        plus.addActionListener(this);
        minus.addActionListener(this);
        divide.addActionListener(this);
        multiply.addActionListener(this);
        clear.addActionListener(this);
        equals.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            display.setText(display.getText() + command);
        } else if (command.charAt(0) == 'C') {
            display.setText("");
            firstNumber = secondNumber = result = 0;
            operator = '\0';
        } else if (command.charAt(0) == '=') {
            secondNumber = Double.parseDouble(display.getText());
            switch (operator) {
                case '+':
                    result = firstNumber + secondNumber;
                    break;
                case '-':
                    result = firstNumber - secondNumber;
                    break;
                case '*':
                    result = firstNumber * secondNumber;
                    break;
                case '/':
                    result = firstNumber / secondNumber;
                    break;
            }
            display.setText("" + result);
            firstNumber = result;
        } else {
            if (!display.getText().isEmpty()) {
                firstNumber = Double.parseDouble(display.getText());
                display.setText("");
                operator = command.charAt(0);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            calculator calculator = new calculator();
            calculator.setVisible(true);
        });
    }
}