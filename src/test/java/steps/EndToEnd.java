package steps;

import com.qa.opencart.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.ScenarioContext;

import java.util.Properties;

public class EndToEnd {
    private Hooks hooks;
    private ScenarioContext scenarioContext;
    private LoginPage loginPage;
    private Properties prop;
    private AccountsPage accPage;
    private SearchResultsPage searchResultsPage;
    private ProductInfoPage productInfoPage;
    private CartPage cartPage;

    public EndToEnd(Hooks hooks, ScenarioContext scenarioContext){
        this.hooks=hooks;
        this.loginPage=hooks.getLoginPage();
        this.scenarioContext=scenarioContext;
        prop=hooks.getProperties();

    }
    @Given("the user is logged in to the application")
    public void user_logs_into_application(){
        accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }

    @When("the user searches for product {string}")
    public void user_searches_for_product(String product){
        searchResultsPage=accPage.doSearch(product);
    }
    @Then("the search result count should be {int}")
    public void validate_search_Result_count(int expProdCount){
        int actProdCount=searchResultsPage.getSearchResultsCount();
        Assert.assertEquals(expProdCount,actProdCount);
    }
    @When("the user selects product {string}")
    public void user_selects_product(String productName){
        productInfoPage=searchResultsPage.selectProduct(productName);
    }
    @Then("the product image count should be {int}")
    public void validate_product_image_count(int expImageCount){
       int actImageCount= productInfoPage.getProductImagesCount();
       Assert.assertEquals(expImageCount,actImageCount);
    }
    @Then("the selected product header should be {string}")
    public void validate_product_header(String expHeader){
        String actHeader=productInfoPage.getProductHeader();
        Assert.assertEquals(expHeader,actHeader);
    }
    @When("the user adds the product to cart")
    public void the_user_adds_the_product_to_cart(){
        String cartMsg=productInfoPage.addToCart();
        scenarioContext.setContext("ADD_TO_CART_MSG",cartMsg);
    }
    @Then("user validates add to cart message {string}")
    public void user_validates_add_to_cart_message(String expMsg){
        Assert.assertEquals(expMsg,scenarioContext.getContext("ADD_TO_CART_MSG").toString());
    }
    @When("user goes to shopping cart page")
    public void user_moves_to_shopping_cart_page(){
        cartPage=productInfoPage.goToViewCartPage();
    }
    @Then("user should see {string} as header")
    public void user_should_see_header(String header){
        Assert.assertEquals(cartPage.getPageHeader(),header);
    }
    @Then("user should see product name as {string}")
    public void user_should_see_product_name(String productName){
        Assert.assertEquals(productName,cartPage.getProductName());
    }
    @Then("user should see total price as {string}")
    public void user_should_see_total_price(String totalPrice){
        Assert.assertEquals(totalPrice,cartPage.getTotalCost());
    }












}
