package lebedev_d_v.paint.view;

import lebedev_d_v.paint.buttonsTool.DrawingButtonsTool;
import lebedev_d_v.paint.buttonsTool.FileButtonsTool;

import java.awt.*;
import javax.swing.*;

public class PaintWindow {
    DrawingArea drawingArea;
    DrawingButtonsTool drawingButtonsTool;
    FileButtonsTool fileButtonsTool;

    public static void main(String[] args) {
        new PaintWindow().frame();
    }

    private void frame() {
        JFrame frame = new JFrame("Paint");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        drawingArea = new DrawingArea();
        fileButtonsTool = new FileButtonsTool(drawingArea);
        drawingButtonsTool = new DrawingButtonsTool(drawingArea);
        content.add(drawingArea, BorderLayout.CENTER);
        content.add(fileButtonsTool.createButtonsPanel(), BorderLayout.NORTH);
        content.add(drawingButtonsTool.createButtonsPanel(), BorderLayout.WEST);
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}