
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;

public class LoginPage
{
    WebDriverWait wait;
    protected WebDriver webDriver;
    private By dropDown = By.xpath("//*[@class='gekhq4-4 egoSnI']");
    private By loginButton = By.xpath("//*[@class='sc-12t95ss-3 fDarBX']");
    private By facebookButton = By.xpath("//*[@class='faux-fb-btn']");
    private By emailArea = By.id("email");
    private By passwordArea = By.id("pass");
    private By facebookLogin = By.id("loginbutton");
    public LoginPage(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver,60);
    }

    public LoginPage loginPage(String username, String password) throws InterruptedException
    {
        Thread.sleep(60);
        wait.until(ExpectedConditions.elementToBeClickable(dropDown));
        webDriver.findElement(dropDown).click();
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        webDriver.findElement(loginButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(facebookButton));
        webDriver.findElement(facebookButton).click();
        String parentWindow = webDriver.getWindowHandle();
        Set<String> windows = webDriver.getWindowHandles();
        Iterator<String> i = windows.iterator();
        while (i.hasNext())
        {
            String childWindow = i.next();
            if(!parentWindow.equals(childWindow))
            {
                webDriver.switchTo().window(childWindow);
            }
        }
        webDriver.findElement(emailArea).sendKeys(username);
        Thread.sleep(200);
        webDriver.findElement(passwordArea).sendKeys(password);
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(facebookLogin));
        webDriver.findElement(facebookLogin).click();
        webDriver.close();
        webDriver.switchTo().window(parentWindow);
        Thread.sleep(500);
        return new LoginPage(webDriver);

    }
}
