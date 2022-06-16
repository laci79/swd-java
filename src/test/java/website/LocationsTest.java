package website;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumExtension.class)
@Slf4j
class LocationsTest {

    @Test
    @DisplayName("Create location, assert, then find in table")
    void createLocationsTest(WebDriver driver) {
        driver.get("http://localhost:8080");

        var createLocationLink = driver.findElement(By.id("create-location-link"));
        createLocationLink.click();

        var locationNameField = driver.findElement(By.id("location-name"));
        String name = "Training360";
        locationNameField.sendKeys(name);

        var locationCords = driver.findElement(By.id("location-coords"));
        locationCords.sendKeys("47.19,19");

        var createButton = driver.findElement(By.cssSelector("input.btn-primary[value='Create location']"));
        createButton.click();

        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        var message = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("message-div"))));
        assertEquals("Location has been created", message.getText());

        wait.until(d ->
                d.findElements(By.cssSelector("tr > td:nth-child(2)"))
                        .stream().map(WebElement::getText)
                        .anyMatch(t -> t.equals(name))
        );
    }

    LocationsPageObject page;

    @BeforeEach
    void initPage(WebDriver driver) {
        page = new LocationsPageObject(driver);
    }

    @Test
    void testCreateWithPageObject(WebDriver driver) {
        page
                .go()
                .clickOnCreateLocation()
                .fillForm("Test Location" + UUID.randomUUID(), "1,1");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/MOCK_DATA.csv", numLinesToSkip = 1)
    void testCreateLocationDDT(String name, String lat, String lon) {

        log.debug("Location: {} ({},{})", name, lat, lon);

        page
                .go()
                .clickOnCreateLocation()
                .fillForm(name, lat + "," + lon)
                .clickOnCreateButton();
    }

    @Test
    void testEdit() {
        var fixture = new Locat
    }
}
