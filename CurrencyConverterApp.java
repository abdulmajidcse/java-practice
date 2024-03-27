import javax.swing.*;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.event.DocumentEvent;

public class CurrencyConverterApp extends JFrame {
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JTextField amountTextField;
    private JTextField resultTextField;
    // Fixed exchange rate (1 USD = 85 BDT)
    private JTextField exchangeRateField;
    // default exchange rate (you can change default value from here or app window)
    private String defaultExchangeRateValue = "109";

    public CurrencyConverterApp() {
        setTitle("Currency Converter");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1)); // Main layout with 5 rows

        // Panel for "exchange rate" components
        JPanel exchangeRatePanel = new JPanel(new FlowLayout());
        exchangeRatePanel.add(new JLabel("Exchange Rate (1 USD = ?):"));
        exchangeRateField = new JTextField(10);
        // set default value for exchange rate
        exchangeRateField.setText(defaultExchangeRateValue);
        // Add a document listener to the exchange rate text field to trigger conversion
        exchangeRateField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                convertCurrency();
            }

            public void removeUpdate(DocumentEvent e) {
                convertCurrency();
            }

            public void insertUpdate(DocumentEvent e) {
                convertCurrency();
            }
        });

        exchangeRatePanel.add(exchangeRateField);

        // Panel for "From Currency" components
        JPanel fromCurrencyPanel = new JPanel(new FlowLayout());
        fromCurrencyPanel.add(new JLabel("From Currency:"));
        fromCurrencyComboBox = new JComboBox<>(new String[] { "USD", "BDT" });
        fromCurrencyComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToCurrency();
                convertCurrency();
            }
        });
        fromCurrencyPanel.add(fromCurrencyComboBox);

        // Panel for "To Currency" components
        JPanel toCurrencyPanel = new JPanel(new FlowLayout());
        toCurrencyPanel.add(new JLabel("To Currency:"));
        toCurrencyComboBox = new JComboBox<>(new String[] { "BDT", "USD" });
        toCurrencyComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateFromCurrency();
                convertCurrency();
            }
        });
        toCurrencyPanel.add(toCurrencyComboBox);

        // Panel for "Amount" components
        JPanel amountPanel = new JPanel(new FlowLayout());
        amountPanel.add(new JLabel("Amount:"));
        amountTextField = new JTextField(10);
        // Add a document listener to the amount text field to trigger conversion
        amountTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                convertCurrency();
            }

            public void removeUpdate(DocumentEvent e) {
                convertCurrency();
            }

            public void insertUpdate(DocumentEvent e) {
                convertCurrency();
            }
        });

        amountPanel.add(amountTextField);

        // Panel for "Result" components
        JPanel resultPanel = new JPanel(new FlowLayout());
        resultPanel.add(new JLabel("Result:"));
        resultTextField = new JTextField(10);
        resultTextField.setEditable(false); // Result field should not be editable
        resultPanel.add(resultTextField);

        // Add nested panels to the frame
        add(exchangeRatePanel);
        add(fromCurrencyPanel);
        add(amountPanel);
        add(toCurrencyPanel);
        add(resultPanel);

        setVisible(true);
    }

    private void updateToCurrency() {
        String fromCurrency = fromCurrencyComboBox.getSelectedItem().toString();
        String toCurrency = (fromCurrency.equals("BDT")) ? "USD" : "BDT";
        toCurrencyComboBox.setSelectedItem(toCurrency);
    }

    private void updateFromCurrency() {
        String toCurrency = toCurrencyComboBox.getSelectedItem().toString();
        String fromCurrency = (toCurrency.equals("BDT")) ? "USD" : "BDT";
        fromCurrencyComboBox.setSelectedItem(fromCurrency);
    }

    private void convertCurrency() {
        try {
            String fromCurrency = fromCurrencyComboBox.getSelectedItem().toString();
            String toCurrency = toCurrencyComboBox.getSelectedItem().toString();
            String exchangeRateText = exchangeRateField.getText();
            String amountText = amountTextField.getText();

            // Check if exchange rate text is not empty
            if (exchangeRateText.isEmpty()) {
                resultTextField.setText("0"); // Clear result field if exchange rate field is empty
                return;
            }

            // Parse exchange rate as double
            double exchangeRate = Double.parseDouble(exchangeRateText);

            // Check if amount text is not empty
            if (amountText.isEmpty()) {
                resultTextField.setText("0"); // Clear result field if amount field is empty
                return;
            }

            // Parse amount as double
            double amount = Double.parseDouble(amountText);

            // Perform currency conversion
            double result;
            if (fromCurrency.equals("BDT") && toCurrency.equals("USD")) {
                result = amount / exchangeRate;
            } else if (fromCurrency.equals("USD") && toCurrency.equals("BDT")) {
                result = amount * exchangeRate;
            } else {
                // Conversion not supported
                JOptionPane.showMessageDialog(null, "Conversion not supported.");
                resultTextField.setText(""); // Clear result field
                return;
            }

            // Format the result to display two digits after the decimal point
            DecimalFormat df = new DecimalFormat("#.##");
            String formattedResult = df.format(result);

            // Display formatted result
            resultTextField.setText(formattedResult);
        } catch (Exception e) {
            // Display formatted result
            resultTextField.setText("0");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyConverterApp::new);
    }
}
