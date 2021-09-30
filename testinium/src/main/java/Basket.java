import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;

public class Basket
{
    protected WebDriver webDriver;
    WebDriverWait wait;
    private By valueArea = By.xpath("//*[@class='number-selection gg-form-item']");
    private By value = By.xpath("//*[@value='2']");
    private By deleteItem = By.xpath("//*[@class='gg-icon gg-icon-bin-medium']");
    private By price = By.cssSelector("div.total-price > strong");
    private By amount = By.cssSelector("select[class='amount']");
    private By emptyCart = By.cssSelector("#empty-cart-container > div > div > div > div > h2");
    public Basket(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver,60);
    }
    String basketPrice;
    String itemAmount;
    String empty;
    public void basketOperations() throws InterruptedException {
        Thread.sleep(200);
        webDriver.get("https://www.gittigidiyor.com/sepetim");
        basketPrice = webDriver.findElement(price).getText();
        System.out.println(basketPrice);
        webDriver.findElement(valueArea).click();
        webDriver.findElement(value).click();
        Thread.sleep(2000);
        itemAmount = webDriver.findElement(amount).getAttribute("value");
        System.out.println(itemAmount);
        wait.until(ExpectedConditions.elementToBeClickable(deleteItem));
        webDriver.findElement(deleteItem).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCart));
        empty = webDriver.findElement(emptyCart).getText();
        System.out.println(empty);
    }
}
