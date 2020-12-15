package lebedev_d_v.paint.buttonsTool;

import lebedev_d_v.paint.view.DrawingAction;
import lebedev_d_v.paint.view.DrawingArea;
import lebedev_d_v.paint.view.FigureStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawingButtonsTool extends ButtonsTool {
    private JButton clearButton, chooseColorButton, changeBrushWidthButton, brushButton, rectangleButton, circleButton;

    public DrawingButtonsTool(DrawingArea drawingArea) {
        super(drawingArea);
    }

    @Override
    public JPanel createButtonsPanel() {
        JPanel buttons = new JPanel(new GridLayout(8, 1, 0, 0));
        brushButton = new JButton("Brush");
        brushButton.addActionListener(e -> drawingArea.chooseDrawingAction(DrawingAction.DRAW_LINE));
        rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(e -> {
            final FigureStyle[] figureStyle = new FigureStyle[1];
            figureStyle[0] = FigureStyle.FILLED;
            JRadioButton filledRadioButton = new JRadioButton("Filled");
            filledRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    figureStyle[0] = FigureStyle.FILLED;
                }
            });
            JRadioButton emptyRadioButton = new JRadioButton("Empty");
            emptyRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    figureStyle[0] = FigureStyle.EMPTY;
                }
            });
            JPanel radioButtons = new JPanel();
            radioButtons.add(filledRadioButton);
            radioButtons.add(emptyRadioButton);
            JOptionPane.showConfirmDialog(null, radioButtons, "Choose Rectangle Style", JOptionPane.OK_CANCEL_OPTION);
            drawingArea.chooseDrawingAction(DrawingAction.DRAW_RECTANGLE, figureStyle[0]);
        });
        circleButton = new JButton("Ellipse");
        circleButton.addActionListener(e -> {
            final FigureStyle[] figureStyle = new FigureStyle[1];
            figureStyle[0] = FigureStyle.FILLED;
            JRadioButton filledRadioButton = new JRadioButton("Filled");
            filledRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    figureStyle[0] = FigureStyle.FILLED;
                }
            });
            JRadioButton emptyRadioButton = new JRadioButton("Empty");
            emptyRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    figureStyle[0] = FigureStyle.EMPTY;
                }
            });
            JPanel radioButtons = new JPanel();
            radioButtons.add(filledRadioButton);
            radioButtons.add(emptyRadioButton);
            JOptionPane.showConfirmDialog(null, radioButtons, "Choose Ellipse Style", JOptionPane.OK_CANCEL_OPTION);
            drawingArea.chooseDrawingAction(DrawingAction.DRAW_ELLIPSE, figureStyle[0]);
        });
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
