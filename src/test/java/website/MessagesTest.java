package website;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumExtension.class)
@Slf4j
public class MessagesTest {

    @Test
    void messagesTest(WebDriver driver) {
        driver.get("http://127.0.0.1:5500/components/messages/index.html");
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        var button = driver.findElement(By.id("liveAlertTimeoutBtn"));
        button.click();
        var message = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".alert")));
        log.debug(message.getText());
        assertThat(message.getText().startsWith("Nice"));

    }
}
