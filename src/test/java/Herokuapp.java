import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Herokuapp {
    private static WebDriver driver;

    @BeforeClass
    public static void init() {
        File chromedriverPath = new File("src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chromedriverPath.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        driver.get("https://the-internet.herokuapp.com");
    }

    @Test
    public void linkNotFound() {
        String linkName = System.getProperty("linkName");
        isLinkPresent(linkName);
        List<WebElement> listOfElements = driver.findElements(By.xpath("//*[@id='content']//a"));
        for (WebElement el : listOfElements) {
            if (el.getText().equals(linkName)) {
                el.click();
                break;
            }
        }
    }

    private void isLinkPresent(String linkName) {
        try{
            driver.findElement(By.linkText(linkName));
        }
        catch (Exception e){
            Assert.fail("Link's name not found");
        }
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
