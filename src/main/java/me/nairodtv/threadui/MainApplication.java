package me.nairodtv.threadui;

import javax.swing.*;

public class MainApplication {

    public static void main(String[] args) {
        MyUI ui = new MyUI();
        START_job(6, ui);
    }

    public static void START_job(int N, MyUI myUI) {
        SwingUtilities.invokeLater(() -> {

            for (int i = 0; i < N; i++) {
                MyBackgroundTask backgroundTask = new MyBackgroundTask(myUI, i);
                backgroundTask.execute();
            }

            myUI.addTabs(N);
        });
    }
}
