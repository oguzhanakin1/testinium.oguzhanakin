import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Search
{
    protected WebDriver webDriver;
    WebDriverWait wait;

    private By searchBox = By.xpath("//*[@class='sc-4995aq-0 sc-14oyvky-0 gHqOYK']");

    public Search(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver,60);
    }

    public Search itemSearch(String product) throws InterruptedException {
        Thread.sleep(1000);
        webDriver.findElement(searchBox).sendKeys(product);
        webDriver.findElement(searchBox).submit();
        Thread.sleep(500);
        return new Search(webDriver);
    }
}
