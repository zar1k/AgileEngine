package com.agileengine.tools;

import com.agileengine.model.Rectangle;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * Tools for the ImageComparison object.
 */
public class ImageComparisonTools {
    /**
     * Make a copy of the BufferedImage object.
     *
     * @param image the provided image.
     * @return copy of the provided image.
     */
    public static BufferedImage deepCopy(BufferedImage image) {
        ColorModel cm = image.getColorModel();
        WritableRaster raster = image.copyData(null);
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    /**
     * It checks the images for equality by comparing their width, height and extension.
     *
     * @param imgOne        BufferedImage object of the first image.
     * @param imgSecond     BufferedImage object of the second image.
     * @param nameFirstImg  the name of the first image.
     * @param nameSecondImg the name of the second image.
     */
    public static void checkCorrectImage(BufferedImage imgOne, BufferedImage imgSecond, String nameFirstImg, String nameSecondImg) {
        if (imgOne.getHeight() != imgSecond.getHeight() ||
                imgOne.getWidth() != imgSecond.getWidth() ||
                !FileManager.getExtension(nameFirstImg).equalsIgnoreCase(FileManager.getExtension(nameSecondImg))) {
            throw new IllegalArgumentException("Images dimensions mismatch.");
        }
    }

    /**
     * Says if the two pixels equal or not. The rule is the difference between two pixels need to be more then 10%.
     *
     * @param x      the X value of the binary matrix.
     * @param y      the X value of the binary matrix.
     * @param image1 BufferedImage object of the first image.
     * @param image2 BufferedImage object of the second image.
     * @return true if they' are difference, false otherwise.
     */
    public static boolean isDifferent(int x, int y, BufferedImage image1, BufferedImage image2) {
        final int rgb = 3;
        boolean result = false;

        // samples for the specified pixel
        int[] pixelImg1 = image1.getRaster().getPixel(x, y, new int[rgb]);
        int[] pixelImg2 = image2.getRaster().getPixel(x, y, new int[rgb]);
        // gets modules of the images
        double mod1 = Math.sqrt(pixelImg1[0] * pixelImg1[0] + pixelImg1[1] * pixelImg1[1] + pixelImg1[2] * pixelImg1[2]);
        double mod2 = Math.sqrt(pixelImg2[0] * pixelImg2[0] + pixelImg2[1] * pixelImg2[1] + pixelImg2[2] * pixelImg2[2]);
        // gets module of the difference of images.
        double mod3 = Math.sqrt(
                Math.abs(pixelImg1[0] - pixelImg2[0]) * Math.abs(pixelImg1[0] - pixelImg2[0]) +
                        Math.abs(pixelImg1[1] - pixelImg2[1]) * Math.abs(pixelImg1[1] - pixelImg2[1]) +
                        Math.abs(pixelImg1[2] - pixelImg2[2]) * Math.abs(pixelImg1[2] - pixelImg2[2]));
        double changePixelImage1 = mod3 / mod1;
        double changePixelImage2 = mod3 / mod2;
        if (changePixelImage1 > 0.1 && changePixelImage2 > 0.1) result = true;
        return result;
    }

    /**
     * Create a Rectangle object.
     *
     * @param matrix  the matrix of the Conformity pixels.
     * @param counter the number from marks regions.
     * @return the Rectangle object.
     */
    public static Rectangle createRectangle(int[][] matrix, int counter) {
        Rectangle rectangle = new Rectangle();

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                if (matrix[y][x] == counter) {
                    if (x < rectangle.getMinX()) rectangle.setMinX(x);
                    if (y < rectangle.getMinY()) rectangle.setMinY(y);

                    if (x > rectangle.getMaxX()) rectangle.setMaxX(x);
                    if (y > rectangle.getMaxY()) rectangle.setMaxY(y);
                }
            }
        }
        return rectangle;
    }
}
