/**
 * Image Comparison Requirements
 * Write a program in Java that compares any 2 images and shows the differences visually. Remember that Working Software
 * is the main value, so something simple that works is generally better than a complex unfinished solution.
 * <p>
 * Must have
 * 1. Implementation should use only standard core language and platform features, no 3rd party libraries and plagiarized
 * code is permitted.
 * 2. Pixels (with the same coordinates in two images) can be visually similar, but have different values of RGB.
 * We should only consider 2 pixels to be "different" if the difference between them is more than 10%.
 * 3. The output of the comparison should be a copy of one of the images image with differences outlined with red
 * rectangles as shown below.
 * 4. We need to see your own code. No third party libraries and borrowed code is allowed.
 * 5. Target completion time is 2 hours, but you may choose to use up to 4 hours. Submissions sent after 4 hours will
 * be disqualified. Note that in addition to quality, time is also factored into scoring the task. The closer you get
 * to 2 hours the higher is the score.
 * <p>
 * Nice to have
 * 1. It should be possible to exclude certain parts of the image from comparison, for example a clock or dynamically
 * generated number. They will be provided by the caller as a list of rectangles to exclude.
 * 2. Provide some sort of UI either as a web page or GUI that allows the user to select the images and view differences
 * as an overlay on either of the images.
 * <p>
 * Expected Deliverables
 * 1. Source code.
 * 2. Binary version of the algorithm that runs and produces output of comparison. No build should be required.
 * 3. Output image showing the result of comparison.
 * <p>
 * Tips and Hints
 * 1. Use javax.imageio.ImageIO to read/write images.
 * 2. Consider java.awt.image.BufferedImage#createGraphics() to draw on in-memory images.
 *
 * @author <a href="mailto:andreyzarazka@gmail.com">Andrew Zarazka</a>
 */
package com.agileengine;