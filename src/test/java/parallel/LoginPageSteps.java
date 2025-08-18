package parallel;
import com.qa.opencart.pages.AccountsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.qa.opencart.pages.LoginPage;
import org.junit.Assert;
import utils.ScenarioContext;


public class LoginPageSteps {
    private Hooks hooks;
    private LoginPage loginPage;
    private ScenarioContext scenarioContext;
    private AccountsPage accPage;

    public LoginPageSteps(Hooks hooks,ScenarioContext scenarioContext) {
        this.hooks = hooks;
        this.loginPage = hooks.getLoginPage();
        this.scenarioContext=scenarioContext;
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        System.out.println("User is on login page");
    }

    @When("the user fetches the page title")
    public void the_user_fetches_the_page_title() {
       String loginPageTitle = loginPage.getLoginPageTitle();
       scenarioContext.setContext("LOGIN_PAGE_TITLE",loginPageTitle);
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String title) {
        Assert.assertEquals(title,scenarioContext.getContext("LOGIN_PAGE_TITLE").toString());
    }

    @Then("the page title should not be empty")
    public void the_page_title_should_not_be_empty(){
        Assert.assertFalse(scenarioContext.getContext("LOGIN_PAGE_TITLE").toString().isEmpty());
    }
    @When("the user checks the page URL")
    public void the_user_checks_the_page_URL(){
        String LOGIN_PAGE_URL=loginPage.getLoginPageURL();
        scenarioContext.setContext("LOGIN_PAGE_URL",LOGIN_PAGE_URL);
    }
    @Then("the URL should contain {string}")
    public void the_URL_should_contain(String fractionUrl){
        Assert.assertTrue(scenarioContext.getContext("LOGIN_PAGE_URL").toString().contains(fractionUrl));
    }
    @When("the user checks the forgot password link")
    public void the_user_checks_the_forgot_password_link(){
        boolean forgotLinkExist=loginPage.checkForgotPwdLinkExist();
        scenarioContext.setContext("FORGOT_PSWD_LINK_PRESENT",forgotLinkExist);
    }
    @Then("the forgot password link should be displayed")
    public void the_forgot_password_link_should_be_displayed(){
        Assert.assertTrue(Boolean.parseBoolean(scenarioContext.getContext("FORGOT_PSWD_LINK_PRESENT").toString()));
    }

    @When("the user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String username,String password){
        accPage=loginPage.doLogin(username,password);
    }
    @Then("the user should be redirected to the accounts page with title {string}")
    public void the_user_should_be_redirected_to_the_accounts_page_with_title(String title){
        Assert.assertEquals(title,accPage.getAccPageTitle());
    }








}















