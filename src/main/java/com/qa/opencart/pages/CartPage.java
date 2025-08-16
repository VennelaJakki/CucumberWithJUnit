package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    private By header= By.tagName("h1");
    private By productName=By.xpath("//div[@id='content']//table/tbody//td[@class='text-left'][1]");
    private By total=By.xpath("(//div[@class='row'])[3]//tr[2]/td[2]");
    public CartPage(WebDriver driver) {
        this.driver=driver;
        this.eleUtil=new ElementUtil(driver);
    }

    public String getPageHeader(){
       return eleUtil.doGetText(header).trim();
    }
    public String getProductName(){
        return eleUtil.doGetText(productName).trim();
    }
    public String getTotalCost(){
        return eleUtil.doGetText(total).trim();
    }
}
