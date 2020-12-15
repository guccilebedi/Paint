package lebedev_d_v.paint.buttonsTools;

import lebedev_d_v.paint.view.DrawingArea;

import javax.swing.*;

public abstract class ButtonsTool {
    protected DrawingArea drawingArea;

    public ButtonsTool(DrawingArea drawingArea) {
        this.drawingArea = drawingArea;
    }

    public abstract JPanel createButtonsPanel();
}
