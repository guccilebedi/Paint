package lebedev_d_v.paint.view;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PaintWindow {

    JButton openFileButton, clearButton, saveButton, saveAsButton, chooseColorButton, changeBrushWidthButton, brushButton, rectangleButton, circleButton;
    DrawingArea drawingArea;

    public static void main(String[] args) {
        new PaintWindow().frame();
    }

    private void frame() {
        JFrame frame = new JFrame("Paint");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        drawingArea = new DrawingArea();
        content.add(drawingArea, BorderLayout.CENTER);
        content.add(fileButtons(), BorderLayout.NORTH);
        content.add(drawButtons(), BorderLayout.WEST);
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JPanel fileButtons() {
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

    private JPanel drawButtons() {
        JPanel buttons = new JPanel(new GridLayout(8, 1, 0, 0));
        brushButton = new JButton("Brush");
        brushButton.addActionListener(e -> drawingArea.chooseDrawingAction(DrawingAction.DRAW_LINE));
        rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(e -> drawingArea.chooseDrawingAction(DrawingAction.DRAW_RECTANGLE));
        circleButton = new JButton("Circle");
        circleButton.addActionListener(e -> drawingArea.chooseDrawingAction(DrawingAction.DRAW_CIRCLE));
        clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> drawingArea.chooseDrawingAction(DrawingAction.CLEAR));
        chooseColorButton = new JButton("Choose Color");
        chooseColorButton.addActionListener(e -> {
            JColorChooser colorChooser = new JColorChooser();
            JOptionPane.showConfirmDialog(null, colorChooser, "Choose Color", JOptionPane.OK_CANCEL_OPTION);
            drawingArea.chooseDrawingAction(colorChooser.getColor());
        });
        changeBrushWidthButton = new JButton("Change Brush Width");
        changeBrushWidthButton.addActionListener(e -> {
            JTextField changeBrushWidthTextField = new JTextField();
            JOptionPane.showConfirmDialog(null, changeBrushWidthTextField, "Change Brush Width", JOptionPane.OK_CANCEL_OPTION);
            if (!changeBrushWidthTextField.getText().equals("")) {
                try {
                    drawingArea.chooseDrawingAction(Integer.parseInt(changeBrushWidthTextField.getText()));
                } catch (NumberFormatException ignored) {
                }
            }
        });
        buttons.add(brushButton);
        buttons.add(rectangleButton);
        buttons.add(circleButton);
        buttons.add(chooseColorButton);
        buttons.add(changeBrushWidthButton);
        buttons.add(clearButton);
        return buttons;
    }
}
