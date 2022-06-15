package website;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SeleniumExtension.class)
@Slf4j
public class ComponentsTest {

    @Test
    void testListExists(WebDriver driver) {
        driver.get("http://127.0.0.1:5500/components/index.html");
        var items = driver.findElements(By.tagName("li"));
//        List<String> texts = new ArrayList<>();
//        for (var item : items) {
//            texts.add(item.getText());
//        }




//        var texts = items.stream().map(WebElement::getText).toList();
//        log.debug("List items: " + texts);
//
//        assertEquals(List.of("One", "Two", "Three", "Four"), texts);

        assertThat(items)
                .extracting(WebElement::getText)
                .hasSize(4)
                .contains("One", "Three");
    }

    @Test
    void testNames(WebDriver driver) {
        driver.get("http://127.0.0.1:5500/components/index.html");
        var items = driver.findElements(By.cssSelector("td:nth-child(2)"));
        log.debug("List items: " + items.get(1).getText());
        assertThat(items)
                .extracting(WebElement::getText)
                .contains("Jack", "John", "Jane");
    }

    @Test
    void testInputField(WebDriver driver) {
        driver.get("http://127.0.0.1:5500/components/index.html");
        var input = driver.findElement(By.name("text"));
        input.sendKeys("Hello");

        log.debug(input.getDomProperty("value"));
    }

    @Test
    void testCheckbox(WebDriver driver){
        driver.get("http://127.0.0.1:5500/components/index.html");
        var checkbox = driver.findElement(By.name("checkbox"));
        var label = driver.findElement(By.cssSelector("[for=checkbox1]"));
        label.click();
        assertTrue(checkbox.isSelected());
        assertFalse(driver.findElement(By.name("disabled-checkbox")).isEnabled());
    }

    @Test
    void testRadioButton(WebDriver driver){
        driver.get("http://127.0.0.1:5500/components/index.html");

        driver.findElement(By.id("radiobtn1")).click();
        var count = driver.findElements(By.cssSelector("input[type=radio"))
                .stream()
                .filter(we -> we.isSelected())
                .count();
        assertEquals(1, count);
    }

    @Test
    void testSelect(WebDriver driver) {
        driver.get("http://127.0.0.1:5500/components/index.html");
        var select = new Select(driver.findElement(By.id("dropdown")));
        select.selectByValue("option3");
        assertEquals("Option 3", select.getFirstSelectedOption().getText());
    }

}
