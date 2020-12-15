package lebedev_d_v.paint.drawingTools;

import lebedev_d_v.paint.checkers.SizeChecker;
import lebedev_d_v.paint.view.DrawingArea;
import lebedev_d_v.paint.view.FigureStyle;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class RectangleDrawingTool extends DrawingTool {
    private Rectangle2D rectangle;
    private Point p1, p2;

    public RectangleDrawingTool(DrawingArea drawingArea, BufferedImage bufferedImage, Graphics2D g2, Color color, FigureStyle figureStyle) {
        super(drawingArea, bufferedImage, g2, color, figureStyle);
    }

    @Override
    public void draw() {
        drawingArea.removeListeners();
        drawingArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                p1 = e.getPoint();
                rectangle = new Rectangle2D.Double();
                drawingArea.setRectangle(rectangle);
            }
        });
        drawingArea.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                p2 = e.getPoint();
                if (g2 != null) {
                    switch (SizeChecker.checkSize(p1, p2)) {
                        case P2_IS_FURTHER_AND_HIGHER -> {
                            rectangle.setRect(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
                            drawingArea.setRectangle(rectangle);
                        }
                        case P2_IS_FURTHER_NOT_HIGHER -> {
                            rectangle.setRect(p1.x, p2.y, p2.x - p1.x, p1.y - p2.y);
                            drawingArea.setRectangle(rectangle);
                        }
                        case P2_IS_NOT_FURTHER_HIGHER -> {
                            rectangle.setRect(p2.x, p1.y, p1.x - p2.x, p2.y - p1.y);
                            drawingArea.setRectangle(rectangle);
                        }
                        case P2_IS_NOT_FURTHER_NOT_HIGHER -> {
                            rectangle.setRect(p2.x, p2.y, p1.x - p2.x, p1.y - p2.y);
                            drawingArea.setRectangle(rectangle);
                        }
                    }
                    drawingArea.repaint();
                }
            }
        });
        drawingArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                g2 = (Graphics2D) bufferedImage.getGraphics();
                g2.setColor(color);
                switch (figureStyle) {
                    case FILLED -> g2.fill(rectangle);
                    case EMPTY -> g2.draw(rectangle);
                }
                rectangle = null;
                drawingArea.setRectangle(rectangle);
            }
        });
    }
}
