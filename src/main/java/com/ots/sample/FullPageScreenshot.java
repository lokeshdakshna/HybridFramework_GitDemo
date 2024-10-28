package com.ots.sample;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FullPageScreenshot {

    public static void main(String[] args) throws IOException {
        // Set the path to the ChromeDriver executable
       // System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the webpage
        driver.get("https://www.naukri.com/");

        // Get the total height of the webpage
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long totalHeight = (Long) js.executeScript("return document.body.scrollHeight");

        // Set initial height for capturing
        long viewportHeight = (Long) js.executeScript("return window.innerHeight");
        long totalWidth = (Long) js.executeScript("return document.body.clientWidth");

        // Create an empty image to store the stitched screenshot
        BufferedImage stitchedImage = new BufferedImage((int) totalWidth, (int) totalHeight, BufferedImage.TYPE_INT_RGB);

        // Perform scrolling and capture screenshots
        long currentHeight = 0;
        while (currentHeight < totalHeight) {
            // Capture the screenshot of the current viewport
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage screenshot = ImageIO.read(screenshotFile);

            // Paste the screenshot onto the stitched image
            Graphics2D graphics = stitchedImage.createGraphics();
            graphics.drawImage(screenshot, 0, (int) currentHeight, null);
            graphics.dispose();

            // Move to the next position
            currentHeight += viewportHeight;

            // Scroll down
            js.executeScript("window.scrollTo(0, " + currentHeight + ");");
            try {
                Thread.sleep(500); // Adjust the delay as needed to ensure proper loading
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Save the stitched screenshot
        ImageIO.write(stitchedImage, "png", new File("full_page_screenshot1.png"));

        // Quit the WebDriver
        driver.quit();
    }
}

