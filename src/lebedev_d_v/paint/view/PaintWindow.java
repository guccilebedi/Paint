package lebedev_d_v.paint.view;

import java.awt.*;
import javax.swing.*;

public class PaintWindow {

    JButton clearButton, saveButton, chooseColorButton, changeBrushWidthButton, brushButton, rectangleButton, circleButton;
    DrawArea drawArea;

    public static void main(String[] args) {
        new PaintWindow().frame();
    }

    private void frame() {
        JFrame frame = new JFrame("Paint");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        drawArea = new DrawArea(0);
        content.add(drawArea, BorderLayout.CENTER);
        content.add(buttons(), BorderLayout.NORTH);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JPanel buttons() {
        JPanel buttons = new JPanel();
        brushButton = new JButton("Brush");
        brushButton.addActionListener(e -> {

        });
        rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(e -> {

        });
        circleButton = new JButton("Circle");
        circleButton.addActionListener(e -> {

        });
        clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> drawArea.clear());
        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> drawArea.save(drawArea));
        chooseColorButton = new JButton("Choose color");
        chooseColorButton.addActionListener(e -> {
            JColorChooser colorChooser = new JColorChooser();
            JOptionPane.showConfirmDialog(null, colorChooser, "Choose color", JOptionPane.OK_CANCEL_OPTION);
            drawArea.chooseColor(colorChooser.getColor());
        });
        changeBrushWidthButton = new JButton("Change brush width");
        changeBrushWidthButton.addActionListener(e -> {
            JTextField changeBrushWidthTextField = new JTextField();
            JOptionPane.showConfirmDialog(null, changeBrushWidthTextField, "Change brush width", JOptionPane.OK_CANCEL_OPTION);
            drawArea.changeWidth(Integer.parseInt(changeBrushWidthTextField.getText()));
        });
        buttons.add(brushButton);
        buttons.add(rectangleButton);
        buttons.add(circleButton);
        buttons.add(chooseColorButton);
        buttons.add(changeBrushWidthButton);
        buttons.add(clearButton);
        buttons.add(saveButton);
        return buttons;
    }
}
