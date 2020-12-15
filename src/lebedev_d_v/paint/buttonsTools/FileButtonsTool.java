package lebedev_d_v.paint.buttonsTools;

import lebedev_d_v.paint.view.DrawingAction;
import lebedev_d_v.paint.view.DrawingArea;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FileButtonsTool extends ButtonsTool {
    private JButton openFileButton, saveButton, saveAsButton;

    public FileButtonsTool(DrawingArea drawingArea) {
        super(drawingArea);
    }

    @Override
    public JPanel createButtonsPanel() {
        JPanel buttons = new JPanel(new GridLayout(1, 3, 0, 0));
        openFileButton = new JButton("Open File");
        openFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("./images/"));
            fileChooser.setFileFilter(new FileNameExtensionFilter(".png", "png"));
            try {
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    drawingArea.chooseDrawingAction(DrawingAction.OPEN_FILE, fileChooser.getSelectedFile());
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> drawingArea.chooseDrawingAction(DrawingAction.SAVE));
        saveAsButton = new JButton("Save As");
        saveAsButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("./images/"));
            fileChooser.setFileFilter(new FileNameExtensionFilter(".png", "png"));
            try {
                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    drawingArea.chooseDrawingAction(DrawingAction.SAVE_AS, fileChooser.getSelectedFile());
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        buttons.add(openFileButton);
        buttons.add(saveButton);
        buttons.add(saveAsButton);
        return buttons;
    }
}