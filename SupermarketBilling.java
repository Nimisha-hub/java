import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SupermarketBilling extends JFrame implements ActionListener {
    private JTextField idField, nameField, priceField, quantityField, expDateField, mfgDateField;
    private JButton addButton, clearButton;
    private JTextArea billArea;
    private JCheckBox confirmPurchase;
    private double totalBill = 0.0;

    public SupermarketBilling() {
        setTitle("Supermarket Billing System");
        setSize(700, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 240, 240));

        // Title Label
        JLabel titleLabel = new JLabel("Supermarket Billing System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(50, 50, 150));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setPreferredSize(new Dimension(getWidth(), 50));
        add(titleLabel, BorderLayout.NORTH);

        // Center Panel - Form
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Form Fields
        idField = createTextField();
        nameField = createTextField();
        priceField = createTextField();
        quantityField = createTextField();
        expDateField = createTextField();
        mfgDateField = createTextField();

        addButton = createButton("Add to Bill", new Color(34, 167, 240));
        clearButton = createButton("Clear", new Color(200, 50, 50));

        confirmPurchase = new JCheckBox("Confirm Purchase");
        confirmPurchase.setFont(new Font("Arial", Font.BOLD, 14));
        confirmPurchase.setBackground(new Color(240, 240, 240));

        // Adding to GridBagLayout
        addToGrid(centerPanel, gbc, "Product ID:", idField, 0);
        addToGrid(centerPanel, gbc, "Product Name:", nameField, 1);
        addToGrid(centerPanel, gbc, "Price per Unit:", priceField, 2);
        addToGrid(centerPanel, gbc, "Quantity:", quantityField, 3);
        addToGrid(centerPanel, gbc, "Expiration Date (DD-MM-YYYY):", expDateField, 4);
        addToGrid(centerPanel, gbc, "Manufacture Date (DD-MM-YYYY):", mfgDateField, 5);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        centerPanel.add(confirmPurchase, gbc);

        // Adding Buttons with Proper Grid Reset
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        centerPanel.add(addButton, gbc);

        gbc.gridx = 1;
        centerPanel.add(clearButton, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // Bill Display Area
        billArea = new JTextArea();
        billArea.setEditable(false);
        billArea.setFont(new Font("Arial", Font.PLAIN, 14));
        billArea.setBackground(Color.WHITE);
        billArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        billArea.setText("------ Supermarket Bill ------\n");
        add(new JScrollPane(billArea), BorderLayout.SOUTH);

        // Button Actions
        addButton.addActionListener(this);
        clearButton.addActionListener(e -> {
            billArea.setText("------ Supermarket Bill ------\n");
            totalBill = 0.0;
        });
    }

    private JTextField createTextField() {
        JTextField field = new JTextField(15);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        return field;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        return button;
    }

    private void addToGrid(JPanel panel, GridBagConstraints gbc, String label, JTextField field, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (!confirmPurchase.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please confirm the purchase before adding to the bill!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());
            String expDate = expDateField.getText().trim();
            String mfgDate = mfgDateField.getText().trim();

            if (id.isEmpty() || name.isEmpty() || expDate.isEmpty() || mfgDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double totalPrice = price * quantity;
            totalBill += totalPrice;

            billArea.append(String.format("\nID: %s | Name: %s | Qty: %d | Price: %.2f | Total: %.2f\nExp: %s | Mfg: %s\n", 
                id, name, quantity, price, totalPrice, expDate, mfgDate));

            billArea.append("--------------------------------------------------\n");
            billArea.append(String.format("Current Total Bill: %.2f\n", totalBill));

            // Display Thank You Message
            billArea.append("\n\nTHANK YOU FOR SHOPPING!\n");
            billArea.setForeground(new Color(0, 128, 0)); // Green color for thank you message

            // Clear fields
            idField.setText("");
            nameField.setText("");
            priceField.setText("");
            quantityField.setText("");
            expDateField.setText("");
            mfgDateField.setText("");
            confirmPurchase.setSelected(false); // Reset checkbox

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for Price and Quantity!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SupermarketBilling().setVisible(true);
        });
    }
}
