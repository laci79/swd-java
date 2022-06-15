package website;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

@ExtendWith(SeleniumExtension.class)
public class GridTest {

    @Test
    void gridTest(WebDriver driver) throws IOException {
        driver.get("http://127.0.0.1:5500/grid/index.html");
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.move(file.toPath(), Path.of("./screenshot.png"), StandardCopyOption.REPLACE_EXISTING);
        var cell5 =driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)"));
        var cell2 = driver.findElement(with(By.tagName("td")).below(cell5));

        File element = (driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)"))).getScreenshotAs(OutputType.FILE);
        Files.move(element.toPath(), Path.of("./screenshot2.png"), StandardCopyOption.REPLACE_EXISTING);
        assertEquals("2", cell2.getText());
    }
}
