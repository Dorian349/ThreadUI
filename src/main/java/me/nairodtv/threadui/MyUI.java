package me.nairodtv.threadui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class MyUI extends JFrame {
    private final JTabbedPane tabbedPane;
    private final HashMap<Integer, JTextArea> textAreas;

    public MyUI() {
        super("Thread Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();
        textAreas = new HashMap<>();

        add(tabbedPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addTabs(String[] titles) {
        for (int i = 0; i < titles.length; i++) {
            JPanel panel = new JPanel(new BorderLayout());

            JTextArea textArea = new JTextArea(5, 30);
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

            JScrollPane scrollPane = new JScrollPane(textArea);
            panel.add(scrollPane, BorderLayout.CENTER);

            tabbedPane.addTab(titles[i], panel);
            textAreas.put(i, textArea);
        }
    }

    public void updateLabels(int tabId, String topText, List<String> lines) {
        JTextArea textArea = textAreas.get(tabId);
        textArea.setText(topText + "\n\n" + String.join("\n", lines));
    }
}