package lebedev_d_v.paint.drawingTools;

import lebedev_d_v.paint.checkers.SizeChecker;
import lebedev_d_v.paint.view.DrawingArea;
import lebedev_d_v.paint.view.FigureStyle;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class EllipseDrawingTool extends DrawingTool{
    private Ellipse2D ellipse;
    private Point p1, p2;

    public EllipseDrawingTool(DrawingArea drawingArea, BufferedImage bufferedImage, Graphics2D g2, Color color, FigureStyle figureStyle) {
        super(drawingArea, bufferedImage, g2, color, figureStyle);
    }

    @Override
    public void draw() {
        drawingArea.removeListeners();
        drawingArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                p1 = e.getPoint();
                ellipse = new Ellipse2D.Double();
                drawingArea.setEllipse(ellipse);
            }
        });
        drawingArea.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                p2 = e.getPoint();
                if (g2 != null) {
                    switch (SizeChecker.checkSize(p1, p2)) {
                        case P2_IS_FURTHER_AND_HIGHER -> ellipse.setFrame(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
                        case P2_IS_FURTHER_NOT_HIGHER -> ellipse.setFrame(p1.x, p2.y, p2.x - p1.x, p1.y - p2.y);
                        case P2_IS_NOT_FURTHER_HIGHER -> ellipse.setFrame(p2.x, p1.y, p1.x - p2.x, p2.y - p1.y);
                        case P2_IS_NOT_FURTHER_NOT_HIGHER -> ellipse.setFrame(p2.x, p2.y, p1.x - p2.x, p1.y - p2.y);
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
                    case FILLED -> g2.fill(ellipse);
                    case EMPTY -> g2.draw(ellipse);
                }
                ellipse = null;
                drawingArea.setEllipse(ellipse);
            }
        });
    }
}
