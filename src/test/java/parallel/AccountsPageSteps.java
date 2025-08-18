package parallel;

import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.ScenarioContext;

import java.util.Arrays;
import java.util.List;

public class AccountsPageSteps{
    private LoginPage loginPage;
    private AccountsPage accPage;
    private SearchResultsPage searchResultsPage;
    private ProductInfoPage productInfoPage;
    private ScenarioContext scenarioContext;
    private Hooks hooks;
    private List<String> headers;


    public AccountsPageSteps(Hooks hooks,ScenarioContext scenarioContext){
        this.hooks=hooks;
        this.loginPage=hooks.getLoginPage();
        this.scenarioContext=scenarioContext;
    }

    @Given("the user is logged in and on the accounts page")
    public void the_user_is_logged_in_and_on_the_accounts_page(){
       accPage= loginPage.doLogin(hooks.getProperties().getProperty("username"), hooks.getProperties().getProperty("password"));
    }

    @When("the user checks the page headers")
    public void the_user_checks_the_page_headers(){
        headers=accPage.getAccPageHeaders();
    }

    @Then("the page headers should be:")
    public void the_page_headers_should_be(DataTable expHeaders){
        Assert.assertEquals(expHeaders.asList(String.class),headers );
    }

    @When("the user searches for {string}")
    public void the_user_searches_for(String searchKey){
        searchResultsPage=accPage.doSearch(searchKey);
    }
    @Then("the result count should be {int}")
    public void the_result_count_should_be(int resultCount){
        Assert.assertEquals(resultCount,searchResultsPage.getSearchResultsCount());
    }
    @When("the user selects the product {string}")
    public void the_user_selects_the_product(String productName){
        productInfoPage=searchResultsPage.selectProduct(productName);
    }
    @Then("the image count should be {int}")
    public void the_image_count_should_be(int imageCount){
        Assert.assertEquals(imageCount, productInfoPage.getProductImagesCount());
    }

    @Then("the product header should be {string}")
    public void the_product_header_should_be(String header) {
       Assert.assertEquals(header,productInfoPage.getProductHeader());
    }
    @Then("the product code should be {string}")
    public void the_product_code_should_be(String productCode) {
        Assert.assertEquals(productCode,productInfoPage.getProductInfoMap().get("Product Code"));
    }
    @Then("the availability should be {string}")
    public void the_availability_should_be(String availability) {
        Assert.assertEquals(availability,productInfoPage.getProductInfoMap().get("Availability"));
    }
    @Then("the price should be {string}")
    public void the_price_should_be(String price){
        Assert.assertEquals(price,productInfoPage.getProductInfoMap().get("productPrice"));
    }

    @Then("the ExTax should be {string}")
    public void the_ex_tax_should_be(String exTax) {
        Assert.assertEquals(exTax,productInfoPage.getProductInfoMap().get("exTaxPrice"));
    }


















}