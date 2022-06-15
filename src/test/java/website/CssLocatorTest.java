package website;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SeleniumExtension.class)
public class CssLocatorTest {

    @Test
    void cssLocatorTest(WebDriver driver) {
        driver.findElement(By.cssSelector("#name-input")).click();
        driver.findElement(By.cssSelector("#name-input")).sendKeys("John");
        driver.findElement(By.cssSelector("#submit-button")).click();
        String message = driver.findElement(By.cssSelector("#message")).getText();

        assertEquals("John", message);
    }
}
