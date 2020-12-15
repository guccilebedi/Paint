package lebedev_d_v.paint.drawingTools;

import lebedev_d_v.paint.view.DrawingArea;
import lebedev_d_v.paint.view.FigureStyle;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class LineDrawingTool extends DrawingTool {
    private Point p1, p2;
    private int brushWidth = 0;

    public LineDrawingTool(DrawingArea drawingArea, BufferedImage bufferedImage, Graphics2D g2, Color color, FigureStyle figureStyle) {
        super(drawingArea, bufferedImage, g2, color, figureStyle);
    }

    @Override
    public void draw() {
        drawingArea.removeListeners();
        drawingArea.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                p1 = e.getPoint();
            }
        });
        drawingArea.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                p2 = e.getPoint();
                g2 = (Graphics2D) bufferedImage.getGraphics();
                g2.setColor(color);
                if (brushWidth != 0) {
                    g2.setStroke(new BasicStroke(brushWidth));
                }
                if (g2 != null) {
                    g2.drawLine(p1.x, p1.y, p2.x, p2.y);
                    drawingArea.repaint();
                    p1 = p2;
                }
            }
        });
    }

    public void setBrushWidth(int brushWidth) {
        this.brushWidth = brushWidth;
    }
}
