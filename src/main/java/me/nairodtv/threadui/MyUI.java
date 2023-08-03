package me.nairodtv.threadui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MyUI extends JFrame {
    private final JTabbedPane tabbedPane;

    public MyUI() {
        super("Thread Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();

        add(tabbedPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addTabs(int N) {
        for (int i = 0; i < N; i++) {
            JPanel labelAndComponent = new JPanel();
            labelAndComponent.add(createLabelAndComponent("Thread Starting...", List.of("Starting...")));
            tabbedPane.addTab("Thread #" + i, labelAndComponent);
        }
    }

    JPanel createLabelAndComponent(String topText, List<String> lines) {
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        JLabel label = new JLabel(topText);
        label.setAlignmentX(CENTER_ALIGNMENT);

        JTextArea textArea = new JTextArea() {
            @Override
            protected void paintComponent(Graphics g) {
                String[] lines = getText().split("\n");
                FontMetrics fm = g.getFontMetrics();
                int lineHeight = fm.getHeight();
                int y = fm.getAscent() + 25;
                for (String line : lines) {
                    int x = (getWidth() - fm.stringWidth(line)) / 2;
                    g.drawString(line, x, y);
                    y += lineHeight;
                }
            }
        };
        textArea.setText(String.join("\n", lines));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(label);
        pane.add(new JScrollPane(textArea));
        return pane;
    }

    public void updateLabels(int tabId, String topText, List<String> lines) {
        tabbedPane.setComponentAt(tabId, createLabelAndComponent(topText, lines));
    }
}