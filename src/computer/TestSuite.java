package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Write the following Test:
 * 1.Test name verifyProductArrangeInAlphaBaticalOrder()
 * 1.1 Click on Computer Menu.
 * 1.2 Click on Desktop
 * 1.3 Select Sort By position "Name: Z to A"
 * 1.4 Verify the Product will arrange in Descending order.
 * 2. Test name verifyProductAddedToShoppingCartSuccessFully()
 * 2.1 Click on Computer Menu.
 * 2.2 Click on Desktop
 * 2.3 Select Sort By position "Name: A to Z"
 * 2.4 Click on "Add To Cart"
 * 2.5 Verify the Text "Build your own computer"
 * 2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
 * 2.7.Select "8GB [+$60.00]" using Select class.
 * 2.8 Select HDD radio "400 GB [+$100.00]"
 * 2.9 Select OS radio "Vista Premium [+$60.00]"
 * 2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander
 * [+$5.00]"
 * 2.11 Verify the price "$1,475.00"
 * 2.12 Click on "ADD TO CARD" Button.
 * 2.13 Verify the Message "The product has been added to your shopping cart" on Top
 * green Bar
 * After that close the bar clicking on the cross button.
 * 2.14 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
 * 2.15 Verify the message "Shopping cart"
 * 2.16 Change the Qty to "2" and Click on "Update shopping cart"
 * 2.17 Verify the Total"$2,950.00"
 * 2.18 click on checkbox “I agree with the terms of service”
 * 2.19 Click on “CHECKOUT”
 * 2.20 Verify the Text “Welcome, Please Sign In!”
 * 2.21Click on “CHECKOUT AS GUEST” Tab
 * 2.22 Fill the all mandatory field
 * 2.23 Click on “CONTINUE”
 * 2.24 Click on Radio Button “Next Day Air($0.00)”
 * 2.25 Click on “CONTINUE”
 * 2.26 Select Radio Button “Credit Card”
 * 2.27 Select “Master card” From Select credit card dropdown
 * 2.28 Fill all the details
 * 2.29 Click on “CONTINUE”
 * 2.30 Verify “Payment Method” is “Credit Card”
 * 2.32 Verify “Shipping Method” is “Next Day Air”
 * 2.33 Verify Total is “$2,950.00”
 * 2.34 Click on “CONFIRM”
 * 2.35 Verify the Text “Thank You”
 * 2.36 Verify the message “Your order has been successfully processed!”
 * 2.37 Click on “CONTINUE”
 * 2.37 Verify the text “Welcome to our store”
 */
public class TestSuite extends Utility {
    String baseUrl = " https://demo.nopcommerce.com/";

    @Before
    public void setUp() {                       //Open browser
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {
        //Click on computer link
        clickOnElement(By.linkText("Computers"));
        //Click on desktop
        clickOnElement(By.xpath("//a[@title='Show products in category Desktops'][normalize-space()='Desktops']"));
        //Store products before clicking on filter
        List<WebElement> beforeFilterProductNames = driver.findElements(By.cssSelector(".product-title"));
        //Create arraylist
        List<String> beforeFilterProductNamesList = new ArrayList<>();
        //Store elements text to array list
        for (WebElement p : beforeFilterProductNames) {
            beforeFilterProductNamesList.add(p.getText());
        }
        //Sort arraylist to ascending oreder
        Collections.sort(beforeFilterProductNamesList);
        //Reverse the list
        Collections.reverse(beforeFilterProductNamesList);
        //Select the filter
        selectByValueFromDropDown(By.id("products-orderby"), "6");
        Thread.sleep(2000);
        //Store elements after filtering
        List<WebElement> afterFilterProductNames = getMultipleElements(By.className("product-title"));
        //Create anothor list to store text of elements after clicking on filter Z to A
        List<String> afterFilterProductNamesList = new ArrayList<>();
        for (WebElement s : afterFilterProductNames) {
            afterFilterProductNamesList.add(s.getText());
        }
        //Compare both list
        Assert.assertEquals("Products are not sorted in descending order", afterFilterProductNamesList, beforeFilterProductNamesList);
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        //Click on Computer Menu
        clickOnElement(By.linkText("Computers"));
        //Click on Desktop
        clickOnElement(By.xpath("//a[@title='Show products in category Desktops'][normalize-space()='Desktops']"));
        //Select Sort By position "Name: A to Z"
        selectByValueFromDropDown(By.id("products-orderby"), "5");
        //Click on "Add To Cart"
        Thread.sleep(2000);
        clickOnElement(By.xpath("//button[text()='Add to cart']"));
        //Verify the Text "Build your own computer"
        Assert.assertEquals("Build your own computer", getTextFromElement(By.xpath("//h1[text()='Build your own computer']")));
        //Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        selectByValueFromDropDown(By.id("product_attribute_1"), "1");
        //Select "8GB [+$60.00]" using Select class.
        selectByValueFromDropDown(By.id("product_attribute_2"), "5");
        //Select HDD radio "400 GB [+$100.00]"
        clickOnElement(By.id("product_attribute_3_7"));
        Thread.sleep(1000);
        //2.9 Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.id("product_attribute_4_9"));
        //2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commande
        Thread.sleep(1000);
        clickOnElement(By.id("product_attribute_5_12"));
        Thread.sleep(1000);
        //Verify the price "$1,475.00"
        Assert.assertEquals("$1,475.00", getTextFromElement(By.id("price-value-1")));
        //Click on "ADD TO CARD" Button.
        clickOnElement(By.id("add-to-cart-button-1"));
        //Verify the Message "The product has been added to your shopping cart" on Top green Bar
        Assert.assertEquals("The product has been added to your shopping cart", getTextFromElement(By.xpath("//p[@class='content']")));
        //close the bar clicking on the cross button.
        clickOnElement(By.xpath("//span[@class='close']"));
        //Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverOnElement(By.xpath("//span[@class='cart-label']"));
        Thread.sleep(2000);
        clickOnElement(By.xpath("//button[@class='button-1 cart-button']"));
        //Verify the message "Shopping cart"
        Assert.assertEquals("Shopping cart", getTextFromElement(By.xpath("//h1[text()='Shopping cart']")));
        //Change the Qty to "2" and Click on "Update shopping cart"
        clickOnElement(By.xpath("//div[@class='quantity up']"));
        //Verify the Total"$2,950.00"
        Assert.assertEquals("$2,950.00", getTextFromElement(By.xpath("//span[@class='product-subtotal']")));
        //click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));
        //Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));
        //Verify the Text “Welcome, Please Sign In!”
        Assert.assertEquals("Welcome, Please Sign In!", getTextFromElement(By.xpath("//h1[text()='Welcome, Please Sign In!']")));
        //Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[text()='Checkout as Guest']"));
        //Fill the all mandatory field
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "Nishit");
        sendTextToElement(By.id("BillingNewAddress_LastName"), "Chitaliya");
        sendTextToElement(By.id("BillingNewAddress_Email"), "nishitchitaliya@gmail.com");
        selectByValueFromDropDown(By.id("BillingNewAddress_CountryId"), "133");
        sendTextToElement(By.id("BillingNewAddress_City"), "Surat");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "a 3/4 ");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "395004");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "07424314233");
        //Click on “CONTINUE”
        clickOnElement(By.name("save"));
        //Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.id("shippingoption_1"));
        //Click on “CONTINUE”
        clickOnElement(By.cssSelector(".button-1.shipping-method-next-step-button"));
        //Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));
        Thread.sleep(1000);
        //Select “Master card” From Select credit card dropdown
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[4]/div[2]/div[1]/button[1]"));
        selectByValueFromDropDown(By.id("CreditCardType"), "MasterCard");
        //Fill all the details
        sendTextToElement(By.id("CardholderName"), "Nishir Chitaliya");
        sendTextToElement(By.id("CardNumber"), "5413330089010640");
        selectByValueFromDropDown(By.id("ExpireYear"), "2028");
        sendTextToElement(By.id("CardCode"), "727");
        //Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        //Verify “Payment Method” is “Credit Card”
        String actPaymentText = getTextFromElement(By.xpath("//span[normalize-space()='Payment Method:']")) + getTextFromElement(By.xpath("//span[normalize-space()='Credit Card']"));
        Assert.assertEquals("Payment method not valid", "Payment Method:Credit Card", actPaymentText);
        //Verify “Shipping Method” is “Next Day Air”
        String actualShippingText = getTextFromElement(By.xpath("//span[normalize-space()='Shipping Method:']")) + getTextFromElement(By.xpath("//span[normalize-space()='Next Day Air']"));
        Assert.assertEquals("Shipping method not valid", "Shipping Method:Next Day Air", actualShippingText);
        //Verify Total is “$2,950.00”
        Assert.assertEquals("$2,950.00", getTextFromElement(By.xpath("//span[@class='value-summary']")));
        //Click on “CONFIRM”
        clickOnElement(By.xpath("//button[text()='Confirm']"));
        //Verify the Text “Thank You”
        Assert.assertEquals("Thank you", getTextFromElement(By.xpath("//h1[text()='Thank you']")));
        //Verify the message “Your order has been successfully processed!”
        Assert.assertEquals("Your order has been successfully processed!", getTextFromElement(By.xpath("//strong[text()='Your order has been successfully processed!']")));
        //Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 order-completed-continue-button']"));
        //Verify the text “Welcome to our store”
        Assert.assertEquals("Welcome to our store", getTextFromElement(By.xpath("//h2[text()='Welcome to our store']")));

    }

    @After
    public void tearDown() {                //Close the browser
        closeBrowser();
    }
}
