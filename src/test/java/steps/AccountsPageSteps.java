package steps;

import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.ScenarioContext;

import java.util.List;

public class AccountsPageSteps{
    private LoginPage loginPage;
    private AccountsPage accPage;
    private SearchResultsPage searchResultsPage;
    private ProductInfoPage productInfoPage;
    private ScenarioContext scenarioContext;
    private Hooks hooks;
    private SoftAssert softAssert;

    public AccountsPageSteps(Hooks hooks,ScenarioContext scenarioContext){
        this.hooks=hooks;
        this.loginPage=hooks.getLoginPage();
        this.scenarioContext=scenarioContext;
        softAssert=new SoftAssert();
    }

    @Given("the user is logged in and on the accounts page")
    public void the_user_is_logged_in_and_on_the_accounts_page(){
       accPage= loginPage.doLogin(hooks.getProperties().getProperty("username"), hooks.getProperties().getProperty("password"));
    }

    @When("the user checks the page headers")
    public void the_user_checks_the_page_headers(){
        List<String> pageHeaders=accPage.getAccPageHeaders();
        scenarioContext.setContext("ACC_PAGE_HEADERS",pageHeaders);
    }

    @Then("the page headers should be:")
    public void the_page_headers_should_be(DataTable headers){
        Assert.assertEquals(List.of(scenarioContext.getContext("ACC_PAGE_HEADERS")),headers.asList());

    }

    @When("the user searches for {string}")
    public void the_user_searches_for(String searchKey){
        searchResultsPage=accPage.doSearch(searchKey);
    }
    @Then("the result count should be {int}")
    public void the_result_count_should_be(int resultCount){
        Assert.assertEquals(searchResultsPage.getSearchResultsCount(),resultCount);
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
       softAssert.assertEquals(productInfoPage.getProductHeader(),header);
    }
    @Then("the product code should be {string}")
    public void the_product_code_should_be(String productCode) {
        softAssert.assertEquals(productInfoPage.getProductInfoMap().get("Product Code"),productCode);
    }
    @Then("the availability should be {string}")
    public void the_availability_should_be(String availability) {
        softAssert.assertEquals(productInfoPage.getProductInfoMap().get("Availability"),availability);
    }
    @Then("the price should be {string}")
    public void the_price_should_be(String price){
        softAssert.assertEquals(productInfoPage.getProductInfoMap().get("productPrice"),price);
    }

    @Then("the ExTax should be {string}")
    public void the_ex_tax_should_be(String exTax) {
        softAssert.assertEquals(productInfoPage.getProductInfoMap().get("exTaxPrice"),exTax);
        softAssert.assertAll();
    }


















}