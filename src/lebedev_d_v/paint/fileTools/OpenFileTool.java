package lebedev_d_v.paint.fileTools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OpenFileTool extends FileTool {

    public OpenFileTool(BufferedImage bufferedImage, File file, Graphics2D g2) {
        super(bufferedImage, file, g2);
    }

    @Override
    public BufferedImage interactWithFile() throws IOException {
        bufferedImage = ImageIO.read(file);
        g2 = (Graphics2D) bufferedImage.getGraphics();
        return bufferedImage;
    }
}
