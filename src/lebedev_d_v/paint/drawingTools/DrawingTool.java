package lebedev_d_v.paint.drawingTools;

import lebedev_d_v.paint.view.DrawingArea;
import lebedev_d_v.paint.view.FigureStyle;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class DrawingTool {
    protected DrawingArea drawingArea;
    protected BufferedImage bufferedImage;
    protected Graphics2D g2;
    protected Color color;
    protected FigureStyle figureStyle;

    public DrawingTool(DrawingArea drawingArea, BufferedImage bufferedImage, Graphics2D g2, Color color, FigureStyle figureStyle) {
        this.drawingArea = drawingArea;
        this.bufferedImage = bufferedImage;
        this.g2 = g2;
        this.color = color;
        this.figureStyle = figureStyle;
    }

    public abstract void draw();

    public void setColor(Color color) {
        this.color = color;
    }
}
