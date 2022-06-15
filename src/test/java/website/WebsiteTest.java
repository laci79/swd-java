package website;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// teszt osztály
@Slf4j
@SeleniumTest
@ExtendWith(LoggingExtension.class)
class WebsiteTest {

    @Test
    @DisplayName("Test stting the border of the input element")
    void testSetBorder(WebDriver driver) {
        driver.get("http://127.0.0.1:5500/index.html");
        WebElement input = driver.findElement(By.id("field-to-validate"));  // ide var is jó
        String value = input.getText();                                     // ide var is jó
        if (value.equals("")) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].style['border'] = '3px solid red';", input);

        }
        System.out.println("End");
    }

//    private static final Logger log = LoggerFactory.getLogger(WebsiteTest.class);

    // teszteset
    @Test
    void testSearch(WebDriver driver) {

        // when
        driver.manage().window().maximize();
        driver.findElement(By.id("id-search-field")).click();
        driver.findElement(By.id("id-search-field")).sendKeys("testing");
        driver.findElement(By.id("submit")).click();
        log.debug("Click on GO button");

        // then
        String result = driver.findElement(By.cssSelector("h3:nth-child(2)")).getText();
        assertEquals("Results", result);
    }

    @Test
    void testPsf(WebDriver driver) {
        driver.findElement(By.linkText("PSF")).click();
        log.debug("Click on m button");
        assertEquals("Python Software Foundation", driver.getTitle());

    }
}
