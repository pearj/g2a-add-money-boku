package selenium.g2a.add.money;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class G2aAddMoneyTest {

    @Test
    public void addMoney() throws Throwable {

        System.setProperty("webdriver.chrome.driver", "C:\\path\\to\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        openPayPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.ignoring(NoSuchElementException.class);
        
        By tryAgain = By.id("try_again");
        while(true) {
            attemptPay(driver, wait);
            WebElement tryA = getElementFromSelector(driver, wait, tryAgain);
            tryA.click();
        }
    }

    private void openPayPage(ChromeDriver driver) {
        driver.get("https://pay.g2a.com/funds/add");
        driver.manage()
                .addCookie(new Cookie.Builder("g2aSSO",
                        "<paste your g2aSSO cookie here>")
                                .domain(".g2a.com").build());
        driver.get("https://pay.g2a.com/funds/add");
    }

    private void attemptPay(ChromeDriver driver, WebDriverWait wait) throws InterruptedException {
        
        By addFundsId = By.id("add-funds-form");
        wait.until(ExpectedConditions.visibilityOfElementLocated(addFundsId));

        WebElement amount = driver.findElementById("amount");
//        amount.sendKeys("17.38");
        amount.sendKeys("23.17");

        Select cur = new Select(driver.findElementById("currency"));
        cur.selectByValue("AUD");

        WebElement addFundsButton = driver.findElement(addFundsId);

        addFundsButton.submit();

        By bokuSelector = By.cssSelector("a[data-target=\"boku\"]");
        WebElement bokuButton = getElementFromSelector(driver, wait, bokuSelector);
        bokuButton.click();

        WebElement continuePay = getElementFromSelector(driver, wait, By.cssSelector("#continue-pay:not(.disabled)"));
        System.out.println(continuePay.getAttribute("innerHTML").toString());
        continuePay.click();
    }

    private WebElement getElementFromSelector(ChromeDriver driver, WebDriverWait wait, By elementSelector) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementSelector));
        return driver.findElement(elementSelector);
    }
}
