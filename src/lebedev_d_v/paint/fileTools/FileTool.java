package lebedev_d_v.paint.fileTools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class FileTool {
    protected BufferedImage bufferedImage;
    protected File file;
    protected Graphics2D g2;

    public FileTool(BufferedImage bufferedImage, File file, Graphics2D g2) {
        this.bufferedImage = bufferedImage;
        this.file = file;
        this.g2 = g2;
    }

    public abstract BufferedImage interactWithFile() throws IOException;
}
