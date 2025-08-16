package steps;

import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.utils.StringUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import utils.ScenarioContext;

public class RegisterPageSteps{
    Hooks hooks;
    ScenarioContext scenarioContext;
    LoginPage loginPage;
    RegisterPage registerPage;

    public RegisterPageSteps(Hooks hooks, ScenarioContext scenarioContext){
        this.hooks=hooks;
        this.loginPage=hooks.getLoginPage();
        this.scenarioContext=scenarioContext;
    }
    @Given("the user navigates to the registration page")
    public void the_user_navigates_to_the_registration_page(){
        registerPage=loginPage.navigateToRegisterPage();
    }

    @When("the user enters {string},{string},{string},{string} and subscribes {string}")
    public void the_user_enters_and(String fname,String lname,String telephone,String pswd,String subscribe){
        boolean regSuccess=registerPage.userRegister(fname,lname, StringUtils.getRandomEmailId(),telephone,pswd,subscribe);
        scenarioContext.setContext("REG_SUCCESS",regSuccess);
    }

    @Then("the user registration should be successful")
    public void the_user_registration_should_be_successful(){
        Assert.assertTrue(Boolean.parseBoolean(scenarioContext.getContext("REG_SUCCESS").toString()));
    }



}