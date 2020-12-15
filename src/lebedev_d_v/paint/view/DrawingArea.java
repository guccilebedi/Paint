package lebedev_d_v.paint.view;

import lebedev_d_v.paint.drawingTools.EllipseDrawingTool;
import lebedev_d_v.paint.drawingTools.LineDrawingTool;
import lebedev_d_v.paint.drawingTools.RectangleDrawingTool;
import lebedev_d_v.paint.fileTools.OpenFileTool;
import lebedev_d_v.paint.fileTools.SaveFileTool;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class DrawingArea extends JComponent {
    private BufferedImage bufferedImage;
    private Graphics2D g2;
    private Rectangle2D rectangle;
    private Ellipse2D ellipse;
    private FigureStyle figureStyle;
    private Color color = Color.BLACK;
    private SaveFileTool saveFileTool;
    private OpenFileTool openFileTool;
    private LineDrawingTool lineDrawingTool;
    private RectangleDrawingTool rectangleDrawingTool;
    private EllipseDrawingTool ellipseDrawingTool;

    public DrawingArea() {
    }

    protected void paintComponent(Graphics g) {
        if (bufferedImage == null) {
            bufferedImage = (BufferedImage) createImage(getWidth(), getHeight());
            g2 = (Graphics2D) bufferedImage.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(bufferedImage, 0, 0, null);
        g2 = (Graphics2D) g;
        g.setColor(color);
        if (rectangle != null) {
            switch (figureStyle) {
                case FILLED -> g2.fill(rectangle);
                case EMPTY -> g2.draw(rectangle);
            }
        }
        if (ellipse != null) {
            switch (figureStyle) {
                case FILLED -> g2.fill(ellipse);
                case EMPTY -> g2.draw(ellipse);
            }
        }
    }

    public void chooseDrawingAction(DrawingAction drawingAction) {
        switch (drawingAction) {
            case DRAW_LINE -> {
                lineDrawingTool = new LineDrawingTool(this, bufferedImage, g2, color, figureStyle);
                lineDrawingTool.draw();
            }
            case CLEAR -> clear();
            case SAVE -> {
                saveFileTool = new SaveFileTool(bufferedImage, null, g2);
                saveFileTool.interactWithFile();
            }
        }
    }

    public void chooseDrawingAction(DrawingAction drawingAction, FigureStyle figureStyle) {
        switch (drawingAction) {
            case DRAW_RECTANGLE -> {
                this.figureStyle = figureStyle;
                rectangleDrawingTool = new RectangleDrawingTool(this, bufferedImage, g2, color, figureStyle);
                rectangleDrawingTool.draw();
            }
            case DRAW_ELLIPSE -> {
                this.figureStyle = figureStyle;
                ellipseDrawingTool = new EllipseDrawingTool(this, bufferedImage, g2, color, figureStyle);
                ellipseDrawingTool.draw();
            }
        }
    }

    public void chooseDrawingAction(DrawingAction drawingAction, File file) throws IOException {
        switch (drawingAction) {
            case OPEN_FILE -> {
                openFileTool = new OpenFileTool(bufferedImage, file, g2);
                bufferedImage = openFileTool.interactWithFile();
                repaint();
            }
            case SAVE_AS -> {
                saveFileTool = new SaveFileTool(bufferedImage, file, g2);
                saveFileTool.interactWithFile();
            }
        }
    }

    public void chooseDrawingAction(Color color) {
        chooseColor(color);
    }

    public void chooseDrawingAction(int width) {
        changeBrushWidth(width);
    }

    public void removeListeners() {
        for (MouseListener listener : getMouseListeners()) {
            removeMouseListener(listener);
        }
        for (MouseMotionListener listener : getMouseMotionListeners()) {
            removeMouseMotionListener(listener);
        }
    }

    private void changeBrushWidth(int brushWidth) {
        if (lineDrawingTool != null) {
            lineDrawingTool.setBrushWidth(brushWidth);
        }
    }

    private void chooseColor(Color color) {
        this.color = color;
        if (rectangleDrawingTool != null) {
            rectangleDrawingTool.setColor(color);
        }
        if (ellipseDrawingTool != null) {
            ellipseDrawingTool.setColor(color);
        }
        if (lineDrawingTool != null) {
            lineDrawingTool.setColor(color);
        }
    }

    private void clear() {
        g2 = (Graphics2D) bufferedImage.getGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(color);
        repaint();
    }

    public void setRectangle(Rectangle2D rectangle) {
        this.rectangle = rectangle;
    }

    public void setEllipse(Ellipse2D ellipse) {
        this.ellipse = ellipse;
    }
}
