package parallel;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/parallel",
        glue = "parallel",
        tags = "@smoke",
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "com.aventstack.chaintest.plugins.ChainTestCucumberListener:"
        },
        monochrome = true,
        dryRun = false
)
public class SmokeTest {


}
