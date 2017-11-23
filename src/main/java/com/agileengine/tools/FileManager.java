package com.agileengine.tools;

import com.agileengine.logic.ImageComparison;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class FileManager {
    /**
     * Reads the file extension.
     *
     * @param fileName file name.
     * @return the file extension (png, jpg, gif ...).
     */
    static String getExtension(String fileName) {
        int index = fileName.lastIndexOf('.') + 1;
        return index == -1 ? null : fileName.substring(index);
    }

    /**
     * Reads image from the provided path.
     *
     * @param path the path where contains image.
     * @return the BufferedImage object of this specific image.
     * @throws URISyntaxException
     * @throws IOException
     */
    public static BufferedImage readImageFromResources(String path) throws URISyntaxException, IOException {
        return ImageIO.read(new File(ImageComparison.class.getClassLoader().getResource(path).toURI().getPath()));
    }

    /**
     * Save image to the provided path.
     *
     * @param path  the path to the saving image.
     * @param image the BufferedImage object of this specific image.
     * @throws IOException
     */
    public static void saveImage(String path, BufferedImage image) throws IOException {
        new File(path).mkdirs();
        ImageIO.write(image, getExtension(path), new File(path));
    }
}