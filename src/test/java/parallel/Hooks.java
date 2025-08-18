package parallel;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.LoginPage;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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
        getThreadInfo();
    }

    private static void getThreadInfo(){
        Thread currentThread = Thread.currentThread();
        System.out.println("Current Thread Name: " + currentThread.getName());
        System.out.println("Current Thread ID: " + currentThread.getId());
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
