import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class Crawler {

    // location of chomedriver.exe
    private final static String DRIVER_LOC = "C:\\WebDrivers\\chromedriver.exe";

    public static void main(String[] args) {

        // set the location of chromedriver.exe
        System.setProperty("webdriver.chrome.driver", DRIVER_LOC);

        // set options for chromedriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");

        // start chrome driver
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://www.instagram.com/p/BqTWMjNFTkF/");

            List<WebElement> commentSection = driver.findElement(By.tagName("article")).findElements(By.tagName("li"));

            String caption = commentSection.get(0).getText();

            WebElement moreCommentsButton = commentSection.get(1);

            try {
                while (moreCommentsButton != null && moreCommentsButton.isDisplayed() && moreCommentsButton.isEnabled()) {
                    moreCommentsButton.click();
                    Thread.sleep(500);
                }
            }
            catch (StaleElementReferenceException ex) {
                System.out.println("All comments are loaded!");
            }

            commentSection = driver.findElement(By.tagName("article")).findElements(By.tagName("li"));

            for(WebElement element : commentSection)
                System.out.println(element.getText());
        }
        catch (Exception ex) {
            ex.printStackTrace();
//            driver.close();
//            driver.quit();
        }
    }
}
