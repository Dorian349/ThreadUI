package me.nairodtv.threadui;

import javax.swing.*;

public class MainApplication {

    public static void main(String[] args) {
        MyUI ui = new MyUI();
        START_job(6, ui);
    }

    public static void START_job(int N, MyUI myUI) {
        SwingUtilities.invokeLater(() -> {

            String[] titles = new String[N];

            for (int i = 0; i < N; i++) {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                MyBackgroundTask backgroundTask = new MyBackgroundTask(myUI, i);
                backgroundTask.execute();

                titles[i] = "Thread " + i;
            }

            myUI.addTabs(titles);
        });
    }
}
