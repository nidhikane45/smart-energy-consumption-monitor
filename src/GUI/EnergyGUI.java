package GUI;

import javax.swing.*;
import java.awt.*;

import hashtable.HashTableApplianceStorage;
import Heap.MaxHeap;
import BST.EnergyBST;
import common.Appliance;

public class EnergyGUI {

    public static void main(String[] args) {

        HashTableApplianceStorage table = new HashTableApplianceStorage(20);
        MaxHeap heap = new MaxHeap(20);
        EnergyBST bst = new EnergyBST();

        JFrame frame = new JFrame("Digital Energy Monitor");
frame.setSize(900, 550);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setLayout(new BorderLayout());


JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
mainPanel.setBackground(new Color(30, 30, 30));
mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
frame.setContentPane(mainPanel);


JLabel title = new JLabel("Digital Energy Monitor");
title.setForeground(Color.WHITE);
title.setFont(new Font("Segoe UI", Font.BOLD, 24));
mainPanel.add(title, BorderLayout.NORTH);


JPanel left = new JPanel();
left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
left.setBackground(new Color(40, 40, 40));
left.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);

      
JTextField nameField = new JTextField();
JTextField powerField = new JTextField();
JTextField hoursField = new JTextField();
JTextField dateField = new JTextField();

JTextField[] fields = {nameField, powerField, hoursField, dateField};
String[] labels = {"Name", "Power", "Hours", "Date"};

for (int i = 0; i < labels.length; i++) {
    JLabel l = new JLabel(labels[i]);
    l.setForeground(Color.WHITE);
    l.setFont(labelFont);
    left.add(l);

    fields[i].setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    left.add(fields[i]);
    left.add(Box.createVerticalStrut(10));
}


JPanel btnPanel = new JPanel(new GridLayout(4,2,10,10));
btnPanel.setBackground(new Color(40,40,40));

JButton addBtn = new JButton("Add");
JButton showBtn = new JButton("Show");
JButton searchBtn = new JButton("Search");
JButton maxBtn = new JButton("Highest");
JButton totalBtn = new JButton("Total");
JButton historyBtn = new JButton("Add History");
JButton showHistoryBtn = new JButton("History");
JButton clearBtn = new JButton("Clear");

JButton[] buttons = {
    addBtn, showBtn, searchBtn, maxBtn,
    totalBtn, historyBtn, showHistoryBtn, clearBtn
};


for (JButton b : buttons) {
    b.setFocusPainted(false);
    b.setBackground(new Color(70,130,180));
    b.setForeground(Color.WHITE);
    b.setFont(new Font("Segoe UI", Font.BOLD, 12));
}

clearBtn.setBackground(new Color(200,60,60)); // red

for (JButton b : buttons) btnPanel.add(b);

left.add(btnPanel);


JTextArea output = new JTextArea();
output.setBackground(new Color(20,20,20));
output.setForeground(Color.GREEN);
output.setFont(new Font("Consolas", Font.PLAIN, 14));
output.setCaretColor(Color.WHITE);
output.setEditable(false);

JScrollPane scroll = new JScrollPane(output);


mainPanel.add(left, BorderLayout.WEST);
mainPanel.add(scroll, BorderLayout.CENTER);

left.setPreferredSize(new Dimension(280, 0));




addBtn.addActionListener(e -> {
    try {
        String name = nameField.getText();
        int power = Integer.parseInt(powerField.getText());
        int hours = Integer.parseInt(hoursField.getText());

        table.add(name, power, hours);
        heap.insert(new Appliance(name, power, hours));

        output.setText("Added!\n");
    } catch (Exception ex) {
        output.setText("Invalid input!");
    }
});


showBtn.addActionListener(e -> {
    output.setText(table.getAllData());
});


searchBtn.addActionListener(e -> {
    String name = nameField.getText().trim();

    if (name.isEmpty()) {
        output.setText("Enter appliance name to search!");
        return;
    }

    Appliance a = table.search(name);

    if (a != null) {
        output.setText(
            "🔍 Appliance Found:\n\n" +
            "Name: " + a.getName() + "\n" +
            "Power: " + a.power + " W\n" +
            "Hours: " + a.hours + "\n" +
            "Energy: " + a.getEnergy() + " units"
        );
    } else {
        output.setText("❌ Appliance not found!");
    }
});


maxBtn.addActionListener(e -> {
    Appliance max = heap.getMax();
    if (max != null)
        output.setText("Highest:\n" + max);
    else
        output.setText("No data");
});


totalBtn.addActionListener(e -> {
    output.setText("Total Energy: " + table.getTotalEnergy());
});


historyBtn.addActionListener(e -> {
    try {
        String date = dateField.getText();
        int energy = table.getTotalEnergy();

        bst.insert(date, energy);
        output.setText("History Added!");
    } catch (Exception ex) {
        output.setText("Error adding history!");
    }
});

      
showHistoryBtn.addActionListener(e -> {
    output.setText(bst.getHistory());
});


clearBtn.addActionListener(e -> {
    nameField.setText("");
    powerField.setText("");
    hoursField.setText("");
    dateField.setText("");
    output.setText("");
});

frame.setVisible(true);
    }
}
