package me.nairodtv.threadui;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyBackgroundTask extends SwingWorker<Void, Void> {
    private final MyUI ui;
    private final int threadId;

    public MyBackgroundTask(MyUI ui, int threadId) {
        this.ui = ui;
        this.threadId = threadId;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (true) {

            String updatedTitle = Utils.getAlphaNumericString(10);
            List<String> updatedLines = IntStream.range(5, 20).mapToObj(Utils::getAlphaNumericString).collect(Collectors.toList());

            SwingUtilities.invokeLater(() -> {
                ui.updateLabels(threadId, updatedTitle, updatedLines);
            });

            Thread.sleep(2500);
        }
    }
}
