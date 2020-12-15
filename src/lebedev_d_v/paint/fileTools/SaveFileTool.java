package lebedev_d_v.paint.fileTools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaveFileTool extends FileTool {

    public SaveFileTool(BufferedImage bufferedImage, File file, Graphics2D g2) {
        super(bufferedImage, file, g2);
    }

    @Override
    public BufferedImage interactWithFile() {
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
                ImageIO.write(bufferedImage, "png", new File(fileName));
            }
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }
}
