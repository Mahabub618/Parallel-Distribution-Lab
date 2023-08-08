import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JTextField displayField;
    private String currentInput;

    public Calculator() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        currentInput = "";

        initializeComponents();
    }

    private void initializeComponents() {
        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.PLAIN, 24));
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "CE"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(new ButtonClickListener(label));
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        private String buttonLabel;

        public ButtonClickListener(String buttonLabel) {
            this.buttonLabel = buttonLabel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (buttonLabel) {
                case "C":
                    currentInput = "";
                    break;
                case "CE":
                    if (!currentInput.isEmpty()) {
                        currentInput = currentInput.substring(0, currentInput.length() - 1);
                    }
                    break;
                case "=":
                    calculateResult();
                    break;
                default:
                    currentInput += buttonLabel;
            }
            displayField.setText(currentInput);
        }

        private void calculateResult() {
            try {
                double result = evaluateExpression(currentInput);
                currentInput = String.valueOf(result);
            } catch (Exception ex) {
                currentInput = "Error";
            }
        }

        private double evaluateExpression(String expression) {
            try {
                return new ExtendedScriptEngine().eval(expression);
            } catch (Exception ex) {
                return Double.NaN;
            }
        }
    }

    private class ExtendedScriptEngine {
        private double eval(String expression) {
            try {
                String[] operands = expression.split("[+\\-*/]");
                double operand1 = Double.parseDouble(operands[0]);
                double operand2 = Double.parseDouble(operands[1]);
                char operator = expression.charAt(operands[0].length());

                switch (operator) {
                    case '+':
                        return operand1 + operand2;
                    case '-':
                        return operand1 - operand2;
                    case '*':
                        return operand1 * operand2;
                    case '/':
                        if (operand2 != 0) {
                            return operand1 / operand2;
                        } else {
                            throw new ArithmeticException("Division by zero");
                        }
                    default:
                        throw new IllegalArgumentException("Invalid operator");
                }
            } catch (Exception ex) {
                throw new IllegalArgumentException("Invalid expression");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}
