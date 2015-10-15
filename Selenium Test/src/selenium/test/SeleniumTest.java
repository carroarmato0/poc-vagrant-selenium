package selenium.test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author carroarmato0
 */
public class SeleniumTest {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws InterruptedException, IOException {
    // We could use any driver for our tests...
    DesiredCapabilities capabilities = new DesiredCapabilities();

    capabilities.setJavascriptEnabled(true);
    capabilities.setPlatform(Platform.LINUX);
    capabilities.setBrowserName("firefox");
    capabilities.setVersion("38.3.0");

    // Get a handle to the driver. This will throw an exception
    // if a matching driver cannot be located
    WebDriver driver = null;
    try {
      driver = new RemoteWebDriver(new URL("http://192.168.33.21:4444/wd/hub"),capabilities);
    } catch (MalformedURLException ex) {
      Logger.getLogger(SeleniumTest.class.getName()).log(Level.SEVERE, null, ex);
    }

    if ( driver != null ) {
      //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
      driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
      driver.manage().window().setSize(new Dimension(1920, 1080));

      driver.get("https://www.google.com/ncr");
      WebElement element = driver.findElement(By.name("q"));
      element.sendKeys("Inuits");
      element.submit();
      
      // wait until the google page shows the result
      WebElement myDynamicElement = (new WebDriverWait(driver, 20))
              .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
      
      System.out.println("Creating screenshot.png");
      File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(scrFile, new File("./screenshot.png"));
    }
    
    driver.close();
    
  }
  
}
