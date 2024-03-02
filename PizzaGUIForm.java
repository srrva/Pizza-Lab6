import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PizzaGUIForm extends JFrame {

    JPanel mainPanel;
    JPanel crustPanel;
    JPanel sizePanel;
    JPanel toppingsPanel;
    JPanel orderPanel;
    JTextArea orderTextArea;

    JRadioButton thinCrustRB;
    JRadioButton regularCrustRB;
    JRadioButton deepDishCrustRB;
    JPanel radioPnl;

    JComboBox<String> sizeComboBox;

    JCheckBox spiderCheckBox;
    JCheckBox fingersCheckBox;
    JCheckBox hanselngretelCheckBox;
    JCheckBox tailCheckBox;
    JCheckBox shipsCheckBox;
    JCheckBox brainsCheckBox;

    JButton orderButton;
    JButton clearButton;
    JButton quitButton;

    public PizzaGUIForm(){
        mainPanel = new JPanel();
        crustPanel = new JPanel();
        createCrustPanel();
        mainPanel.setLayout(new GridLayout(6,1));

        mainPanel.add(radioPnl);
        createCrustPanel();
       // mainPanel.add(crustPanel);


        createSizePanel();
        mainPanel.add(sizePanel);

        createToppingsPanel();
        mainPanel.add(toppingsPanel);

        createOrderPanel();
        mainPanel.add(orderPanel);

        createButtonPanel();
        mainPanel.add(createButtonPanel());

        setContentPane(mainPanel);

        setSize(400, 900);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createCrustPanel() {
        radioPnl = new JPanel();
        radioPnl.setBorder(new TitledBorder(new EtchedBorder(), "Crust Size"));

        thinCrustRB = new JRadioButton("Thin");
        regularCrustRB = new JRadioButton("Regular");
        deepDishCrustRB = new JRadioButton("Deep Dish");

        ButtonGroup crustGroup = new ButtonGroup();
        crustGroup.add(thinCrustRB);
        crustGroup.add(regularCrustRB);
        crustGroup.add(deepDishCrustRB);

        radioPnl.add(thinCrustRB);
        radioPnl.add(regularCrustRB);
        radioPnl.add(deepDishCrustRB);
    }

    private void createSizePanel() {
        sizePanel = new JPanel();
        sizePanel.setBorder(new TitledBorder(new EtchedBorder(),"Size"));

        String[] sizes = {"Small", "Medium", "Large", "Super"};
        sizeComboBox = new JComboBox<>(sizes);

        sizePanel.add(sizeComboBox);
    }

    private void createToppingsPanel() {
        toppingsPanel = new JPanel();
        toppingsPanel.setBorder(new TitledBorder(new EtchedBorder(),"Toppings"));

        spiderCheckBox = new JCheckBox("Spiders");
        fingersCheckBox = new JCheckBox("Human Fingers");
        hanselngretelCheckBox = new JCheckBox("Hansel and Gretel");
        tailCheckBox = new JCheckBox("Rattle Snake Tail");
        shipsCheckBox = new JCheckBox("Ships");
        brainsCheckBox = new JCheckBox("Brains");

        toppingsPanel.add(spiderCheckBox);
        toppingsPanel.add(fingersCheckBox);
        toppingsPanel.add(hanselngretelCheckBox);
        toppingsPanel.add(tailCheckBox);
        toppingsPanel.add(shipsCheckBox);
        toppingsPanel.add(brainsCheckBox);
    }

    private void createOrderPanel() {
        orderPanel = new JPanel();
        orderPanel.setBorder(new TitledBorder(new EtchedBorder(),"Order Details"));

        orderTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(orderTextArea);

        orderPanel.add(scrollPane);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new TitledBorder(new EtchedBorder(),"Actions"));

        orderButton = new JButton("Order");
        clearButton = new JButton("Clear");
        quitButton = new JButton("Quit");

        orderButton.addActionListener(this::orderPizza);
        clearButton.addActionListener(this::clearForm);
        quitButton.addActionListener(this::quitApplication);

        buttonPanel.add(orderButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);

        return buttonPanel;
    }

    private void clearForm(ActionEvent e) {
        // Clear radio button selection for crust type
        ButtonGroup crustGroup = new ButtonGroup();
        crustGroup.add(thinCrustRB);
        crustGroup.add(regularCrustRB);
        crustGroup.add(deepDishCrustRB);
        crustGroup.clearSelection();

        // Reset combo box selection for size
        sizeComboBox.setSelectedIndex(0);


        // Clear checkbox selection for toppings
        spiderCheckBox.setSelected(false);
        fingersCheckBox.setSelected(false);
        hanselngretelCheckBox.setSelected(false);
        tailCheckBox.setSelected(false);
        shipsCheckBox.setSelected(false);
        brainsCheckBox.setSelected(false);

        // Clear the orderTextArea
        orderTextArea.setText("");
    }

    private void orderPizza(ActionEvent e) {
        //set prices
        double basePrice = 0.0;
        String size = (String) sizeComboBox.getSelectedItem();
        switch (size) {
            case "Small":
                basePrice = 8.00;
                break;
            case "Medium":
                basePrice = 12.00;
                break;
            case "Large":
                basePrice = 16.00;
                break;
            case "Super":
                basePrice = 20.00;
                break;
        }
        // I made crust type cost 0.00, to say it is included with purchase of pizza!

        String crustType = "";
        if (thinCrustRB.isSelected()){
            crustType = "Thin";
        }
        if (regularCrustRB.isSelected()) {
            crustType = "Regular";
        }
        if (deepDishCrustRB.isSelected()) {
            crustType = "Deep Dish";
        }
        switch (crustType) {
            case "Thin":
                basePrice = 0.00;
                break;
            case "Regular":
                basePrice = 0.00;
                break;
            case "Deep Dish":
                basePrice = 0.00;
                break;
        }

        int toppingCount = 0;
        if (spiderCheckBox.isSelected()) toppingCount++;
        if (fingersCheckBox.isSelected()) toppingCount++;
        if (hanselngretelCheckBox.isSelected()) toppingCount++;
        if (tailCheckBox.isSelected()) toppingCount++;
        if (shipsCheckBox.isSelected()) toppingCount++;
        if (brainsCheckBox.isSelected()) toppingCount++;

        double toppingPrice = toppingCount * 1.00;

        double subTotal = basePrice + toppingPrice;

        // Construct the receipt
        StringBuilder receipt = new StringBuilder();
        receipt.append("============================================\n");
        receipt.append("Crust Type & Size               Price\n");
        switch (size) {
            case "Small":
                receipt.append(String.format("%-38s $%.2f\n", "Small", 8.00));
                break;
            case "Medium":
                receipt.append(String.format("%-35s $%.2f\n", "Medium", 12.00));
                break;
            case "Large":
                receipt.append(String.format("%-37s $%.2f\n", "Large", 16.00));
                break;
            case "Super":
                receipt.append(String.format("%-37s $%.2f\n", "Super", 20.00));
                break;
        }
        switch (crustType) {
            case "Thin":
                receipt.append(String.format("%-38s $%.2f\n", "Thin", 0.00));
                break;
            case "Regular":
                receipt.append(String.format("%-37s $%.2f\n", "Regular", 0.00));
                break;
            case "Deep Dish":
                receipt.append(String.format("%-33s $%.2f\n", "Deep Dish", 0.00));
                break;
        }
        receipt.append("Ingredient(s)\n");
        if (spiderCheckBox.isSelected()) {
            receipt.append(String.format("%-36s $%.2f\n", "Spiders", 1.00));
        }
        if (fingersCheckBox.isSelected()) {
            receipt.append(String.format("%-30s $%.2f\n", "Human Fingers", 1.00));
        }
        if (hanselngretelCheckBox.isSelected()) {
            receipt.append(String.format("%-30s $%.2f\n", "Hansel and Gretel", 1.00));
        }
        if (tailCheckBox.isSelected()) {
            receipt.append(String.format("%-32s $%.2f\n", "Rattle Snake Tail", 1.00));
        }
        if (shipsCheckBox.isSelected()) {
            receipt.append(String.format("%-37s $%.2f\n", "Ships", 1.00));
        }
        if (brainsCheckBox.isSelected()) {
            receipt.append(String.format("%-31s $%.2f\n", "Human Brains", 1.00));
        }
        receipt.append("--------------------------------------------\n");
        receipt.append(String.format("%-35s $%.2f\n", "Sub-total:", subTotal));
        receipt.append(String.format("%-36s $%.2f\n", "Tax (7%):", subTotal * 0.07));
        receipt.append("--------------------------------------------\n");
        receipt.append(String.format("%-38s $%.2f\n", "Total:", subTotal * 1.07));
        receipt.append("============================================\n\n");

// Display the receipt in the JTextArea
        orderTextArea.setText(receipt.toString());

    }

    private void quitApplication(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(mainPanel, "Are you sure you want to quit?", "Quit",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}