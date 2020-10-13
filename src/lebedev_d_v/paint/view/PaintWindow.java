package lebedev_d_v.paint.view;

import lebedev_d_v.paint.service.DrawArea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PaintWindow {

    JButton clearButton, saveButton, chooseColorButton, changeBrushWidthButton, rectangleButton, circleButton;
    DrawArea drawArea;

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearButton) {
                drawArea.clear();
            } else if (e.getSource() == saveButton) {
                drawArea.save(drawArea);
            } else  if (e.getSource() == chooseColorButton) {
                JColorChooser colorChooser = new JColorChooser();
                JOptionPane.showConfirmDialog(null, colorChooser, "Choose color", JOptionPane.OK_CANCEL_OPTION);
                drawArea.chooseColor(colorChooser.getColor());
            } else if (e.getSource() == changeBrushWidthButton) {
                JTextField changeBrushWidthTextField = new JTextField();
                JOptionPane.showConfirmDialog(null, changeBrushWidthTextField, "Change brush width", JOptionPane.OK_CANCEL_OPTION);
                drawArea.changeWidth(Integer.parseInt(changeBrushWidthTextField.getText()));
            } else if (e.getSource() == rectangleButton) {

            } else if (e.getSource() == circleButton) {

            }
        }
    };

    public static void main(String[] args) {
        new PaintWindow().frame();
    }

    public void frame() {
        JFrame frame = new JFrame("Paint");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        drawArea = new DrawArea();
        content.add(drawArea, BorderLayout.CENTER);
        JPanel buttons = new JPanel();
        circleButton = new JButton("Circle");
        circleButton.addActionListener(actionListener);
        rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(actionListener);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(actionListener);
        saveButton = new JButton("Save");
        saveButton.addActionListener(actionListener);
        chooseColorButton = new JButton("Choose color");
        chooseColorButton.addActionListener(actionListener);
        changeBrushWidthButton = new JButton("Change brush width");
        changeBrushWidthButton.addActionListener(actionListener);
        //buttons.add(rectangleButton);
        //buttons.add(circleButton);
        buttons.add(chooseColorButton);
        buttons.add(changeBrushWidthButton);
        buttons.add(clearButton);
        buttons.add(saveButton);
        content.add(buttons, BorderLayout.NORTH);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
