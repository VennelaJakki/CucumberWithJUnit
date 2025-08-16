package steps;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import utils.BrowserContext;

import java.util.Properties;

public class Hooks {

    private  DriverFactory df;
    private  WebDriver driver;
    private  Properties prop;
    private  LoginPage loginPage;

    @Before
    public void setUp() {
        df = new DriverFactory();
        prop = df.initProp();

        String browserName = BrowserContext.getBrowser();
        if (browserName != null) {
            prop.setProperty("browser", browserName);
        }

        driver = df.initDriver(prop);
        loginPage = new LoginPage(driver);
    }
    @AfterStep
    public void takeScreenshot(Scenario scenario){
        if(scenario.isFailed()){
            byte[] screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }

    @After
    public  void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public Properties getProperties() {
        return prop;
    }


}
