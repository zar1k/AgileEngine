package com.agileengine.logic;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

import com.agileengine.model.Rectangle;

import static com.agileengine.tools.FileManager.readImageFromResources;
import static com.agileengine.tools.ImageComparisonTools.*;


public class ImageComparison {
    /**
     * The first number which marks first rectangle.
     */
    private static final int COUNTER = 2;
    /**
     * The threshold which means the max distance between non-equal pixels.
     * Could be changed according size and requirements to the image.
     */
    public static int threshold = 5;

    /**
     * Draw rectangle which cover the regions of the difference pixels.
     *
     * @param nameFirstImg  the name of the first image.
     * @param nameSecondImg the name of the second image.
     * @return the result of the drawing.
     * @throws IOException
     * @throws URISyntaxException
     */
    public static BufferedImage drawTheDifference(String nameFirstImg, String nameSecondImg) throws IOException, URISyntaxException {
        BufferedImage image1 = readImageFromResources(nameFirstImg);
        BufferedImage image2 = readImageFromResources(nameSecondImg);

        // check images for valid
        checkCorrectImage(image1, image2, nameFirstImg, nameSecondImg);
        BufferedImage outImg = deepCopy(image2);

        Graphics2D graphics = outImg.createGraphics();
        graphics.setColor(Color.RED);

        int[][] matrix = populateTheMatrixOfTheDifferences(image1, image2);
        int lastNumberCount = groupRegions(matrix);
        drawRectangles(matrix, graphics, COUNTER, lastNumberCount);
        return outImg;
    }

    /**
     * Populate binary matrix by "0" and "1". If the pixels are difference set it as "1", otherwise "0".
     *
     * @param image1 BufferedImage object of the first image.
     * @param image2 BufferedImage object of the second image.
     * @return populated binary matrix.
     */
    public static int[][] populateTheMatrixOfTheDifferences(BufferedImage image1, BufferedImage image2) {
        int[][] matrix = new int[image1.getWidth()][image1.getHeight()];
        for (int y = 0; y < image1.getHeight(); y++) {
            for (int x = 0; x < image1.getWidth(); x++) {
                matrix[x][y] = isDifferent(x, y, image1, image2) ? 1 : 0;
            }
        }
        return matrix;
    }

    /**
     * Group rectangle regions in binary matrix.
     *
     * @param matrix The binary matrix.
     * @return the last number which marks the last region.
     */
    private static int groupRegions(int[][] matrix) {
        int regionCount = COUNTER;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 1) {
                    joinToRegion(matrix, row, col, regionCount);
                    regionCount++;
                }
            }
        }
        return regionCount;
    }

    /**
     * The recursive method which go to all directions and finds difference
     * in binary matrix using threshold for set max distance between values which equal "1".
     * and set the groupCount to matrix.
     *
     * @param matrix      the binary matrix.
     * @param row         the value of the row.
     * @param col         the value of the column.
     * @param regionCount the number which marks caught values that are "1".
     */
    private static void joinToRegion(int[][] matrix, int row, int col, int regionCount) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length || matrix[row][col] != 1) return;

        matrix[row][col] = regionCount;

        for (int i = 0; i < threshold; i++) {
            // goes to all directions.
            joinToRegion(matrix, row - 1 - i, col, regionCount);
            joinToRegion(matrix, row, col + 1 + i, regionCount);
            joinToRegion(matrix, row + 1 + i, col, regionCount);
            joinToRegion(matrix, row, col - 1 - i, regionCount);

            joinToRegion(matrix, row - 1 - i, col - 1 - i, regionCount);
            joinToRegion(matrix, row - 1 - i, col + 1 + i, regionCount);
            joinToRegion(matrix, row + 1 + i, col + 1 + i, regionCount);
            joinToRegion(matrix, row + 1 + i, col - 1 - i, regionCount);
        }
    }

    /**
     * Draw rectangles with the differences pixels.
     *
     * @param matrix          the matrix of the Conformity pixels.
     * @param graphics        the Graphics2D object for drawing rectangles.
     * @param counter         the number from marks regions.
     * @param lastNumberCount the last number which marks region.
     */
    private static void drawRectangles(int[][] matrix, Graphics2D graphics, int counter, int lastNumberCount) {
        if (counter > lastNumberCount) return;
        Rectangle rectangle = createRectangle(matrix, counter);
        graphics.drawRect(rectangle.getMinY(), rectangle.getMinX(), rectangle.getWidth(), rectangle.getHeight());
        drawRectangles(matrix, graphics, ++counter, lastNumberCount);
    }
}