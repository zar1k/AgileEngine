package com.agileengine;

import com.agileengine.logic.ImageComparison;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

import static com.agileengine.tools.FileManager.saveImage;

public class App {
    public static void main(String[] args) throws IOException, URISyntaxException {
        // Draw rectangles on the image.
        BufferedImage drawnDifferences = ImageComparison.drawTheDifference("image1.png", "image2.png");
        saveImage("result/result.png", drawnDifferences);
    }
}
