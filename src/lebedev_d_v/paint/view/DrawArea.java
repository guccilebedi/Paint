package lebedev_d_v.paint.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawArea extends JComponent {

    private Image image;
    private Graphics2D g2;
    private int newX, newY, oldX, oldY;

    public DrawArea(int caseSwitch) {
        switch (caseSwitch) {
            case (0):
                drawLine();
            case (1):
                drawRectangle();
            case (2):
                drawCircle();
        }
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

    private void drawLine() {
        setDoubleBuffered(false);
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
                    g2.drawRect(oldX, oldY, newX - oldX, newY - oldY);
                    repaint();
                    oldX = newX;
                    oldY = newY;
                }
            }
        });
    }

    private void drawCircle() {
        setDoubleBuffered(false);
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
                    g2.fillOval(oldX, oldY, newX - oldX, newY - oldY);
                    repaint();
                    oldX = newX;
                    oldY = newY;
                }
            }
        });
    }

    public void changeWidth(int width) {
        g2.setStroke(new BasicStroke(width));
    }

    public void chooseColor(Color color) {
        g2.setPaint(color);
        repaint();
    }

    public void save(DrawArea drawArea) {
        BufferedImage bImg = new BufferedImage(drawArea.getWidth(), drawArea.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        drawArea.paintAll(cg);
        try {
            ImageIO.write(bImg, "png", new File("./image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        g2.setPaint(Color.white);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setPaint(Color.black);
        repaint();
    }
}
