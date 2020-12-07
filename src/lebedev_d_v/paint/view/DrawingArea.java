package lebedev_d_v.paint.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawingArea extends JComponent {

    private Image image;
    private Graphics2D g2;
    private int newX, newY, oldX, oldY;

    public DrawingArea() {
    }

    public void chooseDrawingAction(DrawingAction drawingAction) {
        switch (drawingAction) {
            case DRAW_LINE -> drawLine();
            case DRAW_RECTANGLE -> drawRectangle();
            case DRAW_CIRCLE -> drawCircle();
            case CLEAR -> clear();
            case SAVE -> save(null);
        }
    }

    public void chooseDrawingAction(DrawingAction drawingAction, File file) throws IOException {
        switch (drawingAction) {
            case OPEN_FILE -> openFile(file);
            case SAVE_AS -> save(file);
        }
    }

    public void chooseDrawingAction(Color color) {
        chooseColor(color);
    }

    public void chooseDrawingAction(int width) {
        changeBrushWidth(width);
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getWidth(), getHeight());
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image, 0, 0, null);
    }

    private void removeListeners() {
        for (MouseListener listener : getMouseListeners()) {
            removeMouseListener(listener);
        }
        for (MouseMotionListener listener : getMouseMotionListeners()) {
            removeMouseMotionListener(listener);
        }
    }

    private void openFile(File file) throws IOException {
        image = ImageIO.read(file);
        g2 = (Graphics2D) image.getGraphics();
        repaint();
        chooseColor(Color.BLACK);
    }

    private void drawLine() {
        setDoubleBuffered(false);
        removeListeners();
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                newX = e.getX();
                newY = e.getY();
                if (g2 != null) {
                    g2.drawLine(oldX, oldY, newX, newY);
                    repaint();
                    oldX = newX;
                    oldY = newY;
                }
            }
        });
    }

    private void drawRectangle() {
        setDoubleBuffered(false);
        removeListeners();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                newX = e.getX();
                newY = e.getY();
                if (g2 != null) {
                    if (newX > oldX) {
                        if (newY > oldY) {
                            g2.fillRect(oldX, oldY, newX - oldX, newY - oldY);
                        } else {
                            g2.fillRect(oldX, newY, newX - oldX, oldY - newY);
                        }
                    } else {
                        if (newY > oldY) {
                            g2.fillRect(newX, oldY, oldX - newX, newY - oldY);
                        } else {
                            g2.fillRect(newX, newY, oldX - newX, oldY - newY);
                        }
                    }
                    repaint();
                    oldX = newX;
                    oldY = newY;
                }
            }
        });
    }

    private void drawCircle() {
        setDoubleBuffered(false);
        removeListeners();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                newX = e.getX();
                newY = e.getY();
                if (g2 != null) {
                    if (newX > oldX) {
                        if (newY > oldY) {
                            g2.fillOval(oldX, oldY, newX - oldX, newY - oldY);
                        } else {
                            g2.fillOval(oldX, newY, newX - oldX, oldY - newY);
                        }
                    } else {
                        if (newY > oldY) {
                            g2.fillOval(newX, oldY, oldX - newX, newY - oldY);
                        } else {
                            g2.fillOval(newX, newY, oldX - newX, oldY - newY);
                        }
                    }
                    repaint();
                    oldX = newX;
                    oldY = newY;
                }
            }
        });
    }

    private void changeBrushWidth(int width) {
        g2.setStroke(new BasicStroke(width));
    }

    private void chooseColor(Color color) {
        g2.setColor(color);
        repaint();
    }

    private void save(File file) {
        BufferedImage bImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        paintAll(cg);
        try {
            if (file == null) {
                int fileNameSuffix = 0;
                String fileName = "./images/image" + fileNameSuffix + ".png";
                file = new File(fileName);
                while (file.exists()) {
                    fileNameSuffix++;
                    fileName = "./images/image" + fileNameSuffix + ".png";
                    file = new File(fileName);
                }
                ImageIO.write(bImg, "png", new File(fileName));
            }
            ImageIO.write(bImg, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clear() {
        Color temp = g2.getColor();
        g2.setColor(Color.white);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(temp);
        repaint();
    }
}
