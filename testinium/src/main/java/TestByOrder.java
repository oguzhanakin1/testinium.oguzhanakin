import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestByOrder
{
    private static ChromeDriver chromeDriver;
    private static Logger logger;



    private void setupDriver()
    {
        if(chromeDriver == null)
        {
            WebDriverManager.chromedriver().setup();
            chromeDriver = new ChromeDriver();
            chromeDriver.manage().window().maximize();
            chromeDriver.get("https://www.gittigidiyor.com/");

        }
    }
    private By slider = By.cssSelector("#main-header > div > div > div > div > div > nav > span:nth-child(1)");
    @Test
    public void ALoginPage() throws InterruptedException
    {
        setupDriver();
        System.out.println(chromeDriver.findElement(slider).getText());
        assertEquals("Elektronik",chromeDriver.findElement(slider).getText());
        logger.info("Main page assertion passed!");
        LoginPage login = new LoginPage(chromeDriver);
        login.loginPage("oguzakina@gmail.com", "aU1mi1$9F53HL7");
    }

    @Test
    public void BSearch() throws InterruptedException {
        setupDriver();
        chromeDriver.get("https://www.gittigidiyor.com/");
        Search search = new Search(chromeDriver);
        search.itemSearch("bilgisayar");
    }
    String getPrice;
    @Test
    public void CProducts() throws InterruptedException {
        setupDriver();
        Products products = new Products(chromeDriver);
        products.selectProduct();
        assertEquals("https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2",products.url);
        getPrice = products.productPrice;
        logger.info("Second page assertion passed");
    }
    @Test
    public void DBasket() throws InterruptedException {
        setupDriver();
        Basket basket = new Basket(chromeDriver);
        basket.basketOperations();
        assertEquals(getPrice,basket.basketPrice);
        logger.info("prices are equal");
        assertEquals("2",basket.itemAmount);
        logger.info("amount is 2");
        assertTrue(basket.empty.contains("bulunmamaktadır."));
        logger.info("Basket is empty");
    }
}
