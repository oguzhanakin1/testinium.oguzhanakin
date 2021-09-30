import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class Products
{
    protected WebDriver webDriver;
    WebDriverWait wait;

    //private By secondPage = By.xpath("//*[@class='sc-12aj18f-1 ubwpe']");
    private By secondPage = By.cssSelector("main > div > div > div > div > div > div > div > nav > ul > li:nth-child(12) > a");
    private By products = By.cssSelector("main > div > div > div > div > div > div > div > ul > li");
    private By addToBasket = By.id("add-to-basket");
    private By basket = By.cssSelector("#main-header > div > div > div > div > div > div > div:nth-child(3) > a");
    private By price = By.id("sp-price-lowPrice");
    public Products(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver,60);
    }
    String url;
    String productPrice;

    public Products selectProduct() throws InterruptedException {
        Thread.sleep(1000);
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(secondPage));
        webDriver.findElement(secondPage).click();
        Thread.sleep(200);
        url = webDriver.getCurrentUrl();
        wait.until(ExpectedConditions.numberOfElementsToBe(products,32));
        List<WebElement> productList = webDriver.findElements(products);
        System.out.println(productList.size());
        Random random = new Random();
        int i = random.nextInt(productList.size());
        System.out.println(i);
        productList.get(i).click();
        productPrice = webDriver.findElement(price).getText();
        System.out.println(productPrice);
        Thread.sleep(2000);
        jse.executeScript("window.scrollBy(0,600)");
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(addToBasket));
        webDriver.findElement(addToBasket).click();
        jse.executeScript("window.scrollBy(0,-600)");
        Thread.sleep(700);
        webDriver.findElement(basket).click();

        return new Products(webDriver);
    }
}